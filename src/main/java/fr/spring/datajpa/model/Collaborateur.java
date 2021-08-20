package fr.spring.datajpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Collaborateur extends AbstractUser {
	
	@ManyToMany
	@JoinTable(name="USER_TRAVEL",
		joinColumns=@JoinColumn(name="ID_USER", referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="ID_TRAVEL", referencedColumnName="ID")
	)
	private Set<Covoiturage> travels;
	
	@OneToOne
	@JoinColumn(name = "VEHICULE_PERSO", referencedColumnName = "immatriculation")
	private VehiculePrivate vehiculePerso;
	
	@OneToMany(mappedBy="organisator")
	private Set<AbstractTravel> organizedTravels;
	
	public Collaborateur() {
		travels = new HashSet<Covoiturage>();
		organizedTravels = new HashSet<AbstractTravel>();
	}

	public VehiculePrivate getVehiculePerso() {
		return vehiculePerso;
	}

	public void setVehiculePerso(VehiculePrivate vehiculePerso) {
		this.vehiculePerso = vehiculePerso;
	}

	public Set<Covoiturage> getTravels() {
		return travels;
	}

	public Set<AbstractTravel> getOrganizedTravels() {
		return organizedTravels;
	}
	
}
