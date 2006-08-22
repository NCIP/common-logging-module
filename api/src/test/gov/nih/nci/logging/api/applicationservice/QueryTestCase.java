package test.gov.nih.nci.logging.api.applicationservice;

import gov.nih.nci.logging.api.applicationservice.Query;
import gov.nih.nci.logging.api.applicationservice.QueryImpl;
import gov.nih.nci.logging.api.applicationservice.SearchCriteria;
import gov.nih.nci.logging.api.applicationservice.exception.QuerySpecificationException;

import java.util.Collection;

import junit.framework.TestCase;

public class QueryTestCase extends TestCase {
	Query query;

	
	/**
	 * @param name
	 */
	public QueryTestCase(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		query = new QueryImpl();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSetCriteriaNull(){
		assertNotNull(query);
		try {
			query.setCriteria(null);
			assertTrue(false);
		} catch (QuerySpecificationException e) {
			assertTrue(true);
		}
	}
	public void testSetCriteriaNotNull(){
		assertNotNull(query);
		try {
			query.setCriteria(getSearchCriteriaThatGivesResults());
			assertTrue(true);
		} catch (QuerySpecificationException e) {
			assertTrue(false);
		}
	}
	public void testEstimatedResultSize_NoResults(){
		assertNotNull(query);
		try {
			query.setCriteria(getSearchCriteriaThatGivesNoResults());
			int size = query.totalResultSize();
			assertTrue("Estimated Result Size should be zero", size== 0);
		} catch (QuerySpecificationException e) {
			assertTrue(false);
		}
	}
	public void testEstimatedResultSize_WithResults(){
		assertNotNull(query);
		try {
			query.setCriteria(getSearchCriteriaThatGivesResults());
			int size = query.totalResultSize();
			assertTrue("Estimated Result Size should be greater than zero", size!= 0);
		} catch (QuerySpecificationException e) {
			assertTrue(false);
		}
	}
	public void testQuery_NoResults(){
		assertNotNull(query);
		try {
			query.setCriteria(getSearchCriteriaThatGivesNoResults());
			Collection collection = query.query();
			assertTrue("Expected Result Collection should  be null.", collection==null);
		} catch (QuerySpecificationException e) {
			assertTrue(false);
		}
	}	
	public void testQuery_WithResults(){
		assertNotNull(query);
		try {
			query.setCriteria(getSearchCriteriaThatGivesResults());
			Collection collection = query.query();
			assertTrue("Expected Result Collection should  Not be null.", collection!=null);
			assertTrue("Expected Result Collection Size should be greater than Zero.", collection.size()!= 0);
		} catch (QuerySpecificationException e) {
			assertTrue(false);
		}
	}
	public void testQuery_MaxSize_NoResults(){
		assertNotNull(query);
		try {
			query.setCriteria(getSearchCriteriaThatGivesNoResults());
			Collection collection = query.query(50);
			assertTrue("Expected Result Collection should  be null.", collection==null);
		} catch (QuerySpecificationException e) {
			assertTrue(false);
		}
	}	
	public void testQuery_MaxSize_WithResults(){
		assertNotNull(query);
		try {
			query.setCriteria(getSearchCriteriaThatGivesResults());
			Collection collection = query.query(50);
			assertTrue("Expected Result Collection should  Not be null.", collection!=null);
			assertTrue("Expected Result Collection Size should be greater than Zero.", collection.size()!= 0);
		} catch (QuerySpecificationException e) {
			assertTrue(false);
		}
	}
	public void testQuery_OffSet_0_RecordCount_0(){
		assertNotNull(query);
		try {
			query.setCriteria(getSearchCriteriaThatGivesResults());
			Collection collection = query.query(0,0);
			assertTrue(false);
		} catch (QuerySpecificationException e) {
			assertTrue(true);
		}
	}
	public void testQuery_OffSet_Not0_RecordCount0(){
		assertNotNull(query);
		try {
			query.setCriteria(getSearchCriteriaThatGivesResults());
			Collection collection = query.query(10,0);
			assertTrue(false);
		} catch (QuerySpecificationException e) {
			assertTrue(true);
		}
	}
	public void testQuery_OffSet_0_RecordCount_Not0(){
		assertNotNull(query);
		try {
			query.setCriteria(getSearchCriteriaThatGivesResults());
			Collection collection = query.query(0,10);
			assertTrue(false);
		} catch (QuerySpecificationException e) {
			assertTrue(true);
		}
	}
	public void testQuery_OffSet_Not0_RecordCount_Not0_NoResults(){
		assertNotNull(query);
		try {
			query.setCriteria(getSearchCriteriaThatGivesNoResults());
			Collection collection = query.query(1,10);
			assertTrue("Expected Result Collection should  be null.", collection==null);
		} catch (QuerySpecificationException e) {
			assertTrue(false);
		}
	}
	public void testQuery_OffSet_Not0_RecordCount_Not0_WithResults(){
		assertNotNull(query);
		try {
			query.setCriteria(getSearchCriteriaThatGivesResults());
			Collection collection = query.query(1,10);
			assertTrue("Expected Result Collection should  Not be null.", collection!=null);
			assertTrue("Expected Result Collection Size should be greater than Zero.", collection.size()!= 0);
		} catch (QuerySpecificationException e) {
			assertTrue(false);
		}
	}
    
	private SearchCriteria getSearchCriteriaThatGivesResults() {
		SearchCriteria searchCriteria = new SearchCriteria();
		return searchCriteria;
	}
	private SearchCriteria getSearchCriteriaThatGivesNoResults(){
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setApplication("ZZZZZZZZZZZZZZZZZ");
		searchCriteria.addAscendingSortOrderFor(SearchCriteria.SORT_BY_PARAMETER_APPLICATION);
		searchCriteria.addAscendingSortOrderFor(SearchCriteria.SORT_BY_PARAMETER_USER);

		return searchCriteria;
	}
	
}

