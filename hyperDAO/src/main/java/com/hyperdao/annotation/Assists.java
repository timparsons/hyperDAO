/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Tim
 *
 */
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Assists {
	public abstract Class<?> sourceType();
}
