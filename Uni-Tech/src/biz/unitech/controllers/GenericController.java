package biz.unitech.controllers;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;

import biz.unitech.uimodel.Message;
import biz.unitech.uimodel.Messages;

public class GenericController {
	
	private Logger logger = Logger.getLogger(getClass());

	protected void registerError(Model model, Messages messages) {
		model.addAttribute("errorMessages", messages);
	}

	protected void registerError(Model model, Exception e) {
		logger.error(e.getMessage(), e);
		registerError(model, new Messages(new Message[] { new Message(e.getMessage(), null) }));
	}

	protected void registerSuccess(Model model, Messages messages) {
		model.addAttribute("successMessages", messages);
	}

	protected void registerInfo(Model model, Messages messages) {
		model.addAttribute("infoMessages", messages);
	}
}
