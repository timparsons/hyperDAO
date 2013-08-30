/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hyperdao.exception.HyperDAOException;
import com.hyperdao.util.DBUtil;

/**
 * @author Tim
 *
 */
public class PreparedStatement<T, ID> {
	
	private static final Logger logger = LoggerFactory.getLogger(PreparedStatement.class);

	private final DataSource dataSource;
	
	public PreparedStatement(DataSource dataStore) {
		this.dataSource = dataStore;
	}
	
	public T handlePreparedStatement(String sql, T entity, PreparedStatementExecutor<T, ID> executor) throws HyperDAOException {
		T ret = null;
		
		java.sql.PreparedStatement ps = null;
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ret = executor.executeStatement(ps, entity);
		} catch (SQLException e) {
			logger.error("Exception while handling prepared statement with sql: " + sql, e);
			throw new HyperDAOException("Exception while handling prepared statement with sql: " + sql, e);
		} finally {
			DBUtil.closeStatement(ps);
			DBUtil.closeConnection(conn);
		}
		
		return ret;
	}
	
	public Iterable<T> handlePreparedStatementReturnIterable(String sql, T model, PreparedStatementExecutor<T, ID> executor) throws HyperDAOException {
		Iterable<T> ret = null;
		
		java.sql.PreparedStatement ps = null;
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ret = executor.executeStatement(model, ps);
		} catch (SQLException e) {
			logger.error("Exception while handling prepared statement with sql: " + sql, e);
			throw new HyperDAOException("Exception while handling prepared statement with sql: " + sql, e);
		} finally {
			DBUtil.closeStatement(ps);
			DBUtil.closeConnection(conn);
		}
		
		return ret;
	}
	
	public Iterable<T> handlePreparedStatementByList(String sql, Iterable<? extends T> entities, PreparedStatementExecutor<T, ID> executor) throws HyperDAOException {
		Iterable<T> ret = null;
		
		java.sql.PreparedStatement ps = null;
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ret = executor.executeStatement(ps, entities);
		} catch (SQLException e) {
			logger.error("Exception while handling prepared statement with sql: " + sql, e);
			throw new HyperDAOException("Exception while handling prepared statement with sql: " + sql, e);
		} finally {
			DBUtil.closeStatement(ps);
			DBUtil.closeConnection(conn);
		}
		
		return ret;
	}
	
	public T handlePreparedStatementById(ID id, String sql, PreparedStatementExecutor<T, ID> executor) throws HyperDAOException {
		T ret = null;
		
		java.sql.PreparedStatement ps = null;
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ret = executor.executeStatementById(id, ps);
		} catch (SQLException e) {
			logger.error("Exception while handling prepared statement with sql: " + sql, e);
			throw new HyperDAOException("Exception while handling prepared statement with sql: " + sql, e);
		} finally {
			DBUtil.closeStatement(ps);
			DBUtil.closeConnection(conn);
		}
		
		return ret;
	}

}
