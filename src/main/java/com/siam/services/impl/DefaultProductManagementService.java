package com.siam.services.impl;

import com.siam.enteties.Product;
import com.siam.enteties.impl.DefaultProduct;
import com.siam.services.ProductManagementService;
import com.siam.storage.ProductStoringService;
import com.siam.storage.impl.DefaultProductStoringService;

import java.util.List;

public class DefaultProductManagementService implements ProductManagementService {

	private static DefaultProductManagementService instance;

	private static List<Product> products;



	static  {
		ProductStoringService productStoringService = DefaultProductStoringService.getInstance();
		products = productStoringService.loadProducts();
	}

	private DefaultProductManagementService() {

	}

	public static ProductManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultProductManagementService();
		}
		return instance;
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public Product getProductById(int productIdToAddToCart) {
		for (Product product : products) {
			if (product.getId() == productIdToAddToCart) {
				return product;
			}
		}
		return null;
	}

}
