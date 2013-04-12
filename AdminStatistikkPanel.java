import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class AdminStatistikkPanel extends JPanel
{	
	private JTextField fromFld, toFld;
	private JButton calculateBtn;
	private JTextArea display;
	private JPanel choicePnl, dispPnl;
	private Listener listener;
	private JScrollPane scroll;
	private Personlist list;

	public AdminStatistikkPanel(Personlist l )
	{
		list = l;
	
		choicePnl = new JPanel(new GridLayout( 5,4 ));
		dispPnl = new JPanel();
		
		setLayout( new BorderLayout( 5, 5) );

		listener = new Listener();

		choicePnl.add( new JLabel( "Fra: " ) );
		fromFld = new JTextField(4);
		fromFld.setEditable( false );
		choicePnl.add(fromFld);

		choicePnl.add( new JLabel( "Til: " ) );
		toFld = new JTextField(4);
		toFld.setEditable( false );
		choicePnl.add(toFld);

		display = new JTextArea(20,40);
		scroll = new JScrollPane(display);

		calculateBtn = new JButton(" Beregn ");
		calculateBtn.addActionListener( listener );
		choicePnl.add(calculateBtn);


		dispPnl.add(display);

		add(choicePnl, BorderLayout.PAGE_START);
		add(dispPnl);

	}

	private class Listener implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 	
      		
      		if(e.getSource() == calculateBtn)
      		{
      			display.setText("Her skal det beregnes det noe ");
      		}
	
    	}
	}	
}
	
