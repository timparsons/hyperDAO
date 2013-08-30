/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.executor;

import java.sql.ResultSet;

/**
 * @author Tim
 *
 */
public interface ResultSetExecutor<T> {

	T createObject(ResultSet rs);
}
