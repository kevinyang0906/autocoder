package ${classPackage};

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ${className} ${type}.
 * TODO Change ME.
 * 
 * @author ${author}
 *
 */
@Entity
@Table(name = "${table.tableName}")
public class ${table.entityName} {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "${table.primaryKey.fieldName}")
	private ${table.primaryKey.type} id;
	
	<#list table.fields as field>
	/** DB Column: ${field.fieldName}. TODO Change ME. */
	<#if field.type.name() == "DATETIME">
	@Temporal(TemporalType.TIMESTAMP)
	<#elseif field.type.name() == "DATE">
	@Temporal(TemporalType.DATE)
	</#if>
	private ${field.type} ${field.propertyName};
	
	</#list>
	public ${table.primaryKey.type} getId() {
		return id;
	}

	public void setId(${table.primaryKey.type} id) {
		this.id = id;
	}

	<#list table.fields as field>
	public ${field.type} ${field.getterName}() {
		return ${field.propertyName};
	}

	public void ${field.setterName}(${field.type} ${field.propertyName}) {
		this.${field.propertyName} = ${field.propertyName};
	}

	</#list>
}
