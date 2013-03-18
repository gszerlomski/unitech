package biz.unitech.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jboss.logging.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import biz.unitech.dao.DuplicateEntryException;
import biz.unitech.dao.FittingDao;
import biz.unitech.dao.OrderDao;
import biz.unitech.datamodel.Fitting;
import biz.unitech.datamodel.FittingType;
import biz.unitech.datamodel.Grip;
import biz.unitech.datamodel.Oring;
import biz.unitech.datamodel.PriceList;
import biz.unitech.datamodel.Supplier;
import biz.unitech.datamodel.SupplierOrder;
import biz.unitech.datamodel.TubeDim;
import biz.unitech.uimodel.FittingDescUIModel;
import biz.unitech.uimodel.FittingUIModel;
import biz.unitech.uimodel.FittingUIPricing;
import biz.unitech.uimodel.Message;
import biz.unitech.uimodel.Messages;
import biz.unitech.uimodel.OrderList;
import biz.unitech.uimodel.OrderUIModel;
import biz.unitech.uimodel.SupplierOrderLineItemUIModel;
import biz.unitech.uimodel.SupplierOrderUIModel;
import biz.unitech.uimodel.UIModelCreator;

@Controller
@SessionAttributes(types = { OrderUIModel.class, FittingUIPricing.class, OrderList.class})
public class SupplierOrderController {

	private Logger logger = Logger.getLogger(getClass());

	@ModelAttribute("orderModel")
	public OrderUIModel populateForm() {
		return createNewSupplierOrderModel();
	}

	@RequestMapping(value = "createSupplierOrder.htm", method = RequestMethod.GET)
	public ModelAndView handleRequest(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		return new ModelAndView("jsp/createSupplierOrder.jsp");
	}

	private OrderUIModel createNewSupplierOrderModel() {
		SupplierOrderUIModel supplierOrderModel = UIModelCreator.getNewSupplierOrderUIModel();

		FittingDescUIModel fittingDescModel = UIModelCreator.getFittingDescUIModel();

		OrderUIModel newOrderModel = new OrderUIModel(supplierOrderModel, fittingDescModel, null, null);

		return newOrderModel;
	}

