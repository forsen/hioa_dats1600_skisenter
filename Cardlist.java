//a list over all of the skicards a person holds and all cards ever sold
import java.io.*;
import java.util.*;

public class Cardlist implements Serializable
{
	private List<Card> list = new LinkedList<>();
	
	/*<datafelt>

	<konstruktør som oppretter startverdier for datafeltene>
	*/

	public boolean isEmpty()
	{
		return list == null || list.size() == 0;
	}

	public String input(Card obj)
	{
		list.add(obj);
		return obj.getCardID() + " Ble opprettet med kundenummer: ";
		
	}

	public Card deleteCard(Card obj)
	{
		Iterator<Card> it = list.iterator();

		while (it.hasNext())
		{
			if(it.next().equals(obj))
			{
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


}//end of class Skicardlist