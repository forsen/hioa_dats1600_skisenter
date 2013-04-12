import java.io.*;

public class Chairlift extends Lift implements Serializable
{
	private final int CHAIRCLIP = 2;
	private int seats;


	public Chairlift( Personlist cr, String n, int m, int s)
	{
		super(cr, n,2,m);
		seats = s;
	
	}

	public String toString()
	{
		return super.toString() + "\nAntall seter: " + seats; 
	}

}