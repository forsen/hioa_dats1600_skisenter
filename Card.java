
import java.io.Serializable;
import java.util.*;

public class Card implements Serializable
{
	private List<Skicard> skiCardList; 
	private int cardNumber;
	private Skicard current; 
	private static int next = 100000;

	public Card()
	{
		skiCardList = new LinkedList<>(); 
		cardNumber = ++next; 
		current = null; 
	}

	public void input( Skicard obj )
	{
		skiCardList.add(obj);
		current = obj; 
	}

	public Skicard getCurrent()
	{
		return current; 
	}

	public int getCardID()
	{
		return cardNumber; 
	}

	public static int readNext()
	{
		return next;
	}

	public static void setNext( int n )
	{
		next = n; 
	}
}