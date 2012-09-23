
/*
 * RentalData
 *
 * Stores information about a video rental
 *
 */

package au.edu.swin.ict.dvdstore;
import java.io.Serializable;

import com.googlecode.objectify.*; 

import javax.persistence.Id;

public class RentalData implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8867272830290884105L;
	
	
	@Id String rental_id;  // unique ID for each rental record
	long staff_id;
	long video_id;
	long customer_id;
	String requested;
	String dispatched;
	String returned;

	public RentalData()
	{
	}

	public RentalData(long staff_id, long customer_id, long video_id, String requested, String dispatched, String returned)
	{
        this.staff_id = staff_id;
        this.video_id = video_id;
        this.customer_id = customer_id;
        this.requested = requested;
        this.dispatched = dispatched;
        this.returned = returned;
	}
	
	public String getID()
	{
		return rental_id;
	}

	public void setID(String id)
	{
		rental_id = id;
	}

	public long getStaffID()
	{
		return staff_id;
	}

	public void setStaffID(long id)
	{
		staff_id = id;
	}

	public long getVideoID()
	{
		return video_id;
	}

	public void setVideoID(long id)
	{
		video_id = id;
	}

	public long getCustomerID()
	{
		return customer_id;
	}

	public void setCustomerID(long id)
	{
		customer_id = id;
	}

	public String getRequested()
	{
		return requested;
	}

	public void setRequested(String id)
	{
		requested = id;
	}
	
	public String getDispatched()
	{
		return dispatched;
	}

	public void setDispatched(String id)
	{
		dispatched = id;
	}

	public String getReturned()
	{
		return returned;
	}

	public void setReturned(String returned)
	{
		this.returned = returned;
	}


}
