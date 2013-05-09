import java.util.*;

public class Punchcard extends Skicard
{
	private int clipCount;

	public Punchcard( Date bd, Date b)
	{
		super(Info.PUNCHCARDPRICE, bd, b, Skicard.PUNCHCARD);
		clipCount = -1;
	}

	public int getClipCount()
	{
		return clipCount;
	}

	public void initialized()
	{
		clipCount += 11;
	}

	public void usePunchCard( int c )
	{
		clipCount -= c;
	}

	public String toString()
	{
		return "Klippekort\tAntall klipp igjen: " + getClipCount()+ "\t" + super.toString();
	}
}