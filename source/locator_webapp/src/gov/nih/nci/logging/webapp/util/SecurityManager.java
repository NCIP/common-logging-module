package gov.nih.nci.logging.webapp.util;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;

public class SecurityManager
{
	private static AuthenticationManager authenticationManager = null;
	private static AuthorizationManager authorizationManager = null;

	
	private static AuthenticationManager getAuthenticationManager() throws Exception
	{
		if (null == authenticationManager)
		{
			authenticationManager = SecurityServiceProvider.getAuthenticationManager(Constants.APPLICATION_CONTEXT_NAME);
		}
		return authenticationManager;
	}
	
	private static AuthorizationManager getAuthorizationManager() throws Exception
	{
		if (null == authorizationManager)
		{
			authorizationManager = SecurityServiceProvider.getAuthorizationManager(Constants.APPLICATION_CONTEXT_NAME);
		}
		return authorizationManager;
	}
	
	public static boolean login(String userName, String password) throws Exception
	{
		return getAuthenticationManager().login(userName,password);		
	}
	
	public static boolean checkPermission(String applicationName, String userName, String attributeName, String attributeValue) throws Exception
	{
		if (SecurityConfiguration.isElementSecured(applicationName, attributeName))
		{
			return getAuthorizationManager().checkPermission(userName,attributeName + ":" + attributeValue, Constants.PRIVILEGE);
		}
		else
		{
			return true;
		}
	}
}
