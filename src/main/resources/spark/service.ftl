/*
 * Copyright (c) 2013, Snowball Finance and/or its affiliates. All rights reserved.
 * SNOWBALLFINANCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
<#assign modelPropertyName = uncapitalize(components.model.className)/>
package ${classPackage};

/**
 * ${components.model.className} Service.
 *  ${description}功能Service
 * 
 * @author hjyang
 * @since ${date}
 * @version ${version} 
 *
 */
public interface ${className} {

	
	public void add(${components.model.className} ${modelPropertyName}) throws DataAccessException;

	public Long query(<#list table.primaryKey as field>${field.type} ${field.propertyName}<#if field_has_next>,</#if></#list>) throws DataAccessException;

	public void remove(<#list table.primaryKey as field>${field.type} ${field.propertyName}<#if field_has_next>,</#if></#list>) throws DataAccessException;

	public void update(${components.model.className} ${modelPropertyName}) throws DataAccessException;
	
}
