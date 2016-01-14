package com.bmdb.persist;

import java.util.List;

import javax.persistence.EntityManager;

public class UsersService extends EntityService<User> {

	UsersService(EntityManager entityManager) {
		super(entityManager, User.class);
	}

	public boolean exist(String name) {
		return getUser(name) != null;
	}

	public boolean register(User bean) {
		if (!exist(bean.getUserName())) {
			addEntity(bean);
			return true;
		} else {
			return false;
		}
	}

	public List<User> getUsers() {
		return getEntities();
	}

	public User login(String userName, String password) {
		for (User user : getUsers()) {
			if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	public User getUser(String userName) {
		return getByPrimaryKey(userName, "username", String.class);
	}


    public User getUserById(int id)
    {
        return getById(id);
    }


    public void remove(int id)
    {
        User user = getUserById(id);
        DBContext.get().getReviewsProvider().removeByUser(user);
        removeEntity(user);
    }

	public void update(User user, String name, String email) {
		getEntityManager().getTransaction().begin();
	        user.setName(name);
	        user.setEmail(email);
	        getEntityManager().getTransaction().commit();
	}
}
