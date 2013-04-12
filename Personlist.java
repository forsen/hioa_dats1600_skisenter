import java.io.*;
import java.util.*;
import javax.swing.DefaultListModel;

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
			return obj.getFirstName() + " " + obj.getLastName()+ " Ble opprettet med kunde nr " + obj.getCustId(); 
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
		return obj.getFirstName() + " " + obj.getLastName()+ " Ble opprettet med kunde nr " + obj.getCustId(); 
	}

	public void sort()
	{
		Collections.sort(registry, new Sortorder());
	}

	
/*
	public String findPerson(String fn, String ln)
	{
		Iterator<Person> it = registry.iterator();

		String result = ""; 

		while(it.hasNext())
		{
			Person owner = it.next();

			if(fn != null && owner.getFirstName().toLowerCase().matches(".*"+fn.toLowerCase()+".*"))
			{
				result += owner.toString();
			}

			if(ln != null && owner.getLastName().toLowerCase().matches(".*"+ln.toLowerCase()+".*"))
			{
				result += owner.toString();
			}
		    
		}
		return result;
	}
*/

	// tester en ny måte å representere resultatet på. 

	public DefaultListModel findPerson(String fn, String ln)
	{
		DefaultListModel<Person> searchresult = new DefaultListModel<>();  

		Iterator<Person> it = registry.iterator();

		while(it.hasNext())
		{
			Person owner = it.next();

			if(!fn.isEmpty() && owner.getFirstName().toLowerCase().matches((".*" + fn + ".*" ).toLowerCase() ))
			{
				searchresult.addElement( owner );
			}

			if(!ln.isEmpty() && owner.getLastName().toLowerCase().matches((".*" + ln + ".*" ).toLowerCase() ))
			{
				searchresult.addElement( owner );
			}
		    
		}
		return searchresult;
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

/*
	public Person findPerson(Card c)
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
*/	
	

	public String toString()
	{
		StringBuilder text = new StringBuilder();
		
		Iterator<Person> it = registry.iterator();
		
		
		while(it.hasNext())
		{
			Person runner = it.next();
			text.append(runner.toString());
			text.append("\nHeiheiPersonlist");
			text.append(runner.seeAllCard());
			text.append("\n");


		
		} 
		
		String doneText = text.toString();
		
		return doneText;
	}

	public String personListe()
	{
		StringBuilder text = new StringBuilder();
		
		Iterator<Person> it = registry.iterator();
		
		
		while(it.hasNext())
		{
			Person runner = it.next();
			text.append(runner.toString());
			text.append("\n");
			


		
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

