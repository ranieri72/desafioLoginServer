package main.java.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "user_session")
@XmlRootElement
public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_token", length = 32)
	private String token;

	@Column(name = "token_expiration")
	private Date tokenExpiration;

	@Id
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@XmlTransient
	private User user;	

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

	@XmlTransient
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
