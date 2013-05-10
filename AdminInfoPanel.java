
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AdminInfoPanel extends JPanel
{	
	private JTextField crdNr, tlfNr;
	private JButton deletePersBtn,findPerson, showPersons,showPersWcards, showCards, showPassings;
	private JTextArea display;
	private JPanel fieldPnl, butnPnl, dispPnl;
	private Listener listener;
	private JScrollPane scroll, listscroll;
	private Personlist list;
	private List<Validations> validations;
	private Cardlist cardregistry;
	private JTable perstable, passTable;
	private Toolkit toolbox;
	private JList<Person> reg;
	private DefaultListModel<Person> model;
	

 	


	public AdminInfoPanel(Personlist l,List<Validations> v, Cardlist cr )
	{
		Border etched = BorderFactory.createEtchedBorder();
		toolbox = Toolkit.getDefaultToolkit();

		list = l;
		validations = v;
		cardregistry = cr;

		fieldPnl = new JPanel(new GridLayout( 2,3 ));
		butnPnl = new JPanel(new GridLayout( 2,2 ));
		dispPnl = new JPanel();
		dispPnl.setLayout(new BorderLayout());
	
		setBackground(new Color(200, 230, 255));
		fieldPnl.setBackground(new Color(200, 230, 255));
		butnPnl.setBackground(new Color(200, 230, 255));
		dispPnl.setBackground(new Color(200, 230, 255));
		

		listener = new Listener();
		reg = null;

		showpersons();
		showpassings();


		display = new JTextArea(1,65);
		display.setEditable(false);

		scroll = new JScrollPane(display);
		scroll.setPreferredSize(new Dimension(780, 50));
		dispPnl.add(scroll, BorderLayout.CENTER);
		
		
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
		showPersons.setToolTipText("Vis en liste over alle registrerte personer ");
		butnPnl.add(showPersons);

		showCards = new JButton(" Vis uregistrerte kort ");
		showCards.addActionListener( listener );
		showCards.setToolTipText("Vis en liste over alle uregistrerte kort");
		butnPnl.add(showCards);

		showPersWcards = new JButton(" Vis personer med kort ");
		showPersWcards.addActionListener( listener );
		showPersWcards.setToolTipText("Vis en liste over alle registrerte personer og alle deres kort");
		butnPnl.add(showPersWcards);

		showPassings = new JButton(" Vis heis passeringer ");
		showPassings.addActionListener( listener );
		showPersons.setToolTipText("Vis en liste over alle passeringer som er gjort i alle heiser ");
		butnPnl.add(showPassings);

		 
		

		
		setLayout( new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.PAGE_END;
	

		add(fieldPnl, c);

		c.gridy=1;

		add(butnPnl, c);

		c.gridy=2;

		c.weighty=1;
		c.weightx=1;
		add(dispPnl, c);

		c.gridy=3;

		


	}

	private void findPerson()
	{
		try
		{
			String pattern = "\\d{6}";
			String stingcardNr = crdNr.getText();
			if(stingcardNr.matches(pattern))
			{
				int cardnr = Integer.parseInt(crdNr.getText());
				Person p = list.findPersonByCard(cardnr);
				display.setText(p.toString());
			}JOptionPane.showMessageDialog(null,"Du må ha 6 siffre");
		}
		catch(NullPointerException npe)
		{
			display.setText("Colund't find the card");
		}
		
	} 


	private void showPersonsWithcards()
	{
		list.sort();
		display.setText(list.toString());
	}

		
	private void showCards()
	{

		display.setText(cardregistry.toString());
		
	}

	private void showpersons()
	{
		perstable = list.personTable();
		
	}

	private void showpassings()
	{
		passTable = showPassings();
	}
	
	
	private void deletePerson()
	{
		Person p = null;
		
		try
		{
			String pattern = "\\d{8}";
			String stingtlf = tlfNr.getText();
			
			if(stingtlf.matches(pattern))
			{
				int tlfnr = Integer.parseInt(stingtlf); 	
				if(reg != null)
				{
					p = (Person) model.get(reg.getSelectedIndex());
					System.out.println(p.toString());
					p = list.deletePerson(p);
				
					reg = null;
				}		
				else
				{
					reg  = new JList<Person>();
				
					model = (list.findPerson(tlfnr));
					reg.setModel( model );
					
					if(model.getSize() == 1)
					{	
						p = (Person)model.firstElement();
						p = list.deletePerson(p);
					}
					else if(model.getSize() >= 2)
					{ 
						listscroll = new JScrollPane(reg );
						scroll.setViewportView(listscroll);
						JOptionPane.showMessageDialog(null, "Det er fler enn 1 med samme nr. Velg 1 og trykk på slett Person knappen igjen\n");
						return;
					}

				}	
				
				scroll.setViewportView(display);
				display.setText(p.getFirstName() +" "+ p.getLastName()+ " er nå slettet fra systemet");	
				tlfNr.setText("");
			}

		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null,"Du må ha 8 siffre");
		}

	}

	private JTable showPassings()
	{
		
		String[] columnName = {"HeisNr", "KortNr", "KortType", "Passerings tid"};
		Object[][] passings = new Object[validations.size() ][4]; 
		Iterator<Validations> it = validations.iterator();

		for (int i = 0; i < validations.size(); i++ )
		{
			Validations runner = it.next();
			Calendar cal = Calendar.getInstance();  
			cal.setTime(runner.getDate());
			String date = "" + cal.get(Calendar.DAY_OF_MONTH) +"."+ (cal.get(Calendar.MONTH ) + 1) +"."+  cal.get(Calendar.YEAR ) 
			+ " Kl " + cal.get(Calendar.HOUR_OF_DAY )+ ":" + cal.get(Calendar.MINUTE );
			

			passings[i][0] = runner.getLiftId();
			passings[i][1] = runner.getCard();
			passings[i][2] = runner.getCard().getCurrent().getType("");
			passings[i][3] = date;

		}
		passTable = new JTable(passings,columnName);
		passTable.setAutoCreateRowSorter(true);
		passTable.setEnabled(false);
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

      			showpersons();
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
      			showpassings();
      			scroll.setViewportView(passTable);
      		}
      		if(e.getSource() == deletePersBtn)
      		{
      			deletePerson();	
      		}
    	}
	}	
}
	