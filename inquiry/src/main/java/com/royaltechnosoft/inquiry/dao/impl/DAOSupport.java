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
	@Autowired private SessionFactory sessionFactory;
	private Class<T> t;
	
	public void setGenericType(Class<T> t){
		this.t=t;
	}
	
	public List<T> find(T model) {
		return find(model, null, null, null);
	}
	
	public List<T> find(T model, String sortColumn, Byte direction) {
		return find(model, sortColumn, direction, null);
	}
	
	public T findOne(T model) {
		Session session = getSession();
		Criteria criteria = DAOUtil.createCriteria(model, session);
		T t = (T) criteria.uniqueResult();
		closeSession(session);
		return t;
	}
	
	public T findOne(int id) {
		Session session = getSession();
		Criteria criteria = DAOUtil.createCriteriaFromId(id, t, session);
		T t = (T) criteria.uniqueResult();
		closeSession(session);
		return t;
	}
	
	public long count(T model) {
		Session session = getSession();
		Criteria criteria = DAOUtil.createCriteria(model, session);
		criteria.setProjection(Projections.rowCount());
		Number count = (Number) criteria.uniqueResult();
		closeSession(session);
		return (Long) count; 

	}
	
	public void save(T model) {
		Session session = getSession();
        session.saveOrUpdate(model);
        closeSession(session);
	}
	
	public void insert(T model) {
		Session session = getSession();
		session.save(model);
		closeSession(session);
	}
	
	public void update(int id, T updateModel) {
		T existingModel = findOne(id);
		
		Session session = getSession();
		session.saveOrUpdate(DAOUtil.getUpdatedModel(existingModel, updateModel));
		closeSession(session);
	}
	
	public void update(T queryModel, T updateModel) {
		List<T> existingModels = find(queryModel);
		Session session = getSession();
		for (Object object : DAOUtil.getUpdatedModels(existingModels.toArray(), updateModel)) {
			session.saveOrUpdate(object);
		}
		closeSession(session);
	}
	
	public void destroy(T model) {
		Session session = getSession();
		session.delete(findOne(model));
		closeSession(session);
	}
	
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
	
	public Session getSession(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}
	
	public void closeSession(Session session){
		if(session.getTransaction().isActive())
			session.getTransaction().commit();
		session.close();
	}

	public List<T> find(T model, Integer page) {
		return find(model, null, null, page);
	}

	public List<T> find(T model, String sortColumn, Byte direction, Integer page) {
		Session session = getSession();
		Criteria criteria = DAOUtil.createCriteria((Model) model, session);
		if(sortColumn!=null && direction!=null){
			switch(direction){
			case ASCENDING:
				criteria.addOrder(Order.asc(sortColumn));
				break;
			case DESCENDING:
				criteria.addOrder(Order.desc(sortColumn));
				break;
			}
		}
		if(page!=null){
			criteria.setFirstResult((page - 1) * LIMIT_PER_PAGE);
			criteria.setMaxResults(LIMIT_PER_PAGE);
		}
		List<T> list = criteria.list();
		closeSession(session);
		return list;
	}

	public int countPage(T model) {
		return getPageCount(count(model));
	}
	
	public int getPageCount(long totalResults){
		if (totalResults % LIMIT_PER_PAGE == 0)
			return (int) (totalResults / LIMIT_PER_PAGE);
		else
			return (int) (totalResults / LIMIT_PER_PAGE) + 1;
	}
}
