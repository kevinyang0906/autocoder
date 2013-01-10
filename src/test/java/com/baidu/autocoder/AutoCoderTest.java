package com.baidu.autocoder;

import static org.junit.Assert.*;

import org.junit.Test;

public class AutoCoderTest {

	@Test
	public void testMain() {
		AutoCoder.main(new String[]{"usergroup","UserGroup","用户组","user"});
		AutoCoder.main(new String[]{"member","Member","用户组成员","user"});
	}

}
