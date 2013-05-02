import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Random;
import java.util.Date;
import java.io.IOException;
import java.util.List;

public class RandomData
{

	/************************************************************************/



	private final int ANTALLPERSONER = 1000;
	private final int MINKORTPERPERSON = 2;
	private final int MAXKORTPERPERSON = 5;
	private final int MINSKIKORTPERKORT = 1; 
	private final int MAXSKIKORTPERKORT = 4;
	private final int SKISESONGSTARTMND = 11;
	private final int SKISESONGSLUTTMND = 5; 



	/************************************************************************/

	private Personlist registry;
	private Cardlist cardlist; 
	private List<Validations> validations; 
	private Random rand; 
	private NameGenerator gen; 

	public RandomData( Personlist r, Cardlist c, List<Validations> v )
	{
		registry = r;
		cardlist = c;
		validations = v;
		try
		{
			gen = new NameGenerator( "vokaler" );
		}
		catch( IOException ioe )
		{}

		rand = new Random();

		addPerson();
	}


	private void addPerson()
	{
		for( int i = 0; i < ANTALLPERSONER; i++)
		{
			String firstname = gen.compose(2);
			String lastname = gen.compose( 4 );

			Date dob = randomDate( 1920, 2010 );

			int number = rand.nextInt( 88888888 + 1) + 1000000; 

			Person p = new Person( firstname, lastname, number, dob, null);

			p.setCreated( randomDate( 2012, 2013 ) );

			int numberofcards = randBetween( MINKORTPERPERSON, MAXKORTPERPERSON );

			for( int j = 0; j < numberofcards; j++)
				p.addCard( addCards( dob ) );

			registry.input( p );
			 
		}
	}

	private Card addCards( Date b )
	{
		Card n = new Card(); 
		Skicard s = new Daycard( b, b );
		int random = randBetween( 1, 4 );
		int i = 0; 
		do
		{
			switch( random ) 
			{
				case 1: 
					s = new Daycard( b, randomDate(2012, 2013) );
					break;
				case 2: 
					s = new Seasoncard( b, randomDate(2012,2013) );
					break;
				case 3: 
					s = new Hourcard( b, randomDate( 2012, 2013) );
					break; 
				case 4:
					s = new Punchcard( b, randomDate( 2012, 2013 ) );
					break; 
			}

			n.input( s );
			i++;

			validate( n ); 
		}
		while( i < random );

		return n; 
	}


	private void validate( Card c )
	{
		Skicard sc = c.getCurrent();
		Date d = sc.getBought();
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(d);

		if( sc instanceof Daycard )
		{
			cal.set(Calendar.HOUR_OF_DAY, randBetween(cal.get(Calendar.HOUR_OF_DAY), 17) );
			cal.set(Calendar.MINUTE, randBetween(0, 59) );
		}
		if( sc instanceof Hourcard )
		{
			cal.set(Calendar.MINUTE, randBetween(0, 59) );
		}
		if( sc instanceof Seasoncard )
		{
			cal.set(Calendar.MONTH, randBetween(cal.get(Calendar.MONTH), 5 ) );
			cal.set(Calendar.DAY_OF_MONTH, randBetween(1, 28) );
			cal.set(Calendar.HOUR_OF_DAY, randBetween(10, 17) );
			cal.set(Calendar.MINUTE, randBetween( 0, 59 ) );
		}

		d = cal.getTime(); 

		Validations v = new Validations( randBetween(1,2), c, d );

		validations.add( v );

	}

	private Date randomDate( int min, int max )
	{
		int year = randBetween( min, max );
		int month = randBetween( 0, 11 );

		GregorianCalendar gc = new GregorianCalendar( year, month, 1 );

		int day = randBetween( 1, gc.getActualMaximum(gc.DAY_OF_MONTH));

		gc.set( year, month, day);
		Date d = gc.getTime();
		return d;

	}
	private int randBetween(int start, int end)
	{
		return start + (int)Math.round(Math.random() * (end - start));
	}
}