package gov.nih.nci.logging.api.logger.hibernate;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * <!-- LICENSE_TEXT_END -->
 */

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.*;
import org.hibernate.type.Type;

import gov.nih.nci.logging.api.logger.util.ApplicationProperties;
import gov.nih.nci.logging.api.logger.util.MessageGenerator;
import gov.nih.nci.logging.api.logger.util.ThreadVariable;
import gov.nih.nci.logging.api.user.UserInfo;

/**
 * @author Ekagra Software Technologes Limited ('Ekagra')
 * 
 */

public class ObjectStateLogger
{
	private static Logger logger = null;
	private static ObjectStateLogger myInstance = null;

	private ObjectStateLogger()
	{	 
		logger = Logger.getLogger(ApplicationProperties.getInstance().getLoggerName());
	}

	/**
	 * This method logs the message for update operation
	 * 
	 * @param obj --
	 *            Object to be audited
	 * @param id --
	 *            Serializable id of the object
	 * @param currentState --
	 *            current states of the object after the operation
	 * @param previousState --
	 *            previous states of the object before the operation
	 * @param propertyNames
	 *            --names of the object states
	 * @param types --
	 *            Hibernate types of the object states
	 * @param operation --
	 *            the name of the operation being performed
	 * 
	 */
	public  void logMessage(Object obj, Serializable id, Object[] currentState, Object[] prevState, String[] propertyNames, Type[] types, String operation)
	{
		if (ApplicationProperties.getInstance().isLoggingEnabled())
		{						
			System.out.println("isEnabled " + ApplicationProperties.getInstance().isObjectStateLoggingEnabled(obj));
			if (ApplicationProperties.getInstance().isObjectStateLoggingEnabled(obj) == true)
			{
				if (ApplicationProperties.getInstance().getMessageLoggingFormat().equalsIgnoreCase(ApplicationProperties.messageLoggingStringFormat)){
					String myMessage = null;
					myMessage = MessageGenerator.generateStringMessage(obj, id, currentState, prevState, propertyNames, types, operation);
					logMessage(myMessage);
					}
				else if (ApplicationProperties.getInstance().getMessageLoggingFormat().equalsIgnoreCase(ApplicationProperties.messageLoggingXMLFormat))
				{
					String outfileName = obj.getClass().getName() + System.currentTimeMillis() + ".xml";
					MessageGenerator.generateXMLMessage(obj, id, currentState, prevState, propertyNames, types, operation, outfileName);
				}
			}
		}
	}

	/**
	 * This method does the actual logging 
	 * @param message --
	 *            message to be logged
	 */
	public  void logMessage(String message)
	{
		UserInfo userInfo = (UserInfo) ThreadVariable.get();
		if (null == userInfo)
			userInfo = new UserInfo();
		if (userInfo.getIsIntransaction() == true)
		{
			logToBuffer(message);
		}
		else
		{
			log(message);
			System.out.println("-- start to write the msgs to database for user: " + userInfo.getUsername());
			System.out.println(message);
		}
	}

	/**
	 * This method saves the message to the buffer for later use
	 * @param msg --
	 *            message to be logged
	 */
	public  void logToBuffer(String msg)
	{
		 
			UserInfo userInfo = (UserInfo) ThreadVariable.get();
			if (null == userInfo)
				userInfo = new UserInfo();
			ArrayList logs = userInfo.getTransactionLogs();
			if (logs == null)
			{
				logs = new ArrayList();
			}

			logs.add(msg);
			userInfo.setTransactionLogs(logs);
			ThreadVariable.set(userInfo);		 

	}

	/**
	 * This method logs the message
	 * 
	 * @param message --  message to be logged
	 */
	public  void log(String message)
	{
		Level level = Level.toLevel(ApplicationProperties.getInstance().getLogLevel().toUpperCase());	 
		logger.log(level, message);
	}

	/**
	 * @return -- Returns the singleton of this class
	 */
	public static ObjectStateLogger getInstance()
	{
		if (myInstance == null)
		{
			myInstance = new ObjectStateLogger();
		}
		return myInstance;
	}

}
