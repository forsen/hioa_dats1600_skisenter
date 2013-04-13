import java.awt.*;
import javax.swing.*;
import java.util.Date;

public class ReceiptPainting extends JPanel
{
	private JTextArea printItems;
	private double sum;
	private int MARGIN = 10;
	private int YSTART = 50;
	private int LINESPACE = 20; 
	private int currentY = YSTART; 

	public ReceiptPainting( JTextArea p, double s )
	{
		sum = s;
		printItems = p;
		setBackground( Color.WHITE );
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(350,600);
	}
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );

		Graphics2D g2d = (Graphics2D) g;

		printCenteredString( "Offpist skisenter", 350, 0, currentY, g2d );
		currentY += LINESPACE; 
		printCenteredString( "ORG.NR. 123 456 789 MVA", 350, 0, currentY, g2d );
		currentY += LINESPACE; 
		printCenteredString( "Granskogen 1", 350, 0, currentY, g2d );
		currentY += LINESPACE;
		printCenteredString( "TLF: 22 33 44 55", 350, 0, currentY, g2d );
		currentY += LINESPACE;
		printDashedLine( MARGIN, 330, currentY, g2d );
		currentY += LINESPACE;
		g2d.drawString( new Date().toString(), MARGIN, currentY);
		currentY += LINESPACE;
		printDashedLine( MARGIN, 330, currentY, g2d );
		currentY += LINESPACE;

		String[] items = areaToString(); 

		for( int i = 1; i < items.length; i++ )
		{
			g2d.drawString( items[i], MARGIN, currentY);
			currentY += LINESPACE;

		}

		printDashedLine( MARGIN, 330, currentY, g2d );
		currentY += LINESPACE;
		g2d.drawString( "Total eks. moms", MARGIN, currentY );
		printRightAlignedString( "" + sum*0.8 , 350, 0, currentY, g2d );
		currentY += LINESPACE; 
		g2d.drawString( "Moms 25%", MARGIN, currentY );
		printRightAlignedString( "" + (sum - (sum*0.8)), 350, 0, currentY, g2d );
		currentY += LINESPACE;
		printDashedLine( MARGIN, 330, currentY, g2d );
		currentY += LINESPACE;

		Font orig = g2d.getFont();
		Font f = orig;
		g2d.setFont( f.deriveFont( Font.BOLD, 16F ) );

		g2d.drawString( "Total", MARGIN, currentY );
		printRightAlignedString( "" + sum, 350, 0, currentY, g2d );

	}

	private String[] areaToString()
	{
		int lines = printItems.getLineCount();
		String[] strings = new String[ lines ];
		String allLines = printItems.getText();

		strings = allLines.split( "\n" );
		
		return strings;
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

	private void printRightAlignedString( String s, int width, int xPos, int yPos, Graphics g2d )
	{
		int stringLength = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();

		int start = width - stringLength - MARGIN; 

		g2d.drawString(s, start + xPos, yPos );
	}
}