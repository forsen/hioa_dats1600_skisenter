import java.util.*;
import javax.swing.DefaultListModel;
import java.text.NumberFormat;
import java.util.Locale;

public class ShoppingCart
{
	private static double sum; 
	private static DefaultListModel<CartItems> items;
	private static List<CartItems> cartList;
	private static DefaultListModel<Card> cardList;

	private static List<Card> newCards; 

	private NumberFormat paymentFormat;

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

		paymentFormat = NumberFormat.getCurrencyInstance( new Locale( "no", "NO" ) );

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


		Card nCard = new Card( new Date() ); 

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

	public List<Card> getNewCards()
	{
		return newCards;
	}

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

	public DefaultListModel<CartItems> deleteFromCart(int index )
	{
		try
		{
			sum -= items.getElementAt( index ).getPrice();
		}
		catch( NullPointerException npe )
		{
			sum -= Info.RETURNPRICE; 
		}
		try
		{
			//sum -= items.getElementAt( index ).getPrice();
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
					System.out.println( "Something went wrong here" );
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

					System.out.println( "Something went wrong here" );
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
				text.append( cIt.next().toString() + "\t\t" + paymentFormat.format( Info.CARDPRICE ) );
				text.append( "\n" );
			}

			text.append( "\n" );
		}


		while( it.hasNext() )
		{
			CartItems ci = it.next();
			text.append( ""+ci.getCardID() );
			text.append( ", ");
			try
			{
				text.append( ci.getType() );
				text.append( "\t" );
				text.append( paymentFormat.format( ci.getPrice() ) );
				text.append( "\n" );
			}
			catch( NullPointerException npe )
			{
				text.append( "\t\t" );
				text.append( paymentFormat.format( Info.RETURNPRICE ) );
				text.append( "\n" );
			}
		}

		
		return text.toString();
	}
}