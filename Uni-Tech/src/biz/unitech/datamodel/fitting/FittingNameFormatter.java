package biz.unitech.datamodel.fitting;

import java.util.regex.Pattern;

public class FittingNameFormatter {
	
	private static final String FITTING_TYPE_VAR = "${" + FittingType.NAME_SYMBOL + "}";
	private static final String TUBE_DIM_VAR = "${"+ TubeDim.NAME_SYMBOL +"}";
	private static final String ORING_VAR = "${" + Oring.NAME_SYMBOL + "}";
	private static final String GRIP_VAR = "${" + Grip.NAME_SYMBOL + "}";
	private static final String ADAPTOR_VAR = "${" + Adaptor.NAME_SYMBOL + "}";
	private static final String THREAD_DIM_VAR = "${" + ThreadDim.NAME_SYMBOL + "}";
	

	public static String format(Fitting fitting) {
		String format = fitting.getFittingType().getNameFormat();
		String name = format;
		if(fitting.getFittingType() != null) {
			name = name.replaceFirst(FITTING_TYPE_VAR, fitting.getFittingType().getFittingTypeName());
		}
		if(fitting.getTubeDim() != null) {
			name = name.replaceFirst(TUBE_DIM_VAR, fitting.getTubeDim().getTubeDimName());
		}
		if(fitting.getOring() != null) {
			name = name.replaceFirst(ORING_VAR, fitting.getOring().getOringName());
		}
		if(fitting.getGrip() != null) {
			name = name.replaceFirst(GRIP_VAR, fitting.getGrip().getGripName());
		}
		if(fitting.getAdaptor() != null) {
			name = name.replaceFirst(ADAPTOR_VAR, fitting.getAdaptor().getAdaptorName());
		}
		if(fitting.getThreadDim() != null) {
			name = name.replaceFirst(THREAD_DIM_VAR, fitting.getThreadDim().getThreadDimName());
		}
		
		if(name.contains("$")) {
			throw new InvalidFittingnameException("invalid fitting name format for " + fitting.getFittingOrderCode());
		}
		
		return name;
	}
	
	public static String format(String name, String fittingTypeName, String tubeDimName, String oringName, String gripName, 
			String adaptorName, String threadDimName) {
		
		if(fittingTypeName != null) {
			name = name.replaceFirst(Pattern.quote(FITTING_TYPE_VAR), fittingTypeName);
		}
		if(tubeDimName != null) {
			name = name.replaceFirst(Pattern.quote(TUBE_DIM_VAR), tubeDimName);
		}
		if(oringName != null) {
			name = name.replaceFirst(Pattern.quote(ORING_VAR), oringName);
		}
		if(gripName != null) {
			name = name.replaceFirst(Pattern.quote(GRIP_VAR), gripName);
		}
		if(adaptorName != null) {
			name = name.replaceFirst(Pattern.quote(ADAPTOR_VAR), adaptorName);
		}
		if(threadDimName != null) {
			name = name.replaceFirst(Pattern.quote(THREAD_DIM_VAR), threadDimName);
		}
		
		if(name.contains("$")) {
			throw new InvalidFittingnameException("invalid fitting name format");
		}
		return name;
	}

}
