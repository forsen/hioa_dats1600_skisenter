public class Hourcard extends Skicard
{
	private boolean gyldig; 
	private int heiskortnr; 

	public Hourcard( <dataverdi?> )
	{
		gyldig = true; 
	}

	public boolean gyldig()
	{
		return gyldig;
	}

	public int getHeiskort()
	{
		return heiskortnr; 
	}// end of method

}// end of class Hourcard