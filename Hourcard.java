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

	/* This method is made for future versions, when it may become necessary to unvalidate a card.
	 *
	 *	public void unvalidate()
	 *	{
	 *		calHelper.setTime( new Date() );
	 *		expires = calHelper.getTime();
	 *	}  
	 **/

	public String toString()
	{
		
		String expires = null;

		if(super.getExpires() == null)
			expires = "ikke Validert";
		if (super.getExpires() != null)
		{
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(super.getExpires());
			expires = ( "" + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH ) + 1) + "." + cal.get(Calendar.YEAR ) 
				+ " kl " + cal.get(Calendar.HOUR_OF_DAY) + ":" +  cal.get(Calendar.MINUTE));
		}

		return  "Timeskort\tGår ut: " + expires + "\t"+super.toString();
	}

}// end of class Hourcard