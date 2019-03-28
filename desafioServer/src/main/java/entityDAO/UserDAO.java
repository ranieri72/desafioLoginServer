package main.java.entityDAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import main.java.entity.User;
import main.java.utilDAO.GenericDAO;

public class UserDAO extends GenericDAO<User> {

	public UserDAO(EntityManager em) {
		super(em);
	}

	public User selectByLoginPassword(User u) {
		try {
			String consulta = "SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2";
			Query q1 = getEntityManager().createQuery(consulta, User.class);
			q1.setParameter(1, u.getEmail());
			q1.setParameter(2, u.getPassword());
			return (User) q1.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public User selectByLoginToken(User u) {
		try {
			String consulta = "SELECT u FROM User u WHERE u.email = ?1 AND u.token = ?2";
			Query q1 = getEntityManager().createQuery(consulta, User.class);
			q1.setParameter(1, u.getEmail());
			q1.setParameter(2, u.getToken());
			return (User) q1.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
