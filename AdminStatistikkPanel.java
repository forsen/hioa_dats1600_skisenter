
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Iterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminStatistikkPanel extends JPanel
{	
	private JLabel from, to, month, liftnr;
	private JTextField fromFld, toFld,monthFld, liftFLd;
	private JTextArea display;
	private JPanel displayPnl, graphPnl;
	private JButton soldCardsBtn, regPersBtn, revenueBtn, liftPassBtn;
	private JTabbedPane tabDisp; 
	private JScrollPane scroll;
	private Listener listener;
	private Personlist list;
	private Calculator cal;
	private List<Validations> validations;
	private Cardlist cardregistry;
	private Toolkit toolbox;

	private SimpleDateFormat formatter;

	public static String scale; 


	// fjern etterhvert
	private int[][] graph;

	public AdminStatistikkPanel(Personlist l,List<Validations> v, Cardlist cr )
	{
		setBackground(new Color(200, 230, 255));


		// setting "firstLight" to the day when our skisenter first saw the day of light, currently 010113.

		formatter = new SimpleDateFormat("ddMMyy"); 

		

		scale = "Dager";
		list = l;
		validations = v;
		cardregistry = cr;
		toolbox = Toolkit.getDefaultToolkit();


		cal = new Calculator(list, validations, cardregistry);

		listener = new Listener();
		
		setLayout( new GridBagLayout() );
	
		displayPnl = new JPanel();
		displayPnl.setLayout(new BorderLayout());
		
		tabDisp = new JTabbedPane();

		graph = new int[1][10];

		graphPnl = new GraphPanel( graph, "x", "y", "" ); 
		displayPnl.setBackground(new Color(200, 230, 255));
		tabDisp.addTab("Rapport", displayPnl);
		tabDisp.addTab("Grafisk visning", graphPnl);

		tabDisp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		


		soldCardsBtn = new JButton( "Solgte kort" );
		soldCardsBtn.setToolTipText("Se hvor mange kort som ble solg over en periode");
		soldCardsBtn.addActionListener( listener );
		

		regPersBtn = new JButton( "Registrerte personer" );
		regPersBtn.setToolTipText("Se hvor mange personer som ble registrert over en periode");
		regPersBtn.addActionListener( listener );
		

		revenueBtn = new JButton( "Omsetning" );
		revenueBtn.setToolTipText("Se hvor stor omsetning sentret hadde over en periode");
		revenueBtn.addActionListener( listener );
		

		liftPassBtn = new JButton( "Passeringer i heis" );
		liftPassBtn.setToolTipText("Se hvor mange heis passeringer det var over en periode (skriv inn heisnr for å bare trekke ut fra en heis)");
		liftPassBtn.addActionListener( listener );
	

		from = new JLabel( "Fra: " );
		fromFld = new JTextField(4);
		fromFld.setToolTipText("Sett inn en startdato du ønsker og beregne info fra");
		fromFld.setEditable( true );
	

		to = new JLabel( "Til: " );
		toFld = new JTextField(4);
		toFld.setToolTipText("Sett inn en sluttdato du ønsker og beregne info til");
		toFld.setEditable( true );
	

		month = new JLabel( "Måned: " );
		monthFld = new JTextField(4);
		monthFld.setToolTipText("Sett inn et tall som representerer mn du har lyst å se beregninger på");
		monthFld.setEditable( true );
	

		liftnr = new JLabel( "HeisNr: " );
		liftFLd = new JTextField(4);
		liftFLd.setToolTipText("om du ønsker å se heis passringer i en spessiell heis taster du inn heisens nr");
		liftFLd.setEditable( true );
	

		display = new JTextArea(1,65);
		display.setEditable( false );

		scroll = new JScrollPane(display);
		displayPnl.add(scroll);
		displayPnl.add(display, BorderLayout.CENTER);

		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0,0,0,0);
//FØRSTE - KOLONNE /////////////////////////////////////////
		c.gridheight = 1; 
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 0;
		c.gridwidth = 1; 
		c.weighty = 0.2;
		add(soldCardsBtn, c);
		
		c.gridheight = 1; 
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 1;
		c.gridwidth = 1; 
		c.weighty = 0.2;
		add(regPersBtn, c);

		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 2; 
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(revenueBtn, c);

		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 3; 
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(liftPassBtn, c);

/*		c.gridheight = 1;
		c.weightx = 1;
		c.gridx = 0; 
		c.gridy = 4; 
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(graphBtn, c);

		c.gridheight = 1;
		c.weightx = 1;
		c.gridx = 0; 
		c.gridy = 5; 
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(calculateBtn, c);*/

//ANDRE-KOLONNE //////////////////////////////////////////////
		c.gridheight = 1; 
		c.weightx = 0.5;
		c.gridx = 1; 
		c.gridy = 0;
		c.gridwidth = 1; 
		c.weighty = 0.2;
		add(from, c);

		c.gridheight = 1; 
		c.weightx = 0.5;
		c.gridx = 1; 
		c.gridy = 1;
		c.gridwidth = 1; 
		c.weighty = 0.2;
		add(to,c);	

		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 1; 
		c.gridy = 2; 
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(month, c);

		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 1; 
		c.gridy = 3; 
		c.gridwidth = 2;
		c.weighty = 0.2;
		add(liftnr, c);

//tredje kolonne /////////////////////////////

		c.gridheight = 1; 
		c.weightx = 0.5;
		c.gridx = 2; 
		c.gridy = 0;
		c.gridwidth = 1; 
		c.weighty = 0.2;
		add(fromFld, c);

		c.gridheight = 1; 
		c.weightx = 0.5;
		c.gridx = 2; 
		c.gridy = 1;
		c.gridwidth = 1; 
		c.weighty = 0.2;
		add(toFld,c);	

		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 2; 
		c.gridy = 2; 
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(monthFld, c);

		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 2; 
		c.gridy = 3; 
		c.gridwidth = 2;
		c.weighty = 0.2;
		add(liftFLd, c);


		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 10; 
		c.gridwidth = 3;
		c.weighty = 0.2;
		add(tabDisp, c);

		// fjern etterhvert

		/*graph = new int[30];

		for( int i = 0; i < graph.length; i++)
			graph[i] = (int) Math.floor((Math.random()*100)+1);*/



	}
