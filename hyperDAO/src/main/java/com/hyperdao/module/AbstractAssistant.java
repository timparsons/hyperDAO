/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.module;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tim
 *
 */
public abstract class AbstractAssistant {

	private Map<Class<?>, Method> assistMethods;
	
	public AbstractAssistant() {
		assistMethods = new HashMap<Class<?>, Method>();
		registerTypes();
	}

	/**
	 * Register the assisted type to the method used to retrieve the resulting value, and store the types in the map
	 */
	public abstract void registerTypes();

	/**
	 * Return the {@link Map} of class to assist to the method that does the work
	 * @return the map of method that assists a class
	 */
	public Map<Class<?>, Method> getAssistMethods() {
		return assistMethods;
	}

}
