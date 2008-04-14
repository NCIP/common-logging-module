package gov.nih.nci.logging.api.user;

import java.util.ArrayList;
import gov.nih.nci.logging.api.logger.util.ThreadVariableGroupInfos;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * @author Ekagra Software Technologes Limited ('Ekagra')
 * 
 * This is a helper class for the groupInfos and ThreadLocal variable
 * 
 */
public class GroupInfosHelper
{
	public static void setGroupInfos(String[] groupNames)
	{
		 GroupInfos groupInfos = (GroupInfos)ThreadVariableGroupInfos.get();
		  if (null == groupInfos){
		   groupInfos = new GroupInfos();
		  }
		  
		  ArrayList<GroupInfo> groupInfoList = new ArrayList<GroupInfo>();
		  for(int i=0;i<groupNames.length;i++){
			  GroupInfo groupInfo = null;
			  if (!(null == groupNames[i] || groupNames[i].trim().length() == 0)){
				  groupInfo = new GroupInfo();
				  groupInfo.setGroupName(groupNames[i]);
			  }
			  if(groupInfo!=null) groupInfoList.add(groupInfo);			  
		  }
		  groupInfos.setGroupInfos(groupInfoList);
		  ThreadVariableGroupInfos.set(groupInfos);
	}

}
