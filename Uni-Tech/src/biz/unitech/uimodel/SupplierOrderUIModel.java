package biz.unitech.uimodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import biz.unitech.controllers.FormValidationException;
import biz.unitech.datamodel.orders.Supplier;
import biz.unitech.datamodel.orders.SupplierOrder;
import biz.unitech.datamodel.orders.SupplierOrderLineItem;

public class SupplierOrderUIModel  extends OrderUIModel {

	private Supplier supplier;

	private List<SupplierOrderLineItemUIModel> lineItems;


	public SupplierOrderUIModel(Supplier supplier, FittingUIModel fitting) {
		super(fitting);
		lineItems = getLineItems();
		this.supplier = supplier;
		setCreationDate(Calendar.getInstance().getTime());
	}

	public SupplierOrderUIModel(SupplierOrder supplierOrder, FittingUIModel fitting) {
		super(fitting);
		setOrderId(supplierOrder.getSupplierOrderId());
		this.supplier = supplierOrder.getSupplier();
		this.lineItems = convertItemsList(supplierOrder.getItems());
		setCreationDate(supplierOrder.getOrderDate());
		setEstimatedDeliveryDate(supplierOrder.getDeliveryDate());
		setCompleted(supplierOrder.isCompleted());
		setCompletedDate(supplierOrder.getCompletedDate());
		setOrderNumber(supplierOrder.getOrderNumber());
	}

	private List<SupplierOrderLineItemUIModel> convertItemsList(Set<SupplierOrderLineItem> items) {
		List<SupplierOrderLineItemUIModel> result = new ArrayList<SupplierOrderLineItemUIModel>(items.size());

		for (SupplierOrderLineItem item : items) {
			result.add(new SupplierOrderLineItemUIModel(item));
		}
		return result;
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
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void addLineItem(FittingUIModel fitting, FittingUIPricing pricing) throws FormValidationException {
		try {
			SupplierOrderLineItemUIModel item = new SupplierOrderLineItemUIModel(fitting, Integer.parseInt(pricing.getAmount().getValue()),
					pricing.getTotalPriceDiscounted(), pricing.getSingleProductPrice());
			lineItems.add(item);
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
		return getTotalPrice(lineItems);
	}

	private BigDecimal getTotalPrice(List<SupplierOrderLineItemUIModel> lineItems) {
		BigDecimal temp = new BigDecimal(0);
		for (SupplierOrderLineItemUIModel elem : lineItems) {
			temp = temp.add(elem.getTotalPrice());
		}
		return temp;
	}

	private List<SupplierOrderLineItemUIModel> getItemsPerCompletion(boolean completed) {
		List<SupplierOrderLineItemUIModel> result = new LinkedList<SupplierOrderLineItemUIModel>();
		for (SupplierOrderLineItemUIModel item : lineItems) {
			if (item.isDelivered() == completed) {
				result.add(item);
			}
		}
		return result;
	}

	public List<SupplierOrderLineItemUIModel> getNotCompletedLineItems() {
		return getItemsPerCompletion(false);
	}
	
	public BigDecimal getNotCompletedLineItemsTotalPrice() {
		return getTotalPrice(getNotCompletedLineItems());
	}

	public List<SupplierOrderLineItemUIModel> getCompletedLineItems() {
		return getItemsPerCompletion(true);
	}
	
	public BigDecimal getCompletedLineItemsTotalPrice() {
		return getTotalPrice(getCompletedLineItems());
	}

	public void clearLineItems() {
		lineItems = new ArrayList<SupplierOrderLineItemUIModel>();
	}
}