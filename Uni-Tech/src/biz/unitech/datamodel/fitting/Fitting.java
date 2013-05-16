package biz.unitech.datamodel.fitting;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import biz.unitech.dao.DatabaseException;
import biz.unitech.dao.DuplicateEntryException;
import biz.unitech.dao.FittingDao;
import biz.unitech.uimodel.FittingUIModel;

@Entity
public class Fitting {

	@Id
	@GeneratedValue
	private int fittingId;

	@ManyToOne()
	private FittingType fittingType;

	@ManyToOne()
	private TubeDim tubeDim;

	@ManyToOne()
	private Oring oring;

	@ManyToOne(optional = true)
	private Grip grip;

	@ManyToOne(optional = true)
	private ThreadDim threadDim;

	@ManyToOne(optional = true)
	private Adaptor adaptor;

	private transient String fittingOrderCode;

	public int getFittingId() {
		return fittingId;
	}

	public void setFittingId(int fittingId) {
		this.fittingId = fittingId;
	}

	public String getFittingOrderCode() {
		if (fittingOrderCode == null) {
			StringBuffer buffer = new StringBuffer();

			if (fittingType != null) {
				buffer.append(fittingType.getOrderCodeAsString());
			}
			if (oring != null) {
				buffer.append(oring.getOrderCodeAsString());
			}
			if (grip != null) {
				buffer.append(grip.getOrderCodeAsString());
			}
			if (tubeDim != null) {
				buffer.append(tubeDim.getOrderCodeAsString());
			} else if (adaptor != null) {
				buffer.append(adaptor.getOrderCodeAsString());
			}
			if (threadDim != null) {
				buffer.append(threadDim.getOrderCodeAsString());
			} else {
				buffer.append("00");
			}

			fittingOrderCode = buffer.toString();
		}
		return fittingOrderCode;
	}

	public FittingType getFittingType() {
		return fittingType;
	}

	public void setFittingType(FittingType fittingType) {
		this.fittingType = fittingType;
	}

	public TubeDim getTubeDim() {
		return tubeDim;
	}

	public void setTubeDim(TubeDim tubeDim) {
		this.tubeDim = tubeDim;
	}

	public Oring getOring() {
		return oring;
	}

	public void setOring(Oring oring) {
		this.oring = oring;
	}

	public Grip getGrip() {
		return grip;
	}

	public void setGrip(Grip grip) {
		this.grip = grip;
	}

	public ThreadDim getThreadDim() {
		return threadDim;
	}

	public void setThreadDim(ThreadDim threadDim) {
		this.threadDim = threadDim;
	}

	public Adaptor getAdaptor() {
		return adaptor;
	}

	public void setAdaptor(Adaptor adaptor) {
		this.adaptor = adaptor;
	}

	public String toString() {
		return FittingNameFormatter.format(this);
	}

	/**
	 * Get empty fitting prototype
	 * 
	 * @param type
	 * @return
	 */
	public static Fitting getInstance(FittingType type) {
		Fitting fitting = new Fitting();
		String nameFormat = type.getNameFormat();
		if (nameFormat != null) {
			if (nameFormat.contains(FittingType.NAME_SYMBOL)) {
				fitting.setFittingType(type);
			}
			if (nameFormat.contains(TubeDim.NAME_SYMBOL)) {
				fitting.setTubeDim(new TubeDim(0, ""));
			}
			if (nameFormat.contains(Oring.NAME_SYMBOL)) {
				fitting.setOring(new Oring(0, ""));
			}
			if (nameFormat.contains(Grip.NAME_SYMBOL)) {
				fitting.setGrip(new Grip(0, ""));
			}
			if (nameFormat.contains(ThreadDim.NAME_SYMBOL)) {
				fitting.setThreadDim(new ThreadDim(0, ""));
			}
			if (nameFormat.contains(Adaptor.NAME_SYMBOL)) {
				fitting.setAdaptor(new Adaptor(0, ""));
			}
		}
		return fitting;
	}

	public static Fitting getInstance() {
		return new Fitting();
	}

	/**
	 * Get fitting from table of fittings if only exists. If it does not exist,
	 * create it (save in database) and return.
	 * 
	 * @param fittingUIModel
	 * @return
	 * 
	 * @throws DuplicateEntryException
	 * @throws DatabaseException 
	 * @throws NumberFormatException
	 */
	public static Fitting getFitting(FittingUIModel model) throws DuplicateEntryException, DatabaseException {

		Fitting fitting = null;
		List<Fitting> fittings = FittingDao.getFitting(model.getFittingType() == null ? null : model.getFittingType().getValue(), model
				.getAdaptor() == null ? null : model.getAdaptor().getValue(), model.getGrip() == null ? null : model.getGrip().getValue(),
				model.getOring() == null ? null : model.getOring().getValue(), model.getThreadDim() == null ? null : model.getThreadDim()
						.getValue(), model.getTubeDim() == null ? null : model.getTubeDim().getValue());

		if (fittings.size() > 1) {
			throw new DuplicateEntryException("Fitting id" + fittings.get(0).getFittingId() + " is a duplicate of "
					+ fittings.get(1).getFittingId());
		} else if (fittings.isEmpty()) {
			fitting = FittingDao.createFitting(model.getFittingType() == null ? null : model.getFittingType().getValue(), model
					.getAdaptor() == null ? null : model.getAdaptor().getValue(), model.getGrip() == null ? null : model.getGrip()
					.getValue(), model.getOring() == null ? null : model.getOring().getValue(), model.getThreadDim() == null ? null : model
					.getThreadDim().getValue(), model.getTubeDim() == null ? null : model.getTubeDim().getValue());
		} else {
			fitting = fittings.get(0);
		}

		return fitting;
	}
}