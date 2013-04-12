import java.util.Date; 

public abstract class Timebasedcard extends Skicard
{
	//<datafelter>
	protected Date expires; 

	public Timebasedcard(int p, int d, String ag, Date b )
	{
		super(p, d, ag, b);

	}

	public void setExpires(Date expires)
	{
		this.expires = expires;
	}

	public Date getExpires()
	{
		return expires;
	}



	abstract public void initialized();		/*abstrakt metode for å initializere (gi kortet startttid og slutttid)*/

	abstract public void unvalidate(); 		/*<abstrakt metode for å sette ugyldig>*/
}