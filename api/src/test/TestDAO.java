package test;


import gov.nih.nci.logging.api.applicationservice.SearchCriteria;
import gov.nih.nci.logging.api.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



/**

 * 
 */
public class TestDAO extends HibernateDaoSupport {

	public void saveOrUpdateObject(Object object) {
		getHibernateTemplate().saveOrUpdate(object);
	}

	public void deleteObject(Object object) {
		getHibernateTemplate().delete(object);
	}

	public Object retrieveObjectById(Class cl, Long id) {
		return getHibernateTemplate().get(cl, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.c3pr.persistence.dao.ParticipantDAO#retrieveParticipants(gov.nih.nci.c3pr.persistence.dao.ParticipantSearchCriteria)
	 */
	public Collection retrieveLogMessages(
			SearchCriteria sc) {

		String queryStr = "Select distinct lm from LogMessage as lm";

		ArrayList paramList = new ArrayList();
		StringBuffer whereClause = new StringBuffer();
		buildRetreiveLogMessageWhereClause(whereClause, paramList, sc);

		if (whereClause.length() > 0)
			queryStr = queryStr + " where " + whereClause
					+ " ORDER BY lm.created_on";

		Collection col = getHibernateTemplate().find(
				queryStr, paramList.toArray());

		return col;
	}

	/**
	 * Method to build the where clause of the query to retrieve LogMessages
	 * based on the SearchCriteria.
	 * 
	 * @param whereClause
	 * @param paramList
	 * @param sc
	 */
	private void buildRetreiveLogMessageWhereClause(StringBuffer whereClause,
			ArrayList paramList, SearchCriteria sc) {

		if (!StringUtils.isBlank(sc.getApplication())) {
			String valueString = sc.getApplication();
			String tempWhere = "(lm.application=? or APPLICATION like ?)";
			String wildCardedString = StringUtils.wildCard(StringUtils.initString(valueString));
			paramList.add(StringUtils.initString(valueString));
			paramList.add(wildCardedString);
		}
		if (!StringUtils.isBlank(sc.getLogLevel())) {
			String valueString = sc.getLogLevel();
			String tempWhere = "(lm.application=? )";
			String wildCardedString = StringUtils.wildCard(StringUtils.initString(valueString));
			paramList.add(StringUtils.initString(valueString));

			if (whereClause.length() > 0)
				whereClause.append(" and ");
			whereClause.append(" " + tempWhere);
		}
		if (!StringUtils.isBlank(sc.getMessage())) {
			String valueString = sc.getMessage();
			String tempWhere = "(lm.message=? or lm.message like ?)";
			String wildCardedString = StringUtils.wildCard(StringUtils.initString(valueString));
			paramList.add(StringUtils.initString(valueString));
			paramList.add(wildCardedString);

			if (whereClause.length() > 0)
				whereClause.append(" and ");
			whereClause.append(" " + tempWhere);
		}
		if (!StringUtils.isBlank(sc.getNdc())) {
			String valueString = sc.getNdc();
			String tempWhere = "(lm.ndc=? or lm.ndc like ?)";
			String wildCardedString = StringUtils.wildCard(StringUtils.initString(valueString));
			paramList.add(StringUtils.initString(valueString));
			paramList.add(wildCardedString);

			if (whereClause.length() > 0)
				whereClause.append(" and ");
			whereClause.append(" " + tempWhere);
		}

		if (!StringUtils.isBlank(sc.getObjectID())) {
			String valueString = sc.getNdc();
			String tempWhere = "(lm.ndc=? or lm.ndc like ?)";
			String wildCardedString = StringUtils.wildCard(StringUtils.initString(valueString));
			paramList.add(StringUtils.initString(valueString));
			paramList.add(wildCardedString);

			if (whereClause.length() > 0)
				whereClause.append(" and ");
			whereClause.append(" " + tempWhere);

		}
		if (!StringUtils.isBlank(sc.getObjectName())) {
			String valueString = sc.getObjectName();
			String tempWhere = "(lm.objectName=? or lm.objectName like ?)";
			String wildCardedString = StringUtils.wildCard(StringUtils.initString(valueString));
			paramList.add(StringUtils.initString(valueString));
			paramList.add(wildCardedString);

			if (whereClause.length() > 0)
				whereClause.append(" and ");
			whereClause.append(" " + tempWhere);
		}
		if (!StringUtils.isBlank(sc.getOperation())) {
			String valueString = sc.getOperation();
			String tempWhere = "(lm.operation =? or lm.operation like ?)";
			String wildCardedString = StringUtils.wildCard(StringUtils.initString(valueString));
			paramList.add(StringUtils.initString(valueString));
			paramList.add(wildCardedString);

			if (whereClause.length() > 0)
				whereClause.append(" and ");
			whereClause.append(" " + tempWhere);
		}
		if (!StringUtils.isBlank(sc.getOrganization())) {
			String valueString = sc.getOrganization();
			String tempWhere = "(lm.organization=? or lm.organization like ?)";
			String wildCardedString = StringUtils.wildCard(StringUtils.initString(valueString));
			paramList.add(StringUtils.initString(valueString));
			paramList.add(wildCardedString);

			if (whereClause.length() > 0)
				whereClause.append(" and ");
			whereClause.append(" " + tempWhere);
		}
		if (!StringUtils.isBlank(sc.getServer())) {
			String valueString = sc.getServer();
			String tempWhere = "(lm.server=? or lm.server like ?)";
			String wildCardedString = StringUtils.wildCard(StringUtils.initString(valueString));
			paramList.add(StringUtils.initString(valueString));
			paramList.add(wildCardedString);

			if (whereClause.length() > 0)
				whereClause.append(" and ");
			whereClause.append(" " + tempWhere);
		}
		if (!StringUtils.isBlank(sc.getSessionID())) {
			String valueString = sc.getSessionID();
			String tempWhere = "(lm.session_id=? or lm.session_id like ?)";
			String wildCardedString = StringUtils.wildCard(StringUtils.initString(valueString));
			paramList.add(StringUtils.initString(valueString));
			paramList.add(wildCardedString);

			if (whereClause.length() > 0)
				whereClause.append(" and ");
			whereClause.append(" " + tempWhere);
		}
		if (!StringUtils.isBlank(sc.getThrowable())) {
			String valueString = sc.getThrowable();
			String tempWhere = "(lm.throwable=? or lm.throwable like ?)";
			String wildCardedString = StringUtils.wildCard(StringUtils.initString(valueString));
			paramList.add(StringUtils.initString(valueString));
			paramList.add(wildCardedString);

			if (whereClause.length() > 0)
				whereClause.append(" and ");
			whereClause.append(" " + tempWhere);
		}
		if (!StringUtils.isBlank(sc.getThreadName())) {
			String valueString = sc.getThreadName();
			String tempWhere = "(lm.thread =? or lm.thread like ?)";
			String wildCardedString = StringUtils.wildCard(StringUtils.initString(valueString));
			paramList.add(StringUtils.initString(valueString));
			paramList.add(wildCardedString);

			if (whereClause.length() > 0)
				whereClause.append(" and ");
			whereClause.append(" " + tempWhere);
		}
		if (!StringUtils.isBlank(sc.getUserName())) {
			String valueString = sc.getUserName();
			String tempWhere = "(lm.user =? or lm.user like ?)";
			String wildCardedString = StringUtils.wildCard(StringUtils.initString(valueString));
			paramList.add(StringUtils.initString(valueString));
			paramList.add(wildCardedString);

			if (whereClause.length() > 0)
				whereClause.append(" and ");
			whereClause.append(" " + tempWhere);
		}

	}


}