package biz.unitech.tools;

import java.text.DateFormat;
import java.text.ParseException;

public class Tester {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		System.out.println(DateFormat.getDateInstance(DateFormat.SHORT).parse("2/9/2013"));
	}

}
