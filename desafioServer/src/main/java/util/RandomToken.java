package main.java.util;

import java.security.SecureRandom;

public class RandomToken {

	public static String generateToken() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[32];
		random.nextBytes(bytes);
		return bytes.toString();
	}
}