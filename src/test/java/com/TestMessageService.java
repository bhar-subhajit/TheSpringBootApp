package com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestMessageService {
	
	String a = "abcd";
	MessageService ms = new MessageService();
	
	@Test
	public void testCaps() {
		Assertions.assertEquals(ms.capitalise(a), "ABCD");
	}
	
	@Test
	public void testSmall() {
		String b = "AAAb";
		Assertions.assertEquals(ms.small(b), "aaab");
	}
	
	@Test
	public void testConcat() {
		String x= "Hi", y= "Hello";
		Assertions.assertEquals(ms.concat(x,y), "HiHello");
	}
	
	@Test
	public void testLastChar() {
		Assertions.assertEquals(ms.lastChar(a,"d"), true);
	}
	
	@Test
	public void testTrim() {
		String x = "  Hello";
		Assertions.assertEquals(ms.trim(x), "Hello");
	}
	
}
