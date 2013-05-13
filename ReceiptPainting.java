import java.awt.*;
import javax.swing.*;
import java.util.Date;
import java.util.regex.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Locale;
import java.text.NumberFormat;

/**
  * This is a class to paint the receipt on a JPanel
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

public class ReceiptPainting extends JPanel
{
	private JTextArea printItems;
	private double[] payments;
	private double sum;
	private BufferedImage img;
	private int MARGIN = 10;
	private int YSTART = 120;
	private int LINESPACE = 20; 
	private int WIDTH = 350;
	private int currentY = YSTART;
	private int size;  
	private NumberFormat paymentFormat;

/**
  * This constructor receives the necessary data to paint the receipt. It will also try to load our logo.
  * @param p 	The textarea containing all items in the order
  * @param o 	A double array containing the payments made. Index tells if it's paid
  * 			with card or cash, value tells how much is paid.
  * @param s 	A double containing the sum of the order. 
  * @see CashRegister#CASH
  * @see CashRegister#CARD
  * @see PrintWindow
  */
	public ReceiptPainting( JTextArea p, double[] o, double s )
	{
		sum = s;
		printItems = p;
		payments = o; 
		setBackground( Color.WHITE );
		try
		{
			img = ImageIO.read( new File("img/offpist_kvittering.png"));
			size = img.getWidth();
		}
		catch( IOException ioe )
		{
			System.out.println( "couldn't load the image" );
		}

		paymentFormat = NumberFormat.getCurrencyInstance( new Locale( "no", "NO" ) );


	}

/**
  * Method to calculate the height. Necessary to make sure the entire receipt is visible, no matter how many items is on the list.
  * @return Returns an integer containing the desired height in pixels
  */
	public int calculateHeight()
	{
		return 500 + (areaToString().length * LINESPACE);
	}

/**
  * Method to set the preferredsize of the receipt. Necessary to make sure the entire receipt is visible.
  * @return Returns a suiting Dimension for our receipt.
  */
	public Dimension setPreferredSize()
	{
		return new Dimension(WIDTH,calculateHeight());
	}

/**
  * Method that draws the receipt. 
  */
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );

		Graphics2D g2d = (Graphics2D) g;

		


		g2d.drawImage( img, (WIDTH/2 - size/2), 10, null );

		printCenteredString( "Offpist Skiresort", WIDTH, 0, currentY, g2d );
		currentY += LINESPACE; 
		printCenteredString( "ORG.NR. 123 456 789 MVA", WIDTH, 0, currentY, g2d );
		currentY += LINESPACE; 
		printCenteredString( "Granskogen 1", WIDTH, 0, currentY, g2d );
		currentY += LINESPACE;
		printCenteredString( "TLF: 22 33 44 55", WIDTH, 0, currentY, g2d );
		currentY += LINESPACE;
		printDashedLine( MARGIN, WIDTH - MARGIN, currentY, g2d );
		currentY += LINESPACE;
		g2d.drawString( new Date().toString(), MARGIN, currentY);
		currentY += 0.5*LINESPACE;
		printDashedLine( MARGIN, WIDTH - MARGIN, currentY, g2d );
		currentY += LINESPACE;

		String[] items = getStrings(); 
		String[] amounts = getAmounts();
		for( int i = 1; i < items.length; i++ )
		{
			g2d.drawString( items[i], MARGIN, currentY);
			printRightAlignedString( amounts[i], WIDTH, 0, currentY, g2d);
			currentY += LINESPACE;

		}
		currentY -= 0.5*LINESPACE;
		printDashedLine( MARGIN, WIDTH - MARGIN, currentY, g2d );
		currentY += LINESPACE;
		g2d.drawString( "Total eks. moms", MARGIN, currentY );
		printRightAlignedString( paymentFormat.format( sum*0.8 ) , WIDTH, 0, currentY, g2d );
		currentY += LINESPACE; 
		g2d.drawString( "Moms 25%", MARGIN, currentY );
		printRightAlignedString( paymentFormat.format(sum - (sum*0.8)), WIDTH, 0, currentY, g2d );
		currentY += 0.5*LINESPACE;
		printDashedLine( MARGIN, WIDTH - MARGIN, currentY, g2d );
		currentY += LINESPACE;

		Font orig = g2d.getFont();
		Font f = orig;
		g2d.setFont( f.deriveFont( Font.BOLD, 16F ) );

		g2d.drawString( "Total", MARGIN, currentY );
		printRightAlignedString( paymentFormat.format( sum ), WIDTH, 0, currentY, g2d );
		currentY += 2*LINESPACE;

		g2d.setFont( orig );

		if( payments[CashRegister.CASH] != 0 )
		{
			g2d.drawString("Mottat kontant: ", MARGIN, currentY);
			printRightAlignedString( paymentFormat.format( payments[CashRegister.CASH] ), WIDTH, 0, currentY, g2d );
			currentY += LINESPACE; 
		}
		if( payments[CashRegister.CARD] != 0 )
		{
			g2d.drawString("Mottat med kort: ", MARGIN, currentY);
			printRightAlignedString( paymentFormat.format( payments[CashRegister.CARD] ), WIDTH, 0, currentY, g2d );
			currentY += LINESPACE; 
		}
		if( payments[CashRegister.CASH] + payments[CashRegister.CARD] > sum )
		{
			g2d.drawString("Tilbake: ", MARGIN, currentY );
			printRightAlignedString( paymentFormat.format(sum - (payments[CashRegister.CASH] + payments[CashRegister.CARD])), WIDTH, 0, currentY, g2d );
			currentY += LINESPACE;
		}

		currentY += 3*LINESPACE;

		printCenteredString( "Nyttige telefonnr (i prioritert rekkefølge)", WIDTH, 0, currentY, g2d );
		currentY += LINESPACE;
		printCenteredString( "AfterSki: 22 33 44 55", WIDTH, 0, currentY, g2d );
		currentY += LINESPACE;
		printCenteredString("Ambulansehelikopter: 113", WIDTH, 0, currentY, g2d );

		currentY = YSTART; 


	}

