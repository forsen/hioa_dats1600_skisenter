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
import javax.swing.table.AbstractTableModel;
import java.util.Locale;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
  * This class creates the info panel on the Admin window. The info panel can display information on our different registries, like person registry,
  * card registry, validations registry.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

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
	private JTable perstable, passTable, cardTable;
	private Toolkit toolbox;
	private JList<Person> reg;
	private DefaultListModel<Person> model;
	private NumberFormat paymentFormat;


/**
  * The constructor creates all the elements and place them on the panel. 
  * @param l 	the personlist to show information about
  * @param v 	the validations to show information about
  * @param cr 	the cardlist to show information about
  * @see Admin
  */
	public AdminInfoPanel(Personlist l,List<Validations> v, Cardlist cr )
	{
		Border etched = BorderFactory.createEtchedBorder();
		toolbox = Toolkit.getDefaultToolkit();

		paymentFormat = NumberFormat.getCurrencyInstance( new Locale( "no", "NO" ) );

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

/** 
  * This method is used to find a person based on the card number. It will add the persons toString() info to the display if found. 
  * @see Person
  */

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

				if(p==null)
					display.setText("Søket gav ingen treff");
			}
			else
				JOptionPane.showMessageDialog(null,"Du må ha 6 siffre");
		}
		catch(NullPointerException npe)
		{
			display.setText("Colund't find the card");
		}
		
	} 

/**
  * This method writes out every person to the display, including all the cards this person holds. 
  */

	private void showPersonsWithcards()
	{
		list.sort();
		display.setText(list.toString());
	}

/**
  * This method deletes a person from our registry based on the phone number. If more than one person share a phone number, you'll be able to choose from
  * a list which one to delete. It will write the result to the display.
  */

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
						JOptionPane.showMessageDialog(null, "Det er fler enn én med samme nr. Velg personen som skal slettes, og trykk på slett Person knappen igjen\n");
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

/**
  * This method will show every passing through the lifts in a jtable.
  * @return Return a JTable containing all passings through our lifts. 
  */
	private JTable showPassings()
	{
		
		String[] columnName = {"HeisNr", "KortNr", "KortType", "Passerings tid"};
		Object[][] passings = new Object[validations.size() ][4]; 
		Iterator<Validations> it = validations.iterator();

		for (int i = 0; i < validations.size(); i++ )
		{
			Validations runner = it.next();

			String date = new SimpleDateFormat("dd.MM.yy HH:mm").format( runner.getDate() );			

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

/**
  * This method will create a table of all the unregistered cards, and their skicards.
  * @return Returns a JTable containing all unregistered cards. 
  */
	public JTable unregCardTable()
	{

		DefaultListModel<Card> cards = cardregistry.listCards();
		//Object[][] unRegCards = new Object[cards.size() ][6]; 
		int size = cards.getSize();
		
		ArrayList<ListObject> list = new ArrayList<ListObject>(); 

		int tblIdx = 0; 
		for(int i = 0; i < size; i++)
		{	
			Card runner = cards.getElementAt(i);


			List<Skicard> skicards = runner.getSkicardlist();
			Iterator it = skicards.iterator();
			while(it.hasNext())
			{	
				Skicard skicrunner = (Skicard)it.next();

				
				for(int j = 0; j<skicards.size(); j++)
				{

					
					if( skicrunner instanceof Timebasedcard )
					{	
						String expire;
						if( ((Timebasedcard) skicrunner).getExpires() == null )
							expire = "Ikke validert enda";
						else
						{
							if( skicrunner instanceof Hourcard )
								expire = new SimpleDateFormat("dd.MM.yy HH:mm").format( ((Timebasedcard) skicrunner).getExpires());
							else
								expire = new SimpleDateFormat("dd.MM.yy").format( ((Timebasedcard) skicrunner).getExpires() );
						}

						String age = null;
						if(skicrunner.getAgeGroup() == 1)
							age = "Barn";
						if(skicrunner.getAgeGroup() == 2)
							age = "Voksen";

						list.add( new ListObject( runner.getCardID(), skicrunner.getType(""), expire, paymentFormat.format(skicrunner.getPrice()),
							100-(100* skicrunner.getDiscount())+ "%", age  ));
					}
					String age = null;
						if(skicrunner.getAgeGroup() == 1)
							age = "Barn";
						if(skicrunner.getAgeGroup() == 2)
							age = "Voksen";

					else if (skicrunner instanceof Punchcard )
						list.add( new ListObject( runner.getCardID(), skicrunner.getType(""), "" + ((Punchcard) skicrunner).getClipCount(), paymentFormat.format(skicrunner.getPrice()),
							100-(100* skicrunner.getDiscount()) + "%", age) );

				}
			
		
			} 
		}

		JTable unRegCtable = new JTable( new MyTableModel( list ));
		unRegCtable.setAutoCreateRowSorter(true);
		unRegCtable.setEnabled(false);
		return unRegCtable;
	
	}

/**
  * A class creating a custom tablemodel. The is used to create a table of cards and skicards. 
  */
	private class MyTableModel extends AbstractTableModel 
	{
		String[] columnName = {"Kortnummer", "Type", "Går ut", "Pris", "Rabatt", "Aldersgruppe"};

		ArrayList<ListObject> list = null;
 
		public MyTableModel(ArrayList<ListObject> list) 
		{
			this.list = list;
		}
 
		public int getColumnCount() {
			return columnName.length;
		}
 
		public int getRowCount() {
			return list.size();
		}
 
		public String getColumnName(int col) {
			return columnName[col];
		}
 
		public Object getValueAt(int row, int col) 
		{
 
			ListObject object = list.get(row);
 
			switch (col) 
			{
			case 0:
				return object.getCardID();
			case 1:
				return object.getType();
			case 2:
				return object.getString(); 
			case 3:
				return object.getPrice();
			case 4:
				return object.getDiscount();
			case 5: 
				return object.getAgeGroup();
			default:
				return "unknown";
			}
		}
 
		public Class getColumnClass(int c) 
		{
			return getValueAt(0, c).getClass();
		}
	}
 
/**
  * A class to create Object to put in a JTable. The object will contain information about Cards and Skicards.
  */
	private class ListObject
	{
		int cardId;
		String type;
		String price;
		String both;
		String discount;
		String ageGroup;

		public ListObject( int cId, String t, String b, String p, String d, String ag )
		{
			cardId = cId;
			type = t; 
			both = b;
			price = p;
			discount = d;
			ageGroup = ag;
		}

		public int getCardID()
		{
			return cardId;
		}
		public String getType()
		{
			return type;
		}
		public String getPrice()
		{
			return price;
		}
		public String getDiscount()
		{
			return discount;
		}
		public String getString()
		{
			return both;
		}
		public String getAgeGroup()
		{
			return ageGroup;
		}
	}

	
/**
 * This Listener initiates methods by listening on clicked buttons.
 */

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
      			scroll.setViewportView(list.personTable());

      		if(e.getSource() == showPersWcards)
      		{
      			showPersonsWithcards();
      			scroll.setViewportView(display);
      		}
      		if(e.getSource() == showCards)
      			scroll.setViewportView(unregCardTable());

      		if(e.getSource() == showPassings)
      			scroll.setViewportView(showPassings());

      		if(e.getSource() == deletePersBtn)
      			deletePerson();	
    	}
	}	
}
// end of class AdminInfoPanel
	