package no.forsen.skisenter;

import java.io.*;
import java.util.List;

/**
  * This class is a subclass of Lift. 
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */



public class Chairlift extends Lift implements Serializable
{
	private final int CHAIRCLIP = 2;
	private int seats;

/**
  * Creates the Chairlift. This lift type takes 2 clips. 
  * @param lv 	the validations to show information about
  * @param cr 	the personlist to show information about
  * @param n    the lift name
  * @param m    the length of the lift
  * @param s    the number of seats 
  */
	public Chairlift( List<Validations> lv, Personlist cr, String n, int m, int s)
	{
		super(lv, cr, n,2,m);
		seats = s;
	
	}


/**
  * This is Chairlifts toString. It contains its supers toString plus information about seats.
  */
	public String toString()
	{
		return super.toString() + "\nAntall seter: " + seats; 
	}

} //end of class Chairlift