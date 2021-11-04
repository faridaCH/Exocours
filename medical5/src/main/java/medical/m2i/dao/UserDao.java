package medical.m2i.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import medical.m2i.model.User;

public class UserDao {

	EntityManagerFactory emf;
	EntityManager em;

	public UserDao() {
		super();
		emf = Persistence.createEntityManagerFactory("medical7");
		em = emf.createEntityManager();

	}

	public List<User> getUsers() throws ClassNotFoundException {
		return em.createQuery("from User", User.class).getResultList();
	}

	public List<User> getUsersByUsernameAndPassword(String pusername, String ppassword) throws ClassNotFoundException {
		return em.createNamedQuery("User.findByUsernameAndPassword", User.class).setParameter("username", pusername)
				.setParameter("password", ppassword).getResultList();
	}

}
