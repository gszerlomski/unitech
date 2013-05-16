package biz.unitech.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import biz.unitech.dao.CustomerDao;
import biz.unitech.uimodel.CustomerUIModel;

@Controller
public class CustomerOrderController {

	private Logger logger = Logger.getLogger(getClass());

	
	@RequestMapping(value = "createCustomerOrder.htm", method = RequestMethod.GET)
	public ModelAndView createCustomerOrder(Model model) {
		return new ModelAndView("jsp_new/selectCustomer.jsp");
	}
	
	@RequestMapping(value = "createCustomer.htm", method = RequestMethod.GET)
	public ModelAndView createCustomer(Model model) {

		model.addAttribute("customer", new CustomerUIModel());
		
		return new ModelAndView("jsp_new/selectCustomer.jsp");
	}
	
	@RequestMapping(value = "saveCustomer.htm", method = RequestMethod.POST)
	public ModelAndView saveCustomer(Model model, @ModelAttribute("customer") CustomerUIModel customer) {
		
		CustomerDao.saveOrUpdate(new Customer(customer));
		
		return new ModelAndView("jsp_new/selectCustomer.jsp");
	}
}