package fr.spring.datajpa.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import fr.spring.datajpa.enums.Status;

@Entity
public class VehiculeService extends AbstractVehicule {

	@Column(name="IMG_URL")
	private String imgUrl;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="RESPONSABLE")
	private Administrateur responsable;

	@OneToMany(mappedBy="vehicule")
	private Set<Reservation> reservations;
}
