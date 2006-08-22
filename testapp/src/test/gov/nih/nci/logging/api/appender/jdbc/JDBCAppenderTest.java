package test.gov.nih.nci.logging.api.appender.jdbc;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */



import org.apache.log4j.Category;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * JUNIT test class for the JDBCAppender.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 * 
 */
public class JDBCAppenderTest extends junit.framework.TestCase 
{
	protected static java.util.Random random = new java.util.Random();
	private final static char[] chars;

	static
	{
		chars = new char[52];
		for (int i = 0; i < 26; i++)
		{
			chars[i] = (char) (97 + i);
			chars[i + 26] = (char) (65 + i);
		}
	}

	protected static String generateString()
	{
		int length = random.nextInt(66);
		char[] array = new char[length];
		for (int i = 0; i < length; i++)
		{
			array[i] = chars[random.nextInt(chars.length)];
		}
		return new String(array);
	}

	static Category cat = Category.getInstance(JDBCAppenderTest.class.getName());

	public JDBCAppenderTest(String testName)
	{
		super(testName);
	}

	public static void testAppend() throws Exception
	{
		DOMConfigurator.configure("resources/sample_log4j_config.xml");

		
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++)
		{

			cat.debug("DEBUG");
			cat.info("INFO");
			cat.error("ERROR");
			cat.fatal("FATAL");
			cat.fatal("WARN");

		}
		long endTime = System.currentTimeMillis();

		System.out.println("Total elapsed time was: " + (endTime - startTime));

	}

	public static void main(String[] args) throws Exception
	{
		JDBCAppenderTest.testAppend();
	}

}