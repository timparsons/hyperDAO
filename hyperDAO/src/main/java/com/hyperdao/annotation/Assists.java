/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A pointer to the type that is in need of assistance
 * 
 * @author Tim
 *
 */
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Assists {
    
    /**
     * The type in need of assistance
     */
    Class<?> value();
}
