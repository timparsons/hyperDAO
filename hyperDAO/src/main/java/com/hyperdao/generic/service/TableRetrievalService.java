package com.hyperdao.generic.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hyperdao.generic.model.Column;
import com.hyperdao.generic.model.ForeignKey;
import com.hyperdao.generic.model.Table;
import com.hyperdao.generic.model.ForeignKey.ReferenceType;
import com.hyperdao.util.constant.SQLConstants;

/**
 * Given a model class that contains JPA annotations, create a database mapping.
 * 
 * This includes table columns, primary key, and foreign keys.
 * 
 * For any foreign keys of the given model class, also recursively 
 * retrieve all the table information as specified above.
 * 
 * Also derive the create/read/update/delete SQL queries based off of the retrieved information
 *  
 * @author Tim
 *
 */
public class TableRetrievalService {
	private static final Logger logger = LoggerFactory.getLogger(TableRetrievalService.class);
	
	/* Maintain the tables that have been previously registered, so that it doesn't have to be done again */
	private static Map<Class<?>, Table> tables;

	/**
	 * Retrieve all of the database table information based on the JPA annotated model class
	 * 
	 * @param tableClass - model class containing JPA annotations to 
	 * specify columns, primary keys, and foreign keys
	 * 
	 * @return a {@link Table} object containing CRUD SQL queries
	 */
	public static Table registerTable(Class<?> tableClass) {
		return registerTable(tableClass, true);
	}
	
	/**
	 * Retrieve all of the database table information based on the JPA annotated model class
	 * 
	 * @param tableClass - model class containing JPA annotations to 
	 * specify columns, primary keys, and foreign keys
	 * 
	 * @param excludeTableClasses - class to exclude when looking up foreign keys
	 * 
	 * @return a {@link Table} object containing CRUD SQL queries
	 */
	private static Table registerTable(Class<?> tableClass, boolean deriveSQL) {
		if(tables == null) {
			tables = new HashMap<Class<?>, Table>();
		}
		Table table = tables.get(tableClass);
		if(table == null) {
			table = new Table();

			tables.put(tableClass, table);
			
			table.setTableClass(tableClass);
			table.setName(getTableName(tableClass));
			registerColumnsAndKeys(table, tableClass);
			
			if(deriveSQL) {
				deriveSQL();
			}
			
//			tables.put(tableClass, table);
		}
		
		
		return table;
	}
	


	/**
	 * @param entity
	 * @return
	 */
	public static Collection<Method> getEntitySortedMethods(Class<?> tableClass) {
		Collection<Method> methods = new TreeSet<Method>(new Comparator<Method>() {
//			@Override
			public int compare(Method m1, Method m2) {
				return m1.getName().compareTo(m2.getName());
			}
		});
		
		for(Method m : tableClass.getMethods()) {
			methods.add(m);
		}
		
		return methods;
	}

	/**
	 * Given a model class, derive the SQL table name
	 * 
	 * @param tableClass
	 * @return the {@link String} value of the SQL table name for the model class
	 */
	private static String getTableName(Class<?> tableClass) {
		String tableName = null;
		Annotation annotation = tableClass.getAnnotation(javax.persistence.Table.class);
		if(annotation != null) {
			try {
				tableName = (String) annotation.annotationType().getMethod("name").invoke(annotation);
			} catch (Exception e) {
				logger.error("Unable to retrieve table name", e);
			} 
		}
		
		return tableName;
	}
	
	/**
	 * For a model class, look at all methods, and potential 
	 * JPA annotations to derive the SQL table structure
	 * 
	 * Recursively add {@link ForeignKey} table information
	 * 
	 * @param table
	 * @param tableClass
	 * @param excludeTableClasses
	 */
	private static void registerColumnsAndKeys(Table table, Class<?> tableClass) {
		Column primaryKey = null;
		List<Column> columns = new ArrayList<Column>();
		List<ForeignKey> keys = new ArrayList<ForeignKey>();
		Collection<Method> methods = getEntitySortedMethods(tableClass);
		for(Method m : methods) {
			Column retrievedColumn = retrieveColumn(m, tableClass);
			ForeignKey retrievedKey = retrieveKey(m, tableClass);
			if(retrievedColumn != null) {
				columns.add(retrievedColumn);
				if(retrievedColumn.isPrimary() && primaryKey == null) {
					primaryKey = retrievedColumn;
				}
			}
			if(retrievedKey != null) {
				keys.add(retrievedKey);
			}
		}
		
		table.setColumns(columns);
		table.setForeignKeys(keys);
		table.setPrimaryKey(primaryKey);
	}
	
