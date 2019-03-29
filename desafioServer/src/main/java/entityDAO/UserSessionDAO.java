package main.java.entityDAO;

import javax.persistence.EntityManager;

import main.java.entity.UserSession;
import main.java.utilDAO.GenericDAO;

public class UserSessionDAO extends GenericDAO<UserSession> {
	
	public UserSessionDAO(EntityManager em) {
		super(em);
	}
}
