package fr.spring.datajpa.payload.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

public class VoitureRequest {

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

	public LocalDateTime getDateTimeAller() {
		return LocalDateTime.parse(dateAller+"T"+heuresAller+":"+minutesAller+":00");
	}
	
	public LocalDateTime getDateTimeRetour() {
		return LocalDateTime.parse(dateRetour+"T"+heuresRetour+":"+minutesRetour+":00");
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
