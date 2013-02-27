package biz.unitech.tools;

public class ThreadDimSQLGenerator {
	
	private static final String sqlPrefix = "INSERT INTO ThreadDim values (";
	private static final String sqlPostfix = "');";
	private static final String sqlMiddle = ", 'M";
	
	public static void main( String[] args) {
		
		String[][] tdim = new String[16][];
		
		tdim[0] = new String[] { "10x100", "10"};
		tdim[1] = new String[] { "12x100", "12"};
		tdim[2] = new String[] { "14x150", "14"};
		tdim[3] = new String[] { "16x150", "16"};
		tdim[4] = new String[] { "18x150", "18"};
		tdim[5] = new String[] { "20x150", "20"};
		tdim[6] = new String[] { "22x150", "22"};
		tdim[7] = new String[] { "24x150", "24"};
		tdim[8] = new String[] { "27x150", "27"};
		tdim[9] = new String[] { "30x150", "30"};
		tdim[10] = new String[] { "33x200", "33"};
		tdim[11] = new String[] { "39x200", "39"};
		tdim[12] = new String[] { "42x200", "42"};
		tdim[13] = new String[] { "45x200", "45"};
		tdim[14] = new String[] { "48x200", "48"};
		tdim[15] = new String[] { "60x200", "60"};
		
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
		
		String[] result2 = CrossGenerator.generate(sqlPrefix, tdim, sqlMiddle, sqlPostfix);
		
		for (String string : result) {
			System.out.println(string);
		}
		
		for (String string : result2) {
			System.out.println(string);
		}
		
	}
	
	
}
