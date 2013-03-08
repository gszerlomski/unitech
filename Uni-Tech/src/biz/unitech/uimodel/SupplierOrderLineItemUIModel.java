package biz.unitech.uimodel;

import java.math.BigDecimal;

import biz.unitech.datamodel.PriceList;
import biz.unitech.datamodel.SupplierOrderLineItem;

public class SupplierOrderLineItemUIModel {

	private FittingUIModel product;

	private int amount;

	private BigDecimal totalPrice;

	private BigDecimal singleProductPrice;

	private boolean delivered;

	public SupplierOrderLineItemUIModel() {
	}

	public SupplierOrderLineItemUIModel(FittingUIModel product, int amount, BigDecimal totalPrice, BigDecimal singleItemPrice) {
		this.product = product;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.singleProductPrice = singleItemPrice;
	}

	public SupplierOrderLineItemUIModel(SupplierOrderLineItem item) {
		this.product = new FittingUIModel(item.getFitting());
		this.amount = item.getAmount();
		this.totalPrice = item.getPrice();
		this.delivered = item.isCompleted();

		FittingUIPricing pricing = new FittingUIPricing(PriceList.getInstance(item.getFitting().getFittingType(), item.getFitting()
				.getTubeDim()), item.getFitting().getGrip(), item.getOrder().getSupplier());

		this.singleProductPrice = pricing.getSingleProductPrice();
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

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getSingleProductPrice() {
		return singleProductPrice;
	}

	public void setSingleProductPrice(BigDecimal singleProductPrice) {
		this.singleProductPrice = singleProductPrice;
	}
}
