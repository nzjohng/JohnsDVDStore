
package au.edu.swin.ict.dvdstore;

import java.io.Serializable;
import java.util.*;


public class DVDInterface implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5190316235621450917L;

	public DVDInterface()
	{
		
		
	}

	private transient  DVDManager DVD_manager = DVDStoreFactory.getInstance().getDVDManager();
	private transient  RentalManager rental_manager = DVDStoreFactory.getInstance().getRentalManager();
	
	public DVDManager getDVDManager() {
		if(DVD_manager == null)
			DVD_manager = DVDStoreFactory.getInstance().getDVDManager();
		return DVD_manager;
	}
	
	public RentalManager getRentalManager() {
		if(rental_manager == null)
			rental_manager = DVDStoreFactory.getInstance().getRentalManager();
		return rental_manager;
	}
	
	public void processRequest(String action, CustomerData customer_data, DVDData DVD_data)
	{
		if(action == null)
			return;


        // setDVD(null);

		if(action.equals("Search"))
			doFindDVD(DVD_data);
		else if(action.equals("Details"))
			doDVDDetails(DVD_data);
		else if(action.equals("Rent"))
			doDVDRental(customer_data, DVD_data);

	}

	private String message = "";

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}

	private Vector DVDs = new Vector();

	public void setDVDs(Vector DVDs)
	{
		this.DVDs = DVDs;
	}

	public Vector getDVDs()
	{
		return DVDs;
	}

	public void doFindDVD(DVDData DVD_data)
	{
		if(DVD_data.getTitle() == null)
			setMessage("Enter a title value");
		else
		{
			if(DVD_data.getCategory() == null)
				DVD_data.setCategory("");

			try {
                          DVDs = getDVDManager().findDVD(DVD_data.getTitle(),DVD_data.getCategory());
                          setDVD(null); // so don't display last details items
                          setMessage(DVDs.size()+" DVDs found...");
			} catch (Exception e) {
				setMessage("Error selecting DVDs: "+e.toString());
			}
		}
	}

	DVDData DVD = null;

	public void setDVD(DVDData DVD)
	{
		this.DVD = DVD;
	}

	public DVDData getDVD()
	{
		return DVD;
	}

	public void doDVDDetails(DVDData DVD_data)
	{
		if(DVD_data.getID() == 0)
			setMessage("Select a DVD...");
		else
		{
			try {
				DVD = getDVDManager().findDVD(DVD_data.getID());
				setDVDs(new Vector()); // zero any previous search results
				setMessage("Found DVD");
			} catch (Exception e) {
				DVD = null;
				setMessage("Error selecting DVDs: "+e.toString());
			}
		}
	}
	
	public void doDVDRental(CustomerData customer_data, DVDData DVD_data) {
		if(DVD_data.getID() == 0)
			setMessage("Please select a DVD to rent...");
		else
		{
			try {
				DVD_data = getDVDManager().findDVD(DVD_data.getID());
				if(DVD_data.getNumCopies() < 1)
					setMessage("No more copies of this DVD available");
				else{
					RentalData rental = getRentalManager().findRental(customer_data.getID(), DVD_data.getID(), null);
					if(rental != null) {
						if(rental.getDispatched() == null || rental.getDispatched().equals("null")) {
							setMessage("DVD already requested to be rented - awaiting dispatch");
							return;
						} else {
							if(rental.getReturned() == null || rental.getDispatched().equals("null")) {
								setMessage("DVD already rented - awaiting return");
								return;
							}		
						}
					}
					// never rented OR rented & returned
					
					rental  = new RentalData(0,customer_data.getID(),DVD_data.getID(),DVDStoreFactory.getTodayDate(),null,null);
					getRentalManager().insertRental(rental);
					
					setDVD(null);
					setDVDs(new Vector());
						
					setMessage("DVD "+DVD_data.getTitle()+" requested to dispatch added!");
				}
			} catch (Exception e) {
				DVD = null;
				setMessage("Error selecting DVDs: "+e.toString());		
			}
		}
	}
	

	
}

