import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Iterator;

public class AdminStatistikkPanel extends JPanel
{	
	private JLabel from, to, month, liftnr;
	private JTextField fromFld, toFld,monthFld, liftFLd;
	private JTextArea display;
	private JPanel displayPnl, graphPnl;
	private JButton soldCardsBtn, regPersBtn, revenueBtn, liftPassBtn, graphBtn, calculateBtn;
	private JScrollPane scroll;
	private Listener listener;
	private Personlist list;
	private Calculator cal;
	private List<Validations> validations;
	private Cardlist cardregistry;

	// fjern etterhvert
	private int[] graph;

	public AdminStatistikkPanel(Personlist l,List<Validations> v, Cardlist cr )
	{
		list = l;
		validations = v;
		cardregistry = cr;

		cal = new Calculator(list, validations, cardregistry);

		listener = new Listener();
		
		setLayout( new GridBagLayout() );

	
		displayPnl = new JPanel();
		
		//checkPnl = new JPanel(new Gris)

			
		soldCardsBtn = new JButton( "Solgte kort" );
		soldCardsBtn.addActionListener( listener );
		

		regPersBtn = new JButton( "Registrerte personer" );
		regPersBtn.addActionListener( listener );
		

		revenueBtn = new JButton( "Omsetning" );
		revenueBtn.addActionListener( listener );
		

		liftPassBtn = new JButton( "Passeringer i heis" );
		liftPassBtn.addActionListener( listener );
		

		graphBtn = new JButton( "Graf" );
		graphBtn.addActionListener( listener );
	

		calculateBtn = new JButton( "Beregn" );
		calculateBtn.addActionListener( listener );
	

		from = new JLabel( "Fra: " );
		fromFld = new JTextField(4);
		fromFld.setEditable( true );
	

		to = new JLabel( "Til: " );
		toFld = new JTextField(4);
		toFld.setEditable( true );
	

		month = new JLabel( "Måned: " );
		monthFld = new JTextField(4);
		monthFld.setEditable( true );
	

		liftnr = new JLabel( "HeisNr: " );
		liftFLd = new JTextField(4);
		liftFLd.setEditable( true );
	

		display = new JTextArea(20,40);
		display.setEditable( false );

		scroll = new JScrollPane(display);
		displayPnl.add(scroll);
		displayPnl.add(display);

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

		c.gridheight = 1;
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
		add(calculateBtn, c);

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
		add(displayPnl, c);

		// fjern etterhvert

		/*graph = new int[30];

		for( int i = 0; i < graph.length; i++)
			graph[i] = (int) Math.floor((Math.random()*100)+1);*/

/*		graph[0] = 43;
		graph[1] = 55;
		graph[2] = 42;
		graph[3] = 60;
		graph[4] = 49;
		graph[5] = 55; 
		graph[6] = 60;
		graph[7] = 54;
		graph[8] = 37;
		graph[9] = 67;
*/
	}
//START-BEREGN


	public void totalRegPepole()
	{
		display.append("\nTotalt registrerte personer er: " + cal.totalRegPepole());
	}

	public void totalSoldCard()
	{
		display.append("\nTotalt solgte skikort er: " + cal.totalSoldCard());
	}

	public void regThatTime()
	{
		try
		{
			int nr = Integer.parseInt(monthFld.getText());

			display.append("\nAntall personer som ble registrert i månede " + nr + " er " + cal.regThatTime(nr));
		}
		catch(NullPointerException npe)
		{
			display.setText("Må sette inn en Måned");
		}
		catch(NumberFormatException nfe)
		{
			display.setText("Må sette inn en tall");
		}
	}



	public void passings()
	{
		String lift = liftFLd.getText();
   		if(!(lift.isEmpty()))
   		{
   			int liftnr = Integer.parseInt(lift);
   			display.append("\nAntall passeringer gjennom heis nummer " + lift + " er " +cal.passesbyTypeofCard( liftnr));
   					
   		}
   		else display.append("\nAntall passeringer gjennom alle heiser er " + cal.showPassings());
	}

	public void revenue()
	{
		display.append("\nTotal omsetning er "+  cal.totalCost() + " KR");
	}

	/*public void totalPunch()
	{
		display.append("\nTotal salg av Klippekort er " +cal.totalPunch() + "KR og antall klippekort som er solgt er: " + cal.totalPunch()/Info.PUNCHCARDPRICE + "Stk");
	}*/
//END-BEREGN

//START-GRAF

	public void monthlyCardSale()
	{
		graph = new int[12];


		for( int i = 0; i < graph.length; i++)
		{

			int y = cal.cardSoldInMonthX(i);
			System.out.println(y);
			graph[i] = y;
		}	
	}


	private class Listener implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 	
      		
      		if(e.getSource() == calculateBtn)
      		{
      			graphPnl.setVisible(false);
      			display.setVisible( true );
      		}
      		else if ( e.getSource()== graphBtn )
       		{
       			monthlyCardSale();
       			graphPnl = new GraphPanel( graph );
       			//scroll = new JScrollPane(graphPnl);
       			displayPnl.add(graphPnl);
       			graphPnl.setVisible(true);
       			display.setVisible( false );
       				
       		}
       		else if (e.getSource() == soldCardsBtn )
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
	
