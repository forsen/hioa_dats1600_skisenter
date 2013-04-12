import java.io.*;

public class Tcuplift extends Lift implements Serializable
{
	private final int TCUPCLIP;

	public Tcuplift( Personlist cr, String n, int m)
	{
		super(cr, n,2,m);
		TCUPCLIP = 1;
	}
}