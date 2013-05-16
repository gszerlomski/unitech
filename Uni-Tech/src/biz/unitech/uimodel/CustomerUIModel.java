package biz.unitech.uimodel;

import biz.unitech.datamodel.orders.Customer;

public class CustomerUIModel {

	private InputField customerName;
	private InputField customerStreet;
	private InputField customerHomeNr;
	private InputField customerPostCode;
	private InputField customerCity;
	
	public CustomerUIModel() {}
	
	public CustomerUIModel(Customer customer) {
		customerName = new InputField(customer.getCustomerName(), false);
		customerStreet = new InputField(customer.getCustomerStreet(), false);
		customerHomeNr = new InputField(customer.getCustomerHomeNr(), false);
		customerPostCode = new InputField(customer.getCustomerPostCode(), false);
		customerCity = new InputField(customer.getCustomerCity(), false);
	}

	public InputField getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(InputField customerName) {
		this.customerName = customerName;
	}
	
	public InputField getCustomerStreet() {
		return customerStreet;
	}
	
	public void setCustomerStreet(InputField customerStreet) {
		this.customerStreet = customerStreet;
	}
	
	public InputField getCustomerHomeNr() {
		return customerHomeNr;
	}
	
	public void setCustomerHomeNr(InputField customerHomeNr) {
		this.customerHomeNr = customerHomeNr;
	}
	
	public InputField getCustomerPostCode() {
		return customerPostCode;
	}
	
	public void setCustomerPostCode(InputField customerPostCode) {
		this.customerPostCode = customerPostCode;
	}
	
	public InputField getCustomerCity() {
		return customerCity;
	}
	
	public void setCustomerCity(InputField customerCity) {
		this.customerCity = customerCity;
	}
}