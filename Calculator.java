import javax.swing.*;
import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Iterator;

public class Calculator
{
	private Personlist custRegistry;
	//private List<Skicard> cardlist;
	private List<Validations> validations;

	public Calculator(Personlist cr, List<Validations> v )
	{
		custRegistry = cr;
		//cardlist = cl;
		validations = v;
	}

	public int totalRegPepole()
	{
		return custRegistry.totalRegPepole();
	}

	public int regThatTime(int nr)
	{
		return custRegistry.regThatTime(nr);
	}

	public int passesbyTypeofCard(int liftnr)
	{
		Iterator<Validations> it = validations.iterator();

		int antall = 0;

		while(it.hasNext())
		{
			if(it.next().getLiftId() == liftnr)
				antall++;
		}
		return antall;
	}

	public int totalCost()
	{
		return custRegistry.totalCost();
	}

	public int showPassings()
	{
		
		Iterator<Validations> it = validations.iterator();
		int antall = 0;

		while( it.hasNext() )
		{
			it.next();
			antall++;
		}
		return antall;
	}

}