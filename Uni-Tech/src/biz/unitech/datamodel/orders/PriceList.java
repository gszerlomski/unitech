package biz.unitech.datamodel.orders;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import biz.unitech.dao.FittingDao;
import biz.unitech.datamodel.fitting.FittingType;
import biz.unitech.datamodel.fitting.TubeDim;

@Entity
public class PriceList {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fittingTypeOrderCode", nullable = false, insertable = false, updatable = false)
	private FittingType fittingType;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tubeDimOrderCode", nullable = false, insertable = false, updatable = false)
	private TubeDim tubeDim;

	BigDecimal standardPrice;
	
	@EmbeddedId
	private PriceListId priceListId = new PriceListId();

	public static PriceList getEmptyInstance(FittingType type, TubeDim dim) {
		return new PriceList(type, dim, new BigDecimal(0));
	}

	public static PriceList getInstance(FittingType type, TubeDim dim) {
		PriceList.PriceListId plID = new PriceList.PriceListId(type.getFittingTypeOrderCode(), dim.getTubeDimOrderCode());
		PriceList list =  FittingDao.getPriceListItemById(plID);
		if(list == null) {
			list = getEmptyInstance(type, dim);
		}
		return list;
	}

	public PriceList(FittingType fittingType, TubeDim tubeDim, BigDecimal standardPrice) {
		setFittingType(fittingType);
		setTubeDim(tubeDim);
		setStandardPrice(standardPrice);
	}

	public PriceList() {}

	public PriceListId getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(PriceListId priceListId) {
		this.priceListId = priceListId;
	}

	public FittingType getFittingType() {
		return fittingType;
	}

	public void setFittingType(FittingType fittingType) {
		this.fittingType = fittingType;
		this.priceListId.setFittingTypeOrderCode(fittingType.getFittingTypeOrderCode());
	}

	public TubeDim getTubeDim() {
		return tubeDim;
	}

	public void setTubeDim(TubeDim tubeDim) {
		this.tubeDim = tubeDim;
		this.priceListId.setTubeDimOrderCode(tubeDim.getTubeDimOrderCode());
	}

	public BigDecimal getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}

	@Embeddable
	public static class PriceListId implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column(name = "fittingTypeOrderCode")
		private int fittingTypeOrderCode;

		@Column(name = "tubeDimOrderCode")
		private int tubeDimOrderCode;

		/**
		 * required no-arg constructor
		 */
		public PriceListId() {
		}

		public PriceListId(int fittingTypeOrderCode, int tubeDimOrderCode) {
			this.fittingTypeOrderCode = fittingTypeOrderCode;
			this.tubeDimOrderCode = tubeDimOrderCode;
		}

		public int getFittingTypeOrderCode() {
			return fittingTypeOrderCode;
		}

		public int getTubeDimOrderCode() {
			return tubeDimOrderCode;
		}

		public void setFittingTypeOrderCode(int fittingTypeOrderCode) {
			this.fittingTypeOrderCode = fittingTypeOrderCode;
		}

		public void setTubeDimOrderCode(int tubeDimOrderCode) {
			this.tubeDimOrderCode = tubeDimOrderCode;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof PriceListId))
				return false;

			final PriceListId other = (PriceListId) o;
			StringBuffer buffer = new StringBuffer();
			buffer.append(fittingTypeOrderCode).append(tubeDimOrderCode);

			String thisSignature = buffer.toString();

			buffer = new StringBuffer();
			buffer.append(other.fittingTypeOrderCode).append(other.tubeDimOrderCode);

			String otherSignature = buffer.toString();

			return thisSignature.equals(otherSignature);
		}

		@Override
		public int hashCode() {
			StringBuffer buffer = new StringBuffer();
			buffer.append(fittingTypeOrderCode).append(tubeDimOrderCode);
			return buffer.toString().hashCode();
		}
	}
}
