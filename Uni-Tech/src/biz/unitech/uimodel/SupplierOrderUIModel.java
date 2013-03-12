package biz.unitech.uimodel;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import biz.unitech.controllers.FormValidationException;
import biz.unitech.datamodel.Supplier;
import biz.unitech.datamodel.SupplierOrder;
import biz.unitech.datamodel.SupplierOrderLineItem;

public class SupplierOrderUIModel {

	private int orderId;

	private Supplier supplier;

	private List<SupplierOrderLineItemUIModel> lineItems;

	private Date creationDate;

	private Date estimatedDeliveryDate;

	private boolean completed;

	private Date completedDate;
	
	/**
	 * Order Total Price
	 */
	private BigDecimal totalPrice;

	// TODO: Display sum of all costs in order summary.
	// It might be good to update sum whenever order line item is added to
	// supplierOrderModel.

	public SupplierOrderUIModel(Supplier supplier) {
		lineItems = getLineItems();
		this.supplier = supplier;
		this.creationDate = Calendar.getInstance().getTime();
		updateTotalPrice();
	}

	public SupplierOrderUIModel(SupplierOrder supplierOrder) {
		this.orderId = supplierOrder.getOrderId();
		this.supplier = supplierOrder.getSupplier();
		this.lineItems = convertItemsList(supplierOrder.getItems());
		this.creationDate = supplierOrder.getOrderDate();
		this.estimatedDeliveryDate = supplierOrder.getDeliveryDate();
		this.completed = supplierOrder.isCompleted();
		this.completedDate = supplierOrder.getCompletedDate();
	}

	private List<SupplierOrderLineItemUIModel> convertItemsList(Set<SupplierOrderLineItem> items) {
		List<SupplierOrderLineItemUIModel> result = new ArrayList<SupplierOrderLineItemUIModel>(items.size());
		
		for (SupplierOrderLineItem item : items) {
			result.add(new SupplierOrderLineItemUIModel(item));
		}
		return result;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getSupplierName() {
		return supplier.getSupplierName();
	}

	public List<SupplierOrderLineItemUIModel> getLineItems() {

		if (lineItems == null) {
			lineItems = new ArrayList<SupplierOrderLineItemUIModel>();
		}
		return lineItems;
	}

	public void setLineItems(List<SupplierOrderLineItemUIModel> items) {
		this.lineItems = items;
		updateTotalPrice();
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getCreationDateString() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return creationDate == null ? "" : df.format(creationDate);
	}

	public String getEstimatedDeliveryDateString() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return estimatedDeliveryDate == null? "" : df.format(estimatedDeliveryDate);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Date getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setCreationDateString(String creationDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.creationDate = df.parse(creationDate);
	}
	
	public void setEstimatedDeliveryDateString(String estimatedDeliveryDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.estimatedDeliveryDate = df.parse(estimatedDeliveryDate);
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public Date getCompletedDate() {
		return completedDate;
	}
	
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public void addLineItem(FittingUIModel fitting, FittingUIPricing pricing) throws FormValidationException {
		try {
		SupplierOrderLineItemUIModel item = new SupplierOrderLineItemUIModel(fitting, Integer.parseInt(pricing.getAmount().getValue()),
				pricing.getTotalPriceDiscounted(), pricing.getSingleProductPrice());
			lineItems.add(item);
			updateTotalPrice();
		} catch (NumberFormatException e) {
			throw new FormValidationException("Nieprawidłowa ilość produktów, lub liczba nie jest całkowita, podano \"" 
							+ pricing.getAmount().getValue() + "\"");
		}
	}
	
	public void setLineItemsCompletion(boolean completion) {
		for (SupplierOrderLineItemUIModel item : lineItems) {
			item.setDelivered(completion);
		}
	}
	
	public boolean getLineItemsCompletion() {
		boolean result = true;
		for (SupplierOrderLineItemUIModel item : lineItems) {
			result = result && item.isDelivered();
		}
		return result;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	private void updateTotalPrice() {
		BigDecimal temp = new BigDecimal(0);
		for (SupplierOrderLineItemUIModel elem : lineItems) {

			temp = temp.add(elem.getTotalPrice());
		}
		totalPrice = temp;
	}
}