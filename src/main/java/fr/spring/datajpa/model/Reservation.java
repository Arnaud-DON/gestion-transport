package fr.spring.datajpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation extends AbstractTravel {

	@Column(name = "WITH_DRIVER")
	private boolean withDriver;
	
	@ManyToOne
	@JoinColumn(name="VEHICULE")
	private VehiculeService vehicule;
}
