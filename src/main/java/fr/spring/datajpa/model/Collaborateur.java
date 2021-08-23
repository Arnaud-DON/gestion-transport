package fr.spring.datajpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Collaborateur extends AbstractUser {
	
	@ManyToMany
	@JoinTable(name="USER_TRAVEL",
		joinColumns=@JoinColumn(name="ID_USER", referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="ID_TRAVEL", referencedColumnName="ID")
	)
	private Set<Covoiturage> travels;
	
	@OneToMany(mappedBy="organisator")
	private Set<AbstractTravel> organizedTravels;
	
	public Collaborateur() {
		travels = new HashSet<Covoiturage>();
		organizedTravels = new HashSet<AbstractTravel>();
	}

	public Set<Covoiturage> getTravels() {
		return travels;
	}

	public Set<AbstractTravel> getOrganizedTravels() {
		return organizedTravels;
	}
	
}
