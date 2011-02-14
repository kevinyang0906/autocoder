<#assign serivcePropertyName = uncapitalize(components.service.className)/>
package ${classPackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baidu.spark.dao.Pagination;
import ${components.model.classPackage}.${components.model.className};
import ${components.service.classPackage}.${components.service.className};

/**
 * ${components.model.className} Controller.
 * TODO Change ME.
 * 
 * @author ${author}
 *
 */
@Controller
@RequestMapping("/${pluralize(lower(entityName))}")
public class ${className} {
	
	private ${components.service.className} ${serivcePropertyName};

	@RequestMapping("/list")
	public void list(@RequestParam(value = "page", required = false) Integer page, ModelMap modelMap) {
		// TODO Add logic
	}

	@Autowired
	public void set${capitalize(serivcePropertyName)}(${components.service.className} ${serivcePropertyName}) {
		this.${serivcePropertyName} = ${serivcePropertyName};
	}
	
}
