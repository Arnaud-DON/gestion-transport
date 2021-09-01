package fr.spring.datajpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.spring.datajpa.enums.Role;
import fr.spring.datajpa.model.AbstractUser;
import fr.spring.datajpa.model.Administrateur;
import fr.spring.datajpa.model.Chauffeur;
import fr.spring.datajpa.model.Collaborateur;
import fr.spring.datajpa.payload.request.LoginRequest;
import fr.spring.datajpa.payload.request.SignupRequest;
import fr.spring.datajpa.payload.response.JwtResponse;
import fr.spring.datajpa.payload.response.MessageResponse;
import fr.spring.datajpa.repository.UserRepository;
import fr.spring.datajpa.security.jwt.JwtUtils;
import fr.spring.datajpa.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getMail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String tokenJwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		Role role = userDetails.getRole();

		return ResponseEntity.ok(new JwtResponse(tokenJwt,
												 userDetails.getEmail(), 
												 role,
												 userDetails.getName(),
												 userDetails.getFirstName(),
												 userDetails.getTel(),
												 userDetails.getImgUrl()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		String mail = signUpRequest.getMail();
		
		if (userRepository.existsByMail(mail)) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		String name = signUpRequest.getName();
		String firstName = signUpRequest.getFirstName();
		String tel = signUpRequest.getTel();
		String imgUrl = signUpRequest.getImgUrl();
		String pwd = signUpRequest.getPassword();
		Role role = signUpRequest.getRole();
		
		// Create new user's account
		AbstractUser user = null;
		
		switch(role) {

		case COLLABORATEUR:
			user = new Collaborateur();
			break;
			
		case ADMIN:
			user = new Administrateur();
			break;
			
		case CHAUFFEUR:
			user = new Chauffeur();
			break;
			
		default:
			throw new IllegalArgumentException("Unexpected value please select a correct Role : " + signUpRequest.getRole());
		}
		
		user.setFirstName(firstName);
		user.setImgUrl(imgUrl);
		user.setMail(mail);
		user.setName(name);
		user.setPassword(encoder.encode(pwd));
		user.setTel(tel);
		
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	public static AbstractUser getCurrentUtilisateur(UserRepository userRepository) throws Exception{

		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		AbstractUser currentUser = userRepository.findByMail(userDetails.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("Authentication error, please logout and login back. If the problem persists please contact an administrator."));
		
		return currentUser;
	}
}