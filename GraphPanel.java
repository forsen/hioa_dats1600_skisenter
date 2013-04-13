import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Iterator;

public class GraphPanel extends JPanel
{
	/*private int xPos, yPos;
	private final static int STARTPOSY = 10+100;
	private final static int STARTPOSX = 30;*/
	private final static int INTERVALY = 550;
	private final static int INTERVALX = 450;
	//private List<Person> list;
	/*List<Person> l*/
	public GraphPanel()
	{
		//list = l;
		setBackground( Color.WHITE );
		setPreferredSize (new Dimension(600,500));
		nyGrafFlate();

	}

	public void nyGrafFlate()
  	{
	   repaint();
	}

	public void drawYinterval(Graphics g)
	{
		int intertval = 100;

		for(int i = 30; i < INTERVALX; i+=((INTERVALX-30)/10))
		{
			g.drawLine(25,i,35,i);
			g.drawString(intertval+"", 0, i);

			intertval-=10;
		}
	}
	
	public void drawXinterval(Graphics g)
	{
		int intertval = 1;

		for(int i = (20+42); i < INTERVALY; i+=((INTERVALY-30)/10))
		{
			g.drawLine(i,447,i,454);
			g.drawString(intertval+"", i, 480);

			intertval+=1;
		}
	}

	/*public void drawSale(Graphics g)
	{
		Iterator<Person> it = list.iterator();
		Calendar helper = Calendar.getInstance();

	
		
		while(it.hasNext())
		{
			Person runner = it.next();
			helper.setTime( runner.getCreated());
			int min = helper.get(Calendar.MINUTE );
			int one = 0 ,two= 0,three = 0,four= 0,five= 0,six= 0,seven= 0,eight= 0,nine = 0,ten = 0;

			switch( min )
			{
				case 1 : one++;
							break; 
				case 2 : two++;
							break;
				case 3 : three++;
							break;
				case 4 : four++;
							break;
				case 5 : five++;
							break;
				case 6 : six++;
							break;
				case 7 : seven++;
							break;
				case 8 : eight++;
							break;
				case 9 : nine++;
							break;
				case 10 : ten++;
							break;
			}

		}	

		


	}*/


	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);

		g.drawLine(30, 10, 30, 450);
		g.drawString("Y", 10,15);
		g.drawLine(30,450 , 550, 450);
		g.drawString("X", 560,450);

		drawXinterval(g);
		drawYinterval(g);


	}
}