
package au.edu.swin.ict.dvdstore.mysql;

import java.sql.*;
import java.util.*;

import au.edu.swin.ict.dvdstore.RentalData;
import au.edu.swin.ict.dvdstore.RentalManager;

public class RentalManagerRDBMSImp implements RentalManager
{

    public RentalManagerRDBMSImp()
    {
    }

	public RentalData findRental(long customer_id, long video_id, String date_returned) throws Exception
	{
	// finds a video rental record...

		if(date_returned == null)
			date_returned = "null";

        ResultSet rs = MySQLConn.getInstance().executeQuery("SELECT * FROM dvd_rental WHERE video_id = "+video_id+" AND customer_id = "+customer_id+" AND date_returned='"+date_returned+"'");

        if(rs.next()) {
            int the_staff_id = rs.getInt("staff_id");
            int the_customer_id = rs.getInt("customer_id");
            int the_video_id = rs.getInt("video_id");
            String requested = rs.getString("date_requested");
            String dispatched = rs.getString("date_dispatched");
            String returned = rs.getString("date_returned");

            rs.close();
            rs = null;

            return new RentalData(the_staff_id,the_customer_id,the_video_id,requested,dispatched,returned);
        }

        return null;
	}
	
    public Vector findCustomerRentals(long customer_id) throws Exception
	{
	// finds a video rental record...
       ResultSet rs = MySQLConn.getInstance().executeQuery("SELECT * FROM dvd_rental WHERE customer_id = "+customer_id);

       Vector result = new Vector();
        while(rs.next()) {
            int the_staff_id = rs.getInt("staff_id");
            int the_customer_id = rs.getInt("customer_id");
            int the_video_id = rs.getInt("video_id");
            String requested = rs.getString("date_requested");
            String dispatched = rs.getString("date_dispatched");
            String returned = rs.getString("date_returned");

            result.addElement(new RentalData(the_staff_id,the_customer_id,the_video_id,requested,dispatched,returned));
        }

        rs.close();
        return result;
	}

	public void insertRental(RentalData data) throws Exception
	{
        MySQLConn.getInstance().execute("INSERT INTO dvd_rental (staff_id,customer_id,video_id,date_requested,date_dispatched,date_returned) VALUES ("+data.getStaffID()+","+data.getCustomerID()+","+data.getVideoID()+",'"+data.getRequested()+"','"+data.getDispatched()+"','"+data.getReturned()+"')");
	}
	
    public void dispatchDVD(RentalData data) throws Exception
    {
        MySQLConn.getInstance().execute("UPDATE dvd_rental SET date_dispatched="+data.getDispatched() +" where customer_id = "+data.getCustomerID()+" AND video_id = "+data.getVideoID()+" AND date_returned = 'null'");
    }

    public void returnDVD(RentalData data) throws Exception
    {
        MySQLConn.getInstance().execute("UPDATE dvd_rental SET date_returned="+data.getReturned() +" where customer_id = "+data.getCustomerID()+" AND video_id = "+data.getVideoID()+" AND date_returned = 'null'");
    }

    public void deleteRental(RentalData data) throws Exception
	{
		MySQLConn.getInstance().execute("DELETE FROM dvd_rental WHERE staff_id = "+data.getStaffID()+", customer_id = "+data.getCustomerID()+", video_id = "+data.getVideoID()+", returned ='"+data.getReturned()+"'");
	}

}

