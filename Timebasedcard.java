public abstract class Timebasedcard extends Skicard
{
	//<datafelter>
	boolean valid;
	int cardNumber;

	public Timebasedcard(int cNr, boolean v )
	{
		valid = v;
		cardNumber = cNr; 
	}

	/*<abstrakt metode(r) for å sjekke gyldighet (evt datometoder for å holde styr på tider)>

	<abstrakt getmetode for heiskortnr>*/

	abstract public void unvalidate(); /*<abstrakt metode for å sette ugyldig>*/

	abstract public void addTime(); /*<abstrakt metode for å legge til mer tid>*/
}