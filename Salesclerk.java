import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Salesclerk extends JFrame
{

	private final int LEFT = 20;
	private final int RIGHT = 40;
	private JButton custWindowBtn, salesWindowBtn, replaceWindowBtn, refillWindowBtn, custWindowSearchBtn, custWindowRegBtn;
	private JPanel topMenuPnl, custWindowPnl, salesWindowPnl, replaceWindowPnl, refillWindowPnl, statusPnl; 
	private JPanel custWindowNamePnl, custWindowPhonePnl, custWindowBornPnl, custWindowBtnPnl;
	private JTextField custWindowName, custWindowPhone, custWindowBorn;
	private JTextArea salesWindowTxt, replaceWindowTxt, refillWindowTxt, statusTxt;
	private Listener listener;

	public Salesclerk( Personlist custRegistry )
	{
		super("Testvindu");

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

		custWindowNamePnl = new JPanel( new FlowLayout() );
		custWindowPhonePnl = new JPanel( new FlowLayout() );
		custWindowBornPnl = new JPanel( new FlowLayout() );
		custWindowBtnPnl = new JPanel( new FlowLayout() );

		custWindowName = new JTextField(25);
		custWindowPhone = new JTextField(25);
		custWindowBorn = new JTextField(25);


		statusTxt = new JTextArea(10,40);
		listener = new Listener(); 

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
		salesWindowBtn.addActionListener( listener );
		refillWindowBtn.addActionListener(listener);
		replaceWindowBtn.addActionListener(listener);
		custWindowPnl.add(custWindowNamePnl);
		custWindowPnl.add(custWindowPhonePnl);
		custWindowPnl.add(custWindowBornPnl);
		custWindowPnl.add(custWindowBtnPnl);
		custWindowNamePnl.add( new JLabel( "Navn" ) );
		custWindowNamePnl.add( custWindowName );
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
		statusTxt.setText("Status ting her!");


		setSize(600,600);
		setVisible(true);
		custWindowPnl.setVisible(true);
		salesWindowPnl.setVisible(false);
		refillWindowPnl.setVisible(false);
		replaceWindowPnl.setVisible(false);
		statusPnl.setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}

	private void registerPerson()
	{
		String name = custWindowName.getText();
		int number = Integer.parseInt(custWindowPhone.getText());
		int age = Integer.parseInt(custWindowBorn.getText());

		Person p = new Person( name, number, age );

		custRegistry.input( p ); 

		status.setText(name + " ble opprettet med kundenr: " + p.getCustId());
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