/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hyperdao.util.constant.SQLConstants;

/**
 * @author Tim
 *
 */
public class DBUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(DBUtil.class);
	
	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static void closeStatement(PreparedStatement ps) {
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
		} catch (SQLException e) {
			logger.error("Unable to close preparedStatement", e);
		}
	}
	
	/**
	 * Add the column to the conditional clause of the SQL statement using an AND if not the first statement
	 * @param columnName - name of column to include in conditional clause
	 * @param isFirst - indicator of if the condition is the first or does it require a preceding comma
	 * @param sql - StringBuilder SQL statement
	 * @return FALSE to indicate that the next condition to be added is not the first one
	 */
	public static boolean addAndCondition(String columnName, boolean isFirst, StringBuilder sql) {
		if(!isFirst) {
			sql.append(SQLConstants.SQL_AND);
		}
		sql.append(columnName + SQLConstants.SQL_EQUAL_PARAMETER);
		return false;
	}
	
	/**
	 * Add the column to the conditional clause of the SQL statement using an OR if not the first statement
	 * @param columnName - name of column to include in conditional clause
	 * @param isFirst - indicator of if the condition is the first or does it require a preceding comma
	 * @param sql - StringBuilder SQL statement
	 * @return FALSE to indicate that the next condition to be added is not the first one
	 */
	public static boolean addOrCondition(String columnName, boolean isFirst, StringBuilder sql) {
		if(!isFirst) {
			sql.append(SQLConstants.SQL_OR);
		}
		sql.append(columnName + SQLConstants.SQL_EQUAL_PARAMETER);
		return false;
	}

}
