package test;
 
import gov.nih.nci.logging.api.applicationservice.Query;
import gov.nih.nci.logging.api.applicationservice.QueryImpl;
import gov.nih.nci.logging.api.applicationservice.SearchCriteria;
import gov.nih.nci.logging.api.applicationservice.exception.QuerySpecificationException;
import gov.nih.nci.logging.api.domain.LogMessage;
import gov.nih.nci.logging.api.logger.util.ApplicationConstants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;


public class TestQueryAPIClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		//QueryClient();
		
		parseObjectStateMessage();		
	
	}

	private static void parseObjectStateMessage() {
		String objectName = "";
		String objectID = "";
		String comment = "";
		String operationName = "";
		HashMap previousAttributes= new HashMap();
		HashMap currentAttributes= new HashMap();
		
		// get Content of object_state log message field
		String objectAttributeMessage = "&operation=[name=INSERT;comment=qq]&object=[name=test.application.domainobjects.Customer;ID=0]&attributes=[first=Bill;last=Burke;street=1 Boston Road;city=Newland;state=MA;zip=02115;items=[test.application.domainobjects.Item@fb6354]]";
		
		//Operation Name
		//System.out.println(objectAttributeMessage.indexOf("&operation=[name="));
		
		StringTokenizer stringTokenizer = new StringTokenizer(objectAttributeMessage,"&");
		while(stringTokenizer.hasMoreElements()){
			String messagetemp = (String) stringTokenizer.nextElement();
			System.out.println(messagetemp);
			if(messagetemp.startsWith(ApplicationConstants.OBJECT_STATE_MESSAGE_OPERATION)){
				StringTokenizer attTknzr = new StringTokenizer(messagetemp,";[]");
				while(attTknzr.hasMoreElements()){
					String temp = (String) attTknzr.nextElement();
					if(temp==null) continue;
					if(temp.startsWith(ApplicationConstants.OBJECT_STATE_MESSAGE_OPERATION)) continue;
					
					String attributeName= temp.substring(0,temp.indexOf("="));
					if(ApplicationConstants.OBJECT_STATE_MESSAGE_OPERATION_NAME.equalsIgnoreCase(attributeName)){
						operationName= temp.substring(temp.indexOf("=")+1);
					}
					if(ApplicationConstants.OBJECT_STATE_MESSAGE_OPERATION_COMMENT.equalsIgnoreCase(attributeName)){
						comment= temp.substring(temp.indexOf("=")+1);
					}
					
				}
			}
			if(messagetemp.startsWith(ApplicationConstants.OBJECT_STATE_MESSAGE_OBJECT)){
				StringTokenizer attTknzr = new StringTokenizer(messagetemp,";[]");
				while(attTknzr.hasMoreElements()){
					String temp = (String) attTknzr.nextElement();
					if(temp==null) continue;
					if(temp.startsWith(ApplicationConstants.OBJECT_STATE_MESSAGE_OBJECT)) continue;
					
					String attributeName= temp.substring(0,temp.indexOf("="));
					if(ApplicationConstants.OBJECT_STATE_MESSAGE_OPERATION_OBJECT_NAME.equalsIgnoreCase(attributeName)){
						objectName= temp.substring(temp.indexOf("=")+1);
					}
					if(ApplicationConstants.OBJECT_STATE_MESSAGE_OPERATION_OBJECT_IDENTIFIER_ATTRIBUTE_VALUE.equalsIgnoreCase(attributeName)){
						objectID= temp.substring(temp.indexOf("=")+1);
					}
					
				}
			}
			if(messagetemp.startsWith(ApplicationConstants.OBJECT_STATE_MESSAGE_PREVIOUS_ATTRIBUTES)){
				StringTokenizer attTknzr = new StringTokenizer(messagetemp,";[]");
				while(attTknzr.hasMoreElements()){
					String temp = (String) attTknzr.nextElement();
					if(temp==null) continue;
					if(temp.startsWith(ApplicationConstants.OBJECT_STATE_MESSAGE_PREVIOUS_ATTRIBUTES)) continue;
					
					String attributeName= temp.substring(0,temp.indexOf("="));
					String attributeValue = temp.substring(temp.indexOf("=")+1);
					if(attributeName !=null && attributeName.length()>0){
						previousAttributes.put(attributeName,attributeValue);	
					}
				}
			}
			if(messagetemp.startsWith(ApplicationConstants.OBJECT_STATE_MESSAGE_CURRENT_ATTRIBUTES)){
				StringTokenizer attTknzr = new StringTokenizer(messagetemp,";[]");
				while(attTknzr.hasMoreElements()){
					String temp = (String) attTknzr.nextElement();
					if(temp==null) continue;
					if(temp.startsWith(ApplicationConstants.OBJECT_STATE_MESSAGE_CURRENT_ATTRIBUTES)) continue;
					
					String attributeName= temp.substring(0,temp.indexOf("="));
					String attributeValue = temp.substring(temp.indexOf("=")+1);
					if(attributeName !=null && attributeName.length()>0){
						currentAttributes.put(attributeName,attributeValue);	
					}
				}
			}
			if(messagetemp.startsWith(ApplicationConstants.OBJECT_STATE_MESSAGE_ATTRIBUTES)){
				StringTokenizer attTknzr = new StringTokenizer(messagetemp,";[]");
				while(attTknzr.hasMoreElements()){
					String temp = (String) attTknzr.nextElement();
					if(temp==null) continue;
					if(temp.startsWith(ApplicationConstants.OBJECT_STATE_MESSAGE_ATTRIBUTES)) continue;
					
					if(temp.indexOf("=")<=0) continue;
					
					String attributeName= temp.substring(0,temp.indexOf("="));
					String attributeValue = temp.substring(temp.indexOf("=")+1);
					if(attributeName !=null && attributeName.length()>0){
						currentAttributes.put(attributeName,attributeValue);	
					}
				}
			}
		
		}
		

		System.out.println(" Break point Here.");
		System.out.println(" Object Name "+objectName);
		
		
	}

	private static void QueryClient() throws QuerySpecificationException {

		SearchCriteria sc = new SearchCriteria();
		sc.setApplication("a");
		

		
		Query query = new QueryImpl();
		
		Collection logmessages = query.query();
		
		Iterator iter = logmessages.iterator();
		while(iter.hasNext()){
			Object logmessage = (LogMessage)iter.next();
			if(((LogMessage) logmessage).isObjectStateLog()){
				//process ObjectState Log
				LogMessage oslm = (LogMessage)logmessage;
				oslm.getMessage();
				
			}else{
				//process Event Log
				LogMessage elm = (LogMessage)logmessage;
				String message = (String)elm.getApplication();
				
			}
		}
		


		
	}
	
	
	
	

}
