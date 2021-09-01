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
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.spring.datajpa.enums.TravelStatus;

@Entity
public class Covoiturage extends AbstractTravel {
	
	@ManyToMany
	@JoinTable(name="USER_TRAVEL",
		joinColumns=@JoinColumn(name="ID_COVOIT", referencedColumnName="id"),
		inverseJoinColumns=@JoinColumn(name="ID_PASSAGER", referencedColumnName="id")
	)
	@JsonIgnoreProperties("travels")
	private Set<Collaborateur> passagers;

	@Column(name = "NB_PASSAGERS")
	private int nbPassagers;

	@ManyToOne
	@JoinColumn(name="ORGANISATEUR")
    @JsonBackReference(value="organisateur")
	private Collaborateur organisateur;

	public Collaborateur getOrganisateur() {
		return organisateur;
	}
	
	

	public Covoiturage(Collaborateur organisateur, String adresseDepart, String adresseDestination,
			int duree, LocalDateTime date, VehiculePrivate vehicule) {
		setAdresseDepart(adresseDepart);
		setAdresseDestination(adresseDestination);
		setDuree(duree);
		setDate(date);
		setTravelStatus(TravelStatus.CREATED);
		this.organisateur = organisateur;
		this.vehicule = vehicule;
		this.passagers = new HashSet<Collaborateur>();
		this.nbPassagers = 0;
	}

	public void setOrganisateur(Collaborateur organisateur) {
		this.organisateur = organisateur;
	}
	
	@Embedded
	private VehiculePrivate vehicule;
	
	public Covoiturage() {
		passagers = new HashSet<Collaborateur>();
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



	public void addPassager(Collaborateur passager) {
		if(getNbPassagers() < vehicule.getTotalPlaces()) {
			this.passagers.add(passager);
			this.nbPassagers++;
		}
	}
	
}
