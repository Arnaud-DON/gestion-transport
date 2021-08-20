package fr.spring.datajpa.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Chauffeur extends AbstractUser {

	@OneToMany(mappedBy="chauffeur")
	private Set<Reservation> reservations;
}
