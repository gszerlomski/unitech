package biz.unitech.datamodel.fitting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Adaptor {
	
	public static transient final String NAME_SYMBOL = "AD";

	@Id
	private int adaptorOrderCode;
	
	private transient String orderCode;
	
	@Column ( name="adaptorName", length=7)
	private String adaptorName;
	
	public Adaptor() {}
	
	public Adaptor(int adaptorOrderCode, String adaptorName) {
		super();
		this.adaptorOrderCode = adaptorOrderCode;
		this.adaptorName = adaptorName;
	}

	public String getAdaptorName() {
		return adaptorName;
	}
	
	public void setAdaptorName(String adaptorName) {
		this.adaptorName = adaptorName;
	}
	
	public int getAdaptorOrderCode() {
		return adaptorOrderCode;
	}
	
	public void setAdaptorOrderCode(int adaptorOrderCode) {
		this.adaptorOrderCode = adaptorOrderCode;
	}

	public String getOrderCodeAsString() {
		if(orderCode == null) {
			StringBuilder builder = new StringBuilder(adaptorOrderCode<10? "0" : "");
			builder.append(adaptorOrderCode);
			orderCode = builder.toString();
		}
		return orderCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof Adaptor))
			return false;

		Adaptor adaptor = (Adaptor) obj;
		
		return adaptorOrderCode == adaptor.getAdaptorOrderCode();
	}
	
	@Override
	public int hashCode() {
		return adaptorOrderCode;
	}
}