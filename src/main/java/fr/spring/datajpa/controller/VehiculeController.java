package fr.spring.datajpa.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RestController;

import fr.spring.datajpa.enums.Category;
import fr.spring.datajpa.enums.VehiculeStatus;
import fr.spring.datajpa.model.AbstractUser;
import fr.spring.datajpa.model.Administrateur;
import fr.spring.datajpa.model.Collaborateur;
import fr.spring.datajpa.model.Reservation;
import fr.spring.datajpa.model.VehiculeService;
import fr.spring.datajpa.payload.request.AddVehiculeRequest;
import fr.spring.datajpa.payload.request.ResaRequest;
import fr.spring.datajpa.payload.request.VoitureRequest;
import fr.spring.datajpa.repository.ReservationRepository;
import fr.spring.datajpa.repository.UserRepository;
import fr.spring.datajpa.repository.VehiculeRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin/vehicule")
public class VehiculeController {

	@Autowired
	VehiculeRepository vehiculeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/listVehicules")
	public ResponseEntity<?> getAllVehicules(){
		try {
			return new ResponseEntity<List<VehiculeService>>(
					vehiculeRepository.findAll(), HttpStatus.CREATED);
		}
		catch(Exception e){
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping("/newVehicule")
	public ResponseEntity<?> createVehicule(@Valid @RequestBody AddVehiculeRequest addVehiculeRequest) {
		try {
			
			AbstractUser currentUser = AuthController.getCurrentUtilisateur(userRepository);
			Administrateur responsable = null;
			if(currentUser instanceof Administrateur) {
				responsable = (Administrateur) currentUser;
			}
			else {
				throw new Exception("Unauthorized action ");
			}
			
			String immatriculation = addVehiculeRequest.getImmatriculation();
			
			if(vehiculeRepository.existsByImmatriculation(immatriculation)) {
				throw new Exception("Un véhicule avec cet immatriculation existe déjà ");
			}
			
			String marque = addVehiculeRequest.getMarque();
			String modele = addVehiculeRequest.getModele();
			Category categorie = addVehiculeRequest.getCategorie();
			String imgUrl = addVehiculeRequest.getImgUrl();
			
			VehiculeService vehicule = new VehiculeService(
							immatriculation,
							marque,
							modele,
							categorie,
							imgUrl
						);
			vehicule.setResponsable(responsable);
			vehicule.setStatus(VehiculeStatus.CREATED);
			
			vehicule = vehiculeRepository.save(vehicule);
			return new ResponseEntity<VehiculeService>(vehicule, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/listAvailablesVehicules")
	public ResponseEntity<?> getAvailableVehiculesForDates(@Valid @RequestBody VoitureRequest request){
		try {
			@SuppressWarnings("unused")
			Collaborateur currentUser = null;
			try {
				currentUser = (Collaborateur) AuthController.getCurrentUtilisateur(userRepository);
			}
			catch(ClassCastException e) {
				throw new Exception("Unauthorized action ");
			}
			List<VehiculeService> voituresDispos = vehiculeRepository.findLesVoituresQuiPeuventRouler();
			List<VehiculeService> result = new ArrayList<VehiculeService>();
			
			LocalDateTime dateAller = request.getDateTimeAller();
			LocalDateTime dateRetour = request.getDateTimeRetour();
			
			for(VehiculeService voiture : voituresDispos) {
				if(voiture.getConcurrentTravel(dateAller, dateRetour) == null){
					result.add(voiture);
				}
			}
			
			return new ResponseEntity<List<VehiculeService>>(
					result, HttpStatus.CREATED);
		}
		catch(Exception e){
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@Autowired
	ReservationRepository resaRepo;

	@PostMapping("/reserverVehicule")
	public ResponseEntity<?> reserverVehicules(@Valid @RequestBody ResaRequest request){
		try {
			
			@SuppressWarnings("unused")
			Collaborateur currentUser = null;
			try {
				currentUser = (Collaborateur) AuthController.getCurrentUtilisateur(userRepository);
			}
			catch(ClassCastException e) {
				throw new Exception("Unauthorized action ");
			}
			
			VehiculeService vehicule = request.getVehicule();
			if(vehicule.getStatus() == VehiculeStatus.IN_SERVICE) {
				vehicule.setStatus(VehiculeStatus.RESERVED);
			}
			
			Reservation resa = new Reservation();
			resa.setDate(request.getDateTimeAller());
			resa.setDuree(request.getDuree());
			resa.setVehicule(vehicule);
//			resa.setWithDriver(request.withDriver);
			resaRepo.save(resa);
			vehicule = vehiculeRepository.save(vehicule);
			
			return new ResponseEntity<VehiculeService>(vehicule, HttpStatus.CREATED);
		}
		catch(Exception e){
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
