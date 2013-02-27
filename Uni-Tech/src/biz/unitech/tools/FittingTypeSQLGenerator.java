package biz.unitech.tools;

public class FittingTypeSQLGenerator {
	
	//SQL: INSERT INTO FittingType values (fittingTypeOrderCode, fittingTypeName, nameFormat);
	
	
	
	private static final String sqlPrefix = "INSERT INTO FittingType values (";
	private static final String sqlPostfix = "');";
	private static final String sqlMiddle1 = ",'";
	private static final String sqlMiddle2 = "','";
	
	public static void main( String[] args) {
		
		String[][] type = new String[27][];
		
		type[0] = new String[] { "U", "", "2", "${TY} ${TU} ${OR} ${GR}"};
		type[1] = new String[] { "UR", "1", "2", "${TY} ${TU} ${TH} ${OR} ${GR}"};
		type[2] = new String[] { "PC", "15", "2", "${TY} ${TU} ${OR} ${GR}"};
		type[3] = new String[] { "BO", "10", "1", "${TY} ${TU} ${OR} ${GR}"};
		type[4] = new String[] { "UM", "2", "1", "${TY} ${TU} ${TH} ${OR} ${GR}"};
		type[5] = new String[] { "UMC", "3", "1", "${TY} ${TU} ${TH} ${OR} ${GR}"};
		type[6] = new String[] { "UMN", "20", "1", "${TY} ${TU} ${TH} ${OR} ${GR}"};
		type[7] = new String[] { "UMI", "83", "1", "${TY} ${TU} ${TH} ${OR} ${GR}"};
		type[8] = new String[] { "UMCI", "82", "1", "${TY} ${TU} ${TH} ${OR} ${GR}"};
		type[9] = new String[] { "UMNI", "79", "1", "${TY} ${TU} ${TH} ${OR} ${GR}"};
		type[10] = new String[] { "UF", "4", "1", "${TY} ${TU} ${TH} ${OR} ${GR}"};
		type[11] = new String[] { "UFN", "21", "1", "${TY} ${TU} ${TH} ${OR} ${GR}"};
		type[12] = new String[] { "AM", "6", "0", "${TY} ${TU} ${TH} ${OR}"};
		type[13] = new String[] { "AMC", "7", "0", "${TY} ${TU} ${TH}"};
		type[14] = new String[] { "AMN", "24", "0", "${TY} ${TU} ${TH}"};
		type[15] = new String[] { "AF", "8", "0", "${TY} ${TU} ${TH}"};
		type[16] = new String[] { "AFN", "23", "0", "${TY} ${TU} ${TH}"};
		type[17] = new String[] { "AUR", "5", "1", "${TY} ${AD} ${TU} ${TH} ${OR} ${GR}"};
		type[18] = new String[] { "E", "50", "2", "${TY} ${TU} ${OR} ${GR}"};
		type[19] = new String[] { "T", "30", "3", "${TY} ${TU} ${OR} ${GR}"};
		type[20] = new String[] { "C", "40", "4", "${TY} ${TU} ${OR} ${GR}"};
		type[21] = new String[] { "Y", "35", "3", "${TY} ${TU} ${OR} ${GR}"};
		type[22] = new String[] { "EM", "52", "1", "${TY} ${TU} ${TH} ${OR} ${GR}"};
		type[23] = new String[] { "EMC", "53", "1", "${TY} ${TU} ${TH} ${OR} ${GR}"};
		type[24] = new String[] { "EMN", "58", "1", "${TY} ${TU} ${TH} ${OR} ${GR}"};
		type[25] = new String[] { "OR", "61", "1", "${TY} ${TU} ${TH} ${OR} ${GR}"};
		type[26] = new String[] { "OB", "13", "0", "${TY} ${TU}"};
		
		
		String[][] bodyNut = new String[7][];
		bodyNut[0] = new String[] { "A", "10"};
		bodyNut[1] = new String[] { "AC", "13"};
		bodyNut[2] = new String[] { "AZ", "16"};
		bodyNut[3] = new String[] { "X", "20"};
		bodyNut[4] = new String[] { "XL", "21"};
		bodyNut[5] = new String[] { "L", "40"};
		bodyNut[6] = new String[] { "D", "30"};
		
		
		String[] result = CrossGenerator.generate(sqlPrefix, type, sqlMiddle1, sqlMiddle2,  bodyNut, sqlPostfix);
		
		for (String string : result) {
			System.out.println(string);
		}
		
	}
	
	
}
