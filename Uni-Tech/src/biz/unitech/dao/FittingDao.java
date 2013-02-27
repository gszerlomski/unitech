package biz.unitech.dao;

import java.util.ArrayList;
import java.util.List;

import biz.unitech.datamodel.Adaptor;
import biz.unitech.datamodel.Fitting;
import biz.unitech.datamodel.FittingType;
import biz.unitech.datamodel.Grip;
import biz.unitech.datamodel.Oring;
import biz.unitech.datamodel.PriceList;
import biz.unitech.datamodel.PriceList.PriceListId;
import biz.unitech.datamodel.Supplier;
import biz.unitech.datamodel.ThreadDim;
import biz.unitech.datamodel.TubeDim;

public class FittingDao {

	public static List<Fitting> getAllFittings() {
		List<Object> fittings = DatabaseUtils.getAll(Fitting.class);
		DAOConverter<Fitting> fittingConverter = new DAOConverter<Fitting>();
		return fittingConverter.convertToList(fittings);
	}

	public static List<Supplier> getSupplierByName(String supplierName) {
		List<Object> list = DatabaseUtils.findByParam(Supplier.class, new Param[] { new Param("supplierName", supplierName) });
		List<Supplier> suppliers = new ArrayList<Supplier>(list.size());
		for (Object object : list) {
			suppliers.add((Supplier) object);
		}
		return suppliers;
	}

	public static List<FittingType> getAllFittingTypes() {
		List<Object> list = DatabaseUtils.getAll(FittingType.class);
		List<FittingType> types = new ArrayList<FittingType>(list.size());
		for (Object object : list) {
			FittingType type = (FittingType) object;
			types.add(type);
		}
		return types;
	}

	public static List<Oring> getAllOrings() {
		List<Object> list = DatabaseUtils.getAll(Oring.class);
		List<Oring> types = new ArrayList<Oring>(list.size());
		for (Object object : list) {
			Oring type = (Oring) object;
			types.add(type);
		}
		return types;
	}

	public static List<ThreadDim> getAllThreadDims() {
		List<Object> list = DatabaseUtils.getAll(ThreadDim.class);
		List<ThreadDim> types = new ArrayList<ThreadDim>(list.size());
		for (Object object : list) {
			ThreadDim type = (ThreadDim) object;
			types.add(type);
		}
		return types;
	}

	public static List<TubeDim> getAllTubeDims() {
		List<Object> list = DatabaseUtils.getAll(TubeDim.class);
		List<TubeDim> types = new ArrayList<TubeDim>(list.size());
		for (Object object : list) {
			TubeDim type = (TubeDim) object;
			types.add(type);
		}
		return types;
	}

	public static List<Adaptor> getAllAdaptors() {
		List<Object> list = DatabaseUtils.getAll(Adaptor.class);
		List<Adaptor> types = new ArrayList<Adaptor>(list.size());
		for (Object object : list) {
			Adaptor type = (Adaptor) object;
			types.add(type);
		}
		return types;
	}

	public static List<Grip> getAllGrips() {
		List<Object> list = DatabaseUtils.getAll(Grip.class);
		List<Grip> types = new ArrayList<Grip>(list.size());
		for (Object object : list) {
			Grip type = (Grip) object;
			types.add(type);
		}
		return types;
	}

	public static FittingType getFittingTypeByName(String fittingTypeName) throws DuplicateEntryException {

		if (fittingTypeName == null || fittingTypeName.length() == 0)
			return null;

		List<Object> list = DatabaseUtils.findByParam(FittingType.class, new Param[] { new Param("fittingTypeName", fittingTypeName) });

		FittingType type = null;
		if (list.size() > 1) {
			throw new DuplicateEntryException("Typ złączki " + fittingTypeName + " już istnieje.");
		} else if (!list.isEmpty()) {
			type = (FittingType) list.get(0);
		}
		return type;
	}

	public static TubeDim getTubeDimByName(String tubeDim) throws DuplicateEntryException {

		if (tubeDim == null || tubeDim.length() == 0)
			return null;

		List<Object> list = DatabaseUtils.findByParam(TubeDim.class, new Param[] { new Param("tubeDimName", tubeDim) });

		TubeDim dim = null;
		if (list.size() > 1) {
			throw new DuplicateEntryException("Wymar rury " + tubeDim + " już istnieje.");
		} else if (!list.isEmpty()) {
			dim = (TubeDim) list.get(0);
		}
		return dim;
	}

	public static PriceList getPriceListItemById(PriceListId plID) {
		return (PriceList) DatabaseUtils.getById(PriceList.class, plID);

	}

	/**
	 * There are grips with the same name, but different order ID. Return the
	 * one with lower ID.
	 * 
	 * @param grip
	 * @return null if gripName is empty/null
	 * @throws DuplicateEntryException
	 */
	public static Grip getGripByName(String gripName) throws DuplicateEntryException {

		if (gripName == null || gripName.length() == 0)
			return null;

		List<Object> list = DatabaseUtils.findByParam(Grip.class, new Param[] { new Param("gripName", gripName) });
		Grip grip = null;
		if (list.size() > 2) {
			throw new DuplicateEntryException("Liczba pierścieni o nazwie ('" + gripName + "') jest większa niz 2.");
		} else if (list.size() == 2) {
			grip = ((Grip) list.get(0)).getGripOrderCode() < ((Grip) list.get(1)).getGripOrderCode() ? (Grip) list.get(0) : (Grip) list
					.get(1);
		} else if (!list.isEmpty()) {
			grip = (Grip) list.get(0);
		}
		return grip;
	}

