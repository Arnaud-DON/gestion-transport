package fr.spring.datajpa.controller;

import fr.spring.datajpa.DTO.CovoiturageDTO;
import fr.spring.datajpa.DTO.ReservationDTO;
import fr.spring.datajpa.model.Covoiturage;
import fr.spring.datajpa.model.Reservation;
import fr.spring.datajpa.repository.ReservationRepository;
import fr.spring.datajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/travel")
public class ReservationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/listresavehiculeencours")
    public List<ReservationDTO> extraireCurrentCovoit(@RequestParam(value="type",required = false)String type)
            throws Exception{List<Reservation> reservations = reservationRepository.findCurrentReservationCollaborateur(AuthController.getCurrentUtilisateur(userRepository).getMail());

        List<ReservationDTO> dtos = new ArrayList<>();
        for (Reservation resa: reservations) {
            ReservationDTO dto = new ReservationDTO();
            dto.setAdresseDepart(resa.getAdresseDepart());
            dto.setDate(resa.getDate());
            dto.setAdresseDestination(resa.getAdresseDestination());
            dto.setDuree(resa.getDuree());
            dto.setOrganisateur(resa.getOrganisateur());
            dto.setId(resa.getId());
            dtos.add(dto);
        }
        return dtos;}
}
