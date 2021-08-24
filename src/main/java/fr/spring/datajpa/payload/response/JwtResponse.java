package fr.spring.datajpa.payload.response;

import fr.spring.datajpa.enums.Role;

public class JwtResponse {
	private String token;
	private String mail;
	private Role role;

	public JwtResponse(String accessToken, String mail, Role role) {
		this.token = accessToken;
		this.mail = mail;
		this.role = role;
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
}