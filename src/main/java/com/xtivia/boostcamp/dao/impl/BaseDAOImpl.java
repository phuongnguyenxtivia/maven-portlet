/**
 * 
 */
package com.xtivia.boostcamp.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xtivia.boostcamp.dao.IBaseDAO;

/**
 * @author pnguyen
 *
 */
@Repository
@Transactional
public abstract class BaseDAOImpl <T, PK extends Serializable> implements IBaseDAO<T, PK> {
	@Autowired
    private SessionFactory sessionFactory;
	protected Class<? extends T> daoType;
	
	/**
    * By defining this class as abstract, we prevent Spring from creating 
    * instance of this class If not defined as abstract, 
    * getClass().getGenericSuperClass() would return Object. There would be 
    * exception because Object class does not hava constructor with parameters.
    */
    public BaseDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    
	@Override
	public List<T> findAll() {
		return currentSession().createCriteria(daoType).list();
	}

	@Override
	public T findOne(PK key) {
		return (T) currentSession().get(daoType, key);
	}

	@Override
	public void add(T entity) {
		currentSession().save(entity);
	}

	@Override
	public void update(T entity) {
		currentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		currentSession().delete(entity);
	}

	@Override
	public void save(T entity) {
		currentSession().saveOrUpdate(entity);
	}

	@Override
	public void deleteById(PK key) {
		T entity = findOne(key);
	    delete(entity);
	}
}
