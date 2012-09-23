/*
 * CustomerManagerImp class
 *
 * Provides implementation used to find, insert, update, delete CustomerData objects
 * This version uses JDBC to talk to a database.
 *
 * Note all data update methods are synchronised to stop inter-thread collisions.
 *
 */


package au.edu.swin.ict.dvdstore.objectify;

import java.sql.ResultSet;

import au.edu.swin.ict.dvdstore.CustomerData;
import au.edu.swin.ict.dvdstore.CustomerManager;

import com.googlecode.objectify.Objectify;

public class CustomerManagerObjectifyImp implements CustomerManager
{

    public CustomerManagerObjectifyImp()
    {
    }

    public synchronized CustomerData findCustomer(long ID) throws Exception
    // locate customer by ID value
    {
    	if(ID == 0)
    		return null;

    	Objectify ofy = DVDStoreObjectify.getInstance().getObjectify();
    	
    	try {
    		CustomerData c1 = ofy.get(CustomerData.class, ID);
    	
    		return c1;
    	} catch (com.googlecode.objectify.NotFoundException e) {
    		return null;
    	}
    		
    }

    public String stripTrailing(String value)
    // strip trailing blanks...
    {
        char cs[] = value.toCharArray();

        int end = cs.length;
        while(end > 0 && cs[end-1] == ' ')
            end--;

        return new String(cs,0,end);
    }

    public synchronized void insertCustomer(CustomerData data) throws Exception
    // insert new customer record into database
    {
    	Objectify ofy = DVDStoreObjectify.getInstance().getObjectify();
    	ofy.put(data);
    }

    public synchronized void updateCustomer(CustomerData data) throws Exception
    // update customer record in database
    {
    	Objectify ofy = DVDStoreObjectify.getInstance().getObjectify();
    	// will insert if not already there...
    	ofy.put(data);
    }

    public synchronized void deleteCustomer(CustomerData data) throws Exception
    // delete customer record from database
    {
    	Objectify ofy = DVDStoreObjectify.getInstance().getObjectify();
    	ofy.delete(data);
   }


}
