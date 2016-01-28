package no.forsen.skisenter;

import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Daycard is a subclass of Timebasedcard.
 * 
 * @author Erik Haider Forsén
 * @author Ole Hansen
 * @author Julie Hill Roa
 * @version 0.9
 */


public class Daycard extends Timebasedcard
{
	Calendar calHelper; 

/**
  *	Creates the Daycard
  *
  *	@param bd 	The card holders birthdate, determines if child discount should be used.
  * @param b	The creation date of the Card
  *
  */
	

	public Daycard( Date bd, Date b)
	{
		super( Info.DAYCARDPRICE, bd, b, Skicard.DAYCARD); 	
		calHelper = Calendar.getInstance(); 		
	}

/**
  *	This method is an abstract method in Timebasedcard. 
  * The method initializes the card, and gives the card an expire date. 
  * 
  */
		public void initialized()
		{
			setExpires(new Date());


			calHelper.setTime( expires );
			calHelper.set( Calendar.HOUR_OF_DAY, 23 );
			calHelper.set( Calendar.MINUTE, 0 );
			calHelper.set( Calendar.SECOND, 10 );
			setExpires(calHelper.getTime());
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
  * This is Dayscards toString. It returns a string that contains a cards expire date.
  */
	public String toString()
	{

		String expires = null;

		if(super.getExpires() == null)
			expires = "ikke Validert";
		if (super.getExpires() != null)
		{
			expires = new SimpleDateFormat("dd.MM.yy").format( super.getExpires() );
		}

		return "Dagskort\tGår ut: " + expires + "\t" +super.toString();
	}
}