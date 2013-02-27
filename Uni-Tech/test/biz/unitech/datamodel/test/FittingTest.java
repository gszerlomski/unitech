package biz.unitech.datamodel.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import biz.unitech.datamodel.Fitting;
import biz.unitech.datamodel.FittingType;
import biz.unitech.datamodel.Grip;
import biz.unitech.datamodel.Oring;
import biz.unitech.datamodel.TubeDim;

public class FittingTest {

	@Test
	public void fittingOrderCodeTest() {
		Fitting fitting = new Fitting();
		fitting.setFittingType(new FittingType(13, "UAC", "${TY} ${TU} ${OR} ${GR}"));
		fitting.setTubeDim(new TubeDim(8, "8"));
		fitting.setOring(new Oring(1, "JN"));
		fitting.setGrip(new Grip(20, "BAB", new BigDecimal(12.5)));
		
		assertEquals("Fitting Order Code does not match", "00131200800", fitting.getFittingOrderCode());
	}
}
