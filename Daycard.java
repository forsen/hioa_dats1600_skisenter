import java.util.Date;

public class Daycard extends Skicard
{
	Date date1 = new Date();

	public Daycard(int cc, int p, int d, String ag, Date dat)
	{
		super(p, d, ag); 
		date1 = dat;
	}

	public boolean isValid()
	{
		Date date2 = new Date();

		if(date1.equals(date2))
		{
			return true;
		}
		else return false;
	}

	/*<metode(r) for å sjekke gyldighet (evt datometoder for å holde styr på tider)>

	<getmetode for heiskortnr>

	<metode for å sette ugyldig>

	<metode for å legge til mere tid>*/

}