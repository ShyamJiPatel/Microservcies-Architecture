package com.shyam.api.productservice.enums;

public enum Currency {
	INR("Rupee"), USD("Dollar"), JPY("Yen");

	private String name;

	private Currency(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
