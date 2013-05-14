package skisenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
  * This class sets up a JDialog window to put the painted
  * Skicard or Receipt on.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

public class PrintWindow extends JDialog
{

	private JPanel printArea;
	private JButton print;
	private Listener btnListener;
	private JScrollPane scroll;

/**
  * This is the default constructor. It will set up the elements and layout.
  * @param parent This is the parent window this JDialog belongs to.
  */
	public PrintWindow( Window parent )
	{
		super( parent, "Printvindu" );


		btnListener = new Listener();

		print = new JButton( "Print" );
		print.addActionListener( btnListener );
		setLayout( new BorderLayout() );
		add( print, BorderLayout.PAGE_END );

		setVisible( true ); 
	}
/**
  * This is the constructor to set up the window for the receipt. 
  * It will receive the necessary data, and create a ReceiptPainting object
  * and put it on screen.
  * @param parent 	The parent window this JDialog belongs to
  * @param j 	The textarea where all the items to be printed on the receipt is
  * @param o 	A double array containing the payments made. Index tells if it's paid
  * 			with card or cash, value tells how much is paid.
  * @param s 	A double containing the sum of the order. 
  * @see CashRegister#CASH
  * @see CashRegister#CARD
  * @see ReceiptPainting
  */
	public PrintWindow( Window parent, JTextArea j, double[] o, double s )
	{
		this( parent ); 
		printArea = new ReceiptPainting( j, o, s ); 
		printArea.setPreferredSize( new Dimension(350, 450 + ( j.getLineCount() * 20 ) ) );

		scroll = new JScrollPane( printArea );

		add( scroll, BorderLayout.CENTER );
		setSize( 400,800 );

	}

/**
  * This is the constructor to set up the window for the card.
  * It will receive the necessary data, and create a CardPainting object
  * and put it on screen.
  * @param parent 	The parent window this JDialog belongs to
  * @param c 		The card to be printed
  * @see CardPainting
  */
	public PrintWindow( Window parent, Card c )
	{
		this( parent ); 
		printArea = new CardPainting( c );

		scroll = new JScrollPane( printArea );

		setSize( 500,750 );

		add( scroll, BorderLayout.CENTER ); 
	}



	private class Listener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{

			PrintUtilities.printComponent( printArea );
		}
	}

}