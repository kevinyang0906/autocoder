package com.baidu.autocoder.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 命名工具类测试.
 * 
 * @author GuoLin
 *
 */
public class StringUtilsTests {

	@Test
	public void underscoreToCamel_smoke() {
		assertEquals("userName", StringUtils.underscoreToCamel("user_name"));
		assertEquals("userNameIsAGoodName", StringUtils.underscoreToCamel("user_name_is_a_good_name"));
		assertEquals("usernameHaha", StringUtils.underscoreToCamel("_username_haha"));
		assertEquals("usernameHaha", StringUtils.underscoreToCamel("____username_haha"));
		assertEquals("username", StringUtils.underscoreToCamel("username_"));
		assertEquals("username", StringUtils.underscoreToCamel("username"));
	}
	
	@Test
	public void pluralize_smoke() {
		assertEquals("users", StringUtils.pluralize("user"));
		assertEquals("classes", StringUtils.pluralize("class"));
		assertEquals("boxes", StringUtils.pluralize("box"));
		assertEquals("stories", StringUtils.pluralize("story"));
		assertEquals("tomatoes", StringUtils.pluralize("tomato"));
		assertEquals("children", StringUtils.pluralize("child"));
		assertEquals("people", StringUtils.pluralize("person"));
	}
}
