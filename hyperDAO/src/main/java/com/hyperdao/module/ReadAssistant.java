/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.module;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.hyperdao.annotation.Assists;
import com.hyperdao.generic.impl.GenericDAOImpl;

/**
 * When reading the {@link ResultSet} of a SQL query, a ReadAssistant will
 * invoke a user defined method to convert a primitive value into what is needed
 * in the {@link Object}. 
 * <br/><br/>
 * For example, if an {@link Enum} is a field of an {@link Object}, then the
 * ReadAssistant will tell {@link GenericDAOImpl} how to convert an
 * {@link Integer} into an {@link Enum} usable by the {@link Object}
 * 
 * @see {@link WriteAssistant} for writing a value to the database
 * 
 * @author Tim
 * 
 */
public class ReadAssistant extends AbstractAssistant{

    private Map<Class<?>, Class<?>> complexToPrimitive;
    
    public ReadAssistant() {
        super();
    }
    
    @Override
    public void doSetup() {
        super.doSetup();
        this.complexToPrimitive = new HashMap<Class<?>, Class<?>>();
    }
    
    /* (non-Javadoc)
     * @see com.hyperdao.module.Assistant#registerTypes()
     */
    @Override
    public void registerTypes() {
        for(Method method : this.getClass().getMethods()) {
            Annotation assist = method.getAnnotation(Assists.class);
            
            if(assist != null) {
                Class<?> resultType = null;
                Class<?>[] params = null;
                try {
                    resultType = (Class<?>) assist.annotationType().getMethod("value").invoke(assist);
                    params = method.getParameterTypes();
                } catch (Exception e) {
                    
                } 
                
                if(resultType != null && params != null && params.length > 0) {
                    getAssistMethods().put(resultType, method);
                    getComplexToPrimitive().put(resultType, params[0]);
                }
            }
        }
    }
    
    /**
     * 
     * @param source
     * @param resultType
     * @return
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

    public Map<Class<?>, Class<?>> getComplexToPrimitive() {
        return complexToPrimitive;
    }

}
