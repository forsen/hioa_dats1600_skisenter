import javax.swing.DefaultListModel;
import java.io.*;
import java.util.*;



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
	
	protected Cardlist list;

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
	}

	public int getCustId()
	{
		return custId;
	}

	public String getFirstName()
	{
		return firstname;
	}

	public void setFirstName(String f)
	{
		firstname = f;
	}

	public String getLastName()
	{
		return lastname;
	}

	public void setLastName(String l)
	{
		lastname = l;
	}
	public int getphoneNr()
	{
		return phoneNr;
	}

	public void setphoneNr(int n)
	{
		phoneNr = n;
	}

	public Date getBirth()
	{
		return born;
	}

	public void setBirth(Date b)
	{
		born = b;
	}

	public void setImage(File f)
	{
		img = f;
	}

	public File getImage()
	{
		return img;
	}

	public Date getCreated()
	{
		return created;
	}

	// fjern etterhvert
	public void setCreated( Date c )
	{
		created = c; 
	}
	public boolean hasCard()
	{
		return (list != null) && (!list.isEmpty());
	}

	public boolean equivalent( Person p )
	{
		return p.getFirstName().equals( firstname ) && p.getLastName().equals( lastname )&& (p.getphoneNr() == ( phoneNr ) ) && (p.getBirth().compareTo( born ) == 0);
	}


	// legg til en "add ski card" metode her, som tar imot
	// et skikort, sammen med et kortid, og legger det til riktig 
	// kort. 

	// to be able to preserve next value when saving / loading data file

	public DefaultListModel<Card> listCards()
	{
		return list.listCards();
	}

	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	public int addCard( Card n )
	{
		return list.addCard( n );
	}

	public void removeCard( Card n )
	{
		list.deleteCard(n);
	}

	public static int readNext()
	{
		return next;
	}

	public static void setNext( int n )
	{
		next = n;
	}

	public String seeAllCard()
	{
		return list.toString()+ "\n";
	}

	public Card findCard(int cardNumber)
	{
		return list.findCard(cardNumber);
	}

//STATISTIKK//////////////////////////////////////////////////////////////////////////


	public List<Card> getRelevantCards(Date start, Date end)
	{
		return list.getRelevantCards(start,end);
	}
//END OF STATISTIKK//////////////////////////////////////////////////////////////

	public String toString()
	{
		StringBuilder text = new StringBuilder();

		Calendar cal = Calendar.getInstance(); 
		Calendar cal2 = Calendar.getInstance(); 

		cal.setTime(born);
		cal2.setTime(created);


		text.append(firstname); 
		text.append(" ");
		text.append(lastname);
		text.append("\ntlf: "); 
		text.append(phoneNr); 
		text.append("\nFødt: ");
		text.append( "" + cal.get(Calendar.DAY_OF_MONTH) );
		text.append( "." + (cal.get(Calendar.MONTH ) + 1) );
		text.append( "." + cal.get(Calendar.YEAR ) );
		text.append("\n"); 
		text.append("Opprettet: ");
		text.append( "" + cal2.get(Calendar.DAY_OF_MONTH) );
		text.append( "." + (cal2.get(Calendar.MONTH ) + 1) );
		text.append( "." + cal2.get(Calendar.YEAR ) );
		
		String doneTekst = text.toString();

		return doneTekst;
	}

	@Override
	public int compareTo( Person p)
	{
		if( this.created.getTime() > p.created.getTime())
		{
			System.out.println( " -1 " );
			return -1;
		}
		else 
		{
			System.out.println( "1" );
			return 1; 
		}
	}





	/*<datafelter, inkludert heiskortliste>

	<konstruktør som oppretter personobjektet>

	<metode for å returnere navn>

	<metode for å returnere kundenr>

	<metode for å registrere heiskort til navn>*/

	
}