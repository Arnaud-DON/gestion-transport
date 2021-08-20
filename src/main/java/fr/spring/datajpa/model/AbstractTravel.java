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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.spring.datajpa.enums.Status;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="TRAVELS")
public abstract class AbstractTravel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name="ORGANISATOR")
	private Collaborateur organisator;
	
	@Column(name = "DEPARTURE")
	private String departure;
	
	@Column(name = "ARRIVAL")
	private String arrival;
	
	@Column(name = "DATE")
	private LocalDateTime date;
	
	@Column(name = "DURATION")
	private int duration;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private Status status;

	public Collaborateur getOrganisator() {
		return organisator;
	}

	public void setOrganisator(Collaborateur organisator) {
		this.organisator = organisator;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}
	
}
