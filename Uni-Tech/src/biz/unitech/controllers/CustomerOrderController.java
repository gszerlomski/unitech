package biz.unitech.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import biz.unitech.dao.CustomerDao;
import biz.unitech.dao.DatabaseException;
import biz.unitech.datamodel.orders.Customer;
import biz.unitech.datamodel.orders.Customers;
import biz.unitech.uimodel.CustomerUIModel;

@Controller
@SessionAttributes(types = { Customers.class, Customer.class })
public class CustomerOrderController extends GenericController {

	@ModelAttribute("customers")
	public Customers getCustomers() {
		List<Customer> list = CustomerDao.getCustomers();
		List<CustomerUIModel> converted = convertToUIList(list);
		return new Customers(converted);
	}

	@RequestMapping(value = "createCustomerOrder.htm", method = RequestMethod.GET)
	public ModelAndView createCustomerOrder(Model model) {
		model.addAttribute("customers", getCustomers());

		return new ModelAndView("jsp_new/selectCustomer.jsp");
	}

	private List<CustomerUIModel> convertToUIList(List<Customer> list) {
		List<CustomerUIModel> result = new ArrayList<CustomerUIModel>(list.size());
		for (Customer customer : list) {
			result.add(new CustomerUIModel(customer));
		}
		return result;
	}

	@RequestMapping(value = "createCustomer.htm", method = RequestMethod.GET)
	public ModelAndView createCustomer(Model model) {

		model.addAttribute("customer", new CustomerUIModel());

		return new ModelAndView("jsp_new/selectCustomer.jsp");
	}

	@RequestMapping(value = "saveCustomer.htm", method = RequestMethod.POST)
	public ModelAndView saveCustomer(Model model, @ModelAttribute("customer") CustomerUIModel customer) {

		try {
			CustomerDao.saveOrUpdate(new Customer(customer));
		} catch (DatabaseException e) {
			registerError(model, e);
		}
		model.addAttribute("customers", getCustomers());
		
		return new ModelAndView("jsp_new/selectCustomer.jsp");
	}

	@RequestMapping(value = "cancelCustomer.htm", method = RequestMethod.GET)
	public ModelAndView cancelCustomer(Model model) {

		model.addAttribute("customer", null);

		return new ModelAndView("jsp_new/selectCustomer.jsp");
	}
}