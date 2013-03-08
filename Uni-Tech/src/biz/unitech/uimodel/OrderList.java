package biz.unitech.uimodel;

import java.util.List;

public class OrderList {

	private List<SupplierOrderUIModel> orders;
	
	public OrderList(List<SupplierOrderUIModel> orders) {
		this.orders = orders;
	}

	public List<SupplierOrderUIModel> getOrders() {
		return orders;
	}
	
	public void setOrders(List<SupplierOrderUIModel> orders) {
		this.orders = orders;
	}
}