package biz.unitech.uimodel;


public class OrderUIModel {
	
	SupplierOrderUIModel supplierOrderModel;
	
	FittingDescUIModel fittingDesc;
	
	FittingUIModel fitting;
	
	Integer number;

	public OrderUIModel(SupplierOrderUIModel orderModel,
			FittingDescUIModel fittingModel,
			FittingUIModel fitting, Integer number) {
		this.supplierOrderModel = orderModel;
		this.fittingDesc = fittingModel;
		this.fitting = fitting;
		this.number = number;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
