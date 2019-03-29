package main.java.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestLogin {

	private User user;
	private UserSession session;
	private String error = null;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}
}