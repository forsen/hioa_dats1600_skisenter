package skisenter;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;


/**
  * This class is an abstract class. The Lift class has two subclasses, Chairlift and Tcuplift.
  * Lift contains data about the lifts.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

public abstract class Lift implements Serializable
{
	private int liftNr;
	private String name;
	private int clips;
	private int length;
	private List<Validations> validations;
	private Personlist registry; 
	private static int next = 1; 

/**
  * The constructor creates all the elements and place them on the panel. 
  * @param cr 	the personlist to show information about
  * @param lv 	the validations to show information about
  * @param n    the lift name
  * @param c    how many clips the lift will take when using a punchcard
  * @param m    the length of the lift
  */
	public Lift( List<Validations> lv, Personlist cr, String n, int c, int m)
	{
		liftNr = next++;
		name = n;
		clips = c;
		length = m;
		registry = cr; 
		validations = lv;
		
	}

/**
  * This method returns the number of the lift. 
  * @return returns the liftnumber.
  */
	public int getLiftNr()
	{
		return liftNr;
	}

/**
  * This method returns the name of the lift.
  * @return returns the name of the lift.
  */
	public String getName()
	{
		return name;
	}

/**
  * This method returns the number of clips needed to ride the lift.
  * @return returns number of clips.
  */
	public int getClips()
	{
		return clips;
	}

/**
  * This method returns the length of the lift.
  * @return returns length of the lift.
  */
	public int getLength()
	{
		return length;
	}

/**
  * This method returns validations from list.
  * @return returns validations from list.
  */
	public List<Validations> getValidations()
	{
		return validations;
	}


/**
  * This method registers ride with a lift, for statistical purposes.
  */
	public void registrations( Card c )
	{
		Validations v = new Validations( liftNr, c, null );
		validations.add( v );
	}



/**
  * This is Lifts toString. It returns a string that contains the lifts number, its name, the number of clips needed, and its length.
  */
	public String toString()
	{

    	StringBuilder text = new StringBuilder();
    
    	text.append(name + ":\t"); 
    	text.append("\nHeisnummer: "); 
    	text.append(liftNr); 
    	text.append("\n Klipp som trengs for å kunne kjøre: "); 
    	text.append(clips); 
   	 	text.append("\n Lengde:"); 
   	 	text.append(length); 
   		text.append("\n "); 
    
    	String doneTekst = text.toString();
    
    	return doneTekst;
  
	}
} // end of class Lift