import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrintWindow extends JDialog
{

	private JPanel printArea;
	private JButton print;
	private Listener btnListener;
	private JScrollPane scroll;

//	public static final RECEIPT = 1;
//	public static final CARD = 2; 


	public PrintWindow( Window parent )
	{
		super( parent, "Printvindu" );


		btnListener = new Listener();

		print = new JButton( "Print" );
		print.addActionListener( btnListener );
		setLayout( new BorderLayout() );
		add( print, BorderLayout.PAGE_END );

		//setSize( 400,800 );
		setVisible( true ); 
	}

	public PrintWindow( Window parent, JTextArea j, double[] o, double s )
	{
		this( parent ); 
		printArea = new ReceiptPainting( j, o, s ); 
		printArea.setPreferredSize( new Dimension(350, 450 + ( j.getLineCount() * 20 ) ) );

		scroll = new JScrollPane( printArea );

		add( scroll, BorderLayout.CENTER );
		setSize( 400,800 );
//		add( print, BorderLayout.PAGE_END );



//		setSize( 400, 800 );
//		setVisible( true );
	}

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