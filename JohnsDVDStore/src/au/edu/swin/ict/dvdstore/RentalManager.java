package au.edu.swin.ict.dvdstore;

public interface RentalManager
{

	public RentalData findRental(long customer_id, long video_id, String returned) throws Exception;
	// find Rental using customer & video IDs plus date returned (which can be null)
	
	public void insertRental(RentalData data) throws Exception;
	// insert new Rental record

	public void dispatchDVD(RentalData data) throws Exception;
	// update Rental's properties
	
	public void returnDVD(RentalData data) throws Exception;
	// update Rental's properties

	public void deleteRental(RentalData data) throws Exception;
	// delete Rental record

    public java.util.Vector findCustomerRentals(long customer_id) throws Exception;

}

