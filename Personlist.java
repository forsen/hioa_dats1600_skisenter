import java.io.*;
import java.util.*;
import javax.swing.DefaultListModel;
import javax.swing.*;
import java.text.SimpleDateFormat;

/**
 * Personlist creats a LinkedList of PersonObjects. 
 * 
 * @author Erik Haider Forsén
 * @author Ole Hansen
 * @author Julie Hill Roa
 * @version 0.9
 */

public class Personlist implements Serializable
{
	
	private List<Person> registry = new LinkedList<>();


/** 
 *This methode checks if the list is empty (No elements in the list)
 */
	public boolean isEmpty()
	{
		return registry == null || registry.size() == 0;
	}

/** 
 *This methode adds an element to the list.
 * First it checks if the person excist already, if it doesn't the element will be added.
 * @param obj The Person object you want to add to the list.
 * @return Returns a String to let the user know if it was sucsessfully added of if the object alredy excisted.
 */
	public String input(Person obj)
	{
		Skiresort.unsaved = true; 

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

/**
 *This methode sorts the Personlist. 
 * @see Sortorder  
 */
	public void sort()
	{
		Collections.sort(registry, new Sortorder());
	}

/**
 *This methode search for an Peronobject with the firstname or Lastname containing the String paramaters
 *All of these Persons is added to an DefaultListModel of persons 
 * @param fn is a String with a firstname or a bit of the firstname
 * @param ln is a String with a lastname or a bit of the lastname 
 * @return Returns the DefaultListModel of persons
 */

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

/**
 *This methode search for an Peronobject with the phonenumber equal the int paramater
 *All of these Persons is added to an DefaultListModel of persons 
 * 
 * @param nr is a int with a phonenumber
 * @return Returns the DefaultListModel of persons with that phonenumber
 */

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

/**
 *This methode search for an Peronobject with the phonenumber equal the int nr paramater,
 *firstname is equl to the String fn parameter and lastname equal to the String ln.
 * 
 * @param nr is a int with a phonenumber
 * @param fn is a String with a firstname
 * @param ln is a String with a lastname
 * @return Returns the Person that matches all the parameters
 */

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

/**
 *This methode adds all the Persons registered between the start and the end parameters
 *All of these Persons is added to an list of persons 
 * 
 * @param start the start of the periode
 * @param end the end of the periode
 * @return Returns the list of persons registered in that periode
 */

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

/**
 * creates a list of cards, bought between the start and end parameters 
 * all of these cards is added to a list of cards
 * @param start the start of the periode 
 * @param end the end of the periode
 * @return returns the list of cards bought in that periode
 */

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
	
/**
 * goes throu a persons card to see if he has a specific card
 * @param obj the cardnumber you want to find
 * @return returns the card 
 */


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
/**
 * The methode deletes the person from the list.
 * @param obj the person you want to delete for the list
 * @return returns the deleted person
 */

	public Person deletePerson(Person obj)
	{
		
		Skiresort.unsaved = true; 

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

/**
 * Searching throu the list for a Person with a card with the cardnumber egual to the parameter.
 * @param c the cardnumber you want to fins the owner to
 * @return returns the owner of the card  
 */
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

/**
 * This class' toString method. 
 * @return Returns a string containing all the persons personalia in this list and the cards these persons has.
 */
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

/**
  * This class makes a JTable of all the persons in this list. 
  * @return Returns a JTable containing these persons personalia
  */

	public JTable personTable()
	{
		sort();
		String[] columnName = {"Fornavn", "Etternavn", "Født", "Tlf Nummer", "Opprettet"};
		Object[][] people = new Object[registry.size() ][5]; 
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
		
		Iterator<Person> it = registry.iterator();
		
		for(int i = 0; i < registry.size(); i++)
		{	
			Person runner = it.next(); 
			
			
			
			people[i][0] = runner.getLastName();
			people[i][1] = runner.getFirstName();
			people[i][2] = sdf.format(runner.getBirth());
			people[i][3] = runner.getphoneNr();
			people[i][4] = sdf.format(runner.getCreated());
		
		} 
		JTable perstable = new JTable(people,columnName);
		perstable.setAutoCreateRowSorter(true);
		perstable.setEnabled(false);
		return perstable;
		
	}


}



