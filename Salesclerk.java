import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.*;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;


public class Salesclerk extends JFrame
{

	private final int DELAY = 5; // hvor mange sekunder en melding til statustxt skal vises
	private final int LEFT = 20;
	private final int RIGHT = 40;
	private JButton custWindowBtn, salesWindowBtn, replaceWindowBtn, nextCustBtn;
	private JPanel custWindowPnl, salesWindowPnl, replaceWindowPnl, statusPnl; 
	private JPanel framePnl, salesClerkCustInfoPnl;
	public static JTextArea statusTxt;

	//replaceWindowTxt

	public static Person customer = null; 
	public static JTextArea salesClerkSearchInfoTxt;

	private Listener listener;

	private Personlist custRegistry; 
	private Cardlist cardregistry;

	private JTabbedPane salesClerkTabs;

	private Toolkit toolbox;

	private Container c;
	private BorderLayout layout;

	public Salesclerk( Personlist cr, Cardlist cardr, String m )
	{
		super("Salgsvindu");




		getContentPane().setBackground(new Color(199,214, 226));
		toolbox = Toolkit.getDefaultToolkit();


		setBackground(new Color(200, 230, 255));
		framePnl = new JPanel()
		{
			@Override
			protected void paintComponent(Graphics grphcs)
			{		
				Graphics2D g2d = (Graphics2D) grphcs;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				Color color1 = new Color(245, 250, 255);
				Color color2 = new Color(150, 195, 245);

				GradientPaint gp = new GradientPaint(0,0, color1, 0, getHeight(), color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());

			}

		};

		salesClerkSearchInfoTxt = new JTextArea( 5, 20 );
		salesClerkSearchInfoTxt.setEditable(false);
		salesClerkSearchInfoTxt.setToolTipText("Info om kunden du hjelper");

		Dimension windowDimension = toolbox.getScreenSize();

		int height = windowDimension.height;
		int width = windowDimension.width; 

		setSize( width/2, height/2 );
		setLocationByPlatform( true );

		custRegistry = cr; 
		cardregistry = cardr;

		salesClerkCustInfoPnl = new JPanel( new BorderLayout() );


	
		ImageIcon kunde = new ImageIcon("img/kunde.png");
		// custWindowBtn = new JButton(kunde);
		// ImageIcon kunde2 = new ImageIcon("img/kunde2.png");
		// // custWindowBtn.setRolloverIcon(kunde2);
		// // custWindowBtn.setFocusPainted(false);
		// // custWindowBtn.setContentAreaFilled(false);
		// // custWindowBtn.setBorderPainted(false);
		// // custWindowBtn.setToolTipText("Registrering av kunde");

		ImageIcon salg = new ImageIcon("img/salg.png");
		// salesWindowBtn = new JButton(salg);
		// ImageIcon salg2 = new ImageIcon("img/salg2.png");
		// salesWindowBtn.setRolloverIcon(salg2);
		// salesWindowBtn.setFocusPainted(false);
		// salesWindowBtn.setContentAreaFilled(false);
		// salesWindowBtn.setBorderPainted(false);
		// salesWindowBtn.setToolTipText("Registrering av nytt salg");

		ImageIcon erstatt = new ImageIcon("img/erstatt.png");
		replaceWindowBtn = new JButton(erstatt);
		ImageIcon erstatt2 = new ImageIcon("img/erstatt2.png");
		replaceWindowBtn.setRolloverIcon(erstatt2);
		replaceWindowBtn.setFocusPainted(false);
		replaceWindowBtn.setContentAreaFilled(false);
		replaceWindowBtn.setBorderPainted(false);
		replaceWindowBtn.setToolTipText("Erstatt kort");

		ImageIcon next = new ImageIcon("img/neste.png");
		nextCustBtn = new JButton(next);
		nextCustBtn.setToolTipText("For å ekspidere neste kunde");
		ImageIcon next2 = new ImageIcon("img/neste2.png");
		nextCustBtn.setRolloverIcon(next2);

		nextCustBtn.setFocusPainted(false);
		nextCustBtn.setContentAreaFilled(false);
		nextCustBtn.setBorderPainted(false);

		salesClerkCustInfoPnl.add( nextCustBtn, BorderLayout.PAGE_START );
		salesClerkCustInfoPnl.add( salesClerkSearchInfoTxt, BorderLayout.CENTER );

		statusTxt = new JTextArea(5,50){
			@Override
			public void setText( String t )
			{
				super.setText( t );
				if( t != null )
				{
					Timer timer = new Timer(); 
					timer.schedule( new ClearStatusTxt(), DELAY*1000 );
				}
			}
		};

		statusTxt.setBackground(new Color(238, 248, 255));
		statusTxt.setEditable( false );

		//custWindowPnl = new CustWindowPanel( custRegistry, statusTxt, customer );
		custWindowPnl = new CustWindowPanel( custRegistry, statusTxt );
		//custWindowPnl.setLayout( new BoxLayout( custWindowPnl, BoxLayout.PAGE_AXIS) );
		//custWindowPnl.setLayout( new BorderLayout( 5,5 ) );


		//salesWindowPnl = new SalesWindowPanel( customer );
		salesWindowPnl = new SalesWindowPanel(cardregistry);

		replaceWindowPnl = new ReplaceWindowPanel( statusTxt);


		//statusPnl = new JPanel( new FlowLayout() );




		//replaceWindowTxt = new JTextArea(LEFT, RIGHT);





		listener = new Listener(); 

		layout = new BorderLayout( 5, 5 );
		c = getContentPane();
		
		c.setLayout( layout );
		c.add(framePnl, BorderLayout.LINE_START );
		c.add(salesClerkCustInfoPnl, BorderLayout.CENTER );
//		c.add(salesClerkSearchInfoTxt, BorderLayout.CENTER );

		c.add(statusTxt, BorderLayout.PAGE_END );


	 

		// custWindowBtn.addActionListener( listener );

		// salesWindowBtn.addActionListener( listener );

		// replaceWindowBtn.addActionListener(listener);

		nextCustBtn.addActionListener(listener);


		salesClerkTabs = new JTabbedPane();
		salesClerkTabs.addTab( "Kunde", kunde, custWindowPnl );
		salesClerkTabs.addTab( "Nysalg", salg, salesWindowPnl);
		salesClerkTabs.addTab( "Erstatt", erstatt, replaceWindowPnl );
		framePnl.add(salesClerkTabs);

		//replaceWindowPnl.add(replaceWindowTxt);
		//statusPnl.add(statusTxt);


		//replaceWindowTxt.setText("Her kan man erstatte kort");
		statusTxt.setText(m);



		// custWindowPnl.setVisible(true);
		// salesWindowPnl.setVisible(false);
		// replaceWindowPnl.setVisible(false);
		// //statusPnl.setVisible(true);

		layout.layoutContainer( c );
		

	}

