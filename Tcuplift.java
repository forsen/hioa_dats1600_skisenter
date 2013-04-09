import java.io.*;

public class Tcuplift extends Lift implements Serializable
{
	private final int TCUPCLIP;

	public Tcuplift(int l, String n,int c, int m, int v)
	{
		super(l,n,c,m,v);
		TCUPCLIP = 1;
	}
}