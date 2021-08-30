package fr.spring.datajpa.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.spring.datajpa.model.AbstractTravel;
import fr.spring.datajpa.model.AbstractUser;
import fr.spring.datajpa.model.Collaborateur;
import fr.spring.datajpa.model.Covoiturage;
import fr.spring.datajpa.model.VehiculePrivate;
import fr.spring.datajpa.payload.request.PublicationRequest;
import fr.spring.datajpa.repository.TravelRepository;
import fr.spring.datajpa.repository.UserRepository;
import fr.spring.datajpa.security.services.UserDetailsImpl;

import fr.spring.datajpa.repository.CovoiturageRepository;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/travel")
public class TravelController {

	@Autowired
	TravelRepository travelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/newAnnonce")
	public ResponseEntity<?> createTutorial(@Valid @RequestBody PublicationRequest pubRequest) {
		try {
			
			AbstractUser currentUser = getCurrentUtilisateur();
			Collaborateur organisateur = null;
			if(currentUser instanceof Collaborateur) {
				organisateur = (Collaborateur) currentUser;
			}
			else {
				throw new Exception("Unauthorized action !");
			}
			
			String immatriculation = pubRequest.getImmatriculation();
			String marque = pubRequest.getMarque();
			String modele = pubRequest.getModele();
			int nbPlaces = pubRequest.getNbPlaces();
			
			VehiculePrivate vehicule = new VehiculePrivate(
							immatriculation,
							marque,
							modele,
							nbPlaces
						);

			String adresseDepart = pubRequest.getAdresseDepart();
			String adresseDestination = pubRequest.getAdresseDestination();
			int duree = pubRequest.getDuree();
			LocalDateTime dateDepart = pubRequest.getDateTime();
			
			AbstractTravel concurrentTravel = organisateur.getConcurrentTravel(dateDepart, duree);
			if(concurrentTravel != null) {
				throw new Exception("Vous avez déjà un voyage prévu sur cette période : (date: "
									+ concurrentTravel.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"))
									+ ", durée : "+concurrentTravel.getDuree()+" minutes) ");
			}
			
			Covoiturage covoit = new Covoiturage(
							organisateur,
							adresseDepart,
							adresseDestination,
							duree,
							dateDepart,
							vehicule
						);
			
			covoit = travelRepository.save(covoit);
			return new ResponseEntity<Covoiturage>(covoit, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	private AbstractUser getCurrentUtilisateur() throws Exception{

		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		AbstractUser currentUser = userRepository.findByMail(userDetails.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("Authentication error, please logout and login back. If the problem persists please contact an administrator."));
		
		return currentUser;
	}

	@Autowired
	CovoiturageRepository covoiturageRepository;

	@GetMapping("/listcovoiturage")
	public List<Covoiturage> getallcovoit(){return covoiturageRepository.findAll();}

}
