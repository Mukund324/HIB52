package net.scm.model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	  private static StandardServiceRegistry registry;
	  private static SessionFactory sessionFactory;

	  public static SessionFactory getSessionFactory() {
	    if (sessionFactory == null) {
	      try {
	        // Create registry
	        registry = new StandardServiceRegistryBuilder()
	            .configure("net/scm/model/hibernate.cfg.xml")
	            .build();

	        // Create MetadataSources
	        MetadataSources sources = new MetadataSources(registry);

	        // Create Metadata
	        Metadata metadata = sources.getMetadataBuilder().build();

	        // Create SessionFactory
	        sessionFactory = metadata.getSessionFactoryBuilder().build();

	      } catch (Exception e) {
	        e.printStackTrace();
	        if (registry != null) {
	          StandardServiceRegistryBuilder.destroy(registry);
	        }
	      }
	    }
	    return sessionFactory;
	  }

	  public static void shutdown() {
	    if (registry != null) {
	      StandardServiceRegistryBuilder.destroy(registry);
	    }
	  }
	}

/*
public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	private static ServiceRegistry serviceRegistry;

	static {

		try {

			Configuration configuration = new Configuration();
			System.err.println("Config Created successfully");
            configuration.configure("net/scm/model/hibernate.cfg.xml");
            System.err.println("Config Read successfully");
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
					configuration.getProperties()).getBootstrapServiceRegistry();
			System.err.println("Service Registry created successfully");
			//sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
			System.err.println("Session Factory created successfully");

		} catch (Throwable th) {

			System.err.println("Initial SessionFactory creation failed " + th);

			throw new ExceptionInInitializerError(th);

		}

	}

	public static SessionFactory getSessionFactory() {

		return sessionFactory;

	}

	

}
*/