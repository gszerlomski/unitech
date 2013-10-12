package biz.unitech.uimodel;

import java.math.BigDecimal;

import biz.unitech.datamodel.orders.CustomerOrderLineItem;
import biz.unitech.datamodel.orders.CustomerPriceList;
import biz.unitech.datamodel.orders.SupplierPriceList;

public class CustomerOrderLineItemUIModel {

	private FittingUIModel product;

	private int amount;

	private boolean delivered;

	public CustomerOrderLineItemUIModel() {
	}

	public CustomerOrderLineItemUIModel(FittingUIModel product, int amount) {
		this.product = product;
		this.amount = amount;
	}

	public CustomerOrderLineItemUIModel(CustomerOrderLineItem item) {
		this.product = new FittingUIModel(item.getFitting());
		this.amount = item.getAmount();
		this.delivered = item.isCompleted();
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public FittingUIModel getProduct() {
		return product;
	}

	public void setProduct(FittingUIModel product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}