package biz.unitech.dao;

public class EntityDeletedException extends RuntimeException {

	
	private Class klass;


	public EntityDeletedException(Class klass) {
		this.klass = klass;
	}
	
	
	@Override
	public String toString() {
		return "Entity of type " + klass + "was used after being deleted";
	}
}
