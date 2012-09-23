package au.edu.swin.ict.dvdstore;

public interface DVDManager
{

	public DVDData findDVD(long id) throws Exception;
	// find video using ID

	public java.util.Vector findDVD(String title_part, String acategory) throws Exception;
	// find video using title/category

	public void insertDVD(DVDData data) throws Exception;
	// insert new video record

	public void updateDVD(DVDData data) throws Exception;
	// update video's properties

	public void deleteDVD(DVDData data) throws Exception;
	// delete video record

}

