package main.java.utilDAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

public abstract class GenericDAO<T> {
	private EntityManager entityManager;
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericDAO(EntityManager em) {
		this.setEntityManager(em);
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		persistentClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	public void insert(T object) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			getEntityManager().persist(object);
			tx.commit();
		} catch (PersistenceException e) {
			tx.rollback();
			e.printStackTrace();
			throw new PersistenceException("Erro ocorreu durante a inserção no banco de dados!");
		}
	}

	public final void insertCollection(Collection<T> list) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			for (T entity : list) {
				getEntityManager().persist(entity);
			}
			tx.commit();
		} catch (PersistenceException e) {
			tx.rollback();
			e.printStackTrace();
			throw new PersistenceException("Erro ocorreu durante a inserção no banco de dados!");
		}
	}

	public T alter(T object) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			object = getEntityManager().merge(object);
			tx.commit();
		} catch (PersistenceException e) {
			tx.rollback();
			e.printStackTrace();
			throw new PersistenceException("Erro ocorreu durante a alteração no banco de dados!");
		}
		return object;
	}

	public final void delete(T object) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			object = getEntityManager().merge(object);
			getEntityManager().remove(object);
			tx.commit();
		} catch (PersistenceException e) {
			tx.rollback();
			e.printStackTrace();
			throw new PersistenceException("Erro ocorreu durante a remoção no banco de dados!");
		}
	}

	public final T select(Serializable id) {
		T instance = null;
		try {
			instance = (T) getEntityManager().find(getPersistentClass(), id);
		} catch (PersistenceException re) {
			re.printStackTrace();
			throw new PersistenceException("Erro ocorreu durante a busca no banco de dados!");
		}
		return instance;
	}

	public final void refresh(T objeto) {
		getEntityManager().refresh(objeto);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected final Class<T> getPersistentClass() {
		return persistentClass;
	}
}