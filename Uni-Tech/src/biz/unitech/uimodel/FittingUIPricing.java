package biz.unitech.uimodel;

import java.math.BigDecimal;

import biz.unitech.controllers.FormValidationException;
import biz.unitech.dao.DuplicateEntryException;
import biz.unitech.datamodel.FittingType;
import biz.unitech.datamodel.Grip;
import biz.unitech.datamodel.PriceList;
import biz.unitech.datamodel.Supplier;
import biz.unitech.datamodel.TubeDim;

public class FittingUIPricing {

	private InputField amount;

	private InputField fittingPrice;

	private InputField discountedPrice;

	private int gripNumber;

	private InputField gripPrice;

	public FittingUIPricing() {
	}

	public FittingUIPricing(InputField amount, InputField fittingPrice, InputField discountedPrice, int gripNumber, InputField gripPrice) {

		this.amount = amount;
		this.fittingPrice = fittingPrice;
		this.discountedPrice = discountedPrice;
		this.gripNumber = gripNumber;
		this.gripPrice = gripPrice;
	}

	public FittingUIPricing(PriceList prices, Grip grip, Supplier supplier) {
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