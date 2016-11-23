/**
 * 
 */
package com.xtivia.boostcamp.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author pnguyen
 *
 */
public interface IBaseService <T, PK extends Serializable> {
	List<T> getList();
	
	List<T> getListByKey(PK key);
	
	T get(PK key);
	
	void add(T entity);
	
	void update(T entity);
	
	void delete(PK key);
	
	boolean validate(T entity);
}