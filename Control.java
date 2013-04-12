import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.*;
import java.text.ParseException;

public class Control extends JFrame
{
	private JButton ctrlRegCustNr;
	private JTextField ctrlWindowCustNr, ctrlWindowShowTime; 
	private JPanel ctrlWindowPassThrough;
	private JLabel ctrlWindowTextShowTime, ctrlWindowTextCustNr;
	private JTextArea ctrlWindowStatusTxt;


	private Toolkit toolbox;

	public Control()
	{
		super("Kontrollvindu");

		toolbox = Toolkit.getDefaultToolkit();

		String time = new SimpleDateFormat("hh:mm").format(new Date());

		Dimension windowDimension = toolbox.getScreenSize();

		int height = windowDimension.height;
		int width = windowDimension.width; 

		setSize( width/2, height/2 );
		setLocationByPlatform( true );


		ctrlRegCustNr = new JButton("Validér");

		ctrlWindowTextCustNr = new JLabel("Customer ID:");
		ctrlWindowTextShowTime = new JLabel("Current time:");

		ctrlWindowCustNr = new JTextField();
		ctrlWindowCustNr.setPreferredSize(new Dimension(80,20));
		

		
		ctrlWindowShowTime = new JTextField();
		ctrlWindowShowTime.setPreferredSize(new Dimension(80,20));
		ctrlWindowShowTime.setText(time);

		ctrlWindowPassThrough = new JPanel();
		Color color = new Color(255, 0, 0);
		ctrlWindowPassThrough.setPreferredSize(new Dimension(600, 400));
		ctrlWindowPassThrough.setBackground(color);

		ctrlWindowStatusTxt = new JTextArea(10,10);
		ctrlWindowStatusTxt.setText("Statusfelt her");


		Container c = getContentPane();
		c.setLayout( new FlowLayout() );
		c.add(ctrlRegCustNr);
		c.add(ctrlWindowTextCustNr);
		c.add(ctrlWindowCustNr);
		c.add(ctrlWindowTextShowTime);
		c.add(ctrlWindowShowTime);
		c.add(ctrlWindowPassThrough);
		c.add(ctrlWindowStatusTxt);

	}

	
	/*<konstruktør for å opprette vinduet>

	<metoder for å validere kort basert på kortnr>
	
	<abstrakte metode for å undersøke om et tidsbasert skikort er gyldig>

	<abstrakt metode for å trekke X klipp>

	<knappelytter og diverse andre ting som kreves i en vindusklasse>*/

}