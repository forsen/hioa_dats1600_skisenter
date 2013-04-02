//a list over all of the skicards a person holds and all cards ever sold
public class Skicardlist implements Serializable
{
	private List<Skicard> registry = new LinkedList<>();
	
	/*<datafelt>

	<konstruktør som oppretter startverdier for datafeltene>
	*/

	public void input(Skicard obj)
	{
		registry.add(obj);
	}

	public void deleteCard(Skicard obj)
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
	/*
	<metode for innsetting av kort i listen>
	<metode for fjerning av kort i listen>
	<metode for finne/søke etter kort i listen etter kort nr>
	<metode for å finne eier av kort>
	<metode for å sjekke om listen er tom>*/


}//end of class Skicardlist