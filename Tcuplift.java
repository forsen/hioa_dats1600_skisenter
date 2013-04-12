import java.io.*;
import java.util.List;

public class Tcuplift extends Lift implements Serializable
{
	private final int TCUPCLIP;

	public Tcuplift( List<Validations> lv, Personlist cr, String n, int m)
	{
		super(lv, cr, n,2,m);
		TCUPCLIP = 1;
	}
}