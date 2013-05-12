package biz.unitech.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerOrderController {

	private Logger logger = Logger.getLogger(getClass());

	
	@RequestMapping(value = "createCustomerOrder.htm", method = RequestMethod.GET)
	public ModelAndView handleRequest() {

		return new ModelAndView("jsp_new/createCustomerOrder.jsp");
	}
}