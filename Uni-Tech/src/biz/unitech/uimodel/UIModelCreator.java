package biz.unitech.uimodel;

import java.util.List;

import biz.unitech.dao.FittingDao;
import biz.unitech.datamodel.orders.Customer;
import biz.unitech.datamodel.orders.Supplier;
import biz.unitech.datamodel.orders.SupplierOrder;

public class UIModelCreator {
	
	public static final String DEFAULT_SUPPLIER_NAME = "F.B.O.";

	
	public static FittingDescUIModel getFittingDescUIModel() {
		return new FittingDescUIModel(
				FittingDao.getAllFittingTypes(), FittingDao.getAllTubeDims(),
				FittingDao.getAllThreadDims(), FittingDao.getAllOrings(),
				FittingDao.getAllGrips(), FittingDao.getAllAdaptors());
	}

	public static SupplierOrderUIModel getNewSupplierOrderUIModel() {
		List<Supplier> suppliers = FittingDao.getSupplierByName(DEFAULT_SUPPLIER_NAME);
		if(suppliers.isEmpty()) {
			throw new RuntimeException("Supplier with name " + DEFAULT_SUPPLIER_NAME + " does not exist");
		}
		return new SupplierOrderUIModel(suppliers.get(0), null);
	}
	
	public static CustomerOrderUIModel getNewCustomerOrderUIModel(Customer customer) {
		return new CustomerOrderUIModel(customer, null);
	}
}
