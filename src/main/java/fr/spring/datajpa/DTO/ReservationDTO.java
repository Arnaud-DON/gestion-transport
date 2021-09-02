package fr.spring.datajpa.DTO;

import fr.spring.datajpa.enums.TravelStatus;
import fr.spring.datajpa.model.Collaborateur;
import fr.spring.datajpa.model.VehiculePrivate;
import fr.spring.datajpa.model.VehiculeService;

import java.time.LocalDateTime;
import java.util.Set;

public class ReservationDTO {
    private Long id;
    private VehiculeService vehicule;
    private Collaborateur organisateur;
    private String adresseDepart;
    private String adresseDestination;
    private LocalDateTime date;
    private int duree;
    private TravelStatus travelStatus;

    public ReservationDTO() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehiculeService getVehicule() {
        return vehicule;
    }

    public void setVehicule(VehiculeService vehicule) {
        this.vehicule = vehicule;
    }

    public Collaborateur getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Collaborateur organisateur) {
        this.organisateur = organisateur;
    }

    public String getAdresseDepart() {
        return adresseDepart;
    }

    public void setAdresseDepart(String adresseDepart) {
        this.adresseDepart = adresseDepart;
    }

    public String getAdresseDestination() {
        return adresseDestination;
    }

    public void setAdresseDestination(String adresseDestination) {
        this.adresseDestination = adresseDestination;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public TravelStatus getTravelStatus() {
        return travelStatus;
    }

    public void setTravelStatus(TravelStatus travelStatus) {
        this.travelStatus = travelStatus;
    }
}
