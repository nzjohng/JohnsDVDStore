/*
 * CustomerManager interface
 *
 * Used to find, insert, update, delete CustomerData objects
 *
 */

package au.edu.swin.ict.dvdstore;

public interface CustomerManager
{

    public CustomerData findCustomer(long ID) throws Exception;
    // locate customer by ID value

    public void insertCustomer(CustomerData data) throws Exception;
    // insert new customer record into database

    public void updateCustomer(CustomerData data) throws Exception;
    // update customer record in database

    public void deleteCustomer(CustomerData data) throws Exception;
    // delete customer record from database

}
