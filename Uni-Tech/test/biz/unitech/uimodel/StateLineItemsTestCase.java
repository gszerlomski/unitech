package biz.unitech.uimodel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import biz.unitech.datamodel.SupplierOrder;

public class StateLineItemsTestCase {

	@Test
	public void test() {
		List<SupplierOrderLineItemUIModel> items = new ArrayList<SupplierOrderLineItemUIModel>();
		SupplierOrderUIModel order = new SupplierOrderUIModel(new SupplierOrder());
		SupplierOrderLineItemUIModel item1 = new SupplierOrderLineItemUIModel();
		SupplierOrderLineItemUIModel item2 = new SupplierOrderLineItemUIModel();
		SupplierOrderLineItemUIModel item3 = new SupplierOrderLineItemUIModel();
		SupplierOrderLineItemUIModel item4 = new SupplierOrderLineItemUIModel();
		SupplierOrderLineItemUIModel item5 = new SupplierOrderLineItemUIModel();
		SupplierOrderLineItemUIModel item6 = new SupplierOrderLineItemUIModel();
		item1.setAmount(1);
		item1.setDelivered(true);
		item2.setAmount(2);
		item2.setDelivered(false);
		item3.setAmount(3);
		item3.setDelivered(false);
		item4.setAmount(4);
		item4.setDelivered(true);
		item5.setAmount(5);
		item5.setDelivered(false);
		item6.setAmount(6);
		item6.setDelivered(true);
		
		
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);
		
		order.setLineItems(items);
		List<SupplierOrderLineItemUIModel> UndeliveredItems = new ArrayList<SupplierOrderLineItemUIModel>(order.getItemsByDeliveryStatus(items, false));
		List<SupplierOrderLineItemUIModel> DeliveredItems = new ArrayList<SupplierOrderLineItemUIModel>(order.getItemsByDeliveryStatus(items, true));

		assertTrue("Number of elements is incorrect", UndeliveredItems.size() == 3);
		assertTrue("Number of elements is incorrect", DeliveredItems.size() == 3);
		assertEquals(3, UndeliveredItems.get(1).getAmount());
		assertEquals(4, DeliveredItems.get(1).getAmount());

	}

}
