package biz.unitech.dao;

import org.hibernate.exception.ConstraintViolationException;

public class DuplicateEntryException extends Exception {

	private ConstraintViolationException delegate;

	public DuplicateEntryException(ConstraintViolationException e) {
		this.delegate = e;
	}
	
	public DuplicateEntryException(String string) {
		super(string);
	}

	public String getMessage() {
		return delegate.getMessage();
	}
}
