package com.nihilent.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.nihilent.sender.MessageSender;

public class NetClientGet {

	final static Logger log = Logger.getLogger(NetClientGet.class);
	
	public static void main(String[] args) {

		try {

			URL url = new URL(
					"http://localhost:8080/UnicoAssignment/json/product/showmsg");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			
			while ((output = br.readLine()) != null) {
				
				if (log.isDebugEnabled()) {
					log.debug("Received Message: "+output);
				}

				
			}
			
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
			
		}

	}

}