package fr.spring.datajpa.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.spring.datajpa.DTO.CovoiturageDTO;
import fr.spring.datajpa.model.AbstractTravel;
import fr.spring.datajpa.model.Collaborateur;
import fr.spring.datajpa.model.Covoiturage;
import fr.spring.datajpa.model.VehiculePrivate;
import fr.spring.datajpa.payload.request.PublicationRequest;
import fr.spring.datajpa.repository.CovoiturageRepository;
import fr.spring.datajpa.repository.TravelRepository;
import fr.spring.datajpa.repository.UserRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/travel")
public class TravelController {

	@Autowired
	TravelRepository travelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/newAnnonce")
	public ResponseEntity<?> publishAnnonce(@Valid @RequestBody PublicationRequest pubRequest) {
		try {
			
			Collaborateur organisateur = null;
			try {
				organisateur = (Collaborateur) AuthController.getCurrentUtilisateur(userRepository);
			}
			catch(ClassCastException e) {
				throw new Exception("Unauthorized action ");
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
	
	@Autowired
	CovoiturageRepository covoiturageRepository;

	@GetMapping("/listcovoiturage")
	public List<CovoiturageDTO> extraireCovoit(@RequestParam(value = "type", required = false) String type)
	throws Exception{

		List<Covoiturage> covoiturages = covoiturageRepository.findCovoiturageCollaborateur(AuthController.getCurrentUtilisateur(userRepository).getMail());

		List<CovoiturageDTO> dtos = new ArrayList<>();
		for (Covoiturage covoit: covoiturages) {
			CovoiturageDTO dto = new CovoiturageDTO();
			dto.setAdresseDepart(covoit.getAdresseDepart());
			dto.setDate(covoit.getDate());
			dto.setAdresseDestination(covoit.getAdresseDestination());
			dto.setDuree(covoit.getDuree());
			dto.setOrganisateur(covoit.getOrganisateur());
			dto.setNbPassagers(covoit.getNbPassagers());
			dtos.add(dto);
		}
		return dtos;}

	@GetMapping("/listAvailablesCovoiturages")
	public ResponseEntity<?> getAvailableCovoits(){
		try {
			
			Collaborateur currentUser = null;
			try {
				currentUser = (Collaborateur) AuthController.getCurrentUtilisateur(userRepository);
			}
			catch(ClassCastException e) {
				throw new Exception("Unauthorized action ");
			}
			List<Covoiturage> availableCovoits = covoiturageRepository.findAvailableCovoitsForUserID(currentUser.getId());
		
			return new ResponseEntity<List<Covoiturage>>(
					availableCovoits, HttpStatus.CREATED);
		}
		catch(Exception e){
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
}
