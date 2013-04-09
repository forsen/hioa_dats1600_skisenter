import java.util.*;

public class Hourcard extends Timebasedcard
{
	Calendar calHelper; 


	public Hourcard(int p, int d, String ag, Date b )
	{
		super(p, d, ag, b); 
		bought = new Date();  	
		calHelper = Calendar.getInstance(); 
	}

	public void initialized()
	{
		expires = new Date( );


		calHelper.setTime( expires );
		calHelper.set( Calendar.HOUR_OF_DAY, 1 );
		calHelper.set( Calendar.MINUTE, 0 );
		calHelper.set( Calendar.SECOND, 0 );
		expires = calHelper.getTime(); 

	}

	public void unvalidate()
	{
		calHelper.setTime( new Date() );
		expires = calHelper.getTime();
	}

	public void addTime()
	{
		Date dateAfterFill = (new Date() + 1000 * 60 * 60);
		bought = dateAfterFill;
	}

	/*<konstruktør som gir startverdier>

	<metode(r) for å sjekke gyldighet (evt datometoder for å holde styr på tider)>

	<getmetode for heiskortnr>*/

	/*<metode for å sette ugyldig>*/

	/*<metode for å legge til mer tid>*/


}// end of class Hourcard