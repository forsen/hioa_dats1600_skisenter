import java.io.*;
import java.util.*;
import javax.swing.DefaultListModel;
import javax.swing.*;



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


	public DefaultListModel<Person> findPerson(int nr)
	{
		DefaultListModel<Person> searchresult = new DefaultListModel<>();  

		Iterator<Person> it = registry.iterator();

		while(it.hasNext())
		{
			Person owner = it.next();

			if(owner.getphoneNr()==nr)
			{
				searchresult.addElement( owner );

			}
		    
		}
		return searchresult;
		
	}

	public Person findPerson( int nr, String fn, String ln )
	{
		Iterator<Person> it = registry.iterator(); 

		while( it.hasNext() )
		{
			Person p = it.next();

			if( p.getFirstName().toLowerCase().matches(fn.toLowerCase()) && p.getLastName().toLowerCase().matches(ln.toLowerCase())
				&& p.getphoneNr() == nr )
				return p;
		}

		return null; 
	}
//STATISTIKK//////////////////////////////////////////////////////////////////////////////////////////

	public List<Person> totalRegPeople( Date s, Date e )
	{

		List<Person> sortit = registry; 

		List<Person> returnList = new LinkedList<>(); 

		Collections.sort(sortit);
		Collections.reverse(sortit);
		Iterator<Person> it = sortit.iterator();

		
		while(it.hasNext())
		{	

			Person p = it.next();



			if( p.getCreated().getTime() > s.getTime() && p.getCreated().getTime() < e.getTime() )
				returnList.add( p );

			if( p.getCreated().getTime() > e.getTime() )
				return returnList;

		}
		return returnList;
	}



	public List<Card> getRelevantCards(Date start, Date end)
	{
		List<Card> tempList = new LinkedList<>();
		Iterator<Person> it = registry.iterator();

		while (it.hasNext())
		{
			Person runner = it.next();

			tempList.addAll(runner.getRelevantCards(start,end));
		}
		
		return tempList;
	}


//END OF STATISTIKK///////////////////////////////////////////////////////
	



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
		
		System.out.println("inne i deletemetoden");
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

	public JTable personTable()
	{
		sort();
		String[] columnName = {"Fornavn", "Etternavn", "Født", "Tlf Nummer", "Opprettet"};
		Object[][] people = new Object[registry.size() ][5]; 
		
		
		Iterator<Person> it = registry.iterator();
		
		for(int i = 0; i < registry.size(); i++)
		{	
			Person runner = it.next();
			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();  
			cal.setTime(runner.getBirth());
			cal2.setTime(runner.getCreated());
			String born = "" + cal.get(Calendar.DAY_OF_MONTH) + "."+ (cal.get(Calendar.MONTH ) + 1) +"."+ cal.get(Calendar.YEAR );
			String created = "" + cal2.get(Calendar.DAY_OF_MONTH) +"."+  (cal2.get(Calendar.MONTH ) + 1) +"."+  cal2.get(Calendar.YEAR );

			
			people[i][0] = runner.getLastName();
			people[i][1] = runner.getFirstName();
			people[i][2] = born;
			people[i][3] = runner.getphoneNr();
			people[i][4] = created;
		
		} 
		JTable perstable = new JTable(people,columnName);
		perstable.setAutoCreateRowSorter(true);
		perstable.setEnabled(false);
		return perstable;
		
	}


}



	/*<datafelter>

	<konstruktør som oppretter listen>

	<metode for å legge personer i listen>

	<metode for å fjerne personer fra listen>

	<metode for å søke personer basert på heiskort>

	<metode for å søke personer på navn>

	<metode for å sjekke om lista er tom>*/

