public class Punchcard extends Skicard
{
	private int clipCount;

	public Punchcard(int cc, int p, int d, String ag)
	{
		super(p, d, ag);
		clipCount = cc;
		//pris per klipp fra Info
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