//START-BEREGN




	private void totalRegPepole()
	{

		Date start;
		Date end; 
		
		start = getStartDate();
		end = getEndDate();	

		if( start == null || end == null )
			return;

		int[][] regPeopleIntrvl = cal.totalRegPeople(start, end);
		
		graphPnl = new GraphPanel( regPeopleIntrvl, scale, "Ant", formatter.format(start) + " - " + formatter.format(end) );
		int idx = tabDisp.getSelectedIndex();
		tabDisp.remove( 1 );
		tabDisp.add("Grafisk visning", graphPnl);
		tabDisp.setSelectedIndex( idx );

		display.setText("Registrerte personer i intervallet " + formatter.format(start) + " til " + formatter.format(end) +":\n");
		int total = 0; 
		for( int i = 0; i < regPeopleIntrvl.length; i++ )
		{
			for( int j = 0; j < regPeopleIntrvl[i].length; j++ )
				total += regPeopleIntrvl[i][j];
		}
		display.append( "" + total );
		
	}

	private void totalSoldCard()
	{
		Date start;
		Date end; 
		
		start = getStartDate();
		end = getEndDate(); 

		if( start == null || end == null )
			return;

		int[][] soldCardIntrvl = cal.totalSoldCard(start, end);

		graphPnl = new GraphPanel( soldCardIntrvl, scale, "Ant", formatter.format(start) + " - " + formatter.format( end ) );
		int idx = tabDisp.getSelectedIndex();
		tabDisp.remove( 1 );
		tabDisp.add( "Grafisk visning", graphPnl );
		tabDisp.setSelectedIndex( idx );
		
		int[] total = new int[5];
		
		for( int i = 0; i < soldCardIntrvl.length; i++ )
		{
			for( int j = 0; j < soldCardIntrvl[i].length; j++ )
				total[i] += soldCardIntrvl[i][j];
		}
		display.setText("Solgte skikort i intervallet " + formatter.format(start) + " til " + formatter.format(end) + ":");
		display.append( "\nDagskort: \t" + total[Skicard.DAYCARD] );
		display.append( "\nTimeskort: \t" + total[Skicard.HOURCARD] );
		display.append( "\nSesongkort: \t" + total[Skicard.SEASONCARD] );
		display.append( "\nKlippekort: \t" + total[Skicard.PUNCHCARD] );
		display.append( "\nFysiske kort: \t" + total[4] );
	}

	private void regThatTime()
	{
		try
		{
			int nr = Integer.parseInt(monthFld.getText());

			display.append("\nAntall personer som ble registrert i månede " + nr + " er " + cal.regThatTime(nr));
		}
		catch(NullPointerException npe)
		{
			display.setText("Need to put in a date");
		}
		catch(NumberFormatException nfe)
		{
			display.setText("Has to be a number");
		}
	}

	/*public LinkedList<Card> getCards()
	{

	} */



	private void passings()
	{
		String lift = liftFLd.getText();
		String pattern = "\\d{1}";
   		if(!(lift.isEmpty()))
   		{
   			
   			if(lift.matches(pattern))
			{
   				int liftnr = Integer.parseInt(lift);
   				display.append("\nAntall passeringer gjennom heis nummer " + lift + " er " +cal.passesbyTypeofCard( liftnr));
   			}else 
   			JOptionPane.showMessageDialog(null,"Du må sette inn siffer");		
   		}
   		else display.append("\nAntall passeringer gjennom alle heiser er " + cal.showPassings());
	}


	private void revenue()
	{

		Date start;
		Date end; 
		
		start = getStartDate();
		end = getEndDate();	

		if( start == null || end == null )
			return;

		int[][] totalRevenue = cal.totalRevenue(start, end);
		
		graphPnl = new GraphPanel( totalRevenue, scale, "KR", formatter.format(start) + " - " + formatter.format(end) );
		int idx = tabDisp.getSelectedIndex();
		tabDisp.remove( 1 );
		tabDisp.add("Grafisk visning", graphPnl);
		tabDisp.setSelectedIndex( idx );

		display.setText("Omsetning i intervallet " + formatter.format(start) + " til " + formatter.format(end) +":\n");
	
		int[] total = new int[5];
		
		int sum = 0;

		for( int i = 0; i < totalRevenue.length; i++ )
		{
			for( int j = 0; j < totalRevenue[i].length; j++ )
			{
				total[i] += totalRevenue[i][j];
				sum += totalRevenue[i][j];
			}
		}
		display.append( "\nDagskort: \t" + total[Skicard.DAYCARD] );
		display.append( "\nTimeskort: \t" + total[Skicard.HOURCARD] );
		display.append( "\nSesongkort: \t" + total[Skicard.SEASONCARD] );
		display.append( "\nKlippekort: \t" + total[Skicard.PUNCHCARD] );
		display.append( "\nFysiske kort: \t" + total[4] );
		display.append( "\n\nTotalt: \t" + sum );
		
	}

	/*public void totalPunch()
	{
		display.append("\nTotal salg av Klippekort er " +cal.totalPunch() + "KR og antall klippekort som er solgt er: " + cal.totalPunch()/Info.PUNCHCARDPRICE + "Stk");
	}*/
