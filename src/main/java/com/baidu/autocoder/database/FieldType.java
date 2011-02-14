package com.baidu.autocoder.database;

import java.util.Map;

import com.baidu.autocoder.Configuration;

/**
 * 字段类型枚举.
 * 
 * @author GuoLin
 *
 */
public enum FieldType {
	
	/** 字符串类型. */
	STRING("String"),
	
	/** 长整型. */
	LONG("Long"),
	
	/** 整型. */
	INTEGER("Integer"),
	
	/** 短整型. */
	SHORT("Short"),
	
	/** 浮点型. */
	BIGDECIMAL("java.math.BigDecimal"),
	
	/** 布尔型. */
	BOOLEAN("Boolean"),
	
	/** 日期型. */
	DATE("java.util.Date"),
	
	/** 时间型. */
	DATETIME("java.util.Date");
	
	/** 类名. */
	private String className;
	
	/**
	 * 构造器.
	 * @param className 类名
	 */
	private FieldType(String className) {
		this.className = className;
	}
	
	@Override
	public String toString() {
		return className;
	}
	
	/**
	 * 根据输入的数据库类型字符串获取枚举类型.
	 * @param dbType 数据库类型字符串表示
	 * @return 枚举类型，如果找不到匹配的类型，则返回null
	 */
	public static FieldType getFieldTypeByDbType(String dbType) {
		Map<String, String> map = Configuration.getConfiguration().getTypeConvertMap();
		String type = map.get(dbType.toLowerCase());
		if (type == null) {
			return null;
		}
		return FieldType.valueOf(type);
	}
	
}
