package biz.unitech.uimodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

import biz.unitech.controllers.FormValidationException;
import biz.unitech.datamodel.orders.Customer;
import biz.unitech.datamodel.orders.CustomerOrder;
import biz.unitech.datamodel.orders.CustomerOrderLineItem;

public class CustomerOrderUIModel  extends OrderUIModel {

	public static final String VARIABLE_NAME = "customerOrderModel";
	
	private Customer customer;

	private List<CustomerOrderLineItemUIModel> lineItems;

	public CustomerOrderUIModel(Customer customer, FittingUIModel fitting) {
		super(fitting);
		lineItems = getLineItems();
		this.customer = customer;
		setCreationDate(Calendar.getInstance().getTime());
	}

	public CustomerOrderUIModel(CustomerOrder customerOrder, FittingUIModel fitting) {
		super(fitting);
		setOrderId(customerOrder.getCustomerOrderId());
		this.customer = customerOrder.getCustomer();
		this.lineItems = convertItemsList(customerOrder.getItems());
		setCreationDate(customerOrder.getOrderDate());
		setEstimatedDeliveryDate(customerOrder.getDeliveryDate());
		setCompleted(customerOrder.isCompleted());
		setCompletedDate(customerOrder.getCompletedDate());
		setOrderNumber(customerOrder.getOrderNumber());
	}

	private List<CustomerOrderLineItemUIModel> convertItemsList(Set<CustomerOrderLineItem> items) {
		List<CustomerOrderLineItemUIModel> result = new ArrayList<CustomerOrderLineItemUIModel>(items.size());

		for (CustomerOrderLineItem item : items) {
			result.add(new CustomerOrderLineItemUIModel(item));
		}
		return result;
	}

	public String getCustomerName() {
		return customer.getCustomerName();
	}

	public List<CustomerOrderLineItemUIModel> getLineItems() {

		if (lineItems == null) {
			lineItems = new ArrayList<CustomerOrderLineItemUIModel>();
		}
		return lineItems;
	}

	public void setLineItems(List<CustomerOrderLineItemUIModel> items) {
		this.lineItems = items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void addLineItem(FittingUIModel fitting, FittingUIPricing pricing) throws FormValidationException {
		try {
			CustomerOrderLineItemUIModel item = new CustomerOrderLineItemUIModel(fitting, Integer.parseInt(pricing.getAmount().getValue()),
					pricing.getTotalPriceDiscounted(), pricing.getSingleProductPrice());
			lineItems.add(item);
		} catch (NumberFormatException e) {
			throw new FormValidationException("Nieprawidłowa ilość produktów, lub liczba nie jest całkowita, podano \""
					+ pricing.getAmount().getValue() + "\"");
		}
	}

	public void setLineItemsCompletion(boolean completion) {
		for (CustomerOrderLineItemUIModel item : lineItems) {
			item.setDelivered(completion);
		}
	}

	public boolean getLineItemsCompletion() {
		boolean result = true;
		for (CustomerOrderLineItemUIModel item : lineItems) {
			result = result && item.isDelivered();
		}
		return result;
	}

	public BigDecimal getTotalPrice() {
		return getTotalPrice(lineItems);
	}

	private BigDecimal getTotalPrice(List<CustomerOrderLineItemUIModel> lineItems) {
		BigDecimal temp = new BigDecimal(0);
		for (CustomerOrderLineItemUIModel elem : lineItems) {
			temp = temp.add(elem.getTotalPrice());
		}
		return temp;
	}

	private List<CustomerOrderLineItemUIModel> getItemsPerCompletion(boolean completed) {
		List<CustomerOrderLineItemUIModel> result = new LinkedList<CustomerOrderLineItemUIModel>();
		for (CustomerOrderLineItemUIModel item : lineItems) {
			if (item.isDelivered() == completed) {
				result.add(item);
			}
		}
		return result;
	}

	public List<CustomerOrderLineItemUIModel> getNotCompletedLineItems() {
		return getItemsPerCompletion(false);
	}
	
	public BigDecimal getNotCompletedLineItemsTotalPrice() {
		return getTotalPrice(getNotCompletedLineItems());
	}

	public List<CustomerOrderLineItemUIModel> getCompletedLineItems() {
		return getItemsPerCompletion(true);
	}
	
	public BigDecimal getCompletedLineItemsTotalPrice() {
		return getTotalPrice(getCompletedLineItems());
	}

	public void clearLineItems() {
		lineItems = new ArrayList<CustomerOrderLineItemUIModel>();
	}
}