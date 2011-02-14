package com.baidu.autocoder.database;

import java.util.List;

import com.baidu.autocoder.util.StringUtils;

/**
 * 数据库表类.
 * 
 * @author GuoLin
 *
 */
public class Table {

	/** 表名 */
	private final String tableName;
	
	/** 实体名 */
	private final String entityName;
	
	/** 主键 */
	private final Field primaryKey;
	
	/** 字段列表 */
	private final List<Field> fields;

	/**
	 * 构造器.
	 * 实体名将会根据表名自动生成
	 * @param tableName 表名
	 * @param primaryKey 主键
	 * @param fields 字段列表
	 */
	public Table(String tableName, Field primaryKey, List<Field> fields) {
		this(tableName, StringUtils.underscoreToCamel(tableName, true), primaryKey, fields);
	}

	/**
	 * 构造器.
	 * @param tableName 表名
	 * @param entityName 实体名称
	 * @param primaryKey 主键
	 * @param fields 字段列表
	 */
	public Table(String tableName, String entityName, Field primaryKey, List<Field> fields) {
		this.tableName = tableName;
		this.entityName = entityName;
		this.primaryKey = primaryKey;
		this.fields = fields;
	}

	public String getTableName() {
		return tableName;
	}

	public String getEntityName() {
		return entityName;
	}

	public Field getPrimaryKey() {
		return primaryKey;
	}

	public List<Field> getFields() {
		return fields;
	}

}
