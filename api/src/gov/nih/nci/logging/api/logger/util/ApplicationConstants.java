package gov.nih.nci.logging.api.logger.util;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * @author Ekagra Software Technologes Limited ('Ekagra')
 * 
 * ApplicationConstants includes for managing the property file and message contents.
 * 
 */

public interface ApplicationConstants
{

	// Logger ApplicationConstants.
	
	public static final String ObjectStateLoggerConfigFile = "Logger";
	public static final String ApplicationPropertyFile = "ObjectStateLoggerConfig.xml";
	
	
	// Object State Logger Configuration
	
	public static final String OBJECT_STATE_LOGGER_CONFIG = "ObjectStateLoggerConfig"; //Root Element
	public static final String LOGGER_CONFIGURATION_FILE = "LoggerConfigurationFile";
	
	public static final String OBJECT_STATE_LOGGER_MESSAGE_FORMAT = "ObjectStateLoggerMessageFormat";
	public static final String OBJECT_STATE_LOGGER_MESSAGE_FORMAT_STRING = "STRING";
	public static final String OBJECT_STATE_LOGGER_MESSAGE_FORMAT_XML = "XML";
	
	public static final String OBJECT_STATE_LOGGER_NAME= "ObjectStateLoggerName";
	public static final String LOG_LEVEL = "LogLevel";
	
	public static final String LOGGING_ENABLED = "LoggingEnabled";
	public static final String LOGGING_ENABLED_TRUE = "TRUE";
	public static final String LOGGING_ENABLED_FALSE = "FALSE";
	
	public static final String DOMAIN_OBJECT_LIST= "DomainObjectList";
	public static final String DOMAIN_OBJECT= "DomainObject";
	public static final String OBJECT_NAME= "ObjectName";
	public static final String IDENTIFIER_ATTRIBUTE = "IdentifierAttribute";
	
	// Message Constants	
	
	public static final String OBJECT_STATE_MESSAGE_OPERATION = "operation";
	public static final String OBJECT_STATE_MESSAGE_OBJECT = "object";
	public static final String OBJECT_STATE_MESSAGE_PREVIOUS_ATTRIBUTES = "previousAttributes";
	public static final String OBJECT_STATE_MESSAGE_CURRENT_ATTRIBUTES = "currentAttributes";
	public static final String OBJECT_STATE_MESSAGE_ATTRIBUTES = "attributes";
	
	public static final String OBJECT_STATE_MESSAGE_OPERATION_NAME= "name";
	public static final String OBJECT_STATE_MESSAGE_OPERATION_COMMENT= "comment";
	public static final String OBJECT_STATE_MESSAGE_OPERATION_OBJECT_NAME= "objName";
	public static final String OBJECT_STATE_MESSAGE_OPERATION_OBJECT_IDENTIFIER_ATTRIBUTE_VALUE= "objID";
	
	
	
	
	
	
	
	

}
