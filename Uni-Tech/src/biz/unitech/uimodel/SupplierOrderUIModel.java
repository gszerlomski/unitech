package biz.unitech.uimodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import biz.unitech.controllers.FormValidationException;
import biz.unitech.datamodel.Supplier;

public class SupplierOrderUIModel {
	
	private int orderId;

	private Supplier supplier;

	private List<SupplierOrderLineItemUIModel> lineItems;
	
	private Date creationDate;
	
	private Date estimatedDeliveryDate;
	
	private boolean completed;
	
	//TODO: Display sum of all costs in order summary. 
    //It might be good to update sum whenever order line item is added to supplierOrderModel. 

	public SupplierOrderUIModel(Supplier supplier) {
		lineItems = getLineItems();
		this.supplier = supplier;
		this.creationDate = Calendar.getInstance().getTime();
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getSupplierName() {
		return supplier.getSupplierName() + "  -  " + supplier.getSupplierNIP();
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
	
	public String getCreationDateString() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return creationDate == null? "" : df.format(creationDate);
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
}