import java.util.*;

public class Punchcard extends Skicard
{
	private int clipCount;

	public Punchcard(int p, int d, String ag, Date b)
	{
		super(p, d, ag, b, "Klippekort");
		clipCount = 0;
	}

	public int getClipCount()
	{
		return clipCount;
	}

	public void initialized()
	{
		clipCount =+ 10;
	}

	public void usePunchCard()
	{
		clipCount =-1;
	}

	public String toString()
	{
		return super.toString()+ "\nKlippekort";
	}
}