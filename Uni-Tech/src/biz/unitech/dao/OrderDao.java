package biz.unitech.dao;

import java.util.List;

import biz.unitech.datamodel.SupplierOrder;

public class OrderDao {

	public static List<SupplierOrder> getAllSupplierOrders() {
		List<Object> orders = DatabaseUtils.getAll(SupplierOrder.class);
		DAOConverter<SupplierOrder> converter = new DAOConverter<SupplierOrder>();
		return converter.convertToList(orders);
	}

	public static List<SupplierOrder> getOrdersByCompletion(boolean completed) {

		List<Object> list = DatabaseUtils.findByParam(SupplierOrder.class, new Param[] { 
				Param.getInstance("completed", completed)});

		DAOConverter<SupplierOrder> converter = new DAOConverter<SupplierOrder>();
		return converter.convertToList(list);
	}
}