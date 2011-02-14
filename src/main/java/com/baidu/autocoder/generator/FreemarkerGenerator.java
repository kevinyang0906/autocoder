package com.baidu.autocoder.generator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.autocoder.util.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 模板生成器Freemarker实现.
 * 
 * @author GuoLin
 *
 */
public class FreemarkerGenerator extends Generator {
	
	private static final Logger logger = LoggerFactory.getLogger(FreemarkerGenerator.class);

	/** Freemarker配置. */
	private final Configuration freemarkerConfig;
	
	/** Freemarker全局属性映射模型. */
	private final Map<String, Object> globalMap;
	
	/**
	 * 构造器.
	 * @param templateBasePath 模板路径前缀
	 * @param encoding 模板编码
	 */
	public FreemarkerGenerator(String templateBasePath, String encoding) {
		super(templateBasePath, encoding);
		
		// 配置freemarker
		freemarkerConfig = new Configuration();
		freemarkerConfig.setDefaultEncoding(encoding);
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), templateBasePath);
		
		// 构造全局属性映射
		globalMap = new HashMap<String, Object>();
		globalMap.put("capitalize", new CapitalizeMethodModel());
		globalMap.put("uncapitalize", new UncapitalizeMethodModel());
		globalMap.put("pluralize", new PluralizeMethodModel());
		globalMap.put("upper", new UpperMethodModel());
		globalMap.put("lower", new LowerMethodModel());
	}
	
	public String generate(String templateFile, Map<String, ?> model) {
		try {
			// 加载模板
			Template template = freemarkerConfig.getTemplate(templateFile);
			
			// 合并用户model和全局model
			Map<String, Object> propModel = new HashMap<String, Object>();
			propModel.putAll(globalMap);
			propModel.putAll(model);
			
			// 生成内容
			StringWriter result = new StringWriter();
			template.process(propModel, result);
			return result.toString();
			
		} catch (TemplateException ex) {
			logger.error("Error while processing FreeMarker template ", ex);
		} catch (FileNotFoundException ex) {
			logger.error("Error while open template file ", ex);
		} catch (IOException ex) {
			logger.error("Error while generate template content ", ex);
		}
		return null;
	}
	
	/**
	 * Freemarker的首字母大写方法.
	 * 
	 * @author GuoLin
	 *
	 */
	private static class CapitalizeMethodModel implements TemplateMethodModelEx {

		@SuppressWarnings("rawtypes")
		public Object exec(List arguments) throws TemplateModelException {
			return StringUtils.capitalize(arguments.get(0));
		}
		
	}
	
	/**
	 * Freemarker的首字母小写方法.
	 * 
	 * @author GuoLin
	 *
	 */
	private static class UncapitalizeMethodModel implements TemplateMethodModelEx {

		@SuppressWarnings("rawtypes")
		public Object exec(List arguments) throws TemplateModelException {
			return StringUtils.uncapitalize(arguments.get(0));
		}
		
	}
	
	/**
	 * Freemarker复数化方法.
	 * 
	 * @author GuoLin
	 *
	 */
	private static class PluralizeMethodModel implements TemplateMethodModelEx {

		@SuppressWarnings("rawtypes")
		public Object exec(List arguments) throws TemplateModelException {
			return StringUtils.pluralize(arguments.get(0));
		}
		
	}
	
	/**
	 * Freemarker将字符串转换为小写的方法.
	 * 
	 * @author GuoLin
	 *
	 */
	private static class LowerMethodModel implements TemplateMethodModelEx {

		@SuppressWarnings("rawtypes")
		public Object exec(List arguments) throws TemplateModelException {
			String string = StringUtils.toString(arguments.get(0));
			if (StringUtils.isEmpty(string)) {
				return string;
			}
			return string.toLowerCase();
		}
		
	}
	
	/**
	 * Freemarker将字符串转换为大写的方法.
	 * 
	 * @author GuoLin
	 *
	 */
	private static class UpperMethodModel implements TemplateMethodModelEx {

		@SuppressWarnings("rawtypes")
		public Object exec(List arguments) throws TemplateModelException {
			String string = StringUtils.toString(arguments.get(0));
			if (StringUtils.isEmpty(string)) {
				return string;
			}
			return string.toUpperCase();
		}
		
	}
	
	/**
	 * Freemarker获取model所有值的方法.
	 * 
	 * @author GuoLin
	 *
	 */
	private static class ShowMeTheMoneyMethodModel implements TemplateMethodModelEx {

		@SuppressWarnings("rawtypes")
		public Object exec(List arguments) throws TemplateModelException {
			String string = StringUtils.toString(arguments.get(0));
			if (StringUtils.isEmpty(string)) {
				return string;
			}
			return string.toUpperCase();
		}
		
	}
	
}
