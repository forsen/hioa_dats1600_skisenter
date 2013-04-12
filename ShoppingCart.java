import java.util.*;
import javax.swing.DefaultListModel;

public class ShoppingCart
{
	private double sum; 
	private DefaultListModel<Skicard> items;
	private List<Card> cardList;

	public ShoppingCart()
	{
		items = new DefaultListModel<>();
		cardList = new LinkedList<>();
	}

	public boolean exists( Card c )
	{
		Iterator<Card> it = cardList.iterator();

		while( it.hasNext() )
		{
			Card card = it.next();

			if( card.equals(c) )
			{
				return true;
			}
		}

		return false; 
	}

	public DefaultListModel addToCart( Card c, Skicard sc )
	{
		if( exists( c ))
			return items;

		cardList.add( c );
		items.addElement( sc );
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
		// do something more
	}
}