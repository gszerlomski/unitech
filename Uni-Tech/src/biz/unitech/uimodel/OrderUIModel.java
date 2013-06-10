package biz.unitech.uimodel;



public class OrderUIModel {
	
	SupplierOrderUIModel supplierOrderModel;
	
	FittingDescUIModel fittingDesc;
	
	FittingUIModel fitting;

	public OrderUIModel(SupplierOrderUIModel orderModel,
			FittingDescUIModel fittingModel,
			FittingUIModel fitting) {
		this.supplierOrderModel = orderModel;
		this.fittingDesc = fittingModel;
		this.fitting = fitting;
	}
	
	public OrderUIModel() {}

	public SupplierOrderUIModel getSupplierOrderModel() {
		return supplierOrderModel;
	}
	
	public void setSupplierOrderModel(SupplierOrderUIModel supplierOrderModel) {
		this.supplierOrderModel = supplierOrderModel;
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
