package biz.unitech.tools;

public class OringSQLGenerator {
	
	//SQL: INSERT INTO FittingType values (fittingTypeOrderCode, fittingTypeName, nameFormat);
	
	
	
	private static final String sqlPrefix = "INSERT INTO Oring values (";
	private static final String sqlPostfix = "');";
	private static final String sqlMiddle = ", '";
	
	public static void main( String[] args) {
		
		String[][] oring = new String[5][];
		
		oring[0] = new String[] { "JN", "1"};
		oring[1] = new String[] { "JV", "2"};
		oring[2] = new String[] { "JS", "3"};
		oring[3] = new String[] { "JE", "4"};
		oring[4] = new String[] { "JH", "6"};
		
		String[] result = CrossGenerator.generate(sqlPrefix, oring, sqlMiddle, sqlPostfix);
		
		for (String string : result) {
			System.out.println(string);
		}
		
	}
	
	
}
