/*
 * StaffData class
 *
 * Provides class for recording and managing staff data.
 * Inherits data from PersonData class.
 *
 */

package au.edu.swin.ict.dvdstore;

public class StaffData extends PersonData
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4740960006403490896L;
	
	
	protected String position; // staff member's position in store
    protected float salary; // staff member's salary in $

	public StaffData()
	{
	}

    public StaffData(long ID, String name, int age, String password, String position, float salary)
    {
        super(ID,name,age,password);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition()
    // returns staff position
    {
        return position;
    }

    public void setPosition(String position)
    // update staff position
    {
        this.position = position;
    }

   public float getSalary()
    // returns staff salary
    {
        return salary;
    }

    public void setSalary(float salary)
    // update staff salary
    {
        this.salary = salary;
    }

}
