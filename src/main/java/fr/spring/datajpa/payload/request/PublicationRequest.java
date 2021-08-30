package fr.spring.datajpa.payload.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
 
public class PublicationRequest {
    
	@NotBlank
    private String adresseDepart;
    
    @NotBlank
    private String adresseDestination;
    
    @NotBlank
    private int duree;
    
    @NotBlank
    private int distance;
    
    @NotBlank
    private String immatriculation;
 
    @NotBlank
    private String marque;
    
    @NotBlank
    private String modele;
    
    @NotBlank
    private int nbPlaces;

	@NotBlank
    private String date;
    
    @NotBlank
    private String heures;
    
    @NotBlank
    private String minutes;

	public String getAdresseDepart() {
		return adresseDepart;
	}

	public String getAdresseDestination() {
		return adresseDestination;
	}

	public int getDuree() {
		return duree;
	}

	public int getDistance() {
		return distance;
	}
    
    public int getNbPlaces() {
		return nbPlaces;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public String getMarque() {
		return marque;
	}

	public String getModele() {
		return modele;
	}

	public String getDate() {
		return date;
	}

	public String getHeures() {
		return heures;
	}

	public String getMinutes() {
		return minutes;
	}
	
	public LocalDateTime getDateTime() {
		return LocalDateTime.parse(date+"T"+heures+":"+minutes+":00");
	}
	
}