import java.util.*;

public class Hourcard extends Timebasedcard
{
	Calendar calHelper; 
	


	public Hourcard( Date bd, Date b )
	{
		super(Info.HOURCARDPRICE, bd, b, "Timeskort"); 
		bought = new Date();  	
		calHelper = Calendar.getInstance(); 
	}

	public void initialized()
	{
		setExpires(new Date());


		calHelper.setTime( expires );
		calHelper.set( Calendar.HOUR_OF_DAY, 1 );
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
		return super.toString()+ "\nTimeskort\nGår ut: " + super.getExpires();
	}

}// end of class Hourcard