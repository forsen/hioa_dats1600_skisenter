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
import java.util.Locale;
import java.text.NumberFormat;

/**
  * This class creates the statistics panel on the Admin window. The statistics panel can display reports and graphs of data, revenue, registered person,
  * sold cards, and validations.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */


public class AdminStatistikkPanel extends JPanel
{	
	private JLabel from, to, liftnr;
	private JTextField fromFld, toFld, liftFLd;
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
	private NumberFormat paymentFormat; 

	private SimpleDateFormat formatter;

	public static String scale; 
	public final static int CARDS = 1;
	public final static int VALIDS = 2;
	public final static int PERSON = 3; 
	public final static int REVENUE = 4; 

	private int[][] graph;

/**
  * This constructor creates elements and place them on the panel. It also receives the necessary data to create the reports and graphs.
  * @param l 	The personlist to show stats about
  * @param v 	The validations to show stats about
  * @param cr 	The cardlist to show stats about
  */
	public AdminStatistikkPanel(Personlist l,List<Validations> v, Cardlist cr )
	{
		setBackground(new Color(200, 230, 255));


		// setting "firstLight" to the day when our skisenter first saw the day of light, currently 010113.

		formatter = new SimpleDateFormat("ddMMyy"); 
		paymentFormat = NumberFormat.getCurrencyInstance( new Locale( "no","NO" ) );
		

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

		graphPnl = new GraphPanel( graph, "x", "y", "", 0 ); 
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



	}

/**
  * This method will gather the data based on a time range, compile it to a nested array, and put the data to display as a report and as a graph. 
  */
	private void totalRegPepole()
	{

		Date start;
		Date end; 
		
		start = getStartDate();
		end = getEndDate();	

		if( start == null || end == null )
			return;

		int[][] regPeopleIntrvl = cal.totalRegPeople(start, end);
		
		// the following code is necessary to repaint the graph with new data. 		
		graphPnl = new GraphPanel( regPeopleIntrvl, scale, "Ant", formatter.format(start) + " - " + formatter.format(end), PERSON );
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
/**
  * This method will gather the data based on a time range, compile it to a nested array, and put the data to display as a report and as a graph. 
  */
	private void totalSoldCard()
	{
		Date start;
		Date end; 
		
		start = getStartDate();
		end = getEndDate(); 

		if( start == null || end == null )
			return;

		int[][] soldCardIntrvl = cal.totalSoldCard(start, end);

		// the following code is necessary to repaint the graph with new data. 
		graphPnl = new GraphPanel( soldCardIntrvl, scale, "Ant", formatter.format(start) + " - " + formatter.format( end ), CARDS );
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

/**
  * This method will gather the data based on a time range, compile it to a nested array, and put the data to display as a report and as a graph. 
  */
	private void passings()
	{
		Date start; 
		Date end; 

		start = getStartDate(); 
		end = getEndDate(); 

		if( start == null || end == null )
			return; 

		int[][] passingsIntrvl = cal.showPassings( start, end );
		
		// the following code is necessary to repaint the graph with new data. 
		graphPnl = new GraphPanel( passingsIntrvl, scale, "Ant", formatter.format( start ) + " - " + formatter.format( end ), VALIDS ); 
		int idx = tabDisp.getSelectedIndex();
		tabDisp.remove( 1 );
		tabDisp.add( "Grafisk visning", graphPnl );
		tabDisp.setSelectedIndex( idx );

		int[] passings = new int[Info.LIFTS];
		int total = 0; 
		display.setText("Heispasseringer i intervallet " + formatter.format(start) + " til " + formatter.format(end) + ":\n\n");
		
		for( int i = 0; i < passingsIntrvl.length; i++ )
		{
			display.append("Heis nr: " + (i+1) + "\t\t" );
			for( int j = 0; j < passingsIntrvl[i].length; j++ )
			{
				passings[i] += passingsIntrvl[i][j];
				total += passingsIntrvl[i][j];
			}
			display.append( passings[i] + "\n" );
		}

		display.append("\nTotalt: " + "\t\t" + total ); 
	}

/**
  * This method will gather the data based on a time range, compile it to a nested array, and put the data to display as a report and as a graph. 
  */
	private void revenue()
	{

		Date start;
		Date end; 
		
		start = getStartDate();
		end = getEndDate();	

		if( start == null || end == null )
			return;

		int[][] totalRevenue = cal.totalRevenue(start, end);
		
		// the following code is necessary to repaint the graph with new data. 		
		graphPnl = new GraphPanel( totalRevenue, scale, "KR", formatter.format(start) + " - " + formatter.format(end), REVENUE );
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
		display.append( "\nDagskort: \t" + paymentFormat.format( total[Skicard.DAYCARD] ) );
		display.append( "\nTimeskort: \t" + paymentFormat.format( total[Skicard.HOURCARD] ) );
		display.append( "\nSesongkort: \t" + paymentFormat.format( total[Skicard.SEASONCARD] ) );
		display.append( "\nKlippekort: \t" + paymentFormat.format( total[Skicard.PUNCHCARD] ) );
		display.append( "\nFysiske kort: \t" + paymentFormat.format( total[4] ) );
		display.append( "\n\nTotalt: \t" + paymentFormat.format( sum ) );
		
	}




/**
  * A method to retrieve the start Date of the range. If no start Date is specified, it will use the date when this Skisenter first opened.
  * @return Returns a Date object for the beginning of the range
  * @see Info#firstLight
  */
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

/**
  * A method to retrieve the end Date of the range. If no end Date is specified, it will use the today's date.
  * @return Returns a Date object for the end of the range
  */
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


       		if (e.getSource() == soldCardsBtn )
       		{
       			totalSoldCard();
       		}
       		else if (e.getSource() == regPersBtn )
       			totalRegPepole();
       		else if (e.getSource() == liftPassBtn)
       			 passings();
       		else if (e.getSource() == revenueBtn)
       			revenue();
    		

    	}
	}



}
	
