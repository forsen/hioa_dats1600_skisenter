
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Iterator;

public class AdminInfoPanel extends JPanel
{	
	private JTextField crdNr, tlfNr;
	private JButton deletePersBtn,findPerson, showPersons,showPersWcards, showCards, showPassings;
	private JTextArea display;
	private JPanel fieldPnl, butnPnl, dispPnl;
	private Listener listener;
	private JScrollPane scroll;
	private Personlist list;
	private List<Validations> validations;
	private Cardlist cardregistry;
	private JTable table;
	

	public AdminInfoPanel(Personlist l,List<Validations> v, Cardlist cr )
	{
		
		setBackground(new Color(220, 240, 255));
		list = l;
		validations = v;
		cardregistry = cr;

		fieldPnl = new JPanel(new GridLayout( 2,3 ));
		fieldPnl.setBackground(new Color(220, 240, 255));
		butnPnl = new JPanel(new GridLayout(2,2));
		butnPnl.setBackground(new Color(220, 240, 255));
		dispPnl = new JPanel(new BorderLayout());
		dispPnl.setBackground(new Color(220, 240, 255));
	
		
		setLayout( new BorderLayout( 5, 5) );

		listener = new Listener();

		table = list.personTable();
		scroll = new JScrollPane(table);
		dispPnl.add(scroll);
		dispPnl.setSize(400,500);
		

		display = new JTextArea(20,40);
		display.setEditable(false);
		
		fieldPnl.add( new JLabel( "Kortnr" ) );
		crdNr = new JTextField(5);
		crdNr.setEditable( true );
		fieldPnl.add(crdNr);

		findPerson = new JButton(" Finn Person ");
		findPerson.addActionListener( listener );
		fieldPnl.add(findPerson);

		fieldPnl.add( new JLabel( "Tlfnr" ) );
		tlfNr = new JTextField(5);
		tlfNr.setEditable( true );
		fieldPnl.add(tlfNr);

		deletePersBtn = new JButton(" Slett Person ");
		deletePersBtn.addActionListener( listener );
		fieldPnl.add(deletePersBtn);

		showPersons = new JButton(" Vis Personliste ");
		showPersons.addActionListener( listener );
		butnPnl.add(showPersons);

		showCards = new JButton(" Vis uregistrerte kort ");
		showCards.addActionListener( listener );
		butnPnl.add(showCards);

		showPersWcards = new JButton(" Vis personer med kort ");
		showPersWcards.addActionListener( listener );
		butnPnl.add(showPersWcards);

		showPassings = new JButton(" Vis heis passeringer ");
		showPassings.addActionListener( listener );
		butnPnl.add(showPassings);
		

		//dispPnl.add(scroll);
		

		add(fieldPnl, BorderLayout.PAGE_START);
		add(butnPnl, BorderLayout.CENTER);
		add(dispPnl, BorderLayout.PAGE_END);
		


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
			display.setText("Fant ikke eieren til kortet");


		}
		
	} 

	public void showPersons()
	{
		
		display.setText(list.personListe());
	}


	public void personList()
	{
		

		
	}

	public void showPersonsWithcards()
	{
		list.sort();
		display.setText(list.toString());
		dispPnl.add(scroll);
	
	}

		
	public void showCards()
	{
		display.setText(cardregistry.toString());
	}
	
	
	public void deletePerson()
	{
		try
		{
			int tlfnr = Integer.parseInt(tlfNr.getText());
			Person p = list.deletePerson((list.findPerson(tlfnr)));
			display.setText(p.getFirstName() +" "+ p.getLastName()+ " er nå slettet fra systemet");
			tlfNr.setText("");

		}
		catch(NullPointerException npe)
		{

		}


	}

	public void showPassings()
	{
		
		Iterator<Validations> it = validations.iterator();
		StringBuilder text = new StringBuilder();

		while( it.hasNext() )
		{
			text.append( it.next().toString() );
		}
		display.setText( text.toString() );
	}	

	


	private class Listener implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 	
      		
      		if(e.getSource() == findPerson)
      		{
      			findPerson();
      			scroll.setViewportView(display);
      		}
      		if(e.getSource() == showPersons)
      		{
      			personList();
      			scroll.setViewportView(table);
      		}

      		if(e.getSource() == showPersWcards)
      		{
      			showPersonsWithcards();
      			scroll.setViewportView(display);
      		}

      		if(e.getSource() == showCards)
      		{
      			showCards();
      			scroll.setViewportView(display);
      		}

      		if(e.getSource() == showPassings)
      		{
      			showPassings();
      			scroll.setViewportView(display);
      		}

      		if(e.getSource() == deletePersBtn)
      		{
      			deletePerson();
      			scroll.setViewportView(display);
      		}

      		
    	}
	}


}
	