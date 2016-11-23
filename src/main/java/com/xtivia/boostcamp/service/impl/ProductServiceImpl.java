/**
 * 
 */
package com.xtivia.boostcamp.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.liferay.portal.kernel.util.Validator;
import com.xtivia.boostcamp.dao.IProductDAO;
import com.xtivia.boostcamp.domain.Product;
import com.xtivia.boostcamp.service.IProductService;

/**
 * @author pnguyen
 *
 */
@Service("productService")
public class ProductServiceImpl implements IProductService {
	@Autowired
	@Qualifier("inMemoryProductDAO")
	private IProductDAO productDAO;
	
	public ProductServiceImpl() {
		
	}
	
	public ProductServiceImpl(IProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	@Override
	public List<Product> getList() {
		return productDAO.getList();
	}

	@Override
	public Product get(String key) {
		return productDAO.get(key);
	}

	@Override
	public void add(Product entity) {
		productDAO.add(entity);
	}

	@Override
	public void update(Product entity) {
		productDAO.update(entity);
	}

	@Override
	public void delete(String key) {
		productDAO.delete(key);
	}

	@Override
	public boolean validate(Product entity) {
		if (entity == null) {
			return false;
		}

		if (Validator.isBlank(entity.getId())) {
			return false;
		}

		if (Validator.isBlank(entity.getName())) {
			return false;
		}

		if (!Validator.isNull(entity.getQuantity()) && entity.getQuantity() < 0) {
			return false;
		}

		if (!Validator.isNull(entity.getUnitPrice()) && entity.getUnitPrice().compareTo(new BigDecimal(0)) < 1) {
			return false;
		}

		return true;
	}

	@Override
	public List<Product> getListByKey(String key) {
		return productDAO.getListByKey(key);
	}
}