/**
  * A method to split the TextArea into a String Array. It splits the text each time it sees a newline character "\n"
  * @return Returns a string array containing all the original text.
  */
	private String[] areaToString()
	{
		int lines = printItems.getLineCount();
		String[] strings = new String[ lines ];
		String allLines = printItems.getText();

		strings = allLines.split( "\n" );


		
		return strings;
	}

/**
  * A method to retrieve the individual amounts on each items from the String array containing both text and amounts. The following pattern is used for the split:
  * (\\Dkr\\s\\d*,\\d{2})
  * @return Returns a String array containing one element per item, where the value is the price tag of the item.
  * @see areaToString()
  * @see getStrings()
  */
	private String[] getAmounts()
	{
		String[] strings = areaToString();
		String[] amounts = new String[ strings.length];
		//Pattern pattern = Pattern.compile("(\\d{2,},-)");
		Pattern pattern = Pattern.compile("(\\Dkr\\s\\d*,\\d{2})");
		
		Matcher matcher;

		for(int i = 0; i < strings.length; i++)
		{
			String amount = "";
			matcher = pattern.matcher(strings[i]);
			while( matcher.find() )
			{
				amounts[i] = matcher.group(1);
			}

			if( amounts[i] == null )
			{
				amounts[i] = "";
			}

		}

		return amounts;

	}

/**
  * A method to retrieve the item names from a string array, leaving out the price tags. The following pattern is used when splitting:
  * (\\Dkr\\s\\d*,\\d{2})
  * @see getAmounts()
  * @see areaToString()
  * @return A String array containing the item names.
  */
	private String[] getStrings()
	{
		String[] strings = areaToString();
		//Pattern pattern = Pattern.compile("(\\d{2,},-)");
		Pattern pattern = Pattern.compile("(\\Dkr*\\s\\d*,\\d{2})");

		Matcher matcher; 

		for( int i = 0; i < strings.length; i++)
		{
			String amount = "";
			matcher = pattern.matcher(strings[i]);
			while( matcher.find() )
			{
				try
				{
					strings[i] = strings[i].substring(0, matcher.start());
					System.out.println( strings[i] );
				}
				catch( NullPointerException npe )
				{
					System.out.println("Didn't get in atring");
				}
			}
		}

		return strings;
	}

/**
  * A method to draw a horizontal dashed line. 
  * @param x1Pos 	the x-position where the line should start
  * @param x2Pos 	the x-position where the line should end
  * @param yPos		the y-position of the horizontal line
  * @param g2d 		The Graphics2D object used to draw
  */
	private void printDashedLine( int x1Pos, int x2Pos, int yPos, Graphics2D g2d )
	{
		float[] dashPattern = {6, 3, 6, 3};
		g2d.setStroke( new BasicStroke( 1, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10, dashPattern, 0 ));

		g2d.drawLine(x1Pos, yPos, x2Pos, yPos);
	}

/**
  * A method to draw a centered string. It takes the print area width, and the length of the String into account when calculating where to start.
  * @param s 	The String to be centered
  * @param width 	The width of the print area
  * @param xPos 	The left threshold of the print area
  * @param yPos 	The y-position where you want the String
  * @param g2d 		The Graphics2D object used to draw
  */
	private void printCenteredString( String s, int width, int xPos, int yPos, Graphics2D g2d)
	{
		int stringLength = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();

		int start = width/2 - stringLength/2;

		g2d.drawString(s, start + xPos, yPos );
	}

/**
  * A method to draw a right aligned String. It takes the print area width, and the length of the String into account when calculating where to start.
  * @param s 	The String to be centered
  * @param width 	The width of the print area
  * @param xPos 	The left threshold of the print area
  * @param yPos 	The y-position where you want the String
  * @param g2d 		The Graphics2D object used to draw
  */
	private void printRightAlignedString( String s, int width, int xPos, int yPos, Graphics2D g2d )
	{
		int stringLength = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();

		int start = width - stringLength - MARGIN; 

		g2d.drawString(s, start + xPos, yPos );
	}
}