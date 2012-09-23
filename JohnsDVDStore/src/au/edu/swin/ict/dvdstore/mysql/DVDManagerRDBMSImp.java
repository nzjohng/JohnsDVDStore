package au.edu.swin.ict.dvdstore.mysql;

import java.sql.*;
import java.util.*;

import au.edu.swin.ict.dvdstore.DVDData;
import au.edu.swin.ict.dvdstore.DVDManager;

public class DVDManagerRDBMSImp implements DVDManager
{

    public DVDManagerRDBMSImp()
    {
    }

	public DVDData findDVD(long ID) throws Exception
	{
        ResultSet rs = MySQLConn.getInstance().executeQuery("SELECT * FROM dvd_dvd WHERE id = "+ID);

        if(rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String category = rs.getString("category");
            float cost = rs.getFloat("cost");
            int nights = rs.getInt("nights");
            String rating = rs.getString("rating");
            int num_copies = rs.getInt("num_copies");
            rs.close();

            return new DVDData(id,title,category,cost,nights,rating,num_copies);
        }

        return null;
	}

	public Vector findDVD(String title_part, String acategory) throws Exception
	{
        String query = "SELECT * FROM dvd_dvd WHERE title LIKE '%"+title_part+"%'";


        if(!acategory.equals(""))
            query = query+" AND DVD.category = '"+acategory+"'";

       ResultSet rs = MySQLConn.getInstance().executeQuery(query);

        Vector result = new Vector();

        while(rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String category = rs.getString("category");
            float cost = rs.getFloat("cost");
            int nights = rs.getInt("nights");
            String rating = rs.getString("rating");
            int num_copies = rs.getInt("num_copies");


            result.addElement(new DVDData(id,title,category,cost,nights,rating,num_copies));
        }

        rs.close();

        return result;
	}

    public void insertDVD(DVDData data) throws Exception
    {
        MySQLConn.getInstance().execute("INSERT INTO dvd_dvd (id,title,category,cost,nights,rating,num_copies) VALUES ("+data.getID()+",'"+data.getTitle()+"','"+data.getCategory()+"',"+data.getCost()+","+data.getNights()+",'"+data.getRating()+"',"+data.getNumCopies()+")");
    }

	public void updateDVD(DVDData data) throws Exception
	{
		MySQLConn.getInstance().execute("UPDATE dvd_dvd SET title = '"+data.getTitle()+"', category = '"+data.getCategory()+"', cost = "+data.getCost()+", rating = '"+data.getRating()+"', nights = "+data.getNights()+", num_copies = "+data.getNumCopies()+" WHERE id = "+data.getID());
	}

    public void deleteDVD(DVDData data) throws Exception
	{
		MySQLConn.getInstance().execute("DELETE FROM dvd_dvd WHERE id = "+data.getID());
	}

}

