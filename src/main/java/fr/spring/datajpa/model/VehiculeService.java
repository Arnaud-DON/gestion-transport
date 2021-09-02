package fr.spring.datajpa.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fr.spring.datajpa.enums.Category;
import fr.spring.datajpa.enums.VehiculeStatus;

@Entity
public class VehiculeService {

	@Id
	@Column(name="IMMATRICULATION")
	private String immatriculation;
	
	@Column(name="Marque")
	private String marque;
	
	@Column(name="MODELE")
	private String modele;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CATEGORIE")
	private Category categorie;

	@Column(name="IMG_URL")
	private String imgUrl;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private VehiculeStatus status;
	
	@ManyToOne
	@JoinColumn(name="RESPONSABLE")
    @JsonBackReference(value="responsable")
	private Administrateur responsable;

	@OneToMany(mappedBy="vehicule")
    @JsonManagedReference(value="vehicule")
	private Set<Reservation> reservations;
	
	public VehiculeService() {
		reservations = new HashSet<Reservation>();
	}

	public VehiculeService(String immatriculation, String marque, String modele, Category categorie, String imgUrl) {
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.categorie = categorie;
		this.imgUrl = imgUrl;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
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

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public Category getCategorie() {
		return categorie;
	}

	public void setCategorie(Category categorie) {
		this.categorie = categorie;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	// Return the first found concurrent travel of given date and duration, return null is no result
	public AbstractTravel getConcurrentTravel(LocalDateTime dateDepart, int dureeMinutes) {
		
		
		LocalDateTime dateArrivee = dateDepart.plusMinutes(dureeMinutes);
		for(AbstractTravel resa : reservations) {
			LocalDateTime tDateDepart = resa.getDate();
			LocalDateTime tDateArrivee = tDateDepart.plusMinutes(resa.getDuree());
			
			if((dateDepart.isBefore(tDateArrivee) && dateDepart.isAfter(tDateDepart)
					|| dateArrivee.isBefore(tDateArrivee) && dateArrivee.isAfter(tDateDepart))
			 || (tDateDepart.isBefore(dateArrivee) && tDateDepart.isAfter(dateDepart)
				|| tDateArrivee.isBefore(dateArrivee) && tDateArrivee.isAfter(dateDepart))) {
				return resa;
			}
		}
		return null;
	}

	public AbstractTravel getConcurrentTravel(LocalDateTime dateAller, LocalDateTime dateRetour) {
		for(AbstractTravel resa : reservations) {
			LocalDateTime tDateDepart = resa.getDate();
			LocalDateTime tDateArrivee = tDateDepart.plusMinutes(resa.getDuree());
			
			if(dateAller.isEqual(tDateDepart) || dateAller.isEqual(tDateArrivee)
					|| dateRetour.isEqual(tDateDepart) || dateRetour.isEqual(tDateArrivee)) {
				return resa;
			}
			
			if((dateAller.isBefore(tDateArrivee) && dateAller.isAfter(tDateDepart)
					|| dateRetour.isBefore(tDateArrivee) && dateRetour.isAfter(tDateDepart))
			 || (tDateDepart.isBefore(dateRetour) && tDateDepart.isAfter(dateAller)
				|| tDateArrivee.isBefore(dateRetour) && tDateArrivee.isAfter(dateAller))) {
				return resa;
			}
		}
		return null;
		// TODO Auto-generated method stub
		
	}
}
