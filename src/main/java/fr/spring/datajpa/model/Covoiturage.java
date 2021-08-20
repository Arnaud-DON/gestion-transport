package fr.spring.datajpa.model;

import java.util.Set;

import javax.persistence.Column;
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
	
//	@Column(name="IMMATRICULATION")
//	private String immatriculation;
//	
//	@Column(name="BRAND")
//	private String brand;
//	
//	@Column(name="MODEL")
//	private String model;
//	
//	@Enumerated(EnumType.STRING)
//	@Column(name="CATEGORY")
//	private Category category;

	@Column(name = "TRAVELERS_NUMBER")
	private int nbTravelers;
}
