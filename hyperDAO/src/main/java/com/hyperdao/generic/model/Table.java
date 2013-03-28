package com.hyperdao.generic.model;

import java.util.List;

/**
 * A representation of a SQL table
 * 
 * @author Tim
 *
 */
public class Table {

	private String name;
	private Column primaryKey;
	private List<Column> columns;
	private List<ForeignKey> foreignKeys;
	private String createSQL;
	private String eagerReadSQL;
	private String lazyReadSQL;
	private String updateSQL;
	private String deleteSQL;
	private Class<?> tableClass;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Column getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(Column primaryKey) {
		this.primaryKey = primaryKey;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	public List<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}
	public void setForeignKeys(List<ForeignKey> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}
	public String getCreateSQL() {
		return createSQL;
	}
	public void setCreateSQL(String createSQL) {
		this.createSQL = createSQL;
	}
	
	/**
	 * Returns a SQL statement that will fetch all of the 
	 * {@link Table} information, as well as the information from foreign keys
	 * 
	 * @return a {@link String} SQL statement to retrieve all information related to the {@link Table}
	 */
	public String getEagerReadSQL() {
		return eagerReadSQL;
	}
	
	/**
	 * Set the SQL statement that will eagerly retrieve data from the {@link Table}
	 * @param eagerReadSQL - eager SQL to set
	 */
	public void setEagerReadSQL(String eagerReadSQL) {
		this.eagerReadSQL = eagerReadSQL;
	}
	
	/**
	 * Returns a SQL statement that will fetch only the {@link Table} 
	 * information but not any information from foreign keys
	 * 
	 * @return a {@link String} SQL statement to retrieve information directly on the {@link Table}
	 */
	public String getLazyReadSQL() {
		return lazyReadSQL;
	}
	
	/**
	 * Set the SQL statement that will lazily retrieve data from the {@link Table}
	 * @param lazyReadSQL - lazy SQL to set
	 */
	public void setLazyReadSQL(String lazyReadSQL) {
		this.lazyReadSQL = lazyReadSQL;
	}
	public String getUpdateSQL() {
		return updateSQL;
	}
	public void setUpdateSQL(String updateSQL) {
		this.updateSQL = updateSQL;
	}
	public String getDeleteSQL() {
		return deleteSQL;
	}
	public void setDeleteSQL(String deleteSQL) {
		this.deleteSQL = deleteSQL;
	}
	public Class<?> getTableClass() {
		return tableClass;
	}
	public void setTableClass(Class<?> tableClass) {
		this.tableClass = tableClass;
	}
	
	@Override
	public String toString() {
		return "Table [name=" + name + ", primaryKey=" + primaryKey
				+ ", columns=" + columns + ", foreignKeys=" + foreignKeys
				+ ", createSQL=" + createSQL + ", readSQL=" + eagerReadSQL
				+ ", updateSQL=" + updateSQL + ", deleteSQL=" + deleteSQL
				+ ", tableClass=" + tableClass + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columns == null) ? 0 : columns.hashCode());
		result = prime * result
				+ ((createSQL == null) ? 0 : createSQL.hashCode());
		result = prime * result
				+ ((deleteSQL == null) ? 0 : deleteSQL.hashCode());
		result = prime * result
				+ ((foreignKeys == null) ? 0 : foreignKeys.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((primaryKey == null) ? 0 : primaryKey.hashCode());
		result = prime * result + ((eagerReadSQL == null) ? 0 : eagerReadSQL.hashCode());
		result = prime * result
				+ ((updateSQL == null) ? 0 : updateSQL.hashCode());
		result = prime * result + ((tableClass == null) ? 0 : tableClass.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		if (columns == null) {
			if (other.columns != null)
				return false;
		} else if (!columns.equals(other.columns))
			return false;
		if (createSQL == null) {
			if (other.createSQL != null)
				return false;
		} else if (!createSQL.equals(other.createSQL))
			return false;
		if (deleteSQL == null) {
			if (other.deleteSQL != null)
				return false;
		} else if (!deleteSQL.equals(other.deleteSQL))
			return false;
		if (foreignKeys == null) {
			if (other.foreignKeys != null)
				return false;
		} else if (!foreignKeys.equals(other.foreignKeys))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (primaryKey == null) {
			if (other.primaryKey != null)
				return false;
		} else if (!primaryKey.equals(other.primaryKey))
			return false;
		if (eagerReadSQL == null) {
			if (other.eagerReadSQL != null)
				return false;
		} else if (!eagerReadSQL.equals(other.eagerReadSQL))
			return false;
		if (updateSQL == null) {
			if (other.updateSQL != null)
				return false;
		} else if (!updateSQL.equals(other.updateSQL))
			return false;
		if(tableClass == null) {
			if(other.tableClass != null)
				return false;
		} else if (!tableClass.equals(other.tableClass))
			return false;
		return true;
	}

}
