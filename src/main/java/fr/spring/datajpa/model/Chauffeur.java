package fr.spring.datajpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import fr.spring.datajpa.enums.Role;

@Entity
public class Chauffeur extends AbstractUser {
	
	@OneToMany(mappedBy="chauffeur")
	private Set<Reservation> reservations;
	
	public Chauffeur() {
		reservations = new HashSet<Reservation>();
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	@Override
	public Role getRole() {
		return Role.CHAUFFEUR;
	}
	
}
