import java.util.Date;
import java.util.Calendar;
import java.io.Serializable;

public class Validations implements Serializable
{
	private int liftId;
	private Card card;
	private Date date; 

	public Validations( int l, Card c, Date d )
	{
		liftId = l; 
		card = c;
		if( d == null )
			date = new Date(); 
		else
			date = d; 
		
		Skiresort.unsaved = true; 
	}

	public int getLiftId()
	{
		return liftId;
	}

	public Card getCard()
	{
		return card;
	}

	public Date getDate()
	{
		return date;
	}

	public String toString()
	{
		StringBuilder text = new StringBuilder();

		Calendar helper = Calendar.getInstance();

		helper.setTime( date );


		text.append( "Heis nr: " + liftId );
		text.append( "\n" );
		text.append( card.toString() );
		text.append( "\n" );
		text.append( "Korttype: " + card.getCurrent().getType() );
		text.append( "\n" );
		text.append( "Passert: " + helper.get(Calendar.DAY_OF_MONTH ));
		text.append( "." + helper.get(Calendar.MONTH));
		text.append( "." + helper.get(Calendar.YEAR ));
		text.append( " " + helper.get(Calendar.HOUR_OF_DAY ));
		text.append( "." + helper.get(Calendar.MINUTE ) );
		text.append( "\n\n\n");

		return text.toString();
	}
}