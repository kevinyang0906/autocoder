/*
 * Copyright (c) 2013, Snowball Finance and/or its affiliates. All rights reserved.
 * SNOWBALLFINANCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
<#assign modelPropertyName = uncapitalize(components.model.className)/>
package ${classPackage};

import java.sql.SQLException;

import ${components.dao.classPackage}.${components.dao.className};
import ${components.model.classPackage}.${components.model.className};
import net.comze.suinoux.orm.client.SimpleClientTemplate;
import com.snowballfinance.platform.community.core.DataAccessException;

/**
 * ${components.model.className} DAO  implementation.
 * ${description}功能DAOImpl
 * 
 * @author hjyang
 * @since ${date}
 * @version ${version} 
 *
 */
public class ${className} extends  SimpleClientTemplate  implements ${components.dao.className} {

	@Override
	public void insert(${components.model.className} ${modelPropertyName}) throws DataAccessException{
		try {
		    this.execute(INSERT_SQL, new Object[] { 
		    	<#list table.primaryKey as field>${modelPropertyName}.${field.getterName}()<#if field_has_next>,</#if></#list><#if (table.fields?size > 0)>,</#if><#list table.fields as field>${modelPropertyName}.${field.getterName}()<#if field_has_next>,</#if></#list>
		    });
		} catch (SQLException e) {
		    throw new DataAccessException(e);
		}
	}
	
	@Override
	public ${components.model.className} select(<#list table.primaryKey as field>${field.type} ${field.propertyName}<#if field_has_next>,</#if></#list>) throws DataAccessException{
		try {
		    return (${components.model.className}) queryBean(SELECT_SQL, new Object[] { 
					<#list table.primaryKey as field>${field.propertyName}<#if field_has_next>,</#if></#list>
			}, ${components.model.className}.class);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	@Override
	public int update(${components.model.className} ${modelPropertyName}) throws DataAccessException{
	 	try {
			return this.execute(UPDATE_SQL, new Object[] {
				<#list table.fields as field>${modelPropertyName}.${field.getterName}()<#if field_has_next>,</#if></#list><#if (table.primaryKey?size > 0)>,</#if><#list table.primaryKey as field>${modelPropertyName}.${field.getterName}()<#if field_has_next>,</#if></#list>
			});
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	@Override
	public void delete(<#list table.primaryKey as field>${field.type} ${field.propertyName}<#if field_has_next>,</#if></#list>) throws DataAccessException{
		try {
		    this.execute(DELETE_SQL, new Object[] { 
		    	<#list table.primaryKey as field>${field.propertyName}<#if field_has_next>,</#if></#list>
		    });
		} catch (SQLException e) {
		    throw new DataAccessException(e);
		}
	}
	
	private static final String INSERT_SQL= "insert into ${table.tableName}(<#list table.primaryKey as field>${field.fieldName}<#if field_has_next>,</#if></#list><#if (table.fields?size > 0)>,</#if><#list table.fields as field>${field.fieldName}<#if field_has_next>,</#if></#list>) values(<#list table.primaryKey as field>?<#if field_has_next>,</#if></#list><#if (table.fields?size > 0)>,</#if><#list table.fields as field>?<#if field_has_next>,</#if></#list>)";
	private static final String SELECT_SQL= "select <#list table.primaryKey as field>${field.fieldName} as ${field.propertyName}<#if field_has_next>,</#if></#list><#if (table.fields?size > 0)>,</#if><#list table.fields as field>${field.fieldName} as ${field.fieldName}<#if field_has_next>,</#if></#list> from ${table.tableName} where <#list table.primaryKey as field>${field.fieldName} = ?<#if field_has_next> and </#if></#list>";
	private static final String DELETE_SQL= "delete from ${table.tableName} where <#list table.primaryKey as field>${field.fieldName} = ?<#if field_has_next> and </#if></#list>";
	private static final String UPDATE_SQL= "update ${table.tableName} set <#list table.fields as field>${field.fieldName} = ?<#if field_has_next>,</#if></#list> where  <#list table.primaryKey as field>${field.fieldName} = ?<#if field_has_next> and </#if></#list>";
	
}
