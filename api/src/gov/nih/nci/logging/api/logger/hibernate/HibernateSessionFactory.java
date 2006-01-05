package gov.nih.nci.logging.api.logger.hibernate;
/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * <!-- LICENSE_TEXT_END -->
 */

import org.hibernate.*;
import org.hibernate.cfg.*;

/**
 * @author Ekagra Software Technologes Limited ('Ekagra')
 * 
 * 
 * This class provides the utility to obtain a Hibernate session for auditing
 * 
 */

public class HibernateSessionFactory
{
	private static SessionFactory sessionFactory = null;
    private static HibernateSessionFactory myInstance = null;
    private HibernateSessionFactory()
	{

		try
		{
			Configuration cfg = new Configuration();
			sessionFactory = cfg.configure().buildSessionFactory();
		}
		catch (Throwable ex)
		{
			ex.printStackTrace(System.out);			
		}		
	}

	

	/**
	 * @return - Returns a Hibernate session that can be used for object auditing
	 * @throws HibernateException
	 */
	public   Session getAuditSession() throws HibernateException
	{
		return sessionFactory.openSession(new ObjectStateInterceptor());
	}

	/**
	 * @return - Returns a regular Hibernate session
	 * @throws HibernateException
	 */
	public   Session getSession() throws HibernateException
	{
		return sessionFactory.openSession();
	}
	
	public static HibernateSessionFactory getInstance (){
		if (myInstance == null)
			myInstance = new HibernateSessionFactory();
		return myInstance;
	}
	

}
