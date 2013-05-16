package biz.unitech.dao;

public class Dao {
	
	public static void saveOrUpdate(Object object) throws DatabaseException {
		DatabaseUtils.saveOrUpdate(object);
	}
	
	public static void update(Object object) throws DatabaseException {
		DatabaseUtils.update(object);
	}
}