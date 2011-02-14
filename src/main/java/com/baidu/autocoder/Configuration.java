package com.baidu.autocoder;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.autocoder.component.ClassComponent;
import com.baidu.autocoder.component.Component;
import com.baidu.autocoder.generator.Generator;

/**
 * 统一配置类.
 * 
 * @author GuoLin
 *
 */
public class Configuration {
	
	private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
	
	/** 配置文件名. */
	private static final String CONFIGURATION_FILE = "/autocoder.json";
	
	/** 单例实例. */
	private static Configuration instance = new Configuration();

	/** 数据库驱动类. */
	private final String databaseDriver;
	
	/** 数据库URL. */
	private final String databaseUrl;
	
	/** 数据库用户名. */
	private final String databaseUsername;
	
	/** 数据库密码. */
	private final String databasePassword;
	
	/** 数据库类型与Java原生类型映射关系. */
	private final Map<String, String> typeConvertMap;
	
	/** 模板路径. */
	private final String templatePath;
	
	/** 目标项目源码路径. */
	private final String targetPath;
	
	/** 编码. */
	private final String encoding;
	
	/** 组件Map. */
	private final Map<String, Component> componentMap;
	
	/** 作者，用于注释. */
	private final String author;
	
	/** 生成器类 */
	private final Generator generator;
	
	/**
	 * 构造器.
	 * @param fileName 配置文件名
	 */
	private Configuration() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		JsonNode root;
		try {
			root = mapper.readValue(this.getClass().getResource(CONFIGURATION_FILE), JsonNode.class);
		} catch (JsonParseException ex) {
			logger.error("", ex);
			throw new RuntimeException(ex);
		} catch (JsonMappingException ex) {
			logger.error("", ex);
			throw new RuntimeException(ex);
		} catch (IOException ex) {
			logger.error("", ex);
			throw new RuntimeException(ex);
		}
		
		// 数据库配置
		JsonNode database = root.path("database");
		databaseDriver = database.path("driver").getTextValue();
		databaseUrl = database.path("url").getTextValue();
		databaseUsername = database.path("username").getTextValue();
		databasePassword = database.path("password").getTextValue();
		
		// 类型映射
		typeConvertMap = new HashMap<String, String>();
		JsonNode typeMapping = root.path("typeMapping");
		for (Iterator<String> iter = typeMapping.getFieldNames(); iter.hasNext(); ) {
			String dbType = iter.next();
			String javaType = typeMapping.path(dbType).getTextValue();
			typeConvertMap.put(dbType, javaType);
		}
		
		// 模板配置
		templatePath = root.path("templatePath").getTextValue();
		
		// 编码
		encoding = root.path("encoding").getTextValue();
		
		// 目标路径
		targetPath = root.path("targetPath").getTextValue();
		
		// 实例化生成器
		String generatorClass = root.path("templateGenerator").getTextValue();
		generator = buildGenerator(generatorClass);
		
		componentMap = new HashMap<String, Component>();

		// Class组件
		JsonNode components = root.path("components").path("classes");
		for (Iterator<String> iter = components.getFieldNames(); iter.hasNext(); ) {
			String id = iter.next();
			JsonNode component = components.path(id);
			String className = component.path("className").getTextValue();
			String packageName = component.path("classPackage").getTextValue();
			String template = component.path("template").getTextValue();
			componentMap.put(id, new ClassComponent(id, className, packageName, template));
		}
		
		// File组件
		
		// 作者
		author = root.path("author").getTextValue();
	}
	
	/**
	 * 获取配置实例.
	 * @return 配置实例
	 */
	public static Configuration getConfiguration() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	private Generator buildGenerator(String className) {
		// 获取模板生成类信息
		Class<? extends Generator> clazz;
		try {
			clazz = (Class<? extends Generator>) Class.forName(className);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("The specified class of templateGenerator not found.", ex);
		} catch (ClassCastException ex) {
			throw new ClassCastException("The specified class of templateGenerator must be implemented by com.baidu.autocoder.generator.Generator: " + ex.getMessage());
		}
		
		// 获取实现类构造器
		Constructor<? extends Generator> constructor = null;
		try {
			constructor = clazz.getConstructor(String.class, String.class);
		} catch (SecurityException ex) {
			throw new RuntimeException("The specified class of templateGenerator must has a public constructor with two String parameters(templatePath, encoding).", ex);
		} catch (NoSuchMethodException ex) {
			throw new RuntimeException("The specified class of templateGenerator must has a constructor with two String parameters(templatePath, encoding).", ex);
		}
		
		// 通过构造器实例化类
		try {
			return constructor.newInstance(templatePath, encoding);
		} catch (IllegalArgumentException ex) {
			throw new RuntimeException("The specified class of templateGenerator must has a constructor with two String parameters(templatePath, encoding).", ex);
		} catch (InstantiationException ex) {
			throw new RuntimeException("Cannot instantiate specified class of templateGenerator.", ex);
		} catch (IllegalAccessException ex) {
			throw new RuntimeException("The specified class of templateGenerator must has a public constructor with two String parameters(templatePath, encoding).", ex);
		} catch (InvocationTargetException ex) {
			throw new RuntimeException("Cannot instantiate specified class of templateGenerator.", ex);
		}
		
	}

	public String getDatabaseDriver() {
		return databaseDriver;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public String getDatabaseUsername() {
		return databaseUsername;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public Map<String, String> getTypeConvertMap() {
		return typeConvertMap;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public Map<String, Component> getComponentMap() {
		return componentMap;
	}

	public String getAuthor() {
		return author;
	}

	public Generator getGenerator() {
		return generator;
	}
	
}
