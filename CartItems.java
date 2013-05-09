public class CartItems
{
	private Card card;
	private Skicard skicard;
	
	public CartItems( Card c, Skicard sc )
	{
		card = c;
		skicard = sc;
	}

	public boolean exists( Card c )
	{
		return (card.equals(c) );
	}

	public String getType()
	{
		return skicard.getType("");
	}

	public double getPrice()
	{
		return skicard.getPrice();
	}

	public int getCardID()
	{
		return card.getCardID();
	}

	public Card getCard()
	{
		return card; 
	}

	public void checkOut()
	{
		if( skicard == null )
			card.setReturned();
		else
			card.input( skicard );
	}
}