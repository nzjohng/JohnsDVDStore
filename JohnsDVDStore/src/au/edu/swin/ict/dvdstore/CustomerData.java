/*
 * CustomerData class
 *
 * Provides class for recording and managing customer data.
 * Inherits data from PersonData class.
 * Storage & retrieval from database now done by CustomerManager.
 * Can be serialised across RMI connection.
 * Interface "CustomerData" will be used in CORBA verison...
 *
 * Used by CustomerMaintenance program, rent/return, fines calculation
 * etc etc
 *
 */

package au.edu.swin.ict.dvdstore;

import com.googlecode.objectify.*; 

public class CustomerData extends PersonData
{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -163710591045138355L;
	
	protected String address; // customer's address
    protected String phone; // customer's phone #

    protected int certificate; // for web-based logins

    // could have other stuff like outstanding fines, preffered status, etc.

	public CustomerData()
	{
	}

    public CustomerData(long ID, String name, int age, String password, String address, String phone)
    {
        super(ID,name,age,password);
        this.address = address;
        this.phone = phone;
    }

    public String getAddress()
    // returns customer's address
    {
        return address;
    }

    public void setAddress(String address)
    // update customer's address
    {
        this.address = address;
    }

   public String getPhone()
    // returns customer's phone #
    {
        return phone;
    }

    public void setPhone(String phone)
    // update customer's phone #
    {
        this.phone = phone;
    }

    public int getCertificate()
    {
      return certificate;
    }

    public void setCertificate(int certificate)
    {
      this.certificate = certificate;
    }

    public boolean authenticateUser(int certificate)
    {
      return (getCertificate() == certificate);
    }
}
