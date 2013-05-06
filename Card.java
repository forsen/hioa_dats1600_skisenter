import java.io.Serializable;
import java.util.*;

public class Card implements Serializable
{
	private List<Skicard> skiCardList; 
	private int cardNumber;
	private Skicard current; 
	private boolean returned;
	private Date bought;  
	private static int next = 100000;

	public Card( Date b )
	{
		Skisenter.unsaved = true; 
		skiCardList = new LinkedList<>(); 
		cardNumber = ++next; 
		current = null; 
		bought = b;
		
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

	public void setReturned()
	{
		returned = true; 
	}

	public boolean getReturned()
	{
		return returned;
	}

	public Date getCardBought()
	{
		return bought;
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
		return total;
	}
//STATISTIKK//////////////////////////////////////////////////////////////////
	public List<Skicard> getRelevantCards(Date start, Date end)
	{
		List<Skicard> tempList = new LinkedList<>();
		Iterator<Skicard> it = skiCardList.iterator();

		while(it.hasNext())
		{
			Skicard runner = it.next();
			if( (runner.getBought().after(start) && runner.getBought().before(end)) 
				|| (runner.getBought().equals(start) || runner.getBought().equals(end)) )
			{
				tempList.add(runner);
			}
		}
		return tempList;

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