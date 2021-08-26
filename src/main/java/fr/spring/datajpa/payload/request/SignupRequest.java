package fr.spring.datajpa.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import fr.spring.datajpa.enums.Role;
 
public class SignupRequest {
    
	@NotBlank
    private String role;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    
    @NotBlank
    @Size(min = 10, max = 15)
    private String tel;
    
    @NotBlank
    @Size(min = 5, max = 100)
    private String imgUrl;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String mail;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
 
    public String getMail() {
        return mail;
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

	public void setMail(String mail) {
        this.mail = mail;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Role getRole() {
      return Role.valueOf(this.role);
    }
    
    public void setRole(Role role) {
      this.role = role.name();
    }
}