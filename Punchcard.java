import java.util.*;

/**
  * Punchcard is a subclass of Skicard. It creates a card with a number of clips.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

public class Punchcard extends Skicard
{
	private int clipCount;

/**
  * Creates the punchcard, and gives it a start value of -1 clips. This value will chane when
  * the card later is initialized.
  *
  * @param bd	Date object to determine agegroup
  * @param b 	Date object for when the card is bought
  */
	public Punchcard( Date bd, Date b)
	{
		super(Info.PUNCHCARDPRICE, bd, b, Skicard.PUNCHCARD);
		clipCount = -1;
	}

/**
  * Gets the clipcount of a punchcard. 
  * @return returns clipcount of a card.
  */
	public int getClipCount()
	{
		return clipCount;
	}

/**
  * Method that initializes the card. It adds eleven clips to the clipcount of a card.
  * In other words, the user will get 10 clips to use, since the start value of the clipcount is -1.
  * The start value of -1 clips is because it helps the Contol window to seperate cards that havn't
  * been used before, and cards that are depleted (clipCount = 0)
  */

	public void initialized()
	{
		clipCount += Info.CLIPS + 1;
	}

/**
  * Method that uses the punchcard, and deducts clips from the card.
  * The number of clips deducted are dependable on which lift is being used.
  *
  * @param c 	clips to be deducted.
  */
	public void usePunchCard( int c )
	{
		clipCount -= c;
	}


/**
  * This is Punchcard's toString. It contains clipcount, how many clips are left, and supers toString.
  * @see Skicard#toString()
  */

	public String toString()
	{
		String expires = null;

		if(getClipCount() == -1)
			expires = "ikke Validert";
		if (getClipCount() != -1)
			expires = "" + getClipCount() + "\t";

		return "Klippekort\tAntall klipp igjen: " + expires + "" + super.toString();
	}
} //end of class Punchcard