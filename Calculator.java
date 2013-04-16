import javax.swing.*;
import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Iterator;

public class Calculator
{
	private Personlist custRegistry;
	private Cardlist cardlist;
	private List<Validations> validations;

	public Calculator(Personlist cr, List<Validations> v, Cardlist cl )
	{
		custRegistry = cr;
		cardlist = cl;
		validations = v;
	}

	public int totalRegPepole()
	{
		return custRegistry.totalRegPepole();
	}

	public int regCards()
	{
		return 0;
	}

	public int unregCards()
	{
		return cardlist.allCards();
	}

	public int totalSoldCard()
	{
		return regCards()+unregCards();
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

	public int totalPunch()
	{
		return custRegistry.totalPunch();
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