import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Salesclerk extends JFrame
{

	private JButton button;
	private JButton button2;
	private JPanel panel;
	private JPanel panel2;
	private JPanel panel3;
	private JTextArea txtarea;
	private JTextArea txtarea2;
	private Listener listener;

	public Salesclerk()
	{
		super("Testvindu");

		button = new JButton("Test1");
		button2 = new JButton("Test2");
		panel = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		txtarea = new JTextArea(20,20);
		txtarea2 = new JTextArea(20,20);
		listener = new Listener(); 

		Container c = getContentPane();
		c.setLayout( new FlowLayout() );
		c.add(panel);
		c.add(panel2);
		c.add(panel3);

		panel.add(button);
		panel.add(button2);
		button.addActionListener( listener );
		button2.addActionListener( listener );
		panel2.add(txtarea);
		panel3.add(txtarea2);

		txtarea.setText("Vindu1");
		txtarea2.setText("Vindu2");
		setSize(300,300);
		setVisible(true);
		panel2.setVisible(false);
		panel3.setVisible(false);

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}

	private class Listener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			if( e.getSource() == button )
			{
				panel3.setVisible(false);
				panel2.setVisible(true);
			}
			else if( e.getSource() == button2 )
			{
				panel2.setVisible(false);
				panel3.setVisible(true);
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