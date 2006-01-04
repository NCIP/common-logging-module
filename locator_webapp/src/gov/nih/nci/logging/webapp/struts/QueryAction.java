package gov.nih.nci.logging.webapp.struts;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */
import gov.nih.nci.logging.webapp.dao.SummaryDaoJdbc;
import gov.nih.nci.logging.webapp.struts.QueryForm;
import gov.nih.nci.logging.webapp.util.Constants;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;

/**
 * Action class for processing log tracker Queries
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 */

public class QueryAction extends Action implements Constants
{

	/*
	 * Struts event handling method for processing a Summary Query
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		QueryForm qf = (QueryForm) form;
		SummaryDaoJdbc dao = new SummaryDaoJdbc();
		List l = dao.findSummaries(qf);
		request.getSession().setAttribute(SUMMARY_LIST, l);

		return mapping.findForward(ACTION_SUCCESS);
	}

}