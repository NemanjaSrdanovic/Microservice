package com.group9.places.api;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.group9.places.constants.ServiceConstants;
import com.group9.places.model.Adress;

/**
 * This object is used by the controller to make a request to a API which
 * converts written addresses into addresses containing latitude and longitude.
 * Created using the singleton pattern which means that only one instance of
 * this object can be created, so that request sending ins centralised.
 * 
 * @author Group 9
 *
 */
public class GeocodingAPI {

	private static Logger logger = LoggerFactory.getLogger(GeocodingAPI.class);
	private static HttpRequest httpRequest;
	private static long nextRequestSlot;

	/**
	 * Instantiates a new GeocodingAPI object.
	 */

	private GeocodingAPI() {

	}

	static {

		httpRequest = new HttpRequest();
		nextRequestSlot = System.currentTimeMillis();

	}

	/**
	 * This method is sending the street name and house number that are inputed over
	 * the URI to the nominatim API. That API is converting the string input into
	 * coordinates (lat / lon) and returning them as a JSON object. After extracting
	 * those information from the JSON object a new Address object containing the
	 * address as whole and longitude and latitude is created and returned.
	 * 
	 * @param streetName  (String input from the URI)
	 * @param houseNumber (String input from the URI)
	 * @return (Address object containing address as a string and latitude and
	 *         longitude)
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws JSONException
	 */
	public static Adress getLocationCoordiates(String streetName, String houseNumber)
			throws MalformedURLException, IOException, JSONException {

		String adress = streetName + " " + houseNumber;

		timeUntilNextRequest();

		String urlToSendTo = "https://nominatim.openstreetmap.org/search.php?q=" + streetName + "+" + houseNumber
				+ "&polygon_geojson=1&format=jsonv2";

		JSONArray myResponse = new JSONArray(httpRequest.sendHttpRequest(urlToSendTo).toString());

		return new Adress(adress, myResponse.getJSONObject(0).get("lat").toString(),
				myResponse.getJSONObject(0).get("lon").toString());

	}

	/**
	 * Between request there has to be a minimum waiting time of 3 seconds. If this
	 * time is not exceeded the system will wait the resting time else a new request
	 * time is set.
	 */
	private static void timeUntilNextRequest() {

		if (System.currentTimeMillis() < getNextRequestSlot()) {

			try {

				Thread.sleep(getNextRequestSlot() - System.currentTimeMillis());

				logger.info("Waiting for next request slot.");

			} catch (InterruptedException e) {
				logger.error("GeocodingAPI Request Exceptin: {}", e.getMessage());
				e.printStackTrace();
			}

		}
		setNextRequestSlot(System.currentTimeMillis() + ServiceConstants.TIME_MILIS_UNTIL_NEXT_REQUEST);

	}

	/**
	 * Returns the time in milliseconds when the next request can be send.
	 * 
	 * @return
	 */
	public static long getNextRequestSlot() {
		return nextRequestSlot;
	}

	/**
	 * Sets the time in milliseconds when the next request can be send.
	 * 
	 * @param nextRequestSlot
	 */
	public static void setNextRequestSlot(long nextRequestSlot) {
		GeocodingAPI.nextRequestSlot = nextRequestSlot;
	}

}
