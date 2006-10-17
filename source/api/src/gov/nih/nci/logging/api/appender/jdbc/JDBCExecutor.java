package gov.nih.nci.logging.api.appender.jdbc;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */
import gov.nih.nci.logging.api.appender.util.AppenderUtils;
import gov.nih.nci.logging.api.util.HibernateUtil;
import gov.nih.nci.logging.api.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 
 * Runnable class responsible for inserting the log messages in batch 
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 * 
 */
public class JDBCExecutor implements java.lang.Runnable
{

	private List buff;
	private Properties props;
	private String dbUrl = null;
	private String dbDriverClass = null;
	private String dbUser = null;
	private String dbPwd = null;


	/**
	 * Constructor for JDBCExcecutor.
	 * 
	 * @param rows -
	 */
	public JDBCExecutor(List messages, Properties props)
	{
		setBuff(messages);
		setProps(props);
		setDBProperties();
	}

	

	private void setDBProperties() {
		if(this.props!=null){
			
			this.setDbDriverClass(StringUtils.initString(this.props.getProperty("hibernate.connection.driver_class")));
			this.setDbPwd(StringUtils.initString(this.props.getProperty("hibernate.connection.password")));
			this.setDbUser(StringUtils.initString(this.props.getProperty("hibernate.connection.username")));
			this.setDbUrl(StringUtils.initString(this.props.getProperty("hibernate.connection.url")));
		}
		
	}



	/*
	 * Executes a batch insert of the messages into the RDBMS.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		try
		{
			insert();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			AppenderUtils.writeMsgToTmpFile(ex);
		}
	}

	private void insert() throws Exception{
		
		Connection conn = null;
		Statement stmt = null;
		try
		{
			conn = createConn();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			Iterator i = getBuff().iterator();
			List list = new ArrayList();
			Iterator iterator = getBuff().iterator();
			while (iterator.hasNext())
			{
				Object o  = (Object) iterator.next();
				List l =  SQLGenerator.getSQLStatements(o);
				list.addAll(l);
				
			    
			    
			}
			Iterator iter = list.iterator();
			while(iter.hasNext()){
				stmt.execute((String)iter.next());
			}
			conn.commit();
		}
		finally
		{
			try
			{
				stmt.close();
			}
			catch (Exception ex)
			{
			}
			try
			{
				conn.close();
			}
			catch (Exception ex)
			{
			}
		}
	}
	



	protected Connection createConn()
	{
		Connection con = null;
		try
		{
			if (getDbDriverClass() != null)
			{
				Class.forName(getDbDriverClass());
			}
			con = DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPwd());
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
		return con;
	}
	
	

	/**
	 * @return Returns the buff.
	 */
	public List getBuff()
	{
		return buff;
	}

	/**
	 * @param buff
	 * The buff to set.
	 */
	public void setBuff(List buff)
	{
		this.buff = buff;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}



	public String getDbDriverClass() {
		
		return dbDriverClass;
	}



	public void setDbDriverClass(String dbDriverClass) {
		this.dbDriverClass = dbDriverClass;
	}



	public String getDbPwd() {
		return dbPwd;
	}



	public void setDbPwd(String dbPwd) {
		this.dbPwd = dbPwd;
	}



	public String getDbUrl() {
		
		return dbUrl;
	}



	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}



	public String getDbUser() {
		return dbUser;
	}



	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	
}