package skisenter;

import javax.swing.DefaultListModel;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;


/**
 * This class creates Person objects. 
 * 
 * @author Erik Haider Forsén
 * @author Ole Hansen
 * @author Julie Hill Roa
 * @version 0.9
 */

public class Person implements Serializable, Comparable<Person>
{
	
	private int custId;
	private static int next = 10000;
	private String firstname;
	private String lastname;
	private int phoneNr;
	private Date born;
	private Date created; 
	private File img;
	private SimpleDateFormat dateFormatter;
	
	protected Cardlist list;

/**
  * This constructor receives all necessary data to create a Person object
  * It will also create a cardlist per object, so each person has its own cardlist.
  * @param fn 	First name
  * @param ln 	Last name
  * @param p 	Phone number
  * @param b 	Date of birth
  * @param i 	Mugshot
  * @see Cardlist
  */
	public Person( String fn, String ln, int p, Date b, File i )
	{
		Skiresort.unsaved = true;
		custId = ++next;
		firstname = fn;
		lastname = ln;
		phoneNr = p;
		born = b;
		list  = new Cardlist();
		created = new Date();
		img = i;

		dateFormatter = new SimpleDateFormat("dd.MM.yy");
	}

/**
  * Method to return customer ID
  * @return Customer ID
  */
	public int getCustId()
	{
		return custId;
	}

/**
  * Method to return this persons First Name
  * @return First Name
  */
	public String getFirstName()
	{
		return firstname;
	}

/**
  * Method to set (update) first name of this person.
  * @param f 	Incoming first name
  * @see CustWindowPanel#updateCust()
  */
	public void setFirstName(String f)
	{
		firstname = f;
	}

/**
  * Method to return this persons Last Name
  * @return Last Name
  */
	public String getLastName()
	{
		return lastname;
	}

/**
  * Method to set (update) last name of this person. 
  * @param l 	Incoming last name
  * @see CustWindowPanel#updateCust()
  */
	public void setLastName(String l)
	{
		lastname = l;
	}

/**
  * Method to return this persons phone number
  * @return Phone Number
  */
	public int getphoneNr()
	{
		return phoneNr;
	}

/**
  * Method to set (update) phone number of this person.
  * @param n 	Incoming phone number
  * @see CustWindowPanel#updateCust()
  */
	public void setphoneNr(int n)
	{
		phoneNr = n;
	}

/**
  * Method to return Date of Birth of this person.
  * @return Date of Birth
  */
	public Date getBirth()
	{
		return born;
	}

/**
  * Method to set (update) Date of Birth of this person.
  * @param b 	Incoming Date object
  * @see CustWindowPanel#updateCust()
  */
	public void setBirth(Date b)
	{
		born = b;
	}

/**
  * Method to set (update) this persons image
  * @param f 	File object to associate with this person
  * @see CustWindowPanel#imageUpload()
  */
	public void setImage(File f)
	{
		img = f;
	}
/**
  * Method to return the image file object associated with this person.
  * @return Returns the file object
  */
	public File getImage()
	{
		return img;
	}

/**
  * Method to return the creation date of this Person object.
  * @return The dateobject representing the date and time this person was created.
  */
	public Date getCreated()
	{
		return created;
	}


/**
  * Method to set the creation date for this person. Necessary to create demo content
  * in this program.
  * @param c 	The date object to set as creation date
  * @see RandomData#addPerson()
  */
	public void setCreated( Date c )
	{
		created = c; 
	}

/**
  * Method to decide if the incoming person object is identical to this one (in reality the same person)
  * @param p 	The person object to compare
  * @return Returns true if the two person objects is in fact the same person, false otherwise.
  */
	public boolean equivalent( Person p )
	{
		return p.getFirstName().equals( firstname ) && p.getLastName().equals( lastname )&& (p.getphoneNr() == ( phoneNr ) ) && (p.getBirth().compareTo( born ) == 0);
	}

/**
  * Method to return a list of all cards associated with this person.
  * @return Returns a list of all (not returned) cards associated with this person.
  * @see Cardlist
  * @see Card
  */
	public DefaultListModel<Card> listCards()
	{
		return list.listCards();
	}

/**
  * Method to add a card to this persons cardlist.
  * @param n 	Card to be added
  * @return Returns the index of the newly added card.
  */
	public int addCard( Card n )
	{
		return list.addCard( n );
	}

/**
  * Removes a card from this persons cardlist
  * @param n 	Card to be removed
  * @see ReplaceWindowPanel
  */
	public void removeCard( Card n )
	{
		list.deleteCard(n);
	}

/**
  * Method to save the next Person number when writing to file, as static variables is not written with the Object. Unless we save this 
  * varible, we'll end up with several Persons with the same ID, as it will start over at the beginning after a restart of the program.
  * @return returns the next available ID for new Persons.
  * @see Skiresort#saveFile()
  */
	public static int readNext()
	{
		return next;
	}
/**
  * Method to write the next Person number when reading from file. 
  *
  * @param n the next available ID for Person
  * @see #readNext()
  * @see Skiresort#readFile()
  */
	public static void setNext( int n )
	{
		next = n;
	}

/**
  * Method to compile a String listing all cards associated with this Person.
  * @return A list with information about all the cards this Person holds.
  * @see Personlist#toString()
  */
	public String seeAllCard()
	{
		return list.toString()+ "\n";
	}

/**
  * Method to search for a card based on its Card Number
  * @param cardNumber 	Card Number to search for
  * @return Returns the card if found, or null otherwise.
  * @see Cardlist#findCard( int cardNumber )
  */
	public Card findCard(int cardNumber)
	{
		return list.findCard(cardNumber);
	}

/**
  * Method to return a list of cards created between the incoming dates.
  * @param start 	The beginning of the interval to look for cards in
  * @param end 		The end of the interval to look for cards in
  * @return Returns a list of all the cards created between the start date and the end date
  * @see Calculator#totalSoldCard( Date start, Date end )
  * @see Calculator#totalRevenue( Date start, Date end )
  */
	public List<Card> getRelevantCards(Date start, Date end)
	{
		return list.getRelevantCards(start,end);
	}

/**
  * This class' toString method. 
  * @return Returns a string containing this persons personalia
  */
	public String toString()
	{
		StringBuilder text = new StringBuilder();

		text.append(firstname); 
		text.append(" ");
		text.append(lastname);
		text.append("\ntlf: "); 
		text.append(phoneNr); 
		text.append("\nFødt: ");
		text.append( dateFormatter.format( born ) );
		text.append("\n"); 
		text.append("Opprettet: ");
		text.append( dateFormatter.format( created ) );
		String doneTekst = text.toString();

		return doneTekst;
	}

	@Override
	public int compareTo( Person p)
	{
		if( this.created.getTime() > p.created.getTime())
		{
			return -1;
		}
		else 
		{
			return 1; 
		}
	}

	
}