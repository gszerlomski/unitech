package biz.unitech.datamodel.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import biz.unitech.datamodel.fitting.Adaptor;
import biz.unitech.datamodel.fitting.Fitting;
import biz.unitech.datamodel.fitting.FittingType;
import biz.unitech.datamodel.fitting.Grip;
import biz.unitech.datamodel.fitting.Oring;
import biz.unitech.datamodel.fitting.ThreadDim;
import biz.unitech.datamodel.fitting.TubeDim;
import biz.unitech.datamodel.orders.SupplierPriceList;
import biz.unitech.datamodel.orders.Supplier;
import biz.unitech.datamodel.orders.SupplierOrder;
import biz.unitech.datamodel.orders.SupplierOrderLineItem;

public class HibernateService {
	
	private static final ServiceRegistry serviceRegistry;
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			Configuration config = new Configuration();
			config
				.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
				.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
				.setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:baseball")
				.setProperty("hibernate.connection.username", "sa")
				.setProperty("hibernate.connection.password", "")
				.setProperty("hibernate.connection.pool_size", "1")
				.setProperty("hibernate.connection.autocommit", "false")
				.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.HashtableCacheProvider")
				.setProperty("hibernate.hbm2ddl.auto", "create-drop")
				.setProperty("hibernate.current_session_context_class", "thread")
				.setProperty("hibernate.show_sql", "true")
				.setProperty("hibernate.hbm2ddl.import_files", "insert.sql");
			config
				.addAnnotatedClass(Fitting.class)
				.addAnnotatedClass(FittingType.class)
				.addAnnotatedClass(Grip.class)
				.addAnnotatedClass(Oring.class)
				.addAnnotatedClass(SupplierPriceList.class)
				.addAnnotatedClass(Supplier.class)
				.addAnnotatedClass(SupplierOrder.class)
				.addAnnotatedClass(SupplierOrderLineItem.class)
				.addAnnotatedClass(ThreadDim.class)
				.addAnnotatedClass(Adaptor.class)
				.addAnnotatedClass(TubeDim.class);
			
			
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
					config.getProperties()).buildServiceRegistry();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	protected static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}