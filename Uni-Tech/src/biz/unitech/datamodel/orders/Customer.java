package biz.unitech.datamodel.orders;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import biz.unitech.uimodel.CustomerUIModel;

@Entity
public class Customer {

	@Id
	@GeneratedValue
	int customerId;
	
	private String customerName;
	private String customerStreet;
	private String customerHomeNr;
	private String customerPostCode;
	private String customerCity;
	
	public Customer() {}
	
	public Customer(String customerName, String customerStreet, String customerHomeNr, String customerPostCode,
			String customerCity, BigDecimal customerMargin) {
		this.customerName = customerName;
		this.customerStreet = customerStreet;
		this.customerHomeNr = customerHomeNr;
		this.customerPostCode = customerPostCode;
		this.customerCity = customerCity;
	}

	public Customer(CustomerUIModel customerModel) {
		customerName = customerModel.getCustomerName().getValue();
		customerStreet = customerModel.getCustomerStreet().getValue();
		customerHomeNr = customerModel.getCustomerHomeNr().getValue();
		customerPostCode = customerModel.getCustomerPostCode().getValue();
		customerCity = customerModel.getCustomerCity().getValue();
	}
	
	private BigDecimal percentToDiscount(String value) {
		return percentToDiscount(new BigDecimal(value));
	}
	
	private BigDecimal percentToDiscount(BigDecimal discount) {
		BigDecimal percent = discount.divide(new BigDecimal(100));
		return new BigDecimal(1).add(percent);
	}

	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerStreet() {
		return customerStreet;
	}
	
	public void setCustomerStreet(String customerStreet) {
		this.customerStreet = customerStreet;
	}
	
	public String getCustomerHomeNr() {
		return customerHomeNr;
	}
	
	public void setCustomerHomeNr(String customerHomeNr) {
		this.customerHomeNr = customerHomeNr;
	}
	
	public String getCustomerPostCode() {
		return customerPostCode;
	}
	
	public void setCustomerPostCode(String customerPostCode) {
		this.customerPostCode = customerPostCode;
	}
	
	public String getCustomerCity() {
		return customerCity;
	}
	
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}
}