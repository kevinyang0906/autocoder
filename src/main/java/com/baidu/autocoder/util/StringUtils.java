package com.baidu.autocoder.util;

import java.util.Locale;
import java.util.Map;

import org.jvnet.inflector.Noun;

/**
 * 字符串工具类.
 * 
 * @author GuoLin
 *
 */
public class StringUtils {

	/**
	 * 用参数模型中的内容替换字符串中的参数.
	 * @param s 待替换的字符串
	 * @param model 参数模型
	 * @return 替换后的字符串
	 */
	public static String subsititue(String string, Map<String, ?> model) {
		String result = string;
		for (Map.Entry<String, ?> entry : model.entrySet()) {
			String key = "\\$\\{" + entry.getKey() + "\\}";
			String value = (entry.getValue() instanceof String) ? (String)entry.getValue() : entry.getValue().toString();
			result = result.replaceAll(key, value);
		}
		return result;
	}
	
	/**
	 * 将对象转化为字符串.
	 * @param obj 对象
	 * @return 字符串
	 */
	public static String toString(Object obj) {
		return (obj == null) ? null : (obj instanceof String) ? (String)obj : obj.toString();
	}
	
	/**
	 * 将带下划线的名字转换为驼峰式命名.
	 * @param obj 带下划线的字符串
	 * @return 驼峰式命名
	 */
	public static String underscoreToCamel(Object obj) {
		return underscoreToCamel(obj, false);
	}

	/**
	 * 将带下划线的名字转换为驼峰式命名.
	 * @param obj 带下划线的字符串
	 * @param capitalizeFirst 首字母是否需要大写
	 * @return 驼峰式命名
	 */
	public static String underscoreToCamel(Object obj, boolean capitalizeFirst) {
		String string = toString(obj);
        if (isEmpty(string)) {
            return string;
        }
		
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == '_') {
				if (result.length() > 0 && i + 1 < string.length()) {
					result.append(Character.toUpperCase(string.charAt(++i)));
				}
			} else {
				result.append(string.charAt(i));
			}
		}
		
		return capitalizeFirst ? capitalize(result.toString()) : result.toString();
	}

	/**
	 * 首字符大写.
	 * @param obj 对象
	 * @return 首字符大写后的字符串
	 */
    public static String capitalize(Object obj) {
        String string = toString(obj);
        if (isEmpty(string)) {
            return string;
        }
        return ("" + string.charAt(0)).toUpperCase() + string.substring(1);
    }
    
	/**
	 * 首字符小写.
	 * @param obj 对象
	 * @return 首字符小写后的字符串
	 */
    public static String uncapitalize(Object obj) {
        String string = toString(obj);
        if (isEmpty(string)) {
            return string;
        }
        return ("" + string.charAt(0)).toLowerCase() + string.substring(1);
    }
    
    /**
     * 将字符串变为复数形式.
     * @param obj 对象
     * @return 复数形式字符串
     */
    public static String pluralize(Object obj) {
    	String string = toString(obj);
        if (isEmpty(string)) {
            return string;
        }
        return Noun.pluralOf(string, Locale.ENGLISH);
    }

    /**
     * 检测字符串是否为空.
     * @param obj 对象
     * @return 如果为空返回true，否则返回false
     */
    public static boolean isEmpty(Object obj) {
    	String string = toString(obj);
        return string == null || string.length() == 0;
    }

}
