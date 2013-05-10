import java.io.*;
import java.util.*;

//Superclass for all the different cardtypes 

public abstract class Skicard implements Serializable 
{
	public final static int DAYCARD = 0;
	public final static int HOURCARD = 1;
	public final static int SEASONCARD = 2;
	public final static int PUNCHCARD = 3; 

	private final int CHILD = 1;
	private final int ADULT = 2;



	private double price;
	private double discount;
	private int ageGroup;
	private int type;
	protected Date bought; 


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


	public double getPrice()
	{
		return price;
	}

	public void setPrice( double p )
	{
		price = p;
	}

	public double getDiscount()
	{
		return discount;
	}

	public int getAgeGroup()
	{
		return ageGroup;
	}

	public int getType()
	{
		return type; 
	}

	public String getType( String t )
	{
		// parameter just so we're able to overload this method

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

	public Date getBought()
	{
		return bought;
	}

	abstract public void initialized();
  

	public String toString()
	{
		StringBuilder text = new StringBuilder();

		//text.append("Card ID: " + cardNumber + "\n");
		text.append("Price: ");
		text.append(price);
		text.append("\t");
		text.append("Discount: ");
		text.append(discount);
		text.append( "\t");
		text.append( "Aldersgruppe: ");
		text.append(ageGroup);
		


		String doneText = text.toString();

		return doneText;
	}
	 
}