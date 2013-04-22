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
		Skisenter.unsaved = true; 

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

	public DefaultListModel<Person> findPerson(String fn, String ln)
	{
		DefaultListModel<Person> searchresult = new DefaultListModel<>();  

		Iterator<Person> it = registry.iterator();

		while(it.hasNext())
		{
			Person owner = it.next();

			if(!fn.isEmpty() && !ln.isEmpty() )
			{
				if (owner.getFirstName().toLowerCase().matches((".*" + fn + ".*" ).toLowerCase() ) && 
					owner.getLastName().toLowerCase().matches((".*" + ln + ".*" ).toLowerCase() ) )
				{
					searchresult.addElement( owner );
				}
			}
			else if(!fn.isEmpty() && owner.getFirstName().toLowerCase().matches((".*" + fn + ".*" ).toLowerCase() ))
			{
				searchresult.addElement( owner );
			}

			else if(!ln.isEmpty() && owner.getLastName().toLowerCase().matches((".*" + ln + ".*" ).toLowerCase() ))
			{
				searchresult.addElement( owner );
			}
		    
		}
		return searchresult;
	}

	public int totalRegPepole()
	{
		Iterator<Person> it = registry.iterator();
		int antall=0;;
		
		while(it.hasNext())
		{	
			Person p = it.next();
			antall++;

		}
		return antall;
	}

	public int regThatTime(int nr)
	{
		Calendar helper = Calendar.getInstance();
		Iterator<Person> it = registry.iterator();
		int antall=0;;
		
		while(it.hasNext())
		{	
			Person p = it.next();
			helper.setTime( p.getCreated());
			
			if(helper.get(Calendar.MONTH ) == nr)
			{
				antall++;
			}
			
		}
		return antall;
	}

	public int totalCost()
	{
		Iterator<Person> it = registry.iterator();

		int total = 0;

		while (it.hasNext())
		{
			Person runner = it.next();

			total += runner.totalCost();
		}
		return total;
	}

	/*public int totalPunch()
	{
		Iterator<Person> it = registry.iterator();

		int totalpunch = 0;

		while (it.hasNext())
		{
			Person runner = it.next();

			totalpunch += runner.totalPunch();
		}
		return totalpunch;
	}*/

	public int soldCards()
	{
		
		Iterator<Person> it = registry.iterator();

		int sold = 0;

		while(it.hasNext())
		{
			Person owner = it.next();

			sold += owner.soldCards();
		}
		return sold;
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

	public Card findCard( int nr )
	{
		Iterator<Person> it = registry.iterator();

		while(it.hasNext())
		{
			Person p = it.next();

			if( p.findCard( nr ) != null )
				return p.findCard( nr );
		}

		return null; 
	}


	public Person deletePerson(Person obj)
	{
		/*if(isEmpty())
			return null;*/

		Skisenter.unsaved = true; 

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


	public Person findPersonByCard(int c)
	{
		Iterator<Person> it = registry.iterator();

		while(it.hasNext())
		{
			Person p = it.next();

			if( p.findCard( c ) != null )
			{
				return p;
			} 
		}
		return null;
	}

	
	
	

	public String toString()
	{
		StringBuilder text = new StringBuilder();
		
		Iterator<Person> it = registry.iterator();
		
		
		while(it.hasNext())
		{
			Person runner = it.next();
			text.append(runner.toString());
			text.append("\n");
			text.append(runner.seeAllCard());
			text.append("---------------------");
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

