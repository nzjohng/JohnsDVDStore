package au.edu.swin.ict.dvdstore;

public interface StaffManager
{

	public StaffData findStaff(long id) throws Exception;
	// find staff using ID

    public void insertStaff(StaffData data) throws Exception;
    // insert new staff record

    public void updateStaff(StaffData data) throws Exception;
    // update staff record

    public void deleteStaff(StaffData data) throws Exception;
    // delete staff record

}

