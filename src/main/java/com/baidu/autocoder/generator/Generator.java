package com.baidu.autocoder.generator;

import java.util.Map;

/**
 * 模板生成器接口.
 * 
 * @author GuoLin
 *
 */
public abstract class Generator {
	
	/** 默认模板编码. */
	private static final String DEFAULT_ENCODING = "UTF-8";
	
	/** 模板路径前缀. */
	protected final String basePath;
	
	/** 模板编码. */
	protected final String encoding;
	
	/**
	 * 构造器.
	 * @param basePath 模板路径前缀
	 * @param encoding 模板编码
	 */
	protected Generator(String basePath, String encoding) {
		this.basePath = basePath;
		this.encoding = encoding;
	}

	/**
	 * 构造器.
	 * @param templatePath 模板路径前缀
	 */
	protected Generator(String basePath) {
		this(basePath, DEFAULT_ENCODING);
	}

	/**
	 * 生成模板.
	 * @param templateFile 模板文件
	 * @param model 需要注入到模板中的内容
	 * @return 生成完成后的字符串
	 */
	public abstract String generate(String templateFile, Map<String, ?> model);
	
}
