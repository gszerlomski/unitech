package biz.unitech.datamodel;

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
import biz.unitech.uimodel.OrderUIModel;
import biz.unitech.uimodel.SupplierOrderLineItemUIModel;
import biz.unitech.uimodel.SupplierOrderUIModel;

@Entity
public class SupplierOrder {

	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<SupplierOrderLineItem> items;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "supplierId")
	private Supplier supplier;

	@Id
	@GeneratedValue
	private int orderId;

	private Date orderDate;
	
	private Date deliveryDate;
	
	private Date completedDate;
	
	private boolean completed;
	
	private String orderNumber;

	public SupplierOrder() {
	}

	public SupplierOrder(Set<SupplierOrderLineItem> items, Supplier supplier,
			Date orderDate, Date completedDate, String orderNumber) {
		if (items != null) {
			for (SupplierOrderLineItem item : items) {
				addOrderLineItem(item);
			}
		} else {
			items = new HashSet<SupplierOrderLineItem>();
		}
		this.supplier = supplier;
		this.orderDate = orderDate;
		this.completedDate = completedDate;
		this.orderNumber = orderNumber;
	}
	
	public SupplierOrder(Supplier supplier) {
		this.items = new HashSet<SupplierOrderLineItem>();
		this.supplier = supplier;
		this.orderDate = Calendar.getInstance().getTime();
	}

	public SupplierOrder(OrderUIModel orderModel) throws DuplicateEntryException, NumberFormatException, DatabaseException {
		this(orderModel.getSupplierOrderModel());
	}

	public SupplierOrder(SupplierOrderUIModel model) throws DuplicateEntryException, NumberFormatException, DatabaseException {
		orderId = model.getOrderId();
		completed = model.isCompleted();
		completedDate = model.getCompletedDate() == null ? Calendar.getInstance().getTime() : model.getCompletedDate();
		orderDate = model.getCreationDate();
		deliveryDate = model.getEstimatedDeliveryDate();
		supplier = model.getSupplier();
		orderNumber = model.getOrderNumber();
		
		if(items == null) {
			items = new HashSet<SupplierOrderLineItem>();
		}
		
		for (SupplierOrderLineItemUIModel item : model.getLineItems()) {
			addOrderLineItem(item);
		}
	}

	private void addOrderLineItem(SupplierOrderLineItemUIModel item) throws NumberFormatException, DuplicateEntryException, DatabaseException {
		items.add(new SupplierOrderLineItem(this, item));
	}

	public void addOrderLineItem(SupplierOrderLineItem item) {
		items.add(item);
		if (item.getOrder() != null
				&& item.getOrder().getOrderId() != this.getOrderId()) {
			throw new RuntimeException("Order " + item.order.getOrderId()
					+ " cannot be overwritten with " + this.getOrderId());
		}
		item.setOrder(this);
	}

	public Set<SupplierOrderLineItem> getItems() {
		return items;
	}

	public void setItems(Set<SupplierOrderLineItem> items) {
		this.items = items;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	
	public void uploadFiles() {
		
	}
}