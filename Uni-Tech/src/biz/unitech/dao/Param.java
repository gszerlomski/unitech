package biz.unitech.dao;

public class Param {
	
	private String name;
	
	private String value;
	
	public Param() {}
	
	public Param(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public Param(String name, int value) {
		this.name = name;
		this.value = String.valueOf(value);
	}

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

}
