
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

	public String input( Skicard obj )
	{
		skiCardList.add(obj);
		current = obj; 
		return obj.toString() + " ble lagg til " + cardNumber;
	}

	public Skicard getCurrent()
	{
		return current; 
	}

	public void setCurrent(Skicard c)
	{
		 current = c; 
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

	public void setSkicardlist(List<Skicard> c)
	{
		skiCardList = c; 
	}

	public List<Skicard> getSkicardlist()
	{
		return skiCardList; 
	}

	//metode for å se hva slags type kort dette kortet tidligere har vært
	public String history()
	{
		StringBuilder text = new StringBuilder();
		
		Iterator<Skicard> it = skiCardList.iterator();
		
		
		while(it.hasNext())
		{
			Skicard runner = it.next();
			text.append("\n");
			text.append(runner.toString());
			text.append("\n");
			



		
		} 
		
		String doneText = text.toString();
		
		return doneText;
	}

	public String toString()
	{
		return "Kortnr: " + cardNumber;
	}
}