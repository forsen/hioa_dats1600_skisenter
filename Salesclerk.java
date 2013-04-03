import javax.swing.*;
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
	private JButton custWindowBtn, salesWindowBtn, replaceWindowBtn, refillWindowBtn, custWindowSearchBtn, custWindowRegBtn;
	private JPanel topMenuPnl, custWindowPnl, salesWindowPnl, replaceWindowPnl, refillWindowPnl, statusPnl; 
	private JPanel custWindowFirstNamePnl, custWindowLastNamePnl, custWindowPhonePnl, custWindowBornPnl, custWindowBtnPnl;
	private JTextField custWindowFirstName, custWindowLastName, custWindowPhone, custWindowBorn;
	private JTextArea salesWindowTxt, replaceWindowTxt, refillWindowTxt, statusTxt;
	private Listener listener;
	private CustListener custListener;

	private Personlist custRegistry; 

	private Toolkit toolbox;

	public Salesclerk( Personlist cr, String m )
	{
		super("Testvindu");

		toolbox = Toolkit.getDefaultToolkit();

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
		custWindowPnl = new JPanel();
		custWindowPnl.setLayout( new BoxLayout( custWindowPnl, BoxLayout.PAGE_AXIS) );


		salesWindowPnl = new JPanel( new FlowLayout() );
		replaceWindowPnl = new JPanel( new FlowLayout() );
		refillWindowPnl = new JPanel( new FlowLayout() );
		statusPnl = new JPanel( new FlowLayout() );

		salesWindowTxt = new JTextArea(LEFT,RIGHT);
		refillWindowTxt = new JTextArea(LEFT, RIGHT);
		replaceWindowTxt = new JTextArea(LEFT, RIGHT);

		custWindowSearchBtn = new JButton("Søk");
		custWindowRegBtn = new JButton("Ny kunde");

		custWindowFirstNamePnl = new JPanel( new FlowLayout() );
		custWindowLastNamePnl = new JPanel( new FlowLayout() );
		custWindowPhonePnl = new JPanel( new FlowLayout() );
		custWindowBornPnl = new JPanel( new FlowLayout() );
		custWindowBtnPnl = new JPanel( new FlowLayout() );

		custWindowFirstName = new JTextField(25);
		custWindowLastName = new JTextField(25);
		custWindowPhone = new JTextField(25);
		custWindowBorn = new JTextField(25);


		statusTxt = new JTextArea(10,40);
		listener = new Listener(); 
		custListener = new CustListener();

		Container c = getContentPane();
		c.setLayout( new FlowLayout() );
		c.add(topMenuPnl);
		c.add(custWindowPnl);
		c.add(refillWindowPnl);
		c.add(replaceWindowPnl);
		c.add(salesWindowPnl);
		c.add(statusPnl);

		topMenuPnl.add(custWindowBtn);
		topMenuPnl.add(salesWindowBtn);
		topMenuPnl.add(replaceWindowBtn);
		topMenuPnl.add(refillWindowBtn);

		custWindowBtn.addActionListener( listener );
		custWindowRegBtn.addActionListener( custListener );
		custWindowSearchBtn.addActionListener( custListener );
		salesWindowBtn.addActionListener( listener );
		refillWindowBtn.addActionListener(listener);
		replaceWindowBtn.addActionListener(listener);
		custWindowPnl.add(custWindowFirstNamePnl);
		custWindowPnl.add(custWindowLastNamePnl);
		custWindowPnl.add(custWindowPhonePnl);
		custWindowPnl.add(custWindowBornPnl);
		custWindowPnl.add(custWindowBtnPnl);
		custWindowFirstNamePnl.add( new JLabel( "Fornavn" ) );
		custWindowFirstNamePnl.add( custWindowFirstName );
		custWindowLastNamePnl.add( new JLabel( "Etternavn" ) );
		custWindowLastNamePnl.add( custWindowLastName );
		custWindowPhonePnl.add( new JLabel( "Telefon" ) );
		custWindowPhonePnl.add( custWindowPhone );
		custWindowBornPnl.add( new JLabel( "Født") );
		custWindowBornPnl.add( custWindowBorn );
		custWindowBtnPnl.add( custWindowRegBtn );
		custWindowBtnPnl.add( custWindowSearchBtn );
		salesWindowPnl.add(salesWindowTxt);
		refillWindowPnl.add(refillWindowTxt);
		replaceWindowPnl.add(replaceWindowTxt);
		statusPnl.add(statusTxt);

		salesWindowTxt.setText("Nytt salg vindu");
		refillWindowTxt.setText("Her kan man fylle på heiskort");
		replaceWindowTxt.setText("Her kan man erstatte kort");
		statusTxt.setText(m);



		custWindowPnl.setVisible(true);
		salesWindowPnl.setVisible(false);
		refillWindowPnl.setVisible(false);
		replaceWindowPnl.setVisible(false);
		statusPnl.setVisible(true);

	}

	private void registerPerson()
	{
		String firstname = custWindowFirstName.getText();
		String lastname = custWindowLastName.getText();
		int number = Integer.parseInt(custWindowPhone.getText());
		try
		{
			Date born = new SimpleDateFormat("ddMMyy").parse(custWindowBorn.getText());
			Person p = new Person( firstname, lastname, number, born );
			
		

			statusTxt.setText(custRegistry.input( p ));

		}
		catch( ParseException pe )
		{

		}


		

	}

	private void findPerson()
	{
		String firstname = custWindowFirstName.getText();
		String lastname = custWindowLastName.getText();
		try	
		{
			int number = Integer.parseInt(custWindowPhone.getText());

			statusTxt.setText( custRegistry.findPerson(number).toString());
		}
		catch( NumberFormatException nfe )
		{

		}

		if(firstname != null)
			statusTxt.setText(custRegistry.findPerson(firstname, lastname));


	}

	private class CustListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			if( e.getSource() == custWindowRegBtn )
				registerPerson();
			if( e.getSource() == custWindowSearchBtn )
				findPerson();
		}
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
				salesWindowPnl.setVisible(true);
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