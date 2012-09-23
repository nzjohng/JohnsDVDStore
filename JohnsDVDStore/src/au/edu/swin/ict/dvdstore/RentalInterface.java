package au.edu.swin.ict.dvdstore;

import java.io.Serializable;



/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class RentalInterface implements Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 2778779912627434237L;

public RentalInterface() {
  }

  private transient StaffManager staff_manager = DVDStoreFactory.getInstance().getStaffManager();
  private transient CustomerManager customer_manager = DVDStoreFactory.getInstance().getCustomerManager();
  private transient DVDManager DVD_manager =  DVDStoreFactory.getInstance().getDVDManager();
  private transient RentalManager rental_manager = DVDStoreFactory.getInstance().getRentalManager();
  
  public StaffManager getStaffManager() {
	  if(staff_manager == null)
		  staff_manager = DVDStoreFactory.getInstance().getStaffManager();
	  return staff_manager;
  }
  
  public CustomerManager getCustomerManager() {
	  if(customer_manager == null) {
		  customer_manager = DVDStoreFactory.getInstance().getCustomerManager();
	  }
	  return customer_manager;
  }
  
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


  public void processRequest(
    String action,
    StaffData staff_data,
    CustomerData customer_data,
    DVDData DVD_data
  ) {

    if(action == null) {
      setMessage("");
      return;
    }

   
    
    if(action.equals("findStaff"))
      doFindStaff(staff_data);
    else if(action.equals("findCustomer"))
      doFindCustomer(customer_data);
    else if(action.equals("findDVD"))
      doFindDVD(DVD_data);
    else if(action.equals("rentDVD"))
      doRentDVD(staff_data,customer_data,DVD_data);
    else if(action.equals("returnDVD"))
      doReturnDVD(staff_data,customer_data,DVD_data);
    else
    	setMessage("Unknown action "+action);
  }

  private String message = "";

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public void doFindStaff(StaffData staff_data)
  {
    try {
        if(staff_data.getID() == 0) {
          setMessage("Enter your staff ID");
          staff_data.setID(0);
          return;
        }

        if(staff_data.getPassword().equals("")) {
          setMessage("Enter your password");
          staff_data.setID(0);
          return;
        }

        StaffData s2 = getStaffManager().findStaff(staff_data.getID());

        if(s2 != null)
        {
          if(s2.matchesPassword(staff_data.getPassword())) {
            setMessage("Found staff member");
            staff_data.setName(s2.getName());
          } else
            setMessage("That password is incorrect");

        } else
            setMessage("Can't find that staff record");
    } catch (Exception e) {
      setMessage("Error finding staff: "+e.toString());
      staff_data.setID(0);
    }
  }

  public void doFindCustomer(CustomerData customer_data)
  {
    if(customer_data.getID() == 0) {
      setMessage("Enter customer ID");
      customer_data.setID(0);
      return;
    }

    try {
      CustomerData c2 = getCustomerManager().findCustomer(customer_data.getID());
      if(c2 != null) {
        setMessage("Customer found");
        customer_data.setName(c2.getName());
        customer_data.setAddress(c2.getAddress());
      } else {
        setMessage("Can't find that customer record");
      }
    } catch (Exception e) {
      setMessage("Error looking up customer: "+e.toString());
      customer_data.setID(0);
    }
  }

  public void doFindDVD(DVDData DVD_data)
  {
    if(DVD_data.getID() == 0) {
      setMessage("Enter DVD ID");
      DVD_data.setID(0);
      return;
    }

    try {
      DVDData v2 = getDVDManager().findDVD(DVD_data.getID());
      if(v2 != null) {
        setMessage("DVD found");
        DVD_data.setTitle(v2.getTitle());
      } else {
        setMessage("Can't find that DVD record");
      }
    } catch (Exception e) {
      setMessage("Error looking up DVD: "+e.toString());
      DVD_data.setID(0);
    }
  }

  public void doRentDVD(
    StaffData staff_data,
    CustomerData customer_data,
    DVDData DVD_data
  )
  {
    if(staff_data.getID() == 0 || customer_data.getID() == 0 || DVD_data.getID() == 0) {
      setMessage("Enter staff, customer and DVD IDs first");
      return;
    }
     
    try {
    	// populate objects
    	staff_data = getStaffManager().findStaff(staff_data.getID());
    	customer_data = getCustomerManager().findCustomer(customer_data.getID());
        DVD_data = getDVDManager().findDVD(DVD_data.getID());
        
        // has customer asked for this DVD??
    	RentalData rental = getRentalManager().findRental(customer_data.getID(),DVD_data.getID(),null);
    	if(rental != null) {
    		if(rental.getDispatched() != null && !rental.getDispatched().equals("null")) {
    			setMessage("Already dispatched to customer on "+rental.getDispatched());
    			return;
    		}

    	try {

          	// need to do begin transaction...
            DVDStoreFactory.getInstance().begin();
      	  
            // indicate sent to customer & by whom
        	rental.setStaffID(staff_data.getID());
        	rental.setDispatched(DVDStoreFactory.getTodayDate());
        	getRentalManager().dispatchDVD(rental);
          
            // indicate number of copies available now reduced by 1 - should change to do on REQUEST !!!

            DVD_data.setNumCopies(DVD_data.getNumCopies()-1);
            getDVDManager().updateDVD(DVD_data);

            // need to do commit transaction...
            DVDStoreFactory.getInstance().commit();

            setMessage("Rented DVD"); 
        	
    	} catch (Exception e) {
  	         setMessage("Couldn't rent DVD: exception: "+e);
	         try { 
	         	// need to do rollback of transaction...
	         	DVDStoreFactory.getInstance().rollback();
	         	
	         } catch (Exception e2) { 
	         	setMessage("*** Couldn't roll back transaction: "+e);
	         }
    	}
      } else
    	  setMessage("This DVD not requested by customer!");
    } catch (Exception e) {
    	setMessage("Couldn't check DVD: exception:"+e);
    }
       
  }

  public void doReturnDVD(
    StaffData staff_data,
    CustomerData customer_data,
    DVDData DVD_data)
  {
    if(staff_data.getID() == 0 || customer_data.getID() == 0 || DVD_data.getID() == 0) {
      setMessage("Enter staff, customer and DVD IDs first");
      return;
    }

    try {
    	// populate objects...
    	staff_data = getStaffManager().findStaff(staff_data.getID());
    	customer_data = getCustomerManager().findCustomer(customer_data.getID());
        DVD_data = getDVDManager().findDVD(DVD_data.getID());

    	// has customer rented this DVD?
        RentalData rental_data = getRentalManager().findRental(customer_data.getID(),DVD_data.getID(),null);
        if(rental_data == null || !rental_data.getReturned().equals("null"))
          setMessage("This DVD not rented by this customer!");
        else {
      	  try {
          	  // begin transaction...
              DVDStoreFactory.getInstance().begin();

        	  
              rental_data.setReturned(DVDStoreFactory.getTodayDate());
              getRentalManager().returnDVD(rental_data);
              DVD_data.setNumCopies(DVD_data.getNumCopies()+1);
              getDVDManager().updateDVD(DVD_data);

              // need to commit transaction...
            
              DVDStoreFactory.getInstance().commit();

              setMessage("Returned DVD");
      				
            } catch (Exception e) {
              setMessage("Couldn't return DVD: exception "+e);
              try { 
              	// need to rollback transaction
                  DVDStoreFactory.getInstance().rollback();
              } catch (Exception e2) { 
            	  setMessage("Couldn't rollback transaction: exception "+e);
              }
            }
        }
    } catch (Exception e) {
       setMessage("couldn't check DVD record: "+e);        
    }
  }

}