package biz.unitech.dao;

import org.hibernate.Query;

public class BooleanParam extends Param {
	
	private boolean value;

	protected BooleanParam(String name, Boolean value) {
		this.name = name;
		this.value = value;
	}

	protected BooleanParam(String name, boolean value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void addParameter(Query q) {
		q.setBoolean(getName(), value);
	}
}
