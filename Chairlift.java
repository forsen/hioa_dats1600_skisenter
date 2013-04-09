import java.io.*;

public class Chairlift extends Lift implements Serializable
{
	private final int CHAIRCLIP = 2;
	private int seats;


	public Chairlift( String n, int m, int s)
	{
		super(n,2,m);
		seats = s;
	
	}

}