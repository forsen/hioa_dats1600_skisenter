import java.io.*;

public class Chairlift extends Lift implements Serializable
{
	private final int CHAIRCLIP;
	private int seats;


	public Chairlift(int l, String n,int c, int m, int s, int v)
	{
		super(l,n,c,m,v);
		seats = s;
		CHAIRCLIP = 2;
	}

}