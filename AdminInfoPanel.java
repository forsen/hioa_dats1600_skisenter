import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class AdminInfoPanel extends JPanel
{	
	private JButton showPersons,showPersWcards, showCards;
	private JTextArea display;
	private JPanel butnPnl, dispPnl;
	private Listener listener;
	private JScrollPane scroll;
	private Personlist list;

	public AdminInfoPanel(Personlist l)
	{
		list = l;
		butnPnl = new JPanel(new GridLayout( 4,2 ));
		dispPnl = new JPanel();
		
		setLayout( new BorderLayout( 5, 5) );

		listener = new Listener();

		display = new JTextArea(20,40);
		scroll = new JScrollPane(display);

		showPersons = new JButton(" Vis Person ");
		showPersons.addActionListener( listener );
		butnPnl.add(showPersons);

		showCards = new JButton(" Vis kort ");
		showCards.addActionListener( listener );
		butnPnl.add(showCards);

		showPersWcards = new JButton(" Vis personer med kort ");
		showPersWcards.addActionListener( listener );
		butnPnl.add(showPersWcards);
		

		dispPnl.add(display);

		add(butnPnl, BorderLayout.PAGE_START);
		add(dispPnl);



	}

	private class Listener implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 	
      		
      		if(e.getSource() == showPersons)
      		{
      			
      		}

      		if(e.getSource() == showPersWcards)
      		{
      			
      		}

      		if(e.getSource() == showCards)
      		{
      			
      		}
      		
    	}
	}	
}
	