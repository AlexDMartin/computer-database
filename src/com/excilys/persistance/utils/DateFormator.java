package com.excilys.persistance.utils;

import java.util.Date;

public class DateFormator {
	public static String formatDate(Date d) {
		String year = Integer.toString(d.getYear() + 1900) ;
		String month = Integer.toString(d.getMonth() + 1);
		String day = Integer.toString(d.getDate());
		String hour = Integer.toString(d.getHours());
		String min = Integer.toString(d.getMinutes());
		String sec = Integer.toString(d.getSeconds());
		return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
	}
}
