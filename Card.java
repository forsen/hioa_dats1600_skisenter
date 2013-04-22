import java.io.Serializable;
import java.util.*;

public class Card implements Serializable
{
	private List<Skicard> skiCardList; 
	private int cardNumber;
	private Skicard current; 
	private static int next = 100000;
	private int soldCards;
	private static int manysold;

	public Card()
	{
		Skisenter.unsaved = true; 
		skiCardList = new LinkedList<>(); 
		cardNumber = ++next; 
		current = null; 
		soldCards = ++manysold;
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
	public int getHowManySoldCards()
	{
		return soldCards;
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

	public Date getBought()
	{
		return current.getBought();
	}

	public int totalCost()
	{
		Iterator<Skicard> it = skiCardList.iterator();
		int total = 0;

		while(it.hasNext())
		{
			Skicard runner = it.next();
			total += runner.getPrice();
		}

		int sold = 70 * getHowManySoldCards();

		total += sold;
		return total;
	}

	/*public int totalPunch()
	{

		Iterator<Skicard> it = skiCardList.iterator();
		int totalpunch = 0;

		while(it.hasNext())
		{
			Skicard runner = it.next();
			if (runner instanceof Punchcard)
			totalpunch += runner.getPrice();
		} 
		return totalpunch;
	}*/

	public String showAll()
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