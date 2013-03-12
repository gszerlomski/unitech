package biz.unitech.dao;

import org.hibernate.Query;

public class IntegerParameter extends Param {

	private int value;
	
	public IntegerParameter(String name, int value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void addParameter(Query q) {
		q.setInteger(name, value);
	}

}
