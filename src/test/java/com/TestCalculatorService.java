package com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCalculatorService {
	int a=10,b=5;
	@Test
	public void testAdd() {
		CalculatorService cs = new CalculatorService();
		Assertions.assertEquals(cs.add(a,b), 15);
	}
	
	@Test
	public void testSubtract() {
		CalculatorService cs = new CalculatorService();
		Assertions.assertEquals(cs.subtract(a,b), 5);
	}
	
	@Test
	public void testMultiply() {
		CalculatorService cs = new CalculatorService();
		Assertions.assertEquals(cs.multiply(a,b), 50);
	}
	
	@Test
	public void testDivide() {
		CalculatorService cs = new CalculatorService();
		Assertions.assertEquals(cs.divide(a,b), 2);
	}
	
	@Test
	public void testReminder() {
		CalculatorService cs = new CalculatorService();
		Assertions.assertEquals(cs.remainder(a,b), 0);
	}
}