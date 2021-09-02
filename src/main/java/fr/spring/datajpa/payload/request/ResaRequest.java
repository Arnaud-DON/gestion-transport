package fr.spring.datajpa.payload.request;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.validation.constraints.NotBlank;

import fr.spring.datajpa.model.VehiculeService;

public class ResaRequest {

	@NotBlank
    private String dateAller;
    
    @NotBlank
    private String heuresAller;
    
    @NotBlank
    private String minutesAller;

	@NotBlank
    private String dateRetour;
    
    @NotBlank
    private String heuresRetour;
    
    @NotBlank
    private String minutesRetour;
    
    @NotBlank
    private VehiculeService vehicule;

	public VehiculeService getVehicule() {
		return vehicule;
	}

	public LocalDateTime getDateTimeAller() {
		return LocalDateTime.parse(dateAller+"T"+heuresAller+":"+minutesAller+":00");
	}
	
	public LocalDateTime getDateTimeRetour() {
		return LocalDateTime.parse(dateRetour+"T"+heuresRetour+":"+minutesRetour+":00");
	}
	
	public int getDuree() {
		return Math.toIntExact(getDateTimeAller().until(getDateTimeRetour(), ChronoUnit.MINUTES));
	}

	public String getDateAller() {
		return dateAller;
	}

	public String getDateRetour() {
		return dateRetour;
	}

	public String getHeuresAller() {
		return heuresAller;
	}

	public String getMinutesAller() {
		return minutesAller;
	}

	public String getHeuresRetour() {
		return heuresRetour;
	}

	public String getMinutesRetour() {
		return minutesRetour;
	}

}
