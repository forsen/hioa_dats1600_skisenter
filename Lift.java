import java.io.*;

public abstract class Lift implements Serializable
{
	private int liftNr;
	private String name;
	private int clips;
	private int length;

	public Lift(int l, String n, int c, int m)
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
	




	

	/*<datafelt>

	<konstruktør som gir startverdier>

	<abstrakte metoder for å hente ut relevante datafelter>


	<abstrakt metode for å registrere passering>--> DENNE TROR JEG BURDE GJØRES I KONTROLLVINDUET*/

}