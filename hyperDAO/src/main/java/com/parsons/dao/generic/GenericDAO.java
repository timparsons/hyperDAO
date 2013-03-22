package com.parsons.dao.generic;

import java.io.Serializable;

import com.parsons.dao.exception.DAOException;


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
     * @throws DAOException 
     */
	<S extends T> S create(T entity) throws DAOException;
    
    /**
     * Persist all given entities into the database
     * @param entities
     * @return a list of objects saved to the database
     */
    Iterable<T> create(Iterable<? extends T> entities) throws DAOException;
    
    /** 
     * Persist the entity object into database 
     * 
     * @param entity of type {@literal T}
     * @return an identifier to the new instance persisted into the database
     * @throws DAOException 
     */
	<S extends T> S update(T entity) throws DAOException;
    
    /**
     * Persist all given entities into the database
     * @param entities
     * @return a list of objects saved to the database
     */
    Iterable<T> update(Iterable<? extends T> entities) throws DAOException;

    /**
     * Retrieve an object that was previously persisted to the database using
     * the indicated id as primary key
     * 
     * @param id - an identifier to the record in the database
     * @return an instance of {@literal T}
     */
    T read(ID id) throws DAOException;
    
    /**
     * Retrieve an object that was previously persisted to the database using
     * a model 
     * @param model - search object of type {@literal T}
     * @return
     */
    Iterable<T> read(T model) throws DAOException;
    
    /**
     * Retrieve all records of type {@literal T} that have been previously persisted into the database
     * @return a list of all {@literal T}
     */
    Iterable<T> readAll() throws DAOException;

    /** 
     * Remove an object from persistent storage in the database 
     * 
     * @param entity of type {@literal T}
     */
    void delete(T entity) throws DAOException;
    
    /**
     * Remove a list of objects from persistent storage in the database
     * @param entities - list of objects to remove
     * @throws DAOException
     */
    void delete(Iterable<? extends T> entities) throws DAOException;
    
    /**
     * Remove all records of type {@literal T} that have been previously persisted into the database
     * @throws DAOException
     */
    void deleteAll() throws DAOException;
    
    /**
     * Determine if the given entity of type {@literal T} is valid to be persisted into the database
     * @param entity
     * @return TRUE if the entity is valid and ready to be persisted, FALSE otherwise
     */
    boolean isValid(T entity);
}
