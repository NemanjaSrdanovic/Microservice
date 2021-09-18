package com.group9.places.endpoints.tests;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import com.group9.places.controller.ServiceController;
import com.group9.places.endpoints.Endpoints;

public class searchStatisticTest {
	@Test
	public void searchStatisticPosTest() {
		Endpoints endpoints = new Endpoints();
		LinkedHashMap<String, Integer> helper = new LinkedHashMap<>();
		helper.put(null, 1);
		assertThat(endpoints.searchStatistic(""), not(equalTo(helper.get(null))));
	}

	@Test
	public void defaultTest() {
		Endpoints endpoints = new Endpoints();
		String x = ServiceController.recieveDefaultMessages();
		assertThat(endpoints.defaultMessages(), equalTo(x));
	}

}
