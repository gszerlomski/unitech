package biz.unitech.uimodel;

import java.util.Comparator;

import biz.unitech.datamodel.Grip;

public class GripsComparator implements Comparator<Grip> {

	@Override
	public int compare(Grip el1, Grip el2) {
		return el1.getGripName().compareTo(el2.getGripName());
	}

}
