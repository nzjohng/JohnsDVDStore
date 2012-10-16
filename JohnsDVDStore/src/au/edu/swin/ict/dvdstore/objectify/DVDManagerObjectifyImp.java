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
        
        Query<DVDTitleIndex> q;
        
        String search = title_part.toLowerCase();
        
        // Find all DVD title word indexes for words starting with "title_part" characters...
        //
        // to get around objectify querying limitations and mimics SQL  LIKE "...%" WHERE clause component
        //
        // could extend to search for > 1 word part the title (need to take intersection of results)
        //
        q = ofy.query(DVDTitleIndex.class).filter("word >=", search).filter("word <", search+"\uFFFD");
        
       // List<DVDTitleIndex> l = ofy.query(DVDTitleIndex.class).list();  // for testing...

        
        /*
        if(!acategory.equals(""))
        	q = ofy.query(DVDData.class).filter("title > ", title_part).filter("category =", acategory);
        else
        	q = ofy.query(DVDData.class).filter("title > ", title_part);
        */
        
        for (DVDTitleIndex dt: q) {
        	DVDData dvd = ofy.get(DVDData.class,dt.id);
        	if(dvd != null)
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
    	deleteDVDIndex(data);
    	DVDStoreObjectify.getInstance().getObjectify().delete(data);
	}
    
    /*
     * Create an index of form <dvd_id,word> so can search for DVDs for parts of title words
     * 
     */
    public void indexDVD(DVDData data) {
    
    	deleteDVDIndex(data);
    	
    	Objectify ofy = DVDStoreObjectify.getInstance().getObjectify();
    	
    	char to_index[] = (data.getTitle()+" ").toLowerCase().toCharArray();
    	int i = 0;
    	int start = 0;
    	int l = to_index.length;
    	while(i < l) {
    		if(to_index[i] == ' ') {
    			if(start < i) {
    				String word = new String(to_index,start,i-start);
    				System.out.println(word);
    				DVDTitleIndex dt = new DVDTitleIndex(data.getID()+"."+word+"."+start,data.getID(),word);
    				ofy.put(dt);
    			}
    			start = i+1;
    		}
    		i++;
    	}
    }

    
    /*
     * Remove all word indexing for this DVD
     * 
     */
    public void deleteDVDIndex(DVDData data) {
    	Query<DVDTitleIndex> q;
    	Objectify ofy = DVDStoreObjectify.getInstance().getObjectify();
    	
    	q = ofy.query(DVDTitleIndex.class).filter("id = ",data.getID());
    
    	ofy.delete(q);
    }

}

