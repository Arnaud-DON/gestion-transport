package fr.spring.datajpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.spring.datajpa.enums.Role;

@Entity
public class Administrateur extends Collaborateur {

	@JsonIgnore
	@OneToMany(mappedBy="responsable")
	private Set<VehiculeService> vehicules;
	
	public Administrateur() {
		vehicules = new HashSet<VehiculeService>();
	}

	public Set<VehiculeService> getVehicules() {
		return vehicules;
	}

	@Override
	public Role getRole() {
		return Role.ADMIN;
	}
	
}
