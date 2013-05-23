package biz.unitech.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import biz.unitech.dao.DatabaseException;
import biz.unitech.dao.DuplicateEntryException;
import biz.unitech.dao.FittingDao;
import biz.unitech.dao.OrderDao;
import biz.unitech.datamodel.fitting.FittingType;
import biz.unitech.datamodel.fitting.Grip;
import biz.unitech.datamodel.fitting.TubeDim;
import biz.unitech.datamodel.orders.Supplier;
import biz.unitech.datamodel.orders.SupplierOrder;
import biz.unitech.datamodel.orders.SupplierPriceList;
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
@SessionAttributes(types = { SupplierOrderUIModel.class, FittingUIPricing.class, OrderList.class})
public class SupplierOrderController extends GenericController {

	@ModelAttribute("orderModel")
	public SupplierOrderUIModel populateForm() {
		return createNewSupplierOrderModel();
	}

	@RequestMapping(value = "createSupplierOrder.htm", method = RequestMethod.GET)
	public ModelAndView handleRequest(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
	}

	private SupplierOrderUIModel createNewSupplierOrderModel() {
		return UIModelCreator.getNewSupplierOrderUIModel();
	}

	@RequestMapping(value = "createSupplierOrderDetails.htm", method = RequestMethod.POST)
	public ModelAndView createSupplierOrderDetails(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel) {

		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "createSupplierOrder.htm", method = RequestMethod.POST)
	public ModelAndView createSupplierOrder(Model model, @ModelAttribute("orderModel") SupplierOrderUIModel orderModel) {

		SupplierOrder order;
		try {
			order = new SupplierOrder(orderModel);

			FittingDao.saveOrUpdate(order);
			model.addAttribute("orderModel", createNewSupplierOrderModel());
		} catch (Exception e) {
			registerError(model, e);
		}
		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
	}
	
	@RequestMapping(value = "addNewSupplierProduct.htm", method = RequestMethod.POST)
	public ModelAndView addNewSupplierProduct(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel) {

		addNewProduct(model, orderModel);

		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "addNewSupplierProduct.htm", method = RequestMethod.POST, params = "chosenFittingType")
	public ModelAndView addNewSupplierProduct(Model model, @ModelAttribute("orderModel") OrderUIModel orderModel,
			@RequestParam("chosenFittingType") String chosenType) {

		addNewProduct(model, orderModel, chosenType);

		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "newSupplierProduct.htm", method = RequestMethod.POST)
	public ModelAndView newSupplierProduct(Model model, @ModelAttribute("orderModel") SupplierOrderUIModel orderModel) {
		try {

			FittingUIPricing uiPricing = getFittingUIPricing(orderModel.getFitting(), orderModel.getSupplier());

			orderModel.getFitting().setDisabledInputs(true);
			orderModel.getFitting().setPricing(uiPricing);
		} catch (FormValidationException e) {
			registerError(model, e);
		} catch (DuplicateEntryException e) {
			registerError(model, e);
		}

		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
	}
	
	@RequestMapping(value = "clearOrderForm.htm", method = RequestMethod.POST)
	public ModelAndView clearOrder(Model model, @ModelAttribute("orderModel") SupplierOrderUIModel orderModel) {
		orderModel.clearLineItems();
		
		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "addSupplierOrderDetails.htm", method = RequestMethod.POST)
	public ModelAndView addSupplierOrderDetails() {

		return new ModelAndView("jsp_new/addSupplierOrderDetails.jsp");
	}

	@RequestMapping(value = "confirmSupplierOrder.htm", method = RequestMethod.POST)
	public ModelAndView confirmSupplierOrder(Model model, @ModelAttribute("orderModel") SupplierOrderUIModel orderModel) {

		SupplierOrder order = null;
		try {
			order = new SupplierOrder(orderModel);
			FittingDao.saveOrUpdate(order);
			registerSuccess(model, new Messages(new Message[] { Message.ORDER_CREATED }));
		} catch (Exception e) {
			registerError(model, e);
		}
		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
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
		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "pricingDetails.htm", method = RequestMethod.POST, params = "pricingAction=Add")
	public ModelAndView addToOrder(Model model, @ModelAttribute("orderModel") SupplierOrderUIModel orderModel) {

		try {
			orderModel.addLineItem(orderModel.getFitting(), orderModel.getFitting().getPricing());
			orderModel.setFitting(null);
			model.addAttribute("orderModel", orderModel);
		} catch (FormValidationException e) {
			registerError(model, e);
		}
		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "pricingDetails.htm", method = RequestMethod.POST, params = "pricingAction=Save")
	public ModelAndView savePricingDetails(Model model, @ModelAttribute("orderModel") SupplierOrderUIModel orderModel,
			@ModelAttribute("oldPricing") FittingUIPricing oldPricing) {

		FittingUIPricing uiPricing = orderModel.getFitting().getPricing();
		try {
			updateFittingPricing(orderModel.getFitting().getFittingType().getValue(), orderModel.getFitting().getTubeDim().getValue(),
					uiPricing.getFittingPrice().getValue());
			updateFittingGripNumber(orderModel.getFitting().getFittingType().getValue(), orderModel.getFitting().getGripNumber().getValue());
			updateGripPrice(orderModel.getFitting().getGrip().getValue(), uiPricing.getGripPrice().getValue());

			// To revalidate values from database
			uiPricing = getFittingUIPricing(orderModel.getFitting(), orderModel.getSupplier());
			uiPricing.setAmount(orderModel.getFitting().getPricing().getAmount());
			orderModel.getFitting().getGripNumber().setDisabled(true);

		} catch (Exception e) {
			registerError(model, new Messages(new Message[] { new Message(e.getMessage(), null) }));
			uiPricing = oldPricing;
		}
		orderModel.getFitting().setPricing(uiPricing);
		model.addAttribute("orderModel", orderModel);
		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
	}

