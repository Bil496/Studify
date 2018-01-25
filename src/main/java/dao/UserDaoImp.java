package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public class UserDaoImp implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public long save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		sessionFactory.getCurrentSession().save(user);
		return user.getId();
	}

	public User get(long id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	public List<User> list() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.select(root);
		Query<User> query = session.createQuery(cq);
		return query.getResultList();
	}

	public void update(long id, User user) {
		Session session = sessionFactory.getCurrentSession();
		User user2 = session.byId(User.class).load(id);
		user2.setName(user.getName());
		user2.setSurname(user.getSurname());
		user2.setEmail(user.getEmail());
		user2.setUsername(user.getUsername());
		user2.setPassword(passwordEncoder.encode(user.getPassword()));
		// user2.setTopics(user.getTopics());
		// user2.setSkills(user.getSkills());
		// user2.setGroups(user.getGroups());
		session.flush();
	}

	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.byId(User.class).load(id);
		session.delete(user);
	}

	/*
	public Boolean login(long id, User user) {
		Session session = sessionFactory.getCurrentSession();
		User user2 = session.byId(User.class).load(id);
		return passwordEncoder.matches(user.getPassword(), user2.getPassword());
	}
	*/
}
