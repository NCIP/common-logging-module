/*
 * HibernateUtil.java
 * Created on Nov 23, 2004
 */
package gov.nih.nci.logging.api.util;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    private static Logger log = Logger.getLogger(HibernateUtil.class);

    private static final SessionFactory sessionFactory;

    static {
        try {
        	
            // Create the SessionFactory
        	sessionFactory = ((SessionFactory)ObjectFactory.getObject(ObjectFactory.HibernateSessionFactory));
        } catch (Throwable ex) {
            log.error("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static final ThreadLocal session = new ThreadLocal();

    public static Session currentSession() throws HibernateException {
        Session s = (Session) session.get();
        // Open a new Session, if this Thread has none yet
        if (s == null) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        session.set(null);
        if (s != null)
            s.close();
    }
}

