package test;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.logging.api.applicationservice.SearchCriteria;
import gov.nih.nci.logging.api.applicationservice.exception.SearchCriteriaSpecificationException;
import gov.nih.nci.logging.api.domain.LogMessage;
import gov.nih.nci.logging.api.persistence.LogMessageDAO;
import gov.nih.nci.logging.api.util.ObjectFactory;

public class InheritanceTest
{
	public static void main(String args[]){
		testTestDAO();
		testLogMessageDAO();
	}
	
	public static void testLogMessageDAO(){
		LogMessageDAO logMessageDAO = (LogMessageDAO) ObjectFactory.getObject(ObjectFactory.LogMessageDAO);
		SearchCriteria searchCriteria = new SearchCriteria();
		Collection collection = null;
		try {
			collection = logMessageDAO.retrieve(searchCriteria);
		} catch (SearchCriteriaSpecificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator iter = collection.iterator();
		while(iter.hasNext()){
			//print something.
		}
		
		
	}
	public static void testTestDAO(){
		TestDAO dao = (TestDAO) ObjectFactory.getObject(ObjectFactory.TestDAO);
		
		//dao.saveOrUpdateObject(null);
		//dao.deleteObject(null);
	Object a;
	a = dao.retrieveObjectById(LogMessage.class,new Long(4));
	a = dao.retrieveObjectById(LogMessage.class,new Long(5));
		
	} 

}