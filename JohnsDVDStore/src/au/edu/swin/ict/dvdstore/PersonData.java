/*
 * PersonData class
 *
 * Provides abstract class for common kinds of person information e.g.
 * ID, name etc.
 *
 */

package au.edu.swin.ict.dvdstore;


import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.*; 


public abstract class PersonData implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8078060217475949512L;
	
	
	protected  @Id  long ID; // unique ID for person
    protected String name; // person's name
    protected int age; // person's age
    protected String password; // person's password (unencrypted :-)

    protected String action; // submit button value

	public PersonData()
	{
	}

	public PersonData(long ID, String name, int age, String password)
	{
	    this.ID = ID;
	    this.name = name;
	    this.age = age;
	    this.password = password;
	}

	public long getID()
	// getter method for ID
    {
        return ID;
    }

    public void setID(long ID)
    // set ID value - should only use when adding new person record
    {
        this.ID = ID;
    }

    public String getName()
    // returns person's name
    {
        return name;
    }

    public void setName(String name)
    // updates person's name
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean matchesPassword(String password)
    {
        if(this.password.equals(password))
            return true;
        else
            return false;
    }

    public String getAction()
    {
    	return action;
    }

    public void setAction(String action)
    {
		this.action = action;
    }

}
