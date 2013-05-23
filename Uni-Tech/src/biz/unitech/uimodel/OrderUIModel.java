package biz.unitech.uimodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class OrderUIModel {
	
	FittingDescUIModel fittingDesc = UIModelCreator.getFittingDescUIModel();
	
	FittingUIModel fitting;
	
	private Date creationDate;

	private Date estimatedDeliveryDate;

	private boolean completed;

	private Date completedDate;

	private String orderNumber;
	
	private int orderId;

	public OrderUIModel(FittingUIModel fitting) {
		this.fitting = fitting;
	}

	public FittingDescUIModel getFittingDesc() {
		return fittingDesc;
	}

	public void setFittingDesc(FittingDescUIModel fittingDesc) {
		this.fittingDesc = fittingDesc;
	}

	public FittingUIModel getFitting() {
		return fitting;
	}

	public void setFitting(FittingUIModel fitting) {
		this.fitting = fitting;
	}
	
	public String getCreationDateString() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return creationDate == null ? "" : df.format(creationDate);
	}

	public String getEstimatedDeliveryDateString() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return estimatedDeliveryDate == null ? "" : df.format(estimatedDeliveryDate);
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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
}
