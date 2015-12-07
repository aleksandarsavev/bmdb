package com.bmdb.persist;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UsersProvider {
	private EntityManager entityManager;

	UsersProvider(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public boolean exist(String name) {
		TypedQuery<User> createQuery = entityManager
				.createQuery(entityManager.getCriteriaBuilder().createQuery(User.class));
		for (User user : createQuery.getResultList()) {
			if (user.getUserName().equals(name))
				return true;
		}
		return false;
	}

	public boolean register(User bean) {
		if (!exist(bean.getUserName())) {
			entityManager.getTransaction().begin();
			entityManager.persist(bean);
			entityManager.getTransaction().commit();
			return true;
		} else {
			return false;
		}
	}

	public List<User> getUsers() {
		TypedQuery<User> createQuery = entityManager
				.createQuery(entityManager.getCriteriaBuilder().createQuery(User.class));
		return createQuery.getResultList();
	}

	public User login(String userName, String password) {

		TypedQuery<User> createQuery = entityManager
				.createQuery(entityManager.getCriteriaBuilder().createQuery(User.class));
		for (User user : createQuery.getResultList()) {
			if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	public User getUser(String userName) {
		TypedQuery<User> createQuery = entityManager
				.createQuery(entityManager.getCriteriaBuilder().createQuery(User.class));
		for (User user : createQuery.getResultList()) {
			if (user.getUserName().equals(userName)) {
				return user;
			}
		}
		return null;
	}
}
