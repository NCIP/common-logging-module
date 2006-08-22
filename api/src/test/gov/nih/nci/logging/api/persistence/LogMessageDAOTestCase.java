package test.gov.nih.nci.logging.api.persistence;

import gov.nih.nci.logging.api.applicationservice.SearchCriteria;
import gov.nih.nci.logging.api.applicationservice.exception.SearchCriteriaSpecificationException;
import gov.nih.nci.logging.api.domain.LogMessage;
import gov.nih.nci.logging.api.domain.ObjectAttribute;
import gov.nih.nci.logging.api.persistence.LogMessageDAO;
import gov.nih.nci.logging.api.util.ObjectFactory;

import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

public class LogMessageDAOTestCase extends TestCase {

	private LogMessageDAO logMessageDAO = (LogMessageDAO) ObjectFactory.getObject(ObjectFactory.LogMessageDAO);;
	
	
	/**
	 * @param name
	 */
	public LogMessageDAOTestCase(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		logMessageDAO = (LogMessageDAO) ObjectFactory.getObject(ObjectFactory.LogMessageDAO);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

    public void testSave(){
		assertNotNull(logMessageDAO);
		
/*		Object logmessage = createEventLogMessage();
		logMessageDAO.save(logmessage);
		assertTrue("Log Message saved", logmessage!=null);
*/		
		Object logmessage2 = createObjectStateLogMessage();
		logMessageDAO.save(logmessage2);
		assertTrue("Log Message saved", logmessage2!=null);
		
    }

    private LogMessage createEventLogMessage() {
    	LogMessage logmessage = new LogMessage();
    	logmessage.setApplication("App");
    	logmessage.setMessage("Message");
    	java.util.Date d = new java.util.Date();
		d.setTime(new Long(System.currentTimeMillis()).longValue());
		logmessage.setCreatedDate(d);
		return logmessage;
	}
    
    private LogMessage createObjectStateLogMessage() {
    	
    	LogMessage logmessage = new LogMessage();

    	logmessage.setApplication("App");
    	logmessage.setMessage("Message");
    	java.util.Date d = new java.util.Date();
		d.setTime(new Long(System.currentTimeMillis()).longValue());
		logmessage.setCreatedDate(d);
    	
    	ObjectAttribute oa = new ObjectAttribute();
    	oa.setAttributeName("Test1");
    	oa.setPreviousValue("Previuos Test1");
    	logmessage.addObjectAttribute(oa);
    	ObjectAttribute oa2 = new ObjectAttribute();
    	oa2.setAttributeName("Test2");
    	oa2.setPreviousValue("Previuos Test2");
    	logmessage.addObjectAttribute(oa2);
    	
		return logmessage;
	}
	
   
    
    public void testRetrieveByID(){
    	logMessageDAO = (LogMessageDAO) ObjectFactory.getObject(ObjectFactory.LogMessageDAO);
		assertNotNull(logMessageDAO);
		
		LogMessage logmessage = null;
		logmessage = (LogMessage) logMessageDAO.retrieveObjectById(LogMessage.class, new Long(3714));
		
		
			LogMessage oslm = (LogMessage)logmessage;
			//System.out.println("Object State Log.");
			System.out.println(oslm.getMessage());
			System.out.println(oslm.getObjectID());
			Iterator it = oslm.getObjectAttributeSet().iterator();
			while(it.hasNext()){
				ObjectAttribute oa = (ObjectAttribute)it.next();
				//System.out.println(" Attribute : "+oa.getAttribute()+", Previous Value : "+oa.getPreviousValue()+", Next Value : "+oa.getNextValue());
			}
			
	
		
		
		Object eventlogmessage = null;
		eventlogmessage = logMessageDAO.retrieveObjectById(LogMessage.class, new Long(3714));
		assertNotNull(eventlogmessage);
		
		if( logmessage.getClass().getName()==LogMessage.class.getName()){
			LogMessage elm = (LogMessage)logmessage;
			//System.out.println("Event Log.");
			System.out.println(elm.getMessage());	
		}
		
		
    }

    public void testEstimateResultSize(){
    	logMessageDAO = (LogMessageDAO) ObjectFactory.getObject(ObjectFactory.LogMessageDAO);
		assertNotNull(logMessageDAO);
		
		SearchCriteria searchCriteria = getSearchCriteriaThatGivesResults();
		Collection collection = null;
		int resultSize = logMessageDAO.estimateResultSize(searchCriteria);
		assertTrue("Esimated Result Size should be greater than zero.", resultSize!= 0);
		
    }
    
    public void testRetrieveLogMessage(){
    	logMessageDAO = (LogMessageDAO) ObjectFactory.getObject(ObjectFactory.LogMessageDAO);
		assertNotNull(logMessageDAO);
		
		
		SearchCriteria searchCriteria = getSearchCriteriaThatGivesResults();
		Collection collection = null;
		try {
			collection = logMessageDAO.retrieve(searchCriteria);
			assertTrue("No Log Messages Retrieved.", collection!=null);
			assertTrue("No Log Messages Retrieved.", collection.size()!= 0);
		} catch (SearchCriteriaSpecificationException e) {
			assertTrue(false);
		}
		if(collection!=null){
			Iterator iter = collection.iterator();
			while(iter.hasNext()){
				Object logmessage = (Object)iter.next();
				if( logmessage.getClass().getName()==LogMessage.class.getName()){
					LogMessage elm = (LogMessage)logmessage;
					//System.out.println("Event Log Message: "+elm.getMessage());	
				}
				if( logmessage.getClass().getName()==LogMessage.class.getName()){
					LogMessage oslm = (LogMessage)logmessage;
					//System.out.println("Object State Log Message: "+oslm.getComment());
				}
			}
		}
    }

	

    
	private SearchCriteria getSearchCriteriaThatGivesResults() {
		SearchCriteria searchCriteria = new SearchCriteria();
		return searchCriteria;
	}
	private SearchCriteria getSearchCriteriaThatGivesNoResults(){
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setApplication("ZZZZZZZZZZZZZZZZZZZ");
		return searchCriteria;
	}
	
}
