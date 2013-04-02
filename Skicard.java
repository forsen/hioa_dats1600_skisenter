//Superclass for all the different cardtypes 

public abstract class Skicard implements Serializable 
{
	private int cardNumber;


	public Skicard(int cN)
	{
		cardNumber = cN;
	}

	public abstract int getCardNumber()
	{
		return cardNumber;
	}

	public abstract boolean isValid()
	{
		if () 
		{
			return true;
		}
		return false;
	}
	/*<datafelt>

	<konstuktør>

	<astrakt get-metoder for å hente ut datafeltene til kortene>
	<abstrakt metode for å skjekke om kortet er gyldig>*/
	 
}