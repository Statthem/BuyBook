package com.statthem.BuyBook.DAO;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.statthem.BuyBook.model.Book;
import com.statthem.BuyBook.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	Logger logger = Logger.getLogger(UserDAOImpl.class);

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	@Qualifier(value = "hibernate4AnnotatedSessionFactory")
	SessionFactory sessionFactory;

	public User addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		user.setRoleId(1);
		session.persist(user);
		logger.info("new user registered succesfully");
		return user;
	}

	public void deleteUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(user);
		logger.info("user deleted succesfully");
	}

	public void modifyUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
		logger.info("user modified succesfully");

	}

	public User getUserById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = null;

		Query query = session.createQuery("Select u from User u where u.id =:id");
		query.setParameter("id", id);
		user = (User) query.uniqueResult();

		if (Objects.nonNull(user))
			logger.info("Select user succesfuly");

		return user;

	}

	
	public User findByEmail(String email) {
		Session session = this.sessionFactory.getCurrentSession();

		User user = null;

		Query query = session.createQuery("Select u from User u where u.userEmail =:email");
		query.setParameter("email", email);
		user = (User) query.uniqueResult();

		if (Objects.nonNull(user))
			logger.info("equal user email");

		return user;
	}

}
