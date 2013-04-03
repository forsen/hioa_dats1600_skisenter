import java.io.*;
import java.util.*;

public class Personlist implements Serializable
{
	
	private List<Person> registry = new LinkedList<>();


	public boolean isEmpty()
	{
		return registry == null || registry.size() == 0;
	}

	public String input(Person obj)
	{
		if(isEmpty())
		{
			registry.add(obj);
			return obj.getName() + "Ble opprettet med kunde nr " + obj.getCustId(); 
		}	

		Iterator<Person> it = registry.iterator();

		while(it.hasNext())
		{
			Person p = it.next();

			if( p.equivalent(obj) == true)
			{	
				return "Kunden finnes allerede";
			} 

		} 
		registry.add(obj);
		return obj.getName() + "Ble opprettet med kunde nr " + obj.getCustId(); 
	}

	public void sort()
	{
		Collections.sort(registry, new Sortorder());
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


	public Person findPerson(int nr)
	{
		Iterator<Person> it = registry.iterator();

		while(it.hasNext())
		{
			Person owner = it.next();

			if(owner.getphoneNr()==nr)
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

