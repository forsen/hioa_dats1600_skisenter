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
	private JButton custWindowBtn, salesWindowBtn, replaceWindowBtn, refillWindowBtn;
	private JPanel topMenuPnl, custWindowPnl, salesWindowPnl, replaceWindowPnl, refillWindowPnl, statusPnl; 
	private JPanel framePnl;
	private JTextArea replaceWindowTxt, refillWindowTxt, statusTxt;

	public static Person customer = null; 

	private Listener listener;

	private Personlist custRegistry; 

	private Toolkit toolbox;

	private Container c;
	private BorderLayout layout;

	public Salesclerk( Personlist cr, String m )
	{
		super("Testvindu");


		toolbox = Toolkit.getDefaultToolkit();


		framePnl = new JPanel(); 


		Dimension windowDimension = toolbox.getScreenSize();

		int height = windowDimension.height;
		int width = windowDimension.width; 

		setSize( width/2, height/2 );
		setLocationByPlatform( true );

		custRegistry = cr; 
		custWindowBtn = new JButton("Kunde");
		salesWindowBtn = new JButton("NySalg");
		refillWindowBtn = new JButton("Påfyll");
		replaceWindowBtn = new JButton("Erstatt");

		topMenuPnl = new JPanel( new FlowLayout() );
		
		statusTxt = new JTextArea(5,40);

		//custWindowPnl = new CustWindowPanel( custRegistry, statusTxt, customer );
		custWindowPnl = new CustWindowPanel( custRegistry, statusTxt );
		//custWindowPnl.setLayout( new BoxLayout( custWindowPnl, BoxLayout.PAGE_AXIS) );
		//custWindowPnl.setLayout( new BorderLayout( 5,5 ) );


		//salesWindowPnl = new SalesWindowPanel( customer );
		salesWindowPnl = new SalesWindowPanel();
		replaceWindowPnl = new JPanel( new FlowLayout() );
		refillWindowPnl = new JPanel( new FlowLayout() );
		//statusPnl = new JPanel( new FlowLayout() );



		refillWindowTxt = new JTextArea(LEFT, RIGHT);
		replaceWindowTxt = new JTextArea(LEFT, RIGHT);

		framePnl.add(custWindowPnl);
		framePnl.add(refillWindowPnl );
		framePnl.add(replaceWindowPnl );
		framePnl.add(salesWindowPnl );





		listener = new Listener(); 

		layout = new BorderLayout( 5, 5 );
		c = getContentPane();
		
		c.setLayout( layout );
		c.add(topMenuPnl, BorderLayout.PAGE_START );
		c.add(framePnl, BorderLayout.CENTER );
		c.add(statusTxt, BorderLayout.PAGE_END );

		topMenuPnl.add(custWindowBtn);
		topMenuPnl.add(salesWindowBtn);
		topMenuPnl.add(replaceWindowBtn);
		topMenuPnl.add(refillWindowBtn);

	 

		custWindowBtn.addActionListener( listener );

		salesWindowBtn.addActionListener( listener );
		refillWindowBtn.addActionListener(listener);
		replaceWindowBtn.addActionListener(listener);



		refillWindowPnl.add(refillWindowTxt);
		replaceWindowPnl.add(replaceWindowTxt);
		//statusPnl.add(statusTxt);


		refillWindowTxt.setText("Her kan man fylle på heiskort");
		replaceWindowTxt.setText("Her kan man erstatte kort");
		statusTxt.setText(m);



		custWindowPnl.setVisible(true);
		salesWindowPnl.setVisible(false);
		refillWindowPnl.setVisible(false);
		replaceWindowPnl.setVisible(false);
		//statusPnl.setVisible(true);

		layout.layoutContainer( c );

	}




	private class Listener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			salesWindowPnl.setVisible(false);
			custWindowPnl.setVisible(false);
			replaceWindowPnl.setVisible(false);
			refillWindowPnl.setVisible(false);

			if( e.getSource() == custWindowBtn )
				custWindowPnl.setVisible(true);
			else if( e.getSource() == salesWindowBtn )
			{
				salesWindowPnl.setVisible(true);
			}

			else if( e.getSource() == refillWindowBtn )
				refillWindowPnl.setVisible(true);
			else if( e.getSource() == replaceWindowBtn )
				replaceWindowPnl.setVisible(true);

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