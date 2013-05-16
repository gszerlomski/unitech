package biz.unitech.uimodel;

public class CustomerUIModel {

	private InputField customerName;
	private InputField customerStreet;
	private InputField customerHomeNr;
	private InputField customerPostCode;
	private InputField customerCity;
	
	
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