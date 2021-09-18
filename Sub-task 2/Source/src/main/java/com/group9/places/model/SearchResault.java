package com.group9.places.model;

/**
 * 
 * This object is used to return the search result to the caller.
 * 
 * @author Group 9
 *
 */
public class SearchResault {

	private String searchIdentifier;
	private Object resaultObject;

	/**
	 * Instantiates a new SearchResault object. The parameters must not be null.
	 * 
	 * @param searchIdentifier
	 * @param resaultObject
	 */
	public SearchResault(String searchIdentifier, Object resaultObject) {
		super();
		this.searchIdentifier = searchIdentifier;
		this.resaultObject = resaultObject;
	}

	/**
	 * Returns the identifier of the search/searchResault
	 * 
	 * @return
	 */
	public String getSearchIdentifier() {
		return searchIdentifier;
	}

	/**
	 * Returns the result from the search object.
	 * 
	 * @return
	 */
	public Object getResaultObject() {
		return resaultObject;
	}

	/**
	 * Implements a working toString method for this object to ease debugging and to
	 * represent the object in a certain way.
	 */
	@Override
	public String toString() {
		return "SearchResault [searchIdentifier=" + searchIdentifier + ", resaultObject=" + resaultObject + "]";
	}

}
