import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import java.util.Date;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.swing.border.*;
import java.text.ParseException;
import javax.imageio.ImageIO;



/**
  * Control creates the typical controlscreens you see when entering a ski lift. It also
  * calculates if skicards are valid and then eventually grants the user access to walk through.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */



public class Control extends JFrame
{
	private JButton ctrlRegCustNr;
	private JTextField ctrlWindowCustNr, ctrlWindowShowTime; 
	private JPanel ctrlWindowPassThrough, contentPanel;
	private JLabel ctrlWindowTextShowTime, ctrlWindowTextCustNr, ctrlWindowLiftName, ctrlWindowPassThroughLabelText;
	private Lift lift;
	private Personlist registry;
	private Cardlist cardlist;
	private Card validatingCard;
	private BtnListener btnListener;
	private Color passThroughColor;
	private Font font;
	private static final int TIMELIMIT = 10*1000; // 10 seconds.
 

	private Toolkit toolbox;


/**
  * This constructor creates elements and place them on the frame. It also receives the necessary data to determine whether a card is valid or not.
  * @param cr 	The personlist
  * @param l    The lifts
  * @param cl 	The cardlist, to get necessary data about cards.
  */

	public Control( Personlist cr, Lift l, Cardlist cl )
	{

		super("Kontrollvindu");

		contentPanel = new JPanel()
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

		contentPanel.setOpaque(false);


		Border etched = BorderFactory.createEtchedBorder();
		contentPanel.setBorder(etched);
		

		registry = cr; 
		cardlist = cl;
		lift = l; 
		toolbox = Toolkit.getDefaultToolkit();

		
		validatingCard = null;

		btnListener = new BtnListener();

		Dimension windowDimension = toolbox.getScreenSize();

		int height = windowDimension.height;
		int width = windowDimension.width; 


		setSize( width/4, height/4 );
		setMinimumSize( new Dimension( 500,250) );


		setLocationByPlatform( true );

		ctrlRegCustNr = new JButton("Validér");

		ctrlRegCustNr.addActionListener(btnListener);

		ctrlWindowTextCustNr = new JLabel("Kort ID:");
		ctrlWindowTextShowTime = new JLabel("Klokke:");
		ctrlWindowLiftName = new JLabel();
		ctrlWindowLiftName.setText(lift.getName());
		ctrlWindowLiftName.setFont(new Font("Calibri", Font.BOLD, 20));

		ctrlWindowCustNr = new JTextField();
		ctrlWindowCustNr.addActionListener( btnListener );
		ctrlRegCustNr.addMouseListener(new MouseListener() 
		{

    		public void mouseClicked(MouseEvent e)
    		{
    			
    		}

    		public void mouseExited(MouseEvent event)
    		{
    			ctrlWindowPassThrough.setBackground(passThroughColor);

    		}

    		public void mouseEntered(MouseEvent event)
    		{
    			
    		}

    		public void mouseReleased(MouseEvent event)
    		{
    			
    		}

       		public void mousePressed(MouseEvent event)
    		{
    			
    		}

    	});
		

		
		ctrlWindowShowTime = new JTextField();
		ctrlWindowShowTime.setHorizontalAlignment(JTextField.CENTER);


		ctrlWindowPassThrough = new JPanel();
		passThroughColor = new Color(150, 195, 245);
		ctrlWindowPassThrough.setBackground(passThroughColor);
		ctrlWindowPassThrough.setPreferredSize(new Dimension(100,80));


		ctrlWindowPassThroughLabelText = new JLabel();
		
		

		ctrlWindowPassThroughLabelText.setForeground(Color.WHITE);

		ctrlWindowPassThrough.setLayout(new GridBagLayout());
		GridBagConstraints cwc = new GridBagConstraints();
		Border loweredBorder = BorderFactory.createLoweredBevelBorder();
		ctrlWindowPassThrough.setBorder(loweredBorder);
		

		cwc.gridy=1;
		ctrlWindowPassThrough.add(ctrlWindowPassThroughLabelText);
		

		setLayout(new BorderLayout());



		contentPanel.setLayout( new GridBagLayout() );
		

		GridBagConstraints cc = new GridBagConstraints();
		cc.fill = GridBagConstraints.BOTH;


		cc.gridx = 0;
		cc.gridy=6;
		cc.gridwidth=2;
		cc.weightx = 0.5;
		cc.anchor = GridBagConstraints.CENTER;
		contentPanel.add(ctrlRegCustNr, cc);


		
		cc.gridx=0;
		cc.gridy=0;
		cc.gridwidth=2;
		contentPanel.add(ctrlWindowLiftName, cc);

		cc.gridx=0;
		cc.gridwidth=1;
		cc.gridy=2;
		contentPanel.add(ctrlWindowTextCustNr, cc);

		cc.gridx=0;
		cc.gridy=1;
		cc.gridwidth=4;
		cc.anchor=GridBagConstraints.PAGE_START;
		contentPanel.add(ctrlWindowShowTime,cc);

		cc.gridx=1;
		cc.gridy=2;
		cc.gridwidth=1;
		cc.anchor = GridBagConstraints.WEST;
		contentPanel.add(ctrlWindowCustNr, cc) ;

		cc.gridx=0;
		cc.gridy=3;
		cc.gridwidth = 2;
		cc.gridheight = 3;
		contentPanel.add(ctrlWindowPassThrough, cc);

		add(contentPanel);
		

		setFonts("digital.ttf");
		updateTime();

		
	}

