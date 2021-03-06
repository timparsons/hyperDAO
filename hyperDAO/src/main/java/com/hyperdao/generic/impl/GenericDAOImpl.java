package com.hyperdao.generic.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hyperdao.assistant.ReadAssistant;
import com.hyperdao.assistant.WriteAssistant;
import com.hyperdao.exception.HyperDAOException;
import com.hyperdao.executor.PreparedStatement;
import com.hyperdao.executor.PreparedStatementExecutor;
import com.hyperdao.generic.GenericDAO;
import com.hyperdao.generic.model.Column;
import com.hyperdao.generic.model.ForeignKey;
import com.hyperdao.generic.model.Table;
import com.hyperdao.generic.service.TableRetrievalService;
import com.hyperdao.util.DBUtil;
import com.hyperdao.util.DateUtil;
import com.hyperdao.util.StringUtil;
import com.hyperdao.util.constant.SQLConstants;

/**
 * @author Tim
 *
 */
public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {
	private static final Logger logger = LoggerFactory.getLogger(GenericDAOImpl.class);

	private Table table;
	private DataSource dataSource;
	private ReadAssistant readAssistant;
	private WriteAssistant writeAssistant;
	
	public GenericDAOImpl(DataSource dataSource, Class<T> clazz) {
		this.dataSource = dataSource;
		registerTable(clazz);
		this.readAssistant = new ReadAssistant();
		this.writeAssistant = new WriteAssistant();
	}
	
	public GenericDAOImpl(DataSource dataSource, Class<T> clazz, ReadAssistant readAssistant, WriteAssistant writeAssistant) {
	    this(dataSource, clazz);
	    
	    this.readAssistant = readAssistant;
	    this.writeAssistant = writeAssistant;
	}
	
    /**
     * Determine if the given entity of type {@literal T} is valid to be persisted into the database
     * @param entity
     * @return <code>true</code> if the entity is valid and ready to be persisted, <code>false</code> otherwise
     */
    public abstract boolean isValid(T entity);

	/* (non-Javadoc)
	 * @see com.parsons.reciply.dao.base.GenericDAO#create(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
//	@Override
	public <S extends T> S create(T entity) throws HyperDAOException {
		logger.debug("Entry into create with entity: "+entity);
		if(!isValid(entity)) {
			throw new HyperDAOException("Cannot create entity that is not valid");
		}
		
		if(table == null) {
			registerTable((Class<T>) entity.getClass());
		}
		
		PreparedStatement<T, ID> ps = new PreparedStatement<T, ID>(dataSource);
		entity = ps.handlePreparedStatement(table.getCreateSQL(), entity, new PreparedStatementExecutor<T, ID>() {

			@Override
			public T executeStatement(java.sql.PreparedStatement ps, T entity) throws HyperDAOException {
				try {
					populateCreate(entity, ps);
					ps.executeUpdate();
				} catch (Exception e) {
					logger.debug("Unable to insert entity: "+entity, e);
					throw new HyperDAOException("Unable to insert entity: "+entity, e);
				}
				return entity;
			}
		});
		
		logger.debug("Exit from create(entity) with entity: "+entity);
		return (S) entity;
	}
	/* (non-Javadoc)
	 * @see com.parsons.reciply.dao.base.GenericDAO#create(java.lang.Iterable)
	 */
	@SuppressWarnings("unchecked")
//	@Override
	public Iterable<T> create(Iterable<? extends T> entities) throws HyperDAOException {
		logger.debug("Entry into update(List) with entities: "+entities);
		
		if(table == null && entities != null) {
			Iterator<T> iterator = (Iterator<T>) entities.iterator();
			if(iterator.hasNext()) {
				T entity = iterator.next();
				registerTable((Class<T>) entity.getClass());
			}
		}
		
		PreparedStatement<T, ID> ps = new PreparedStatement<T, ID>(dataSource);
		entities = ps.handlePreparedStatementByList(table.getCreateSQL(), entities, new PreparedStatementExecutor<T, ID>() {

			@Override
			public Iterable<T> executeStatement(java.sql.PreparedStatement ps, Iterable<? extends T> entities) throws HyperDAOException {
				try {
					for(T entity : entities) {
						if(!isValid(entity)) {
							logger.warn("Entity: "+entity+" is not valid, so not persisting");
							continue;
						}
						populateCreate(entity, ps);
						ps.addBatch();
					}
					ps.executeBatch();
				} catch (Exception e) {
					logger.debug("Unable to insert entity: "+entities, e);
					throw new HyperDAOException("Unable to insert entity: "+entities, e);
				}
				return (Iterable<T>) entities;
			}
		});
		
		return (Iterable<T>) entities;
	}

	/* (non-Javadoc)
	 * @see com.parsons.reciply.dao.base.GenericDAO#save(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
//	@Override
	public <S extends T> S update(T entity) throws HyperDAOException {
		logger.debug("Entry into update with entity: "+entity);
		if(!isValid(entity)) {
			throw new HyperDAOException("Cannot update entity that is not valid");
		}
		
		if(table == null) {
			registerTable((Class<T>) entity.getClass());
		}
		
//		StringBuilder sql = new StringBuilder(table.getUpdateSQL());
		
		PreparedStatement<T, ID> ps = new PreparedStatement<T, ID>(dataSource);
		ps.handlePreparedStatement(table.getUpdateSQL(), entity, new PreparedStatementExecutor<T, ID>() {

			@Override
			public T executeStatement(java.sql.PreparedStatement ps, T entity) throws HyperDAOException {
				try {
					populateSave(entity, ps, true);
					int updatedCount = ps.executeUpdate();
					logger.debug("Updated "+updatedCount+" records for entity: "+entity);
				} catch (Exception e) {
					logger.debug("Unable to update entity: "+entity, e);
					throw new HyperDAOException("Unable to update entity: "+entity, e);
				}
				return entity;
			}
		});
		
		logger.debug("Exit from update(entity) with entity: "+entity);
		return  (S) entity;
	}

	/* (non-Javadoc)
	 * @see com.parsons.reciply.dao.base.GenericDAO#save(java.lang.Iterable)
	 */
	@SuppressWarnings("unchecked")
//	@Override
	public Iterable<T> update(Iterable<? extends T> entities) throws HyperDAOException {
		logger.debug("Entry into update with entities: "+entities);
		if(table == null && entities != null) {
			Iterator<T> iterator = (Iterator<T>) entities.iterator();
			if(iterator.hasNext()) {
				T entity = iterator.next();
				registerTable((Class<T>) entity.getClass());
			}
		}
		
		PreparedStatement<T, ID> ps = new PreparedStatement<T, ID>(dataSource);
		ps.handlePreparedStatementByList(table.getUpdateSQL(), entities, new PreparedStatementExecutor<T, ID>() {

			@Override
			public Iterable<T> executeStatement(java.sql.PreparedStatement ps, Iterable<? extends T> entities) throws HyperDAOException {
				try {
					for(T entity : entities) {
						logger.debug("Adding entity:"+entity+" to batch");
						if(!isValid(entity)) {
							logger.warn("Entity is not valid, so not updating");
							continue;
						}
						populateSave(entity, ps, true);
						ps.addBatch();
					}
					ps.executeBatch();
				} catch (Exception e) {
					logger.debug("Unable to update entity: "+entities, e);
					throw new HyperDAOException("Unable to update entity: "+entities, e);
				}
				return (Iterable<T>) entities;
			}
		});
		
		logger.debug("Exit from update(entities) with: "+entities);
		return (Iterable<T>) entities;
	}

	/* (non-Javadoc)
	 * @see com.parsons.reciply.dao.base.GenericDAO#read(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
//	@Override
	public T read(ID id) throws HyperDAOException {
		logger.debug("Entry into read with id: "+id);
		if(table == null ) {
			throw new HyperDAOException("Table relation is not set up");
		}
		
//		StringBuilder sql = new StringBuilder(table.getEagerReadSQL());
		StringBuilder sql = new StringBuilder(table.getLazyReadSQL());
		
		sql.append(SQLConstants.SQL_WHERE);
		sql.append(table.getPrimaryKey().getColumnName());
		sql.append(SQLConstants.SQL_EQUAL_PARAMETER);
		
		PreparedStatement<T, ID> ps = new PreparedStatement<T, ID>(dataSource);
		T entity = ps.handlePreparedStatementById(id, sql.toString(), new PreparedStatementExecutor<T, ID>() {
			
			@Override
			public T executeStatementById(final ID id, java.sql.PreparedStatement ps) throws HyperDAOException {
				T entity = null;
				try {
					setParam(id, ps, 0, id.getClass());
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						logger.debug("Populating entity");
						entity = (T) populateEntity(rs, table.getName(), null, table, null);
					}
				} catch (Exception e) {
					logger.debug("Unable to read entity by id: "+id, e);
					throw new HyperDAOException("Unable to read entity by id: "+id, e);
				}
				return entity;
			}
		});
		
		logger.debug("Exit from read(id) with entity: "+entity);
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.parsons.reciply.dao.base.GenericDAO#read(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
//	@Override
	public Iterable<T> read(T model) throws HyperDAOException {
		logger.debug("Entry into read with model: "+model);
		if(table == null && model == null) {
			throw new HyperDAOException("Table relation is not set up");
		} else if(table == null && model != null) {
			registerTable((Class<T>) model.getClass());
		}
		
		StringBuilder sql = new StringBuilder(table.getLazyReadSQL());
		
		if(model != null) {
			logger.debug("Adding search criteria based on model: "+model);
			try {
				appendSearchCriteria(model, sql);
			} catch (Exception e) {
				logger.error("Unable to populate search criteria for the SQL", e);
				throw new HyperDAOException("Unable to populate search criteria for the SQL", e);
			}
		}
		logger.debug("SQL to execute: "+sql.toString());
		
		PreparedStatement<T, ID> ps = new PreparedStatement<T, ID>(dataSource);
		Iterable<T> retList = ps.handlePreparedStatementReturnIterable(sql.toString(), model, new PreparedStatementExecutor<T, ID>() {

			public Iterable<T> executeStatement(T model, java.sql.PreparedStatement ps) throws HyperDAOException {
				List<T> retList = null;
				try {
					if(model != null) {
						logger.debug("Populating search criteria based on model: "+model);
						populateSearchCriteria(model, ps, 0);
					}
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						logger.debug("Populating entity");
						if(retList == null) {
							retList = new ArrayList<T>();
						}
						T entity = (T) populateEntity(rs, table.getName(), null, table, null);
						if(entity != null) {
							retList.add(entity);
						}
					}
					
					logger.debug("Found "+(retList == null ? 0 : retList.size())+" entities to return");
				} catch (Exception e) {
					logger.debug("Unable to retrieve entity by model: " + model, e);
					throw new HyperDAOException("Unable to retrieve entity by model: " + model, e);
				}
				return retList;
			}
		});
		
		logger.debug("Exit from read(model) with list: "+retList);
		return retList;
	}

	/* (non-Javadoc)
	 * @see com.parsons.reciply.dao.base.GenericDAO#readAll()
	 */
//	@Override
	public Iterable<T> readAll() throws HyperDAOException {
		logger.debug("Entry into readAll())");
		T model = null;
		return read(model);
	}

	/* (non-Javadoc)
	 * @see com.parsons.reciply.dao.base.GenericDAO#delete(java.lang.Object)
	 */
//	@Override
	public void delete(T entity) throws HyperDAOException {
		logger.debug("Entry into delete with entity: "+entity);
		StringBuilder sql = new StringBuilder(table.getDeleteSQL());
		try {
			appendSearchCriteria(entity, sql);
		} catch (Exception e) {
			logger.error("Unable to populate search criteria", e);
			throw new HyperDAOException("Unable to populate search criteria", e);
		} 
		logger.debug("SQL to execute: "+sql.toString());
		
		PreparedStatement<T, ID> ps = new PreparedStatement<T, ID>(dataSource);
		ps.handlePreparedStatement(sql.toString(), entity, new PreparedStatementExecutor<T, ID>() {

			public T executeStatement(java.sql.PreparedStatement ps, T entity) throws HyperDAOException {
				try {
					populateSearchCriteria(entity, ps, 0);
					int numDeleted = ps.executeUpdate();
					
					logger.debug("Deleted: "+numDeleted+" entities based on model: "+entity);
				} catch (Exception e) {
					logger.debug("Unable to delete entity: " + entity, e);
					throw new HyperDAOException("Unable to delete entity: " + entity, e);
				}
				return null;
			}
		});
		
		logger.debug("Exit from delete(entity)");
	}

	/* (non-Javadoc)
	 * @see com.parsons.reciply.dao.base.GenericDAO#delete(java.lang.Iterable)
	 */
//	@Override
	@SuppressWarnings("unchecked")
    public void delete(Iterable<? extends T> entities) throws HyperDAOException {
		logger.debug("Entry into delete with entities: "+entities.toString());
		StringBuilder sql = new StringBuilder(table.getDeleteSQL());
		
		sql.append(SQLConstants.SQL_WHERE);
		sql.append(table.getPrimaryKey().getColumnName());
		sql.append(SQLConstants.SQL_EQUAL_PARAMETER);
		
		PreparedStatement<T, ID> ps = new PreparedStatement<T, ID>(dataSource);
		ps.handlePreparedStatementByList(sql.toString(), entities, new PreparedStatementExecutor<T, ID>() {

			public Iterable<T> executeStatement(java.sql.PreparedStatement ps, Iterable<? extends T> entities) throws HyperDAOException {
				try {
					for(T entity : entities) {
						logger.debug("Adding entity: "+entity+" to batch");
						ID id = (ID) table.getPrimaryKey().getDataRetrievalMethod().invoke(entity);
						setParam(id, ps, 0, table.getPrimaryKey().getColumnType());
						ps.addBatch();
					}
					
					ps.executeBatch();
				} catch (Exception e) {
					logger.debug("Unable to delete entity: " + entities, e);
					throw new HyperDAOException("Unable to delete entity: " + entities, e);
				}
				return null;
			}
		});
		
		logger.debug("Exit from delete(entities)");
	}

	/* (non-Javadoc)
	 * @see com.parsons.reciply.dao.base.GenericDAO#deleteAll()
	 */
//	@Override
	public void deleteAll() throws HyperDAOException {
		logger.debug("Entry into deleteAll");
		
		PreparedStatement<T, ID> ps = new PreparedStatement<T, ID>(dataSource);
		ps.handlePreparedStatement(table.getDeleteSQL(), null, new PreparedStatementExecutor<T, ID>() {

			public T executeStatement(java.sql.PreparedStatement ps, T entity) throws HyperDAOException {
				try {
					int numDeleted = ps.executeUpdate();
					
					logger.debug("Deleted "+numDeleted+" entities from table: "+table.getName());
				} catch (Exception e) {
					logger.debug("Unable to delete all", e);
					throw new HyperDAOException("Unable to delete all", e);
				}
				return null;
			}
		});		
		logger.debug("Exit from DeleteAll");
	}

	/**
	 * Return the {@link Table} that was registered with HyperDAO
	 * @return the registered {@link Table}
	 */
	public Table getTable() {
		return table;
	}
	
	protected Table getTable(Class<?> tableClass) {
		return TableRetrievalService.registerTable(tableClass);
	}
	
	/**
	 * For a {@link ResultSet}, populate the related Object based on the table
	 * @param rs - results of the 
	 * @param tableAlias - name of the table
	 * @param columnAliasPrefix - prefix for each column in select clause
	 * @param table - {@link Table} to base {@link Object} population off of
	 * @return a new {@link Object} based on the incoming table
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	protected Object populateEntity(ResultSet rs, String tableAlias, String columnAliasPrefix, Table table, List<String> usedTableAliases) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException, NoSuchMethodException {
		logger.debug("Entry into populateEntity with tableAlias: "+tableAlias+" columnAliasPrefix: "+columnAliasPrefix+" and table: "+table);
		
		if(usedTableAliases != null && usedTableAliases.contains(tableAlias)) {
			return null;
		} else {
			if(usedTableAliases == null) {
				usedTableAliases = new ArrayList<String>();
			}
			usedTableAliases.add(tableAlias);
		}
		Object entity = null;
		entity = table.getTableClass().newInstance();
		
		for(Column column : table.getColumns()) {
			logger.debug("Retrieving value for: "+table.getName()+"."+column.getColumnName());
			String referenceName = column.getColumnName();
			if(columnAliasPrefix != null) {
				referenceName = columnAliasPrefix+column.getColumnName();
			} 
			logger.debug("Column alias is: "+referenceName);
			Object value = getReturnValue(rs, column.getColumnType(), referenceName);
			setObjectValue(entity, value, column);
		}
		
//		if(table.getForeignKeys() != null && table.getForeignKeys().size() > 0) {
//			logger.debug("Table has foreign key references");
//			for(ForeignKey fk : table.getForeignKeys()) {
//				logger.debug("Populating FK table: "+fk.getReferenceTable().getName()+" reference by "+table.getName()+"."+fk.getKeyColumn().getColumnName());
//				String fkTableAlias = TableRetrievalService.getFkTableAlias(table, fk);
//				String fkColumnAliasPrefix = TableRetrievalService.getFkColumnPrefix(table, fk);
//				Object fkEntity = populateEntity(rs, fkTableAlias, fkColumnAliasPrefix, fk.getReferenceTable(), usedTableAliases);
//				setFKObjectValue(entity, fkEntity, fk.getKeyColumn());
//			}
//		}
		
		logger.debug("Exit from populateEntity with entity: "+entity);
		return entity;
	}

	/**
	 * Register the table class with HyperDAO
	 * @param tableClass - model class to register
	 */
	protected void registerTable(Class<T> tableClass) {
		this.table = TableRetrievalService.registerTable(tableClass);
	}
	
	/**
	 * 
	 * @return
	 */
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * 
	 * @return
	 */
	protected ReadAssistant getReadAssistant() {
	    return readAssistant;
	}
	
	/**
	 * 
	 * @return
	 */
	protected WriteAssistant getWriteAssistant() {
	    return writeAssistant;
	}

	/**
	 * @param entity
	 * @param ps 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws SQLException 
	 */
	protected int populateCreate(T entity, java.sql.PreparedStatement ps) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SQLException {
		logger.debug("Entry into populateCreate with entity: "+entity);
		int count = 0;
		Column primaryKeyCol = null;
		
		for(Column column : table.getColumns()) {
			logger.debug("Setting value of column: "+column.getColumnName());
			if(column.isPrimary() && primaryKeyCol == null) {
				logger.debug("Column is a primary key, will add at end of populateCreate");
				primaryKeyCol = column;
				continue;
			} else {
				Object o = column.getDataRetrievalMethod().invoke(entity);
				String objectVal = o == null ? "" : o.toString();
				logger.debug("Setting value of: "+column.getColumnName()+" to: "+objectVal+" for count: "+count);
				count = setParam(o, ps, count, column.getColumnType());
			}
		}

		if(primaryKeyCol != null && !primaryKeyCol.isAutoGen()) {
			Object o = primaryKeyCol.getDataRetrievalMethod().invoke(entity);
			logger.debug("Setting primary key value in search criteria to: "+o.toString());
			count = setParam(o, ps, count, primaryKeyCol.getColumnType());
		}
		
		logger.debug("Exit from populateCreate with count: "+count);
		return count;
	}

	/**
	 * @param entity - object to persist
	 * @param ps - {@link java.sql.PreparedStatement} being built
	 * @param methods - ordered list of methods to ensure that the correct columns get updated
	 * @param idOnly - indicator as to if the save SQL is updating based on only the entity's ID, or by a list of criteria
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	protected int populateSave(T entity, java.sql.PreparedStatement ps, boolean idOnly) throws SQLException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		logger.debug("Entry into populateSave with entity: "+entity.toString()+" idOnly: "+idOnly);
		int count = 0;
		Column primaryKeyCol = null;
		// fields to update
		for(Column column : table.getColumns()) {
			logger.debug("Setting value of column: "+column.getColumnName());
			if(column.isPrimary()) {
				logger.debug("Column is a primary key, skipping in populateSave");
				primaryKeyCol = column;
				continue;
			} else /*if(!idOnly)*/ {
				Object o = column.getDataRetrievalMethod().invoke(entity);
                String objectVal = o == null ? "" : o.toString();
				logger.debug("Setting value of: "+column.getColumnName()+" to: "+objectVal+" for count: "+count);
				count = setParam(o, ps, count, column.getColumnType());
			}
		}

		if(!idOnly) {
			logger.debug("Searching by more than just ID");
			populateSearchCriteria(entity, ps, count);
		} else {
			Object o = primaryKeyCol.getDataRetrievalMethod().invoke(entity);
			logger.debug("Setting primary key value in search criteria to: "+o.toString());
			count = setParam(o, ps, count, primaryKeyCol.getColumnType());
		}
		
		logger.debug("Exit from populateSave with count: "+count);
		return count;
	}

	/**
	 * @param o
	 * @param ps
	 * @throws SQLException 
	 */
	protected int setParam(Object o, java.sql.PreparedStatement ps, int count, Type columnType) throws SQLException {
		logger.debug("Entry into setParam with count: "+count);
		if(o == null) {
			logger.debug("Object is null");
			count = setNull(ps, count, columnType);
		} else if(getWriteAssistant().getAssistMethods().containsKey(o.getClass())) {
           logger.debug("Object is specified in the WriteAssistant, invoking the assistant to get the value");
           Object assistedVal = getWriteAssistant().getValue(o);
           count = setPrimitiveParam(assistedVal, ps, count);
        } else {
            count = setPrimitiveParam(o, ps, count);
        }
		
		logger.debug("Exit from setParam with count: "+count);
		return count;
	}

	/**
     * @param count
     * @param columnType
     * @return
	 * @throws SQLException 
     */
    private int setNull(java.sql.PreparedStatement ps, int count, Type columnType) throws SQLException {
        if(columnType == String.class) {
            logger.debug("Column is of type String");
            ps.setNull(++count, Types.VARCHAR);
        } else if(columnType == Integer.class || columnType == int.class) {
            logger.debug("Column is of type Integer");
            ps.setNull(++count, Types.INTEGER);
        } else if(columnType == Long.class || columnType == long.class) {
            logger.debug("Column is of type Long");
            ps.setNull(++count, Types.BIGINT);
        } else if(columnType == Double.class || columnType == double.class) {
            logger.debug("Column is of type Double");
            ps.setNull(++count, Types.DOUBLE);
        } else if(columnType == Float.class || columnType == float.class) {
            logger.debug("Column is of type Float");
            ps.setNull(++count, Types.FLOAT);
        } else if(columnType == BigDecimal.class) {
            logger.debug("Column is of type BigDecimal");
            ps.setNull(++count, Types.DECIMAL);
        } else if(columnType == Timestamp.class) {
            logger.debug("Column is of type Timestamp");
            ps.setNull(++count, Types.TIMESTAMP);
        } else if(columnType == java.sql.Date.class || columnType == java.util.Date.class) {
            logger.debug("Column is of type java.util.Date or java.sql.Date");
            ps.setNull(++count, Types.DATE);
        } else if(columnType == Boolean.class || columnType == boolean.class) {
            logger.debug("Column is of type Boolean");
            ps.setNull(++count, Types.BOOLEAN);
        } else {
            logger.debug("Column is of type OTHER");
            ps.setNull(++count, Types.OTHER);
        }
        
        return count;
    }

    /**
     * @param o
     * @param ps
     * @param count
	 * @throws SQLException 
     */
    private int setPrimitiveParam(Object o, java.sql.PreparedStatement ps, int count) throws SQLException {
        if(o instanceof String) {
            logger.debug("Object is of type String");
            ps.setString(++count, (String)o);
        } else if(o instanceof Integer) {
            logger.debug("Object is of type Integer");
            ps.setInt(++count, (Integer)o);
        } else if(o instanceof Long) {
            logger.debug("Object is of type Long");
            ps.setLong(++count,  (Long)o);
        } else if(o instanceof Double) {
            logger.debug("Object is of type Double");
            ps.setDouble(++count,  (Double)o);
        } else if(o instanceof Float) {
            logger.debug("Object is of type Float");
            ps.setFloat(++count,  (Float)o);
        } else if(o instanceof BigDecimal) {
            logger.debug("Object is of type BigDecimal");
            ps.setBigDecimal(++count,  (BigDecimal)o);
        } else if(o instanceof Timestamp) {
            logger.debug("Object is of type Timestamp");
            ps.setTimestamp(++count,  (Timestamp)o);
        } else if(o instanceof java.sql.Date) {
            logger.debug("Object is of type java.sql.Date");
            ps.setDate(++count,  (java.sql.Date)o);
        } else if(o instanceof java.util.Date) {
            logger.debug("Object is of type java.util.Date");
            ps.setDate(++count,  DateUtil.convertToSQLDate((java.util.Date)o));
        } else if(o instanceof Boolean) {
            logger.debug("Object is of type Boolean");
            ps.setBoolean(++count, (Boolean)o);
        } else if(o instanceof char[]) {
        	logger.debug("Object is of type char[]");
        	ps.setString(++count, StringUtil.printString((char[])o));
        } else if(o instanceof byte[]) {
        	logger.debug("Object is of type byte[]");
        	ps.setBytes(++count, (byte[])o);
        }
        
        return count;
    }

    /**
	 * @param rs
	 * @param column
	 * @param referenceName 
	 * @return
	 * @throws SQLException 
	 */
	private Object getReturnValue(ResultSet rs, Class<?> columnType, String referenceName) throws SQLException {
		logger.debug("Entry into getReturnValue with columnType: "+columnType+", referenceName: "+referenceName);
		
		Object value = null;
		
		if(getReadAssistant().getAssistMethods().containsKey(columnType)) {
	        logger.debug("Object is specified in the ReadAssistant, invoking the assistant to get the value");
	        Class<?> primitiveType = getReadAssistant().getComplexToPrimitive().get(columnType);
	        Object primitiveValue = getPrimitiveReturnValue(rs, primitiveType, referenceName);
	        value = getReadAssistant().getValue(primitiveValue, columnType);
		} else {
		    value = getPrimitiveReturnValue(rs, columnType, referenceName);
		}
		
		logger.debug("Exit from getReturnValue with value: "+value);
		return value;
	}

	/**
     * @param rs
     * @param columnType
     * @param referenceName
	 * @throws SQLException 
     */
    private Object getPrimitiveReturnValue(ResultSet rs, Type columnType, String referenceName) throws SQLException {
        logger.debug("Entry into getPrimitiveReturnValue with columnType: "+columnType+", referenceName: "+referenceName);
        
        Object value = null;
        
        if(columnType == String.class) {
            logger.debug("Object is of type String");
            value = rs.getString(referenceName);
        } else if(columnType == Integer.class || columnType == int.class) {
            logger.debug("Object is of type Integer");
            value = rs.getInt(referenceName);
        } else if(columnType == Long.class || columnType == long.class) {
            logger.debug("Object is of type Long");
            value = rs.getLong(referenceName);
        } else if(columnType == Double.class || columnType == double.class) {
            logger.debug("Object is of type Double");
            value = rs.getDouble(referenceName);
        } else if(columnType == Float.class || columnType == float.class) {
            logger.debug("Object is of type Float");
            value = rs.getFloat(referenceName);
        } else if(columnType == BigDecimal.class) {
            logger.debug("Object is of type BigDecimal");
            value = rs.getBigDecimal(referenceName);
        } else if(columnType == Timestamp.class) {
            logger.debug("Object is of type Timestamp");
            value = rs.getTimestamp(referenceName);
        } else if(columnType == java.sql.Date.class) {
            logger.debug("Object is of type java.sql.Date");
            value = rs.getDate(referenceName);
        } else if(columnType == java.util.Date.class) {
            logger.debug("Object is of type java.util.Date");
            java.sql.Date sqlDate = rs.getDate(referenceName);
            if(sqlDate != null) {
                value = new java.util.Date(sqlDate.getTime());
            }
        } else if(columnType == Boolean.class || columnType == boolean.class) {
            logger.debug("Object is of type Boolean");
            value = rs.getBoolean(referenceName);
        }
        
        logger.debug("Exit from getPrimitiveReturnValue with value: "+value);
        return value;
    }

    /**
	 * @param entity
	 * @param sql
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	protected StringBuilder appendSearchCriteria(T entity, StringBuilder sql) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		logger.debug("Entry into appendSearchCriteria with entity: "+entity.toString());
		if(sql == null) {
			throw new IllegalArgumentException("StringBuilder 'sql' cannot be null");
		}
		
		sql.append(SQLConstants.SQL_WHERE);
		boolean isFirst = true;
		for(Column column : table.getColumns()) {
			logger.debug("Checking if column: "+column.getColumnName()+" needs to be included in search criteria");
			Object o = column.getDataRetrievalMethod().invoke(entity);
			if(o != null) {
				if(o instanceof Integer && column.isPrimary() && ((Integer)o).intValue() == 0) {
					continue;
				}
				//TODO handle checking foreign keys that are int

				logger.debug("Including column: "+column.getColumnName()+" in search criteria");
				isFirst = DBUtil.addAndCondition(table.getName()+"."+column.getColumnName(), isFirst, sql);
			}
		}
		
		logger.debug("Exit from appendSearchCriteria");
		return sql;
	}

	/**
	 * Populate a {@link java.sql.PreparedStatement} based on the model entity
	 * @param entity - model entity
	 * @param ps - {@link java.sql.PreparedStatement} to set values to
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws SQLException 
	 */
	protected int populateSearchCriteria(T entity, java.sql.PreparedStatement ps, int count) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SQLException {
		logger.debug("Entry into populateSearchCriteria for table: "+entity.toString()+" with count: "+count);
		for(Column column : table.getColumns()) {
			logger.debug("Checking if column: "+column.getColumnName()+" needs to be populated");
			Object o = column.getDataRetrievalMethod().invoke(entity);
			if(o != null) {
				if(o instanceof Integer && column.isPrimary() && ((Integer)o).intValue() == 0) {
					continue;
				}
				//TODO handle checking foreign keys that are int

				logger.debug("Populating search param count: "+count+" with value: "+column.getColumnName());
				count = setParam(o, ps, count, column.getColumnType());
			}
		}
		
		logger.debug("Exit from populateSearchCriteria with count: "+count);
		return count;
	}

	/**
	 * Set the retrieved value to the related variable in the mapped class from a READ query
	 * 
	 * @param entity - Class to populate
	 * @param value - Value to set
	 * @param columnName - Column retrieved from
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	private void setObjectValue(Object entity, Object value, Column column) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		setValue(entity, value, column.getDataSetMethod(), column.getDataRetrievalMethod().getReturnType());
	}

	/**
	 * Set the retrieved value to the related variable in the mapped class from a READ query
	 * 
	 * @param entity - Class to populate
	 * @param value - Value to set
	 * @param m - Set method within the entity that will populate the variable
	 * @param paramType - Object type 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	private void setValue(Object entity, Object value, Method m, Class<?> paramType) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		logger.debug("Setting value of "+entity.getClass()+"."+m.getName()+" to "+(value == null ? "" : value.toString()));
		entity.getClass().getMethod(m.getName(), paramType).invoke(entity, value);
	}

	/**
	 * Set the retrieved FK value from a READ query
	 * @param entity
	 * @param fkEntity
	 * @param columnName
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	private void setFKObjectValue(Object entity, Object fkEntityValue, Column column) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		setValue(entity, fkEntityValue, column.getDataSetMethod(), fkEntityValue.getClass());
	}
}
