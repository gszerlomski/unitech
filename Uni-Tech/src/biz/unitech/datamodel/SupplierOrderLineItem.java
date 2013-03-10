package biz.unitech.datamodel;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import biz.unitech.dao.DuplicateEntryException;
import biz.unitech.uimodel.FittingUIModel;
import biz.unitech.uimodel.SupplierOrderLineItemUIModel;

@Entity
public class SupplierOrderLineItem {
	
	@Id
	@GeneratedValue
	int supplierOrderLineItemId;
	
	@ManyToOne
	@JoinColumn(name="orderId")
	SupplierOrder order;
	
	@ManyToOne(cascade = CascadeType.ALL)
	Fitting fitting;

	int amount;
	
	BigDecimal price;
	
	boolean completed;
	
	public SupplierOrderLineItem() {}

	public SupplierOrderLineItem(SupplierOrder order, Fitting fitting,
			int amount, BigDecimal price) {
		this.order = order;
		if( order != null && !order.getItems().contains(this)) {
			order.addOrderLineItem(this);
		}
		this.fitting = fitting;
		this.amount = amount;
		this.price = price;
	}


	public SupplierOrderLineItem(SupplierOrder order, SupplierOrderLineItemUIModel item) throws DuplicateEntryException {
		this.amount = item.getAmount();
		this.fitting = getFitting(item.getProduct());
		this.order = order;
		this.price = item.getTotalPrice();	
		this.completed = item.isDelivered();
	}

	public int getSupplierOrderLineItemId() {
		return supplierOrderLineItemId;
	}

	public void setSupplierOrderLineItemId(int supplierOrderLineItemId) {
		this.supplierOrderLineItemId = supplierOrderLineItemId;
	}

	public SupplierOrder getOrder() {
		return order;
	}

	public void setOrder(SupplierOrder order) {
		this.order = order;
	}

	public Fitting getFitting(FittingUIModel fittingUIModel) throws DuplicateEntryException {
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