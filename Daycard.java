import java.util.*;

public class Daycard extends Timebasedcard
{
	Calendar calHelper; 

	public Daycard(int p, int d, String ag, Date b)
	{
		super(p, d, ag, b); 
		bought = new Date();  	
		calHelper = Calendar.getInstance(); 		
	}

		public void initialized()
		{
			setExpires(new Date());


			calHelper.setTime( expires );
			calHelper.set( Calendar.HOUR_OF_DAY, 23 );
			calHelper.set( Calendar.MINUTE, 0 );
			calHelper.set( Calendar.SECOND, 0 );
			setExpires(calHelper.getTime());
		}

	public void unvalidate()
	{
		calHelper.setTime( new Date() );
		expires = calHelper.getTime();
	}

	public String toString()
	{
		return super.toString()+ "\nDagskort";
	}
}