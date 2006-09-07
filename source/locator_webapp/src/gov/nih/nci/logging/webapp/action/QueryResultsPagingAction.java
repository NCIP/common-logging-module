//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.0/xslt/JavaClass.xsl

package gov.nih.nci.logging.webapp.action;

import java.util.Collection;
import java.util.List;

import gov.nih.nci.logging.api.applicationservice.Query;
import gov.nih.nci.logging.api.applicationservice.QueryImpl;
import gov.nih.nci.logging.api.applicationservice.SearchCriteria;
import gov.nih.nci.logging.api.applicationservice.exception.QuerySpecificationException;
import gov.nih.nci.logging.api.applicationservice.exception.SearchCriteriaSpecificationException;
import gov.nih.nci.logging.webapp.form.LogMessageForm;
import gov.nih.nci.logging.webapp.form.QueryForm;
import gov.nih.nci.logging.webapp.util.Constants;
import gov.nih.nci.logging.webapp.util.StringUtils;
import gov.nih.nci.logging.webapp.viewobjects.SearchResultPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

/** 
 */
public class QueryResultsPagingAction extends Action
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
		LogMessageForm logMessageForm = (LogMessageForm) form;

		if (session.isNew() || (session.getAttribute(Constants.LOGIN_OBJECT) == null))
		{
			return mapping.findForward(Constants.FORWARD_PUBLIC_LOGIN);
		}

		QueryForm queryForm=null;
		if(session.getAttribute(Constants.CURRENT_FORM)!=null){
			queryForm = (QueryForm) session.getAttribute(Constants.CURRENT_FORM);
		}
		
		
		boolean success=false;
		success = performQuery(queryForm,session);
		if (success)
		{
			return mapping.findForward(Constants.FORWARD_QUERY_RESULTS);
		}
		
		
		
		
		
		return mapping.findForward(Constants.FORWARD_QUERY_RESULTS);

	}

	
	private boolean performQuery(QueryForm queryForm, HttpSession session)
	{
		try
		{
			Query query = new QueryImpl();
			query.setCriteria(getSearchCriteria(queryForm));

			int totalResultSize = query.totalResultSize();

			if (totalResultSize > 0)
			{
				int currentPageNumber  = Constants.DEFAULT_PAGE_NUMBER;
				
				if(session.getAttribute(Constants.VIEW_PAGE_NUMBER)!=null){	
					Integer cpn = (Integer) session.getAttribute(Constants.VIEW_PAGE_NUMBER);
					if(cpn!=null){
						currentPageNumber = cpn.intValue();
					}
				}
				
				int currentOffSet = currentPageNumber * Constants.DEFAULT_PAGE_SIZE;

				//Set Search Result Page information
				SearchResultPage searchResultPage = new SearchResultPage();
				searchResultPage.setTotalResultSize(totalResultSize);
				searchResultPage.setPageSize(Constants.DEFAULT_PAGE_SIZE);
				searchResultPage.setCurrentPageNumber(currentPageNumber);	
				
				if(searchResultPage.getLastPageNumber() <= currentPageNumber){
					// Query results
					Collection resultCollection = query.query(currentOffSet,Constants.DEFAULT_PAGE_SIZE);
					List resultList = (List) resultCollection;
					
					searchResultPage.setSearchResultMessage(Constants.RESULTS_MESSAGE);
					searchResultPage.setSearchResultObjects(resultList);
				}else{
					searchResultPage.setSearchResultMessage(Constants.NO_MORE_RESULTS_MESSAGE);
					searchResultPage.setSearchResultObjects(null);
				}

				// Set Search Results
				session.setAttribute(Constants.SEARCH_RESULTS_PAGE, searchResultPage);
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
		}
		catch (QuerySpecificationException e)
		{
			// Exception trying to Query.
			// TODO Show Error to the User
			return false;
		}
		catch (SearchCriteriaSpecificationException e)
		{
			// Exception trying to Query.
			// TODO Show Error to the User
			return false;
		}
	}

	private SearchCriteria getSearchCriteria(QueryForm queryForm)
	{
		SearchCriteria searchCriteria = new SearchCriteria();

		searchCriteria.setApplication(!StringUtils.isBlankOrNull(queryForm.getApplication()) ? queryForm.getApplication() : null);
		searchCriteria.setEndDate(!StringUtils.isBlankOrNull(queryForm.getEndDate()) ? queryForm.getEndDate() : null);
		searchCriteria.setEndTime(!StringUtils.isBlankOrNull(queryForm.getEndTime()) ? queryForm.getEndTime() : null);
		searchCriteria.setLogLevel(!StringUtils.isBlankOrNull(queryForm.getLogLevel()) ? queryForm.getLogLevel() : null);
		searchCriteria.setMessage(!StringUtils.isBlankOrNull(queryForm.getMessage()) ? queryForm.getMessage() : null);
		searchCriteria.setNdc(!StringUtils.isBlankOrNull(queryForm.getNdc()) ? queryForm.getNdc() : null);
		searchCriteria.setObjectID(!StringUtils.isBlankOrNull(queryForm.getObjectID()) ? queryForm.getObjectID() : null);
		searchCriteria.setObjectName(!StringUtils.isBlankOrNull(queryForm.getObjectName()) ? queryForm.getObjectName() : null);
		searchCriteria.setOperation(!StringUtils.isBlankOrNull(queryForm.getOperation()) ? queryForm.getOperation() : null);
		searchCriteria.setOrganization(!StringUtils.isBlankOrNull(queryForm.getOrganization()) ? queryForm.getOrganization() : null);
		searchCriteria.setServer(!StringUtils.isBlankOrNull(queryForm.getServer()) ? queryForm.getServer() : null);
		searchCriteria.setSessionID(!StringUtils.isBlankOrNull(queryForm.getSessionID()) ? queryForm.getSessionID() : null);
		searchCriteria.setStartDate(!StringUtils.isBlankOrNull(queryForm.getStartDate()) ? queryForm.getStartDate() : null);
		searchCriteria.setStartTime(!StringUtils.isBlankOrNull(queryForm.getStartTime()) ? queryForm.getStartTime() : null);
		searchCriteria.setThreadName(!StringUtils.isBlankOrNull(queryForm.getThread()) ? queryForm.getThread() : null);
		searchCriteria.setThrowable(!StringUtils.isBlankOrNull(queryForm.getThrowable()) ? queryForm.getThrowable() : null);
		searchCriteria.setUserName(!StringUtils.isBlankOrNull(queryForm.getUser()) ? queryForm.getUser() : null);

		return searchCriteria;
	}
	
}
