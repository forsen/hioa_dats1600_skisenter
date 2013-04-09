import java.util.*;

public class Punchcard extends Skicard
{
	private int clipCount;

	public Punchcard(int p, int d, String ag, Date b)
	{
		super(p, d, ag, b);
		clipCount = 10;
		price = Info.getPunchcardPrice();
	}

	public int getClipCount()
	{
		return clipCount;
	}

	public boolean isValid()
	{
		if(clipCount > 0)
		{
			return true;
		}
		return false;
	}

	public void usePunchCard()
	{
		if(isValid());
		{
			clipCount = (clipCount-1);
		}
	}

	public void addClip(int clip)
	{
		clipCount = clipCount + clip; 
	}
}