package gov.nih.nci.logging.api.applicationservice;

import gov.nih.nci.logging.api.applicationservice.exception.QuerySpecificationException;
import gov.nih.nci.logging.api.applicationservice.exception.SearchCriteriaSpecificationException;
import gov.nih.nci.logging.api.domain.LogMessage;
import gov.nih.nci.logging.api.domain.ObjectAttribute;
import gov.nih.nci.logging.api.persistence.LogMessageDAO;
import gov.nih.nci.logging.api.util.ObjectFactory;
import gov.nih.nci.logging.api.util.StringUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class QueryImpl implements Query {

	LogMessageDAO logMessageDAO = (LogMessageDAO) ObjectFactory
			.getObject(ObjectFactory.LogMessageDAO);

	SearchCriteria searchCriteria;
	private boolean searchCriteriaChanged = true;
	private int estimateResultSize = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.logging.api.applicationservice.Query#setCriteria(gov.nih.nci.logging.api.domain.SearchCriteria)
	 */
	public void setCriteria(SearchCriteria searchCriteria) throws QuerySpecificationException {
		if (searchCriteria == null) {
			throw new QuerySpecificationException(
					"Search Criteria is not specified.");
		}
		this.searchCriteria = searchCriteria;
		searchCriteriaChanged = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.logging.api.applicationservice.Query#query()
	 */
	public Collection query() throws QuerySpecificationException {


		try {
			return logMessageDAO.retrieve(this.searchCriteria);
		} catch (SearchCriteriaSpecificationException e) {
			throw new QuerySpecificationException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.logging.api.applicationservice.Query#query(int)
	 */
	public Collection query(int maxSize) throws QuerySpecificationException {
		
		if (maxSize == 0) {
			throw new QuerySpecificationException(
					"Parameter maxSize value should be greater than zero.");
		}
		try {
			return logMessageDAO.retrieve(this.searchCriteria, maxSize);
		} catch (SearchCriteriaSpecificationException e) {
			throw new QuerySpecificationException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.logging.api.applicationservice.Query#query(int, int)
	 */
	public Collection query(int currentStartOffSet, int currentRecordCount) throws QuerySpecificationException {
		if (currentStartOffSet == 0 && currentRecordCount == 0) {
			throw new QuerySpecificationException(
					"Parameter currentStartOffSet and currentRecordCount value should be greater than zero.");
		}
		if (currentStartOffSet > 0 && currentRecordCount == 0) {
			throw new QuerySpecificationException(
					"Parameter currentRecordCount is not specified. Parameter currentRecordCount value should be greater than zero.");
		}
		
		if (currentStartOffSet == 0 && currentRecordCount > 0) {
			throw new QuerySpecificationException(
					"Parameter currentStartOffSet is not specified. Parameter currentStartOffSet value should be greater than zero.");
		}

		try {
			return logMessageDAO.retrieve(this.searchCriteria, currentStartOffSet,currentRecordCount);
		} catch (SearchCriteriaSpecificationException e) {
			throw new QuerySpecificationException(e.getMessage());
		}
	}
	

	
	/* (non-Javadoc)
	 * @see gov.nih.nci.logging.api.applicationservice.Query#estimateResultSize()
	 */
	public int totalResultSize() throws QuerySpecificationException {
		
		if(this.searchCriteriaChanged){
			try {
				this.estimateResultSize = logMessageDAO.estimateResultSize(this.searchCriteria);
			} catch (Exception e) {
				throw new QuerySpecificationException(e.getMessage());
			}
		}
		return this.estimateResultSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.logging.api.applicationservice.Query#toXML(java.util.Collection)
	 */
	public String toXML(Collection logMessageCollection) {

		StringBuffer sb = new StringBuffer();
		sb.append("<LogMessage>\r\n");
		if(logMessageCollection==null  || logMessageCollection.isEmpty()) return null;
		
		Iterator iterator = logMessageCollection.iterator();
		while(iterator.hasNext()){
			LogMessage logMessage = (LogMessage)iterator.next();
			
			//add logmessage elements
			sb.append(addElement("Application",logMessage.getApplication()));
			sb.append(addElement("Created_on",logMessage.getCreatedDate().toString()));
			sb.append(addElement("LogLevel",logMessage.getLogLevel()));
			sb.append(addElement("Message",logMessage.getMessage()));
			sb.append(addElement("Ndc",logMessage.getNdc()));
			sb.append(addElement("ObjectID",logMessage.getObjectID()));
			sb.append(addElement("ObjectName",logMessage.getObjectName()));
			sb.append(addElement("Operation",logMessage.getOperation()));
			sb.append(addElement("Organization",logMessage.getOrganization()));
			sb.append(addElement("Server",logMessage.getServer()));
			sb.append(addElement("SessionID",logMessage.getSessionID()));
			sb.append(addElement("Thread",logMessage.getThread()));
			sb.append(addElement("Throwable",logMessage.getThrowable()));
			sb.append(addElement("UserName",logMessage.getUserName()));
			 
			if(logMessage.isObjectStateLog()){
				//add object state related elements
				sb.append("<ObjectAttributes>\r\n");
				sb.append(addObjectAttributes(logMessage));
				sb.append("</ObjectAttributes>\r\n");
			}
		}
		sb.append("</LogMessage>\r\n");
		
		return sb.toString();
	}

	/**
	 * Add Object Attribute elements
	 * @param logMessage
	 * @return element string of ObjectAttributes.
	 */
	private String addObjectAttributes(LogMessage logMessage) {

		Set objectAttributeSet = logMessage.getObjectAttributeSet();
		Iterator it = objectAttributeSet.iterator();
		StringBuffer objectAttributesElement = new StringBuffer();
		while(it.hasNext()){
			ObjectAttribute oa = (ObjectAttribute)it.next();
			if(!StringUtils.isBlank(oa.getAttributeName())){
				objectAttributesElement.append("<ObjectAttribute>\r\n");
				objectAttributesElement.append(addElement("Attribute",oa.getAttributeName()));
				objectAttributesElement.append(addElement("NextValue",oa.getCurrentValue()));
				objectAttributesElement.append(addElement("PreviousValue",oa.getPreviousValue()));
				objectAttributesElement.append("</ObjectAttribute>\r\n");
			}
		}
		return objectAttributesElement.toString();
	}

	/**
	 * creates and returns an element string 
	 * @param elementName
	 * @param elementValue
	 * @return element
	 */
	private String addElement(String elementName, String elementValue) {
		String element = "";
		if(!StringUtils.isBlank(elementName)){
			element = element+ "<"+elementName+">\r\n";
			element = element+ StringUtils.initString(elementValue);
			element = element+ "<"+elementName+">\r\n";
		}
		return element;
	}





}
