import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Salesclerk extends JFrame
{

	private final int LEFT = 20;
	private final int RIGHT = 40;
	private JButton custWindowBtn, salesWindowBtn, replaceWindowBtn, refillWindowBtn;
	private JPanel topMenuPnl, custWindowPnl, salesWindowPnl, replaceWindowPnl, refillWindowPnl, statusPnl; 
	private JTextArea custWindowTxt, salesWindowTxt, replaceWindowTxt, refillWindowTxt, statusTxt;
	private Listener listener;

	public Salesclerk()
	{
		super("Testvindu");

		custWindowBtn = new JButton("Kunde");
		salesWindowBtn = new JButton("NySalg");
		refillWindowBtn = new JButton("Påfyll");
		replaceWindowBtn = new JButton("Erstatt");

		topMenuPnl = new JPanel( new FlowLayout() );
		custWindowPnl = new JPanel( new FlowLayout() );
		salesWindowPnl = new JPanel( new FlowLayout() );
		replaceWindowPnl = new JPanel( new FlowLayout() );
		refillWindowPnl = new JPanel( new FlowLayout() );
		statusPnl = new JPanel( new FlowLayout() );

		custWindowTxt = new JTextArea(LEFT,RIGHT);
		salesWindowTxt = new JTextArea(LEFT,RIGHT);
		refillWindowTxt = new JTextArea(LEFT, RIGHT);
		replaceWindowTxt = new JTextArea(LEFT, RIGHT);

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

		custWindowPnl.add(custWindowTxt);
		salesWindowPnl.add(salesWindowTxt);
		refillWindowPnl.add(refillWindowTxt);
		replaceWindowPnl.add(replaceWindowTxt);
		statusPnl.add(statusTxt);

		custWindowTxt.setText("Ny kunde vindu");
		salesWindowTxt.setText("Nytt salg vindu");
		statusTxt.setText("Status ting her!");

		//txtarea.setText("Vindu1");
		//txtarea2.setText("Vindu2");
		setSize(600,600);
		setVisible(true);
		custWindowPnl.setVisible(true);
		salesWindowPnl.setVisible(false);
		refillWindowPnl.setVisible(false);
		replaceWindowPnl.setVisible(false);
		statusPnl.setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

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
			{
				custWindowPnl.setVisible(true);
			}
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