/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.module;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.hyperdao.annotation.Assists;

/**
 * @author Tim
 *
 */
public abstract class AbstractAssistant {

	private Map<Class<?>, Method> assistMethods;
	
	public AbstractAssistant() {
	    doSetup();
		registerTypes();
	}

	/**
     * 
     */
    protected void doSetup() {
        assistMethods = new HashMap<Class<?>, Method>();
    }

    /**
	 * Register the assisted type to the method used to retrieve the resulting value, and store the types in the map
	 */
	public void registerTypes() {
	    for(Method method : this.getClass().getMethods()) {
            Annotation assist = method.getAnnotation(Assists.class);
            
            if(assist != null) {
                Class<?> resultType = null;
                try {
                    resultType = (Class<?>) assist.annotationType().getMethod("value").invoke(assist);
                } catch (Exception e) {
                    
                } 
                
                if(resultType != null) {
                    getAssistMethods().put(resultType, method);
                }
            }
        }
	}

	/**
	 * Return the {@link Map} of class to assist to the method that does the work
	 * @return the map of method that assists a class
	 */
	public Map<Class<?>, Method> getAssistMethods() {
		return assistMethods;
	}

}
