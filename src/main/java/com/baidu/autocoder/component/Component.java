package com.baidu.autocoder.component;

import java.util.Map;

/**
 * 组件基类.
 * 
 * @author GuoLin
 *
 */
public abstract class Component {
	
	/** 组件类型标志. */
	protected final String type;
	
	/** 模板文件名. */
	protected final String template;
	
	/**
	 * 构造器.
	 * @param type 组件类型，例如：model,dao,serviceImpl等
	 */
	public Component(String type, String template) {
		this.type = type;
		this.template = template;
	}

	/**
	 * 获取组件类型.
	 * @return 组件类型
	 */
	public final String getType() {
		return type;
	}

	/**
	 * 获取模板文件名.
	 * @return 模板名
	 */
	public final String getTemplate() {
		return template;
	}
	
	/**
	 * 获取目标文件名.
	 * @return 目标文件名
	 */
	public abstract String getTargetFilename();
	
	/**
	 * 获取目标文件目录.
	 * @return 目标文件目录
	 */
	public abstract String getTargetPath();
	
	/**
	 * 根据提供的可映射参数模型重新计算当前实例的值，并返回计算结果.
	 * @param model 可映射参数
	 * @return 新的计算后的组件实例
	 */
	public abstract Component buildCalculatedComponent(Map<String, ?> model);
	
	/**
	 * 将当前组件的值导出成Map.
	 * @return 组件值Map
	 */
	public abstract Map<String, ?> toMap();
	
}
