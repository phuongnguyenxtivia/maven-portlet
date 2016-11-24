/**
 * 
 */
package com.xtivia.boostcamp.dao;

import com.xtivia.boostcamp.dao.factory.InMemoryDAOFactory;
import com.xtivia.boostcamp.dao.factory.MySQLDAOFactory;

/**
 * @author pnguyen
 *
 */
public abstract class DAOFactory {
	// List of DAO types supported by the factory
	public static final int IN_MEMORY = 1;
	public static final int MYSQL = 2;

	// There will be a method for each DAO that can be created. The concrete
	// factories will have to implement these methods.
	public abstract IProductDAO getProductDAO();
	// public abstract AccountDAO getProductDAO();
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case IN_MEMORY:
			return new InMemoryDAOFactory();
		case MYSQL:
			return new MySQLDAOFactory();
		default:
			return null;
		}
	}
}
