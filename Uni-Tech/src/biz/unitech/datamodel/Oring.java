package biz.unitech.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Oring {

	public static transient final String NAME_SYMBOL = "OR";

	@Id
	private int oringOrderCode;

	private transient String orderCodeString;

	@Column(name = "oringName", length = 4)
	private String oringName;

	public Oring() {
	}

	public Oring(int oringOrderCode, String oringName) {
		super();
		this.oringOrderCode = oringOrderCode;
		this.oringName = oringName;
	}

	public String getOringName() {
		return oringName;
	}

	public void setOringName(String oringName) {
		this.oringName = oringName;
	}

	public int getOringOrderCode() {
		return oringOrderCode;
	}

	public void setOringOrderCode(int oringOrderCode) {
		this.oringOrderCode = oringOrderCode;
	}

	public String getOrderCodeAsString() {
		if (orderCodeString == null) {
			orderCodeString = Integer.toString(oringOrderCode);
		}
		return orderCodeString;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof Oring))
			return false;

		Oring oring = (Oring) obj;
		
		return oringOrderCode == oring.getOringOrderCode();
	}
	
	@Override
	public int hashCode() {
		return oringOrderCode;
	}
}
