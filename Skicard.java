import java.io.*;
import java.util.*;

//Superclass for all the different cardtypes 

public abstract class Skicard implements Serializable 
{
	private int cardNumber;
	private static int nesteNr = 1;
	private int pris;
	private int rabatt;
	private String aldersgruppe;



	public Skicard(int p, int r, String ag)
	{
		cardNumber = nesteNr++;
		pris = p;
		rabatt = r;
		aldersgruppe = ag;
	}

	public int getCardNumber()
	{
		return cardNumber;
	}

	public int getPris()
	{
		return pris;
	}

	public int getRabatt()
	{
		return rabatt;
	}

	public abstract boolean isValid();
	 
}