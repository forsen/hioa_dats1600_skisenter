package skisenter;

import java.awt.*;
import javax.swing.*;
import java.util.Date;
import java.util.regex.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.geom.RoundRectangle2D; 
import java.text.NumberFormat;
import java.util.Locale;
import java.text.SimpleDateFormat;

/**
  * Class to paint Skicards. Will put the resulting painting in a JPanel.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  * @see PrintWindow#PrintWindow( Window parent, Card c )
  */
public class CardPainting extends JPanel
{
	private BufferedImage img;
	private BufferedImage persImg; 
	private int size;
	private Card printedCard;
	private Skicard printedSkicard; 
	private String type;
	private String ageGroup;
	private String price; 
	private String cardNr; 
	private String customerID; 
	private String purchaseDate; 
	private NumberFormat paymentFormatter;
	private SimpleDateFormat dateFormatter;

// CONSTANTS
	private final int MARGIN = 10;
	private final int YSTART = 120;
	private final int LINESPACE = 20; 
	private final int WIDTH = 495;
	private final int BIGFONT = 30;
	private final int SMALLFONT = 10;
	private final int ROUNDEDCORNER = 15;

// END CONSTANTS

	private int currentY = YSTART;

/**
  * This constructor gather the necessary data to paint this Skicard. It will
  * try to load the customers photo image, if failing, it will load our default
  * photo image for the print. 
  * It will also prepare the data for painting.
  * @param c 	The card to be painted
  */
	public CardPainting( Card c )
	{
		setBackground( Color.WHITE );

		paymentFormatter = NumberFormat.getCurrencyInstance( new Locale( "no", "NO" ) );
		dateFormatter = new SimpleDateFormat("dd.MM.yy HH:mm");

		printedCard = c;

		cardNr = "Kortnr: " + printedCard.getCardID();

		try
		{
			printedSkicard = printedCard.getCurrent(); 
			type = printedSkicard.getType("");
			switch (printedSkicard.getAgeGroup())
			{
				case 1: 
					ageGroup = "BARN";
					break;
				case 2:
					ageGroup = "VOKSEN";
					break;
			}
			price = "Pris: " + paymentFormatter.format(printedSkicard.getPrice()) ; 
			purchaseDate = "Dato: " + dateFormatter.format(printedSkicard.getBought()); 
		}
		catch( NullPointerException npe )
		{
			type = "";
			ageGroup = "";
			price = "";
			purchaseDate = "Dato: " + dateFormatter.format( new Date() ); 
		}

		try 
		{
			customerID = "Kundenr: " + Salesclerk.customer.getCustId(); 
		}
		catch( NullPointerException npe )
		{
			customerID = ""; 
		}
		try
		{
			
			persImg = ImageIO.read( Salesclerk.customer.getImage() );

		}
		catch( NullPointerException npe )
		{
			try
			{
				persImg = ImageIO.read( getClass().getResource("persImg/default.png"));
			}
			catch( IOException ioe2)
			{
				ioe2.printStackTrace( System.out );
				System.out.println("Couldn't find default.png");

			}			
		}
		catch( IOException ioe )
		{
			try
			{
				persImg = ImageIO.read( getClass().getResource("persImg/default.png"));
			}
			catch( IOException ioe2)
			{
				ioe2.printStackTrace( System.out );
				System.out.println("Couldn't find default.png");
			}
		}
		catch( IllegalArgumentException iae )
		{
			try
			{
				persImg = ImageIO.read( getClass().getResource("persImg/default.png"));
			}
			catch( IOException ioe )
			{
				ioe.printStackTrace(System.out);
				System.out.println("Couldn't find default.png");
			}
		}

		try
		{
			img = ImageIO.read( getClass().getResource("img/skicard.png"));
			size = img.getWidth();
		}
		catch( IOException ioe )
		{
			ioe.printStackTrace( System.out );
			System.out.println( "Couldn't load the image" );
		}



	}

/**
  * This method will do the actual painting. 
  * @param g 	The graphics object to be used for painting.
  */
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );

		Graphics2D g2d = (Graphics2D) g;


		g2d.setFont( new Font( "Arial", Font.PLAIN, BIGFONT ));

		

		g2d.setColor( new Color( 89, 137, 235 ) );
		g2d.fillRoundRect(MARGIN, MARGIN, WIDTH - 2*MARGIN, 50, ROUNDEDCORNER, ROUNDEDCORNER);
		g2d.fillRect(MARGIN,30,WIDTH - 2*MARGIN,440);

		g2d.setColor( new Color(233,233,233) );
		g2d.fillRect(MARGIN,470,WIDTH - 2*MARGIN,160);


		g2d.drawImage( img, (WIDTH/2 - size/2), 2*MARGIN, null );

		g2d.setColor( Color.BLACK );		

		g2d.drawString( type, 50, 505 );
		g2d.drawString( ageGroup, 50, 550 );
		g2d.drawString( price, 50, 595 );

		g2d.drawLine( MARGIN, 630, WIDTH - MARGIN, 630);



		g2d.drawImage( persImg, 350, 480, null );

		g2d.setFont( new Font( "Arial", Font.PLAIN, SMALLFONT ) );

		g2d.drawString( cardNr, 20, 645 );
		printCenteredString( purchaseDate, WIDTH, 0, 645, g2d );
		printRightAlignedString( customerID, WIDTH, 0, 645, g2d );

		g2d.drawRoundRect(MARGIN,MARGIN,WIDTH - 2*MARGIN,640, ROUNDEDCORNER,ROUNDEDCORNER); 

	}

/**
  * This method will print a horizontal dashed line between the given coordinates.
  * @param x1Pos 	the x-position to start the line from
  * @param x2Pos	the x-position where the line ends
  * @param yPos		the y-position, where to place the horizontal line
  */
	private void printDashedLine( int x1Pos, int x2Pos, int yPos, Graphics2D g2d )
	{
		float[] dashPattern = {6, 3, 6, 3};
		g2d.setStroke( new BasicStroke( 1, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10, dashPattern, 0 ));

		g2d.drawLine(x1Pos, yPos, x2Pos, yPos);
	}

/**
  * This method prints a centered string. It calculates the length of the String, and 
  * place it in the middle of the area.
  * @param s 	the String to be drawn
  * @param width 	the width of the area you want to have the centered String in
  * @param xPos 	the x-position where the area starts (left side of your area)
  * @param yPos 	the y-position where you want to place the String
  */
	private void printCenteredString( String s, int width, int xPos, int yPos, Graphics2D g2d)
	{
		int stringLength = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();

		int start = width/2 - stringLength/2;

		g2d.drawString(s, start + xPos, yPos );
	}

/**
  * This method prints a right aligned string. It calculates the length of the String, and
  * place it right aligned based on that. 
  * @param s 	the String to be drawn
  * @param width 	the width of the area where you want your string to be right aligned
  * @param xPos 	the x-position where the area starts (left side of your area)
  * @param yPos 	the y-position where you want to place the String
  */
	private void printRightAlignedString( String s, int width, int xPos, int yPos, Graphics2D g2d )
	{
		int stringLength = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();

		int start = width - stringLength - MARGIN*2; 

		g2d.drawString(s, start + xPos, yPos );
	}
}