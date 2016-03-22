package edu.scramjet.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.scramjet.entity.User;

@Repository
public class UserDAO implements IUserDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public User create(User u) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(u);
		return u;
	}

	@Override
	@Transactional
	public User update(User u) {
		Session session = sessionFactory.getCurrentSession();
		session.update(u);
		return u;
	}

	@Override
	@Transactional
	public List<User> listUsers() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> userlist = session.createQuery("from User").list();
		return userlist;
	}

	@Override
	@Transactional
	public User getUserByID(String uuid) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class,uuid);
		return user;
		
	}
	
	@Override
	@Transactional
	public User getUserByPhone(long phone) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from User where phone = :searchbyphone");
		@SuppressWarnings("unchecked")
		List<User> userlist = query.setParameter("searchbyphone", phone).list();
		if(userlist.size()!=0)
		{
			return userlist.get(0);
		}
		return null;
		
	}

}
