package fr.spring.datajpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class VehiculePrivate extends AbstractVehicule {
	
	@Column(name="TOTAL_PLACES")
	private int totalPlaces;
}
