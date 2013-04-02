import java.io.*;


public class Personlist<Person> implements Serializable
{
	
	private List<Person> registry = new LinkedList<>();


	public boolean isEmpty()
	{
		return registry == null || registry.size() == 0;
	}

	public void input(Person obj)
	{
		registry.add(obj);
	}

	

	public Person findPerson(String name)
	{
		Iterator<Person> it = registry.iterator();

		while(it.hasNext())
		{
			Person owner = it.next();

			if(owner.getName().equals(name))
			{
				return owner;
			}
		    
		}
		return null;
		
	}

	public Person deletePerson(Person obj)
	{
		/*if(isEmpty())
			return null;*/

		Iterator<Person> it = registry.iterator();

		
		while(it.hasNext())
		{
			if(it.next().equals(obj) )
			{
				it.remove();
			 	return obj;
			}
		}	
		return null;
	}


	public Person findCardOwner(Skicard card)
	{
		Iterator<Person> it = registry.iterator();

		while(it.hasNext())
		{
			Person owner = it.next();

			if(owner.ownsCard(card) != null)
			{
				return owner;
			}
		    
		}
		return null;

	}
	
	public String toString()
	{
		StringBuilder text = new StringBuilder();
		
		Iterator<Person> it = registry.iterator();
		
		while( it.hasNext())
		{
			Person runner = it.next();
			text.append("\n");
			text.append(runner.toString());
		
		} 
		
		String doneText = text.toString();
		
		return doneText;
	}

}


	




	
	








	/*<datafelter>

	<konstruktør som oppretter listen>

	<metode for å legge personer i listen>

	<metode for å fjerne personer fra listen>

	<metode for å søke personer basert på heiskort>

	<metode for å søke personer på navn>

	<metode for å sjekke om lista er tom>*/

