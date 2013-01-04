package com.baidu.autocoder;

import static org.junit.Assert.*;

import org.junit.Test;

public class AutoCoderTest {

	@Test
	public void testMain() {
		AutoCoder.main(new String[]{"like_comment_timeline","LikeCommentTimeline","评论赞"});
	}

}
