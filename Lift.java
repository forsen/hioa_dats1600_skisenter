import java.io.*;

public class Lift implements Serializable
{
	private int liftNr;
	private String name;
	private int clips;
	private int length;
	private int validations;

	public Lift(int l, String n, int c, int m, int v)
	{
		liftNr = l;
		name = n;
		clips = c;
		length = m;
		validations = v;
	}

	public int getLiftNr()
	{
		return liftNr;
	}

	public String getName()
	{
		return name;
	}

	public int getClips()
	{
		return clips;
	}

	public int getLength()
	{
		return length;
	}

	public int getValidations()
	{
		return validations;
	}

	public String toString()
	{

    	StringBuilder text = new StringBuilder();
    
    	text.append(name); 
    	text.append("\nHeisnummer: "); 
    	text.append(liftNr); 
    	text.append("\nKlipp som trengs for å kunne kjøre: "); 
    	text.append(clips); 
   	 	text.append("\nlengde"); 
   	 	text.append(length); 
   		text.append("\n"); 
    
    	String doneTekst = text.toString();
    
    	return doneTekst;
  
	}
	
	/*<abstrakt metode for å registrere passering>--> DENNE TROR JEG BURDE GJØRES I KONTROLLVINDUET*/

}