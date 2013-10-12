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
	
	private InputField pricelistFittingPrice;

	private InputField discountedPrice;
	
	private InputField priceWithMargin;

	private int gripNumber;

	private InputField gripPrice;

	private InputField customerFittingPrice;
	
	private InputField lastInvoiceFittingPrice;

	private InputField lastPriceInvoice;

	public FittingUIPricing() {
	}

	public FittingUIPricing(InputField amount, InputField pricelistFittingPrice, InputField fittingPrice, InputField discountedPrice, int gripNumber,
			InputField gripPrice, InputField lastInvoiceFittingPrice, InputField lastPriceInvoice, InputField customerFittingPrice) {

		this.amount = amount;
		this.pricelistFittingPrice = pricelistFittingPrice;
		this.discountedPrice = discountedPrice;
		this.gripNumber = gripNumber;
		this.gripPrice = gripPrice;
		this.lastInvoiceFittingPrice = lastInvoiceFittingPrice;
		this.customerFittingPrice = customerFittingPrice;
		this.lastPriceInvoice = lastPriceInvoice;
		this.fittingPrice = fittingPrice;
	}

	public FittingUIPricing(SupplierPriceList prices, Grip grip, Supplier supplier) {
		this.amount = new InputField("", false);
		this.pricelistFittingPrice = new InputField(prices.getStandardPrice().toPlainString(), true);
		BigDecimal discountedPrice = prices.getStandardPrice().multiply(supplier.getDiscount()).stripTrailingZeros();
		this.discountedPrice = new InputField(discountedPrice.toPlainString(), true);
		this.gripNumber = prices.getFittingType().getGripNumber();
		this.gripPrice = new InputField(grip.getPrice().toPlainString(), true);
		this.fittingPrice = new InputField(discountedPrice.add(grip.getPrice().multiply(BigDecimal.valueOf(gripNumber))).toPlainString(), 
				false);
	}

	public FittingUIPricing(FittingUIPricing pricing) {
		this.amount = new InputField(pricing.amount.getValue(), pricing.amount.isDisabled());
		this.pricelistFittingPrice = new InputField(pricing.pricelistFittingPrice.getValue(), pricing.pricelistFittingPrice.isDisabled());
		this.fittingPrice = new InputField(pricing.fittingPrice.getValue(), pricing.fittingPrice.isDisabled());
		this.discountedPrice = new InputField(pricing.discountedPrice.getValue(), pricing.getDiscountedPrice().isDisabled());
		this.gripNumber = pricing.gripNumber;
		this.gripPrice = new InputField(pricing.gripPrice.getValue(), pricing.gripPrice.isDisabled());
		if (pricing.lastInvoiceFittingPrice != null) {
			this.lastInvoiceFittingPrice = new InputField(pricing.lastInvoiceFittingPrice.getValue(), pricing.lastInvoiceFittingPrice.isDisabled());
		}
		if (pricing.lastPriceInvoice != null) {
			this.lastPriceInvoice = new InputField(pricing.lastPriceInvoice.getValue(), pricing.lastPriceInvoice.isDisabled());
		}
		if (pricing.priceWithMargin != null) {
			this.priceWithMargin = new InputField(pricing.priceWithMargin.getValue(), pricing.priceWithMargin.isDisabled());
		}
		if (pricing.customerFittingPrice != null) {
			this.customerFittingPrice = new InputField(pricing.customerFittingPrice.getValue(), pricing.customerFittingPrice.isDisabled());
		}
	}

	public FittingUIPricing(SupplierPriceList sPrices, CustomerPriceList cPrices, Grip grip, Customer customer) {
		this.amount = new InputField("", false);
		this.pricelistFittingPrice = new InputField(sPrices.getStandardPrice().toPlainString(), true);
		this.fittingPrice = new InputField("", false);
		this.gripNumber = cPrices.getFittingType().getGripNumber();
		this.gripPrice = new InputField(grip.getPrice().toPlainString(), true);
		this.customerFittingPrice = new InputField(cPrices.getCustomerPrice().toPlainString(), true);
		String orderNumber = cPrices.getCustomerOrder() == null ? "" : cPrices.getCustomerOrder().getOrderNumber();
		this.lastPriceInvoice = new InputField(orderNumber, true);
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
	
	public InputField getPricelistFittingPrice() {
		return pricelistFittingPrice;
	}
	
	public void setPricelistFittingPrice(InputField pricelistFittingPrice) {
		this.pricelistFittingPrice = pricelistFittingPrice;
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
	
	public InputField getlastInvoiceFittingPrice() {
		return lastInvoiceFittingPrice;
	}
	
	public void setlastInvoiceFittingPrice(InputField lastInvoiceFittingPrice) {
		this.lastInvoiceFittingPrice = lastInvoiceFittingPrice;
	}
	
	public InputField getCustomerFittingPrice() {
		return customerFittingPrice;
	}
	
	public void setCustomerFittingPrice(InputField customerFittingPrice) {
		this.customerFittingPrice = customerFittingPrice;
	}
	
	public InputField getLastPriceInvoice() {
		return lastPriceInvoice;
	}
	
	public void setLastPriceInvoice(InputField lastPriceInvoice) {
		this.lastPriceInvoice = lastPriceInvoice;
	}
	
	public void setGripNumber(int gripNumber) {
		this.gripNumber = gripNumber;
	}

	public void enableEditableFields() {
		setDisabled(false);
	}

	public void disbleEditableFields() {
		setDisabled(true);
	}

	private void setDisabled(boolean disabled) {
		pricelistFittingPrice.setDisabled(disabled);
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