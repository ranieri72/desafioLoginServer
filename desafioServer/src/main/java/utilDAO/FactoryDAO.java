package main.java.utilDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.java.entityDAO.UserDAO;

public abstract class FactoryDAO {

	private static EntityManager manager;
	private static final EntityManagerFactory factory;

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		factory = Persistence.createEntityManagerFactory("PostgreSQLLocal");
		if (manager == null || !manager.isOpen()) {
			manager = factory.createEntityManager();
		}
	}

	public static UserDAO getUserDAO() {
		UserDAO dao = new UserDAO(manager);
		return dao;
	}
}