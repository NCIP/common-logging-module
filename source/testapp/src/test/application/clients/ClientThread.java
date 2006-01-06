package test.application.clients;
/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */

import java.util.Set;

import gov.nih.nci.logging.api.logger.hibernate.HibernateSessionFactory;
import gov.nih.nci.logging.api.user.UserInfoHelper;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import test.application.domainobjects.Customer;
import test.application.domainobjects.Item;

/**
 *  Test class for the ObjectStateLogger.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 * 
 */

public class ClientThread extends Thread
{
	public static Logger log = Logger.getLogger("TEST");
	private String x = null;
	private int customerid;
	private int itemid;
	

	/**
	 * @param name
	 * @param id
	 */
	public ClientThread(String name, int id)
	{
		this.x = name;
		this.customerid = id;
		this.itemid = id;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{

		try
		{
			sleep((long) (Math.random() * 1000));
		}
		catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// get the Auditing session
		Session session = HibernateSessionFactory.getInstance().getAuditSession();
		// set the userName and sessionID
		UserInfoHelper.setUserInfo(new String("NAME" + x), new String("sessionId" + x));
		// create a new customer object
		Customer customer = new Customer();
		customer.setId(customerid);
		customer.setFirst("Bill");
		customer.setLast("Burke");
		customer.setState("MA");
		customer.setCity("Newland");
		customer.setStreet("1 Boston Road");
		customer.setZip("02115");

		Item item = new Item();
		item.setId(1);
		item.setName("pen");
		item.setmanufacturer("Burke");
		item.setPrice(12.5f);
		item.setCustomerid(customerid);

		customer.getItems().add(item);

		org.hibernate.Transaction tx = null;
		try
		{

			tx = session.beginTransaction();
			session.save(customer);
			tx.commit();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			session.close();
		}

		try
		{
			Set items = customer.getItems();
			Item itema = (Item) items.iterator().next();
			itema.setmanufacturer("Griffin Solutions");
			Item itemb = new Item();
			itemb.setName("New Item");
			itemb.setmanufacturer("Art Solutions");
			itemb.setCustomerid(customer.getId());
			itemb.setId(2);
			itemb.setPrice(12.5f);

			items.add(itemb);
			session = HibernateSessionFactory.getInstance().getAuditSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(customer);
			customer.setCity("Springfield");
			itemb.setmanufacturer("Kunal Solutions");
			tx.commit();

		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			session.close();
		}

	}

}
