package biz.unitech.tools;

public class GripSQLGenerator {
	
	private static final String sqlPrefix = "INSERT INTO Grip values (";
	private static final String sqlPostfix = "', 12.50);";
	private static final String sqlMiddle = ", '";
	
	public static void main( String[] args) {
		
		String[][] grip = new String[9][];
		
		grip[0] = new String[] { "JAB", "10"};
		grip[1] = new String[] { "BAB", "20"};
		grip[2] = new String[] { "BX", "24"};
		grip[3] = new String[] { "BSX", "34"};
		grip[4] = new String[] { "BXL", "25"};
		grip[5] = new String[] { "BL", "27"};
		grip[6] = new String[] { "BD", "21"};
		grip[7] = new String[] { "JAB", "12"};
		grip[8] = new String[] { "BAB", "22"};
		
		String[] result = CrossGenerator.generate(sqlPrefix, grip, sqlMiddle, sqlPostfix);
		
		for (String string : result) {
			System.out.println(string);
		}
		
	}
	
	
}
