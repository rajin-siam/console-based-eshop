package com.siam.enteties.impl;

import com.siam.enteties.Cart;
import com.siam.enteties.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class DefaultCart implements Cart {

	private List<Product> products = new ArrayList<>();

	@Override
	public boolean isEmpty() {
		if(products.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void addProduct(Product product) {
		products.add(product);
		return ;
	}

	@Override
	public Product[] getProducts() {
		int nonNullProductNUmbers = 0;
		for(Product product : products) {
			if(product != null) {
				nonNullProductNUmbers++;
			}
		}
		int index = 0;
		Product[] nonNullProducts = new Product[nonNullProductNUmbers];
		for (Product product : products) {
			if(product != null) {
				nonNullProducts[index++] = product;
			}
		}

		return nonNullProducts;
	}

	@Override
	public void clear() {
		products.clear();
	}

}
