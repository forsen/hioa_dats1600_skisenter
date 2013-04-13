import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.Date;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.io.*;
import java.text.ParseException;
import javax.imageio.ImageIO;

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
	private Image backgroundImage;


	private Toolkit toolbox;

	public Control( Personlist cr, Lift l )
	{
		super("Kontrollvindu");

		registry = cr; 
		lift = l; 
		toolbox = Toolkit.getDefaultToolkit();

		
		validatingCard = null;

		btnListener = new BtnListener();

		Dimension windowDimension = toolbox.getScreenSize();

		int height = windowDimension.height;
		int width = windowDimension.width; 

		setSize( width/4, height/4 );
		setMinimumSize( new Dimension( 360,225) );
		setLayout(new BorderLayout());
//		JLabel background = new JLabel(new ImageIcon("bakgrunn.jpg"));


		try
		{
			 backgroundImage = ImageIO.read(new File("bakgrunn.jpg"));
		}
		catch( IOException ioe )
		{

		}
		setLocationByPlatform( true );

		ImageIcon valider = new ImageIcon("valider.png");
		ctrlRegCustNr = new JButton(valider);
		ImageIcon valider2 = new ImageIcon("valider2.png");
		ctrlRegCustNr.setRolloverIcon(valider2);
		ctrlRegCustNr.setFocusPainted(false);
		ctrlRegCustNr.setContentAreaFilled(false);
		ctrlRegCustNr.setBorderPainted(false);
		ctrlRegCustNr.addActionListener(btnListener);

		ctrlWindowTextCustNr = new JLabel("Kort ID:");
		ctrlWindowTextShowTime = new JLabel("Klokke:");

		ctrlWindowCustNr = new JTextField();
		ctrlWindowCustNr.setPreferredSize(new Dimension(70,20));
		

		
		ctrlWindowShowTime = new JTextField();
		ctrlWindowShowTime.setPreferredSize(new Dimension(80,20));

		ctrlWindowPassThrough = new JPanel();
		ctrlWindowPassThrough.setPreferredSize(new Dimension(600, 400));
		ctrlWindowPassThrough.setBackground(Color.RED);

		ctrlWindowStatusTxt = new JTextArea(10,10);
		ctrlWindowStatusTxt.setText("Statusfelt her");


		Container c = getContentPane();
		c.setLayout( new FlowLayout() );
//		c.add(background);
		c.add(ctrlRegCustNr);
		c.add(ctrlWindowTextCustNr);
		c.add(ctrlWindowCustNr);
		c.add(ctrlWindowTextShowTime);
		c.add(ctrlWindowShowTime);
		c.add(ctrlWindowPassThrough);
		c.add(ctrlWindowStatusTxt);
		repaint();
		updateTime();
	}

	public void paint( Graphics g ) 
	{ 
		super.paint(g);
		g.drawImage(backgroundImage, 0, 0, null);	
	}
	public void updateTime()
	{
		Timer timer = new Timer();
        timer.schedule(new UpdateTime(), 0, 1000);

	}

	public void findCard()
	{
		try
		{
			int cardNumber = Integer.parseInt(ctrlWindowCustNr.getText());

			validatingCard = registry.findCard( cardNumber );

			Skicard currentCard = validatingCard.getCurrent();

			Date now = new Date();

			if( validatingCard != null )
			{

				if( currentCard instanceof Timebasedcard)
				{
					if( ((Timebasedcard) currentCard).getExpires() == null )
					{
						((Timebasedcard) currentCard).initialized();
					}
					
					if( ((Timebasedcard) currentCard).getExpires().after(now) )
					{
						ctrlWindowPassThrough.setBackground(Color.GREEN);
						JOptionPane.showMessageDialog( null, "Gå gjennom. Ditt kort går ut: " + ((Timebasedcard) currentCard).getExpires() );
						ctrlWindowPassThrough.setBackground(Color.RED);
						lift.registrations( validatingCard );
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Ditt kort gikk ut: " + ((Timebasedcard) currentCard).getExpires() );
					}
				}

				if(currentCard instanceof Punchcard)
				{
					if( ((Punchcard) currentCard).getClipCount() == -1)
					{
						((Punchcard) currentCard).initialized();
						lift.registrations( validatingCard );
					}

					if( ((Punchcard) currentCard).getClipCount() > 0)
					{
						ctrlWindowPassThrough.setBackground(Color.GREEN);
						((Punchcard) currentCard).usePunchCard();
						JOptionPane.showMessageDialog( null, "Gå gjennom. Antall klipp igjen på kortet: " + ((Punchcard) currentCard).getClipCount() );
						ctrlWindowPassThrough.setBackground(Color.RED);
						lift.registrations( validatingCard );
					}

					else
					{
						JOptionPane.showMessageDialog( null, "Beklager, ingen fler klipp");
						ctrlWindowPassThrough.setBackground(Color.RED);
					}

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

	private class UpdateTime extends TimerTask
	{
    	public void run()
    	{
      	   String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
     	   ctrlWindowShowTime.setText(time);
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