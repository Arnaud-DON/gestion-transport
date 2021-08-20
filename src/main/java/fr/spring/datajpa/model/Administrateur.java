package fr.spring.datajpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Administrateur extends Collaborateur {

	@OneToMany(mappedBy="responsable")
	private Set<VehiculeService> vehicules;
	
	public Administrateur() {
		vehicules = new HashSet<VehiculeService>();
	}
}
