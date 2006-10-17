package gov.nih.nci.logging.api.appender.jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import gov.nih.nci.logging.api.domain.LogMessage;
import gov.nih.nci.logging.api.domain.ObjectAttribute;
import gov.nih.nci.logging.api.util.StringUtils;

public class SQLGenerator {

	public static final String APPLICATION = "APPLICATION";

	public static final String SERVER = "SERVER";

	public static final String CATEGORY = "CATEGORY";

	public static final String THREAD = "THREAD";

	public static final String USERNAME = "USERNAME";

	public static final String SESSIONID = "SESSION_ID";

	public static final String MSG = "MSG";

	public static final String THROWABLE = "THROWABLE";

	public static final String NDC = "NDC";

	public static final String CREATED_ON = "CREATED_ON";

	public static final String ORGANIZATION = "ORGANIZATION";
	public static final String OBJECT_ID = "OBJECT_ID";
	public static final String OBJECT_NAME = "OBJECT_NAME";
	public static final String OPERATION = "OPERATION";
	
	public static final String COMMA = ",";
	
	
	

	public static List getSQLStatements(Object object) {
		List  l = new ArrayList();
		
		if (object instanceof LogMessage) {

			LogMessage logMessage = (LogMessage) object;
			if (logMessage.isObjectStateLog()) {
				return getObjectStateSQLStatements(logMessage);
			} else {
				List list = new ArrayList();
				list.add(getLogMessageSQLStatement(logMessage));
				return list;
			}
		}
		return new ArrayList();
	}

	private static List getObjectStateSQLStatements(LogMessage logMessage) {
		/*String junkNeverUsed = 
				" BEGIN;"
				+"insert into LOG_MESSAGE (LOG_ID,CREATED_ON)values (NULL,1);"
				+"SELECT @log_id:=LAST_INSERT_ID();"
				+"insert into OBJECT_ATTRIBUTE (OBJECT_ATTRIBUTE_ID,ATTRIBUTE, PREVIOUS_VALUE, CURRENT_VALUE) values (NULL,'a','b','c');"
				+"SELECT @objectattribute_id:= last_insert_id();"
				+"insert into OBJECTATTRIBUTES (LOG_ID, OBJECT_ATTRIBUTE_ID) values (@log_id,@objectattribute_id);"
				+"COMMIT;";*/
		
		List l = new ArrayList();
		List l2 = new ArrayList();
		
		StringBuffer sb1 = new StringBuffer();
		
		 
			l.add(getLogMessageSQLStatement(logMessage));
			//sb1.append(getLogMessageSQLStatement(logMessage));
			l.add("SELECT @log_id:=LAST_INSERT_ID();");
			//sb1.append("SELECT @log_id:=LAST_INSERT_ID();");
			
			Set objectAttributeSet = logMessage.getObjectAttributeSet();
			int iCount = 0;
			StringBuffer sb2 = new StringBuffer();
			Iterator iterator = objectAttributeSet.iterator();
			while(iterator.hasNext()){
				iCount+= 1;
				ObjectAttribute oa = (ObjectAttribute) iterator.next();
				l.add(getObjectAttributeSQLStatement(oa));
				//sb1.append(getObjectAttributeSQLStatement(oa));
				l.add("SELECT @objectattribute_id"+iCount+":= last_insert_id();");
				//sb1.append("SELECT @objectattribute_id"+iCount+":= last_insert_id();");
				l2.add("insert into OBJECTATTRIBUTES (LOG_ID, OBJECT_ATTRIBUTE_ID) values (@log_id,@objectattribute_id"+iCount+");");
				//sb2.append("insert into OBJECTATTRIBUTES (LOG_ID, OBJECT_ATTRIBUTE_ID) values (@log_id,@objectattribute_id"+iCount+");");
			}
			
			for(int i=0; i<l2.size();i++){
				l.add(l2.get(i));
			}
			sb1.append(sb2);
			//sb1.append("COMMIT;");
			
			
	
		return l;
	}

	
	private static String getLogMessageSQLStatement(LogMessage logMessage) {

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO log_message (");
		sql.append(		APPLICATION);
		sql.append(COMMA+SERVER);
		sql.append(COMMA+CATEGORY);
		sql.append(COMMA+THREAD);
		sql.append(COMMA+USERNAME);
		sql.append(COMMA+SESSIONID);
		sql.append(COMMA+MSG);
		sql.append(COMMA+THROWABLE);
		sql.append(COMMA+NDC);
		sql.append(COMMA+CREATED_ON);
		sql.append(COMMA+ORGANIZATION);
		sql.append(COMMA+OBJECT_ID);
		sql.append(COMMA+OBJECT_NAME);
		sql.append(COMMA+OPERATION);
		sql.append(") VALUES ('");
		sql.append(StringUtils.initString(logMessage.getApplication()));
		sql.append("','");
		sql.append(StringUtils.initString(logMessage.getServer()));
		sql.append("','");
		sql.append(StringUtils.initString(logMessage.getLogLevel()));
		sql.append("','");
		sql.append(StringUtils.initString(logMessage.getThread()));
		sql.append("','");
		sql.append(StringUtils.initString(logMessage.getUserName()));
		sql.append("','");
		sql.append(StringUtils.initString(logMessage.getSessionID()));
		sql.append("','");
		sql.append(StringUtils.initString(logMessage.getMessage()));
		sql.append("','");
		sql.append(StringUtils.initString(logMessage.getThrowable()));
		sql.append("','");
		sql.append(StringUtils.initString(logMessage.getNdc()));
		sql.append("','");
		sql.append(logMessage.getCreatedOn());
		sql.append("','");
		sql.append(StringUtils.initString(logMessage.getOrganization()));
		sql.append("','");
		sql.append(StringUtils.initString(logMessage.getObjectID()));
		sql.append("','");
		sql.append(StringUtils.initString(logMessage.getObjectName()));
		sql.append("','");
		sql.append(StringUtils.initString(logMessage.getOperation()));
		sql.append("');");
		return sql.toString();
	}

	private static String getObjectAttributeSQLStatement(ObjectAttribute objectAttribute) {
		
		return "insert into OBJECT_ATTRIBUTE (OBJECT_ATTRIBUTE_ID,ATTRIBUTE, PREVIOUS_VALUE, CURRENT_VALUE) values (NULL"
			+",'"+StringUtils.initString(objectAttribute.getAttributeName())
			+"','"+StringUtils.initString(objectAttribute.getPreviousValue())
			+"','"+StringUtils.initString(objectAttribute.getCurrentValue())
			+"');";
	}
}


