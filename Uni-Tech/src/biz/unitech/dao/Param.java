package biz.unitech.dao;

import org.hibernate.Query;

public abstract class Param {
	
	public static Param getInstance(String name, boolean value) {
		return new BooleanParam(name, value);
	}
	
	public static Param getInstance(String name, String value) {
		return new StringParam(name, value);
	}
	
	public static Param getInstance(String name, int value) {
		return new IntegerParameter(name, value);
	}
	
	protected String name;
	
	public String getName() {
		return name;
	}
	
	public abstract Object getValue();
	
	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void addParameter(Query q);
}
