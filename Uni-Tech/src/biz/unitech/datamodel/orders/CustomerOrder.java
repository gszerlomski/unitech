package biz.unitech.datamodel.orders;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import biz.unitech.dao.DatabaseException;
import biz.unitech.dao.DuplicateEntryException;
import biz.unitech.uimodel.CustomerOrderLineItemUIModel;
import biz.unitech.uimodel.CustomerOrderUIModel;

@Entity
public class CustomerOrder {

	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<CustomerOrderLineItem> items;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId")
	private Customer customer;

	@Id
	@GeneratedValue
	private int customerOrderId;

	private Date orderDate;
	
	private Date deliveryDate;
	
	private Date completedDate;
	
	private boolean completed;
	
	private String orderNumber;

	public CustomerOrder() {
	}

	public CustomerOrder(Set<CustomerOrderLineItem> items, Customer customer,
			Date orderDate, Date completedDate, String orderNumber) {
		if (items != null) {
			for (CustomerOrderLineItem item : items) {
				addOrderLineItem(item);
			}
		} else {
			items = new HashSet<CustomerOrderLineItem>();
		}
		this.customer = customer;
		this.orderDate = orderDate;
		this.completedDate = completedDate;
		this.orderNumber = orderNumber;
	}
	
	public CustomerOrder(Customer customer) {
		this.items = new HashSet<CustomerOrderLineItem>();
		this.customer = customer;
		this.orderDate = Calendar.getInstance().getTime();
	}

	public CustomerOrder(CustomerOrderUIModel model) throws DuplicateEntryException, NumberFormatException, DatabaseException {
		customerOrderId = model.getOrderId();
		completed = model.isCompleted();
		completedDate = model.getCompletedDate() == null ? Calendar.getInstance().getTime() : model.getCompletedDate();
		orderDate = model.getCreationDate();
		deliveryDate = model.getEstimatedDeliveryDate();
		customer = model.getCustomer();
		orderNumber = model.getOrderNumber();
		
		if(items == null) {
			items = new HashSet<CustomerOrderLineItem>();
		}
		
		for (CustomerOrderLineItemUIModel item : model.getLineItems()) {
			addOrderLineItem(item);
		}
	}

	private void addOrderLineItem(CustomerOrderLineItemUIModel item) throws NumberFormatException, DuplicateEntryException, DatabaseException {
		items.add(new CustomerOrderLineItem(this, item));
	}

	public void addOrderLineItem(CustomerOrderLineItem item) {
		items.add(item);
		if (item.getOrder() != null
				&& item.getOrder().getCustomerOrderId() != this.getCustomerOrderId()) {
			throw new RuntimeException("Order " + item.order.getCustomerOrderId()
					+ " cannot be overwritten with " + this.getCustomerOrderId());
		}
		item.setOrder(this);
	}

	public Set<CustomerOrderLineItem> getItems() {
		return items;
	}

	public void setItems(Set<CustomerOrderLineItem> items) {
		this.items = items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(int orderId) {
		this.customerOrderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public Date getCompletedDate() {
		return completedDate;
	}
	
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
}