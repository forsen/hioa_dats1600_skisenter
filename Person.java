import java.io.*;
import java.util.*;


public class Person implements Serializable 
{
	
	private int custId = 0;
	private static int next;
	private String firstname;
	private String lastname;
	private int phoneNr;
	private Date born;
	//BILDEDATAFELT??
	
	private List<Skicard> list;

public Person( String fn, String ln, int p, Date b )
{
	custId = ++next;
	firstname = fn;
	lastname = ln;
	phoneNr = p;
	born = b;
	list  = new LinkedList<>();
}

	public int getCustId()
	{
		return custId;
	}

	public String getFirstName()
	{
		return firstname;
	}

	public String getLastName()
	{
		return lastname;
	}

	public int getphoneNr()
	{
		return phoneNr;
	}

	public Date getBirth()
	{
		return born;
	}

	public boolean hasCard()
	{
		return (list != null) && (!list.isEmpty());
	}

	public void addCard( Skicard c )
	{
		list.add(c);
	}

	public boolean equivalent( Person p )
	{
		return p.getFirstName().equals( firstname ) && p.getLastName().equals( lastname )&& (p.getphoneNr() == ( phoneNr ) ) && (p.getBirth().compareTo( born ) == 0);
	}

	public Person ownsCard(Skicard card)
	{
		Iterator<Skicard> it = list.iterator();

		while(it.hasNext())
		{
			Skicard pass = it.next();

			if(pass.getCardNr()==card.getCardNr() )
			{
				return this;
			}
		} 
		return null; 
	}

	// to be able to preserve next value when saving / loading data file
	public static int readNext()
	{
		return next;
	}

	public static void setNext( int n )
	{
		next = n;
	}

	public String toString()
	{
		StringBuilder text = new StringBuilder();

		text.append(firstname); 
		text.append(" ");
		text.append(lastname);
		text.append("\ntlf: "); 
		text.append(phoneNr); 
		text.append("\nFødt "); 
		text.append(born); 
		text.append("\n"); 

		String doneTekst = text.toString();

		return doneTekst;
	}






	/*<datafelter, inkludert heiskortliste>

	<konstruktør som oppretter personobjektet>

	<metode for å returnere navn>

	<metode for å returnere kundenr>

	<metode for å registrere heiskort til navn>*/

	
}