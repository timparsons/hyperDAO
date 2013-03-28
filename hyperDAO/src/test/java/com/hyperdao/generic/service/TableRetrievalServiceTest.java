/**
 * 
 */
package com.hyperdao.generic.service;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.hyperdao.generic.model.Column;
import com.hyperdao.generic.model.ForeignKey;
import com.hyperdao.generic.model.Table;
import com.hyperdao.generic.service.TableRetrievalService;
import com.hyperdao.model.test.TableOne;

/**
 * @author Tim
 *
 */
public class TableRetrievalServiceTest {

	@Test
	public void testRetrieveTable() {
		List<String> tableOneColumnNames = Arrays.asList("table_one_id", "name", "create_date");
		Table table = TableRetrievalService.registerTable(TableOne.class);
		
		Assert.assertEquals("Table is null", true, table != null);
		Assert.assertEquals("Table name is not TableOne", "Table_One", table.getName());
		Assert.assertEquals("There are no columns", true, table.getColumns() != null);
		Assert.assertEquals("There are no columns", true, !table.getColumns().isEmpty());
		for(Column column : table.getColumns()) {
			Assert.assertEquals("Column not found", true, tableOneColumnNames.contains(column.getColumnName()));
		}
		
		Assert.assertEquals("There is no foreign key", true, table.getForeignKeys() != null);
		Assert.assertEquals("There is no foreign key", true, !table.getForeignKeys().isEmpty());
		Assert.assertEquals("There are not 2 foreign keys", 2, table.getForeignKeys().size());
		
		int read = 0;
		for(ForeignKey fk : table.getForeignKeys()) {
			if(fk.getReferenceTable().getName().equals("Table_Two") || fk.getReferenceTable().getName().equals("Table_Three")) {
				read++;
				if(fk.getReferenceTable().getName().equals("Table_Two")) {
					Assert.assertEquals("Foreign key is not Table_Two", "Table_Two", fk.getReferenceTable().getName());
					Assert.assertEquals("table_one_id", fk.getKeyColumn().getColumnName());
					Assert.assertEquals("table_one_id", fk.getReferenceColumn().getColumnName());
				} else if(fk.getReferenceTable().getName().equals("Table_Three")) {
					Assert.assertEquals("Foreign key is not Table_Three", "Table_Three", fk.getReferenceTable().getName());
					Assert.assertEquals("table_one_id", fk.getKeyColumn().getColumnName());
					Assert.assertEquals("table_one_id", fk.getReferenceColumn().getColumnName());
				}
			}
		}
		
		Assert.assertEquals("Did not read 2 fks", 2, read);
		
		System.out.println("TableOne sql: ");
		System.out.println(table.getCreateSQL());
		System.out.println(table.getUpdateSQL());
		System.out.println(table.getDeleteSQL());
		System.out.println(table.getEagerReadSQL());
		System.out.println("========================");
		Table fk1 = table.getForeignKeys().get(0).getReferenceTable();
		System.out.println(fk1.getName()+" sql: ");
		System.out.println(fk1.getCreateSQL());
		System.out.println(fk1.getUpdateSQL());
		System.out.println(fk1.getDeleteSQL());
		System.out.println(fk1.getEagerReadSQL());
		System.out.println("========================");
		Table fk2 = table.getForeignKeys().get(1).getReferenceTable();
		System.out.println(fk2.getName()+" sql: ");
		System.out.println(fk2.getCreateSQL());
		System.out.println(fk2.getUpdateSQL());
		System.out.println(fk2.getDeleteSQL());
		System.out.println(fk2.getEagerReadSQL());
		System.out.println("========================");
	}

}
