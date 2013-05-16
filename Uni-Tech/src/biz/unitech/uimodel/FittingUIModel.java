package biz.unitech.uimodel;

import biz.unitech.datamodel.fitting.Fitting;
import biz.unitech.datamodel.fitting.FittingNameFormatter;

public class FittingUIModel {
	
	private String nameFormat;
	
	private InputField fittingType;
	
	private InputField adaptor;
	
	private InputField tubeDim;
	
	private InputField threadDim;
	
	private InputField oring;
	
	private InputField grip;
	
	private InputField gripNumber;

	private FittingUIPricing pricing;

	public FittingUIModel(Fitting fitting) {
		fittingType = fitting.getFittingType() != null ? new InputField(fitting.getFittingType().getFittingTypeName(), false) : null;
		tubeDim = fitting.getTubeDim() != null ? new InputField(fitting.getTubeDim().getTubeDimName(), false) : null;
		threadDim = fitting.getThreadDim() != null ? new InputField(fitting.getThreadDim().getThreadDimName(), false) : null;
		oring = fitting.getOring() != null ? new InputField(fitting.getOring().getOringName(), false) : null;
		grip = fitting.getGrip() != null ? new InputField(fitting.getGrip().getGripName(), false) : null;
		adaptor = fitting.getAdaptor() != null ? new InputField(fitting.getAdaptor().getAdaptorName(), false) : null;
		nameFormat = fitting.getFittingType() != null ? fitting.getFittingType().getNameFormat() : null;
		gripNumber = fitting.getFittingType() != null ? new InputField(fitting.getFittingType().getGripNumber(), true) : null;
	}
	
	public FittingUIModel() {}
	
	public InputField getFittingType() {
		return fittingType;
	}
	
	public void setFittingType(InputField fittingType) {
		this.fittingType = fittingType;
	}
	
	public InputField getGrip() {
		return grip;
	}
	
	public void setGrip(InputField grip) {
		this.grip = grip;
	}
	
	public InputField getOring() {
		return oring;
	}
	
	public void setOring(InputField oring) {
		this.oring = oring;
	}
	
	public InputField getThreadDim() {
		return threadDim;
	}
	
	public void setThreadDim(InputField threadDim) {
		this.threadDim = threadDim;
	}
	
	public InputField getTubeDim() {
		return tubeDim;
	}
	
	public void setTubeDim(InputField tubeDim) {
		this.tubeDim = tubeDim;
	}
	
	public InputField getAdaptor() {
		return adaptor;
	}
	
	public void setAdaptor(InputField adaptor) {
		this.adaptor = adaptor;
	}

	public void setDisabledInputs(boolean disabled) {
		if (adaptor != null) adaptor.setDisabled(disabled);
		if (tubeDim != null) tubeDim.setDisabled(disabled);
		if (threadDim != null) threadDim.setDisabled(disabled);
		if (oring != null) oring.setDisabled(disabled);
		if (grip != null) grip.setDisabled(disabled);
		if (gripNumber != null) gripNumber.setDisabled(disabled);
	}
	
	public String getFormattedName() {
		return FittingNameFormatter.format(
				nameFormat, 
				fittingType != null ? fittingType.getValue() : null, 
				tubeDim != null ? tubeDim.getValue() : null, 
				oring != null ? oring.getValue() : null, 
				grip != null ? grip.getValue() : null, 
				adaptor != null ? adaptor.getValue() : null, 
				threadDim != null ? threadDim.getValue() : null);
		
	}

	public InputField getGripNumber() {
		return gripNumber;
	}
	
	public void setGripNumber(InputField gripNumber) {
		this.gripNumber = gripNumber;
	}

	public void setPricing(FittingUIPricing pricing) {
		this.pricing = pricing;
	}

	public FittingUIPricing getPricing() {
		return pricing;
	}
}