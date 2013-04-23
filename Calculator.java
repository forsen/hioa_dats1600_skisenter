
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
	private int[] graph;

	public Calculator(Personlist cr, List<Validations> v, Cardlist cl )
	{
		custRegistry = cr;
		cardlist = cl;
		validations = v;
	
	}
/*
	public List<Person> getRelevantCards(Date start, Date end)
	{
		return custRegistry.getRelevantCards(start,end);
	}
*/
	public int[] totalRegPeople(Date s, Date e)
	{
		List<Person> plist = custRegistry.totalRegPeople(s, e);

		Iterator<Person> it = plist.iterator();

		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		start.setTime( s );
		end.setTime( e );

		int range = end.get(Calendar.DAY_OF_YEAR) - start.get( Calendar.DAY_OF_YEAR );

		System.out.println( "range " + range);


		graph = new int[range];

		for( int i = 0; i < graph.length; i++)
		{
			graph[i] = 0;
		}

		while(it.hasNext())
		{
		 	Calendar c = Calendar.getInstance();
		 	Person p = it.next(); 

		 	c.setTime( p.getCreated() );

		 	System.out.println( p );


			graph[c.get(Calendar.DAY_OF_YEAR) - start.get( Calendar.DAY_OF_YEAR ) ] ++;  
		}		

		if( graph.length > 15 )
			graph = normalize( graph );

		return graph;
	}
/*
	public int totalRegPepole()
	{
		return custRegistry.totalRegPepole();
	}
*/

	private int[] normalize( int[] d )
	{
		int[] data = d; 

		int[] newData = new int[ 15 ];

		int split = (int) Math.ceil((data.length / ((double) newData.length - 1 ))) ; 

		int j = 0;

		for( int i = 0; i < data.length; i++ )
		{
			newData[j] += data[i];

			if( (i+1) % split == 0 )
			{
				j++;
				System.out.println( "i: " + i + ", j: " + j );
			}
		}

		return newData;

	}
	public int regCards()
	{
		return custRegistry.soldCards();
	}

	public int unregCards()
	{
		return cardlist.allCards();
	}

	public int totalSoldCard()
	{
		return regCards() + unregCards();
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
		return custRegistry.totalCost() + cardlist.totalCost();
	}

	/*public int totalPunch()
	{
		return custRegistry.totalPunch();
	}*/

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

	public int regCardsSoldInMonthX(int x)
	{
		return 0;
	}

	public int unregCardsSoldInMonthX(int x)
	{
		return cardlist.unregCardsSoldInMonthX( x );
	}


	public int cardSoldInMonthX(int x)
	{
		return unregCardsSoldInMonthX(x);
	}











}