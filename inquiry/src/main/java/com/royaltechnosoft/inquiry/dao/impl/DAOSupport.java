package com.royaltechnosoft.inquiry.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.royaltechnosoft.inquiry.dao.DAO;
import com.royaltechnosoft.inquiry.util.DAOUtil;

public class DAOSupport<T> implements DAO<T> {
	@Autowired private MongoOperations mongoOperation;
	private Class<T> t;
	
	public void setGenericType(Class<T> t){
		this.t=t;
	}
	
	public List<T> find(T model) {
		return find(model, null, null, null);
	}
	
	public List<T> find(T model, String sortColumn, Direction direction) {
		return find(model, sortColumn, direction, null);
	}
	
	public T findOne(T model) {
		return mongoOperation.findOne(DAOUtil.getFieldsQuery(model), t);
	}
	
	public T findOne(String id) {
		return mongoOperation.findById(id, t);
	}
	
	public long count(T model) {
		return mongoOperation.count(DAOUtil.getFieldsQuery(model), t);
	}
	
	public int countPages(T model) {
		return getPageCount(count(model));
	}
	
	public void save(T model) {
		mongoOperation.save(model);
	}
	
	public void insert(T model) {
		mongoOperation.insert(model);
	}
	
	public boolean update(String id, T updateModel) {
		Query query = new Query();
		query.addCriteria(Criteria.where(DAOUtil.getIdFieldName(t)).is(id));
		Update update = DAOUtil.getFieldsUpdate(updateModel);
		if(update!=null)
			return mongoOperation.updateFirst(query, update, t).getN()>0;
		else
			return true;
	}
	
	public boolean update(T queryModel, T updateModel) {
		Update update = DAOUtil.getFieldsUpdate(updateModel);
		if(update!=null)
			return mongoOperation.updateMulti(DAOUtil.getFieldsQuery(queryModel), update, t).getN()>0;
		else
			return true;
	}
	
	public void destroy(T model) {
		mongoOperation.remove(model);
	}
	
	public void destroyMany(T queryModel) {
		mongoOperation.remove(DAOUtil.getFieldsQuery(queryModel), t);
	}

	public MongoOperations getMongoOperation() {
		return mongoOperation;
	}
	public void setMongoOperation(MongoOperations mongoOperation) {
		this.mongoOperation = mongoOperation;
	}
	public Class<T> getT() {
		return t;
	}
	public void setT(Class<T> t) {
		this.t = t;
	}

	public List<T> find(T model, Integer page) {
		return find(model,null,null,page);
	}

	public List<T> find(T model, String sortColumn, Direction direction, Integer page) {
		Query query = DAOUtil.getFieldsQuery(model);
		if(sortColumn != null && direction != null)
			query.with(new Sort(direction, sortColumn));
		if(page!=null){
			query.skip((page - 1) * LIMIT_PER_PAGE);
			query.limit(LIMIT_PER_PAGE);
		}
		return mongoOperation.find(query, t);
		
	}
	
	public int getPageCount(long totalResults){
		if (totalResults % LIMIT_PER_PAGE == 0)
			return (int) (totalResults / LIMIT_PER_PAGE);
		else
			return (int) (totalResults / LIMIT_PER_PAGE) + 1;
	}
}
