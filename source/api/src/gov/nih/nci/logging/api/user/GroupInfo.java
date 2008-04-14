package gov.nih.nci.logging.api.user;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * <!-- LICENSE_TEXT_END -->
 */

import java.io.Serializable;

/**
 * @author Ekagra Software Technologes Limited ('Ekagra')
 * 
 * The groupInfo bean class
 * 
 */
public class GroupInfo implements Serializable
{
	private String groupName;
	private String sessionId;
	
	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	
}
