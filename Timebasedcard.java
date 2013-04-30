import java.util.Date; 

public abstract class Timebasedcard extends Skicard
{
	//<datafelter>
	protected Date expires; 
	private Date lastValidated;

	public Timebasedcard( int p, Date bd, Date b, String t )
	{
		super(p, bd, b, t);

	}

	public void setExpires(Date expires)
	{
		this.expires = expires;
	}

	public Date getExpires()
	{
		return expires;
	}

	public void setLastValidated( Date d )
	{
		lastValidated = d; 
	}

	public Date getLastValidated()
	{
		return lastValidated;
	}

	abstract public void initialized();		/*abstrakt metode for å initializere (gi kortet startttid og slutttid)*/

	abstract public void unvalidate(); 		/*<abstrakt metode for å sette ugyldig>*/
}