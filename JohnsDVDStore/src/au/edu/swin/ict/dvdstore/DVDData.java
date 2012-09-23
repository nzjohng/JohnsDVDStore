
// Manages video information

package au.edu.swin.ict.dvdstore;

import java.io.Serializable;

import javax.persistence.Id;

public class DVDData implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6868805251156731006L;
	
	
	@Id long ID; // unique ID
    String title; // video's title
    String category; // video's category
    double cost; // cost to hire e.g. 4.5 = $4.50
    int nights; // # nights can hire for e.g. 2
    String rating; // rating e.g. G, PG
    int num_copies; // # copies available

    String action; // for forms

	public DVDData()
	{
	}

	public DVDData(int id, String title, String category, double cost, int nights, String rating, int num_copies)
	{
		this.ID = id;
		this.title = title;
		this.category = category;
		this.cost = cost;
		this.nights = nights;
		this.rating = rating;
		this.num_copies = num_copies;
	}

    public long getID()
    {
        return ID;
    }

    public void setID(long ID)
    {
        this.ID = ID;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    public String getRating()
    {
        return rating;
    }

    public int getNights()
    {
        return nights;
    }

    public int getNumCopies()
    {
		return num_copies;
    }

    public void setNumCopies(int num)
    {
		num_copies = num;
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
