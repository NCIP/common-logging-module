package gov.nih.nci.logging.webapp.util;

import gov.nih.nci.logging.api.logger.util.PropertyFileLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;



public class SecurityConfiguration
{

	private static HashMap securedAttributes  = null;
	
	private static Document getConfigDocument() throws Exception
	{
		
		InputStream inputStream = PropertyFileLoader.getInstance().loadPropertyFile("gov.nih.nci.logging.webapp.SecurityConfig.xml");
        SAXBuilder builder = new SAXBuilder();        
        try
		{
        	return builder.build(inputStream);
		}
		catch (JDOMException e)
		{
			throw new Exception("Error in parsing the Security Config file");
		}
		catch (IOException e)
		{
			throw new Exception("Error in reading the Security Config file");
		}
	}
	
	private static List getElementList(String applicationName) throws Exception
	{
		if (null == securedAttributes)
		{
			securedAttributes = new HashMap();
			Document document = getConfigDocument();
			Element securityConfig = document.getRootElement();
			Element applicationList = securityConfig.getChild("application-list");
			List applications = applicationList.getChildren("application");
			Iterator applicationIterator = applications.iterator();
			 while(applicationIterator.hasNext())
			 {
				Element application = (Element)applicationIterator.next();
				Element applicationNameElement = application.getChild("name");
				String applicationNameValue = applicationNameElement.getTextTrim();
				List attributes = application.getChildren("secured-attribute");
				Iterator attributesIterator = attributes.iterator();
				List attributeList = new ArrayList();
				while(attributesIterator.hasNext())
				{
					Element attributesName = (Element) attributesIterator.next();
					attributeList.add(attributesName.getTextTrim());
				}
				securedAttributes.put(applicationNameValue, attributeList);
			 }
		}
		return (List)securedAttributes.get(applicationName);
	}

	public static boolean isElementSecured(String applicationName, String attributeName) throws Exception
	{
		if (attributeName.equals(Constants.APPLICATION_NAME_ATTRIBUTE))
			return true;
		else
		{
			List elementList = getElementList(applicationName);
			if (null != elementList)
				return elementList.contains(attributeName);
			else 
				return false;
		}
	}
}
