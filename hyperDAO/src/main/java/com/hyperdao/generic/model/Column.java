package com.hyperdao.generic.model;

import java.lang.reflect.Method;

/**
 * A representation of a SQL column
 * 
 * @author Tim
 *
 */
public class Column {
	
	private Class<?> columnType;
	private String columnName;
	private boolean isPrimary;
	private boolean isAutoGen;
	private Method dataRetrievalMethod;
	private Method dataSetMethod;
	
	public Class<?> getColumnType() {
		return columnType;
	}
	public void setColumnType(Class<?> columnType) {
		this.columnType = columnType;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public boolean isAutoGen() {
		return isAutoGen;
	}
	public void setAutoGen(boolean isAutoGen) {
		this.isAutoGen = isAutoGen;
	}
	public Method getDataRetrievalMethod() {
		return dataRetrievalMethod;
	}
	public void setDataRetrievalMethod(Method dataRetrievalMethod) {
		this.dataRetrievalMethod = dataRetrievalMethod;
	}
	public Method getDataSetMethod() {
		return dataSetMethod;
	}
	public void setDataSetMethod(Method dataSetMethod) {
		this.dataSetMethod = dataSetMethod;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + (isPrimary ? 1231 : 1237);
		result = prime * result + (isAutoGen ? 1731 : 1737);
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
		Column other = (Column) obj;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (columnType == null) {
			if (other.columnType != null)
				return false;
		} else if (!columnType.equals(other.columnType))
			return false;
		if (isPrimary != other.isPrimary)
			return false;
		if(isAutoGen != other.isAutoGen)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Column [columnType=" + columnType + ", columnName="
				+ columnName + ", isPrimary=" + isPrimary + ", isAutoGen="
				+ isAutoGen + ", dataRetrievalMethod=" + dataRetrievalMethod
				+ ", dataSetMethod=" + dataSetMethod + "]";
	}
	
}
