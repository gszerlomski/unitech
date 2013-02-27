package biz.unitech.datamodel.test;

import static org.junit.Assert.*;

import org.junit.Test;

import biz.unitech.tools.CrossGenerator;

public class CrossGeneratorTest {

	@Test
	public void test() {
		String[][] cross1 = new String[][]{{"A", "0"}, {"B", "1"}};
		String[][] cross2 = new String[][]{{"C", "2"}, {"D", "3"}};
		String prefix = "prefix ";
		String postfix = " postfix;";
		String middle = " middle ";
		
		String[] result = CrossGenerator.generate(prefix, cross1, middle, cross2, postfix);
		
		assertEquals("Length generated is wrong", 4, result.length);
		assertEquals("Generated crossreference does not match", "prefix 02 middle AC postfix;", result[0]);
		assertEquals("Generated crossreference does not match", "prefix 03 middle AD postfix;", result[1]);
		assertEquals("Generated crossreference does not match", "prefix 12 middle BC postfix;", result[2]);
		assertEquals("Generated crossreference does not match", "prefix 13 middle BD postfix;", result[3]);
	}

}
