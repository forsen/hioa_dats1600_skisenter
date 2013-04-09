import java.io.*;

public class Tcuplift extends Lift implements Serializable
{
	private final int TCUPCLIP;

	public Tcuplift( String n, int m)
	{
		super(n,2,m);
		TCUPCLIP = 1;
	}
}