package main.java.controller;

import java.util.Calendar;
import java.util.Date;

import main.java.entity.RequestLogin;
import main.java.entity.User;
import main.java.entity.UserSession;
import main.java.entityDAO.UserDAO;
import main.java.entityDAO.UserSessionDAO;
import main.java.util.RandomToken;
import main.java.utilDAO.FactoryDAO;

public class UserController {

	private UserDAO userDAO;
	private UserSessionDAO sessionDAO;

	public UserController() {
		userDAO = FactoryDAO.getUserDAO();
		sessionDAO = FactoryDAO.getUserSessionDAO();
	}

	public void register(User user) {
		try {
			userDAO.insert(user);

			UserSession session = RandomToken.generateToken(user, 7);
			sessionDAO.insert(session);
		} catch (Exception e) {
		}
	}

	public RequestLogin login(RequestLogin login) {
		try {
			User u = login.getUser();
			UserSession session = login.getSession();

			if (u.getEmail().isEmpty() || (u.getPassword().isEmpty() && session.getToken().isEmpty())) {
				login.setSession(null);
				login.setUser(null);
				login.setError("Campos nulos!");
				return login;
			}

			if (u.getPassword().isEmpty()) {
				u = userDAO.selectByLoginToken(login);
			} else {
				u = userDAO.selectByLoginPassword(u);
			}

			if (u == null) {
				login.setSession(null);
				login.setUser(null);
				login.setError("Login inválido!");
			} else {
				Calendar c = Calendar.getInstance();
				Date now = c.getTime();

				if (session.getToken().isEmpty() || session.getTokenExpiration().before(now)) {
					session = RandomToken.generateToken(u, 7);
					sessionDAO.alter(session);
				}
				login.setUser(u);
				login.setSession(session);
			}
			return login;
		} catch (Exception e) {
			login.setSession(null);
			login.setUser(null);
			login.setError(e.getLocalizedMessage());
			return login;
		}
	}
}