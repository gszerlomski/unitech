package biz.unitech.datamodel.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import biz.unitech.datamodel.Adaptor;
import biz.unitech.datamodel.FittingType;
import biz.unitech.datamodel.Grip;
import biz.unitech.datamodel.Oring;
import biz.unitech.datamodel.ThreadDim;
import biz.unitech.datamodel.TubeDim;

public class DatabasePrepopulationTest {

	@Test
	public void testFittingTypes() {

		Long retrieved = (Long) DatabaseUtils.count(FittingType.class);
		assertNotNull("Count does not work", retrieved);
		assertEquals("Number of elements does no match", new Long(189), retrieved);
	}
	
	@Test
	public void testOring() {

		Long retrieved = (Long) DatabaseUtils.count(Oring.class);
		assertNotNull("Count does not work", retrieved);
		assertEquals("Number of elements does no match", new Long(5), retrieved);
	}
	
	@Test
	public void testGrip() {

		Long retrieved = (Long) DatabaseUtils.count(Grip.class);
		assertNotNull("Count does not work", retrieved);
		assertEquals("Number of elements does no match", new Long(9), retrieved);
	}
	
	@Test
	public void testTubeDim() {

		Long retrieved = (Long) DatabaseUtils.count(TubeDim.class);
		assertNotNull("Count does not work", retrieved);
		assertEquals("Number of elements does no match", new Long(26), retrieved);
	}
	
	@Test
	public void testAdaptorDim() {

		Long retrieved = (Long) DatabaseUtils.count(Adaptor.class);
		assertNotNull("Count does not work", retrieved);
		assertEquals("Number of elements does no match", new Long(26), retrieved);
	}
	
	@Test
	public void testThreadDim() {

		Long retrieved = (Long) DatabaseUtils.count(ThreadDim.class);
		assertNotNull("Count does not work", retrieved);
		assertEquals("Number of elements does no match", new Long(25), retrieved);
	}
}
