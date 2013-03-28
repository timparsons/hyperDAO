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
public class WriteAssistant extends AbstractAssistant{

	/* (non-Javadoc)
	 * @see com.hyperdao.module.Assistant#registerTypes()
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see com.hyperdao.module.Assistant#getValue(java.lang.Object)
	 */
	public Object getValue(Object source, Class<?> resultType) {
		Method assistMethod = getAssistMethods().get(resultType);
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
