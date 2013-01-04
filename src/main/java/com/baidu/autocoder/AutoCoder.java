package com.baidu.autocoder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.autocoder.component.Component;
import com.baidu.autocoder.database.Table;
import com.baidu.autocoder.database.TableAssembler;
import com.baidu.autocoder.generator.Generator;
import com.baidu.autocoder.util.StringUtils;


/**
 * AutoCoder的主类.
 * 
 * @author GuoLin
 *
 */
public class AutoCoder {
	
	private static final Logger logger = LoggerFactory.getLogger(AutoCoder.class);
	
	/**
	 * 根据表名生成代码.
	 * @param tableName 表名
	 * @param entityName 实体名
	 */
	public void generate(String tableName, String entityName,String description) {
		
		// 加载全局配置
		Configuration config = Configuration.getConfiguration();
		
		// 获取表结构，存储到Table中
		TableAssembler tableAssember = new TableAssembler();
		Table table = tableAssember.getTable(tableName, entityName);
		
		// 生成模板们
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("table", table);
		model.put("entityName", table.getEntityName());
		model.put("description", description);
		model.put("packageName", StringUtils.uncapitalize(table.getEntityName()));
		model.put("author", config.getAuthor());
		model.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		// 重新计算所有的组件
		Map<String, Component> calculatedComponentMap = new HashMap<String, Component>();
		for (Map.Entry<String, Component> entry : config.getComponentMap().entrySet()) {
			Component calculatedComponent = entry.getValue().buildCalculatedComponent(model);
			calculatedComponentMap.put(calculatedComponent.getType(), calculatedComponent);
		}
		model.put("version", config.getVersion());
		model.put("components", calculatedComponentMap);

		// 逐个处理组件
		Generator generator = config.getGenerator();
		for (Map.Entry<String, Component> entry : calculatedComponentMap.entrySet()) {
			Component component = entry.getValue();
			model.putAll(component.toMap());

			// 生成内容
			String content = generator.generate(component.getTemplate(), model);
			if (logger.isDebugEnabled()) {
				logger.debug("Generating content for {} - {}: [{}]", new Object[] { component.getTargetPath(), component.getTargetFilename(), content });
			}
			
			// 保存结果
			File file = new File(component.getTargetPath(), component.getTargetFilename());
			try {
				FileUtils.writeStringToFile(file, content, config.getEncoding());
			} catch (IOException ex) {
				logger.error("", ex);
			}
		}

	}

	/**
	 * 命令行执行方法.
	 * @param args 参数
	 */
	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			System.out.println("Usage: java com.baidu.autocoder.AutoCoder <TableName> [EntityName] [description]");
			return;
		}
		
		String tableName = args[0];
		
		String entityName;
		if (args.length >= 2) {
			entityName = args[1];
		} else {
			entityName = null;
		}
		
		String description;
		if (args.length >= 3) {
			description = args[2];
		} else {
			description = null;
		}
		
		AutoCoder coder = new AutoCoder();
		coder.generate(tableName, entityName,description);
	}
}
