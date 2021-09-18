package com.group9.places.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONException;

import com.group9.places.api.PlacesAPI;
import com.group9.places.model.Place;
import com.group9.places.model.SearchResault;

/**
 * 
 * This object is used to save and manage data regarding a search which can be
 * accessed for its whole life cycle (5 min).
 * 
 * @author Group 9
 *
 */
public class Search {

	private String searchIdentifier;
	private PlacesAPI placesAPI;
	private long runningTime;
	private List<Place> completeResaultList;
	private Set<String> completeCategoriesList;
	private SearchResault searchResault;

	/**
	 * Instantiates a new Search object. The parameters must not be null.
	 * 
	 * @param searchIdentifier
	 */
	public Search(String searchIdentifier) {
		super();
		this.searchIdentifier = searchIdentifier;
		this.runningTime = System.currentTimeMillis();
		this.completeResaultList = new ArrayList<Place>();
		this.placesAPI = new PlacesAPI();
		this.completeCategoriesList = new HashSet<String>();

	}

	/**
	 * 
	 * Hand over the URI string input to the placesAPI object which returns all
	 * Places and their relevant data for that input. That data is than filtered and
	 * only the categories for those places are extracted and returned. Neverless
	 * all data in it form is saved (complete result, all categories and main
	 * categories for that search) so that only one API call is needed.
	 * 
	 * @param streetName  (String input from the URI)
	 * @param houseNumber (String input from the URI)
	 * @param radius      (String input from the URI)
	 * @return (SearchResault object containing the identifier and the result
	 *         object)
	 * @throws JSONException
	 * @throws IOException
	 */
	public SearchResault getCategoriesForSearchedAdresses(String streetName, String houseNumber, String radius)
			throws JSONException, IOException {

		this.completeResaultList = placesAPI.getAllPlacesForAdressAndRadius(streetName, houseNumber, radius);

		extractAllCategories(completeResaultList);

		this.searchResault = new SearchResault(this.searchIdentifier, getMainCategories());

		return this.searchResault;

	}

	/**
	 * Return the identifier for this search.
	 * 
	 * @return
	 */
	public String getSearchIdentifier() {
		return searchIdentifier;
	}

	/**
	 * Returns the running time of this search.
	 * 
	 * @return
	 */
	public long getRunningTime() {
		return runningTime;
	}

	/**
	 * Iterates all Places returned by the API and extracts only the categories for
	 * those places. By saving those categories in a Set non of the categories are
	 * repeated and we have a unique category list for all relevant places.
	 * 
	 * @param resaultList
	 */
	private void extractAllCategories(List<Place> resaultList) {

		for (Place place : resaultList) {

			for (String placeCategory : place.getCategories()) {

				this.completeCategoriesList.add(placeCategory);

			}

		}

	}

	/**
	 * The category set is iterated an only the main categories are extracted and
	 * saved in a separate object.
	 * 
	 * @return
	 */
	public Set<String> getMainCategories() {

		Set<String> mainCategoriesList = new HashSet<String>();

		for (String category : this.completeCategoriesList) {

			if (!category.contains(".")) {

				mainCategoriesList.add(category);

			}
		}

		return mainCategoriesList;

	}

	/**
	 * 
	 * Function extracting all subcategories from the completeCategoryList for the
	 * main category input.
	 * 
	 * @param mainCategory (String input for which the search has to be done)
	 * @return
	 */
	public Set<String> getAllSubcategoriesForMainCategory(String mainCategory) {

		Set<String> subcategoriesList = new HashSet<String>();

		for (String category : this.completeCategoriesList) {

			if (category.contains(".") && category.contains(mainCategory)) {

				subcategoriesList.add(category);

			}
		}

		return subcategoriesList;

	}

	/**
	 * Function extracting all Places from the compleateResaultList containing the
	 * input category.
	 * 
	 * @param category (String input for which the search has to be done)
	 * @return
	 */
	public Set<Place> getAllPlacesForCategory(String category) {

		Set<Place> placesList = new HashSet<Place>();

		for (Place place : this.completeResaultList) {

			if (place.isOfCategory(category)) {

				placesList.add(place);

			}
		}

		return placesList;

	}

	/**
	 * Implements a working toString method for this object to ease debugging and to
	 * represent the object in a certain way.
	 */
	@Override
	public String toString() {
		return "Search resault [searchIdentifier:" + searchIdentifier + ", ResaultList:" + completeResaultList + "]";
	}

}
