/*
 * Copyright (c) 2013, Snowball Finance and/or its affiliates. All rights reserved.
 * SNOWBALLFINANCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
<#assign modelPropertyName = uncapitalize(components.model.className)/>
package ${classPackage};

import ${components.model.classPackage}.${components.model.className};

import com.snowballfinance.platform.community.core.DataAccessException;

/**
 * ${components.model.className} DAO interface.
 * ${description}功能DAO
 * 
 * @author hjyang
 * @since ${date}
 * @version ${version} 
 *
 */
public interface ${className}  {

	
	public void insert(${components.model.className} ${modelPropertyName}) throws DataAccessException;
	
	public ${components.model.className} select(<#list table.primaryKey as field>${field.type} ${field.propertyName}<#if field_has_next>,</#if></#list>) throws DataAccessException;
	
	public int update(${components.model.className} ${modelPropertyName}) throws DataAccessException;
	
	public void delete(<#list table.primaryKey as field>${field.type} ${field.propertyName}<#if field_has_next>,</#if></#list>) throws DataAccessException;
	
}
