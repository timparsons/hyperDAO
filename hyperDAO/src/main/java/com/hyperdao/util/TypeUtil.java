/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.util;

import javax.xml.bind.DatatypeConverter;

/**
 * @author Tim
 *
 */
public class TypeUtil {

	public static Integer parseInteger(String i) {
		return new Integer(i);
	}
	
	public static String printInteger(Integer i) {
		return DatatypeConverter.printInt(i);
	}
	
	public static Double parseDouble(String i) {
		return new Double(i);
	}
	
	public static String printDouble(Double i) {
		return DatatypeConverter.printDouble(i);
	}
	
	public static Boolean parseBoolean(String i) {
		return new Boolean(i);
	}
	
	public static String printBoolean(Boolean i) {
		return DatatypeConverter.printBoolean(i);
	}
}
