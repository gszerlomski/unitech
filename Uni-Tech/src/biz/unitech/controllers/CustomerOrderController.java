package biz.unitech.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import biz.unitech.dao.CustomerDao;
import biz.unitech.dao.DatabaseException;
import biz.unitech.dao.DuplicateEntryException;
import biz.unitech.datamodel.fitting.Grip;
import biz.unitech.datamodel.orders.Customer;
import biz.unitech.datamodel.orders.Customers;
import biz.unitech.uimodel.CustomerOrderUIModel;
import biz.unitech.uimodel.CustomerUIModel;
import biz.unitech.uimodel.FittingUIModel;
import biz.unitech.uimodel.FittingUIPricing;
import biz.unitech.uimodel.OrderUIModel;
import biz.unitech.uimodel.UIModelCreator;

@Controller
@SessionAttributes(types = { Customers.class, CustomerOrderUIModel.class, FittingUIPricing.class })
public class CustomerOrderController extends GenericController {

	@ModelAttribute("customers")
	public Customers getCustomers() {
		List<Customer> list = CustomerDao.getAllCustomers();
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
		model.addAttribute("customer", null);
		return new ModelAndView("jsp_new/selectCustomer.jsp");
	}

	@RequestMapping(value = "cancelCustomer.htm", method = RequestMethod.GET)
	public ModelAndView cancelCustomer(Model model) {
		model.addAttribute("customer", null);
		return new ModelAndView("jsp_new/selectCustomer.jsp");
	}

	@RequestMapping(value = "selectCustomer.htm", method = RequestMethod.GET, params = "customerName")
	public ModelAndView selectCustomer(Model model, @RequestParam("customerName") String customerName) {
		List<Customer> customers = CustomerDao.getCustomerByName(customerName);
		Customer customer = null;
		if (customers.size() > 0) {
			customer = customers.get(0);
		}
		CustomerOrderUIModel order = createNewCustomerOrderModel(customer);
		model.addAttribute("orderModel", order);
		return new ModelAndView("jsp_new/createCustomerOrder.jsp");
	}

	@RequestMapping(value = "addNewCustomerProduct.htm", method = RequestMethod.POST)
	public ModelAndView addNewCustomerProduct(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel) {

		addNewProduct(model, orderModel);

		return new ModelAndView("jsp_new/createCustomerOrder.jsp");
	}

	@RequestMapping(value = "addNewCustomerProduct.htm", method = RequestMethod.POST, params = "chosenFittingType")
	public ModelAndView addNewCustomerProduct(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel,
			@RequestParam("chosenFittingType") String chosenType) {

		addNewProduct(model, orderModel, chosenType);

		return new ModelAndView("jsp_new/createCustomerOrder.jsp");
	}

	@RequestMapping(value = "newCustomerProduct.htm", method = RequestMethod.POST)
	public ModelAndView newCustomerProduct(Model model, @ModelAttribute("orderModel") CustomerOrderUIModel orderModel) {
		try {

			FittingUIPricing uiPricing = getFittingUIPricing(orderModel.getFitting(), orderModel.getCustomer());

			orderModel.getFitting().setDisabledInputs(true);
			orderModel.getFitting().setPricing(uiPricing);
		} catch (FormValidationException e) {
			registerError(model, e);
		} catch (DuplicateEntryException e) {
			registerError(model, e);
		}

		return new ModelAndView("jsp_new/createCustomerOrder.jsp");
	}

	private FittingUIPricing getFittingUIPricing(FittingUIModel fitting, Customer customer) throws FormValidationException,
			DuplicateEntryException {

		Grip grip = getGripByName(fitting.getGrip().getValue());

		return new FittingUIPricing(getPriceList(fitting, grip, customer), grip, customer);
	}

	private CustomerOrderUIModel createNewCustomerOrderModel(Customer customer) {
		return UIModelCreator.getNewCustomerOrderUIModel(customer);
	}
}