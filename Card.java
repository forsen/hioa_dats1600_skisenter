import java.io.Serializable;
import java.util.*;

/**
 * Card is the class to create Card objects.
 *
 *	@author Erik Haider Forsén
 *	@author Ole Hansen
 *	@author Julie Hill Roa
 * @version 0.9
 */
public class Card implements Serializable
{
	private List<Skicard> skiCardList; 
	private int cardNumber;
	private Skicard current; 
	private boolean returned;
	private Date bought;  
	private static int next = 100000;


/**
  *	Creates the Card-object.
  *
  *	@param b 	The creation date of the Card
  *
  */
	public Card( Date b )
	{
		Skiresort.unsaved = true; 
		skiCardList = new LinkedList<>(); 
		cardNumber = ++next; 
		current = null; 
		bought = b;
		
	}
/**
  *	Method to add a Skicard to the Card. 
  * @param obj the Skicard to be added. It will also become the current Skicard
  * @return Returns information about the Skicard you added (its toString())
  *
  */
	public String input( Skicard obj )
	{
		skiCardList.add(obj);
		current = obj; 
		return obj.toString() + " ble lagt til " + cardNumber;
	}

/**
  * Method to retrieve the current Skicard
  * @return Returns the current Skicard object
  */
	public Skicard getCurrent()
	{
		return current; 
	}

/**
  * Method to set the current Skicard
  * @param c the Skicard to be set as current
  */
	public void setCurrent(Skicard c)
	{
		 current = c; 
	}
/**
  * Method to mark a Card as "returned". When a customer wish to return a Card, it's still kept in his Skicard list for 
  * keeping statistics etc. 
  */
	public void setReturned()
	{
		returned = true; 
	}

/**
  * Method to check if this card is returned, and should only appear in statistics
  * @return the returned status of this Card
  */
	public boolean getReturned()
	{
		return returned;
	}

/**
  * Method to retrieve information of when this Card was bought (created)
  * @return the creation date of this Card
  */
	public Date getCardBought()
	{
		return bought;
	}

/**
  * Method to retrieve the unique ID of this Card
  * @return ID of this Card
  */
	public int getCardID()
	{
		return cardNumber; 
	}

/**
  * Method to save the next Card number when writing to file, as static variables is not written with the Object. Unless we save this 
  * varible, we'll end up with several cards with the same ID, as it will start over at the beginning after a restart of the program.
  * @return returns the next available ID for new cards.
  * @see Skiresort#saveFile()
  */
	public static int readNext()
	{
		return next;
	}

/**
  * Method to write the next Card number when reading from file. 
  *
  * @param n the next available ID for Cards
  * @see #readNext()
  * @see Skiresort#readFile()
  */
	public static void setNext( int n )
	{
		next = n; 
	}

/**
  * Method to copy a Skicard list from one Card to another. 
  * @param c the Skicard list you want to set
  * @see ReplaceWindowPanel#replace()
  */
	public void setSkicardlist(List<Skicard> c)
	{
		skiCardList = c; 
	}

/**
  * Method to get the Skicard list for this Card. 
  * @return the Skicard list of this Card
  * @see ReplaceWindowPanel#replace()
  * @see #setSkicardlist( List )
  */
	public List<Skicard> getSkicardlist()
	{
		return skiCardList; 
	}

/**
  * Method to get the date when the current Skicard for this Card was bought
  * @return Date when the current card was bought
  */
	public Date getBought()
	{
		return current.getBought();
	}

/**
  * Method to help generate statistics.
  * @param start The start date of the interval you want to get Skicards from
  * @param end The end date of the interval you want to get Skicards from
  * @return Returns a list of all the Skicards this Card holds, between the dates specified. 
  * @see AdminStatisticsPanel
  */
	public List<Skicard> getRelevantCards(Date start, Date end)
	{
		List<Skicard> tempList = new LinkedList<>();
		Iterator<Skicard> it = skiCardList.iterator();

		while(it.hasNext())
		{
			Skicard runner = it.next();
			if( (runner.getBought().after(start) && runner.getBought().before(end)) 
				|| (runner.getBought().equals(start) || runner.getBought().equals(end)) )
			{
				tempList.add(runner);
			}
		}
		return tempList;

	}

/**
  * Method to get information on all the previous Skicards on this Card
  * @return A string containing information on all the previous Skicards on this Card
  * @see Skicard#toString()
  */
	public String history()
	{
		StringBuilder text = new StringBuilder();
		
		Iterator<Skicard> it = skiCardList.iterator();
		
		while(it.hasNext())
		{
			Skicard runner = it.next();
			text.append(runner.toString());
			text.append("\n");	
		} 
		
		String doneText = text.toString();
		
		return doneText;
	}


/**
  * The toString method for this Card
  * @return Returns a String containing some text and the ID of the Card
*/
	public String toString()
	{
		return "Kortnr: " + cardNumber;
	}
}
// end of class Card