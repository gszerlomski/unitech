package biz.unitech.dao;

import org.hibernate.Query;

public class StringParam extends Param {

	private String value;
	
	protected StringParam(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void addParameter(Query q) {
		q.setString(name, value);
	}

}