	/**
	 * Given a method of a model class, check if the method contains JPA {@link javax.persistence.Column} annotations.
	 * If so, then retrieve the column name and data type.
	 * 
	 * Also check if the column is the primary key by checking for JPA {@link Id} annotation.
	 * 
	 * @param method
	 * @param tableClass 
	 * @return a {@link Column}
	 */
	private static Column retrieveColumn(Method method, Class<?> tableClass) {
		Column column = null;
		Annotation columnAnnotation = method.getAnnotation(javax.persistence.Column.class);
		if(columnAnnotation != null) {
			try {
				String columnName = (String) columnAnnotation.annotationType().getMethod("name").invoke(columnAnnotation);
				Class<?> methodType = method.getReturnType();
				
				column = populateColumn(method, columnName, methodType, getSetMethod(tableClass, method));
			} catch (Exception e) {
				logger.error("Unable to retrieve column information", e);
			}
		}
		
		return column;
	}

	/**
	 * @param tableClass
	 * @param method
	 * @return
	 */
	private static Method getSetMethod(Class<?> tableClass, Method method) {
		String setMethodName = null;
		if(method.getReturnType() == Boolean.class || method.getReturnType() == boolean.class) {
			setMethodName = method.getName().replaceFirst("is", "");
		} else {
			setMethodName = method.getName().replaceFirst("get", "");
		}
		setMethodName = "set"+setMethodName;
		Method setMethod = null;
		try {
			setMethod = tableClass.getMethod(setMethodName, method.getReturnType());
		} catch (SecurityException e) {
			logger.warn("Unable to retrieve set method", e);
		} catch (NoSuchMethodException e) {
			logger.warn("Unable to retrieve set method", e);
		}
		return setMethod;
	}



	/**
	 * Given a model class and a SQL column name, retrieve the {@link Column} information
	 * @param searchColumnName
	 * @param tableClass 
	 * @return the {@link Column} for the model class and SQL column name
	 */
	private static Column retrieveColumnbyName(String searchColumnName, Class<?> tableClass) {
		Column ret = null;
		for(Method m : tableClass.getMethods()) {
			Annotation columnAnnotation = m.getAnnotation(javax.persistence.Column.class);
			if(columnAnnotation != null) {
				try {
					String columnName = (String) columnAnnotation.annotationType().getMethod("name").invoke(columnAnnotation);
					if(columnName.equals(searchColumnName)) {
						ret = populateColumn(m, columnName, m.getReturnType(), getSetMethod(tableClass, m));
						break;
					}
				} catch (Exception e) {
					logger.error("Unable to retrieve method by column name", e);
				} 
			}
		}
		return ret;
	}
	
	/**
	 * Create a new {@link Column} 
	 * 
	 * @param method
	 * @param columnName
	 * @param methodType
	 * @param setMethod 
	 * @return a new {@link Column} based off of the incoming column information
	 */
	private static Column populateColumn(Method method, String columnName, Class<?> methodType, Method setMethod) {
		Column column = new Column();
		if(columnName != null && methodType != null) {
			column = new Column();
			column.setColumnName(columnName);
			column.setColumnType(methodType);
			column.setDataRetrievalMethod(method);
			column.setDataSetMethod(setMethod);
		}
		
		if(column != null) {
			if(method.isAnnotationPresent(javax.persistence.Id.class)) {
				column.setIsPrimary(true);
			}
			if(method.isAnnotationPresent(javax.persistence.SequenceGenerator.class)) {
				column.setAutoGen(true);
			}
		}
		
		return column;
	}
	
