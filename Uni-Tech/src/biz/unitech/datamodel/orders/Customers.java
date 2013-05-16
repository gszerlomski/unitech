package biz.unitech.datamodel.orders;

import java.util.LinkedList;
import java.util.List;

import biz.unitech.uimodel.CustomerUIModel;

public class Customers {
	
	private List<CustomerUIModel> list = new LinkedList<CustomerUIModel>();
	
	public Customers(List<CustomerUIModel> customers) {
		list = customers;
	}

	public void addCustomer(CustomerUIModel customer) {
		list.add(customer);
	}

	public List<CustomerUIModel> getList() {
		return list;
	}

	public void setList(List<CustomerUIModel> list) {
		this.list = list;
	}
}
