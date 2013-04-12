import java.util.Date; 

public abstract class Timebasedcard extends Skicard
{
	//<datafelter>
	protected Date expires; 

	public Timebasedcard(int p, int d, String ag, Date b )
	{
		super(p, d, ag, b);

	}

	/*<abstrakt metode(r) for å sjekke gyldighet (evt datometoder for å holde styr på tider)>*/
  

	abstract public void initialized();		/*abstrakt metode for å initializere (gi kortet startttid og slutttid)*/

	abstract public void unvalidate(); 		/*<abstrakt metode for å sette ugyldig>*/
}