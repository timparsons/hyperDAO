/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.executor;

import java.sql.PreparedStatement;
import java.util.List;

import com.hyperdao.exception.HyperDAOException;

/**
 * @author Tim
 *
 */
public class PreparedStatementExecutor<T, ID> {

	public T executeStatement(PreparedStatement ps, T entity) throws HyperDAOException { return null; }
	public Iterable<T> executeStatement(T entity, PreparedStatement ps) throws HyperDAOException { return null; }
	public Iterable<T> executeStatement(PreparedStatement ps, Iterable<? extends T> entities) throws HyperDAOException { return null; }
	public T executeStatementById(ID id, PreparedStatement ps) throws HyperDAOException { return null; }
	
}
