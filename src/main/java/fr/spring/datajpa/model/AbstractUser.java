package fr.spring.datajpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="USERS")
public abstract class AbstractUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(max = 20)
	@Column(name ="NAME")
	private String name;
	
	@NotBlank
	@Size(max = 20)
	@Column(name ="FIRST_NAME")
	private String firstName;
	
	@Size(max = 50)
	@Email
	@Column(name ="MAIL")
	private String mail;
	
	@NotBlank
	@Size(max = 120)
	@Column(name ="PASSWORD")
	private String password;

	@Size(max = 15)
	@Column(name ="TEL")
	private String tel;
	
	@Column(name ="IMG_URL")
	private String imgUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public long getId() {
		return id;
	}

	public String getFullname() {
		return getFirstName() + " " + getName().toUpperCase();
	}
	
}
