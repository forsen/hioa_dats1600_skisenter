import java.util.*;
import javax.swing.DefaultListModel;

public class ShoppingCart
{
	private static double sum; 
	private static DefaultListModel<CartItems> items;
	private static List<CartItems> cartList;
	private static DefaultListModel<Card> cardList;

	private static List<Card> newCards; 

	private Cardlist unregCardList;


	public ShoppingCart(Cardlist cl)
	{
		items = new DefaultListModel<>();
		cartList = new LinkedList<>();
		newCards = new LinkedList<>();
		unregCardList = cl;

//		try
//		{
//			cardList = Salesclerk.customer.listCards();
//		}
//		catch( NullPointerException npe )
//		{
		cardList = new DefaultListModel<Card>();
//		}


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

	public static void setCardList( DefaultListModel<Card> c)
	{
		cardList  = c; 
	}

	public DefaultListModel<Card> newCard()
	{


		Card nCard = new Card(); 

//		try
//		{
//			cardList = Salesclerk.customer.listCards();
//		}
//		catch( NullPointerException npe )
//		{
			// do nothing
//		}

		cardList.addElement( nCard );
		//System.out.println( "dette er balloks" );
		sum += 70;
		newCards.add( nCard );


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
			if(Salesclerk.customer == null)
			{	
				try
				{
					unregCardList.input(cIt.next());
				}
				catch( NullPointerException npe )
				{
					System.out.println( "Noe gikk galt" );
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

				
				}
			}

		}
	}

	public static DefaultListModel<CartItems> emptyCart()
	{
		items = new DefaultListModel<>();
		cartList = new LinkedList<>();
		newCards = new LinkedList<>();
		cardList = new DefaultListModel<>(); 
		sum = 0; 

		return items;	
	}

	public String toString()
	{
		StringBuilder text = new StringBuilder();

		Iterator<CartItems> it = cartList.iterator();


		if( !newCards.isEmpty() )
		{
			text.append( "Fysiske kort: \n\n" );

			Iterator<Card> cIt = newCards.iterator();

			while( cIt.hasNext() )
			{
				text.append( cIt.next().toString() + "\t\t70,-" );
				text.append( "\n" );
			}

			text.append( "\n" );
		}


		while( it.hasNext() )
		{
			CartItems ci = it.next();
			text.append( ""+ci.getCardID() );
			text.append( ", ");
			text.append( ci.getType() );
			text.append( "\t" );
			text.append( ci.getPrice() + ",-" );
			text.append( "\n" );
		}

		
		return text.toString();
	}
}