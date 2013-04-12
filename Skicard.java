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

	private final int CHILDLIMIT = 16;

	private int price;
	private double discount;
	private int ageGroup;
	private String type;
	protected Date bought; 


	public Skicard(int p, Date bd, Date b, String t )
	{

		price = p;
		discount = 1;
		bought = b; 
		type = t; 

		Calendar cal = Calendar.getInstance();
 		Calendar cal2 = Calendar.getInstance();
 		cal2.setTime( new Date() );

		cal.setTime( bd );

		if( (cal2.get(Calendar.YEAR ) - cal.get(Calendar.YEAR )) <= CHILDLIMIT )
		{
			ageGroup = CHILD; 
			discount = 0.5;
		}
		else
			ageGroup = ADULT;

		price = (int) (price * discount);
	}
/*
	public int getCardNr()
	{
		return cardNumber;
	}
*/
	public int getPrice()
	{
		return price;
	}

	public double getDiscount()
	{
		return discount;
	}

	public int getAgeGroup()
	{
		return ageGroup;
	}

	public String getType()
	{
		return type; 
	}

  

	public String toString()
	{
		StringBuilder text = new StringBuilder();

		//text.append("Card ID: " + cardNumber + "\n");
		text.append("Price: " + price + "\n");
		text.append("Discount: " + discount + "\n");
		text.append(ageGroup + "\n");

		String doneText = text.toString();

		return doneText;
	}
	 
}