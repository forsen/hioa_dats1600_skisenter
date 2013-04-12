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
	private Lift lift;
	private Personlist registry;
	private Card validatingCard;
	private BtnListener btnListener;


	private Toolkit toolbox;

	public Control( Personlist cr, Lift l )
	{
		super("Kontrollvindu");

		registry = cr; 
		lift = l; 
		toolbox = Toolkit.getDefaultToolkit();

		String time = new SimpleDateFormat("hh:mm").format(new Date());
		validatingCard = null;

		btnListener = new BtnListener();

		Dimension windowDimension = toolbox.getScreenSize();

		int height = windowDimension.height;
		int width = windowDimension.width; 

		setSize( width/2, height/2 );
		setLocationByPlatform( true );


		ctrlRegCustNr = new JButton("Validér");
		ctrlRegCustNr.addActionListener(btnListener);

		ctrlWindowTextCustNr = new JLabel("Customer ID:");
		ctrlWindowTextShowTime = new JLabel("Current time:");

		ctrlWindowCustNr = new JTextField();
		ctrlWindowCustNr.setPreferredSize(new Dimension(80,20));
		

		
		ctrlWindowShowTime = new JTextField();
		ctrlWindowShowTime.setPreferredSize(new Dimension(80,20));
		ctrlWindowShowTime.setText(time);

		ctrlWindowPassThrough = new JPanel();
		ctrlWindowPassThrough.setPreferredSize(new Dimension(600, 400));
		ctrlWindowPassThrough.setBackground(Color.RED);

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

	public void findCard()
	{
		try
		{
			int cardNumber = Integer.parseInt(ctrlWindowCustNr.getText());

			validatingCard = registry.findCard( cardNumber );

			Skicard currentCard = validatingCard.getCurrent();

			// Fjern denne når ting funker
			if( validatingCard != null )
			{
				if (((Timebasedcard) currentCard).getExpires() == null)
				{

				
					if(currentCard instanceof Timebasedcard)
					{
						((Timebasedcard) currentCard).initialized();
					}
					
					else if(currentCard instanceof Punchcard)
					{
						((Punchcard) currentCard).initialized();
					}

					ctrlWindowPassThrough.setBackground(Color.GREEN);
					JOptionPane.showMessageDialog( null, validatingCard.history() );
					ctrlWindowPassThrough.setBackground(Color.RED);
				}

				else
				{
					ctrlWindowPassThrough.setBackground(Color.GREEN);
					JOptionPane.showMessageDialog( null, "Gå gjennom. Du har igjen TID" );
					ctrlWindowPassThrough.setBackground(Color.RED);
				}

			}

			else
			{
				ctrlWindowPassThrough.setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "Du dreit deg ut!" ); 
			}
		}
		catch(NumberFormatException nfe)
		{
			 ctrlWindowPassThrough.setBackground(Color.RED);
			 JOptionPane.showMessageDialog(null, "You need to enter a\nnumber", "Input Error", JOptionPane.ERROR_MESSAGE);

		}
	}

	public void validate(int cardNumber)
	{
		
	}


	private class BtnListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			if( ae.getSource() == ctrlRegCustNr )
			{
				findCard();
			}
		}

	}


	/* ta imoot kortID, validere, sjekke om det finnes, gyldig, hvis klippekort trekke et klipp*/
	/* instanceOf Timebasedcard, sjekk om det er initializert;

	/*<konstruktør for å opprette vinduet>

	<metoder for å validere kort basert på kortnr>
	
	<abstrakte metode for å undersøke om et tidsbasert skikort er gyldig>

	<abstrakt metode for å trekke X klipp>

	<knappelytter og diverse andre ting som kreves i en vindusklasse>*/

}