package com.group9.places.controller;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.group9.places.constants.ServiceConstants;

/**
 * Object created using the singleton pattern which means that only one instance
 * of this object can be created, so that data duplication is avoided.
 * 
 * @author Group 9
 *
 */
public class OngoingSearches {

	private static Logger logger = LoggerFactory.getLogger(OngoingSearches.class);

	private static LinkedHashMap<String, Search> searches;

	/**
	 * Instantiates a new OngoingSearches object.
	 */
	private OngoingSearches() {

	}

	/**
	 * Instantiates Map containing all running searches.
	 */
	static {
		searches = new LinkedHashMap<String, Search>();
	}

	/**
	 * Returns the list with all ongoing searches.
	 * 
	 * @return
	 */
	public static LinkedHashMap<String, Search> getSearches() {
		return searches;
	}

	/**
	 * Replaces the running searches list with a new one.
	 * 
	 * @param searches
	 */
	private static void setGames(LinkedHashMap<String, Search> searches) {
		OngoingSearches.searches = searches;
	}

	/**
	 * Function which iterates through the searches list and checking if any search
	 * has exceeded the maximal running time. If so that search is removed due to
	 * exparation which is logged into the console.
	 */
	public static void removeExpiredSearches() {

		LinkedHashMap<String, Search> newSearchList = new LinkedHashMap<>();

		for (Entry<String, Search> entry : OngoingSearches.getSearches().entrySet()) {

			if (!(System.currentTimeMillis()
					- entry.getValue().getRunningTime() >= ServiceConstants.TIME_MILLIS_AFTER_WHICH_SEARCH_EXPIRES)) {

				newSearchList.put(entry.getKey(), entry.getValue());

			} else if (System.currentTimeMillis()
					- entry.getValue().getRunningTime() >= ServiceConstants.TIME_MILLIS_AFTER_WHICH_SEARCH_EXPIRES) {

				logger.debug("Search removed due to expiration. Search ID: {}", entry.getKey());
			}

		}

		logger.info("Ongoint searches: {}", newSearchList.size());

		OngoingSearches.setGames(newSearchList);

	}

}
