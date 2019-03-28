package main.java.controller;

import java.util.Calendar;
import java.util.Date;

import main.java.entity.User;
import main.java.entityDAO.UserDAO;
import main.java.util.RandomToken;
import main.java.utilDAO.FactoryDAO;

public class UserController {

	private UserDAO userDAO;

	public UserController() {
		userDAO = FactoryDAO.getUserDAO();
	}

	public User login(User u) {
		if (u.getEmail().isEmpty()) {
			u = null;
			return u;
		}
		
		if (u.getPassword().isEmpty()) {
			u = userDAO.selectByLoginToken(u);
		} else {
			u = userDAO.selectByLoginPassword(u);
		}
		
		if (u != null) {
			Calendar c = Calendar.getInstance();
			Date now = c.getTime();

			if (u.getToken().isEmpty() || u.getTokenExpiration().before(now)) {
				u.setToken(RandomToken.generateToken());
				userDAO.alter(u);
			}
		}
		return u;
	}
}