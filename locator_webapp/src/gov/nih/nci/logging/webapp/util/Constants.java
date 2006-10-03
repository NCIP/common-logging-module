package gov.nih.nci.logging.webapp.util;


public interface Constants
{

	//Display and Session Constants.
	public static final String USER_NAME = "USER_NAME";
	
	public static final String ALL = "ALL";
	public static final String YES = "YES";
	public static final String NO = "NO";
	public static final String REQUIRED = "REQUIRED";
	public static final String NOT_REQUIRED = "NOT_REQUIRED";

	public static final boolean NOT_DISABLED = false;
	public static final boolean DISABLED = true;
	
	public static String TRUE = "TRUE";
	public static String FALSE = "FALSE";
	public static String ONE = "1";
	public static String ZERO = "0";


	// Display Constants
	public static String SERVER_NAME_COLLECTION = "SERVER_NAME_COLLECTION";
	public static String LOGLEVEL_MAP= "LOGLEVEL_MAP";
	public static Integer DEFAULT_PAGE_SIZE = 10;
	public static Integer DEFAULT_PAGE_NUMBER = 1;
	public static String VIEW_PAGE_SIZE = "ViewPageSize";
	public static String VIEW_PAGE_NUMBER = "ViewPageNumber";
	public static String SEARCH_RESULTS_PAGE = "SearchResultsPage";
	
	public static String RESULTS_MESSAGE = "Found results.";
	public static String NO_RESULTS_MESSAGE = "No Log Messages found for the given search criteria. Modify the search criteria and try again.";
	public static String NO_MORE_RESULTS_MESSAGE = "No Log Messages found for the given search criteria. Modify the search criteria and try again.";
	
	
	
	//Session Constants
	public static final String  APPLICATION_NAME = "APPLICATION_NAME";
	public static final String PASSWORD = "PASSWORD";
	public static final String SEARCH_RESULT = "SEARCH_RESULT";
	public static final String CURRENT_FORM = "CURRENT_FORM";
	public static final String CURRENT_ACTION = "CURRENT_ACTION";
	
	public static final String INPUT_TEXTAREA = "INPUT_TEXTAREA";
	public static final String INPUT_CHECKBOX = "INPUT_CHECKBOX";
	public static final String INPUT_BOX = "INPUT_BOX";
	public static final String INPUT_DATE = "INPUT_DATE";
	public static final String INPUT_RADIO = "INPUT_RADIO";
	public static final String INPUT_HIDDEN = "INPUT_HIDDEN";
	
	public static final String ERROR_ID = "errorId";	
	public static final String LOGIN_OBJECT = "LOGIN_OBJECT";

	public static final String BLANK = "";
	
	public static final String OPERATION = "operation";
	
	public static final String LAST_SUCCESS_LOGIN = "LAST_SUCCESS_LOGIN";
	public static final String NUMBER_FAILED_ATTEMPTS = "NUMBER_FAILED_ATTEMPTS";
	
	public static final String ERROR_DETAILS = "ERROR_DETAILS";
	
	//Forward Constants
	
	public static final String FORWARD_PUBLIC_LOGIN= "showPublicLogin";
	public static final String FORWARD_HOME = "showHome";
	public static final String FORWARD_QUERY = "showQuery";
	public static final String FORWARD_QUERY_RESULTS = "showQueryResults";
	public static final String FORWARD_LOGOUT = "logout"; 





}
