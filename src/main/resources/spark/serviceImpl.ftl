/*
 * Copyright (c) 2013, Snowball Finance and/or its affiliates. All rights reserved.
 * SNOWBALLFINANCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
<#assign daoPropertyName = uncapitalize(components.dao.className)/>
<#assign modelPropertyName = uncapitalize(components.model.className)/>
package ${classPackage};

import ${components.dao.classPackage}.${components.dao.className};
import ${components.model.classPackage}.${components.model.className};
import ${components.service.classPackage}.${components.service.className};

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * ${components.model.className} Service.
 * ${description}功能ServiceImpl
 * 
 * @author hjyang
 * @since ${date}
 * @version ${version} 
 *
 */
@Service
public class ${className} extends AbstractCacheService implements ${components.service.className} {
	
	@Resource
	private ${components.dao.className} ${daoPropertyName};
	
	@Override
	public void add(${components.model.className} ${modelPropertyName}) throws DataAccessException{
		${daoPropertyName}.insert(${modelPropertyName});
	}

	@Override
	public Long query(<#list table.primaryKey as field>${field.type} ${field.propertyName}<#if field_has_next>,</#if></#list>) throws DataAccessException{
		return ${daoPropertyName}.select(<#list table.primaryKey as field>${field.propertyName}<#if field_has_next>,</#if></#list>);
	}

	@Override
	public void remove(<#list table.primaryKey as field>${field.type} ${field.propertyName}<#if field_has_next>,</#if></#list>) throws DataAccessException{
		${daoPropertyName}.delete(<#list table.primaryKey as field>${field.propertyName}<#if field_has_next>,</#if></#list>);
	}

	@Override
	public void update(${components.model.className} ${modelPropertyName}) throws DataAccessException{
		${daoPropertyName}.update(${modelPropertyName});	
	}
}
