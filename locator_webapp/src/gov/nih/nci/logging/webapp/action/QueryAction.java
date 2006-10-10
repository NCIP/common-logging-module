//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.0/xslt/JavaClass.xsl

package gov.nih.nci.logging.webapp.action;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import gov.nih.nci.logging.api.applicationservice.Query;
import gov.nih.nci.logging.api.applicationservice.QueryImpl;
import gov.nih.nci.logging.api.applicationservice.SearchCriteria;
import gov.nih.nci.logging.api.applicationservice.exception.QuerySpecificationException;
import gov.nih.nci.logging.api.applicationservice.exception.SearchCriteriaSpecificationException;
import gov.nih.nci.logging.api.logger.util.ApplicationConstants;
import gov.nih.nci.logging.webapp.form.LoginForm;
import gov.nih.nci.logging.webapp.form.QueryForm;
import gov.nih.nci.logging.webapp.util.Constants;
import gov.nih.nci.logging.webapp.util.SecurityManager;
import gov.nih.nci.logging.webapp.util.StringUtils;
import gov.nih.nci.logging.webapp.util.SystemManager;
import gov.nih.nci.logging.webapp.viewobjects.SearchResultPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

public class QueryAction extends Action
{

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();

		HttpSession session = request.getSession();

		QueryForm queryForm = (QueryForm) form;

		
		
		if (session.isNew() || (session.getAttribute(Constants.LOGIN_OBJECT) == null))
		{
			return mapping.findForward(Constants.FORWARD_PUBLIC_LOGIN);
		}
		String applicationName = "";
		if (session.getAttribute(Constants.LOGIN_OBJECT) != null)
		{
			applicationName = (String) session.getAttribute(Constants.APPLICATION_NAME);
		}
		

		if(session.getAttribute(Constants.LOGLEVEL_MAP)==null){
			session.setAttribute(Constants.LOGLEVEL_MAP, SystemManager.getLogLevelMap());
		}
		if(session.getAttribute(Constants.SERVER_NAME_COLLECTION)==null){
			session.setAttribute(Constants.SERVER_NAME_COLLECTION, SystemManager.getServerNameCollection());
		}
		
		
		
