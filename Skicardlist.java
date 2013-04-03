//a list over all of the skicards a person holds and all cards ever sold
import java.io.*;
import java.util.*;

public class Skicardlist implements Serializable
{
	private List<Skicard> registry = new LinkedList<>();
	
	/*<datafelt>

	<konstruktør som oppretter startverdier for datafeltene>
	*/

	public boolean isEmpty()
	{
		return registry == null || registry.size() == 0;
	}

	public void input(Skicard obj)
	{
		if
		{
			/* Her vi må vi vite om personen er en registrert kunde (har et kundenummer).*/
		}

		registry.add(obj);
		return obj.getCardNr() + " Ble opprettet med kundenummer: ";
		
	}

	public Skicard deleteCard(Skicard obj)
	{
		Iterator<Skicard> it = registry.iterator();

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

	public Skicard findCard(int cardNumber)
	{
		Iterator<Skicard> it = registry.iterator();

		while (it.hasNext())
		{
			Skicard card = it.next();
			if(card.getCardNr() == cardNumber)
			{
				return card;
			}

		}
		return null;
	}

/*	<metode for å finne eier av kort>
	<metode for å sjekke om listen er tom>*/


}//end of class Skicardlist