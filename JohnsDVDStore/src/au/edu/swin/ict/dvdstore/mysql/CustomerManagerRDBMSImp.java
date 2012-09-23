/*
 * CustomerManagerImp class
 *
 * Provides implementation used to find, insert, update, delete CustomerData objects
 * This version uses JDBC to talk to a database.
 *
 * Note all data update methods are synchronised to stop inter-thread collisions.
 *
 */


package au.edu.swin.ict.dvdstore.mysql;

import java.sql.ResultSet;

import au.edu.swin.ict.dvdstore.CustomerData;
import au.edu.swin.ict.dvdstore.CustomerManager;

public class CustomerManagerRDBMSImp implements CustomerManager
{

    public CustomerManagerRDBMSImp()
    {
    }

    public synchronized CustomerData findCustomer(long ID) throws Exception
    // locate customer by ID value
    {
        ResultSet rs = MySQLConn.getInstance().executeQuery("SELECT * FROM dvd_customer WHERE id = "+ID);

        if(rs.next()) {

            long id = rs.getInt("id");
            String name = stripTrailing(rs.getString("name"));
            int age = rs.getInt("age");
            String password = stripTrailing(rs.getString("pword"));
            String address = stripTrailing(rs.getString("address"));
            String phone = stripTrailing(rs.getString("phone"));

            rs.close();

            // NOTE: Nothing is being done to check multiple threads don't get the
            // same customer record, update it independently, and then each update
            // it, over-writting each others changes...
            //
            // To solve this, need to e.g. cache selected customer IDs/objects, return
            // same object from cache, provide "freeCustomer()" function to clear cache
            // when thread no longer needs object etc.
            //
            // Could also e.g. lock the customer record in the database (if DB engine supports
            // this) or lock it in this manager class (using an ID lookup table)
            // and reject requests for a customer record until it is "unlocked".
            // I, personally, prefer this second method,
            // though its a fairly pessimistic algorithm...

            return new CustomerData(id,name,age,password,address,phone);
        }

        return null;
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
        MySQLConn.getInstance().execute("INSERT INTO dvd_customer (id,name,age,pword,address,phone) VALUES ("+
            data.getID()+",'"+data.getName()+"',"+data.getAge()+",'"+data.getPassword()+"','"+data.getAddress()+"','"+data.getPhone()+"')");
    }

    public synchronized void updateCustomer(CustomerData data) throws Exception
    // update customer record in database
    {
         MySQLConn.getInstance().execute("UPDATE dvd_customer SET name='"+data.getName()+"', age="+data.getAge()+", pword ='"+data.getPassword()+"', address='"+data.getAddress()+"', phone='"+data.getPhone()+"' WHERE id="+data.getID());
    }

    public synchronized void deleteCustomer(CustomerData data) throws Exception
    // delete customer record from database
    {
         MySQLConn.getInstance().execute("DELETE FROM dvd_customer WHERE id="+data.getID());
    }


}
