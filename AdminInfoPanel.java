import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;


public class AdminInfoPanel extends JPanel
{	
	private JTextField crdNr;
	private JButton findPerson, showPersons,showPersWcards, showCards, showPassings;
	private JTextArea display;
	private JPanel butnPnl, dispPnl;
	private Listener listener;
	private JScrollPane scroll;
	private Personlist list;



	public AdminInfoPanel(Personlist l )
	{
		list = l;
	
		butnPnl = new JPanel(new GridLayout( 4,2 ));
		dispPnl = new JPanel();
		
		setLayout( new BorderLayout( 5, 5) );

		listener = new Listener();

		display = new JTextArea(20,40);
		scroll = new JScrollPane(display);

		butnPnl.add( new JLabel( "Kortnr" ) );
		crdNr = new JTextField(5);
		crdNr.setEditable( true );
		butnPnl.add(crdNr);

		findPerson = new JButton(" Finn Person ");
		findPerson.addActionListener( listener );
		butnPnl.add(findPerson);

		showPersons = new JButton(" Vis Personliste ");
		showPersons.addActionListener( listener );
		butnPnl.add(showPersons);

		showCards = new JButton(" Vis kort ");
		showCards.addActionListener( listener );
		butnPnl.add(showCards);

		showPersWcards = new JButton(" Vis personer med kort ");
		showPersWcards.addActionListener( listener );
		butnPnl.add(showPersWcards);

		showPassings = new JButton(" Vis heis passeringer ");
		showPassings.addActionListener( listener );
		butnPnl.add(showPassings);
		

		dispPnl.add(display);

		add(butnPnl, BorderLayout.PAGE_START);
		add(dispPnl);



	}

	public void findPerson()
	{
		try
		{
			int cardnr = Integer.parseInt(crdNr.getText());
			Person p = list.findPersonByCard(cardnr);
			display.setText(p.toString());
		}
		catch(NullPointerException npe)
		{
			display.setText("Fant ikke personen daa");
		}
		
	} 

	public void showPersons()
	{
		display.setText(list.personListe());
	}

	public void showPersonsWithcards()
	{
		list.sort();
		display.setText(list.toString());
	}

	public void showCards()
	{
		display.setText("Her kommer det en skikortliste etterhvert");
	}

	public void showPassings()
	{
		display.setText("Her kommer det en liste over heipasseringer etterhvert");
	}


	private class Listener implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 	
      		
      		if(e.getSource() == findPerson)
      		{
      			findPerson();
      		}
      		if(e.getSource() == showPersons)
      		{
      			showPersons();
      		}

      		if(e.getSource() == showPersWcards)
      		{
      			showPersonsWithcards();
      		}

      		if(e.getSource() == showCards)
      		{
      			showCards();
      		}

      		if(e.getSource() == showPassings)
      		{
      			showPassings();
      		}
      		
    	}
	}	
}
	