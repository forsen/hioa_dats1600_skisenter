import java.util.*;
import javax.swing.DefaultListModel;

public class ShoppingCart
{
	private double sum; 
	private DefaultListModel<CartItems> items;
	private List<CartItems> cartList;
	private List<Card> newCards; 

	public ShoppingCart()
	{
		items = new DefaultListModel<>();
		cartList = new LinkedList<>();
		newCards = new LinkedList<>();
	}

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


	public DefaultListModel<Card> newCard()
	{
		DefaultListModel<Card> cardList = null;

		Card nCard = new Card(); 
		try
		{
			cardList = Salesclerk.customer.listCards();
			cardList.addElement( nCard );
			sum += 70;
			newCards.add( nCard );
		}
		catch( NullPointerException npe )
		{
			//JOptionPane.showMessageDialog( null, "Du må velge en person først!" );
		}



		return cardList;
	}

	public DefaultListModel<CartItems> addToCart( Card c, Skicard sc )
	{

		if( exists( c ))
			return items;

		CartItems ci = new CartItems( c, sc );
		cartList.add( ci );
		items.addElement( ci );
		sum += sc.getPrice();
		return items;
	}

	public DefaultListModel deleteFromCart(Card c, Skicard sc )
	{
		items.removeElement( sc );
		sum -= sc.getPrice();

		return items; 
	}

	public double getSum()
	{
		return sum; 
	}

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
			try
			{
				Salesclerk.customer.addCard( cIt.next() );
			}
			catch( NullPointerException npe )
			{
				System.out.println( "Du må velge en person" );
			}
		}
	}

	public DefaultListModel<CartItems> emptyCart()
	{
		items = new DefaultListModel<>();
		cartList = new LinkedList<>();
		sum = 0; 

		return items;	
	}

	public String toString()
	{
		StringBuilder text = new StringBuilder();

		Iterator<CartItems> it = cartList.iterator();

		while( it.hasNext() )
		{
			CartItems ci = it.next();
			text.append( ""+ci.getCardID() );
			text.append( ", ");
			text.append( ci.getType() );
			text.append( ", " );
			text.append( ci.getPrice() + "kr" );
			text.append( "\n" );
		}

		text.append( "\n" );
		text.append( "Sum: " + sum + "kr");
		
		return text.toString();
	}
}