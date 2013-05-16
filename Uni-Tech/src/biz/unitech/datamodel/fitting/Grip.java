package biz.unitech.datamodel.fitting;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Grip implements Comparable<Grip> {
	
	public static transient final String NAME_SYMBOL = "GR";

	@Id
	private int gripOrderCode;

	@Column ( name="gripName", length=3)
	private String gripName;

	@Column(name = "price", precision = 8, scale = 2)
	private BigDecimal price;
	
	public Grip() {}

	public Grip(int gripOrderCode, String gripName, BigDecimal price) {
		this.gripOrderCode = gripOrderCode;
		this.gripName = gripName;
		this.price = price;
	}

	public Grip(int code, String name) {
		this(code, name, new BigDecimal(0));
	}

	public String getGripName() {
		return gripName;
	}
	
	public void setGripName(String gripName) {
		this.gripName = gripName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public int getGripOrderCode() {
		return gripOrderCode;
	}
	
	public void setGripOrderCode(int gripOrderCode) {
		this.gripOrderCode = gripOrderCode;
	}

	public String getOrderCodeAsString() {
		return Integer.toString(gripOrderCode);
	}

	@Override
	public int compareTo(Grip compareGrip) {
		int compareGripOrderCode = compareGrip.getGripOrderCode();
		return this.getGripOrderCode() - compareGripOrderCode;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof Grip))
			return false;

		Grip grip = (Grip) obj;
		
		return gripOrderCode == grip.getGripOrderCode();
	}
	
	@Override
	public int hashCode() {
		return gripOrderCode;
	}
}