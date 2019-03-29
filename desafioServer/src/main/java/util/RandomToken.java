package main.java.util;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

import main.java.entity.User;
import main.java.entity.UserSession;

public class RandomToken {

	public static UserSession generateToken(User u, int daysExpiration) {
		UserSession session = new UserSession();

		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[64];
		random.nextBytes(bytes);
		String token = bytes.toString();

		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, daysExpiration);
		dt = c.getTime();

		session.setToken(token);
		session.setTokenExpiration(dt);
		session.setUser(u);

		return session;
	}
}