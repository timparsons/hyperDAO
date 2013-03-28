/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.module;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.hyperdao.annotation.Assists;

/**
 * @author Tim
 *
 */
public class ReadAssistant extends AbstractAssistant{

	/* (non-Javadoc)
	 * @see com.hyperdao.module.Assistant#registerTypes()
	 */
	@Override
	public void registerTypes() {
		for(Method method : this.getClass().getMethods()) {
			Annotation assist = method.getAnnotation(Assists.class);
			
			if(assist != null) {
				Class<?> sourceType = null;
				try {
					sourceType = (Class<?>) assist.annotationType().getMethod("value").invoke(assist);
				} catch (Exception e) {
					
				} 
				
				if(sourceType != null) {
					getAssistMethods().put(sourceType, method);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.hyperdao.module.Assistant#getValue(java.lang.Object)
	 */
	public Object getValue(Object source) {
		Method assistMethod = getAssistMethods().get(source.getClass());
		Object ret = null;
		if(assistMethod != null) {
			try {
				ret = assistMethod.invoke(this, source);
			} catch (Exception e) {
				
			} 
		}
		
		return ret;
	}

}
