import java.util.*;

public class Seasoncard extends Timebasedcard
{
	Calendar calHelper, nextSeason; 

	public Seasoncard( Date bd, Date b )
	{
		super(Info.SEASONCARDPRICE, bd, b, Skicard.SEASONCARD);  	
		calHelper = Calendar.getInstance(); 		
	}

	public void initialized()
	{
		setExpires(new Date());
		nextSeason = Calendar.getInstance();


		calHelper.setTime( expires );
		calHelper.set( Calendar.MONTH, Calendar.MAY);
		calHelper.set( Calendar.DAY_OF_MONTH, 1 );
		calHelper.set( Calendar.HOUR_OF_DAY, 0 );
		calHelper.set( Calendar.MINUTE, 0 );
		calHelper.set( Calendar.SECOND, 0 );

		nextSeason = calHelper;
		nextSeason.add(Calendar.YEAR, 1);

		setExpires(calHelper.getTime());

		if(Calendar.getInstance().after(calHelper.getTime()))
		{
			setExpires(nextSeason.getTime());
		}

	}

	public void unvalidate()
	{
		calHelper.setTime( new Date() );
		expires = calHelper.getTime();
	}

	public String toString()
	{
		
		String expires= null;

		if(super.getExpires() == null)
			expires = "ikke Validert";
		if (super.getExpires() != null)
		{
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(super.getExpires());
			expires = ( "" + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH ) + 1) + "." + cal.get(Calendar.YEAR ) );
		}


		return "Sesongkort\tGår ut: " + expires+  "\t" + super.toString();
	}
}
