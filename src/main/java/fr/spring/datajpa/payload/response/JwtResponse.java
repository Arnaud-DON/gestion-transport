package fr.spring.datajpa.payload.response;

import fr.spring.datajpa.enums.Role;

public class JwtResponse {
	private String token;
	private String mail;
	private Role role;
	private String name;
	private String firstName;
	private String tel;
	private String imgUrl;

	public JwtResponse(String accessToken, String mail, Role role, String name, String firstName, String tel, String imgUrl) {
		this.token = accessToken;
		this.mail = mail;
		this.role = role;
		this.name = name;
		this.firstName = firstName;
		this.tel = tel;
		this.imgUrl = imgUrl;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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
}