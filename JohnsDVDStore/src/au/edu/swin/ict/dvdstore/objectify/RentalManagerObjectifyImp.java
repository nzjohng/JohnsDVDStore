
package au.edu.swin.ict.dvdstore.objectify;


import java.util.*;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;


import au.edu.swin.ict.dvdstore.RentalData;
import au.edu.swin.ict.dvdstore.RentalManager;

public class RentalManagerObjectifyImp implements RentalManager
{

    public RentalManagerObjectifyImp()
    {
    }

	public RentalData findRental(long customer_id, long video_id, String is_returned) throws Exception
	{
	// finds a video rental record...

		Objectify ofy = DVDStoreObjectify.getInstance().getObjectify();
        
        Query<RentalData> q;
        
        if(is_returned == null)
        	is_returned = "null";
        
        q = ofy.query(RentalData.class).filter("customer_id", customer_id).filter("video_id",video_id).filter("returned",is_returned);
                
        RentalData d = q.get();
        
        if(d != null && is_returned != null && !is_returned.equals(d.getReturned()))
        	return null;
        
        return d;
	}

    public Vector findCustomerRentals(long customer_id) throws Exception
	{
    	// finds all video rental records for customer...
    	Objectify ofy = DVDStoreObjectify.getInstance().getObjectify();
	   	
	   	Vector<RentalData> result = new Vector<RentalData>();
        
        Query<RentalData> q;
        
        q = ofy.query(RentalData.class).filter("customer_id=", customer_id);
           
        for (RentalData rental : q) {
            result.add(rental);
        }

        return result;
    	
 	}

	public void insertRental(RentalData data) throws Exception
	{
		if(data.getID()==null) {
			data.setID(java.util.UUID.randomUUID().toString());
		}
		if(data.getRequested() == null)
			data.setRequested("null");
		if(data.getReturned() == null)
			data.setReturned("null");
		
		
		DVDStoreObjectify.getInstance().getObjectify().put(data);
	}
	
    public void dispatchDVD(RentalData data) throws Exception
    {
    	DVDStoreObjectify.getInstance().getObjectify().put(data);
   }

    public void returnDVD(RentalData data) throws Exception
    {
    	DVDStoreObjectify.getInstance().getObjectify().put(data);
    }

    public void deleteRental(RentalData data) throws Exception
	{
    	DVDStoreObjectify.getInstance().getObjectify().delete(data);
	}

}

