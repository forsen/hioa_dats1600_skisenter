import java.util.*;

public class Daycard extends Timebasedcard
{
	Calendar calHelper; 

	public Daycard(int p, int d, String ag )
	{
		super(p, d, ag ); 
		bought = new Date();  	
		calHelper = Calendar.getInstance(); 		
	}

	public void initialized()
	{
		expires = new Date( );


		calHelper.setTime( expires );
		calHelper.set( Calendar.HOUR_OF_DAY, 23 );
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