	/**
	  * A method that gets, and updates, the current time every second.
	  */

	private void updateTime()
	{
		Timer timer = new Timer();
        timer.schedule(new UpdateTime(), 0, 1000);

	}

	/**
	  * A method that gets the object Lift.
	  * @return Returns a Lift object
	  * @see Lift
	  */

	public Lift getLift()
	{
		return lift; 
	}




/**
 * A method that sets the font type for the different labels we use
 * @param s  String object with the font path
 * @return Returns a boolean value, that tells whether the fonts have been found and set, or not.
 */

	private boolean setFonts(String s)
	{
		try{
			font = Font.createFont(Font.TRUETYPE_FONT, new File(s));
			font = font.deriveFont(Font.BOLD, 20.0f);
			ctrlWindowPassThroughLabelText.setFont(font);
			font = font.deriveFont(Font.PLAIN, 16.0f);
			ctrlWindowShowTime.setForeground(Color.BLACK);
			ctrlWindowShowTime.setFont(font);
			return true;
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null,"Finner ikke fonten. Bruker standard font.");
			return false;
		}
	}


/**
 * A method that finds card, based on what is written in the cardnumber field. The method will also tell the user whether they have 
 * written a valid value, and if not, help the user.
 * @return Returns a Skicard object, if card is found.
 */

	private Skicard findCard()
	{
		validatingCard = null;
		try
		{
			
			String pattern = "\\d{6}";
			String stringcardNumber = ctrlWindowCustNr.getText();
			
			if(stringcardNumber.matches(pattern))
			{	
				int cardNumber = Integer.parseInt(stringcardNumber);

				if (registry.findCard( cardNumber ) != null)
					validatingCard = registry.findCard( cardNumber );

				else if (cardlist.findCard(cardNumber)!= null)
					validatingCard =cardlist.findCard( cardNumber );

				validatingCard.getBought(); // just to trigger a null pointer exception in case there is no skicard.

				return validatingCard.getCurrent();
			} ctrlWindowPassThrough.setBackground(Color.RED);
			 ctrlWindowPassThroughLabelText.setText("Skriv et gyldig kortnummer (6 siffer)");
		}
		catch( NumberFormatException nfe )
		{
			 ctrlWindowPassThrough.setBackground(Color.RED);
			 ctrlWindowPassThroughLabelText.setText("Skriv et gyldig kortnummer (6 siffer)");
		}
		catch( NullPointerException npe )
		{
			ctrlWindowPassThrough.setBackground( Color.RED );
			ctrlWindowPassThroughLabelText.setText( "Finner ikke skikortet" );
		}

		return null;
	}

