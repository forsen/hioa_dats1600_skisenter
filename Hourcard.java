import java.util.*;

public class Hourcard extends Timebasedcard
{
	Calendar calHelper; 
	String t = "Timeskort";


	public Hourcard(int p, int d, String ag, Date b )
	{
		super(p, d, ag, b, t); 
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

	public String toString()
	{
		return super.toString()+ "\nTimeskort";
	}

}// end of class Hourcard