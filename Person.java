import java.io.*;
import java.util.*;


public class Person implements Serializable 
{
	
	private int custId = 0;
	private static int next;
	private String name;
	private int phoneNr;
	private Date born;
	//BILDEDATAFELT??
	
	private List<Skicard> list;

public Person( String n, int p, Date b )
{
	custId = ++next;
	name = n;
	phoneNr = p;
	born = b;
	list  = new LinkedList<>();
}

	public int getCustId()
	{
		return custId;
	}

	public String getName()
	{
		return name;
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
		return sant = p.getName().equals( name ) && (p.getphoneNr() == ( phoneNr ) ) && (p.getBirth().compareTo( born ) == 0);
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



	public String toString()
	{
		StringBuilder text = new StringBuilder();

		text.append(name); 
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