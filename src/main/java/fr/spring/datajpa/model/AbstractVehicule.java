package fr.spring.datajpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import fr.spring.datajpa.enums.Category;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="VEHICULES")
public abstract class AbstractVehicule {

	@Id
	@Column(name="IMMATRICULATION")
	private String immatriculation;
	
	@Column(name="BRAND")
	private String brand;
	
	@Column(name="MODEL")
	private String model;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CATEGORY")
	private Category category;
	
}
