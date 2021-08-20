package fr.spring.datajpa.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Collaborateur extends AbstractUser {
	
	@ManyToMany
	@JoinTable(name="USER_TRAVEL",
		joinColumns=@JoinColumn(name="ID_USER", referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="ID_TRAVEL", referencedColumnName="ID")
	)
	private Set<AbstractTravel> travels;
	
	@OneToOne
	@JoinColumn(name = "VEHICUL_PERSO", referencedColumnName = "immatriculation")
	private VehiculePrivate vehiculePerso;
	
}
