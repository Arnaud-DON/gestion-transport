package fr.spring.datajpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation extends AbstractTravel {

	@Column(name = "WITH_DRIVER")
	private boolean withDriver;

	@ManyToOne
	@JoinColumn(name="VEHICULE")
	private VehiculeService vehicule;
	
	@ManyToOne
	@JoinColumn(name="CHAUFFEUR")
	private Chauffeur chauffeur;
	
	public Reservation() {

	}

	public boolean isWithDriver() {
		return withDriver;
	}

	public void setWithDriver(boolean withDriver) {
		this.withDriver = withDriver;
	}

	public VehiculeService getVehicule() {
		return vehicule;
	}

	public void setVehicule(VehiculeService vehicule) {
		this.vehicule = vehicule;
	}

	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}
	
}
