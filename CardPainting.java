import java.awt.*;
import javax.swing.*;
import java.util.Date;
import java.util.regex.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.geom.RoundRectangle2D; 


public class CardPainting extends JPanel
{
	private BufferedImage img;
	private int MARGIN = 10;
	private int YSTART = 120;
	private int LINESPACE = 20; 
	private int WIDTH = 495;
	private int currentY = YSTART;
	private int size;  


	public CardPainting( )
	{
		setBackground( Color.WHITE );
		try
		{
			img = ImageIO.read( new File("img/skicard.png"));
			size = img.getWidth();
		}
		catch( IOException ioe )
		{
			System.out.println( "Fikk ikke lastet bildet" );
		}


	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );

		Graphics2D g2d = (Graphics2D) g;

		

//		g2d.drawRect(9, 9, 476, 640);



		g2d.setColor( new Color( 89, 137, 235 ) );
		g2d.fillRoundRect(10, 10, 475, 50, 15, 15);
		g2d.fillRect(10,30,475,440);

		g2d.setColor( new Color(233,233,233) );
		g2d.fillRect(10,470,475,150);
		g2d.fillRoundRect( 10,610, 475, 40, 15, 15);

		g2d.setColor( Color.BLACK );
		g2d.drawRoundRect(10,10,475,640, 15,15); 
		
		g2d.drawImage( img, (WIDTH/2 - size/2), 20, null );

		

	}


	private void printDashedLine( int x1Pos, int x2Pos, int yPos, Graphics2D g2d )
	{
		float[] dashPattern = {6, 3, 6, 3};
		g2d.setStroke( new BasicStroke( 1, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10, dashPattern, 0 ));

		g2d.drawLine(x1Pos, yPos, x2Pos, yPos);
	}

	private void printCenteredString( String s, int width, int xPos, int yPos, Graphics2D g2d)
	{
		int stringLength = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();

		int start = width/2 - stringLength/2;

		g2d.drawString(s, start + xPos, yPos );
	}

	private void printRightAlignedString( String s, int width, int xPos, int yPos, Graphics2D g2d )
	{
		int stringLength = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();

		int start = width - stringLength - MARGIN; 

		g2d.drawString(s, start + xPos, yPos );
	}
}