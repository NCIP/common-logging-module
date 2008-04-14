package gov.nih.nci.logging.api.logger.util;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * @author Ekagra Software Technologes Limited ('Ekagra')
 * 
 * This class manages a ThreadLocal variable
 * 
 */
public class ThreadVariableGroupInfos
{
	// The next serial number to be assigned

	private static ThreadLocal groupInfos = new ThreadLocal();
	

	public static Object get()
	{
		return (Object) groupInfos.get();
	}

	public static void set(Object groupinfos)
	{
		groupInfos.set(groupinfos);
	}

	
	

}
