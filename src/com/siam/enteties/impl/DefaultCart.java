package com.siam.enteties.impl;

import com.siam.enteties.Cart;
import com.siam.enteties.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class DefaultCart implements Cart {

	private Product[] products;
	int lastAddedIndex;
	private static final int DEFAULT_PRODUCT_CAPACITY = 10;


	{
		products = new Product[0];
		lastAddedIndex = 0;
	}

	@Override
	public boolean isEmpty() {
		if(products.length == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void addProduct(Product product) {
		if(product == null) {
			return ;
		}
		if(isEmpty()) {
			products = new DefaultProduct[products.length + 1];
		}
		if(products.length <= lastAddedIndex) {
			products = Arrays.copyOf(products, products.length * 2);
		}
		products[lastAddedIndex++] = product;
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
		products = new Product[DEFAULT_PRODUCT_CAPACITY];
	}

}
