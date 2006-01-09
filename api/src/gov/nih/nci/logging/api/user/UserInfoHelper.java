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
		 UserInfo userInfo = (UserInfo)ThreadVariable.get();
		  if (null == userInfo)
		   userInfo = new UserInfo();
		  if (!(null == userName || userName.trim().length() == 0))
		   userInfo.setUsername(userName);
		  if (!(null == sessionID || sessionID.trim().length() == 0))
		  userInfo.setSessionID(sessionID);
		  ThreadVariable.set(userInfo);
	}

}
