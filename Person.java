import javax.swing.DefaultListModel;
import java.io.*;
import java.util.*;


public class Person implements Serializable 
{
	
	private int custId;
	private static int next = 10000;
	private String firstname;
	private String lastname;
	private int phoneNr;
	private Date born;
	private Date created; 
	//BILDEDATAFELT??
	
	protected Cardlist list;

	public Person( String fn, String ln, int p, Date b )
	{
		custId = ++next;
		firstname = fn;
		lastname = ln;
		phoneNr = p;
		born = b;
		list  = new Cardlist();
		created = new Date();
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

	public Date getCreated()
	{
		return created;
	}

	public boolean hasCard()
	{
		return (list != null) && (!list.isEmpty());
	}

	public boolean equivalent( Person p )
	{
		return p.getFirstName().equals( firstname ) && p.getLastName().equals( lastname )&& (p.getphoneNr() == ( phoneNr ) ) && (p.getBirth().compareTo( born ) == 0);
	}

	public Person ownsCard(Card card)
	{
		return (list.ownsCard( card ) == true )? this : null ;
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

	public int totalCost()
	{ 
		return list.totalCost();
		
	}

	public String toString()
	{
		StringBuilder text = new StringBuilder();

		Calendar cal = Calendar.getInstance(); 

		cal.setTime(born);

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

		String doneTekst = text.toString();

		return doneTekst;
	}







	/*<datafelter, inkludert heiskortliste>

	<konstruktør som oppretter personobjektet>

	<metode for å returnere navn>

	<metode for å returnere kundenr>

	<metode for å registrere heiskort til navn>*/

	
}