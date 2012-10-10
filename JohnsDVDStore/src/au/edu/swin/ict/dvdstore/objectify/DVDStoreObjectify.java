package au.edu.swin.ict.dvdstore.objectify;

import au.edu.swin.ict.dvdstore.CustomerData;
import au.edu.swin.ict.dvdstore.CustomerManager;
import au.edu.swin.ict.dvdstore.DVDData;
import au.edu.swin.ict.dvdstore.DVDStoreFactory;
import au.edu.swin.ict.dvdstore.DVDManager;
import au.edu.swin.ict.dvdstore.RentalData;
import au.edu.swin.ict.dvdstore.RentalManager;
import au.edu.swin.ict.dvdstore.StaffData;
import au.edu.swin.ict.dvdstore.StaffManager;
import au.edu.swin.ict.dvdstore.mysql.DVDManagerRDBMSImp;
import au.edu.swin.ict.dvdstore.mysql.MySQLConn;

import com.googlecode.objectify.*;

import java.util.Hashtable;

public class DVDStoreObjectify extends DVDStoreFactory {
	
	private static boolean registered = false;
	
	public static DVDStoreObjectify getInstance()
	{
		return (DVDStoreObjectify) DVDStoreFactory.getInstance();
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub

		// Initialise google data store Entities...
		//
		if(registered)
			return;
		
		registered = true;

		Objectify ofy = ObjectifyService.begin();
		
		ObjectifyService.register(StaffData.class);		
		ObjectifyService.register(CustomerData.class);
		ObjectifyService.register(DVDData.class);
		ObjectifyService.register(RentalData.class);
		ObjectifyService.register(DVDTitleIndex.class);
		
		//if(getCustomerManager().findCustomer(1) == null) {
		
		ofy.put(new StaffData(1,"fred",44,"def","manager",25000));
		
			ofy.put(new CustomerData(1,"john",35, "abc","17 there st","9994567"));
		//}
		
		//if(getDVDManager().findDVD(1111) == null) {
			getDVDManager().insertDVD(new DVDData(1111,"Sleepless in Seattle","Romance",7.0, 7, "PG", 5));
			getDVDManager().insertDVD(new DVDData(1234,"Case 39","Horror",4.50, 7, "M", 3));
			getDVDManager().insertDVD(new DVDData(1212,"Sheakespeare in Love","Comedy",4.50, 7, "PG", 2));
			getDVDManager().insertDVD(new DVDData(9876,"Avatar","SciFi", 4.50, 7, "M", 3));
		//}
			
		getDVDManager().findDVD("a","");
		System.out.println(getDVDManager().findDVD(9876).getNumCopies());

	}
	
	
	Hashtable<Long,Objectify> thread_ofys = new Hashtable<Long,Objectify>();
	
	public Objectify getObjectify() 
	// get or allocate the Objectify instance for this thread
	{
		long id = Thread.currentThread().getId();
		Objectify obj = thread_ofys.get(new Long(id));
		if(obj != null)
			return obj;
		
		obj = ObjectifyService.begin();
		thread_ofys.put(new Long(id), obj);
		
		return obj;
	}
	
	
	public void begin() throws Exception // start transaction
	{
		// need to start recording changes made to entities
		long id = Thread.currentThread().getId();
		thread_ofys.remove(new Long(id));
		
		Objectify ofy = ObjectifyService.beginTransaction();
		
		thread_ofys.put(new Long(id), ofy);
	}
	
	public void commit() throws Exception // commit transaction
	{
		// roll back entity changes to start of transaction
		
		Objectify ofy = getObjectify();
		ofy.getTxn().commit();

		long id = Thread.currentThread().getId();
		thread_ofys.remove(new Long(id));
	}
	
	public void rollback() throws Exception //rollback transaction
	{
		// need to change entity state back to original
		
		Objectify ofy = getObjectify();
		ofy.getTxn().rollback();

		long id = Thread.currentThread().getId();
		thread_ofys.remove(new Long(id));
	}
	
	public CustomerManager getCustomerManager() {
		return new CustomerManagerObjectifyImp();
	}
	
	public DVDManager getDVDManager() {
		return new DVDManagerObjectifyImp();
	}
	
	public RentalManager getRentalManager() {
		return new RentalManagerObjectifyImp();
	}
	
	public StaffManager getStaffManager() {
		return new StaffManagerObjectifyImp();
	}

}
