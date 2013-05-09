import java.util.*;

public class Hourcard extends Timebasedcard
{
	Calendar calHelper; 
	


	public Hourcard( Date bd, Date b )
	{
		super(Info.HOURCARDPRICE, bd, b, Skicard.HOURCARD); 	
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
		return  "Timeskort\tGår ut: " + super.getExpires()+ "\t"+super.toString();
	}

}// end of class Hourcard