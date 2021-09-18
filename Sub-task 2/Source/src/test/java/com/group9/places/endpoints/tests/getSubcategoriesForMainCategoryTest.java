package com.group9.places.endpoints.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.group9.places.endpoints.Endpoints;

public class getSubcategoriesForMainCategoryTest {

	@Test
	public void subcategoriesForMainCategory() {
		Endpoints endpoints = new Endpoints();
		Assertions.assertThrows(NullPointerException.class, () -> endpoints.getSubcategoriesForMainCategory(null, null),
				"Worked");
	}
}
