package gov.nih.nci.logging.api.util;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;


/**
 * ObjectFactory
 *
 */
public class ObjectFactory
{
	private static XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("clm_spring-config.xml"));;
	
	public static final String LogMessageDAO = "LogMessageDAO";
	public static final String HibernateSessionFactory= "HibernateSessionFactory";
	public static final String TestDAO = "TestDAO";
	
	private ObjectFactory()
	{
	}

	/**
	 * Returns an instance of the Object Factory. Since it is implemented as a singleton, 
	 * only one instance will be created during the lifecycle of JVM 
	 * @return
	 */

	/**
	 *  get Object instance from the class name.
	 * @param classname
	 * @return
	 */
	public static Object getObject(String classname){
		
		return  factory.getBean(classname);
	}
	

	
	
}