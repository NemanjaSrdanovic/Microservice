package com.group9.places.model;

import java.util.Set;

/**
 * This object is used to save place data returned by the API.
 * 
 * @author Group 9
 *
 */
public class Place {

	private String name;
	private Adress adress;
	private Set<String> categories;

	/**
	 * Instantiates a new Adress object. The parameters must not be null.
	 * 
	 * @param name
	 * @param adress
	 * @param categories
	 */
	public Place(String name, Adress adress, Set<String> categories) {
		super();
		this.name = name;
		this.adress = adress;
		this.categories = categories;
	}

	/**
	 * Instantiates a new Adress object. The parameters must not be null.
	 * 
	 * @param name
	 * @param adress
	 */
	public Place(String name, Adress adress) {

		this.name = name;
		this.adress = adress;
	}

	/**
	 * Returns if the input category is equals with one of the place categories.
	 * 
	 * @param category
	 * @return
	 */
	public boolean isOfCategory(String category) {

		for (String placeCategory : this.categories) {

			if (placeCategory.equals(category))
				return true;
		}

		return false;

	}

	/**
	 * Returns the name of the place.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the address of the place.
	 * 
	 * @return
	 */
	public Adress getAdress() {
		return adress;
	}

	/**
	 * Returns all categories that this place contains.
	 * 
	 * @return
	 */
	public Set<String> getCategories() {
		return categories;
	}

	/**
	 * Replaces the categories list with the input categories.
	 * 
	 * @param categories
	 */
	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	/**
	 * Implements a working toString method for this object to ease debugging and to
	 * represent the object in a certain way.
	 */
	@Override
	public String toString() {
		return "Place [name:" + name + ", adress:" + adress + "]";
	}

}
