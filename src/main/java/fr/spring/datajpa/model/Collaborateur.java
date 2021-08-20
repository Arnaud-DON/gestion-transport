package fr.spring.datajpa.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Collaborateur extends AbstractUser {
	
	@ManyToMany
	@JoinTable(name="USER_TRAVEL",
		joinColumns=@JoinColumn(name="ID_USER", referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="ID_TRAVEL", referencedColumnName="ID")
	)
	private Set<AbstractTravel> travels;
	
	
}
