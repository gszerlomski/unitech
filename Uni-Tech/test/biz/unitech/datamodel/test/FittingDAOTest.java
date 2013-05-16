package biz.unitech.datamodel.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import biz.unitech.dao.FittingDao;
import biz.unitech.datamodel.orders.Supplier;

public class FittingDAOTest {

	@Test
	public void testGetByParam() {
		List<Supplier> list = FittingDao.getSupplierByName("F.B.O.");
		
		assertEquals("There should be only one Supplier of the name F.B.O.", 1, list.size());
		assertEquals("Supplier name does not match", "F.B.O.", list.get(0).getSupplierName());
	}

}
