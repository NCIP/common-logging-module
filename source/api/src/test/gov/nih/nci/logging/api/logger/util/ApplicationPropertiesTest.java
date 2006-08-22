package test.gov.nih.nci.logging.api.logger.util;

import gov.nih.nci.logging.api.logger.util.ApplicationProperties;
import junit.framework.TestCase;

public class ApplicationPropertiesTest extends TestCase {
	
	
	public void testApplicationProperties(){
	
		ApplicationProperties applicationProperties = ApplicationProperties.getInstance("api/resources/sample_ObjectStateLoggerConfig.xml");
		assertTrue("ApplicationProperties instance should not be null.", applicationProperties!=null);
		
	}
	public void testApplicationProperties_ObjectStateLoggerName(){
		String s = ApplicationProperties.getInstance("api/resources/sample_ObjectStateLoggerConfig.xml").getLoggerName();
		assertTrue("Logger Name  value should not be null.",s!=null);
		
	}
	
	public void testApplicationProperties_LoggerConfigurationFile(){
		assertTrue("Config File  value should not be null.", ApplicationProperties.getInstance("api/resources/sample_ObjectStateLoggerConfig.xml").getConfigFile()!=null);
		
	}
	public void testApplicationProperties_LogLevel(){
		assertTrue(" LogLevel value should not be null.", ApplicationProperties.getInstance("api/resources/sample_ObjectStateLoggerConfig.xml").getLogLevel()!=null);
		
	}
	public void testApplicationProperties_LoggingEnabled(){
		boolean loggingenabled = ApplicationProperties.getInstance("api/resources/sample_ObjectStateLoggerConfig.xml").isLoggingEnabled();
		assertTrue(loggingenabled);
	}
	public void testApplicationProperties_ObjectStateLoggerMessageFormat(){
		assertTrue(" MessageLoggingFormat value should not be null.", ApplicationProperties.getInstance("api/resources/sample_ObjectStateLoggerConfig.xml").getMessageLoggingFormat()!=null);
	}
	public void testApplicationProperties_DomainObjectList(){
		Object obj = new Object();
		String s= new String();
		assertTrue(ApplicationProperties.getInstance("api/resources/sample_ObjectStateLoggerConfig.xml").isObjectStateLoggingEnabled(obj));
		
		assertTrue(ApplicationProperties.getInstance("api/resources/sample_ObjectStateLoggerConfig.xml").isObjectStateLoggingEnabled(s)==false);
	}
	public void testApplicationProperties_IdentifierAttributeMap(){	
		Object obj = new Object();
		
		assertTrue(" Identifier Attribute value should not be null.", ApplicationProperties.getInstance("api/resources/sample_ObjectStateLoggerConfig.xml").getIdentifierAttribute(obj)!=null);
	}
	public void testApplicationProperties_IdentifierAttribute_(){
		String s = new String();
		assertTrue(" Identifier Attribute value should be null.", ApplicationProperties.getInstance("api/resources/sample_ObjectStateLoggerConfig.xml").getIdentifierAttribute(s)==null);
	}
	
	
	/**
	 * @param name
	 */
	public ApplicationPropertiesTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}


	
}
