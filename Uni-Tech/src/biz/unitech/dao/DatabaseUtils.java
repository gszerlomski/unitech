package biz.unitech.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.TransactionException;

public class DatabaseUtils {
	
	private static Logger logger = Logger.getLogger(DatabaseUtils.class);

	protected static List<Object> getAll(Class klass) {
		return findByParam(klass, new Param[]{});
	}

	protected static String buildWhereClause(Param[] params) {

		StringBuffer buffer = new StringBuffer();
		if (params.length > 0) {
			buffer.append(" where ");

			for (int i = 0; i < params.length; i++) {
				buffer.append(params[i].getName()).append(" = ")
						.append(":" + params[i].getName());
				if (i + 1 < params.length) {
					buffer.append(" and ");
				}
			}
		}
		return buffer.toString();
	}

	protected static List<Object> findByParam(Class klass, Param[] params) {
		Session session = HibernateService.getSessionFactory()
				.getCurrentSession();
		try {
			session.beginTransaction();

			Query q = session.createQuery("from " + klass.getName()
					+ buildWhereClause(params));
			for (Param param : params) {
				param.addParameter(q);
			}
			List list = q.list();

			session.getTransaction().commit();
			return list;
		} catch (RuntimeException e) {
			try {
				session.getTransaction().rollback();
			} catch (TransactionException ex) {
				logger.error(e);
			}
		}
		return new ArrayList<Object>(0);
		
	}

	protected static Object getById(Class klass, Serializable id) {
		
		Session session = HibernateService.getSessionFactory()
				.getCurrentSession();
		try {
			session.beginTransaction();

			Object result = session.get(klass, id);

			session.getTransaction().commit();
			return result;
		} catch (RuntimeException e) {
			try {
				session.getTransaction().rollback();
			} catch (TransactionException ex) {
				logger.error(e);
			}
		}
		return null;
	}
	
	protected static void saveOrUpdate (Object object) {
		Session session = HibernateService.getSessionFactory()
				.getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(object);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			try {
				session.getTransaction().rollback();
				logger.error(e);
			} catch (TransactionException ex) {
				logger.error(e);
			}
		}
	}

	protected static void update (Object object) {
		Session session = HibernateService.getSessionFactory()
				.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			try {
				session.getTransaction().rollback();
			} catch (TransactionException ex) {
				logger.error(e);
			}
		}
	}
}
