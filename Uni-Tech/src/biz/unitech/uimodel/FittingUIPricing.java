package biz.unitech.uimodel;

import java.math.BigDecimal;

import biz.unitech.datamodel.fitting.Grip;
import biz.unitech.datamodel.orders.Customer;
import biz.unitech.datamodel.orders.CustomerPriceList;
import biz.unitech.datamodel.orders.Supplier;
import biz.unitech.datamodel.orders.SupplierPriceList;

public class FittingUIPricing {

	private InputField amount;

	private InputField fittingPrice;

	private InputField discountedPrice;

	private int gripNumber;

	private InputField gripPrice;

	private InputField lastPrice;

	private InputField lastPriceInvoice;

	public FittingUIPricing() {
	}

	public FittingUIPricing(InputField amount, InputField fittingPrice, InputField discountedPrice, int gripNumber, 
			InputField gripPrice, InputField lastPrice, InputField lastPriceInvoice) {

		this.amount = amount;
		this.fittingPrice = fittingPrice;
		this.discountedPrice = discountedPrice;
		this.gripNumber = gripNumber;
		this.gripPrice = gripPrice;
		this.lastPrice = lastPrice;
		this.lastPriceInvoice = lastPriceInvoice;
	}

	public FittingUIPricing(SupplierPriceList prices, Grip grip, Supplier supplier) {
		this.amount = new InputField("", false);
		this.fittingPrice = new InputField(prices.getStandardPrice().toPlainString(), true);
		this.discountedPrice = new InputField(prices.getStandardPrice().multiply(supplier.getDiscount()).stripTrailingZeros()
				.toPlainString(), true);
		this.gripNumber = prices.getFittingType().getGripNumber();
		this.gripPrice = new InputField(grip.getPrice().toPlainString(), true);
	}

	public FittingUIPricing(FittingUIPricing pricing) {
		this.amount = new InputField(pricing.amount.getValue(), pricing.amount.isDisabled());
		this.fittingPrice = new InputField(pricing.fittingPrice.getValue(), pricing.fittingPrice.isDisabled());
		this.discountedPrice = new InputField(pricing.discountedPrice.getValue(), pricing.getDiscountedPrice().isDisabled());
		this.gripNumber = pricing.gripNumber;
		this.gripPrice = new InputField(pricing.gripPrice.getValue(), pricing.gripPrice.isDisabled());
		this.lastPrice = new InputField(pricing.lastPrice.getValue(), pricing.lastPrice.isDisabled());
		this.lastPriceInvoice = new InputField(pricing.lastPriceInvoice.getValue(), pricing.lastPriceInvoice.isDisabled());
	}

	public FittingUIPricing(CustomerPriceList prices, Grip grip, Customer customer) {
		this.amount = new InputField("", false);
		this.fittingPrice = new InputField(prices.getStandardPrice().toPlainString(), true);
		this.discountedPrice = new InputField(prices.getStandardPrice().multiply(customer.getDiscount()).stripTrailingZeros()
				.toPlainString(), true);
		this.gripNumber = prices.getFittingType().getGripNumber();
		this.gripPrice = new InputField(grip.getPrice().toPlainString(), true);
		this.lastPrice = new InputField(prices.getLastPrice(), true);
		this.lastPriceInvoice = new InputField(prices.getLastPriceInvoice(), true);
	}

	public InputField getAmount() {
		return amount;
	}

	public void setAmount(InputField amount) {
		this.amount = amount;
	}

	public InputField getFittingPrice() {
		return fittingPrice;
	}

	public void setFittingPrice(InputField fittingPrice) {
		this.fittingPrice = fittingPrice;
	}

	public InputField getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(InputField discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public InputField getGripPrice() {
		return gripPrice;
	}

	public void setGripPrice(InputField gripPrice) {
		this.gripPrice = gripPrice;
	}

	public void enableEditableFields() {
		setDisabled(false);
	}

	public void disbleEditableFields() {
		setDisabled(true);
	}

	private void setDisabled(boolean disabled) {
		fittingPrice.setDisabled(disabled);
		gripPrice.setDisabled(disabled);
	}

	public BigDecimal getTotalPriceDiscounted() {
		BigDecimal amount = new BigDecimal(this.amount.getValue());
		BigDecimal discountedPrice = new BigDecimal(this.discountedPrice.getValue());
		BigDecimal gripNum = new BigDecimal(this.gripNumber);
		BigDecimal gripPrice = new BigDecimal(this.gripPrice.getValue());
		BigDecimal fittingsPrice = amount.multiply(discountedPrice);
		BigDecimal gripsPrice = amount.multiply(gripNum).multiply(gripPrice);

		return fittingsPrice.add(gripsPrice);
	}

	protected int getGripNumber() {
		return gripNumber;
	}

	public BigDecimal getSingleProductPrice() {
		BigDecimal discountedPrice = new BigDecimal(this.discountedPrice.getValue());
		BigDecimal gripNum = new BigDecimal(this.gripNumber);
		BigDecimal gripPrice = new BigDecimal(this.gripPrice.getValue());
		return discountedPrice.add(gripNum.multiply(gripPrice));
	}
}