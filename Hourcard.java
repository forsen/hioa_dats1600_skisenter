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
		calHelper.add( Calendar.HOUR_OF_DAY, 1 );
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