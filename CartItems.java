package skisenter;

/**
  * This is a Class to create Objects to have in the ShoppingCart. It makes 
  * an object of a Card object and a Skicard object. 
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  * @see ShoppingCart
  */

public class CartItems
{
	private Card card;
	private Skicard skicard;
	
/**
  * This constructor receivese the object to combine. 
  * @param c 	The Card to put in the ShoppingCart
  * @param sc 	The Skicard to "add" to the Card
  */
	public CartItems( Card c, Skicard sc )
	{
		card = c;
		skicard = sc;
	}

/**
  * Method to see if a given Card is already present in the ShoppingCart.
  * If so, it shouldn't be added again. 
  * @param c 	The card to look for
  * @return Returns a boolean value based on its findings; true if it's already
  * in the cart, false if not.
  * @see ShoppingCart#exists( Card c )
  */
	public boolean exists( Card c )
	{
		return (card.equals(c) );
	}

/**
  * Method to return the card in this object.
  * @return The card
  */
	public Card getCard()
	{
		return card; 
	}
/**
  * Method to return the skicard in this object.
  * @return The skicard
  */
	public Skicard getSkiCard()
	{
		return skicard;
	}

/**
  * Method used when you want to proceed to payment. If the Skicard in this
  * object is null, the card should be returned (set as returned).
  */
	public void checkOut()
	{
		if( skicard == null )
			card.setReturned();
		else
			card.input( skicard );
	}
}