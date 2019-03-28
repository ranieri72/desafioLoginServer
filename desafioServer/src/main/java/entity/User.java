package main.java.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "android_user")
@XmlRootElement(name = "user")
public class User {
	
	@Id
	@Column(name = "user_id")
	@XmlElement(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_email", length = 25, nullable = false)
	@XmlElement(name = "user_email")
	private String email;

	@Column(name = "user_password", length = 25, nullable = false)
	private String password;
	
	@Column(name = "user_token", length = 32)
	private String token;
	
	@Column(name = "token_expiration")
	private Date tokenExpiration;

	@XmlTransient
	public long getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpiration() {
		return tokenExpiration;
	}

	public void setTokenExpiration(Date tokenExpiration) {
		this.tokenExpiration = tokenExpiration;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlTransient
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlTransient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
