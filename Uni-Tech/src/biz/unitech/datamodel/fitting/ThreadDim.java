package biz.unitech.datamodel.fitting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ThreadDim {
	
	public static transient final String NAME_SYMBOL = "TH";

	@Id
	private int threadDimOrderCode;
	
	@Column ( name="threadDimName", length=7)
	private String threadDimName;

	private transient String orderCode;
	
	public ThreadDim() {}
	
	public ThreadDim(int code, String name) {
		this.threadDimName = name;
		this.threadDimOrderCode = code;
	}

	public String getThreadDimName() {
		return threadDimName;
	}
	
	public void setThreadDimName(String threadDimName) {
		this.threadDimName = threadDimName;
	}
	
	public int getThreadDimOrderCode() {
		return threadDimOrderCode;
	}
	
	public void setThreadDimOrderCode(int threadDimOrderCode) {
		this.threadDimOrderCode = threadDimOrderCode;
	}

	public String getOrderCodeAsString() {
		if(orderCode == null) {
			StringBuilder builder = new StringBuilder(threadDimOrderCode<10? "0" : "");
			builder.append(threadDimOrderCode);
			orderCode = builder.toString();
		}
		return orderCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ThreadDim))
			return false;

		ThreadDim tDim = (ThreadDim) obj;
		
		return threadDimOrderCode == tDim.getThreadDimOrderCode();
	}
	
	@Override
	public int hashCode() {
		return threadDimOrderCode;
	}
}
