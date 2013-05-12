import java.util.Date; 

public abstract class Timebasedcard extends Skicard
{
	//<datafelter>
	protected Date expires; 
	private Date lastValidated;

	public Timebasedcard( int p, Date bd, Date b, int t )
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

	

	abstract public void initialized();	

	/* This method is made for future versions, when it may become necessary to unvalidate a card.
	abstract public void unvalidate(); 	*/
}