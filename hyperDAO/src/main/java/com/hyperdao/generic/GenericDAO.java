package com.hyperdao.generic;

import java.io.Serializable;
import java.sql.SQLException;

import com.hyperdao.exception.HyperDAOException;


/**
 * Generic definition of a Database Access Object (DAO)
 * <br/><br/>
 * See: 
 * <a href="http://www.ibm.com/developerworks/java/library/j-genericdao/index.html">
 * 		http://www.ibm.com/developerworks/java/library/j-genericdao/index.html
 * </a>
 * 
 * @author Tim
 *
 */
public interface GenericDAO <T, ID extends Serializable> {

	/** 
     * Persist the entity object into database 
     * 
     * @param entity of type {@literal T}
     * @return an identifier to the new instance persisted into the database
     * @throws HyperDAOException for any {@link SQLException} caught
     */
	<S extends T> S create(T entity) throws HyperDAOException;
    
    /**
     * Persist all given entities into the database
     * @param entities
     * @return a list of objects saved to the database
     * @throws HyperDAOException for any {@link SQLException} caught
     */
    Iterable<T> create(Iterable<? extends T> entities) throws HyperDAOException;
    
    /** 
     * Persist the entity object into database 
     * 
     * @param entity of type {@literal T}
     * @return an identifier to the new instance persisted into the database
     * @throws HyperDAOException for any {@link SQLException} caught
     */
	<S extends T> S update(T entity) throws HyperDAOException;
    
    /**
     * Persist all given entities into the database
     * @param entities
     * @return a list of objects saved to the database
     * @throws HyperDAOException for any {@link SQLException} caught
     */
    Iterable<T> update(Iterable<? extends T> entities) throws HyperDAOException;

    /**
     * Retrieve an object that was previously persisted to the database using
     * the indicated id as primary key
     * 
     * @param id - an identifier to the record in the database
     * @return an instance of {@literal T}
     * @throws HyperDAOException for any {@link SQLException} caught
     */
    T read(ID id) throws HyperDAOException;
    
    /**
     * Retrieve an object that was previously persisted to the database using
     * a model 
     * @param model - search object of type {@literal T}
     * @return a list of {@literal T} found based on the model
     * @throws HyperDAOException for any {@link SQLException} caught
     */
    Iterable<T> read(T model) throws HyperDAOException;
    
    /**
     * Retrieve all records of type {@literal T} that have been previously persisted into the database
     * @return a list of all {@literal T}
     * @throws HyperDAOException for any {@link SQLException} caught
     */
    Iterable<T> readAll() throws HyperDAOException;

    /** 
     * Remove an object from persistent storage in the database 
     * 
     * @param entity of type {@literal T}
     * @throws HyperDAOException for any {@link SQLException} caught
     */
    void delete(T entity) throws HyperDAOException;
    
    /**
     * Remove a list of objects from persistent storage in the database
     * @param entities - list of objects to remove
     * @throws HyperDAOException for any {@link SQLException} caught
     */
    void delete(Iterable<? extends T> entities) throws HyperDAOException;
    
    /**
     * Remove all records of type {@literal T} that have been previously persisted into the database
     * @throws HyperDAOException for any {@link SQLException} caught
     */
    void deleteAll() throws HyperDAOException;
}
