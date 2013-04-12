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
		return skicard.getType();
	}

	public int getPrice()
	{
		return skicard.getPrice();
	}

	public int getCardID()
	{
		return card.getCardID();
	}

	public void checkOut()
	{
		card.input( skicard );
	}
}