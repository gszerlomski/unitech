package biz.unitech.uimodel;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import biz.unitech.datamodel.orders.Supplier;

public class StateLineItemsTestCase {

	@Test
	public void test() {
		List<SupplierOrderLineItemUIModel> items = new ArrayList<SupplierOrderLineItemUIModel>();
		SupplierOrderUIModel order = new SupplierOrderUIModel(new Supplier(), null);
		SupplierOrderLineItemUIModel item1 = new SupplierOrderLineItemUIModel();
		SupplierOrderLineItemUIModel item2 = new SupplierOrderLineItemUIModel();
		SupplierOrderLineItemUIModel item3 = new SupplierOrderLineItemUIModel();
		SupplierOrderLineItemUIModel item4 = new SupplierOrderLineItemUIModel();
		SupplierOrderLineItemUIModel item5 = new SupplierOrderLineItemUIModel();
		SupplierOrderLineItemUIModel item6 = new SupplierOrderLineItemUIModel();
		item1.setAmount(1);
		item1.setDelivered(true);
		item1.setSingleProductPrice(new BigDecimal(5));
		item1.setTotalPrice(new BigDecimal(5));
		item2.setAmount(2);
		item2.setDelivered(false);
		item2.setSingleProductPrice(new BigDecimal(4));
		item2.setTotalPrice(new BigDecimal(4));
		item3.setAmount(3);
		item3.setDelivered(false);
		item3.setSingleProductPrice(new BigDecimal(2));
		item3.setTotalPrice(new BigDecimal(2));
		item4.setAmount(4);
		item4.setDelivered(true);
		item4.setSingleProductPrice(new BigDecimal(1));
		item4.setTotalPrice(new BigDecimal(1));
		item5.setAmount(5);
		item5.setDelivered(false);
		item5.setSingleProductPrice(new BigDecimal(3));
		item5.setTotalPrice(new BigDecimal(3));
		item6.setAmount(6);
		item6.setDelivered(true);
		item6.setSingleProductPrice(new BigDecimal(2));
		item6.setTotalPrice(new BigDecimal(2));
		
		
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);
		
		
		order.setLineItems(items);
		List<SupplierOrderLineItemUIModel> UndeliveredItems = new ArrayList<SupplierOrderLineItemUIModel>(order.getNotCompletedLineItems());
		List<SupplierOrderLineItemUIModel> DeliveredItems = new ArrayList<SupplierOrderLineItemUIModel>(order.getCompletedLineItems());

		assertTrue("Number of elements is incorrect", UndeliveredItems.size() == 3);
		assertTrue("Number of elements is incorrect", DeliveredItems.size() == 3);
		assertEquals(3, UndeliveredItems.get(1).getAmount());
		assertEquals(4, DeliveredItems.get(1).getAmount());
	}
}