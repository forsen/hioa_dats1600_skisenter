import java.util.Date; 

public abstract class Timebasedcard extends Skicard
{
	/**
	 * Timebasedcard is a subclass of Skicard, and an abstract class for Daycard, Hourcard and Seasoncard.
	 * 
	 * @author Erik Haider Forsén
	 * @author Ole Hansen
	 * @author Julie Hill Roa
	 * @version 0.9
	 */

	protected Date expires; 
	private Date lastValidated;

	/**
	  * Creates a time based card
	  * @param p 	the card price, retrieves from Skicard
	  * @param bd 	The card holders birthdate, determines if child discount should be used, retrieves from Skicard
	  * @param b 	The cards creation date.
	  * @param t 	The cards type.
	  * @see Skicard
	  */

	public Timebasedcard( int p, Date bd, Date b, int t )
	{
		super(p, bd, b, t);

	}

	/**
 	* This method sets expire date on a time based card.
 	* @param expires
  	*/

	public void setExpires(Date expires)
	{
		this.expires = expires;
	}


	/**
 	* This method gets expire date on a time based card.
  	*/
	public Date getExpires()
	{
		return expires;
	}

	/**
 	* This method sets a time for the last time the card was used successfully.
 	* @param d 		this date field is used in the control window. 
 	* @see Control
  	*/
	public void setLastValidated( Date d )
	{
		lastValidated = d; 
	}

	/**
	* This method gets the time for when a card was last used successfully
	*
	*/

	public Date getLastValidated()
	{
		return lastValidated;
	}

	/**
	  *	This method is used in the subclasses of Timebasedcard.
	  * The method initializes the card, and gives the card an expire date. 
	  * 
	  */
	abstract public void initialized();	

	



	/* This method is made for future versions, when it may become necessary to unvalidate a card.
	abstract public void unvalidate(); 	*/
}