package biz.unitech.controllers;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;

import biz.unitech.dao.DuplicateEntryException;
import biz.unitech.dao.FittingDao;
import biz.unitech.datamodel.fitting.Fitting;
import biz.unitech.datamodel.fitting.FittingType;
import biz.unitech.datamodel.fitting.Grip;
import biz.unitech.datamodel.fitting.Oring;
import biz.unitech.datamodel.fitting.TubeDim;
import biz.unitech.datamodel.orders.Customer;
import biz.unitech.datamodel.orders.CustomerPriceList;
import biz.unitech.datamodel.orders.SupplierPriceList;
import biz.unitech.uimodel.FittingUIModel;
import biz.unitech.uimodel.Message;
import biz.unitech.uimodel.Messages;
import biz.unitech.uimodel.OrderUIModel;

public class GenericController {

	private Logger logger = Logger.getLogger(getClass());

	protected void addNewProduct(Model model, OrderUIModel orderModel) {
		Fitting fitting = Fitting.getInstance(new FittingType());
		orderModel.setFitting(new FittingUIModel(fitting));
		model.addAttribute("orderModel", orderModel);
	}
	
	protected void addNewProduct(Model model, OrderUIModel orderModel, String chosenType) {
		FittingType type;
		try {
			type = FittingDao.getFittingTypeByName(chosenType);

			if (type == null) {
				registerInfo(model, new Messages(new Message("Typ złączki " + chosenType + " nie został rozpoznany", null)));
			} else {
				Fitting fitting = Fitting.getInstance(type);
				orderModel.setFitting(new FittingUIModel(fitting));
				model.addAttribute("orderModel", orderModel);
			}

		} catch (DuplicateEntryException e) {
			registerError(model, e);
		}
	}

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
	
	protected SupplierPriceList getPriceList(FittingUIModel fitting, Grip grip) throws FormValidationException, DuplicateEntryException {
		FittingType type = getFittingTypeByName(fitting.getFittingType().getValue());
		TubeDim tubeDim = getTubeDimByName(fitting.getTubeDim().getValue());
		return SupplierPriceList.getInstance(type, tubeDim);
	}
	
	protected CustomerPriceList getPriceList(FittingUIModel fitting, Grip grip, Customer customer) throws FormValidationException, DuplicateEntryException {
		FittingType type = getFittingTypeByName(fitting.getFittingType().getValue());
		TubeDim tubeDim = getTubeDimByName(fitting.getTubeDim().getValue());
		return CustomerPriceList.getInstance(type, tubeDim, customer);
	}
	
	protected Grip getGripByName(String gripName) throws FormValidationException, DuplicateEntryException {
		Grip grip = FittingDao.getGripByName(gripName);
		if (grip == null) {
			throw new FormValidationException("Pierścień \"" + gripName + "\" nie istnieje.");
		}
		return grip;
	}

	protected Oring getOringByName(String oringName) throws FormValidationException, DuplicateEntryException {
		Oring oring = FittingDao.getOringByName(oringName);
		if (oring == null) {
			throw new FormValidationException("Oring \"" + oringName + "\" nie istnieje.");
		}
		return oring;
	}

	protected TubeDim getTubeDimByName(String tubeDimName) throws FormValidationException, DuplicateEntryException {
		TubeDim tdim = FittingDao.getTubeDimByName(tubeDimName);
		if (tdim == null) {
			throw new FormValidationException("Wymiar rury \"" + tubeDimName + "\" nie istnieje.");
		}
		return tdim;
	}

	protected FittingType getFittingTypeByName(String fittingType) throws FormValidationException, DuplicateEntryException {
		FittingType fType = FittingDao.getFittingTypeByName(fittingType);
		if (fType == null) {
			throw new FormValidationException("Typ złączki \"" + fittingType + "\" nie istnieje.");
		}
		return fType;
	}
}
