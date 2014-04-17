package com.royaltechnosoft.inquiry.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.DAO;
import com.royaltechnosoft.inquiry.model.Model;
import com.royaltechnosoft.inquiry.util.DAOUtil;

public class DAOSupport<T> implements DAO<T> {
	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> t;

	public void setGenericType(Class<T> t) {
		this.t = t;
	}

	// Returns a list of objects matching to this model
	public List<T> find(T model) {
		return find(model, null, null, null);
	}

	// Returns a paged list of objects matching to this model
	public List<T> find(T model, Integer page) {
		return find(model, null, null, page);
	}

	// Returns a sorted list of objects matching to this model
	public List<T> find(T model, String sortColumn, Byte direction) {
		return find(model, sortColumn, direction, null);
	}

	// Returns a sorted and paged list of objects matching to this model
	public List<T> find(T model, String sortColumn, Byte direction, Integer page) {
		Session session = getSession();
		Criteria criteria = DAOUtil.createCriteria((Model) model, session);

		// If sortColumn and direction are not null, then add them into criteria
		if (sortColumn != null && direction != null) {
			switch (direction) {
			case ASCENDING:
				criteria.addOrder(Order.asc(sortColumn));
				break;
			case DESCENDING:
				criteria.addOrder(Order.desc(sortColumn));
				break;
			}
		}
		// If page is not null, then set the first result and max results by
		// calculating it using the pre-set value of LIMIT_PER_PAGE
		if (page != null) {
			criteria.setFirstResult((page - 1) * LIMIT_PER_PAGE);
			criteria.setMaxResults(LIMIT_PER_PAGE);
		}
		List<T> list = criteria.list();
		closeSession(session);
		return list;
	}

	// Returns an object matching to this model
	public T findOne(T model) {
		Session session = getSession();
		Criteria criteria = DAOUtil.createCriteria(model, session);
		T t = (T) criteria.uniqueResult();
		closeSession(session);
		return t;
	}

	// Returns an object matching with this id
	public T findOne(int id) {
		Session session = getSession();
		Criteria criteria = DAOUtil.createCriteriaFromId(id, t, session);
		T t = (T) criteria.uniqueResult();
		closeSession(session);
		return t;
	}

	// Returns a count of objects matching to this model
	public long count(T model) {
		Session session = getSession();
		Criteria criteria = DAOUtil.createCriteria(model, session);
		criteria.setProjection(Projections.rowCount());
		Number count = (Number) criteria.uniqueResult();
		closeSession(session);
		return (Long) count;

	}

	// Returns a count of pages of objects matching to this model
	public int countPage(T model) {
		return getPageCount(count(model));
	}

	// Calculates and returns a count of pages to accommodate the number of results specified
	int getPageCount(long totalResults) {
		// If total results are directly divisible by the LIMIT_PER_PAGE, then
		// return quotient
		if (totalResults % LIMIT_PER_PAGE == 0)
			return (int) (totalResults / LIMIT_PER_PAGE);
		// If total results are not directly divisible by the LIMIT_PER_PAGE,
		// then add 1 to the quotient and return it
		else
			return (int) (totalResults / LIMIT_PER_PAGE) + 1;
	}

	// Saves or updates a model object
	public void save(T model) {
		Session session = getSession();
		session.saveOrUpdate(model);
		closeSession(session);
	}

	// Inserts a new model object
	public void insert(T model) {
		Session session = getSession();
		session.save(model);
		closeSession(session);
	}

	// Updates the model found using id
	public void update(int id, T updateModel) {
		T existingModel = findOne(id);

		Session session = getSession();
		session.saveOrUpdate(DAOUtil
				.getUpdatedModel(existingModel, updateModel));
		closeSession(session);
	}

	// Updates all the models found using the queryModel
	public void update(T queryModel, T updateModel) {
		List<T> existingModels = find(queryModel);
		Session session = getSession();
		for (Object object : DAOUtil.getUpdatedModels(existingModels.toArray(),
				updateModel)) {
			session.saveOrUpdate(object);
		}
		closeSession(session);
	}

	// Deletes a record with the specified id
	public void destroy(int id) {
		Session session = getSession();
		session.delete(findOne(id));
		closeSession(session);
	}

	// Deletes a record with the matching model
	public void destroy(T model) {
		Session session = getSession();
		session.delete(findOne(model));
		closeSession(session);
	}

	// Deletes all the records with the matching model
	public void destroyMany(T queryModel) {
		Session session = getSession();
		for (Object object : find(queryModel)) {
			session.delete(object);
		}
		closeSession(session);
	}

	public Class<T> getT() {
		return t;
	}

	public void setT(Class<T> t) {
		this.t = t;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}

	public void closeSession(Session session) {
		if (session.getTransaction().isActive())
			session.getTransaction().commit();
		session.close();
	}
}
