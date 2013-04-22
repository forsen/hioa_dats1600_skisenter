import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrintWindow extends JFrame
{

	private JPanel printArea;
	private JButton print;
	private Listener btnListener;
	private JScrollPane scroll;

	public PrintWindow( JTextArea j, double[] o, double s )
	{
		super( "Printvindu" );

		printArea = new ReceiptPainting( j, o, s ); 
	//	printArea.setPreferredSize( new Dimension(400, 700));
		btnListener = new Listener();


		print = new JButton("Print");
		print.addActionListener( btnListener );

		setLayout(new BorderLayout() );

		scroll = new JScrollPane( printArea );

		add( scroll, BorderLayout.CENTER );
		add( print, BorderLayout.PAGE_END );



		setSize( 400, 800 );
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