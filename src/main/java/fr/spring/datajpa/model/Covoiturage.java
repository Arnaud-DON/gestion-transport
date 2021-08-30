package fr.spring.datajpa.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Covoiturage extends AbstractTravel {
	
	@ManyToMany
	@JsonIgnore
	@JoinTable(name="USER_TRAVEL",
		joinColumns=@JoinColumn(name="ID_TRAVEL", referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="ID_USER", referencedColumnName="ID")
	)
	private Set<Collaborateur> passagers;

	@Column(name = "NB_PASSAGERS")
	private int nbPassagers;
	
	@Embedded
	private VehiculePrivate vehicule;
	
	public Covoiturage() {
		passagers = new HashSet<Collaborateur>();
	}
	
	

	public Covoiturage(Collaborateur organisateur, String adresseDepart, String adresseDestination,
			int duree, LocalDateTime date, VehiculePrivate vehicule) {
		setOrganisateur(organisateur);
		setAdresseDepart(adresseDepart);
		setAdresseDestination(adresseDestination);
		setDuree(duree);
		setDate(date);
		this.vehicule = vehicule;
		this.passagers = new HashSet<Collaborateur>();
		passagers.add(organisateur);
		this.nbPassagers = 1;
	}

	public int getNbPassagers() {
		return nbPassagers;
	}

	public void setNbPassagers(int nbPassagers) {
		this.nbPassagers = nbPassagers;
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



	public void addPassager(Collaborateur organisateur) {
		this.passagers.add(organisateur);
		this.nbPassagers++;
	}
	
}
