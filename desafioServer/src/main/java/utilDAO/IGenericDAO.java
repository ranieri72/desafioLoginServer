package main.java.utilDAO;

import javax.persistence.EntityManager;

public interface IGenericDAO<T> {

	void inserir(Object obj);

	T alterar(T obj);

	void remover(T obj);

	void refresh(T object);

	void setEntityManager(EntityManager entityManager);

	EntityManager getEntityManager();

}