	/**
	 * Given a method of a model class, check if the model contains JPA @ManyToOne and @JoinColumn annotations.
	 * If so, then retrieve the join column name, as well as recursively retrieving the referenced {@link Table}
	 * @param method
	 * @param tableClass
	 * @param excludeTableClasses
	 * @return a {@link ForeignKey} with the key column and referenced table
	 */
	private static ForeignKey retrieveKey(Method method, Class<?> tableClass) {
		ForeignKey key = null;
		Class<?> referenceClass = null;
		
		Annotation joinAnnotation = getJoinAnnotation(method);
		
		Annotation joinColumnAnnotation = method.getAnnotation(javax.persistence.JoinColumn.class);
		
		if(joinAnnotation != null && joinColumnAnnotation != null) {
			try {
				referenceClass = (Class<?>) joinAnnotation.annotationType().getMethod("targetEntity").invoke(joinAnnotation);
				ReferenceType type = ReferenceType.getReferenceType(joinAnnotation.annotationType());
				Table referenceTable = null;
//				if(type.equals(ReferenceType.ONE_TO_MANY)) {
//					if(excludeTableClasses == null) {
//						excludeTableClasses = new LinkedList<Class<?>>();
//					}
//					excludeTableClasses.addFirst(tableClass);
//					referenceTable = registerTable((Class<?>) referenceClass, excludeTableClasses);
//					excludeTableClasses.removeFirst();
//				} else if(excludeTableClasses != null && !excludeTableClasses.contains(referenceClass)){
//					referenceTable = registerTable((Class<?>) referenceClass);
//				}
				
				if(tables.containsKey(referenceClass)) {
					referenceTable = tables.get(referenceClass);
				} else {
					referenceTable = registerTable(referenceClass, false);
				}
				
				String keyColumnName = (String) joinColumnAnnotation.annotationType().getMethod("name").invoke(joinColumnAnnotation);
				
				String referenceColumnName = (String) joinColumnAnnotation.annotationType().getMethod("referencedColumnName").invoke(joinColumnAnnotation);
				if(referenceColumnName == null || referenceColumnName.trim().length() == 0) {
					referenceColumnName = keyColumnName;
				}
				
				Column keyColumn = retrieveColumnbyName(keyColumnName, tableClass);
				Method keyColumnSet = getSetMethod(tableClass, method);
				keyColumn.setDataSetMethod(keyColumnSet);
				
				Column referenceColumn = null;
				if(referenceClass != null) {
					referenceColumn = retrieveColumnbyName(referenceColumnName, referenceClass);
				}
				
				if(referenceTable != null && keyColumn != null && referenceColumn != null) {
					key = new ForeignKey();
					key.setKeyColumn(keyColumn);
					key.setReferenceColumn(referenceColumn);
					key.setReferenceTable(referenceTable);
					key.setReferenceType(type);
				}
			} catch (Exception e) {
				logger.error("Unable to retrieve foreign key information", e);
			} 
		}
		return key;
	}
	
	/**
	 * @param method
	 * @return
	 */
	private static Annotation getJoinAnnotation(Method method) {
		Annotation manyToOneJoinAnnotation = method.getAnnotation(javax.persistence.ManyToOne.class);
		if(manyToOneJoinAnnotation != null) {
			return manyToOneJoinAnnotation;
		}
		
//		Annotation oneToOneJoinAnnotation = method.getAnnotation(javax.persistence.OneToOne.class);
//		if(oneToOneJoinAnnotation != null) {
//			return oneToOneJoinAnnotation;
//		}

		Annotation oneToManyJoinAnnotation = method.getAnnotation(javax.persistence.OneToMany.class);
		if(oneToManyJoinAnnotation != null) {
			return oneToManyJoinAnnotation;
		}	

//		Annotation manyToManyJoinAnnotation = method.getAnnotation(javax.persistence.ManyToMany.class);
//		if(manyToManyJoinAnnotation != null) {
//			return manyToManyJoinAnnotation;
//		}

		return null;
	}



	/**
	 * Given a {@link Table}, derive the CRUD SQL for interacting with the database
	 * @param table
	 */
	private static void deriveSQL() {
		for(Table table : tables.values()) {
			if(table.getCreateSQL() == null || table.getUpdateSQL() == null 
					|| table.getEagerReadSQL() == null || table.getDeleteSQL() == null) {
				deriveCreateUpdateSQL(table);
				deriveReadSQL(table);
				deriveDeleteSQL(table);
			}
		}
	}