	public static Adaptor getAdaptorByName(String adaptorName) throws DuplicateEntryException {
		if (adaptorName == null || adaptorName.length() == 0)
			return null;

		List<Object> list = DatabaseUtils.findByParam(Adaptor.class, new Param[] { new Param("adaptorName", adaptorName) });

		Adaptor adaptor = null;
		if (list.size() > 1) {
			throw new DuplicateEntryException("Adaptor " + adaptorName + " już istnieje.");
		} else if (!list.isEmpty()) {
			adaptor = (Adaptor) list.get(0);
		}
		return adaptor;
	}

	public static Oring getOringByName(String oringName) throws DuplicateEntryException {
		if (oringName == null || oringName.length() == 0)
			return null;

		List<Object> list = DatabaseUtils.findByParam(Oring.class, new Param[] { new Param("oringName", oringName) });

		Oring oring = null;
		if (list.size() > 1) {
			throw new DuplicateEntryException("Oring " + oringName + " już istnieje.");
		} else if (!list.isEmpty()) {
			oring = (Oring) list.get(0);
		}
		return oring;
	}

	public static ThreadDim getThreadDimByName(String threadDimName) throws DuplicateEntryException {

		if (threadDimName == null || threadDimName.length() == 0)
			return null;

		List<Object> list = DatabaseUtils.findByParam(ThreadDim.class, new Param[] { new Param("oringName", threadDimName) });

		ThreadDim tdim = null;
		if (list.size() > 1) {
			throw new DuplicateEntryException("Wymiar gwintu " + threadDimName + " już istnieje.");
		} else if (!list.isEmpty()) {
			tdim = (ThreadDim) list.get(0);
		}
		return tdim;
	}

	public static Grip getGripById(String grip) throws DuplicateEntryException {
		List<Object> list = DatabaseUtils.findByParam(Grip.class, new Param[] { new Param("gripOrderCode", grip) });

		if (list.size() > 1) {
			throw new DuplicateEntryException("Pierścień o nazwie " + grip + " już istnieje.");
		}
		return (Grip) list.get(0);
	}

	public static void saveOrUpdate(Object object) {
		DatabaseUtils.saveOrUpdate(object);
	}

	public static List<Fitting> getFitting(String fittingType, String adaptorName, String gripName, String oringName, String threadDimName,
			String tubeDimName) throws DuplicateEntryException {

		FittingType type = getFittingTypeByName(fittingType);
		Adaptor adaptor = getAdaptorByName(adaptorName);
		Grip grip = getGripByName(gripName);
		Oring oring = getOringByName(oringName);
		ThreadDim threadDim = getThreadDimByName(threadDimName);
		TubeDim tubeDim = getTubeDimByName(tubeDimName);

		Param[] params = buildFitingParamsTable(type, adaptor, grip, oring, threadDim, tubeDim);

		List<Object> list = DatabaseUtils.findByParam(Fitting.class, params);
		List<Fitting> fittings = new ArrayList<Fitting>(list.size());
		for (Object object : list) {
			fittings.add((Fitting) object);
		}
		return fittings;
	}

	private static Param[] buildFitingParamsTable(FittingType type, Adaptor adaptor, Grip grip, Oring oring, ThreadDim threadDim,
			TubeDim tubeDim) {
		List<Param> params = new ArrayList<Param>(6);
		if (type != null) {
			params.add(new Param("fittingType_fittingTypeOrderCode", type.getFittingTypeOrderCode()));
		}
		if (adaptor != null) {
			params.add(new Param("adaptor_adaptorOrderCode", adaptor.getAdaptorOrderCode()));
		}
		if (grip != null) {
			params.add(new Param("grip_gripOrderCode", grip.getGripOrderCode()));
		}
		if (oring != null) {
			params.add(new Param("oring_oringOrderCode", oring.getOringOrderCode()));
		}
		if (threadDim != null) {
			params.add(new Param("threadDim_threadDimOrderCode", threadDim.getThreadDimOrderCode()));
		}
		if (tubeDim != null) {
			params.add(new Param("tubeDim_tubeDimOrderCode", tubeDim.getTubeDimOrderCode()));
		}
		return params.toArray(new Param[0]);
	}

	public static Fitting createFitting(String fittingType, String adaptorName, String gripName, String oringName, String threadDimName,
			String tubeDimName) throws DuplicateEntryException {
		FittingType type = getFittingTypeByName(fittingType);
		Adaptor adaptor = getAdaptorByName(adaptorName);
		Grip grip = getGripByName(gripName);
		Oring oring = getOringByName(oringName);
		ThreadDim threadDim = getThreadDimByName(threadDimName);
		TubeDim tubeDim = getTubeDimByName(tubeDimName);

		Fitting fitting = new Fitting();
		fitting.setAdaptor(adaptor);
		fitting.setFittingType(type);
		fitting.setGrip(grip);
		fitting.setOring(oring);
		fitting.setThreadDim(threadDim);
		fitting.setTubeDim(tubeDim);

		saveOrUpdate(fitting);

		return fitting;
	}

}