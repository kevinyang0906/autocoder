package com.baidu.autocoder.database;

import com.baidu.autocoder.util.StringUtils;

/**
 * 数据库字段类.
 * 
 * @author GuoLin
 *
 */
public class Field {

	/** 数据库字段名. */
	private final String fieldName;
	
	/** 属性名称. */
	private final String propertyName;
	
	/** 字段类型. */
	private final FieldType type;
	
	/** 字段长度. */
	private final int length;

	/** 是否允许为空. */
	private final boolean nullable;
	
	/** 是否是主键. */
	private final boolean primary;

	/**
	 * 构造器.
	 * @param fieldName 名称
	 * @param dbType 数据库字段类型
	 * @param length 长度
	 * @param primary 是否是主键
	 * @param nullable 是否为空
	 */
	public Field(String fieldName, String dbType, int length, boolean primary, boolean nullable) {
		this(fieldName, StringUtils.underscoreToCamel(fieldName), FieldType.getFieldTypeByDbType(dbType), length, primary, nullable);
	}

	/**
	 * 构造器.
	 * @param fieldName 名称
	 * @param propertyName 实体类属性名称
	 * @param dbType 数据库字段类型
	 * @param length 长度
	 * @param primary 是否是主键
	 * @param nullable 是否为空
	 */
	public Field(String fieldName, String propertyName, String dbType, int length, boolean primary, boolean nullable) {
		this(fieldName, propertyName, FieldType.getFieldTypeByDbType(dbType), length, primary, nullable);
	}

	/**
	 * 构造器.
	 * @param fieldName 数据库字段名称
	 * @param propertyName 实体类属性名称
	 * @param type FieldType类型
	 * @param length 长度
	 * @param primary 是否是主键
	 * @param nullable 是否为空
	 */
	public Field(String fieldName, String propertyName, FieldType type, int length, boolean primary, boolean nullable) {
		super();
		this.fieldName = fieldName;
		this.propertyName = propertyName;
		this.type = type;
		this.length = length;
		
		if (primary && nullable) {
			throw new IllegalArgumentException("Primary key must not be nullable.");
		}
		
		this.primary = primary;
		this.nullable = nullable;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public FieldType getType() {
		return type;
	}

	public int getLength() {
		return length;
	}

	public boolean isPrimary() {
		return primary;
	}

	public boolean isNullable() {
		return nullable;
	}
	
	public String getGetterName() {
		return "get" + StringUtils.capitalize(propertyName);
	}
	
	public String getSetterName() {
		return "set" + StringUtils.capitalize(propertyName);
	}
	
}
