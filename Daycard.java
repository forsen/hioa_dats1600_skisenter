import java.util.*;

public class Daycard extends Skicard
{
	private boolean valid = true;
	private Date bought;
	

	public Daycard(int cc, int p, int d, String ag, Date newBought, boolean v)
	{
		super(p, d, ag); 
		valid = v;
		bought = newBought;
		
	}

	public void setDateBought()
	{
		Calendar calendar = Calendar.getInstance();
		Date bought = calendar.getTime();
	}

	public boolean isValid()
	{
		Calendar checktoday = Calendar.getInstance();

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
		bought.add(Calendar.DAY_OF_YEAR, 1); /* Dette blir feil. */
	}
	/*<metode(r) for å sjekke gyldighet (evt datometoder for å holde styr på tider)>

	<getmetode for heiskortnr>

	<metode for å sette ugyldig>

	<metode for å legge til mere tid>*/

}