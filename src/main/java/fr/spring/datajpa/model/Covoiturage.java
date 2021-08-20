package fr.spring.datajpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Covoiturage extends AbstractTravel {
	
	@ManyToMany
	@JoinTable(name="USER_TRAVEL",
		joinColumns=@JoinColumn(name="ID_TRAVEL", referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="ID_USER", referencedColumnName="ID")
	)
	private Set<Collaborateur> passagers;

	@Column(name = "TRAVELERS_NUMBER")
	private int nbTravelers;
	
	@Embedded
	private VehiculePrivate vehicule;
	
	public Covoiturage() {
		passagers = new HashSet<Collaborateur>();
	}

	public int getNbTravelers() {
		return nbTravelers;
	}

	public void setNbTravelers(int nbTravelers) {
		this.nbTravelers = nbTravelers;
	}

	public Set<Collaborateur> getPassagers() {
		return passagers;
	}

	public VehiculePrivate getVehicule() {
		return vehicule;
	}

	public void setVehicule(VehiculePrivate vehicule) {
		this.vehicule = vehicule;
	}
	
}
