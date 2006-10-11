package gov.nih.nci.logging.api.appender.jdbc;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */
import gov.nih.nci.logging.api.appender.util.AppenderUtils;
import gov.nih.nci.logging.api.util.HibernateUtil;

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
	

	/**
	 * Constructor for JDBCExcecutor.
	 * 
	 * @param rows -
	 */
	public JDBCExecutor(List messages, Properties props)
	{
		setBuff(messages);
		setProps(props);
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
		
		HibernateUtil.setProperties(this.props);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		Iterator iterator = getBuff().iterator();
		int i=0;
		while (iterator.hasNext())
		{
			Object o  = (Object) iterator.next();
		    session.save(o);
		}
		tx.commit();
		session.flush();
        session.clear();
		
		
		session.close();
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

	
}