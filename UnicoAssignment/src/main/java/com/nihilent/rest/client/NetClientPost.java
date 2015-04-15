package com.nihilent.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

public class NetClientPost {

	final static Logger log = Logger.getLogger(NetClientGet.class);
	
	public static void main(String[] args) {

		try {

			URL url = new URL(
					"http://localhost:8080/UnicoAssignment/json/product/pushmsg");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = "{\"qty\":100,\"name\":\"iPad 4\"}";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			
			while ((output = br.readLine()) != null) {
				
				if (log.isDebugEnabled()) {
					log.debug("Send Message: "+output);
				}

			
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			log.error(e.getMessage());
			
		} catch (IOException e) {

			log.error(e.getMessage());

		}

	}

}