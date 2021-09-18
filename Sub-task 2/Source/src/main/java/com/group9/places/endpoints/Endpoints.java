package com.group9.places.endpoints;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group9.places.constants.ServiceConstants;
import com.group9.places.controller.OngoingSearches;
import com.group9.places.controller.Search;
import com.group9.places.controller.ServiceController;
import com.group9.places.model.SearchResault;
import com.group9.places.model.SearchStatistic;

/**
 * 
 * Contains all Endpoints that can be called to get described data.
 * 
 * @author Group 9
 *
 */

@RestController
@RequestMapping("/places")
public class Endpoints {

	private static Logger logger = LoggerFactory.getLogger(Endpoints.class);

	/**
	 * Returns a html view containing all endpoints that can be called.
	 * 
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/")
	public String defaultMessages() {

		return ServiceController.recieveDefaultMessages();
	}

	/**
	 * Returns all called searches categories that be sorted ascending or descending
	 * (by called value) by adding it to the URL.
	 * 
	 * @param (sortingOrder can be ascending or descending)
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/searchStatistic/{sortingOrder}")
	public LinkedHashMap<String, Integer> searchStatistic(@PathVariable String sortingOrder) {

		return SearchStatistic.getCategorySearches(sortingOrder);
	}

	/**
	 * Returns all places that were returned by the API for a input address and that
	 * contain the inputed category.
	 * 
	 * @param category         (Category which the returned place has to contain)
	 * @param searchIdentifier (Identifier to find the search data)
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/byCategory/{category}/{searchIdentifier}")
	public SearchResault getPlacesByCategorie(@PathVariable String category, @PathVariable String searchIdentifier) {

		SearchStatistic.addToCategorySearchStatistic(category);

		return ServiceController.retriveAllPlacesForCategory(category, searchIdentifier);
	}

	/**
	 * 
	 * Returns all subcategories that are linked to the main category for the places
	 * returned by the API.
	 * 
	 * @param mainCategory     (Main category for which the subcategories are
	 *                         returned)
	 * @param searchIdentifier (Identifier to find the search data)
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/getSubcategories/{mainCategory}/{searchIdentifier}")
	public SearchResault getSubcategoriesForMainCategory(@PathVariable String mainCategory,
			@PathVariable String searchIdentifier) {

		return ServiceController.retrieveSubcategoriesForMainCategory(mainCategory, searchIdentifier);
	}

	/**
	 * 
	 * Creates an new search with a corresponding unique search identifier and
	 * returns all categories that the surrounding places contain.
	 * 
	 * @param streetName  (String input from the URI)
	 * @param houseNumber (String input from the URI)
	 * @param radius      (String input from the URI)
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	@CrossOrigin
	@RequestMapping(value = "/byAdress/{streetName}/{houseNumber}/{radius}")
	public SearchResault getPlacesByAdressAndRadius(@PathVariable String streetName, @PathVariable String houseNumber,
			@PathVariable String radius) throws IOException, JSONException {

		String searchIdentifier = UUID.randomUUID().toString().substring(0, 5);

		Search newSearch = new Search(searchIdentifier);

		OngoingSearches.getSearches().put(searchIdentifier, newSearch);

		logger.info("New search created. ID :  {}", searchIdentifier);

		return newSearch.getCategoriesForSearchedAdresses(streetName, houseNumber, radius);
	}

	/**
	 * Each search is automatically removed / ended 5 minutes after the search was
	 * created, so no further messages can be sent to it. This scheduler is calling
	 * a method to do that every 5 minutes.
	 */
	@Scheduled(fixedRate = ServiceConstants.TIME_MILLIS_TO_CHECK_FOR_EXPIRED_SEARCHES)
	private void checkForExpiredSearches() {

		OngoingSearches.removeExpiredSearches();
	}

}
