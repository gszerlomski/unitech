package biz.unitech.datamodel.orders;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Supplier {
	
	@Id
	@GeneratedValue
	int supplierId;
	
	String supplierName;
	
	String supplierAddress;
	
	String supplierContactNum;
	
	String supplierNIP;

	private BigDecimal discount;
	
	public Supplier() {}

	public Supplier(int supplierId, String supplierName,
			String supplierAddress, String supplierContactNum,
			String supplierNIP) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierAddress = supplierAddress;
		this.supplierContactNum = supplierContactNum;
		this.supplierNIP = supplierNIP;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierContactNum() {
		return supplierContactNum;
	}

	public void setSupplierContactNum(String supplierContactNum) {
		this.supplierContactNum = supplierContactNum;
	}

	public String getSupplierNIP() {
		return supplierNIP;
	}

	public void setSupplierNIP(String supplierNIP) {
		this.supplierNIP = supplierNIP;
	}

	public BigDecimal getDiscount() {
		return discount;
	}
	
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
}