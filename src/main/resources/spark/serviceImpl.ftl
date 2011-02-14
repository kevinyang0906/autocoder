<#assign daoPropertyName = uncapitalize(components.dao.className)/>
package ${classPackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.spark.dao.Pagination;
import ${components.dao.classPackage}.${components.dao.className};
import ${components.model.classPackage}.${components.model.className};
import ${components.service.classPackage}.${components.service.className};

/**
 * ${components.model.className} Service.
 * TODO Change ME.
 * 
 * @author ${author}
 *
 */
@Service
public class ${className} implements ${components.service.className} {
	
	private ${components.dao.className} ${daoPropertyName};
	
	// TODO Add methods here.

	@Autowired
	public void set${capitalize(daoPropertyName)}(${components.dao.className} ${daoPropertyName}) {
		this.${daoPropertyName} = ${daoPropertyName};
	}

}
