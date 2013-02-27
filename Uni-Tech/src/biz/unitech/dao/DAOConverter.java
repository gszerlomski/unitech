package biz.unitech.dao;

import java.util.ArrayList;
import java.util.List;

public class DAOConverter<T> {
	
	public List<T> convertToList(List<Object> objects) {
		List<T> list = new ArrayList<T>(objects.size());
		for (Object obj : objects) {
			list.add((T)obj);
		}
		return list;
	}

}
