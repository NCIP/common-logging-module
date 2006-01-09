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

public class HibernateSessionFactoryHelper
{

    /**
     * Private Constructor to aviod intialization
     */
    private HibernateSessionFactoryHelper()
    {
    }

	/**
	 * @param sessionFactory TODO
	 * @return - Returns a Hibernate session that can be used for object auditing
	 * @throws HibernateException
	 */
	public static Session getAuditSession(SessionFactory sessionFactory) throws HibernateException
	{
		return sessionFactory.openSession(new ObjectStateInterceptor());
	}

	/**
	 * @return - Returns a regular Hibernate session
	 * @throws HibernateException
	 */
	public static Session getDefaultAuditSession() throws HibernateException
	{
	   Configuration cfg = new Configuration();
	   return cfg.configure().buildSessionFactory().openSession(new ObjectStateInterceptor());
	}
	
}
