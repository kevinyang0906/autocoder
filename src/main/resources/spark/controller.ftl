/*
 * Copyright (c) 2013, Snowball Finance and/or its affiliates. All rights reserved.
 * SNOWBALLFINANCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
<#assign serivcePropertyName = uncapitalize(components.service.className)/>
package ${classPackage};

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ${components.model.classPackage}.${components.model.className};
import ${components.service.classPackage}.${components.service.className};

/**
 * ${components.model.className} Controller.
 * ${description}功能Controller
 * 
 * @author hjyang
 * @since ${date}
 * @version ${version} 
 *
 */
@Controller
@RequestMapping("/${pluralize(lower(entityName))}")
public class ${className} {
	
	@Resource
	private ${components.service.className} ${serivcePropertyName};

	@RequestMapping("/get")
	public void get(@RequestParam(value = "page", required = false) Integer page, ModelMap modelMap) {
		// TODO Add logic
	}

}
