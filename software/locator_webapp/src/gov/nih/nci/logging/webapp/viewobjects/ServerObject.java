/*L
 *  Copyright Ekagra Software Technologies
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-logging-module/LICENSE.txt for details.
 */

package gov.nih.nci.logging.webapp.viewobjects;


public class ServerObject
{
	
	private String name;

	
	public ServerObject(String name)
	{
		this.name = name;
	}


	/**
	 * @return Returns the name.
	 */
	public String getName()
	{
		return name;
	}

	
	/**
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

}
