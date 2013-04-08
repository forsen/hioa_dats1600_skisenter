import java.io.*;
import java.util.*;

//Superclass for all the different cardtypes 

public abstract class Skicard implements Serializable 
{
	private int cardNumber;
	private static int next = 1;
	private int price;
	private int discount;
	private String ageGroup;
	private Date bought; 


	public Skicard(int p, int d, String ag, Date b )
	{
		cardNumber = next++;
		price = p;
		discount = d;
		ageGroup = ag;
		bought = b; 
	}

	public int getCardNr()
	{
		return cardNumber;
	}

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

	public abstract boolean isValid();

	public static int readNext()
	{
		return next;
	}

	public static void setNext( int n )
	{
		next = n;
	}

	public String toString()
	{
		StringBuilder text = new StringBuilder();

		text.append("Card ID: " + cardNumber + "\n");
		text.append("Price: " + price + "\n");
		text.append("Discount: " + discount + "\n");
		text.append(ageGroup + "\n");

		String doneText = text.toString();

		return doneText;
	}
	 
}