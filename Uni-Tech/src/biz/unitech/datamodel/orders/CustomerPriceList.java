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
public class CustomerPriceList {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fittingTypeOrderCode", nullable = false, insertable = false, updatable = false)
	private FittingType fittingType;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tubeDimOrderCode", nullable = false, insertable = false, updatable = false)
	private TubeDim tubeDim;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId", nullable = false, insertable = false, updatable = false)
	private Customer customer;

	BigDecimal standardPrice;

	@EmbeddedId
	private CustomerPriceListId priceListId = new CustomerPriceListId();

	public static CustomerPriceList getEmptyInstance(FittingType type, TubeDim dim, Customer customer) {
		return new CustomerPriceList(type, dim, new BigDecimal(0), customer);
	}

	public static CustomerPriceList getInstance(FittingType type, TubeDim dim, Customer customer) {
		CustomerPriceList.CustomerPriceListId plID = new CustomerPriceList.CustomerPriceListId(type.getFittingTypeOrderCode(), dim.getTubeDimOrderCode(), customer.getCustomerId());
		CustomerPriceList list = FittingDao.getPriceListItemById(plID);
		if (list == null) {
			list = getEmptyInstance(type, dim, customer);
		}
		return list;
	}

	public CustomerPriceList(FittingType fittingType, TubeDim tubeDim, BigDecimal standardPrice, Customer customer) {
		setFittingType(fittingType);
		setTubeDim(tubeDim);
		setStandardPrice(standardPrice);
		setCustomer(customer);
	}

	public CustomerPriceList() {
	}

	public CustomerPriceListId getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(CustomerPriceListId priceListId) {
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		this.priceListId.setCustomerId(customer.getCustomerId());
	}
	
	public String getLastPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLastPriceInvoice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Embeddable
	public static class CustomerPriceListId implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column(name = "fittingTypeOrderCode")
		private int fittingTypeOrderCode;

		@Column(name = "tubeDimOrderCode")
		private int tubeDimOrderCode;
		
		@Column(name= "customerId")
		private int customerId;

		/**
		 * required no-arg constructor
		 */
		public CustomerPriceListId() {
		}

		public CustomerPriceListId(int fittingTypeOrderCode, int tubeDimOrderCode, int customerId) {
			this.fittingTypeOrderCode = fittingTypeOrderCode;
			this.tubeDimOrderCode = tubeDimOrderCode;
			this.customerId = customerId;
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
		
		public int getCustomerId() {
			return customerId;
		}
		
		public void setCustomerId(int cusotmerId) {
			this.customerId = cusotmerId;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof CustomerPriceListId))
				return false;

			final CustomerPriceListId other = (CustomerPriceListId) o;
			StringBuffer buffer = new StringBuffer();
			buffer.append(fittingTypeOrderCode).append(tubeDimOrderCode).append(customerId);

			String thisSignature = buffer.toString();

			buffer = new StringBuffer();
			buffer.append(other.fittingTypeOrderCode).append(other.tubeDimOrderCode).append(other.customerId);

			String otherSignature = buffer.toString();

			return thisSignature.equals(otherSignature);
		}

		@Override
		public int hashCode() {
			StringBuffer buffer = new StringBuffer();
			buffer.append(fittingTypeOrderCode).append(tubeDimOrderCode).append(customerId);
			return buffer.toString().hashCode();
		}
	}
}
