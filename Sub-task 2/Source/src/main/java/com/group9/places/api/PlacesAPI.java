package com.group9.places.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.group9.places.constants.ServiceConstants;
import com.group9.places.model.Adress;
import com.group9.places.model.Place;

/**
 * This object is used by the controller to make a request to a API which
 * returns JSON objects containing places of interest.
 * 
 * @author Group 9
 *
 */
public class PlacesAPI {

	private static Logger logger = LoggerFactory.getLogger(PlacesAPI.class);
	private HttpRequest httpRequest;

	/**
	 * Instantiates a new PlacesAPI object.
	 */
	public PlacesAPI() {

		this.httpRequest = new HttpRequest();
	}

	/**
	 * 
	 * First the String inputs are converted to Address objects containing latitude
	 * and longitude after which the HttpRequest object is used to send that
	 * information to the API which returns a JSON object containing raw Places
	 * data. That data is used as input for the "extractAllPlacesFromJsonResault"
	 * function which extracts only relevant data from the raw data.
	 * 
	 * @param streetName  (String input from the URI)
	 * @param houseNumber (String input from the URI)
	 * @param radius      (String input from the URI)
	 * @return (A list containing Places objects that can be sorted)
	 * @throws IOException
	 * @throws JSONException
	 */
	public List<Place> getAllPlacesForAdressAndRadius(String streetName, String houseNumber, String radius)
			throws IOException, JSONException {

		Adress searchAdress = GeocodingAPI.getLocationCoordiates(streetName, houseNumber);

		String urlToSendTo = "https://api.geoapify.com/v2/places?categories=" + ServiceConstants.mainCategories
				+ "&filter=circle:" + searchAdress.getAdressLatitude() + "," + searchAdress.getAdressLongitude() + ","
				+ radius + "&limit=20&apiKey=" + ServiceConstants.apiKEY;

		JSONObject myResponse = new JSONObject(httpRequest.sendHttpRequest(urlToSendTo).toString());

		return extractAllPlacesFromJsonResault(myResponse);

	}

	/**
	 * In this function a JSON object containing a lot of data for Places is
	 * iterated and only relevant data about a Place is extracted an saved into a
	 * Place object. This Place object is containing a name of the place, its
	 * address and all categories assigned to that place.
	 * 
	 * 
	 * @param myResponse (JSON object containing lots of places data)
	 * @return (List of Places with relevant places data)
	 */
	private List<Place> extractAllPlacesFromJsonResault(JSONObject myResponse) {

		List<Place> finalList = new ArrayList<Place>();
		JSONArray array = myResponse.getJSONArray("features");

		for (int i = 0; i < array.length(); i++) {

			Set<String> helperCategories = new HashSet<String>();
			JSONObject object3 = array.getJSONObject(i);
			JSONObject object4 = object3.getJSONObject("properties");
			JSONArray categoryArray = object4.getJSONArray("categories");

			Adress helperAdress = new Adress(object4.get("address_line2").toString(), object4.get("lon").toString(),
					object4.get("lat").toString());
			Place helperPlace;

			for (int y = 0; y < categoryArray.length(); y++) {

				helperCategories.add(categoryArray.getString(y));

			}

			try {
				helperPlace = new Place(object4.get("name").toString(), helperAdress, helperCategories);
			} catch (Exception e) {

				helperPlace = new Place("nameNotRegistred", helperAdress, helperCategories);
			}

			finalList.add(helperPlace);

		}

		return finalList;

	}
}
