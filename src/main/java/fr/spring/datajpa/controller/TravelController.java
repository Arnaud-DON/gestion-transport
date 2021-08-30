package fr.spring.datajpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.spring.datajpa.model.AbstractUser;
import fr.spring.datajpa.model.Collaborateur;
import fr.spring.datajpa.model.Covoiturage;
import fr.spring.datajpa.model.VehiculePrivate;
import fr.spring.datajpa.payload.request.PublicationRequest;
import fr.spring.datajpa.repository.TravelRepository;
import fr.spring.datajpa.repository.UserRepository;
import fr.spring.datajpa.security.jwt.JwtUtils;
import fr.spring.datajpa.security.services.UserDetailsImpl;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/travel")
public class TravelController {

	@Autowired
	TravelRepository travelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/newAnnonce")
	public ResponseEntity<?> createTutorial(@Valid @RequestBody PublicationRequest pubRequest) {
		try {
			UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			AbstractUser currentUser = userRepository.findByMail(userDetails.getEmail())
					.orElseThrow(() -> new UsernameNotFoundException("Authentication error, please logout and login back. If the problem persists please contact an administrator."));
			
			Collaborateur organisateur = null;
			if(currentUser instanceof Collaborateur) {
				organisateur = (Collaborateur) currentUser;
			}
			else {
				throw new Exception("Unauthorized action !");
			}
			
			VehiculePrivate vehicule = new VehiculePrivate(
							pubRequest.getImmatriculation(),
							pubRequest.getMarque(),
							pubRequest.getModele(),
							pubRequest.getNbPlaces()
						);
			
			Covoiturage covoit = new Covoiturage(
							organisateur,
							pubRequest.getAdresseDepart(),
							pubRequest.getAdresseDestination(),
							pubRequest.getDuree(),
							pubRequest.getDateTime(),
							vehicule
						);
			
			covoit = travelRepository.save(covoit);
			return new ResponseEntity<Covoiturage>(covoit, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
