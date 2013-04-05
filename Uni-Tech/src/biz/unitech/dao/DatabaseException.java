package biz.unitech.dao;

public class DatabaseException extends Exception {
	
	public DatabaseException() {
		super();
	}
	
	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(String string, Exception e) {
		super(string, e);
	}
}