	@RequestMapping(value = "createSupplierOrderDetails.htm", method = RequestMethod.POST)
	public ModelAndView createSupplierOrderDetails(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel) {

		return new ModelAndView("jsp/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "createSupplierOrder.htm", method = RequestMethod.POST)
	public ModelAndView createSupplierOrder(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel) {

		SupplierOrder order;
		try {
			order = new SupplierOrder(orderModel);

			FittingDao.saveOrUpdate(order);
			model.addAttribute("orderModel", createNewSupplierOrderModel());
		} catch (DuplicateEntryException e) {
			registerError(model, e);
		}
		return new ModelAndView("jsp/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "newProductList.htm", method = RequestMethod.POST)
	public ModelAndView newproductList(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel,
			@RequestParam("fittingTypes") String chosenType) {

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

		return new ModelAndView("jsp/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "newProduct.htm", method = RequestMethod.POST)
	public ModelAndView newProduct(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel) {
		try {

			FittingUIPricing uiPricing = getFittingUIPricing(orderModel.getFitting(), orderModel.getSupplierOrderModel().getSupplier());

			orderModel.getFitting().setDisabledInputs(true);
			orderModel.getFitting().setPricing(uiPricing);
		} catch (FormValidationException e) {
			registerError(model, e);
		} catch (DuplicateEntryException e) {
			registerError(model, e);
		}

		return new ModelAndView("jsp/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "addSupplierOrderDetails.htm", method = RequestMethod.POST)
	public ModelAndView addSupplierOrderDetails(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel) {

		return new ModelAndView("jsp/addSupplierOrderDetails.jsp");
	}

	@RequestMapping(value = "confirmSupplierOrder.htm", method = RequestMethod.POST)
	public ModelAndView confirmSupplierOrder(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel) {

		SupplierOrder order = null;
		try {
			order = new SupplierOrder(orderModel);
			FittingDao.saveOrUpdate(order);
			registerSuccess(model, new Messages(new Message[] { Message.ORDER_CREATED }));
		} catch (DuplicateEntryException e) {
			registerError(model, e);
		}
		return new ModelAndView("jsp/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "supplierOrderSummary.htm", method = RequestMethod.GET)
	public ModelAndView supplierOrderSummary(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel) {
		return new ModelAndView("jsp/supplierOrderSummary.jsp");
	}

	@RequestMapping(value = "pricingDetails.htm", method = RequestMethod.POST, params = "pricingAction=Edit")
	public ModelAndView editPricingDetails(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel) {

		FittingUIPricing pricing = orderModel.getFitting().getPricing();
		pricing.enableEditableFields();
		orderModel.getFitting().getGripNumber().setDisabled(false);
		orderModel.getFitting().setPricing(pricing);
		model.addAttribute("orderModel", orderModel);
		model.addAttribute("oldPricing", new FittingUIPricing(pricing));
		return new ModelAndView("jsp/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "pricingDetails.htm", method = RequestMethod.POST, params = "pricingAction=Add")
	public ModelAndView addToOrder(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel) {

		try {
			orderModel.getSupplierOrderModel().addLineItem(orderModel.getFitting(), orderModel.getFitting().getPricing());
			orderModel.setFitting(null);
			model.addAttribute("orderModel", orderModel);
		} catch (FormValidationException e) {
			registerError(model, e);
		}
		return new ModelAndView("jsp/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "pricingDetails.htm", method = RequestMethod.POST, params = "pricingAction=Save")
	public ModelAndView savePricingDetails(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel,
			@ModelAttribute("oldPricing") FittingUIPricing oldPricing) {

		FittingUIPricing uiPricing = orderModel.getFitting().getPricing();
		try {
			updateFittingPricing(orderModel.getFitting().getFittingType().getValue(), orderModel.getFitting().getTubeDim().getValue(),
					uiPricing.getFittingPrice().getValue());
			updateFittingGripNumber(orderModel.getFitting().getFittingType().getValue(), orderModel.getFitting().getGripNumber().getValue());
			updateGripPrice(orderModel.getFitting().getGrip().getValue(), uiPricing.getGripPrice().getValue());

			// To revalidate values from database
			uiPricing = getFittingUIPricing(orderModel.getFitting(), orderModel.getSupplierOrderModel().getSupplier());
			uiPricing.setAmount(orderModel.getFitting().getPricing().getAmount());
			orderModel.getFitting().getGripNumber().setDisabled(true);

		} catch (FormValidationException e) {
			registerError(model, new Messages(new Message[] { new Message(e.getMessage(), null) }));
			uiPricing = oldPricing;
		} catch (DuplicateEntryException e) {
			registerError(model, new Messages(new Message[] { new Message(e.getMessage(), null) }));
			uiPricing = oldPricing;
		}
		orderModel.getFitting().setPricing(uiPricing);
		model.addAttribute("orderModel", orderModel);
		return new ModelAndView("jsp/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "ordersNotCompleted.htm", method = RequestMethod.GET)
	public ModelAndView listUnrealizedOrders(Model model) {

		clearSession(model);
		List<SupplierOrder> list = OrderDao.getOrdersByCompletion(false);
		List<SupplierOrderUIModel> converted = convertToUIList(list);

		model.addAttribute("orderList", new OrderList(converted));

		return new ModelAndView("jsp/ordersNotCompleted.jsp");
	}

	@RequestMapping(value = "notRealizedOrders.htm", method = RequestMethod.POST)
	public ModelAndView modifyUnrealizedOrders(Model model, @ModelAttribute("orderList") OrderList orderList) {

		for (SupplierOrderUIModel supOrder : orderList.getOrders()) {
			setCompletion(supOrder);
			try {
				FittingDao.saveOrUpdate(new SupplierOrder(supOrder));
			} catch (DuplicateEntryException e) {
				registerError(model, e);
			}
		}
		listUnrealizedOrders(model);

		return new ModelAndView("jsp/ordersNotCompleted.jsp");
	}
	
	@RequestMapping(value = "ordersCompleted.htm", method = RequestMethod.GET)
	public ModelAndView listRealizedOrders(Model model) {
		
		clearSession(model);
		List<SupplierOrder> list = OrderDao.getOrdersByCompletion(true);
		List<SupplierOrderUIModel> converted = convertToUIList(list);

		model.addAttribute("orderList", new OrderList(converted));

		return new ModelAndView("jsp/ordersCompleted.jsp");
	}

	@RequestMapping(value = "realizedOrders.htm", method = RequestMethod.POST)
	public ModelAndView modifyRealizedOrders(Model model, @ModelAttribute("orderList") OrderList orderList) {

		for (SupplierOrderUIModel supOrder : orderList.getOrders()) {
			setNonCompletion(supOrder);
			try {
				FittingDao.saveOrUpdate(new SupplierOrder(supOrder));
			} catch (DuplicateEntryException e) {
				registerError(model, e);
			}
		}
		listRealizedOrders(model);
		return new ModelAndView("jsp/ordersCompleted.jsp");
	}

	@RequestMapping(value = "newOrder.htm", method = RequestMethod.POST)
	public ModelAndView createNewOrder(Model model) {

		model.addAttribute("orderModel", createNewSupplierOrderModel());
		return new ModelAndView("jsp/createSupplierOrder.jsp");
	}
	
	@RequestMapping(value = "changeSupplierOrder.htm", method = RequestMethod.POST, params = "itemAction=Delete")
	public ModelAndView deleteSupplierOrderLineItem(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel, 
			@RequestParam("itemModified") String itemModifiedIndex) {
		
		int itemIndex = Integer.parseInt(itemModifiedIndex);
		orderModel.getSupplierOrderModel().getLineItems().remove(itemIndex);
		
		model.addAttribute("orderModel", orderModel);
		
		return new ModelAndView("jsp/createSupplierOrder.jsp");
	}
	
	@RequestMapping(value = "changeSupplierOrder.htm", method = RequestMethod.POST, params = "itemAction=Edit")
	public ModelAndView editSupplierOrderLineItem(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel, 
			@RequestParam("itemModified") String itemModifiedIndex) {
		
		int itemIndex = Integer.parseInt(itemModifiedIndex);
		SupplierOrderLineItemUIModel item = orderModel.getSupplierOrderModel().getLineItems().remove(itemIndex);
		orderModel.setFitting(item.getProduct());
		
		model.addAttribute("orderModel", orderModel);
		
		return new ModelAndView("jsp/createSupplierOrder.jsp");
	}

	private void clearSession(Model model) {
		model.addAttribute("orderModel", null);
		model.addAttribute("orderList", null);
		model.addAttribute("oldPricing", null);	
	}
	
	private void setNonCompletion(SupplierOrderUIModel supOrder) {
		if (!supOrder.isCompleted()) {
			supOrder.setLineItemsCompletion(false);
		} else if (!supOrder.getLineItemsCompletion()) {
			supOrder.setCompleted(false);
		}
		if (!supOrder.isCompleted() && supOrder.getCompletedDate() != null) {
			supOrder.setCompletedDate(null);
		}
	}
	
	private void setCompletion(SupplierOrderUIModel supOrder) {
		if (supOrder.isCompleted()) {
			supOrder.setLineItemsCompletion(true);
		} else if (supOrder.getLineItemsCompletion()) {
			supOrder.setCompleted(true);
		}
		if (supOrder.isCompleted() && supOrder.getCompletedDate() == null) {
			supOrder.setCompletedDate(Calendar.getInstance().getTime());
		}
	}

	private FittingUIPricing getFittingUIPricing(FittingUIModel fitting, Supplier supplier) throws FormValidationException,
			DuplicateEntryException {

		FittingType type = getFittingTypeByName(fitting.getFittingType().getValue());
		TubeDim tubeDim = getTubeDimByName(fitting.getTubeDim().getValue());
		Grip grip = getGripByName(fitting.getGrip().getValue());
		PriceList prices = PriceList.getInstance(type, tubeDim);
		return new FittingUIPricing(prices, grip, supplier);
	}

	private List<SupplierOrderUIModel> convertToUIList(List<SupplierOrder> list) {
		List<SupplierOrderUIModel> result = new ArrayList<SupplierOrderUIModel>(list.size());
		for (SupplierOrder supplierOrder : list) {
			result.add(new SupplierOrderUIModel(supplierOrder));
		}
		return result;
	}

	private void updateGripPrice(String gripName, String gripPrice) throws FormValidationException, DuplicateEntryException {
		Grip grip = getGripByName(gripName);
		grip.setPrice(new BigDecimal(gripPrice));
		FittingDao.saveOrUpdate(grip);
	}

	private void updateFittingGripNumber(String fittingType, String gripNumber) throws FormValidationException, DuplicateEntryException {
		FittingType fType = getFittingTypeByName(fittingType);
		fType.setGripNumber(Integer.parseInt(gripNumber));
		FittingDao.saveOrUpdate(fType);
	}

	private void updateFittingPricing(String fittingTypeName, String tubeDimName, String fittingPrice) throws FormValidationException,
			DuplicateEntryException {
		TubeDim tubeDim = getTubeDimByName(tubeDimName);
		FittingType fittingType = getFittingTypeByName(fittingTypeName);
		PriceList list = PriceList.getInstance(fittingType, tubeDim);

		if (list == null) {
			list = PriceList.getEmptyInstance(fittingType, tubeDim);
		}
		list.setStandardPrice(new BigDecimal(fittingPrice));

		FittingDao.saveOrUpdate(list);
	}

	private Grip getGripByName(String gripName) throws FormValidationException, DuplicateEntryException {
		Grip grip = FittingDao.getGripByName(gripName);
		if (grip == null) {
			throw new FormValidationException("Pierścień \"" + gripName + "\" nie istnieje.");
		}
		return grip;
	}

	private Oring getOringByName(String oringName) throws FormValidationException, DuplicateEntryException {
		Oring oring = FittingDao.getOringByName(oringName);
		if (oring == null) {
			throw new FormValidationException("Oring \"" + oringName + "\" nie istnieje.");
		}
		return oring;
	}

	private TubeDim getTubeDimByName(String tubeDimName) throws FormValidationException, DuplicateEntryException {
		TubeDim tdim = FittingDao.getTubeDimByName(tubeDimName);
		if (tdim == null) {
			throw new FormValidationException("Wymiar rury \"" + tubeDimName + "\" nie istnieje.");
		}
		return tdim;
	}

	private FittingType getFittingTypeByName(String fittingType) throws FormValidationException, DuplicateEntryException {
		FittingType fType = FittingDao.getFittingTypeByName(fittingType);
		if (fType == null) {
			throw new FormValidationException("Typ złączki \"" + fittingType + "\" nie istnieje.");
		}
		return fType;
	}

	private void registerError(Model model, Messages messages) {
		model.addAttribute("errorMessages", messages);
	}

	private void registerError(Model model, Exception e) {
		logger.error(e.getMessage(), e);
		registerError(model, new Messages(new Message[] { new Message(e.getMessage(), null) }));
	}

	private void registerSuccess(Model model, Messages messages) {
		model.addAttribute("successMessages", messages);
	}

	private void registerInfo(Model model, Messages messages) {
		model.addAttribute("infoMessages", messages);
	}
}