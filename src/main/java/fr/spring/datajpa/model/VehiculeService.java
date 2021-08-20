package fr.spring.datajpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import fr.spring.datajpa.enums.VehiculeStatus;

@Entity
public class VehiculeService extends AbstractVehicule {

	@Column(name="IMG_URL")
	private String imgUrl;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private VehiculeStatus status;
	
	@ManyToOne
	@JoinColumn(name="RESPONSABLE")
	private Administrateur responsable;

	@OneToMany(mappedBy="vehicule")
	private Set<Reservation> reservations;
	
	public VehiculeService() {
		reservations = new HashSet<Reservation>();
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public VehiculeStatus getStatus() {
		return status;
	}

	public void setStatus(VehiculeStatus status) {
		this.status = status;
	}

	public Administrateur getResponsable() {
		return responsable;
	}

	public void setResponsable(Administrateur responsable) {
		this.responsable = responsable;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}
	
}
