package com.group9.places.endpoints.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.group9.places.endpoints.Endpoints;

public class getPlacesByCategorieTest {

	@Test
	public void placesByCategorieTest() {
		Endpoints endpoints = new Endpoints();
		Assertions.assertThrows(NullPointerException.class, () -> endpoints.getPlacesByCategorie(null, null), "Worked");
	}
}
