package biz.unitech.datamodel.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import biz.unitech.datamodel.FittingType;
import biz.unitech.datamodel.Oring;

public class DatabaseUtils {

	public static void save(Object object) {
		Session session = biz.unitech.datamodel.test.HibernateService.getSessionFactory()
				.getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(object);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}

	}
	
	public static void update(Object object) {
		Session session = biz.unitech.datamodel.test.HibernateService.getSessionFactory()
				.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}

	}

	public static Object retrieveFirst(Class klass) {
		Session session = biz.unitech.datamodel.test.HibernateService.getSessionFactory()
				.getCurrentSession();
		Object retrieved = null;
		try {
			session.beginTransaction();

			Query q = session.createQuery("from " + klass.getName());
			List list = q.list();

			if (list.isEmpty()) {
				fail("Object not stored");
			}

			retrieved = list.get(0);

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return retrieved;
	}

	public static Object count(Class klass) {
		Session session = biz.unitech.datamodel.test.HibernateService.getSessionFactory()
				.getCurrentSession();
		Object result = null;
		try {
			session.beginTransaction();

			result = session.createCriteria(klass).setProjection(Projections.rowCount()).uniqueResult();

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return result;
	}

	public static void delete(Class klass) {
		Session session = biz.unitech.datamodel.test.HibernateService.getSessionFactory()
				.getCurrentSession();
		try {
			session.beginTransaction();

			Query q = session.createQuery("delete from " + klass.getName());
			q.executeUpdate();

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	public static void delete(Object object) {
		Session session = biz.unitech.datamodel.test.HibernateService.getSessionFactory()
				.getCurrentSession();
		try {
			session.beginTransaction();

			session.delete(object);

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
}
