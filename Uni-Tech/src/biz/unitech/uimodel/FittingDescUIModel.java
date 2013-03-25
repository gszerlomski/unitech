package biz.unitech.uimodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import biz.unitech.datamodel.Adaptor;
import biz.unitech.datamodel.FittingType;
import biz.unitech.datamodel.Grip;
import biz.unitech.datamodel.Oring;
import biz.unitech.datamodel.ThreadDim;
import biz.unitech.datamodel.TubeDim;

public class FittingDescUIModel {
	
	List<FittingType> fittingTypes;
	
	List<TubeDim> tubeDims;
	
	List<ThreadDim> threadDims;
	
	List<Oring> orings;
	
	List<Grip> grips;
	
	List<Adaptor> adaptors;

	public FittingDescUIModel(List<FittingType> fittingTypes,
			List<TubeDim> tubeDims, List<ThreadDim> threadDims,
			List<Oring> orings, List<Grip> grips, List<Adaptor> adaptors) {
		
		this.fittingTypes = fittingTypes;
		this.tubeDims = tubeDims;
		this.threadDims = threadDims;
		this.orings = orings;
		this.grips = sortGrips(grips);
		this.adaptors = adaptors;
	}

	private List<Grip> sortGrips(List<Grip> grips) {
		Collections.sort(grips);
		Set<Grip> temp = new TreeSet<Grip>(new GripsComparator());
		temp.addAll(grips);
		return new ArrayList<Grip>(temp);
	}

	public List<FittingType> getFittingTypes() {
		return fittingTypes;
	}

	public void setFittingTypes(List<FittingType> fittingTypes) {
		this.fittingTypes = fittingTypes;
	}

	public List<TubeDim> getTubeDims() {
		return tubeDims;
	}

	public void setTubeDims(List<TubeDim> tubeDims) {
		this.tubeDims = tubeDims;
	}

	public List<ThreadDim> getThreadDims() {
		return threadDims;
	}

	public void setThreadDims(List<ThreadDim> threadDims) {
		this.threadDims = threadDims;
	}

	public List<Oring> getOrings() {
		return orings;
	}

	public void setOrings(List<Oring> orings) {
		this.orings = orings;
	}

	public List<Grip> getGrips() {
		return grips;
	}

	public void setGrips(List<Grip> grips) {
		this.grips = grips;
	}
	
	public List<Adaptor> getAdaptors() {
		return adaptors;
	}
	
	public void setAdaptors(List<Adaptor> adaptors) {
		this.adaptors = adaptors;
	}
}
