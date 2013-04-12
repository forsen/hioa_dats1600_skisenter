import java.io.*;
import java.util.List;

public class Chairlift extends Lift implements Serializable
{
	private final int CHAIRCLIP = 2;
	private int seats;


	public Chairlift( List<Validations> lv, Personlist cr, String n, int m, int s)
	{
		super(lv, cr, n,2,m);
		seats = s;
	
	}

	public String toString()
	{
		return super.toString() + "\nAntall seter: " + seats; 
	}

}