/**
 * This method checks if a card has an expire date. If it doesn't, then the card hasn't yet been used.
 * If the card hasn't yet been used, the method will initialize the card(give it an expire date).
 * The method also checks whether a card is valid or not, based on card type,
 * expiration date(timebasedcard) or the amount of clips(punchcard). It will also excecute useCard from punchcards, which removes a number of clips, when a person uses it.
 * It also displays for the user whether they can go through or not, their cards expire date(timebasedcard), or their cards remaining amount of clips(punchcard)
 * The method will prevent users from cheating, or using eachothers card by checking if the last passthrough is under 10 seconds ago. 
 * In the real life, we would probably set five minutes, but ten seconds is a lot easier for testing and reviewing the program.
 */

	private void tryValidate()
	{
		Date now = new Date();

		Skicard currentCard = findCard();
		try
		{
			if( currentCard instanceof Timebasedcard)
			{
				if( ((Timebasedcard) currentCard).getExpires() == null )
				{
					((Timebasedcard) currentCard).initialized();
					((Timebasedcard) currentCard).setLastValidated( new Date() );
					lift.registrations( validatingCard );
					ctrlWindowPassThrough.setBackground(Color.GREEN);
					Calendar cal = Calendar.getInstance();  
					cal.setTime(((Timebasedcard) currentCard).getExpires());
					String date = "" + cal.get(Calendar.DAY_OF_MONTH) +"."+ (cal.get(Calendar.MONTH ) + 1) +"."+  cal.get(Calendar.YEAR ) 
					+ " Kl " + cal.get(Calendar.HOUR_OF_DAY )+ ":" + cal.get(Calendar.MINUTE );
					ctrlWindowPassThroughLabelText.setText("Velkommen: Kortet er gyldig til: " + date);

				}
				else if( ((Timebasedcard) currentCard).getExpires().after(now) )
				{

					if( now.getTime() - ((Timebasedcard) currentCard).getLastValidated().getTime() >= TIMELIMIT )
					{
					ctrlWindowPassThrough.setBackground(Color.GREEN);
					ctrlWindowPassThroughLabelText.setText("Gyldig: Kortet er gyldig til: " + ((Timebasedcard) currentCard).getExpires());
					((Timebasedcard) currentCard).setLastValidated( now ); 
					lift.registrations( validatingCard );

					}

					else 
					{
					ctrlWindowPassThrough.setBackground(Color.RED);			
					ctrlWindowPassThroughLabelText.setText("Ugyldig: Under 5 min. siden forrige passering.");
					}
			
				}
				else
				{
					ctrlWindowPassThrough.setBackground(Color.RED);							
					ctrlWindowPassThroughLabelText.setText("Ugyldig: Ditt kort gikk ut: " + ((Timebasedcard) currentCard).getExpires());
				}
			}

			if(currentCard instanceof Punchcard)
			{
				if( ((Punchcard) currentCard).getClipCount() == -1)
				{
					((Punchcard) currentCard).initialized();
				}

				if( ((Punchcard) currentCard).getClipCount() >= lift.getClips() )
				{
					ctrlWindowPassThrough.setBackground(Color.GREEN);
					((Punchcard) currentCard).usePunchCard( lift.getClips());
					ctrlWindowPassThroughLabelText.setText("Gyldig: Antall klipp igjen: " + ((Punchcard) currentCard).getClipCount());

					lift.registrations( validatingCard );
				}

				else
				{
					ctrlWindowPassThrough.setBackground(Color.RED);			
					ctrlWindowPassThroughLabelText.setText("Ugyldig: Ingen fler klipp.");

				}

			}

		}
		catch( NullPointerException npe )
		{
			npe.printStackTrace( System.out );
		}
	}


	private class BtnListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			if( ae.getSource() == ctrlRegCustNr )
			{
				tryValidate();
			}
			if( ae.getSource() == ctrlWindowCustNr )
			{
				tryValidate();
			}
		}

	}

/**
 * This class updates and displays time.
 */
	private class UpdateTime extends TimerTask
	{
    	public void run()
    	{
      	   String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
     	   ctrlWindowShowTime.setText(time);
     	   ctrlWindowShowTime.setEditable(false);
    	}
	} // end of class UpdateTime

} //end of class Control