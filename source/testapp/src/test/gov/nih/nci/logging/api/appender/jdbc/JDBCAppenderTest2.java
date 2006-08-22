package test.gov.nih.nci.logging.api.appender.jdbc;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * JUNIT test class for the JDBCAppender.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 * 
 */
public class JDBCAppenderTest2 extends junit.framework.TestCase 
{
	public static Logger log = Logger.getLogger(JDBCAppenderTest2.class);
	protected static java.util.Random random = new java.util.Random();


	

	/*static Category cat = Category.getInstance(JDBCAppenderTest2.class.getName());*/

	public JDBCAppenderTest2(String testName)
	{
		super(testName);
	}

	public static void testAppend() throws Exception
	{
		/*DOMConfigurator.configure("resources/ObjectStateLoggerConfig.xml");*/
	/*	Logger log2 =Logger.getLogger("TestAppObjectStateLogger");*/
		Level debug= Level.DEBUG;
		
		
		// System.setProperty( "java.library.path", "/oracle/product/9i/bin" );
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++)
		{
			
			 
			log.log(debug, "Debug test event log message");
			/*cat.debug("DEBUG");
			cat.info("INFO");
			cat.error("ERROR");
			cat.fatal("FATAL");*/

		}
		long endTime = System.currentTimeMillis();

		System.out.println("Total elapsed time was: " + (endTime - startTime));

	}

	public static void main(String[] args) throws Exception
	{
		JDBCAppenderTest2.testAppend();
	}

}