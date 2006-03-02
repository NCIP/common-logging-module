package gov.nih.nci.logging.webapp.dao;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Utility Class for obtaining a JDBC connection. This class looks up database connection
 * with the mysql-ds.xml.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 * 
 */
public class JdbcConnectionHandler
{
	public final static String dbName = "java:/LogLocatorDS";
	public static DataSource  dbSource = null;
	static
	{
		try
		{			
			Context ctx = new InitialContext();
			dbSource = (DataSource)ctx.lookup(dbName);			 								 
		}
		catch (Exception ex)
		{
			gov.nih.nci.logging.webapp.struts.DefaultExceptionHandler.logError(ex);
		}

	}


	/**
	 * Creates a new JDBC connection.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection createConnection() throws Exception
	{
		  return  dbSource.getConnection();	
	}

	
}