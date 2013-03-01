package biz.unitech.uimodel;

import java.util.List;

public class OrderList {

	private List<SupplierOrderUIModel> orders;
	
	private List<SupplierOrderUIModel> ordersChanged;
	
	public OrderList(List<SupplierOrderUIModel> orders, List<SupplierOrderUIModel> ordersChanged) {
		this.orders = orders;
		this.ordersChanged = ordersChanged;
	}

	public List<SupplierOrderUIModel> getOrders() {
		return orders;
	}
	
	public void setOrders(List<SupplierOrderUIModel> orders) {
		this.orders = orders;
	}
	
	public List<SupplierOrderUIModel> getOrdersChanged() {
		return ordersChanged;
	}
	
	public void setOrdersChanged(List<SupplierOrderUIModel> ordersChanged) {
		this.ordersChanged = ordersChanged;
	}
}