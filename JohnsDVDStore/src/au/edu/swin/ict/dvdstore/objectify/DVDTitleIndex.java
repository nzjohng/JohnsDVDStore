package au.edu.swin.ict.dvdstore.objectify;

import java.io.Serializable;

import javax.persistence.Id;

public class DVDTitleIndex implements Serializable
{

 /**
	 * 
	 */
	private static final long serialVersionUID = 6868805251156731006L;
	
	@Id String index; // DVD ID - DVD id + Word
	long id; // DVD id
	String word; // title index word
	
	public DVDTitleIndex() {
		
	}
	
	public DVDTitleIndex(String index, long id, String word) {
		this.index = index;
		this.id = id;
		this.word = word;
	}
	
	
	
}
