package au.edu.swin.ict.dvdstore.mysql;

import java.sql.*;

import au.edu.swin.ict.dvdstore.StaffData;
import au.edu.swin.ict.dvdstore.StaffManager;

public class StaffManagerRDBMSImp implements StaffManager
{

    public StaffManagerRDBMSImp()
    {
    }

	public StaffData findStaff(long ID) throws Exception
	{
        ResultSet rs = MySQLConn.getInstance().executeQuery("SELECT * FROM dvd_staff WHERE id = "+ID);

        if(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String password = stripTrailing(rs.getString("passwd"));
            float salary = rs.getFloat("salary");
            String position = rs.getString("pos");
            rs.close();

            return new StaffData(id,name,age,password,position,salary);
        }

        return null;
	}


    public String stripTrailing(String value)
    // strip trailing blanks...
    {
        char cs[] = value.toCharArray();

        int end = cs.length;
        while(end > 0 && cs[end-1] == ' ')
            end--;

        return new String(cs,0,end);
    }

    public void insertStaff(StaffData data) throws Exception
    // insert new staff record into database
    {
        MySQLConn.getInstance().execute("INSERT INTO dvd_staff (id,name,age,passwd,pos,salary) VALUES ("+
            data.getID()+",'"+data.getName()+"',"+data.getAge()+",'"+data.getPassword()+"','"+data.getPosition()+"','"+data.getSalary()+"')");
    }

    public void updateStaff(StaffData data) throws Exception
    // update staff record in database
    {
        MySQLConn.getInstance().execute("UPDATE dvd_staff SET name='"+data.getName()+"', age="+data.getAge()+", passwd='"+data.getPassword()+"', pos='"+data.getPosition()+"', salary="+data.getSalary()+" WHERE id="+data.getID());
    }

    public void deleteStaff(StaffData data) throws Exception
    // delete staff record from database
    {
        MySQLConn.getInstance().execute("DELETE FROM dvd_staff WHERE id="+data.getID());
    }


}

