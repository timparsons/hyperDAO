/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.module;

import java.lang.reflect.Method;

import com.hyperdao.generic.impl.GenericDAOImpl;

/**
 * When creating a SQL query, a WriteAssistant will invoke a user defined method
 * to convert an {@link Object} into what is needed in the SQL query. 
 * <br/><br/>
 * For example, if an {@link Enum} is a field of an {@link Object}, then the
 * WriteAssistant will tell {@link GenericDAOImpl} how to convert the
 * {@link Enum} into a value readable by the SQL engine
 * 
 * @see {@link ReadAssistant} for reading a value from the database
 * 
 * @author Tim
 * 
 */
public class WriteAssistant extends AbstractAssistant{

    /**
     * 
     * @param source
     * @return
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
