package biz.unitech.controllers;

import org.springframework.ui.Model;

public class ControllerUtils {
	
	private static final String MESSAGE_KEY = "message";
	
	private static final String ERROR_KEY = "error";
	
	public static void addError(Model model, String errorText) {
		addMessage(ERROR_KEY, errorText, model);
	}
	
	public static void addMessage(Model model, String message) {
		addMessage(MESSAGE_KEY, message, model);
	}
	
	private static void addMessage(String name, String text, Model model) {
		model.addAttribute(name, text);
	}

}
