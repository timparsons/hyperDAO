package com.parsons.dao.generic.model;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * A representation of a SQL foreign key
 * @author Tim
 *
 */
public class ForeignKey {

	private Table referenceTable;
	private Column referenceColumn;
	private Column keyColumn;
	private ReferenceType referenceType;
	
	public enum ReferenceType {
		MANY_TO_ONE(ManyToOne.class),
		ONE_TO_MANY(OneToMany.class),
		MANY_TO_MANY(ManyToOne.class),
		ONE_TO_ONE(OneToOne.class);
		
		private Class<?> clazz;
		private ReferenceType(Class<?> clazz) {
			this.clazz = clazz;
		}
		
		public Class<?> getName() {
			return clazz;
		}
		
		public static ReferenceType getReferenceType(Class<?> clazz) {
			for(ReferenceType type : ReferenceType.values()) {
				if(type.getName().equals(clazz)) {
					return type;
				}
			}
			
			return null;
		}
		
		public static String stringVal(ReferenceType type) {
			if(type.equals(MANY_TO_MANY)) {
				return "MANY_TO_MANY";
			} else if(type.equals(MANY_TO_ONE)) {
				return "MANY_TO_ONE";
			} else if(type.equals(ONE_TO_MANY)) {
				return "ONE_TO_MANY";
			} else if(type.equals(ONE_TO_ONE)) {
				return "ONE_TO_ONE";
			} else {
				return "";
			}
		}
	}
	
	/**
	 * The foreign table that is reference by the {@link ForeignKey#keyColumn}
	 * @return the referenceTable
	 */
	public Table getReferenceTable() {
		return referenceTable;
	}
	/**
	 * The foreign table that is reference by the {@link ForeignKey#keyColumn}
	 * @param referenceTable the referenceTable to set
	 */
	public void setReferenceTable(Table referenceTable) {
		this.referenceTable = referenceTable;
	}
	/**
	 * The foreign column in the {@link ForeignKey#referenceTable} that is reference by the {@link ForeignKey#keyColumn}
	 * @return the referenceColumn
	 */
	public Column getReferenceColumn() {
		return referenceColumn;
	}
	/**
	 * The foreign column in the {@link ForeignKey#referenceTable} that is reference by the {@link ForeignKey#keyColumn}
	 * @param referenceColumn the referenceColumn to set
	 */
	public void setReferenceColumn(Column referenceColumn) {
		this.referenceColumn = referenceColumn;
	}
	/**
	 * the column in the current table that references {@link ForeignKey#referenceTable}->{@link ForeignKey#referenceColumn}
	 * @return the keyColumn
	 */
	public Column getKeyColumn() {
		return keyColumn;
	}
	/**
	 * the column in the current table that references {@link ForeignKey#referenceTable}->{@link ForeignKey#referenceColumn}
	 * @param keyColumn the keyColumn to set
	 */
	public void setKeyColumn(Column keyColumn) {
		this.keyColumn = keyColumn;
	}
	/**
	 * the type of reference from the current table to the reference table
	 * @return the referenceType
	 */
	public ReferenceType getReferenceType() {
		return referenceType;
	}
	/**
	 * the type of reference from the current table to the reference table
	 * @param referenceType to set
	 */
	public void setReferenceType(ReferenceType referenceType) {
		this.referenceType = referenceType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((keyColumn == null) ? 0 : keyColumn.hashCode());
		result = prime * result
				+ ((referenceColumn == null) ? 0 : referenceColumn.hashCode());
		result = prime * result
				+ ((referenceTable == null) ? 0 : referenceTable.hashCode());
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
		ForeignKey other = (ForeignKey) obj;
		if (keyColumn == null) {
			if (other.keyColumn != null)
				return false;
		} else if (!keyColumn.equals(other.keyColumn))
			return false;
		if (referenceColumn == null) {
			if (other.referenceColumn != null)
				return false;
		} else if (!referenceColumn.equals(other.referenceColumn))
			return false;
		if (referenceTable == null) {
			if (other.referenceTable != null)
				return false;
		} else if (!referenceTable.equals(other.referenceTable))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ForeignKey [referenceTable=" + referenceTable
				+ ", referenceColumn=" + referenceColumn + ", keyColumn="
				+ keyColumn + ", referenceType=" + ReferenceType.stringVal(referenceType) + "]";
	}

}
