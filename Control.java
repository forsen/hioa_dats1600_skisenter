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
	private JPanel ctrlWindowPassThrough, contentPanel;
	private JLabel ctrlWindowTextShowTime, ctrlWindowTextCustNr;
	private Lift lift;
	private Personlist registry;
	private Cardlist cardlist;
	private Card validatingCard;
	private BtnListener btnListener;


	private Toolkit toolbox;




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
		setMinimumSize( new Dimension( 360,225) );


//		JLabel background = new JLabel(new ImageIcon("bakgrunn.jpg"));



		setLocationByPlatform( true );

		ImageIcon valider = new ImageIcon("img/valider.png");
		ctrlRegCustNr = new JButton(valider);
		ImageIcon valider2 = new ImageIcon("img/valider2.png");
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
		ctrlWindowPassThrough.setBackground(Color.RED);
		

		setLayout(new BorderLayout());


		contentPanel.setLayout( new GridBagLayout() );
		

		GridBagConstraints cc = new GridBagConstraints();
		cc.fill = GridBagConstraints.BOTH;

		cc.gridx = 0;
		cc.gridy=0;
		cc.gridwidth=1;
		cc.weightx = 0.2;
		contentPanel.add(ctrlRegCustNr, cc);

		cc.gridx=1;
		cc.gridwidth=1;
		contentPanel.add(ctrlWindowTextCustNr, cc);

		cc.gridx=2;
		cc.gridwidth=1;
		contentPanel.add(ctrlWindowCustNr, cc) ;

		cc.gridx=3;
		cc.gridwidth=1;
		contentPanel.add(ctrlWindowTextShowTime, cc);
		
		cc.gridx=4;
		cc.gridwidth=1;
		contentPanel.add(ctrlWindowShowTime, cc);

		cc.gridx=2;
		cc.gridy=3;
		contentPanel.add(ctrlWindowPassThrough, cc);
		



		add(contentPanel);
		


		updateTime();

		
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

			if (registry.findCard( cardNumber ) != null)
				validatingCard = registry.findCard( cardNumber );
			else if (cardlist.findCard(cardNumber)!= null)
				validatingCard =cardlist.findCard( cardNumber );

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