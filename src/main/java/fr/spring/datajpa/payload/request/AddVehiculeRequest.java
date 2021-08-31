package fr.spring.datajpa.payload.request;

import javax.validation.constraints.NotBlank;

import fr.spring.datajpa.enums.Category;
 
public class AddVehiculeRequest {
    
    @NotBlank
    private String immatriculation;
 
    @NotBlank
    private String marque;
    
    @NotBlank
    private String modele;
    
    @NotBlank
    private Category categorie;
    
    @NotBlank
    private String imgUrl;

	public String getImmatriculation() {
		return immatriculation;
	}

	public String getMarque() {
		return marque;
	}

	public String getModele() {
		return modele;
	}

	public Category getCategorie() {
		return categorie;
	}

	public String getImgUrl() {
		return imgUrl;
	}

}