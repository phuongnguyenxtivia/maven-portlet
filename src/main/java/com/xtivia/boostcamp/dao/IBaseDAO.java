/**
 * 
 */
package com.xtivia.boostcamp.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * @author pnguyen
 *
 */
public interface IBaseDAO <T, PK extends Serializable> {
	T findOne(PK key);
	
	List<T> findAll();
	
	void add(T entity);
	
	void save(T entity) ;
	
	void update(T entity);
	
	void delete(T entity);
	
	void deleteById(PK key);
}