		if (!StringUtils.isBlankOrNull(queryForm.getActivity()))
		{

			queryForm.setActivity(null);
			session.setAttribute(Constants.CURRENT_FORM, queryForm);

			session.removeAttribute(Constants.SEARCH_RESULTS_PAGE);
			session.removeAttribute(Constants.VIEW_PAGE_NUMBER);

			boolean success = false;
			try
			{
				success = performQuery(queryForm, session);
			}
			catch (Exception e)
			{
				// Set Search Result Page information
				SearchResultPage searchResultPage = new SearchResultPage();
				searchResultPage.setTotalResultSize(0);
				searchResultPage.setSearchResultMessage(e.getMessage());
				// Set Search Results
				session.setAttribute(Constants.SEARCH_RESULTS_PAGE, searchResultPage);				
			}
			return mapping.findForward(Constants.FORWARD_QUERY_RESULTS);

		}else{
			queryForm.setActivity(null);
			
			//set Query Form default Values.
			java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(ApplicationConstants.DATE_FORMAT);
			java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat(ApplicationConstants.TIME_FORMAT);
			
			Date currentDate = new Date(System.currentTimeMillis());
			queryForm.setStartDate(dateFormat.format(currentDate));
			queryForm.setStartTime(timeFormat.format(currentDate));
			queryForm.setEndDate(dateFormat.format(currentDate));
			queryForm.setEndTime(timeFormat.format(currentDate));
			queryForm.setApplication(applicationName);
			
			queryForm.setRecordCount(new Integer(Constants.DEFAULT_PAGE_SIZE).toString());
			
			session.setAttribute(Constants.CURRENT_FORM, queryForm);
		}
		return mapping.findForward(Constants.FORWARD_QUERY);

	}

	private boolean performQuery(QueryForm queryForm, HttpSession session) throws Exception
	{
//		try
//		{
			Query query = new QueryImpl();
			try
			{
				query.setCriteria(getSearchCriteria(queryForm,session));
			}
			catch (QuerySpecificationException e1)
			{
				e1.printStackTrace();
				throw e1;
			}
			catch (SearchCriteriaSpecificationException e1)
			{
				e1.printStackTrace();
				throw e1;
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
				throw e1;
			}

			int totalResultSize = 0;
			try
			{
				totalResultSize = query.totalResultSize();
			}
			catch (QuerySpecificationException e)
			{
				e.printStackTrace();
				throw e;
			}

			if (totalResultSize > 0)
			{
				// Query results
				Collection resultCollection = null;
				try
				{
					resultCollection = query.query( 1, new Integer(queryForm.getRecordCount()).intValue());
				}
				catch (NumberFormatException e)
				{
					e.printStackTrace();
				}
				catch (QuerySpecificationException e)
				{
					e.printStackTrace();
				}
				List resultList = (List) resultCollection;

				// Set Search Result Page information
				SearchResultPage searchResultPage = new SearchResultPage();
				searchResultPage.setTotalResultSize(totalResultSize);
				searchResultPage.setPageSize(new Integer(queryForm.getRecordCount()).intValue());
				searchResultPage.setCurrentPageNumber(1);
				searchResultPage.setSearchResultMessage(Constants.RESULTS_MESSAGE);
				searchResultPage.setSearchResultObjects(resultList);
				searchResultPage.getLastPageNumber();
				// Set Search Results
				session.setAttribute(Constants.SEARCH_RESULTS_PAGE, searchResultPage);
				session.setAttribute(Constants.VIEW_PAGE_NUMBER, searchResultPage.getCurrentPageNumber());
				return true;
			}
			else
			{
				
				// Set Search Result Page information
				SearchResultPage searchResultPage = new SearchResultPage();
				searchResultPage.setTotalResultSize(totalResultSize);
				searchResultPage.setSearchResultMessage(Constants.NO_RESULTS_MESSAGE);

				// Set Search Results
				session.setAttribute(Constants.SEARCH_RESULTS_PAGE, searchResultPage);
				
				return false;
			}
//		}
//		catch (Exception e)
//		{
////			 Set Search Result Page information
//			SearchResultPage searchResultPage = new SearchResultPage();
//			searchResultPage.setTotalResultSize(0);
//			searchResultPage.setSearchResultMessage(Constants.NO_RESULTS_MESSAGE);
//
//			// Set Search Results
//			session.setAttribute(Constants.SEARCH_RESULTS_PAGE, searchResultPage);
//			return false;
//		}
		
	}

	private SearchCriteria getSearchCriteria(QueryForm queryForm, HttpSession session) throws Exception
	{
		String applicationName = ((LoginForm)session.getAttribute(Constants.LOGIN_OBJECT)).getApplication();
		String userName = ((LoginForm)session.getAttribute(Constants.LOGIN_OBJECT)).getLoginID();
		
		SearchCriteria searchCriteria = new SearchCriteria();
		
		// Check Permission for Log Level
		if (SecurityManager.checkPermission(applicationName,userName,Constants.LOG_LEVEL_ATTRIBUTE, queryForm.getLogLevel()))
		{
			if(Constants.ALL.equalsIgnoreCase(queryForm.getLogLevel()) || queryForm.getLogLevel().length()==0){
				searchCriteria.setLogLevel(null);	
			}else{
				searchCriteria.setLogLevel(queryForm.getLogLevel());
			}
		}
		else
			throw new Exception ("User does not have access permission to query for the provided value of the Log Level attribute");

		// Check Permission for Application
		if (SecurityManager.checkPermission(applicationName,userName,Constants.APPLICATION_NAME_ATTRIBUTE, queryForm.getApplication()))
			searchCriteria.setApplication(!StringUtils.isBlankOrNull(queryForm.getApplication()) ? queryForm.getApplication() : null);
		else
			throw new Exception ("User does not have access permission to query for the provided value of the Application attribute");
				
		// Check Permission for Organziation
		if (SecurityManager.checkPermission(applicationName,userName,Constants.ORGANIZATION_ATTRIBUTE, queryForm.getOrganization()))
			searchCriteria.setOrganization(!StringUtils.isBlankOrNull(queryForm.getOrganization()) ? queryForm.getOrganization() : null);
		else
			throw new Exception ("User does not have access permission to query for the provided value of the Organization attribute");

		// Check Permission for User
		if (SecurityManager.checkPermission(applicationName,userName,Constants.USER_ATTRIBUTE, queryForm.getUser()))
			searchCriteria.setUserName(!StringUtils.isBlankOrNull(queryForm.getUser()) ? queryForm.getUser() : null);
		else
			throw new Exception ("User does not have access permission to query for the provided value of the User attribute");

		// Check Permission for Object Name
		if (SecurityManager.checkPermission(applicationName,userName,Constants.OBJECT_NAME_ATTRIBUTE, queryForm.getObjectName()))
			searchCriteria.setObjectName(!StringUtils.isBlankOrNull(queryForm.getObjectName()) ? queryForm.getObjectName() : null);
		else
			throw new Exception ("User does not have access permission to query for the provided value of the Object Name attribute");
		
		searchCriteria.setMessage(!StringUtils.isBlankOrNull(queryForm.getMessage()) ? queryForm.getMessage() : null);
		searchCriteria.setNdc(!StringUtils.isBlankOrNull(queryForm.getNdc()) ? queryForm.getNdc() : null);
		searchCriteria.setObjectID(!StringUtils.isBlankOrNull(queryForm.getObjectID()) ? queryForm.getObjectID() : null);
		searchCriteria.setOperation(!StringUtils.isBlankOrNull(queryForm.getOperation()) ? queryForm.getOperation() : null);			
		searchCriteria.setServer(!StringUtils.isBlankOrNull(queryForm.getServer()) ? queryForm.getServer() : null);
		searchCriteria.setSessionID(!StringUtils.isBlankOrNull(queryForm.getSessionID()) ? queryForm.getSessionID() : null);
		searchCriteria.setStartDate(!StringUtils.isBlankOrNull(queryForm.getStartDate()) ? queryForm.getStartDate() : null);
		searchCriteria.setStartTime(!StringUtils.isBlankOrNull(queryForm.getStartTime()) ? queryForm.getStartTime() : null);
		searchCriteria.setEndDate(!StringUtils.isBlankOrNull(queryForm.getEndDate()) ? queryForm.getEndDate() : null);
		searchCriteria.setEndTime(!StringUtils.isBlankOrNull(queryForm.getEndTime()) ? queryForm.getEndTime() : null);
		searchCriteria.setThreadName(!StringUtils.isBlankOrNull(queryForm.getThread()) ? queryForm.getThread() : null);
		searchCriteria.setThrowable(!StringUtils.isBlankOrNull(queryForm.getThrowable()) ? queryForm.getThrowable() : null);

		return searchCriteria;
	}
}
