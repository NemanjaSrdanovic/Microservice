package com.group9.places.model;

/**
 * 
 * This object is used to save data regarding a search addresses.
 * 
 * @author Group 9
 *
 */
public class Adress {

	private String adressLongitude;
	private String adressLatitude;
	private String writenAdress;

	/**
	 * Instantiates a new Adress object. The parameters must not be null.
	 * 
	 * @param writenAdress
	 * @param adressLongitude
	 * @param adressLatitude
	 */
	public Adress(String writenAdress, String adressLongitude, String adressLatitude) {
		super();

		this.writenAdress = writenAdress;
		this.adressLongitude = adressLongitude;
		this.adressLatitude = adressLatitude;

	}

	/**
	 * Returns the Adress object longitude.
	 * 
	 * @return
	 */
	public String getAdressLongitude() {
		return adressLongitude;
	}

	/**
	 * Returns the Adress object latitude.
	 * 
	 * @return
	 */
	public String getAdressLatitude() {
		return adressLatitude;
	}

	/**
	 * Returns the Adress object written address.
	 * 
	 * @return
	 */
	public String getWritenAdress() {
		return writenAdress;
	}

	/**
	 * Implements a working toString method for this object to ease debugging and to
	 * represent the object in a certain way.
	 */
	@Override
	public String toString() {
		return "Adress [adressLongitude:" + adressLongitude + ", adressLatitude:" + adressLatitude + ", writenAdress:"
				+ writenAdress + "]";
	}

}
