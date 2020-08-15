package com.shyam.api.productservice.enums;

public enum Size {
	XS("Extra Small"), S("Small"), M("Medium"), L("Large"), XL("Extra Large"), XXL("Double Extra Large"),
	XXXL("Triple Extra Large");

	private String name;

	private Size(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
