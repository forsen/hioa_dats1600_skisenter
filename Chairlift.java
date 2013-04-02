// kommentar
public class Chairlift extends Lift implements Serializable
{
	
	private int seats;


	public Chairlift(int l, String n,int c, int m, int s)
	{
		super(l,n,c,m);
		seats = s;
	}
	/*<datafelt>

	<konstruktør som gir startverdier>

	<metoder for å hente ut relevante datafelter>

	<metode for å undersøke om et tidsbasert skikort er gyldig>

	<metode for å trekke X klipp>

	<registrere passering>*/
}