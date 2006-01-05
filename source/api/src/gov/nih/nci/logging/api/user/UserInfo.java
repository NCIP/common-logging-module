package gov.nih.nci.logging.api.user;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * <!-- LICENSE_TEXT_END -->
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Ekagra Software Technologes Limited ('Ekagra')
 * 
 * The userInfo bean class
 * 
 */
public class UserInfo implements Serializable
{
	private String userName;
	private String sessionId;
	private boolean isIntransaction;
	private ArrayList transactionLogs;

	public String getUsername()
	{
		return userName;
	}

	public void setUsername(String userName)
	{
		this.userName = userName;
	}

	public String getSessionID()
	{
		return sessionId;
	}

	public void setSessionID(String sessionid)
	{
		this.sessionId = sessionid;
	}

	public boolean getIsIntransaction()
	{
		return isIntransaction;
	}

	public void setIsIntransaction(boolean isIntransaction)
	{

		this.isIntransaction = isIntransaction;
	}

	public ArrayList getTransactionLogs()
	{
		return transactionLogs;
	}

	public void setTransactionLogs(ArrayList transactionLogs)
	{
		this.transactionLogs = transactionLogs;
	}

	private static final long serialVersionUID = 7526471155622776147L;

}
