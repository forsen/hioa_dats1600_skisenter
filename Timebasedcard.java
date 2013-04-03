public abstract class Timebasedcard extends Skicard
{
	//<datafelter>
	boolean valid;

	public Timebasedcard(int p, int d, String ag, boolean v )
	{
		super(p, d, ag);
		valid = v;
	}

	/*<abstrakt metode(r) for å sjekke gyldighet (evt datometoder for å holde styr på tider)>

	<abstrakt getmetode for heiskortnr>*/

	abstract public void unvalidate(); /*<abstrakt metode for å sette ugyldig>*/

	abstract public void addTime(); /*<abstrakt metode for å legge til mer tid>*/
}