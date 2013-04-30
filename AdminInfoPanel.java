
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
	private JTable perstable, passTable;
	


	public AdminInfoPanel(Personlist l,List<Validations> v, Cardlist cr )
	{
		
		list = l;
		validations = v;
		cardregistry = cr;

		fieldPnl = new JPanel(new GridLayout( 2,3 ));
		butnPnl = new JPanel(new GridLayout( 2,3 ));
		dispPnl = new JPanel();
	
		setBackground(new Color(200, 230, 255));
		fieldPnl.setBackground(new Color(200, 230, 255));
		butnPnl.setBackground(new Color(200, 230, 255));
		dispPnl.setBackground(new Color(200, 230, 255));
		
		setLayout( new BorderLayout( 5, 3) );

		listener = new Listener();


		perstable = list.personTable();
		passTable = showPassings();


		display = new JTextArea(20,40);
		display.setEditable(false);

		scroll = new JScrollPane(display);
		dispPnl.add(scroll);
		dispPnl.setSize(400,500);
		
		
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
		

		dispPnl.add(scroll);
		

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


	public void showPersonsWithcards()
	{
		list.sort();
		display.setText(list.toString());
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

	public JTable showPassings()
	{
		
		String[] columnName = {"HeisNr", "KortNr", "KortType", "Passerings tid"};
		Object[][] passings = new Object[validations.size() ][4]; 
		Iterator<Validations> it = validations.iterator();

		for (int i = 1; i < validations.size(); i++ )
		{
			Validations runner = it.next();

			passings[i][0] = runner.getLiftId();
			passings[i][1] = runner.getCard();
			passings[i][2] = runner.getCard().getCurrent().getType();
			passings[i][3] = runner.getDate();

		}
		passTable = new JTable(passings,columnName);
		passTable.setEnabled(false);
		System.out.println("Du har opprettet tabellen");
		return passTable;
		
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

      			
      			scroll.setViewportView(perstable);


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

      			scroll.setViewportView(passTable);
      		
      		}

      		if(e.getSource() == deletePersBtn)
      		{
      			deletePerson();
      			scroll.setViewportView(display);
      		}

      		
    	}
	}	
}
	