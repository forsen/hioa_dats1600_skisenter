import java.util.*;

public class Seasoncard extends Timebasedcard
{
	Calendar calHelper; 

	public Seasoncard( Date bd, Date b )
	{
		super(Info.SEASONCARDPRICE, bd, b, "Sesongkort"); 
		bought = new Date();  	
		calHelper = Calendar.getInstance(); 		
	}

	public void initialized()
	{
		setExpires(new Date());


		calHelper.setTime( expires );
		calHelper.set( Calendar.getInstance().get(Calendar.YEAR), 4, 1);
		calHelper.set( Calendar.HOUR_OF_DAY, 0 );
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
		return super.toString()+ "\nSesongkort\nGår ut: " + super.getExpires();
	}
}
