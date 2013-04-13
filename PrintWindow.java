import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrintWindow extends JFrame
{
	private JTextArea printArea;
	private JButton print;
	private Listener btnListener;

	public PrintWindow( JTextArea j )
	{
		super( "Printvindu" );

		btnListener = new Listener();
		printArea = j;

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