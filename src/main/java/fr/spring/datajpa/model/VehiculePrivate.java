package fr.spring.datajpa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VehiculePrivate {
	
	@Column(name="IMMATRICULATION")
	private String immatriculation;
	
	@Column(name="MARQUE")
	private String marque;
	
	@Column(name="MODEL")
	private String modele;
	
	@Column(name="TOTAL_PLACES")
	private int totalPlaces;
	
	public VehiculePrivate() {

	}

	public VehiculePrivate(String immatriculation, String marque, String modele, int nbPlaces) {
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.totalPlaces = nbPlaces;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public int getTotalPlaces() {
		return totalPlaces;
	}

	public void setTotalPlaces(int totalPlaces) {
		this.totalPlaces = totalPlaces;
	}
	
}
