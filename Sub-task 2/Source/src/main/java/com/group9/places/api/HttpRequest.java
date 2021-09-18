package com.group9.places.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This HttpRequest object is used by the API requesting objects to send a
 * request to the API.
 * 
 * @author Group 9
 *
 */
public class HttpRequest {

	private static Logger logger = LoggerFactory.getLogger(HttpRequest.class);

	/**
	 * 
	 * This method is called by handing over a URL as string. After opening an Http
	 * connection and setting the communication parameters a request is sent to the
	 * hand over URL. The response code to the request is logged and the response
	 * saved as a StringBuffer.
	 * 
	 * @param urlToSendTo (String that has to be a valid URL/not null)
	 * @return
	 */
	public StringBuffer sendHttpRequest(String urlToSendTo) {

		StringBuffer response = null;

		try {

			URL url = new URL(urlToSendTo);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");

			int responseCode = con.getResponseCode();

			logger.info("Sending 'GET' request to URL :  {}", url);
			logger.debug("Response Code: {}", responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		} catch (Exception e) {
			logger.error("HttpRequest error: {}", e.getMessage());
		}

		return response;

	}
}
