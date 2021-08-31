package fr.spring.datajpa.DTO;

import fr.spring.datajpa.enums.TravelStatus;
import fr.spring.datajpa.model.Collaborateur;
import fr.spring.datajpa.model.VehiculePrivate;

import java.time.LocalDateTime;
import java.util.Set;

public class CovoiturageDTO {
    private Set<Collaborateur> passagers;
    private int nbPassagers;
    private VehiculePrivate vehicule;
    private Collaborateur organisateur;
    private String adresseDepart;
    private String adresseDestination;
    private LocalDateTime date;
    private int duree;
    private TravelStatus travelStatus;

    public CovoiturageDTO(Set<Collaborateur> passagers, int nbPassagers, VehiculePrivate vehicule, Collaborateur organisateur, String adresseDepart, String adresseDestination, LocalDateTime date, int duree, TravelStatus travelStatus) {
        this.passagers = passagers;
        this.nbPassagers = nbPassagers;
        this.vehicule = vehicule;
        this.organisateur = organisateur;
        this.adresseDepart = adresseDepart;
        this.adresseDestination = adresseDestination;
        this.date = date;
        this.duree = duree;
        this.travelStatus = travelStatus;
    }

    public CovoiturageDTO() {

    }

    public Set<Collaborateur> getPassagers() {
        return passagers;
    }

    public void setPassagers(Set<Collaborateur> passagers) {
        this.passagers = passagers;
    }

    public int getNbPassagers() {
        return nbPassagers;
    }

    public void setNbPassagers(int nbPassagers) {
        this.nbPassagers = nbPassagers;
    }

    public VehiculePrivate getVehicule() {
        return vehicule;
    }

    public void setVehicule(VehiculePrivate vehicule) {
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
