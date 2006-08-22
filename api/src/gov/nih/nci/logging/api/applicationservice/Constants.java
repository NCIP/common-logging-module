package gov.nih.nci.logging.api.applicationservice;

/**
 *  Constants interface.
 *  
 * To define constants that are available to client as well as the API's.
 * 
 * @author parmarv
 */
public interface Constants {

	
	// Log Message fields.
	 static String _APPLICATION = "application";
	 static String _LOG_LEVEL = "category";
	 static String _DATE = "created_on";
	 static String _OBJECT_ID = "object_id";
	 static String _OBJECT_NAME = "object_name";
	 static String _OPERATION = "operation";
	 static String _ORGANIZATION = "organization";
	 static String _PRIORITY = "priority";
	 static String _SERVER = "server";
	 static String _SESSION_ID = "sessionID";
	 static String _THROWABLE = "throwable";
	 static String _USER = "user";
	 static String _MESSAGE = "message";
	 static String _NDC = "ndc";
	 static String _THREAD = "thread";
	 

	// Sort Parameters
	public static String SORT_BY_PARAMETER_APPLICATION = "application";
	public static String SORT_BY_PARAMETER_LOG_LEVEL = "category";
	public static String SORT_BY_PARAMETER_DATE = "created_on";
	public static String SORT_BY_PARAMETER_OBJECT_ID = "object_id";
	public static String SORT_BY_PARAMETER_OBJECT_NAME = "object_name";
	public static String SORT_BY_PARAMETER_OPERATION = "operation";
	public static String SORT_BY_PARAMETER_ORGANIZATION = "organization";
	public static String SORT_BY_PARAMETER_PRIORITY = "priority";
	public static String SORT_BY_PARAMETER_SERVER = "server";
	public static String SORT_BY_PARAMETER_SESSION_ID = "sessionID";
	public static String SORT_BY_PARAMETER_THROWABLE = "throwable";
	public static String SORT_BY_PARAMETER_USER = "user";

	
	//	Log Type
	public static String EVENT_LOG_TYPE = "ascending";
	public static String OBJECT_STATE_LOG_TYPE= "descending";
	
	// Sort Order
	public static String SORT_ORDER_ASCENDING = "ascending";
	public static String SORT_ORDER_DESCENDING= "descending";

}
