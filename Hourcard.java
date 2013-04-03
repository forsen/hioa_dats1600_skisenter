public class Hourcard extends Timebasedcard
{
	Date bought;

	public Hourcard(int p, int d, String ag, Date newBought, boolean v)
	{
		super(p, d, ag, v); 
		bought = newBought;
	}

	public void firstTimeUsed()
	{
		Date used = new Date();

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(used);

		int hour1 = cal1.get(Calendar.HOUR);

		bought = used;
	}

	public boolean isValid()
	{
		Date checktoday = new Date();

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(checktoday);
		
    	int hour2 = cal2.get(Calendar.HOUR);

		if(hour2 > (hour1 + 1))
		{
			unvalidate();
			return false;
		}
		return true;

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

	/*<konstruktør som gir startverdier>

	<metode(r) for å sjekke gyldighet (evt datometoder for å holde styr på tider)>

	<getmetode for heiskortnr>*/

	/*<metode for å sette ugyldig>*/

	/*<metode for å legge til mer tid>*/


}// end of class Hourcard