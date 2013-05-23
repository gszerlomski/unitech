package biz.unitech.datamodel.orders;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import biz.unitech.dao.DatabaseException;
import biz.unitech.dao.DuplicateEntryException;
import biz.unitech.datamodel.fitting.Fitting;
import biz.unitech.uimodel.CustomerOrderLineItemUIModel;
import biz.unitech.uimodel.FittingUIModel;

@Entity
public class CustomerOrderLineItem {
	
	@Id
	@GeneratedValue
	int customerOrderLineItemId;
	
	@ManyToOne
	@JoinColumn(name="orderId")
	CustomerOrder order;
	
	@ManyToOne(cascade = CascadeType.ALL)
	Fitting fitting;

	int amount;
	
	BigDecimal price;
	
	boolean completed;
	
	public CustomerOrderLineItem() {}

	public CustomerOrderLineItem(CustomerOrder order, Fitting fitting,
			int amount, BigDecimal price) {
		this.order = order;
		if( order != null && !order.getItems().contains(this)) {
			order.addOrderLineItem(this);
		}
		this.fitting = fitting;
		this.amount = amount;
		this.price = price;
	}

	public CustomerOrderLineItem(CustomerOrder order, CustomerOrderLineItemUIModel item) throws DuplicateEntryException, DatabaseException {
		this.amount = item.getAmount();
		this.fitting = getFitting(item.getProduct());
		this.order = order;
		this.price = item.getTotalPrice();	
		this.completed = item.isDelivered();
	}

	public int getCustomerOrderLineItemId() {
		return customerOrderLineItemId;
	}

	public void setCustomerOrderLineItemId(int customerOrderLineItemId) {
		this.customerOrderLineItemId = customerOrderLineItemId;
	}

	public CustomerOrder getOrder() {
		return order;
	}

	public void setOrder(CustomerOrder order) {
		this.order = order;
	}

	public Fitting getFitting(FittingUIModel fittingUIModel) throws DuplicateEntryException, DatabaseException {
		return Fitting.getFitting(fittingUIModel);
	}

	public void setFitting(Fitting fitting) {
		this.fitting = fitting;
	}
	
	public Fitting getFitting() {
		return fitting;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	 public boolean isCompleted() {
		return completed;
	}
	 
	 public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}