	/**
	 * Given a {@link Table}, derive the CREATE and UPDATE SQL statements
	 * @param table
	 */
	private static void deriveCreateUpdateSQL(Table table) {
		StringBuilder createSQL = new StringBuilder(SQLConstants.SQL_INSERT);
		StringBuilder updateSQL = new StringBuilder(SQLConstants.SQL_UPDATE);
		
		createSQL.append(table.getName());
		createSQL.append("(");
		
		updateSQL.append(table.getName());
		updateSQL.append(SQLConstants.SQL_SET);
		
		StringBuilder updateParams = new StringBuilder();
		StringBuilder createParams = new StringBuilder();
		StringBuilder createColumns = new StringBuilder();
		for(Column column : table.getColumns()) {
			if(!column.isPrimary()) {
				createColumns.append(column.getColumnName());
				createColumns.append(SQLConstants.SQL_COMMA);
				
				updateParams.append(column.getColumnName());
				updateParams.append(SQLConstants.SQL_EQUAL_PARAMETER);
				updateParams.append(SQLConstants.SQL_COMMA);
				
				createParams.append("?").append(SQLConstants.SQL_COMMA);
			}
		}

		if(table.getPrimaryKey() != null && !table.getPrimaryKey().isAutoGen()) {
			createColumns.append(table.getPrimaryKey().getColumnName());
		} else {
			createColumns = createColumns.deleteCharAt(createColumns.lastIndexOf(SQLConstants.SQL_COMMA));
			createParams = createParams.deleteCharAt(createParams.lastIndexOf(SQLConstants.SQL_COMMA));
		}
		createSQL.append(createColumns);
		createSQL.append(")");
		createSQL.append(SQLConstants.SQL_VALUES);
		createSQL.append("(");
		
		createSQL.append(createParams);

		updateParams = updateParams.deleteCharAt(updateParams.lastIndexOf(SQLConstants.SQL_COMMA));
		updateSQL.append(updateParams);
		
		if(table.getPrimaryKey() != null) {
			if(!table.getPrimaryKey().isAutoGen()) {
				createSQL.append("?");
			}
			
			updateSQL.append(SQLConstants.SQL_WHERE);
			updateSQL.append(table.getPrimaryKey().getColumnName());
			updateSQL.append(SQLConstants.SQL_EQUAL_PARAMETER);
		}
		
		createSQL.append(")");
		
		table.setCreateSQL(createSQL.toString());
		table.setUpdateSQL(updateSQL.toString());
	}

	/**
	 * Given a {@link Table}, derive the READ SQL statement
	 * The values returned will populate the entire model including ALL foreign keys (recursive)
	 * 
	 * @param table
	 */
	private static void deriveReadSQL(Table table) {
		deriveLazyReadSQL(table);
		deriveEagerReadSQL(table);
	}

	/**
	 * @param table
	 */
	private static void deriveLazyReadSQL(Table table) {
		StringBuilder readSQL = new StringBuilder(SQLConstants.SQL_SELECT);
		
		addSelectItems(table, null, table.getName(), null, readSQL, true, null);
		
		readSQL.append(SQLConstants.SQL_FROM);
		readSQL.append(table.getName());
		
		table.setLazyReadSQL(readSQL.toString());
	}

	/**
	 * @param table
	 */
	private static void deriveEagerReadSQL(Table table) {

		StringBuilder readSQL = new StringBuilder(SQLConstants.SQL_SELECT);
		
		addSelectItems(table, table.getForeignKeys(), table.getName(), null, readSQL, true, null);
		
		readSQL.append(SQLConstants.SQL_FROM);
		readSQL.append(table.getName());
		
		addInnerJoins(table, table.getForeignKeys(), table.getName(), readSQL);
		
		table.setEagerReadSQL(readSQL.toString());
	}

