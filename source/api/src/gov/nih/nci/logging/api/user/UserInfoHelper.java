package gov.nih.nci.logging.api.user;

import gov.nih.nci.logging.api.logger.util.ThreadVariable;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * @author Ekagra Software Technologes Limited ('Ekagra')
 * 
 * This is a helper class for the userInfo and ThreadLocal variable
 * 
 */
public class UserInfoHelper
{
	public static void setUserInfo(String userName, String sessionID)
	{
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(userName);
		userInfo.setSessionID(sessionID);
		ThreadVariable.set(userInfo);
	}

}
