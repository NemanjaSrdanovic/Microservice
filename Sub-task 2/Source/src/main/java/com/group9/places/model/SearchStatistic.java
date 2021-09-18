package com.group9.places.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.group9.places.controller.OngoingSearches;

/**
 * 
 * Object created using the singleton pattern which means that only one instance
 * of this object can be created, so that data duplication is avoided.
 * 
 * @author Group 9
 *
 */
public class SearchStatistic {

	private static Logger logger = LoggerFactory.getLogger(OngoingSearches.class);

	private static LinkedHashMap<String, Integer> categorySearches;

	/**
	 * Instantiates a new OngoingSearches object.
	 */
	private SearchStatistic() {

	}

	/**
	 * Instantiates Map containing all running searches.
	 */
	static {
		categorySearches = new LinkedHashMap<String, Integer>();
	}

	/**
	 * Addes the category for which the search is triggered to the map, so that for
	 * every existing key the value is incremented.
	 * 
	 * @param searchedCategory
	 */
	public static void addToCategorySearchStatistic(String searchedCategory) {

		if (categorySearches.containsKey(searchedCategory)) {

			int searches = categorySearches.get(searchedCategory);

			categorySearches.put(searchedCategory, searches + 1);
		} else {

			categorySearches.put(searchedCategory, 1);
		}

	}

	/**
	 * Returns the map with all searches (keys) and all corresponding search numbers
	 * (value) sorted by order.
	 * 
	 * @param sortingOrder
	 * @return
	 */
	public static LinkedHashMap<String, Integer> getCategorySearches(String sortingOrder) {

		return sortHashMap(categorySearches, sortingOrder);
	}

	/**
	 * Sorts the map by ascending or descending order.
	 * 
	 * @param hashMapToSort
	 * @param sortingOrder
	 * @return
	 */
	private static LinkedHashMap<String, Integer> sortHashMap(LinkedHashMap<String, Integer> hashMapToSort,
			String sortingOrder) {

		List<Map.Entry<String, Integer>> listFromHashMap = new LinkedList<Map.Entry<String, Integer>>(
				categorySearches.entrySet());
		String sortAscending = "ascending";

		Collections.sort(listFromHashMap, new Comparator<Map.Entry<String, Integer>>() {

			public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {

				if (sortingOrder.equals(sortAscending)) {
					return (entry1.getValue().compareTo(entry2.getValue()));
				} else {

					return (entry2.getValue().compareTo(entry1.getValue()));
				}

			}

		});

		LinkedHashMap<String, Integer> sortedHashMap = new LinkedHashMap<String, Integer>();

		for (Map.Entry<String, Integer> entry : listFromHashMap) {
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}

		return sortedHashMap;
	}

}
