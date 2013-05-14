import java.util.*;
import javax.swing.DefaultListModel;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Date;
import java.util.Calendar;

/**
 * ShoppingCart is a class to keep track of all items staged for purchase. It will create a list of CartItems, and have several methods to operate on 
 * this list. 
 *
 * @author Erik Haider Forsén
 * @author Ole Hansen
 * @author Julie Hill Roa
 * @version 0.9
 * @see SalesWindowPanel
 * @see CashRegister
 */

public class ShoppingCart
{
	private static double sum; 
	private static DefaultListModel<CartItems> items;
	private static List<CartItems> cartList;
	private static DefaultListModel<Card> cardList;

	private static List<Card> newCards; 

	private NumberFormat paymentFormat;

	private Cardlist unregCardList;

/**
  * This constructor creates the various lists needed for this shopping cart. It also receives the cardlist over unregistered cards.
  * @param cl 	The system wide cardlist of unregistered cards.
  */
	public ShoppingCart(Cardlist cl)
	{
		items = new DefaultListModel<>();
		cartList = new LinkedList<>();
		newCards = new LinkedList<>();
		unregCardList = cl;

		cardList = new DefaultListModel<Card>();


		paymentFormat = NumberFormat.getCurrencyInstance( new Locale( "no", "NO" ) );
		paymentFormat.setGroupingUsed( false );

	}

/**
  * A method to decide if a card is already present in the shopping cart. A physical card can only be one item in the list. 
  * @param c 	The card to check if already is present
  * @return Returns true if it's already there, false otherwise
  */
	public boolean exists( Card c )
	{
		Iterator<CartItems> it = cartList.iterator();

		while( it.hasNext() )
		{
			CartItems ci = it.next();

			if( ci.exists(c) )
			{
				return true;
			}
		}

		return false; 
	}

/**
  * A method to set the card list of available cards to add Skicards to. If you're working on a registered customer, this will be 
  * his / hers personal cardlist. 
  * @param c 	A persons cardlist
  */
	public static void setCardList( DefaultListModel<Card> c)
	{
		cardList  = c; 
	}

/**
  * A method to create a new card, and put it in the shoppingcart.
  * @return Returns the listmodel to put on display, with the newly created card added to it. 
  */
	public DefaultListModel<Card> newCard()
	{


		Card nCard = new Card( new Date() ); 

		cardList.addElement( nCard );
		sum += Info.CARDPRICE;
		newCards.add( nCard );


		return cardList;
	}

/**
  * A method to return a list of all new cards in the shoppingcart.
  * @return Returns a list of new cards in the shopping cart.
  */
	public List<Card> getNewCards()
	{
		return newCards;
	}

/**
  * This method is called when adding a combination of card and Skicard to the shoppingcart. It wil create a CartItem and update the sum.
  * @param c 	The Card to add
  * @param sc 	The Skicard associated with the Card, if null this method will assume the incoming Card should be returned.
  * @return Returns a list of all the cartItems currently in the shopping cart.
  */
	public DefaultListModel<CartItems> addToCart( Card c, Skicard sc )
	{

		if( exists( c ))
			return items;

		CartItems ci = new CartItems( c, sc );
		cartList.add( ci );
		items.addElement( ci );
		if( sc == null )
			sum += Info.RETURNPRICE;
		else
			sum += sc.getPrice();
		return items;
	}

/**
  * This method will remove the selected CartItem from the ShoppingCart. 
  * @param index 	The list index of the CartItem to remove
  * @return Returns the updated CartItem list
  */
	public DefaultListModel<CartItems> deleteFromCart(int index )
	{
		try
		{
			sum -= items.getElementAt( index ).getSkiCard().getPrice();
		}
		catch( NullPointerException npe )
		{
			sum -= Info.RETURNPRICE; 
		}
		try
		{
			items.removeElementAt( index );
			cartList.remove( index );
		}
		catch( ArrayIndexOutOfBoundsException aioobe )
		{
			if( items.isEmpty() )
				Salesclerk.statusTxt.setText( "Listen er tom" );
			else
				Salesclerk.statusTxt.setText( "Du må velge en linje fra lista" );
		}

		return items; 
	}

/**
  * A method to return the sum of the shopping cart
  * @return the sum of the shopping cart
  */
	public double getSum()
	{
		return sum; 
	}

/**
  * When this method is called, all items in the shopping cart will be added to the correct lists.
  */
	public void checkOut() 
	{
		Iterator<CartItems> it = cartList.iterator();
		
		while( it.hasNext() )
		{
			it.next().checkOut();
		}

		Iterator<Card> cIt = newCards.iterator();

		while( cIt.hasNext() )
		{
			if(Salesclerk.customer == null)
			{	
				try
				{
					unregCardList.addCard(cIt.next());
				}
				catch( NullPointerException npe )
				{
					npe.printStackTrace( System.out );
				}
			}
			else 
			{
				try
				{
					Salesclerk.customer.addCard( cIt.next() );
				}
				catch( NullPointerException npe )
				{

					npe.printStackTrace( System.out );
				}
			}

		}
	}

/**
  * This method will empty the shopping cart by re-create empty lists that is used by the shopping cart.
  * @return Returns an empty list to put on the display
  */ 
	public static DefaultListModel<CartItems> emptyCart()
	{
		items = new DefaultListModel<>();
		cartList = new LinkedList<>();
		newCards = new LinkedList<>();
		cardList = new DefaultListModel<>(); 
		sum = 0.0; 

		return items;	
	}

/**
  * A method to decide if it's freakyFriday today. It will calculate if today is the last friday of this month.
  * @return Returns true if it's the last friday of this month, false otherwise.
  */
	private boolean freakyFriday()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( new Date() );

