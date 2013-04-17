import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.*;
import java.text.ParseException;


public class Salesclerk extends JFrame
{

	private final int LEFT = 20;
	private final int RIGHT = 40;
	private JButton custWindowBtn, salesWindowBtn, replaceWindowBtn, nextCustBtn;
	private JPanel topMenuPnl, custWindowPnl, salesWindowPnl, replaceWindowPnl, statusPnl; 
	private JPanel framePnl;
	private JTextArea statusTxt;

	//replaceWindowTxt

	public static Person customer = null; 
	public static JTextArea salesClerkSearchInfoTxt;

	private Listener listener;

	private Personlist custRegistry; 
	private Cardlist cardregistry;



	private Toolkit toolbox;

	private Container c;
	private BorderLayout layout;

	public Salesclerk( Personlist cr, Cardlist cardr, String m )
	{
		super("Testvindu");




		getContentPane().setBackground(new Color(199,214, 226));
		toolbox = Toolkit.getDefaultToolkit();


		setBackground(new Color(200, 230, 255));
		framePnl = new JPanel();
		framePnl.setBackground(new Color(200, 230, 255));

		salesClerkSearchInfoTxt = new JTextArea( 5, 20 );
		salesClerkSearchInfoTxt.setEditable(false);

		Dimension windowDimension = toolbox.getScreenSize();

		int height = windowDimension.height;
		int width = windowDimension.width; 

		setSize( width/2, height/2 );
		setLocationByPlatform( true );

		custRegistry = cr; 
		cardregistry = cardr;
		ImageIcon kunde = new ImageIcon("img/kunde.png");
		custWindowBtn = new JButton(kunde);
		ImageIcon kunde2 = new ImageIcon("img/kunde2.png");
		custWindowBtn.setRolloverIcon(kunde2);
		custWindowBtn.setFocusPainted(false);
		custWindowBtn.setContentAreaFilled(false);
		custWindowBtn.setBorderPainted(false);
		custWindowBtn.setToolTipText("Registrering av kunde");

		ImageIcon salg = new ImageIcon("img/salg.png");
		salesWindowBtn = new JButton(salg);
		ImageIcon salg2 = new ImageIcon("img/salg2.png");
		salesWindowBtn.setRolloverIcon(salg2);
		salesWindowBtn.setFocusPainted(false);
		salesWindowBtn.setContentAreaFilled(false);
		salesWindowBtn.setBorderPainted(false);
		salesWindowBtn.setToolTipText("Registrering av nytt salg");

		ImageIcon erstatt = new ImageIcon("img/erstatt.png");
		replaceWindowBtn = new JButton(erstatt);
		ImageIcon erstatt2 = new ImageIcon("img/erstatt2.png");
		replaceWindowBtn.setRolloverIcon(erstatt2);
		replaceWindowBtn.setFocusPainted(false);
		replaceWindowBtn.setContentAreaFilled(false);
		replaceWindowBtn.setBorderPainted(false);
		replaceWindowBtn.setToolTipText("Erstatt kort");

		nextCustBtn = new JButton("neste Kunde");

		topMenuPnl = new JPanel( new FlowLayout() );
		topMenuPnl.setBackground(new Color(200, 230, 255));
		
		statusTxt = new JTextArea(5,50);
		statusTxt.setBackground(new Color(238, 248, 255));

		//custWindowPnl = new CustWindowPanel( custRegistry, statusTxt, customer );
		custWindowPnl = new CustWindowPanel( custRegistry, statusTxt );
		//custWindowPnl.setLayout( new BoxLayout( custWindowPnl, BoxLayout.PAGE_AXIS) );
		//custWindowPnl.setLayout( new BorderLayout( 5,5 ) );


		//salesWindowPnl = new SalesWindowPanel( customer );
		salesWindowPnl = new SalesWindowPanel(cardregistry);

		replaceWindowPnl = new ReplaceWindowPanel( statusTxt);


		//statusPnl = new JPanel( new FlowLayout() );




		//replaceWindowTxt = new JTextArea(LEFT, RIGHT);

		framePnl.add(custWindowPnl);

		framePnl.add(replaceWindowPnl );
		framePnl.add(salesWindowPnl );





		listener = new Listener(); 

		layout = new BorderLayout( 5, 5 );
		c = getContentPane();
		
		c.setLayout( layout );
		c.add(topMenuPnl, BorderLayout.PAGE_START );
		c.add(framePnl, BorderLayout.LINE_START );
		c.add(salesClerkSearchInfoTxt, BorderLayout.CENTER );

		c.add(statusTxt, BorderLayout.PAGE_END );

		topMenuPnl.add(custWindowBtn);
		topMenuPnl.add(salesWindowBtn);
		topMenuPnl.add(replaceWindowBtn);
		topMenuPnl.add(nextCustBtn);


	 

		custWindowBtn.addActionListener( listener );

		salesWindowBtn.addActionListener( listener );

		replaceWindowBtn.addActionListener(listener);

		nextCustBtn.addActionListener(listener);




		//replaceWindowPnl.add(replaceWindowTxt);
		//statusPnl.add(statusTxt);


		//replaceWindowTxt.setText("Her kan man erstatte kort");
		statusTxt.setText(m);



		custWindowPnl.setVisible(true);
		salesWindowPnl.setVisible(false);
		replaceWindowPnl.setVisible(false);
		//statusPnl.setVisible(true);

		layout.layoutContainer( c );

	}

	public void clearSearch()
	{
		customer = null;
		SalesWindowPanel.salesWindowCustIDtf.setText( "");
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

		}
		
		
	}





	private class Listener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			salesWindowPnl.setVisible(false);
			custWindowPnl.setVisible(false);
			replaceWindowPnl.setVisible(false);


			if( e.getSource() == custWindowBtn )
				custWindowPnl.setVisible(true);
			else if( e.getSource() == salesWindowBtn )
			{
				salesWindowPnl.setVisible(true);
			}

			else if( e.getSource() == replaceWindowBtn )
				replaceWindowPnl.setVisible(true);

			else if( e.getSource() == nextCustBtn)
			{
				custWindowPnl.setVisible(true);
				clearSearch();
			}
				

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