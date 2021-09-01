package fr.spring.datajpa.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import fr.spring.datajpa.enums.TravelStatus;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="TRAVELS")
public abstract class AbstractTravel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "ADRESSE_DEPART")
	private String adresseDepart;
	
	@Column(name = "ADRESSE_DESTINATION")
	private String adresseDestination;
	
	@Column(name = "DATE")
	private LocalDateTime date;
	
	@Column(name = "DUREE")
	private int duree;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TravelStatus")
	private TravelStatus TravelStatus;

	public String getAdresseDepart() {
		return adresseDepart;
	}

	public void setAdresseDepart(String adresseDepart) {
		this.adresseDepart = adresseDepart;
	}

	public String getAdresseDestination() {
		return adresseDestination;
	}

	public void setAdresseDestination(String adresseDestination) {
		this.adresseDestination = adresseDestination;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public TravelStatus getTravelStatus() {
		return TravelStatus;
	}

	public void setTravelStatus(TravelStatus TravelStatus) {
		this.TravelStatus = TravelStatus;
	}

	public long getId() {
		return id;
	}
	
}
