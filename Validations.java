import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Validations creates an object of a validation.
 * 
 * @author Erik Haider Forsén
 * @author Ole Hansen
 * @author Julie Hill Roa
 * @version 0.9
 */

public class Validations implements Serializable
{

	private int liftId;
	private Card card;
	private Date date; 


/**
  *	Creates the object Validations
  * It contains the liftnumber, a Card object, and a Date object.
  *
  *	@param l 		the lift ID
  * @param c 		the card object
  * @param d 		the date object 
  * @see Skiresort
  */

	public Validations( int l, Card c, Date d )
	{
		liftId = l; 
		card = c;
		if( d == null )
			date = new Date(); 
		else
			date = d; 
		
		Skiresort.unsaved = true; 
	}

/**
  * Returns the lift ID
  *
  * @return 	returns lift ID
  */

	public int getLiftId()
	{
		return liftId;
	}

/**
  * Returns the Card object
  *
  * @return 	returns object Card
  */

	public Card getCard()
	{
		return card;
	}

/**
  * Returns the Date object
  *
  * @return 	returns object Date
  */


	public Date getDate()
	{
		return date;
	}


/**
  * This is Validations toString. It returns a string that contains liftID, object Cards toString, 
  * card type, and the time of a validation.
  *
  * @return 	returns object Date
  */

	public String toString()
	{
		StringBuilder text = new StringBuilder();


		text.append( "Heis nr: " + liftId );
		text.append( "\n" );
		text.append( card.toString() );
		text.append( "\n" );
		text.append( "Korttype: " + card.getCurrent().getType() );
		text.append( "\n" );
		text.append( "Passert: " );
		text.append(new SimpleDateFormat("dd.MM.yy HH:mm").format(date));
		text.append( "\n\n\n");

		return text.toString();
	}
}//end of class Validations