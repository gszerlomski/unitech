package biz.unitech.tools;

public class AdaptorSQLGenerator {
	
	private static final String sqlPrefix = "INSERT INTO Adaptor values (";
	private static final String sqlPostfix = " mm');";
	private static final String sqlPostfix2 = "');";
	private static final String sqlMiddle = ", '";
	
	public static void main( String[] args) {
		
		String[][] tdim = new String[17][];
		
		tdim[0] = new String[] { "6", "6"};
		tdim[1] = new String[] { "8", "8"};
		tdim[2] = new String[] { "10", "10"};
		tdim[3] = new String[] { "12", "12"};
		tdim[4] = new String[] { "14", "14"};
		tdim[5] = new String[] { "15", "15"};
		tdim[6] = new String[] { "16", "16"};
		tdim[7] = new String[] { "18", "18"};
		tdim[8] = new String[] { "20", "20"};
		tdim[9] = new String[] { "22", "22"};
		tdim[10] = new String[] { "25", "25"};
		tdim[11] = new String[] { "28", "28"};
		tdim[12] = new String[] { "30", "30"};
		tdim[13] = new String[] { "35", "35"};
		tdim[14] = new String[] { "38", "38"};
		tdim[15] = new String[] { "40", "40"};
		tdim[16] = new String[] { "44.5", "44"};
		
		String[] result = CrossGenerator.generate(sqlPrefix, tdim, sqlMiddle, sqlPostfix);
		
		tdim = new String[9][];
		
		tdim[0] = new String[] { "1/8", "71"};
		tdim[1] = new String[] { "1/4", "72"};
		tdim[2] = new String[] { "3/8", "73"};
		tdim[3] = new String[] { "1/2", "74"};
		tdim[4] = new String[] { "3/4", "75"};
		tdim[5] = new String[] { "1\"", "76"};
		tdim[6] = new String[] { "1\"1/4", "77"};
		tdim[7] = new String[] { "1\"1/2", "78"};
		tdim[8] = new String[] { "2\"", "79"};
		
		String[] result2 = CrossGenerator.generate(sqlPrefix, tdim, sqlMiddle, sqlPostfix2);
		
		for (String string : result) {
			System.out.println(string);
		}
		
		for (String string : result2) {
			System.out.println(string);
		}
		
	}
	
	
}
