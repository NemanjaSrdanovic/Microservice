package com.group9.places.controller;

import java.util.LinkedList;

import com.group9.places.model.SearchResault;

/**
 * Object created using the singleton pattern which means that only one instance
 * of this object can be created, so that data duplication and multiple method
 * calls are avoided.
 * 
 * @author Group 9
 *
 */
public class ServiceController {

	private static LinkedList<String> defaultMessages = new LinkedList<String>();

	/**
	 * Instantiates a new ServiceController object.
	 */
	private ServiceController() {
	}

	/**
	 * 
	 * Extracts the search referenced by the search identifier from the
	 * OngoingSearches object and uses the search object function to return the
	 * subcategories for the inputed main category. Finally returns a SearchResault
	 * object containing the search identifier and extracted objects.
	 * 
	 * @param mainCategory     (Main category for which the subcategories have to be
	 *                         returned)
	 * @param searchIdentifier (Identifier to find the search in which the
	 *                         categories are saved)
	 * @return
	 */
	public static SearchResault retrieveSubcategoriesForMainCategory(String mainCategory, String searchIdentifier) {

		Search search = OngoingSearches.getSearches().get(searchIdentifier);

		return new SearchResault(searchIdentifier, search.getAllSubcategoriesForMainCategory(mainCategory));

	}

	/**
	 * 
	 * Extracts the search referenced by the search identifier from the
	 * OngoingSearches object and uses the search object function to return the
	 * places for the inputed category. Finally returns a SearchResault object
	 * containing the search identifier and extracted objects.
	 * 
	 * @param category         (Category for which the Places have to be returned)
	 * @param searchIdentifier (Identifier to find the search in which the places
	 *                         are saved)
	 * @return
	 */
	public static SearchResault retriveAllPlacesForCategory(String category, String searchIdentifier) {

		Search search = OngoingSearches.getSearches().get(searchIdentifier);

		return new SearchResault(searchIdentifier, search.getAllPlacesForCategory(category));
	}

	/**
	 * Returns the html view for the initial microservice endpoint.
	 * 
	 * @return
	 */
	public static String recieveDefaultMessages() {
		String returnVal = "";
		defaultMessages.clear();
		defaultMessages.push("<!DOCTYPE html>\n" + "<html>\n" + "<body>\n" + "<table>\n"
				+ "<td><img src=\"https://gitlab.dke.univie.ac.at/uploads/-/system/project/avatar/479/66967-google-places-application-programming-maps-location-interface.png\" width=\"330\" height=\"385\" alt=\"Places Project Logo. Non commercial use only!\"></td>\n"
				+ "<td>\n"
				+ "<h2>To see the documentation please follow this link: https://eis.dke.univie.ac.at/eis2021/details/?id=479</h2>\n"
				+ "<h2>There are currently 4 main endpoints available:</h2>\n"
				+ "<ul style=\"list-style-type:circle;\">\n"
				+ "<li>Endpoint 1: places//byAdress/{streetName}/{houseNumber}/{radius}</li>\n"
				+ "<li>Endpoint 2: places//byCategory/{category}/{searchIdentifier}</li>\n"
				+ "<li>Endpoint 3: places/getSubcategories/{mainCategory}/{searchIdentifier}</li>\n"
				+ "<li>Endpoint 4: places/searchStatistic/{sortingOrder}</li>\n" + "</ul>\n" + "<br>\n"
				+ "<p>Please visit the documentation to learn more about the endpoints.</p>\n" + "</td>\n"
				+ "</table>\n" + "</body>\n" + "</html>\n");

		for (int i = 0; i < defaultMessages.size(); ++i) {
			returnVal += defaultMessages.get(i) + "\n";
		}
		return returnVal;
	}
}
