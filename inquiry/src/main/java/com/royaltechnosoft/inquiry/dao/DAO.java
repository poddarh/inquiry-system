package com.royaltechnosoft.inquiry.dao;

import java.util.List;

public interface DAO<T> {
	static byte ASCENDING = 1;
	static byte DESCENDING = 2;
	
	List<T> find(T model);
	List<T> find(T model, String sortColumn, byte direction);
	T findOne(T model);
	T findOne(int id);
	Number count(T model);
	void insert(T model);
	void save(T model);
	void update(int id, T updateModel);
	void update(T queryModel, T updateModel);
	void destroy(T model);
	void destroyMany(T queryModel);
}
