/*
 * Constants, initialisation etc
 * 
 */

package au.edu.swin.ict.dvdstore;

import java.util.Calendar;

import au.edu.swin.ict.dvdstore.objectify.DVDStoreObjectify;
import au.edu.swin.ict.dvdstore.mysql.DVDStoreMySQL;

public abstract class DVDStoreFactory {
	
	static private DVDStoreFactory instance = null;
	
	public static DVDStoreFactory getInstance()
	{
	    if(instance == null)
			instance = new DVDStoreObjectify(); // change to new DVDStoreMySQL();  to use mySQL database!
		
		return instance;
	}
	
	public abstract void begin() throws Exception; // start transaction
	public abstract void commit() throws Exception; // commit transaction
	public abstract void rollback() throws Exception; //rollback transaction
	
	public abstract void init() throws Exception;
	
	public abstract CustomerManager getCustomerManager();
	
	public abstract DVDManager getDVDManager();
	
	public abstract RentalManager getRentalManager();
	
	public abstract StaffManager getStaffManager();
	
	public static String getTodayDate()
	{
		Calendar c = Calendar.getInstance();
		
		String date = c.get(Calendar.YEAR)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.DAY_OF_MONTH);
		
		return date;
	}
	
}
