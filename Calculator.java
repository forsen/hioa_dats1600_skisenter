import javax.swing.*;
import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Iterator;
import java.text.SimpleDateFormat;

/**
  * Calculator is a class to do the calculations for statistics, reports and graphs.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */
public class Calculator
{
	private Personlist custRegistry;
	private Cardlist cardlist;
	private List<Validations> validations;
	private int[][] graph;

/**
  * This constructor will need access to relevant information to do its calculations
  * @param cr 	the personlist
  * @param v 	the validations
  * @param cl 	the unregistered cards
  * @see Personlist
  * @see Validations
  * @see Cardlist
  */
	public Calculator(Personlist cr, List<Validations> v, Cardlist cl )
	{
		custRegistry = cr;
		cardlist = cl;
		validations = v;
	
	}

/**
  * This method calculates all cards sold within the specified time range. The result will
  * be used to draw a graph. Depending on the range, the data will be normalized
  * to fit within 20 "x-steps" on a graph. The method will also set the proper
  * scale depending on how many times it is normalized.
  *
  * @param s 	Date object representing the start date of the interval
  * @param e 	Date object representing the end date of the interval
  * @return This method returns a multidimensional array of Integers[x][y].
  * The x represents type of card, while y represents the date. The value of
  * int[x][y] is the number of x cards sold the y'th day.  
  */
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
			AdminStatistikkPanel.scale = "Uker";
		}

		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i], 4 );
			AdminStatistikkPanel.scale = "Mnd";
		}

		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i], 12 );
			AdminStatistikkPanel.scale = "År"; 
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
		{
			graph[0] = normalize( graph[0], 7 );
			AdminStatistikkPanel.scale = "Uker";
		}
		if( graph[0].length > 20 )
		{
			graph[0] = normalize( graph[0], 4 );
			AdminStatistikkPanel.scale = "Mnd";
		}
		if( graph[0].length > 20 )
		{
			graph[0] = normalize( graph[0], 12 );
			AdminStatistikkPanel.scale = "År";
		}
 
		return graph;
	}


	// This method needs to be polished. Currently the incoming split assumes every month is exactly 4 weeks, which is not very accurate. 

	public int regCards()
	{
		return custRegistry.soldCards();
	}

	public int unregCards()
	{
		return cardlist.allCards();
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
			AdminStatistikkPanel.scale = "Uker";
		}

		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i], 4 );
			AdminStatistikkPanel.scale = "Mnd";
		}

		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i], 12 );
			AdminStatistikkPanel.scale = "År";
		}

		
		return graph;
	}


	public int[][] showPassings( Date s, Date e )
	{
		
		int start = daysSinceOpening( s );
		int end = daysSinceOpening( e ); 
	
		graph = new int[Info.LIFTS][ end - start + 1 ]; 


		Iterator<Validations> it = validations.iterator();
		


		while( it.hasNext() )
		{
			Validations v = it.next();
			if( v.getDate().after(s) && v.getDate().before(e))
			{
				graph[v.getLiftId() - 1][daysSinceOpening(v.getDate()) - start] ++;
			}
		}


		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i], 7 );
			AdminStatistikkPanel.scale = "Uker";
		}

		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i], 4 );
			AdminStatistikkPanel.scale = "Mnd";
		}

		if( graph[0].length > 20 )
		{
			for( int i = 0; i < graph.length; i++ )
				graph[i] = normalize( graph[i], 12 );
			AdminStatistikkPanel.scale = "År"; 
		}

		return graph;
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

	private int[] normalize( int[] d , int s)
	{
		int[] data = d; 

		int[] newData = new int[ (int) Math.ceil((data.length / (double) s )) ];


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
				
			}
		}

		return newData;

	}


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