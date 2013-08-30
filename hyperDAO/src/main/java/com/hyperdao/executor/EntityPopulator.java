/**
 * Tim Parsons
 * Copyright 2013
 */
package com.hyperdao.executor;

import java.sql.PreparedStatement;

/**
 * @author Tim
 *
 */
public interface EntityPopulator<T> {

	void populatePreparedStatement(T entity, PreparedStatement ps);
}
