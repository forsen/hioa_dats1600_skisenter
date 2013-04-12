import java.util.*;

public class Seasoncard extends Timebasedcard
{
	Calendar calHelper; 

	public Seasoncard(int p, int d, String ag, Date b )
	{
		super(p, d, ag, b); 
		bought = new Date();  	
		calHelper = Calendar.getInstance(); 		
	}

	public void initialized()
	{
		setExpires(new Date());


		calHelper.setTime( expires );
		calHelper.set(Calendar.MONTH, 6);
		setExpires(calHelper.getTime());

	}

	public void unvalidate()
	{
		calHelper.setTime( new Date() );
		expires = calHelper.getTime();
	}

	public String toString()
	{
		return super.toString()+ "\nSesongkort";
	}
}
