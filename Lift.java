import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;

public abstract class Lift implements Serializable
{
	private int liftNr;
	private String name;
	private int clips;
	private int length;
	private List<Validations> validations;
	private Personlist registry; 
	private static int next = 1; 

	public Lift( List<Validations> lv, Personlist cr, String n, int c, int m)
	{
		liftNr = next++;
		name = n;
		clips = c;
		length = m;
		registry = cr; 
		validations = lv;
		
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

	public List<Validations> getValidations()
	{
		return validations;
	}

	public void registrations( Card c )
	{
		Validations v = new Validations( liftNr, c );
		validations.add( v );
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