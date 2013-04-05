import java.util.*;
import java.text.*;
import java.awt.*;
import javax.swing.*;

public class Seasoncard extends Timebasedcard
{
	Date bought;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
	Date seasonEnd = df.parse("2013-04-20");
	int pris;

	public Seasoncard(int p, int d, String ag, Date newBought, boolean v)
	{
		super(p, d, ag, v);
		pris = Info.SEASONCARDPRICE;
		bought = newBought;
	}

	public boolean isValid()
	{

		if(bought.before(seasonEnd))
		{
			return true;
		} 
	}
}

	/*<metode(r) for å sjekke gyldighet (evt datometoder for å holde styr på tider)>

	<getmetode for heiskortnr>

	<metode for å sette ugyldig>

	<metode for å legge til mere tid>*/