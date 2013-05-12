import java.util.*;

/**
 * Seasoncard is a subclass of Timebasedcard.
 * 
 * @author Erik Haider Forsén
 * @author Ole Hansen
 * @author Julie Hill Roa
 * @version 0.9
 */


public class Seasoncard extends Timebasedcard
{
	Calendar calHelper, nextSeason; 


/**
  *	Creates the Seasoncard
  *
  *	@param bd 	The card holders birthdate, determines if child discount should be used.
  * @param b	The creation date of the Card
  *
  */

	public Seasoncard( Date bd, Date b )
	{
		super(Info.SEASONCARDPRICE, bd, b, Skicard.SEASONCARD);  	
		calHelper = Calendar.getInstance(); 		
	}


/**
  *	This method is an abstract method in Timebasedcard. 
  * The method initializes the card, and gives the card an expire date. 
  * If the card is bought after season-end, the card will be available for use throughout next season.
  */
	public void initialized()
	{
		setExpires(new Date());
		nextSeason = Calendar.getInstance();


		calHelper.setTime( expires );
		calHelper.set( Calendar.MONTH, Calendar.MAY);
		calHelper.set( Calendar.DAY_OF_MONTH, 1 );
		calHelper.set( Calendar.HOUR_OF_DAY, 0 );
		calHelper.set( Calendar.MINUTE, 0 );
		calHelper.set( Calendar.SECOND, 0 );

		nextSeason = calHelper;
		nextSeason.add(Calendar.YEAR, 1);

		setExpires(calHelper.getTime());

//		if(Calendar.getInstance().after(calHelper.getTime()))
		if(calHelper.getTime().after( new Date() ))
		{
			setExpires(nextSeason.getTime());
		}

	}



/* This method is made for future versions, when it may become necessary to unvalidate a card.
 *
 *	public void unvalidate()
 *	{
 *		calHelper.setTime( new Date() );
 *		expires = calHelper.getTime();
 *	}  
 **/



/**
  * This is Seasonscards toString. It returns a string that contains a cards expire date.
  */

	public String toString()
	{
		
		String expires= null;

		if(super.getExpires() == null)
			expires = "ikke Validert";
		if (super.getExpires() != null)
		{
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(super.getExpires());
			expires = ( "" + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH ) + 1) + "." + cal.get(Calendar.YEAR ) );
		}


		return "Sesongkort\tGår ut: " + expires+  "\t" + super.toString();
	}
}
