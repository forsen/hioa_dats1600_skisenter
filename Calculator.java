
import javax.swing.*;
import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Iterator;

// kan fjernes
import java.text.SimpleDateFormat;

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

		int start = daysSinceOpening( s );
		int end = daysSinceOpening( e ); 

		graph = new int[5][ end - start + 1];

		for( int i = 0; i < graph[0].length; i++ )
		{
			graph[0][i] = 0;
		}

		while( cIt.hasNext() )
		{

			Card crd = cIt.next();

			List<Skicard> skl = crd.getRelevantCards(s,e);

			Iterator<Skicard> sIt = skl.iterator(); 
			if(crd.getCardBought().after(s) && crd.getCardBought().before(e))
				graph[4][(daysSinceOpening(crd.getCardBought()) - start)]++; 

			while( sIt.hasNext() )
			{		

				Skicard sc = sIt.next(); 

				graph[sc.getType()][(daysSinceOpening( sc.getBought() ) - start )]++; 
				System.out.println( "Skikorttype" + sc.getType() + " Dag: " + ( daysSinceOpening(sc.getBought()) - start ));
				System.out.println( "Kjøpt: " + new SimpleDateFormat("ddMMyy").format(sc.getBought() ));
			} 
		}



		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i], 7 );
		}

		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i], 4 );
		}

		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i], 12 );
		}

		
		return graph;
	}

	public int[][] totalRegPeople(Date s, Date e)
	{
		List<Person> plist = custRegistry.totalRegPeople(s, e);

		Iterator<Person> it = plist.iterator();


		int start = daysSinceOpening( s );
		int end = daysSinceOpening( e );

		graph = new int[1][end - start + 1 ];

		for( int i = 0; i < graph[0].length; i++)
		{
			graph[0][i] = 0;
		}

		while(it.hasNext())
		{

		 	Person p = it.next(); 


		 	System.out.println( p );


			graph[0][ daysSinceOpening( p.getCreated() ) - start ] ++;  
		}		

		if( graph[0].length > 20 )
			graph[0] = normalize( graph[0], 7 );
		if( graph[0].length > 20 )
			graph[0] = normalize( graph[0], 4 );
		if( graph[0].length > 20 )
			graph[0] = normalize( graph[0], 12 );
		
 
		return graph;
	}
/*
	public int totalRegPepole()
	{
		return custRegistry.totalRegPepole();
	}
*/

	private int[] normalize( int[] d , int s)
	{
		int[] data = d; 

		int[] newData = new int[ (int) Math.ceil((data.length / (double) s )) ];

		//int split = (int) Math.ceil((data.length / ((double) newData.length - 1 ))) ; 

		System.out.println( "Lengden: " + data.length );
		System.out.println( "Delt på 7:" + (int) Math.ceil((data.length / (double) 7.0)) );

		int split = s;

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

	public int[][] totalRevenue(Date s, Date e)
	{
		List<Card> clist = custRegistry.getRelevantCards( s, e );
		clist.addAll( cardlist.getRelevantCards( s, e ) );

		Iterator<Card> cIt = clist.iterator();

		int start = daysSinceOpening( s );
		int end = daysSinceOpening( e ); 


		graph = new int[5][ end - start + 1];

		for( int i = 0; i < graph[0].length; i++ )
		{
			graph[0][i] = 0;
		}

		while( cIt.hasNext() )
		{

			Card crd = cIt.next();

			List<Skicard> skl = crd.getRelevantCards(s,e);

			Iterator<Skicard> sIt = skl.iterator(); 
			if(crd.getCardBought().after(s) && crd.getCardBought().before(e))
			{
				graph[4][(daysSinceOpening( crd.getCardBought() ) - start )] += Info.CARDPRICE;
				if( crd.getReturned() )
					graph[4][(daysSinceOpening( crd.getCardBought() ) - start )] += Info.RETURNPRICE;
			}
			while( sIt.hasNext() )
			{		

				Skicard sc = sIt.next(); 

				


				graph[sc.getType()][(daysSinceOpening( sc.getBought() ) - start )] += sc.getPrice(); 

			} 
		}



		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i], 7 );
		}

		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i], 4 );
		}

		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i], 12 );
		}

		
		return graph;
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

/*
	private int calculateRange( Date s, Date e )
	{
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		start.setTime( s );
		end.setTime( e );

		return end.get(Calendar.DAY_OF_YEAR) - start.get( Calendar.DAY_OF_YEAR ) + 1;
	}
*/
	private int daysSinceOpening( Date d )
	{
		Calendar dateToCompare = Calendar.getInstance();
		dateToCompare.setTime( d );

		Calendar opening = Calendar.getInstance();

		opening.setTime( Info.firstLight );

		int days = 0; 

		if( opening.get( Calendar.YEAR ) == dateToCompare.get( Calendar.YEAR ))
			return dateToCompare.get( Calendar.DAY_OF_YEAR );


		for( int i = opening.get( Calendar.YEAR ); i < dateToCompare.get( Calendar.YEAR ); i++ )
			days += daysPerYear( i );
	
		days += dateToCompare.get( Calendar.DAY_OF_YEAR );

		return days; 
	}

	private int daysPerYear( int y )
	{
		return (( y % 4 == 0 && y % 100 != 0 )  || (y % 400 == 0 )? 366:365 );
	}







}