package no.forsen.skisenter;

import java.io.*;
import java.util.*;
import java.lang.Math;
import java.text.NumberFormat;

/**
  * Skicard is the super class for the different types of skicards. 
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

public abstract class Skicard implements Serializable 
{
// CONSTANTS
	public final static int DAYCARD = 0;
	public final static int HOURCARD = 1;
	public final static int SEASONCARD = 2;
	public final static int PUNCHCARD = 3; 
	private final int CHILD = 1;
	private final int ADULT = 2;
// END CONSTANTS


	private double price;
	private double discount;
	private int ageGroup;
	private int type;
	protected Date bought; 


/**
  * Creates the Skicard
  *
  * @param p 	price
  * @param bd 	determines whether the holder is CHILD or ADULT
  * @param b 	the bought date
  * @param t 	the Cards type
  */

	public Skicard(int p, Date bd, Date b, int t )
	{

		price = p;
		discount = 1.0;
		bought = b; 
		type = t; 

		Calendar cal = Calendar.getInstance();
 		Calendar cal2 = Calendar.getInstance();
 		cal2.setTime( new Date() );

		if (bd != null)
		{		
			cal.setTime( bd );

		
			if( (cal2.get(Calendar.YEAR ) - cal.get(Calendar.YEAR )) <= Info.CHILDLIMIT )
			{
				ageGroup = CHILD; 
				discount = Info.DISCOUNT;
			}
			else
				ageGroup = ADULT;
		}	
		else 
			ageGroup = ADULT;

		price = price * discount;

	}

/**
  * Gets the price
  *
  * @return returns the price
  */

	public double getPrice()
	{
		return price;
	}

/** 
  * Sets the price
  *
  * @param p 	price
  */

	public void setPrice( double p )
	{
		price = p;
	}

/** 
  * Gets any disconunt
  *	
  * @return returns any discount
  */

	public double getDiscount()
	{
		return discount;
	}

/** 
  * Sets the discount
  *
  * @param d 	discount
  */

	public void setDiscount(double d)
	{
		discount = d;
	}


/** 
  * Gets the agegroup (child/adult)
  *
  * @return returns agegroup
  */

	public int getAgeGroup()
	{
		return ageGroup;
	}

/** 
  * Gets Cards type
  *	
  * @return returns Cards type
  */

	public int getType()
	{
		return type; 
	}


/** 
  * Creates a String for the Skicard, based on type.
  *	
  * @param t 	parameter just so we're able to overload this method
  * @return 	returns a String for the Skicard, based on type.
  */

	public String getType( String t )
	{


		switch( type )
		{
			case Skicard.DAYCARD: 
				return "Dagskort";
			case Skicard.HOURCARD: 
				return "Timeskort";
			case Skicard.SEASONCARD:
				return "Sesongkort";
			case Skicard.PUNCHCARD:
				return "Klippekort";
		}
		return ""; 
	}


/** 
  * Gets the date card was bought
  *	
  * @return returns Date object of when card was bought
  */

	public Date getBought()
	{
		return bought;
	}

/** 
  * Abstract method that is used in this class' subclasses.
  *	This method gives any card a starting value (time or number of clips)
  */

	abstract public void initialized();
  
/** 
  * This is Skicards toString.
  *	It contains price, discount and agegroup. Other relevant information is in subclasses' toString.
  */
	public String toString()
	{
		

		int disc = (int)(100 - (discount * 100));
		String age = null;

		if(ageGroup == CHILD)
			age = "Barn";
		if(ageGroup == ADULT)
			age = "Voksen";

		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String formatprice = formatter.format(price);


		StringBuilder text = new StringBuilder();
		//text.append("Pris: ");
		text.append(formatprice);
		//text.append(",-");
		text.append("\t");
		text.append("Rabatt: ");
		text.append("" + disc);
		text.append("%");
		text.append( "\t");
		text.append( "Aldersgruppe: ");
		text.append(age);
		


		String doneText = text.toString();

		return doneText;
	}
	 
}// end of class Skicard