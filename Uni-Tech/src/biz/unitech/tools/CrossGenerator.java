package biz.unitech.tools;

public class CrossGenerator {

	public static String[] generate(String prefix, String[][] cross1,
			String middle, String[][] cross2, String postfix) {
		String[] result = new String[cross1.length * cross2.length];

		int i = 0;
		for (String[] first : cross1) {
			for (String[] second : cross2) {

				result[i++] = prefix + first[1] + second[1] + middle + first[0]
						+ second[0] + postfix;
			}
		}
		return result;
	}

	public static String[] generate(String prefix, String[][] values,
			String middle, String postfix) {
		String[] result = new String[values.length];

		int i = 0;
		for (String[] value : values) {
			result[i++] = prefix + value[1] + middle + value[0] + postfix;
		}
		return result;
	}

	public static String[] generate(String prefix, String[][] type,
			String middle1, String middle2, String[][] bodyNut,
			String postfix) {

		String[] result = new String[type.length * bodyNut.length];

		int i = 0;
		for (String[] first : type) {
			for (String[] second : bodyNut) {

				result[i] = prefix + first[1] + second[1] + middle1 + first[0]
						+ second[0] + middle2 + first[2];
				if(first.length > 3) {
					result[i] += middle2 + first[3];
				}
				result[i++] += postfix;
			}
		}
		return result;
	}
}
