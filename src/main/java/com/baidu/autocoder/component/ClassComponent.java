package com.baidu.autocoder.component;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import com.baidu.autocoder.Configuration;
import com.baidu.autocoder.util.StringUtils;

/**
 * 类组件.
 * 
 * @author GuoLin
 *
 */
public class ClassComponent extends Component {
	
	/** 类组件文件名后缀. */
	private static final String FILENAME_SUFFIX = ".java";

	/** 类名. */
	private final String className;
	
	/** 包名. */
	private final String classPackage;
	
	/**
	 * 全值构造器.
	 * @param type 组件类型标志，例如：model,dao,serviceImpl等
	 * @param className 类名
	 * @param classPackage 包名
	 * @param template 模板文件名
	 */
	public ClassComponent(String type, String className, String classPackage, String template) {
		super(type, template);
		this.className = className;
		this.classPackage = classPackage;
	}

	@Override
	public String getTargetFilename() {
		return getClassName() + FILENAME_SUFFIX;
	}

	@Override
	public String getTargetPath() {
		String packagePath = classPackage.replaceAll("\\.", "/");
		return FilenameUtils.concat(Configuration.getConfiguration().getTargetPath(), packagePath);
	}

	public String getClassName() {
		return className;
	}

	public String getClassPackage() {
		return classPackage;
	}

	@Override
	public ClassComponent buildCalculatedComponent(Map<String, ?> model) {
		String newClassName = StringUtils.subsititue(className, model);
		String newClassPackage = StringUtils.subsititue(classPackage, model);
		String newTemplate = StringUtils.subsititue(template, model);
		ClassComponent component = new ClassComponent(type, newClassName, newClassPackage, newTemplate);
		return component;
	}
	
	@Override
	public Map<String, ?> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("className", className);
		map.put("classPackage", classPackage);
		return map;
	}

}
