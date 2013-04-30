 //a list over all of the skicards a person holds and all cards ever sold
import java.io.*;
import java.util.*;
import javax.swing.DefaultListModel;
import java.util.Date;
import java.util.Calendar;

public class Cardlist implements Serializable
{
	private List<Card> list = new LinkedList<>();


	public boolean isEmpty()
	{
		return list == null || list.size() == 0;
	}

	public String input(Card obj)
	{
		Skisenter.unsaved = true; 
		list.add(obj);
		return  " Ble opprettet med kundenummer: " + obj.getCardID();	
	}

	public Card deleteCard(Card obj)
	{

		Iterator<Card> it = list.iterator();

		while (it.hasNext())
		{
			if(it.next().equals(obj))
			{
				Skisenter.unsaved = true;
				it.remove();
				return obj;
			}
		}
		return null;
	}

	public Card findCard(int cardNumber)
	{
		Iterator<Card> it = list.iterator();

		while (it.hasNext())
		{
			Card card = it.next();
			if(card.getCardID() == cardNumber)
			{
				return card;
			}

		}
		return null;
	}

	public int addCard( Card n )
	{
		Skisenter.unsaved = true; 
		list.add( n );
		return n.getCardID();
	}

	public DefaultListModel<Card> listCards()
	{
		DefaultListModel<Card> searchresult = new DefaultListModel<>();  

		Iterator<Card> it = list.iterator();

		while(it.hasNext())
		{
			Card card = it.next();
			if( !card.getReturned() )
				searchresult.addElement( card );
		}

		return searchresult;
	}

	public boolean ownsCard(Card card)
	{
		Iterator<Card> it = list.iterator();

		while(it.hasNext())
		{
			Card pass = it.next();

			if(pass.getCardID()==card.getCardID() )
			{
				return true;
			}
		} 
		return false; 
	}

/*	<metode for å finne eier av kort>*/

	public int totalCost()
	{
		Iterator<Card> it = list.iterator();

		int total = 0;
		int cardNR = 0;

		while (it.hasNext())
		{
			Card runner = it.next();

			total += runner.totalCost();
			total+=Info.CARDPRICE;
			
			
		}
		return total;
	}

/*	public int totalPunch()
	{
		Iterator<Card> it = list.iterator();

		int totalpunch = 0;

		while (it.hasNext())
		{
			Card runner = it.next();
	
			totalpunch += runner.totalCost();
			
		}
		return totalpunch;
	}*/

	/*public int antSoldCards()
	{
		Iterator<Card> it = list.iterator();

		while (it.hasNext())
		{
			Card runner = it.next();
	
			if(it.next() == null)
				return runner.getHowManySoldCards();
			
		}
		return 0;
	}*/
/// STATISTIKK///////////////////////////////////////////////////////////////////
	public List<Card> getRelevantCards(Date start, Date end)
	{
		List<Card> tempList = new LinkedList<>();
		Iterator<Card> it = list.iterator();

		while(it.hasNext())
		{
			Card runner = it.next();
			if( (runner.getBought().after(start) && runner.getBought().before(end)) 
				|| (runner.getBought().equals(start) || runner.getBought().equals(end)) )
			{
				tempList.add(runner);
			}
		}
		return tempList;
	}

	public int allCards()
	{
		Iterator<Card> it = list.iterator();

		int total = 0;

		while (it.hasNext())
		{
			Card runner = it.next();
	
			total ++;
			
		}
		return total;
	}

	public int unregCardsSoldInMonthX(int x)
	{

		Iterator<Card> it = list.iterator();

		int total = 0;

		Calendar helper = Calendar.getInstance();

		while (it.hasNext())
		{

			Card runner = it.next();
			helper.setTime( runner.getBought() );
			
			if( helper.get(Calendar.MONTH )== x)
			total ++;
			
		}
		return total;
	}
//END OF STATISTIKK//////////////////////////////////////////////////////////////////////
	public String toString()
	{
		StringBuilder text = new StringBuilder();
		
		Iterator<Card> it = list.iterator();
		
		
		while(it.hasNext())
		{
			Card runner = it.next();
			if( !runner.getReturned() )
			{
				text.append(runner.toString());
				text.append(runner.history());
				text.append("\n");
			}
		
		} 
		
		String doneText = text.toString();
		
		return doneText;
	}

}//end of class Skicardlist