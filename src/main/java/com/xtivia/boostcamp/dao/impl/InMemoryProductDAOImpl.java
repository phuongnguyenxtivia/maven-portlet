/**
 * 
 */
package com.xtivia.boostcamp.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtivia.boostcamp.dao.IProductDAO;
import com.xtivia.boostcamp.domain.Product;

/**
 * @author pnguyen
 *
 */
@Repository("inMemoryProductDAO")
public class InMemoryProductDAOImpl implements IProductDAO {
	private List<Product> products;
	
	public InMemoryProductDAOImpl() {
		products = new ArrayList<Product>();
		products.add(new Product("1101", "Laptop", 5, new BigDecimal(700), "New model Laptop"));
		products.add(new Product("1102", "Phone", 6, new BigDecimal(600), "New model Phone"));
		products.add(new Product("1103", "TV", 7, new BigDecimal(500), "New model TV"));
		products.add(new Product("1104", "Vest", 8, new BigDecimal(400), "New model Vest"));
		products.add(new Product("1105", "Rolex", 9, new BigDecimal(300), "New model Rolex"));
		products.add(new Product("1106", "Lego", 10, new BigDecimal(200), "New model Lego"));
		products.add(new Product("1107", "Shoe", 11, new BigDecimal(100), "New model Shoe"));
	}

	@Override
	public List<Product> getList() {
		return products;
	}

	@Override
	public Product get(String key) {
        Product entity = null;

        for (Product product : products) {
            if (product != null && product.getId() != null && product.getId().equals(key)) {
            	entity = product;
                break;
            }
        }

        return entity;
	}

	@Override
	public void add(Product entity) {
        if (entity.getId() == null || "".equalsIgnoreCase(entity.getId())) {
        	entity.setId(getNewId());
        } else {
            for (Product existingProduct : products) {
                if (existingProduct.getId().equals(entity.getId())) {
                	//entity.setId(getNewId());
                	return;
                }
            }
        }

        products.add(entity);
	}

	@Override
	public void update(Product entity) {
       for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(entity.getId())) {
            	products.set(i, entity);
            }
        }
	}

	@Override
	public void delete(String key) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(key)) {
            	products.remove(i);
            }
        }
	}
	
    private String getNewId() {
        return String.valueOf(Integer.parseInt(products.get(products.size() - 1).getId()) + 1);
    }

	@Override
	public List<Product> getListByKey(String key) {
		List<Product> list = new ArrayList<Product>(); 

        for (Product product : products) {
            if (product != null && product.getId() != null && product.getId().startsWith(key)) {
            	list.add(product);
            }
        }

        return list;
	}
}
