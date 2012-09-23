package au.edu.swin.ict.dvdstore.mysql;

import au.edu.swin.ict.dvdstore.CustomerManager;
import au.edu.swin.ict.dvdstore.DVDManager;
import au.edu.swin.ict.dvdstore.DVDStoreFactory;
import au.edu.swin.ict.dvdstore.RentalManager;
import au.edu.swin.ict.dvdstore.StaffManager;

public class DVDStoreMySQL extends DVDStoreFactory {


	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub

		// do DB connect...
		//
		if(!MySQLConn.getInstance().isConnected())
			MySQLConn.getInstance().connect();
		
		MySQLConn.getInstance().setAutoCommit(true);
	}
	
	public void begin() throws Exception // start transaction
	{
		MySQLConn.getInstance().setAutoCommit(false);
	}
	public void commit() throws Exception // commit transaction
	{
		MySQLConn.getInstance().commit();
		MySQLConn.getInstance().setAutoCommit(true);
	}
	public void rollback() throws Exception //rollback transaction
	{
		MySQLConn.getInstance().rollback();
		MySQLConn.getInstance().setAutoCommit(true);
	}


	public CustomerManager getCustomerManager() {
		return new CustomerManagerRDBMSImp();
	}
	
	public DVDManager getDVDManager() {
		return new DVDManagerRDBMSImp();
	}
	
	public RentalManager getRentalManager() {
		return new RentalManagerRDBMSImp();
	}
	
	public StaffManager getStaffManager() {
		return new StaffManagerRDBMSImp();
	}
	
}
