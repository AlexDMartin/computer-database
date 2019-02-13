package com.excilys.persistance.utils;

import java.util.Date;

public class DateFormator {
	/**
	 * @author Alex Martin
	 * @param date
	 * @return String that represent the Date formatted to YYYY-MM-DD hh:mm:ss
	 */
	public static String formatDate(Date date) {
		String year = Integer.toString(date.getYear() + 1900) ;
		String month = Integer.toString(date.getMonth() + 1);
		String day = Integer.toString(date.getDate());
		String hour = Integer.toString(date.getHours());
		String min = Integer.toString(date.getMinutes());
		String sec = Integer.toString(date.getSeconds());
		return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
	}
}
