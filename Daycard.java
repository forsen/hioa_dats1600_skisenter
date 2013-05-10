import java.util.*;

public class Daycard extends Timebasedcard
{
	Calendar calHelper; 
	

	public Daycard( Date bd, Date b)
	{
		super( Info.DAYCARDPRICE, bd, b, Skicard.DAYCARD); 	
		calHelper = Calendar.getInstance(); 		
	}

		public void initialized()
		{
			setExpires(new Date());


			calHelper.setTime( expires );
			calHelper.set( Calendar.HOUR_OF_DAY, 23 );
			calHelper.set( Calendar.MINUTE, 0 );
			calHelper.set( Calendar.SECOND, 10 );
			setExpires(calHelper.getTime());
		}

	public void unvalidate()
	{
		calHelper.setTime( new Date() );
		expires = calHelper.getTime();
	}

	public String toString()
	{

		String expires = null;

		if(super.getExpires() == null)
			expires = "ikke Validert";
		if (super.getExpires() != null)
		{
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(super.getExpires());
			expires = ( "" + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH ) + 1) + "." + cal.get(Calendar.YEAR ) );
		}

		return "Dagskort\tGår ut: " + expires + "\t" +super.toString();
	}
}