package com.baidu.autocoder.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.baidu.autocoder.database.FieldType;

/**
 * 字段类型测试用例.
 * 
 * @author GuoLin
 *
 */
public class FieldTypeTests {

	@Test
	public void smoke() {
		assertEquals(FieldType.BOOLEAN, FieldType.getFieldTypeByDbType("tinyint"));
		
		assertEquals(FieldType.LONG, FieldType.getFieldTypeByDbType("number"));
		assertEquals(FieldType.LONG, FieldType.getFieldTypeByDbType("bigint"));
		
		assertEquals(FieldType.INTEGER, FieldType.getFieldTypeByDbType("int"));
		assertEquals(FieldType.INTEGER, FieldType.getFieldTypeByDbType("integer"));
		
		assertEquals(FieldType.BIGDECIMAL, FieldType.getFieldTypeByDbType("float"));
		assertEquals(FieldType.BIGDECIMAL, FieldType.getFieldTypeByDbType("double"));
		assertEquals(FieldType.BIGDECIMAL, FieldType.getFieldTypeByDbType("real"));
		
		assertEquals(FieldType.STRING, FieldType.getFieldTypeByDbType("nvarchar"));
		assertEquals(FieldType.STRING, FieldType.getFieldTypeByDbType("text"));
		assertEquals(FieldType.STRING, FieldType.getFieldTypeByDbType("mediumtext"));
		assertEquals(FieldType.STRING, FieldType.getFieldTypeByDbType("longtext"));
		assertEquals(FieldType.STRING, FieldType.getFieldTypeByDbType("mediumblob"));
		assertEquals(FieldType.STRING, FieldType.getFieldTypeByDbType("varchar"));
		
		assertEquals(FieldType.DATE, FieldType.getFieldTypeByDbType("date"));
		assertEquals(FieldType.DATETIME, FieldType.getFieldTypeByDbType("timestamp"));
		assertEquals(FieldType.DATETIME, FieldType.getFieldTypeByDbType("datetime"));
	}
}