	public void clearSearch()
	{
		
		customer = null;
		CustWindowPanel.blankOut();
		SalesWindowPanel.salesWindowCustIDtf.setText( "");
		SalesWindowPanel.cardnrf.setText("");
		SalesWindowPanel.cardnrf.setEditable( true );
		salesClerkSearchInfoTxt.setText("");
		try
		{
			SalesWindowPanel.cardIDList.setModel( new DefaultListModel<Card>());
			ReplaceWindowPanel.replaceWindowCustIdtf.setText("");
			ReplaceWindowPanel.cardIDList.setModel( new DefaultListModel<Card>());
			salesClerkSearchInfoTxt.setBackground(Color.WHITE);
			CustWindowPanel.clearSearch(); 
			ShoppingCart.emptyCart();


		}
		catch(IllegalArgumentException iae)
		{
			System.out.println("någikk noe galt");
		}
		
		
	}





	private class Listener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			salesWindowPnl.setVisible(false);
			custWindowPnl.setVisible(false);
			replaceWindowPnl.setVisible(false);


			// if( e.getSource() == custWindowBtn )
			// 	custWindowPnl.setVisible(true);
			// else if( e.getSource() == salesWindowBtn )
			// {
			// 	salesWindowPnl.setVisible(true);
			// }

			// else if( e.getSource() == replaceWindowBtn )
			// 	replaceWindowPnl.setVisible(true);

			if( e.getSource() == nextCustBtn)
			{
				//custWindowPnl.setVisible(true);
				salesClerkTabs.setSelectedIndex(0);

				clearSearch();
			}
				

		}
	}

	private class ClearStatusTxt extends TimerTask
	{
		public void run()
		{
			statusTxt.setText( null );
		}
	}

/*	<datafelter>

	<konstruktør som oppretter vinduet>

	<metode for å registrere person>

	<metode for å legge heiskort på person>

	<metode for å kjøpe heiskort uten person>

	<kalkulator (gi tilbake x antall basert på betalt)>

	<metode for å erstatt mistet / ødelagt kort>

	<metode for å fylle på kort>*/
}