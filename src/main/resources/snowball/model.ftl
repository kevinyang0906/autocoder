package ${classPackage};

import java.io.Serializable;

/**
 * ${className} ${type}.
 * ${description}功能Entity
 *
 * @author hjyang
 * @since ${date}
 * @version ${version} 
 */
public class ${table.entityName} implements Serializable {
	
	<#list table.primaryKey as field>
	/** DB Column: ${field.fieldName}. TODO Change ME. */
	private ${field.type} ${field.propertyName};
	    
	public ${field.type} ${field.getterName}() {
		return ${field.propertyName};
	}

	public void ${field.setterName}(${field.type} ${field.propertyName}) {
		this.${field.propertyName} = ${field.propertyName};
	}

	</#list>
	
	<#list table.fields as field>
	/** DB Column: ${field.fieldName}. TODO Change ME. */
	private ${field.type} ${field.propertyName};
	    
	public ${field.type} ${field.getterName}() {
		return ${field.propertyName};
	}

	public void ${field.setterName}(${field.type} ${field.propertyName}) {
		this.${field.propertyName} = ${field.propertyName};
	}

	</#list>
}
