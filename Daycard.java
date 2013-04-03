import java.util.*;

public class Daycard extends Timebasedcard
{
	private Date bought;
	

	public Daycard(int p, int d, String ag, Date newBought, boolean v)
	{
		super(p, d, ag, v); 
		bought = newBought;
		
	}

	public void firstTimeUsed()
	{
		Date used = new Date();
		bought = used;
	}

	public boolean isValid()
	{
		Date checktoday = new Date();

		if(bought.equals(checktoday))
		{
			return true;
		}
		else
		unvalidate();
		return false;
	}

	public void unvalidate()
	{
		valid = false;
	}

	public void addTime()
	{
		if(isValid())
		{
			Date dateAfterFill = new Date(bought.getTime() + 1000 * 60 * 60 * 24);
			bought = dateAfterFill;
		}
	}
	/*<metode(r) for å sjekke gyldighet (evt datometoder for å holde styr på tider)>

	<getmetode for heiskortnr>

	<metode for å sette ugyldig>

	<metode for å legge til mere tid>*/

}