package com.excilys.persistance.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.slf4j.LoggerFactory;

public class DateFormator {
	public static Timestamp formatDate(String date) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			java.util.Date parsedDate = dateFormat.parse(date);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch (Exception e) {
			System.out.println("The date you entered could\'nt be parsed");
			LoggerFactory.getLogger(DateFormator.class).info("Failed to parse date : " + e.getMessage());
		}
		return timestamp;
	}
}
