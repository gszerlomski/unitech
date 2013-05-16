package biz.unitech.uimodel;


public abstract class OrderUIModel {
	
	FittingDescUIModel fittingDesc = UIModelCreator.getFittingDescUIModel();
	
	FittingUIModel fitting;

	public OrderUIModel(FittingUIModel fitting) {
		this.fitting = fitting;
	}

	public FittingDescUIModel getFittingDesc() {
		return fittingDesc;
	}

	public void setFittingDesc(FittingDescUIModel fittingDesc) {
		this.fittingDesc = fittingDesc;
	}

	public FittingUIModel getFitting() {
		return fitting;
	}

	public void setFitting(FittingUIModel fitting) {
		this.fitting = fitting;
	}
}
