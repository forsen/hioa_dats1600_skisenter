import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Random;
import java.util.Date;
import java.io.IOException;
import java.util.List;

/**
  * This is a class to create some random data to our Skiresort. 
  * It will create person objects, card objects, skicard objects and
  * validations. Number of objects is configurable through constants.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

public class RandomData
{
// CONSTANTS

	private final int ANTALLPERSONER = 5000;
	private final int MINKORTPERPERSON = 2;
	private final int MAXKORTPERPERSON = 5;
	private final int MINSKIKORTPERKORT = 1; 
	private final int MAXSKIKORTPERKORT = 4;
	private final int SKISESONGSTARTMND = 11;
	private final int SKISESONGSLUTTMND = 5; 
	private final int UNREGCARDS = 10000;

// END CONSTANTS

	private Personlist registry;
	private Cardlist cardlist; 
	private List<Validations> validations; 
	private Random rand; 
	private NameGenerator gen; 

/** 
  * The constructor receives the necessary registries to work and, and prepare
  * the NameGenerator. It also calls the addPerson() method, and starts the process
  * of generating random data. 
  * @param r 	The personlist to add to
  * @param c 	The cardlist to add to
  * @param v 	The validations list to add to
  * @see NameGenerator
  */
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

		for( int i = 0; i < UNREGCARDS; i++ )
		{
			cardlist.addCard( addCards( null, randomDate( 2012, 2013 ) ) );
		}
	}

/**
  * This method generates the Person objects based on constants
  * set by the user. It will also call the other necessary methods
  * for creating and adding cards. 
  */
	private void addPerson()
	{
		for( int i = 0; i < ANTALLPERSONER; i++)
		{
			String firstname = gen.compose(2);
			String lastname = gen.compose( 4 );

			Date dob = randomDate( 1950, 2010 );

			int number = rand.nextInt( 88888888 + 1) + 10000000; 

			Person p = new Person( firstname, lastname, number, dob, null);

			Date rDate = randomDate( 2012, 2013 );

			p.setCreated( rDate );

			int numberofcards = randBetween( MINKORTPERPERSON, MAXKORTPERPERSON );

			for( int j = 0; j < numberofcards; j++)
				p.addCard( addCards( dob, rDate ) );

			registry.input( p );
			 
		}
	}

/**
  * This method will create and add cards based on user configurable constants
  * @param b 	Date of Birth of the person this card belongs to (null if unregistered card)
  * @param r 	Creation date of this card
  * @return Returns a card with some Skicards associated with it.
  */
	private Card addCards( Date b, Date r )
	{
		Calendar helper = Calendar.getInstance();
		helper.setTime( r );
		Date rDate;
		do
		{
			rDate = randomDate( helper.get(Calendar.YEAR), 2013 );
		} while (rDate.before( r ));

		Card n = new Card( rDate ); 
		Skicard s = new Daycard( b, b );
		//int random = randBetween( 1, 4 );
		int randomNumber = rand.nextInt( 4 ) + 1; 
		int i = 0; 
		do
		{
			int random =rand.nextInt( 4 ) + 1;
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
		while( i < randomNumber );

		return n; 
	}

/**
  * This method will validate each card. It takes a card as parameter, and 
  * validates that card. It also initializes the card.
  * @param c 	The card to be validated
  */
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

		if( sc instanceof Timebasedcard)
			((Timebasedcard) sc).setLastValidated( cal.getTime()); 

		d = cal.getTime(); 

		Validations v = new Validations( randBetween(1,2), c, d );

		validations.add( v );
		sc.initialized();


	}

/**
  * A method to create a random date within a range of years.
  * @param min 	Minimum year for the returned date
  * @param max 	Maximum year for the returned date
  * @return Returns a date object, with random data between the min and max years specified.
  */
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

/**
  * A method to generate a random number between two thresholds.
  * @param start 	Minimum value
  * @param end 		Maximum value
  * @return Returns an Integer between the range specified
  */
	private int randBetween(int start, int end)
	{
		return start + (int)Math.round(Math.random() * (end - start));
	}
} // end of class RandomData