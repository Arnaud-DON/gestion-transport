package fr.spring.datajpa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import fr.spring.datajpa.enums.Category;

@Embeddable
public class VehiculePrivate {
	
	@Column(name="IMMATRICULATION")
	private String immatriculation;
	
	@Column(name="BRAND")
	private String brand;
	
	@Column(name="MODEL")
	private String model;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CATEGORY")
	private Category category;
	
	@Column(name="TOTAL_PLACES")
	private int totalPlaces;
	
	public VehiculePrivate() {

	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getTotalPlaces() {
		return totalPlaces;
	}

	public void setTotalPlaces(int totalPlaces) {
		this.totalPlaces = totalPlaces;
	}
	
}
