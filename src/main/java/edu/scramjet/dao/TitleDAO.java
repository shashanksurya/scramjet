package edu.scramjet.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.scramjet.entity.Title;

@Repository
public class TitleDAO implements ITitleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public Title addTitle(Title t) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(t);
		return t;
	}

	@Override
	@Transactional
	public Title updateTitle(Title t) {
		Session session = sessionFactory.getCurrentSession();
		session.update(t);
		return t;
	}

	@Override
	@Transactional
	public Title removeTitle(int id) {
		Session session = sessionFactory.getCurrentSession();
		Title t = (Title) session.get(Title.class, id);
		session.delete(t);
		return t;

	}

	@Override
	@Transactional
	public List<Title> listTitles() {
		Session session = sessionFactory.getCurrentSession();
		List<Title> titlelist = session.createQuery("from Title").list();
		return titlelist;
	}

	@Override
	@Transactional
	public Title getTitleById(int id) {
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(
				"from Title t where t.id = :searchbyid");
		List<Title> titlelist = query.setParameter("searchbyid",id).list();
		if(titlelist.size()!=0)
		{
			return titlelist.get(0);
		}
		return null;
	}

}