	/**
	 * Derive the items to include in the SELECT of a SQL statement for a given {@link Table}.
	 * 
	 * Recursively move down to each {@link ForeignKey} table
	 * 
	 * @param table
	 * @param name
	 */
	private static void addSelectItems(Table table, List<ForeignKey> foreignKeys, String tableAlias, String columnAliasPrefix, StringBuilder readSQL, boolean firstSelect, List<String> usedTableAliases) {
		
		if(usedTableAliases != null && usedTableAliases.contains(tableAlias)) {
			return;
		} else {
			if(usedTableAliases == null) {
				usedTableAliases = new ArrayList<String>();
			}
			usedTableAliases.add(tableAlias);
		}
		
		String tableAliasWithDot = tableAlias+".";
		
		if(!firstSelect) {
			readSQL.append(SQLConstants.SQL_COMMA);
		}
		
		int count = 0;
		for(Column column : table.getColumns()) {
			count++;
			readSQL.append(tableAliasWithDot);
			readSQL.append(column.getColumnName());
			if(columnAliasPrefix != null) {
				readSQL.append(" AS ");
				readSQL.append(columnAliasPrefix);
				readSQL.append(column.getColumnName());
			}
			if(count < table.getColumns().size()) {
				readSQL.append(SQLConstants.SQL_COMMA);
			}
		}
		
		if(foreignKeys != null && foreignKeys.size() > 0) {
			for(ForeignKey fk : foreignKeys) {
				String fkTableAlias = getFkTableAlias(table, fk);
				String fkColumnAliasPrefix = getFkColumnPrefix(table, fk);
				addSelectItems(fk.getReferenceTable(), determineKeysToInclude(fk, table), fkTableAlias, fkColumnAliasPrefix, readSQL, false, usedTableAliases);
			}
		}
	}

	/**
	 * Derive the INNER JOINs required to retrieve the SELECT criteria
	 * 
	 * Recursively add all INNER JOINs based off of foreign keys
	 * 
	 * @param table
	 * @param name
	 * @param readSQL
	 */
	private static void addInnerJoins(Table table, List<ForeignKey> foreignKeys, String tableAlias, StringBuilder readSQL) {
		if(foreignKeys != null && foreignKeys.size() > 0) {
			for(ForeignKey fk : foreignKeys) {
				String fkTableAlias = getFkTableAlias(table, fk);
				readSQL.append(SQLConstants.SQL_INNER_JOIN);
				readSQL.append(fk.getReferenceTable().getName()).append(" ").append(fkTableAlias);
				readSQL.append(" on ");
				readSQL.append(fkTableAlias).append(".").append(fk.getReferenceColumn().getColumnName());
				readSQL.append(" = ");
				readSQL.append(tableAlias).append(".").append(fk.getKeyColumn().getColumnName());
				
				addInnerJoins(fk.getReferenceTable(), determineKeysToInclude(fk, table), fkTableAlias, readSQL);
				
			}
		}
	}

	/**
	 * @param fk 
	 * @param table 
	 * @return
	 */
	private static List<ForeignKey> determineKeysToInclude(ForeignKey fk, Table table) {
		List<ForeignKey> keysToAdd = new ArrayList<ForeignKey>();
		for(ForeignKey key : fk.getReferenceTable().getForeignKeys()) {
			if(!key.getReferenceTable().getName().equals(table.getName())) {
				keysToAdd.add(key);
			}
		}
		return keysToAdd;
	}

	/**
	 * Given a {@link Table}, derive the DELETE SQL statement
	 * @param table
	 */
	private static void deriveDeleteSQL(Table table) {
		StringBuilder deleteSQL = new StringBuilder(SQLConstants.SQL_DELETE);
		deleteSQL.append(table.getName());
		
		table.setDeleteSQL(deleteSQL.toString());
	}



	/**
	 * @param table 
	 * @param fk 
	 * @return
	 */
	public static String getFkTableAlias(Table table, ForeignKey fk) {
		return table.getName()+"_"+fk.getReferenceTable().getName()+"_"+fk.getKeyColumn().getColumnName();
	}
	
	/**
	 * @param table 
	 * @param fk 
	 * @return
	 */
	public static String getFkColumnPrefix(Table table, ForeignKey fk) {
		return getFkTableAlias(table, fk)+"_";
	}

}
