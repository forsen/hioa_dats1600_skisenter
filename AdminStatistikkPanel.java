import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Iterator;

public class AdminStatistikkPanel extends JPanel
{	
	private JTextField fromFld, toFld,monthFld, liftFLd;
	private JTextArea display;
	private JPanel btnPnl, selectedPnl,fromToPnl, monthPnl, displayPnl, graphPnl;
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
		
		setLayout( new BorderLayout( 5, 8) );

		btnPnl = new JPanel(new GridLayout( 9,1));
		selectedPnl = new JPanel(new GridLayout( 1,6 ));
		displayPnl = new JPanel();
		fromToPnl = new JPanel(new GridLayout(2,1));
		monthPnl = new JPanel(new GridLayout(2,1));
			
		soldCardsBtn = new JButton( "Solgte kort" );
		soldCardsBtn.addActionListener( listener );
		btnPnl.add(soldCardsBtn);

		regPersBtn = new JButton( "Registrerte personer" );
		regPersBtn.addActionListener( listener );
		btnPnl.add(regPersBtn);

		revenueBtn = new JButton( "Omsetning" );
		revenueBtn.addActionListener( listener );
		btnPnl.add(revenueBtn);

		liftPassBtn = new JButton( "Passeringer i heis" );
		liftPassBtn.addActionListener( listener );
		btnPnl.add(liftPassBtn);

		graphBtn = new JButton( "Graf" );
		graphBtn.addActionListener( listener );
		btnPnl.add(graphBtn);

		calculateBtn = new JButton( "Beregn" );
		calculateBtn.addActionListener( listener );
		btnPnl.add(calculateBtn);

		fromToPnl.add( new JLabel( "Fra: " ) );
		fromFld = new JTextField(4);
		fromFld.setEditable( true );
		fromToPnl.add(fromFld);

		fromToPnl.add( new JLabel( "Til: " ) );
		toFld = new JTextField(4);
		toFld.setEditable( true );
		fromToPnl.add(toFld);

		monthPnl.add( new JLabel( "Måned: " ) );
		monthFld = new JTextField(4);
		monthFld.setEditable( true );
		monthPnl.add(monthFld);

		monthPnl.add( new JLabel( "HeisNr: " ) );
		liftFLd = new JTextField(4);
		liftFLd.setEditable( true );
		monthPnl.add(liftFLd);

		selectedPnl.add(fromToPnl, BorderLayout.PAGE_START);
		selectedPnl.add(monthPnl, BorderLayout.PAGE_END);

		display = new JTextArea(20,40);
		display.setEditable( false );

		scroll = new JScrollPane(display);
		displayPnl.add(scroll);
		displayPnl.add(display);


		add(btnPnl, BorderLayout.LINE_START);
		add(selectedPnl, BorderLayout.CENTER);
		add(displayPnl, BorderLayout.PAGE_END);



		

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

	public void totalPunch()
	{
		display.append("\nTotal salg av Klippekort er " +cal.totalPunch() + "KR og antall klippekort som er solgt er: " + cal.totalPunch()/Info.PUNCHCARDPRICE + "Stk");
	}
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
	
