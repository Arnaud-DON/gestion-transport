package fr.spring.datajpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class VehiculePrivate extends AbstractVehicule {
	
	@Column(name="TOTAL_PLACES")
	private int totalPlaces;
	
	public VehiculePrivate() {

	}

	public int getTotalPlaces() {
		return totalPlaces;
	}

	public void setTotalPlaces(int totalPlaces) {
		this.totalPlaces = totalPlaces;
	}
	
}
