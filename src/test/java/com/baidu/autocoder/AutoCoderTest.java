package com.baidu.autocoder;

import static org.junit.Assert.*;

import org.junit.Test;

public class AutoCoderTest {

	@Test
	public void testMain() {
		/** 表名，对象名，描述，包名 **/
		AutoCoder.main(new String[]{"prize","Prize","邀请兑换","invite"});
//		AutoCoder.main(new String[]{"member","Member","用户组成员","user"});
	}

}
