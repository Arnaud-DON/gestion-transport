package fr.spring.datajpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="USERS")
public abstract class AbstractUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name ="NAME")
	private String name;
	
	@Column(name ="FIRST_NAME")
	private String firstName;
	
	@Column(name ="MAIL")
	private String mail;
	
	@Column(name ="PASSWORD")
	private String password;
	
	@Column(name ="TEL")
	private String tel;
	
	@Column(name ="IMG_URL")
	private String imgUrl;
}
