package gov.nih.nci.logging.api.logger.util;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * <!-- LICENSE_TEXT_END -->
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * @author Ekagra Software Technologes Limited ('Ekagra')
 * 
 * This class is a singleton to get property values for logging
 * 
 */

public class ApplicationProperties implements Constants
{
	private static Logger logger = Logger.getLogger(ApplicationProperties.class);
	private static ApplicationProperties myInstance;
	private static List objectNamesList = new ArrayList();

	private static boolean isLoggingEnabled = false;
	private static String messageLoggingFormat = null;
	private static String loggingConfigFile = null;

	private ApplicationProperties()
	{
		try
		{

			InputStream in = PropertyFileLoader.getInstance().loadPropertyFile(ApplicationPropertyFile);
			Document configDocument = null;
			SAXBuilder builder = new SAXBuilder();
			configDocument = builder.build(in);
			Element loggingConfig = configDocument.getRootElement();
			loggingConfigFile = loggingConfig.getChild("logger-config-file").getText().trim();
			messageLoggingFormat = loggingConfig.getChild(ObjectStateLoggerMessageFormat).getText().trim();			
			if (loggingConfig.getChild(ObjectStateLoggingSwitch).getText().trim().equalsIgnoreCase("true"))
			{
				isLoggingEnabled = true;
			}
			else
				isLoggingEnabled = false;
			Element DomainObjectList = loggingConfig.getChild(ObjectStateLoggerDomainObjectAuditingList);
			List objNamesList = DomainObjectList.getChildren("object-name");
			Iterator objIterator = objNamesList.iterator();
			while (objIterator.hasNext())
			{
				Element object = (Element) objIterator.next();
				String objNameValue = object.getText().trim();
				objectNamesList.add(objNameValue);
			}

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			if (logger.isDebugEnabled())
				logger.debug("Error reading the Config File " + ex.getMessage());
		}

	}

	/**
	 * @param obj
	 *            to be determined if audited or not
	 * @return boolean value
	 */
	public boolean isObjectStateLoggingEnabled(Object obj)
	{
		if (objectNamesList.contains(obj.getClass().getName()))
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * @return -- Returns the logging configuration file
	 */
	public String getConfigFile()
	{
		return loggingConfigFile;
	}

	/**
	 * @return returns message logging format
	 */
	public String getMessageLoggingFormat()
	{
		return messageLoggingFormat;
	}

	/**
	 * @return -- Returns the boolean value indicating if the logging is enabled
	 *         or disabled
	 */
	public boolean isLoggingEnabled()
	{
		return isLoggingEnabled;
	}

	/**
	 * @return Returns the single instance of this class
	 */
	public static ApplicationProperties getInstance()
	{
		if (myInstance == null)
		{
			myInstance = new ApplicationProperties();
		}
		logger.info("Inside ApplicationProperties " + myInstance);
		return myInstance;
	}

}
