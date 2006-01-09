/**
 * 
 */
package test.application.clients;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import gov.nih.nci.logging.api.logger.util.ApplicationProperties;

/**
 * @author liany
 * 
 */
public class ObjectStateLoggerTest
{
	static {
	String log4jConfigFile = ApplicationProperties.getInstance().getConfigFile();		
	if (log4jConfigFile.toUpperCase().endsWith(".XML"))
	{
		DOMConfigurator.configure(ClassLoader.getSystemResource(log4jConfigFile));
	}
	else
	{
		PropertyConfigurator.configure(ClassLoader.getSystemResource(log4jConfigFile));
	}	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Configuration cfg = new Configuration();
		SessionFactory sessionFactory = cfg.configure().buildSessionFactory();
		Thread x[] = new Thread[10];
		for (int i = 0; i < 2; i++)
		{
			x[i] = new ClientThread(i + "", i, sessionFactory);
		}

		for (int i = 0; i < 2; i++)
		{
			x[i].start();
		}

		// x[1].notify();

		for (int i = 0; i < 2; i++)
		{
			try
			{
				x[i].join();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
