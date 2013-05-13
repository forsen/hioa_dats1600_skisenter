import java.io.*;
import java.util.List;

/**
 * Tcuplift is a subclass of Lift
 * 
 * @author Erik Haider Forsén
 * @author Ole Hansen
 * @author Julie Hill Roa
 * @version 0.9
 */

public class Tcuplift extends Lift implements Serializable
{
	private final int TCUPCLIP;


/**
  *	Creates the Tcuplift
  *
  *	@param lv		the validations 		
  * @param cr 		the personlist
  * @param n		the lift name
  * @param m 		the length of the lift
  * @see Skiresort
  */

	public Tcuplift( List<Validations> lv, Personlist cr, String n, int m)
	{
		super(lv, cr, n,1,m);
		TCUPCLIP = 1;
	}
}//end of class Tcuplift