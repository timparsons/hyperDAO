package com.hyperdao.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.time.DateUtils;

/**
 * Collection of utilities for dealing with Dates and times.
 * <br/>
 * Extends {@link DateUtils}
 * @author Tim
 *
 */
public class DateUtil extends DateUtils{
	public static Date parseDate(String s) {
		return DatatypeConverter.parseDate(s).getTime();
	}

	public static String printDate(Date dt) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		return DatatypeConverter.printDate(cal);
	}
	
	public static java.sql.Timestamp parseTimeStamp(String s) {
		return new Timestamp(DatatypeConverter.parseDate(s).getTime().getTime());
	}
	
	public static String printTimeStamp(Timestamp time) {
		if(time == null) {
			return null;
		}
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(time.getTime());
		return DatatypeConverter.printDateTime(cal);
	}

	public static String printDate(java.sql.Timestamp dt) {
		if(dt == null) {
			return null;
		}
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		return DatatypeConverter.printDate(cal);
	}
	
	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}
	
	public static java.sql.Date convertToSQLDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}
}

