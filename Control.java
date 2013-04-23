import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.Timer;
import java.util.Date;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.swing.border.*;
import java.text.ParseException;
import javax.imageio.ImageIO;

public class Control extends JFrame
{
	private JButton ctrlRegCustNr;
	private JTextField ctrlWindowCustNr, ctrlWindowShowTime; 
	private JPanel ctrlWindowPassThrough, contentPanel;
	private JLabel ctrlWindowTextShowTime, ctrlWindowTextCustNr, ctrlWindowPassThroughLabelText;
	private Lift lift;
	private Personlist registry;
	private Cardlist cardlist;
	private Card validatingCard;
	private BtnListener btnListener;
	private Color passThroughColor;
	private Font font;
	private Date usedRecentlyPrev, usedRecentlyNow;


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
		setMinimumSize( new Dimension( 400,300) );


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
		cc.gridy=5;
		cc.gridwidth=2;
		cc.weightx = 0.5;
		cc.anchor = GridBagConstraints.CENTER;
		contentPanel.add(ctrlRegCustNr, cc);

		cc.gridx=0;
		cc.gridwidth=1;
		cc.gridy=1;
		contentPanel.add(ctrlWindowTextCustNr, cc);

		cc.gridx=0;
		cc.gridy=0;
		cc.gridwidth=4;
		cc.anchor=GridBagConstraints.PAGE_START;
		contentPanel.add(ctrlWindowShowTime,cc);

		cc.gridx=1;
		cc.gridy=1;
		cc.gridwidth=1;
		cc.anchor = GridBagConstraints.WEST;
		contentPanel.add(ctrlWindowCustNr, cc) ;



		cc.gridx=0;
		cc.gridy=2;
		cc.gridwidth = 2;
		cc.gridheight = 3;
		contentPanel.add(ctrlWindowPassThrough, cc);
		



		add(contentPanel);
		

		setFonts("digital.ttf");
		updateTime();

		
	}

	public void updateTime()
	{
		Timer timer = new Timer();
        timer.schedule(new UpdateTime(), 0, 1000);

	}

	public boolean setFonts(String s)
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
						usedRecentlyPrev = new Date();

					}
					
					if( ((Timebasedcard) currentCard).getExpires().after(now) )
					{
						usedRecentlyNow = new Date();
//Gjør så man ikke få gått inn hvis det ikke går 10 sekunder siden forrige passering. Dette må koples mot hvert enkelt kort.
						if(usedRecentlyNow.getTime() - usedRecentlyPrev.getTime() >= 10*1000)
						{
							ctrlWindowPassThrough.setBackground(Color.GREEN);
							ctrlWindowPassThroughLabelText.setText("Gyldig: Kortet er gyldig til: " + ((Timebasedcard) currentCard).getExpires());

							lift.registrations( validatingCard );
							usedRecentlyPrev = new Date();
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

					if( ((Punchcard) currentCard).getClipCount() > 0)
					{
						ctrlWindowPassThrough.setBackground(Color.GREEN);
						((Punchcard) currentCard).usePunchCard();
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

			else
			{
				ctrlWindowPassThrough.setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "Du dreit deg ut!" ); 
			}
		}
		catch(NumberFormatException nfe)
		{
			 ctrlWindowPassThrough.setBackground(Color.RED);
			 ctrlWindowPassThroughLabelText.setText("Skriv et gyldig kortnummer (6 siffer)");
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
     	   ctrlWindowShowTime.setEditable(false);
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