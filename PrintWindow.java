import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrintWindow extends JFrame
{

	private JPanel printArea;
	private JButton print;
	private Listener btnListener;

	public PrintWindow( JTextArea j, double[] o, double s )
	{
		super( "Printvindu" );

		printArea = new ReceiptPainting( j, o, s ); 
		btnListener = new Listener();


		print = new JButton("Print");
		print.addActionListener( btnListener );

		setLayout(new FlowLayout() );

		add( printArea );
		add( print );



		setSize( 500, 800 );
		setVisible( true );
	}

	private class Listener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{

			PrintUtilities.printComponent( printArea );
		}
	}

}