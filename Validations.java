import java.util.Date;

public class Validations
{
	private Card card;
	private Date date; 

	public Validations( Card c )
	{
		card = c;
		date = new Date(); 
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

		text.append( "Kortnr: " + card.toString() );
		text.append( "\n" );
		text.append( "Korttype: " + card.getCurrent().getType() );
		text.append( "\n" );
		text.append( "Passert: " + date.toString() );

		return text.toString();
	}
}