		cal.set( Calendar.DAY_OF_MONTH, 1);
		cal.set( Calendar.MONTH, cal.get(Calendar.MONTH) + 1 );

		cal.add( Calendar.DAY_OF_MONTH, -( cal.get( Calendar.DAY_OF_WEEK ) % 7 + 1 ) );

		Calendar calCompare = Calendar.getInstance();
		calCompare.setTime( new Date() );

		if( calCompare.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH ) )
			return true;
		return false;
	}

/**
  * This methods toString. It will compile a String with all cartItems and their price. Used by CashRegister.
  * @return A string with all cartItems and price
  * @see CashRegister
  */
	public String toString()
	{
		StringBuilder text = new StringBuilder();

		Iterator<CartItems> it = cartList.iterator();

		double discount = 1.0;

		if( !newCards.isEmpty() )
		{
			text.append( "Fysiske kort: \n\n" );

			Iterator<Card> cIt = newCards.iterator();

			while( cIt.hasNext() )
			{
				text.append( cIt.next().toString() + "             " + paymentFormat.format( Info.CARDPRICE ) );
				text.append( "\n" );
			}

			text.append( "\n" );
		}


		while( it.hasNext() )
		{
			CartItems ci = it.next();
			text.append( ""+ci.getCard().getCardID() );
			text.append( ", ");
			try
			{	
				if( freakyFriday() )
				{
					ci.getSkiCard().setPrice( ci.getSkiCard().getPrice() * Info.FREAKYFRIDAY );
					discount = Info.FREAKYFRIDAY;
				}
				else if( cartList.size() >= Info.GROUPDISCOUNTLIMIT )
				{
					ci.getSkiCard().setPrice( ci.getSkiCard().getPrice() * Info.GROUPDISCOUNT );
					discount = Info.GROUPDISCOUNT;
				}

				text.append( ci.getSkiCard().getType("") );
				// tried with \t, but it wouldn't align properly (since the cardtype length differs)
				text.append( "        " );
				text.append( paymentFormat.format( ci.getSkiCard().getPrice() ) );
				text.append( "\n" );
			}
			catch( NullPointerException npe )
			{
				// tried with \t, but it wouldn't align properly (since the cardtype length differs)
				text.append( "        " );
				text.append( paymentFormat.format( Info.RETURNPRICE ) );
				text.append( "\n" );
			}
			sum = sum*discount;
		}

		
		
		return text.toString();
	}
}