	@RequestMapping(value = "ordersNotCompleted.htm", method = RequestMethod.GET)
	public ModelAndView listUnrealizedOrders(Model model) {

		clearSession(model);
		List<SupplierOrder> list = OrderDao.getOrdersByCompletion(false);
		List<SupplierOrderUIModel> converted = convertToUIList(list);

		model.addAttribute("orderList", new OrderList(converted));

		return new ModelAndView("jsp_new/ordersNotCompleted.jsp");
	}

	@RequestMapping(value = "notRealizedOrders.htm", method = RequestMethod.POST)
	public ModelAndView modifyUnrealizedOrders(Model model, @ModelAttribute("orderList") OrderList orderList) {

		for (SupplierOrderUIModel supOrder : orderList.getOrders()) {
			setCompletion(supOrder);
			try {
				FittingDao.saveOrUpdate(new SupplierOrder(supOrder));
			} catch (Exception e) {
				registerError(model, e);
			}
		}
		listUnrealizedOrders(model);

		return new ModelAndView("jsp_new/ordersNotCompleted.jsp");
	}
	
	@RequestMapping(value = "ordersCompleted.htm", method = RequestMethod.GET)
	public ModelAndView listRealizedOrders(Model model) {
		
		clearSession(model);
		List<SupplierOrder> list = OrderDao.getOrdersByCompletion(true);
		List<SupplierOrderUIModel> converted = convertToUIList(list);

		model.addAttribute("orderList", new OrderList(converted));

		return new ModelAndView("jsp_new/ordersCompleted.jsp");
	}

	@RequestMapping(value = "realizedOrders.htm", method = RequestMethod.POST)
	public ModelAndView modifyRealizedOrders(Model model, @ModelAttribute("orderList") OrderList orderList) {

		for (SupplierOrderUIModel supOrder : orderList.getOrders()) {
			setNonCompletion(supOrder);
			try {
				FittingDao.saveOrUpdate(new SupplierOrder(supOrder));
			} catch (Exception e) {
				registerError(model, e);
			}
		}
		listRealizedOrders(model);
		return new ModelAndView("jsp_new/ordersCompleted.jsp");
	}

	@RequestMapping(value = "newOrder.htm", method = RequestMethod.POST)
	public ModelAndView createNewOrder(Model model) {

		model.addAttribute("orderModel", createNewSupplierOrderModel());
		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
	}
	
	@RequestMapping(value = "changeSupplierOrder.htm", method = RequestMethod.POST, params = "itemAction=Delete")
	public ModelAndView deleteSupplierOrderLineItem(Model model, @ModelAttribute("orderModel") SupplierOrderUIModel orderModel, 
			@RequestParam("itemModified") String itemModifiedIndex) {
		
		int itemIndex = Integer.parseInt(itemModifiedIndex);
		orderModel.getLineItems().remove(itemIndex);
		
		model.addAttribute("orderModel", orderModel);
		
		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
	}
	
	@RequestMapping(value = "changeSupplierOrder.htm", method = RequestMethod.POST, params = "itemAction=Edit")
	public ModelAndView editSupplierOrderLineItem(Model model, @ModelAttribute("orderModel") SupplierOrderUIModel orderModel, 
			@RequestParam("itemModified") String itemModifiedIndex) {
		
		int itemIndex = Integer.parseInt(itemModifiedIndex);
		SupplierOrderLineItemUIModel item = orderModel.getLineItems().remove(itemIndex);
		orderModel.setFitting(item.getProduct());
		
		model.addAttribute("orderModel", orderModel);
		
		return new ModelAndView("jsp_new/createSupplierOrder.jsp");
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

		Grip grip = getGripByName(fitting.getGrip().getValue());
		
		return new FittingUIPricing(getPriceList(fitting, grip), grip, supplier);
	}

	private List<SupplierOrderUIModel> convertToUIList(List<SupplierOrder> list) {
		List<SupplierOrderUIModel> result = new ArrayList<SupplierOrderUIModel>(list.size());
		for (SupplierOrder supplierOrder : list) {
			result.add(new SupplierOrderUIModel(supplierOrder, null));
		}
		return result;
	}

	private void updateGripPrice(String gripName, String gripPrice) throws FormValidationException, DuplicateEntryException, DatabaseException {
		Grip grip = getGripByName(gripName);
		grip.setPrice(new BigDecimal(gripPrice));
		FittingDao.saveOrUpdate(grip);
	}

	private void updateFittingGripNumber(String fittingType, String gripNumber) throws FormValidationException, DuplicateEntryException, DatabaseException {
		FittingType fType = getFittingTypeByName(fittingType);
		fType.setGripNumber(Integer.parseInt(gripNumber));
		FittingDao.saveOrUpdate(fType);
	}

	private void updateFittingPricing(String fittingTypeName, String tubeDimName, String fittingPrice) throws FormValidationException,
			DuplicateEntryException, DatabaseException {
		TubeDim tubeDim = getTubeDimByName(tubeDimName);
		FittingType fittingType = getFittingTypeByName(fittingTypeName);
		SupplierPriceList list = SupplierPriceList.getInstance(fittingType, tubeDim);

		if (list == null) {
			list = SupplierPriceList.getEmptyInstance(fittingType, tubeDim);
		}
		list.setStandardPrice(new BigDecimal(fittingPrice));

		FittingDao.saveOrUpdate(list);
	}
}