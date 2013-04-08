import java.util.Date; 

public abstract class Timebasedcard extends Skicard
{
	//<datafelter>
	private Date expires; 
	private Date initialized; 

	public Timebasedcard(int p, int d, String ag, Date b )
	{
		super(p, d, ag, b);

	}

	/*<abstrakt metode(r) for å sjekke gyldighet (evt datometoder for å holde styr på tider)>

	<abstrakt getmetode for heiskortnr>*/

	abstract public void unvalidate(); /*<abstrakt metode for å sette ugyldig>*/

	abstract public void addTime(); /*<abstrakt metode for å legge til mer tid>*/
}