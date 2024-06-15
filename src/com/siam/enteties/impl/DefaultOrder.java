package com.siam.enteties.impl;

import com.siam.enteties.Order;
import com.siam.enteties.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DefaultOrder implements Order {

	private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;

	private String creditCardNumber;
	private Product[] products;
	private int customerId;

	public DefaultOrder(String creditCardNumber, Product[] products, int customerId) {
		this.creditCardNumber = creditCardNumber;
		this.products = products;
		this.customerId = customerId;
	}
	public DefaultOrder() {

	}

	@Override
	public boolean isCreditCardNumberValid(String creditCardNumber) {
		return creditCardNumber.length() == AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER &&
				!creditCardNumber.contains(" ") && Long.parseLong(creditCardNumber) > 0;
	}

	@Override
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	@Override
	public void setProducts(Product[] products) {
		this.products = products;
	}

	@Override
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	@Override
	public int getCustomerId() {
		return this.customerId;
	}

	@Override
	public String toString() {
		return "Order: customer id - " + this.customerId + "\t" +
				"credit card number - " + this.creditCardNumber + "\t" +
				"products - " + Arrays.toString(this.products);
	}





}
