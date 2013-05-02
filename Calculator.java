
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
	private int[][] graph;

	public Calculator(Personlist cr, List<Validations> v, Cardlist cl )
	{
		custRegistry = cr;
		cardlist = cl;
		validations = v;
	
	}

	public int[][] totalSoldCard(Date s, Date e)
	{
		List<Card> clist = custRegistry.getRelevantCards( s, e );
		clist.addAll( cardlist.getRelevantCards( s, e ) );

		Iterator<Card> cIt = clist.iterator();

		graph = new int[4][calculateRange( s, e )];

		for( int i = 0; i < graph[0].length; i++ )
		{
			graph[0][i] = 0;
		}

		while( cIt.hasNext() )
		{

			Card crd = cIt.next();

			List<Skicard> skl = crd.getRelevantCards(s,e);

			Iterator<Skicard> sIt = skl.iterator(); 

			while( sIt.hasNext() )
			{
				Calendar c = Calendar.getInstance();
				Calendar start = Calendar.getInstance();

				start.setTime( s );

				Skicard sc = sIt.next(); 

				c.setTime( sc.getBought() );


				graph[sc.getType()][c.get(Calendar.DAY_OF_YEAR) - start.get( Calendar.DAY_OF_YEAR ) - 1]++; 
			} 
		}

		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i] );

		}
		return graph;
	}

	public int[][] totalRegPeople(Date s, Date e)
	{
		List<Person> plist = custRegistry.totalRegPeople(s, e);

		Iterator<Person> it = plist.iterator();



		graph = new int[1][calculateRange( s , e )];

		for( int i = 0; i < graph[0].length; i++)
		{
			graph[0][i] = 0;
		}

		while(it.hasNext())
		{
		 	Calendar c = Calendar.getInstance();
		 	Calendar start = Calendar.getInstance(); 
		 	start.setTime( s );
		 	Person p = it.next(); 

		 	c.setTime( p.getCreated() );

		 	System.out.println( p );


			graph[0][c.get(Calendar.DAY_OF_YEAR) - start.get( Calendar.DAY_OF_YEAR ) - 1] ++;  
		}		

		if( graph[0].length > 20 )
			graph[0] = normalize( graph[0] );

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

		int[] newData = new int[ (int) Math.ceil((data.length / (double) 7.0)) ];

		//int split = (int) Math.ceil((data.length / ((double) newData.length - 1 ))) ; 

		System.out.println( "Lengden: " + data.length );
		System.out.println( "Delt på 7:" + (int) Math.ceil((data.length / (double) 7.0)) );

		int split = 7;

		int j = 0;

		for( int i = 0; i < data.length; i++ )
		{
			newData[j] += data[i];

			if( (i+1) % split == 0 )
			{
				j++;
				//System.out.println( "i: " + i + ", j: " + j );
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
/*
	public int totalSoldCard()
	{
		return regCards() + unregCards();
	}
*/
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


	private int calculateRange( Date s, Date e )
	{
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		start.setTime( s );
		end.setTime( e );

		return end.get(Calendar.DAY_OF_YEAR) - start.get( Calendar.DAY_OF_YEAR );
	}








}