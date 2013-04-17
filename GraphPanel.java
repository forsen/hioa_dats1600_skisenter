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

	private static final int XAXISLENGTH = 520;
	private static final int YAXISLENGTH = 450;
	private static final int ORIGOX = 30; 
	private static final int ORIGOY = 450; 

	private int xAxisInterval;
	private int yAxisInterval; 

	private int yAxisMin;
	private int yAxisMax;
	private int[] data;  

	private int lastx;
	private int lasty; 

	public GraphPanel( int[] d )
	{
		data = d; 
		//list = l;
		setBackground( Color.WHITE );
		setPreferredSize (new Dimension(600,500));
//		nyGrafFlate();
		yAxisMax = arrayMax( data );
		yAxisMin = arrayMin( data );
		xAxisInterval = XAXISLENGTH / data.length;
		yAxisInterval = YAXISLENGTH / (yAxisMax - yAxisMin);

		lastx = xAxisInterval + ORIGOX; 
		lasty = ORIGOY - data[0]; 

	}
/*
	public void nyGrafFlate()
  	{
	   repaint();
	}
*/
/*
	private int createYValues( int y )
	{
		y * yAxisInterval - yAxisMin

	}
*/
	private int arrayMin( int[] data )
	{
		int min = data[0]; 

		for(int i = 0; i < data.length; i++)
		{
			min = (min < data[i])?min:data[i];
		}
		System.out.println( "arrayMin: " + min );
		return min; 
	}

	private int arrayMax( int[] data )
	{
		
		 int max;
		try
		{
			 max = data[0];
		}
		catch(NullPointerException npe)
		{
			 max = 1;
		}

		for( int i = 0; i < data.length; i++)
			max = (max > data[i])?max:data[i]; 

		System.out.println( "arrayMax: " + max );
		return max; 
	}

	public void drawYinterval(Graphics2D g2d)
	{

		for(int i = 1; i < 10; i++)
		{

			g2d.drawLine(25, ORIGOY - (i * YAXISLENGTH / 10 ), 35,  ORIGOY - (i * YAXISLENGTH / 10 ));
			g2d.drawString("" + ((((yAxisMax - yAxisMin)/10) * i) + yAxisMin), 0 , ORIGOY - (i * YAXISLENGTH/10));
			printDashedLine( 35, ORIGOY - (i * YAXISLENGTH / 10 ), 550, ORIGOY - (i * YAXISLENGTH / 10 ), g2d );

			System.out.println( "ymin:" + yAxisMin + ", ymax: " + yAxisMax + ", i: " + i);
			System.out.println( "" + (((yAxisMax - yAxisMin)/10) * i));	
		}
	}
	
	public void drawXinterval(Graphics2D g2d)
	{

		for(int i = 1 ; i < data.length; i ++)
		{
			g2d.drawLine((i*xAxisInterval) + ORIGOX, 447, (i*xAxisInterval) + ORIGOX, 454);
			g2d.drawString( "" + i, (i*xAxisInterval) + ORIGOX, 480 );
			printDashedLine( (i*xAxisInterval) + ORIGOX, 447, (i*xAxisInterval) + ORIGOX, 10, g2d); 
		}

/*		int intertval = 1;

		for(int i = (20+42); i < INTERVALY; i+=((INTERVALY-30)/10))
		{
			g.drawLine(i,447,i,454);
			g.drawString(intertval+"", i, 480);

			intertval+=1;
		}*/
	}

	private void drawGraphCoordinates( int y, Graphics2D g2d )
	{
		g2d.drawLine( lastx, lasty, lastx + xAxisInterval, YAXISLENGTH - ((y - yAxisMin) * yAxisInterval) );

		System.out.println("x1: " + lastx +", y1: " + lasty + ", x2: " + (lastx + xAxisInterval) + ", y2: " + (YAXISLENGTH - ((y - yAxisMin) * yAxisInterval)) ); 

		lasty = YAXISLENGTH - ((y - yAxisMin) * yAxisInterval) ;
		lastx += xAxisInterval;

	}


	private void printDashedLine( int x1Pos, int y1Pos, int x2Pos, int y2Pos, Graphics2D g2d )
	{
		Stroke s = g2d.getStroke();

		g2d.setColor( Color.LIGHT_GRAY ); 
		float[] dashPattern = {6, 3, 6, 3};
		g2d.setStroke( new BasicStroke( 1, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10, dashPattern, 0 ));

		g2d.drawLine(x1Pos, y1Pos, x2Pos, y2Pos);

		g2d.setColor( Color.BLACK );
		g2d.setStroke(s); 
	}	

	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g; 

		g2d.drawLine(ORIGOX, 10, ORIGOX, ORIGOY);
		g2d.drawString("Y", 10,15);
		g2d.drawLine(ORIGOX,ORIGOY , 550, ORIGOY);
		g2d.drawString("X", 560,450);

		drawXinterval(g2d);
		drawYinterval(g2d);

		// set first coordinate: 

		lasty = YAXISLENGTH - ((data[0] - yAxisMin) * yAxisInterval);

		for( int i = 1; i<data.length; i++ )
		{
			drawGraphCoordinates( data[i], g2d );
		}

		lastx = xAxisInterval + ORIGOX;
		lasty = YAXISLENGTH - ((data[0] - yAxisMin) * yAxisInterval); 


	}
}