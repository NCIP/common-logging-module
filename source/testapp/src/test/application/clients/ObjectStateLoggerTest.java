/**
 * 
 */
package test.application.clients;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
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
		Thread x[] = new Thread[10];
		for (int i = 0; i < 1; i++)
		{
			x[i] = new ClientThread(i + "", i);
		}

		for (int i = 0; i < 1; i++)
		{
			x[i].start();
		}

		// x[1].notify();

		for (int i = 0; i < 1; i++)
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
