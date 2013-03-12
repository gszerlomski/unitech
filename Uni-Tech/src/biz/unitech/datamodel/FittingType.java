package biz.unitech.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FittingType {

	public static transient final CharSequence NAME_SYMBOL = "TY";

	@Id
	private int fittingTypeOrderCode;
	
	@Column ( name="fittingTypeName", length=6)
	private String fittingTypeName;
	
	private String nameFormat;

	private transient String orderCode;

	private int gripNumber;

	public FittingType() {}	
	
	public FittingType(int fittingTypeOrderCode, String fittingTypeName,
			String nameFormat) {
		super();
		this.fittingTypeOrderCode = fittingTypeOrderCode;
		this.fittingTypeName = fittingTypeName;
		this.nameFormat = nameFormat;
	}
	
	public FittingType(int fittingTypeOrderCode, String fittingTypeName,
			String nameFormat, int gripNumber) {
		super();
		this.fittingTypeOrderCode = fittingTypeOrderCode;
		this.fittingTypeName = fittingTypeName;
		this.nameFormat = nameFormat;
		this.gripNumber = gripNumber;
	}

	public String getFittingTypeName() {
		return fittingTypeName;
	}
	
	public void setFittingTypeName(String fittingTypeName) {
		this.fittingTypeName = fittingTypeName;
	}

	public String getNameFormat() {
		return nameFormat;
	}

	public void setNameFormat(String nameFormat) {
		this.nameFormat = nameFormat;
	}
	
	public int getFittingTypeOrderCode() {
		return fittingTypeOrderCode;
	}
	
	public void setFittingTypeOrderCode(int fittingTypeOrderCode) {
		this.fittingTypeOrderCode = fittingTypeOrderCode;
	}

	public String getOrderCodeAsString() {
		if(orderCode == null) {
			StringBuffer buffer = new StringBuffer();
			for (int i = fittingTypeOrderCode; i < 1000; i*=10) {
				buffer.append("0");
			}
			buffer.append(fittingTypeOrderCode);
			orderCode = buffer.toString();
		}
		return orderCode;
	}

	public int getGripNumber() {
		return gripNumber;
	}
	
	public void setGripNumber(int gripNumber) {
		this.gripNumber = gripNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof FittingType))
			return false;

		FittingType fType = (FittingType) obj;
		
		return fittingTypeOrderCode == fType.getFittingTypeOrderCode();
	}
	
	@Override
	public int hashCode() {
		return fittingTypeOrderCode;
	}
}