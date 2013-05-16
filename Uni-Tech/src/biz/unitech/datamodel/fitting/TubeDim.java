package biz.unitech.datamodel.fitting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TubeDim {

	public static transient final String NAME_SYMBOL = "TU";

	@Id
	private int tubeDimOrderCode;

	@Column(name = "tubeDimName", length = 7)
	private String tubeDimName;

	public TubeDim() {
	}

	public TubeDim(int tubeDimOrderCode, String tubeDimName) {
		super();
		this.tubeDimOrderCode = tubeDimOrderCode;
		this.tubeDimName = tubeDimName;
	}

	public String getTubeDimName() {
		return tubeDimName;
	}

	public void setTubeDimName(String tubeDimName) {
		this.tubeDimName = tubeDimName;
	}

	public int getTubeDimOrderCode() {
		return tubeDimOrderCode;
	}

	public void setTubeDimOrderCode(int tubeDimOrderCode) {
		this.tubeDimOrderCode = tubeDimOrderCode;
	}

	public String getOrderCodeAsString() {
		StringBuilder builder = new StringBuilder(tubeDimOrderCode < 10 ? "0" : "");
		builder.append(tubeDimOrderCode);
		return builder.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof TubeDim))
			return false;

		TubeDim tubeDim = (TubeDim) obj;
		
		return tubeDimOrderCode == tubeDim.getTubeDimOrderCode();
	}
	
	@Override
	public int hashCode() {
		return tubeDimOrderCode;
	}
}