//END-BEREGN

//START-GRAF

	private void monthlyCardSale()
	{
/*		graph = new int[12];


		for( int i = 0; i < graph.length; i++)
		{

			int y = cal.cardSoldInMonthX(i);
			System.out.println(y);
			graph[i] = y;
		}	*/
	}


	private Date getStartDate()
	{
		Date start; 
		try
		{
			start = formatter.parse(fromFld.getText());
		}
		catch( ParseException pe )
		{
			if( !fromFld.getText().isEmpty() )
			{
				JOptionPane.showMessageDialog(null, "Datoen må være på formen ddmmyy!");
				return null;
			}

			start = Info.firstLight;
			fromFld.setText( formatter.format( start ) );
		}

		return start;

	}

	private Date getEndDate()
	{
		Date end; 
		try
		{
			end = formatter.parse(toFld.getText());
		}
		catch( ParseException pe )
		{
			if( !toFld.getText().isEmpty() )
			{
				JOptionPane.showMessageDialog(null, "Datoen må være på formen ddmmyy!");
				return null;
			}
			end = new Date(); 
			toFld.setText( formatter.format( end ) );
		}

		return end;
	}


	private class Listener implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 	
      		
      /*		if(e.getSource() == calculateBtn)
      		{
      			graphPnl.setVisible(false);
      			display.setVisible( true );
      		}*/
   /*   		else if ( e.getSource()== graphBtn )
       		{
       			monthlyCardSale();
       			graphPnl = new GraphPanel( graph );
       			//scroll = new JScrollPane(graphPnl);
       			displayPnl.add(graphPnl);
       			graphPnl.setVisible(true);
       			display.setVisible( false );
       				
       		}*/

       		if (e.getSource() == soldCardsBtn )
       		{
       			totalSoldCard();



/* 			Dirty hack to have the panel refresh when you draw new data on it */

       		}
       		else if (e.getSource() == regPersBtn )
       			totalRegPepole();
       		else if (e.getSource() == liftPassBtn)
       			 passings();
       		else if (e.getSource() == revenueBtn)
       			revenue();

/*
       		else if(tabDisp.getSelectedIndex() == 0)
       		{
       			
      		}
       		else if(tabDisp.getSelectedIndex() == 1)
       		{
       			//monthlyCardSale();
       			graphPnl = new GraphPanel( graph );
       			
       			//scroll = new JScrollPane(graphPnl);
       			tabDisp.setComponentAt(1, graphPnl);
       			
       		}
*/     		

    	}
	}



}
	
