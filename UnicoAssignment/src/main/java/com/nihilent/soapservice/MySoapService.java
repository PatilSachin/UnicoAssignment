package com.nihilent.soapservice;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.nihilent.consumer.QueueListener;


@WebService
public class MySoapService {

	final static Logger log = Logger.getLogger(MySoapService.class);
	
	private static List<String> gcdList;
	
	private Connection conn;

	private Statement stmt;


	@WebMethod
	public int gcd() {
		if (log.isDebugEnabled()) {
			log.debug("Start gcd");
		}
		System.out.println(" In GCD");
		QueueListener listener = new QueueListener();
		String message;
		try {
			message = listener.listenSingleMessage();
			
			String[] arr = message.split("-");
			BigInteger num1 = new BigInteger(arr[0]);
			BigInteger num2 = new BigInteger(arr[1]);
		
			System.out.println(" In GCD : num1"+num1);
			System.out.println(" In GCD : num2"+num2);
			
			if (gcdList == null) {
				gcdList = new ArrayList<String>();
			}
			gcdList.add((num1.gcd(num2)).toString());
			return (num1.gcd(num2)).intValue();
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			
		}
		return 0;
	}

	@WebMethod
	public List<String> gcdList() {
		System.out.println(" In gcdList");
		if (log.isDebugEnabled()) {
			log.debug("Start gcdList");
		}
		try {
			
			List<String> gcdValues = new ArrayList<String>();
			
			if (log.isDebugEnabled()) {
				log.debug("End gcdList");
			}
			return gcdList;
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			
		}
		return null;
	}


	@WebMethod
	public int getSum() {
		System.out.println(" In getSum");
		if (log.isDebugEnabled()) {
			log.debug("End getSum");
		}
		int sum = 0;
		try {
			
			for (String gcd : gcdList) {
				sum = sum + Integer.parseInt(gcd); 
			}
			
			if (log.isDebugEnabled()) {
				log.debug("End getSum");
			}
			return sum;
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			
		}
		return sum;
	}

	
}
