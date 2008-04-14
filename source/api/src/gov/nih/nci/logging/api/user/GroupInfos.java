package gov.nih.nci.logging.api.user;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * <!-- LICENSE_TEXT_END -->
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Ekagra Software Technologes Limited ('Ekagra')
 * 
 * The userInfo bean class
 * 
 */
public class GroupInfos implements Serializable
{

	private ArrayList<GroupInfo> groupInfos;
	
	public ArrayList getGroupInfos()
	{
		return groupInfos;
	}

	public void setGroupInfos(ArrayList groupInfos)
	{
		this.groupInfos = groupInfos;
	}




}
