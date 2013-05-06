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
	private double yAxisInterval; 

	private int yAxisMin;
	private int yAxisMax;
	private int[][] data;  

	private int lastx;
	private int lasty; 

	private String labelX;
	private String labelY;

	private String label; 

	public GraphPanel( int[][] d, String x, String y, String interval )
	{
	
		labelX = x;
		labelY = y;

		label = interval; 

		data = d; 
		//list = l;
		setBackground( Color.WHITE );
		setPreferredSize (new Dimension(700,500));
//		nyGrafFlate();
		try
		{
			yAxisMax = arrayMax( data );
			yAxisMin = arrayMin( data );
			xAxisInterval = XAXISLENGTH / data[0].length;
			yAxisInterval = YAXISLENGTH / (double) (yAxisMax - yAxisMin);
			//lastx = xAxisInterval + ORIGOX; 
			lastx = ORIGOX; 
			lasty = ORIGOY - data[0][0]; 
			repaint(); 
		}
		catch( ArithmeticException ae )
		{
			yAxisMax = 10;
			yAxisMin = 0;
			xAxisInterval = XAXISLENGTH / 10;
			yAxisInterval = YAXISLENGTH / 10;
			lastx = ORIGOX;
			lasty = ORIGOY;
			System.out.println( "ArithmeticException" );
		}

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
	private int arrayMin( int[][] data )
	{
		int  min = data[0][0];
		

		for(int i = 0; i < data.length; i++)
		{
			for(int j = 0; j < data[i].length; j++ )
				min = (min < data[i][j])?min:data[i][j];
		}
		System.out.println( "arrayMin: " + min );
		return min; 
	}

	private int arrayMax( int[][] data )
	{
		
		
			int max = data[0][0];
		

		for( int i = 0; i < data.length; i++)
			for( int j = 0; j < data[i].length; j++)
				max = (max > data[i][j])?max:data[i][j]; 

		System.out.println( "arrayMax: " + max );
		return max; 
	}

	public void drawYinterval(Graphics2D g2d)
	{

		for(int i = 1; i < 10; i++)
		{

			g2d.drawLine(25, ORIGOY - (i * YAXISLENGTH / 10 ), 35,  ORIGOY - (i * YAXISLENGTH / 10 ));
			g2d.drawString("" + ( (int) (((yAxisMax - yAxisMin)/ (double) 10.0 ) * i) + yAxisMin), 0 , ORIGOY - (i * YAXISLENGTH/10));
			printDashedLine( 35, ORIGOY - (i * YAXISLENGTH / 10 ), 550, ORIGOY - (i * YAXISLENGTH / 10 ), g2d );

			System.out.println( "ymin:" + yAxisMin + ", ymax: " + yAxisMax + ", i: " + i);
			System.out.println( "" + (int) (((yAxisMax - yAxisMin)/(double) 10.0) * i));	
		}
	}
	
	public void drawXinterval(Graphics2D g2d)
	{

		for(int i = 1 ; i < data[0].length; i ++)
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
		g2d.drawLine( lastx, lasty, lastx + xAxisInterval, (int) (YAXISLENGTH - ((y - yAxisMin) * yAxisInterval) ) );

		System.out.println("x1: " + lastx +", y1: " + lasty + ", x2: " + (lastx + xAxisInterval) + ", y2: " + (YAXISLENGTH - ((y - yAxisMin) * yAxisInterval)) ); 
		System.out.println("y: " + y);

		lasty = (int) (YAXISLENGTH - ((y - yAxisMin) * yAxisInterval) );
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
		g2d.drawString(labelY, 5,15);
		g2d.drawLine(ORIGOX,ORIGOY , 550, ORIGOY);
		g2d.drawString(labelX, 560,450);

		drawXinterval(g2d);
		drawYinterval(g2d);

		drawInfo(g2d);

		// set first coordinate: 

		for( int i = 0; i<data.length; i++)
		{
			//lasty = YAXISLENGTH - ((data[0][0] - yAxisMin) * yAxisInterval);

			for( int j = 0; j<data[i].length; j++ )
			{
				g2d.setColor( nextColor()[i] );
				drawGraphCoordinates( data[i][j], g2d );
			}
			lastx = ORIGOX;
			lasty =(int) (YAXISLENGTH - ((data[i][0] - yAxisMin) * yAxisInterval)); 
		}
		lastx = ORIGOX;
		//lasty = YAXISLENGTH - ((data[0][0] - yAxisMin) * yAxisInterval); 
		lasty = YAXISLENGTH - data[0][0];


	}

	private void drawInfo( Graphics2D g2d )
	{
		int startX = 580;
		int startY = 20; 
		int linespace = 20; 

		g2d.drawString( "Intervall:", startX, startY );
		startY += linespace;
		g2d.drawString( label, startX, startY );
		startY += 3*linespace; 

		if( data.length == 5 )
		{
			g2d.setColor(nextColor()[Skicard.DAYCARD]);
			g2d.drawString( "Dagskort", startX, startY );
			startY += linespace;

			g2d.setColor(nextColor()[Skicard.HOURCARD]);
			g2d.drawString( "Timeskort", startX, startY );
			startY += linespace; 

			g2d.setColor(nextColor()[Skicard.SEASONCARD]);
			g2d.drawString( "Sesongkort", startX, startY );
			startY += linespace; 

			g2d.setColor(nextColor()[Skicard.PUNCHCARD]);
			g2d.drawString( "Klippekort", startX, startY );
			startY += linespace;

			g2d.setColor(nextColor()[4]);
			g2d.drawString( "Fysiske kort", startX, startY );

			g2d.setColor( Color.BLACK );
	
		}
	}

	private Color[] nextColor()
	{
		Color[] nxtCol = new Color[6]; 
		nxtCol[0] = Color.BLACK;
		nxtCol[1] = Color.GREEN;
		nxtCol[2] = Color.BLUE; 
		nxtCol[3] = Color.RED; 
		nxtCol[4] = Color.CYAN;
		nxtCol[5] = Color.YELLOW;

		return nxtCol; 
 	}
}