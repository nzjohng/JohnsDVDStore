package au.edu.swin.ict.dvdstore.objectify;

import java.sql.*;
import java.util.*;

import au.edu.swin.ict.dvdstore.DVDData;
import au.edu.swin.ict.dvdstore.DVDManager;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class DVDManagerObjectifyImp implements DVDManager
{

    public DVDManagerObjectifyImp()
    {
    }

	public DVDData findDVD(long ID) throws Exception
	{
		Objectify ofy = DVDStoreObjectify.getInstance().getObjectify();
    	
    	try {
    		DVDData d1 = ofy.get(DVDData.class, ID);
    	
    		return d1;
    	} catch (com.googlecode.objectify.NotFoundException e) {
    		return null;
    	}
    		

	}

	public Vector<DVDData> findDVD(String title_part, String acategory) throws Exception
	{
		Objectify ofy = DVDStoreObjectify.getInstance().getObjectify();

        // change to List<DVDData> & ArrayList() !!
        Vector<DVDData> result = new Vector<DVDData>();
        
        Query<DVDData> q;
        
        if(!acategory.equals(""))
        	q = ofy.query(DVDData.class).filter("title > ", title_part).filter("category =", acategory);
        else
        	q = ofy.query(DVDData.class).filter("title > ", title_part);
        
        for (DVDData dvd: q) {
            result.add(dvd);
        }

        return result;
	}

    public void insertDVD(DVDData data) throws Exception
    {
    	DVDStoreObjectify.getInstance().getObjectify().put(data);
    	indexDVD(data);
    }

	public void updateDVD(DVDData data) throws Exception
	{
		DVDStoreObjectify.getInstance().getObjectify().put(data);
		indexDVD(data);
	}

    public void deleteDVD(DVDData data) throws Exception
	{
    	DVDStoreObjectify.getInstance().getObjectify().delete(data);
    	deleteDVDIndex(data);
	}
    
    public void indexDVD(DVDData data) {
    	
    }
    
    public void deleteDVDIndex(DVDData data) {
    	Query<DVDTitleIndex> q;
    	Objectify ofy = DVDStoreObjectify.getInstance().getObjectify();
    	
    	q = ofy.query(DVDTitleIndex.class).filter("id = ",data.getID());
    
    	ofy.delete(q);
    }

}

