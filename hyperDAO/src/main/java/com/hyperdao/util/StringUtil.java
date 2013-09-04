/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.util;

/**
 * @author Tim
 *
 */
public class StringUtil {

	public static char[] parseCharArray(String s) {
		return s.toCharArray();
	}
	
	public static String printString(char[] s) {
		if(s == null) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		for(char i : s) {
			builder.append(i);
		}
		return builder.toString();
	}

}
