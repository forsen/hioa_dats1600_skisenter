import java.io.*;
import java.util.*;

//Superclass for all the different cardtypes 

public abstract class Skicard implements Serializable 
{
	public final static int DAYCARD = 0;
	public final static int HOURCARD = 1;
	public final static int SEASONCARD = 2;
	public final static int PUNCHCARD = 3; 

	private int price;
	private int discount;
	private String ageGroup;
	protected Date bought; 


	public Skicard(int p, int d, String ag, Date b )
	{

		price = p;
		discount = d;
		ageGroup = ag;
		bought = b; 
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

	public int getDiscount()
	{
		return discount;
	}

	public String getAgeGroup()
	{
		return ageGroup;
	}

	/* public abstract boolean isValid();*/



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