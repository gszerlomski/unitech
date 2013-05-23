package biz.unitech.dao;

import java.util.ArrayList;
import java.util.List;

import biz.unitech.datamodel.orders.Customer;

public class CustomerDao extends Dao {

	public static List<Customer> getAllCustomers() {
		List<Object> customers = DatabaseUtils.getAll(Customer.class);
		DAOConverter<Customer> customerConverter = new DAOConverter<Customer>();
		return customerConverter.convertToList(customers);
	}

	public static List<Customer> getCustomerByName(String customerName) {
		List<Object> list = DatabaseUtils.findByParam(Customer.class, new Param[] { 
			Param.getInstance("customerName", customerName) });
		List<Customer> customers = new ArrayList<Customer>(list.size());
		for (Object object : list) {
			customers.add((Customer) object);
		}
		return customers;
	}
}