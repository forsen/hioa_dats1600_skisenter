public abstract class Lift implements Serializable
{
	private int liftNr;
	private String name;
	private int clips;
	private int length;

	public Lift(int l, String n,int c, int m)
	{
		liftNr = l;
		name = n;
		clips = c;
		length = m;
	}

	public int getLiftNr()
	{
		return liftNr;
	}

	public String getName()
	{
		return name;
	}

	public int getclips()
	{
		return clips;
	}

	public int getLength()
	{
		return length;
	}

	public boolean isValid()
	{

	}

	public void stampCard()
	{

	}




	

	/*<datafelt>

	<konstruktør som gir startverdier>

	<abstrakte metoder for å hente ut relevante datafelter>

	<abstrakte metode for å undersøke om et tidsbasert skikort er gyldig>

	<abstrakt metode for å trekke X klipp>

	<abstrakt metode for å registrere passering>--> DENNE TROR JEG BURDE GJØRES I KONTROLLVINDUET*/

}