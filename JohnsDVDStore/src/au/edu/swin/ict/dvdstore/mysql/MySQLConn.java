/*
 * MySQLConn class
 *
 * Provides basic connect, disconnect functions used by Data management
 * classes. Want to share connection between different data classes & threads
 * to avoid creating huge numbers of them, hence use of this class.
 *
 * Note it uses the Singleton pattern to ensure only one instance created...
 *
 * Note all data manipulation functions are synchronized to ensure only one
 * thread can be calling each of them at a time.
 *
 */

package au.edu.swin.ict.dvdstore.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLConn
{

    protected boolean connected = false;
    private Connection con;

    private String driverName = "com.mysql.jdbc.Driver";
    // private String dbName = "jdbc:mysql://localhost/dvdstore1";

    private static MySQLConn instance = new MySQLConn();
      // shared DB instance
      // need to modify to support connection pool & lock-per-thred 

    public MySQLConn()
    {

    }

    synchronized public static MySQLConn getInstance()
    // modify to allow pool of connections & allocate for this thread
    {
        return instance;
    }

    synchronized public void connect() throws Exception
    // connect to MySQL database server
    {
   	    if(connected)
   	        return;
 
   	    DriverManager.getDriver("jdbc:google:rdbms://johnsdvdstoredb:dvdstore1/mysql");
   	    con = DriverManager.getConnection("jdbc:google:rdbms://johnsdvdstoredb:dvdstore1/mysql");
   	    con.setAutoCommit(true);
   	    connected = true;
   	}


    synchronized public void disconnect() throws Exception
    // disconnect from MySQL database server
    {
        con.close();
        connected = false;
    }

    synchronized public boolean isConnected()
    {
		return connected;
    }

    public synchronized void execute(String sql) throws Exception
    {
        Statement stmt = con.createStatement();
        stmt.execute(sql);
    }

    public synchronized ResultSet executeQuery(String sql) throws Exception
    {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    public synchronized void setAutoCommit(boolean status) throws Exception
    {
      con.setAutoCommit(status);
    }
    
    public synchronized void begin() throws Exception
    // begin transactional updates to database
    {
        con.setAutoCommit(false);
        
        // if single connection need  to block all other requests until this one commits/rollbacks
        
    }


    public synchronized void commit() throws Exception
    // commit updates to database
    {
        con.commit();
    }

    public synchronized void rollback() throws Exception
    // undo all updates since last commit
    {
        con.rollback();
    }

}

