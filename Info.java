import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Random;


/**
  * Info is the class that creates the Info-window. The info window is just for displaying information
  * and there is no methods in this class worth mentioning.
  * However, this class holds many static constants which are being fetched up in other classes.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

public class Info extends JFrame 
{
// CONSTANTS
	public final static int PUNCHCARDPRICE = 200;
	public final static int DAYCARDPRICE = 320;
	public final static int HOURCARDPRICE = 120;
	public final static int SEASONCARDPRICE = 3000;
	public final static int CHILDLIMIT = 16;
	public final static double DISCOUNT = 0.5;
	public final static int CARDPRICE = 70; 
	public final static int RETURNPRICE = -50; 
	public final static int LIFTS = 2; 
	public final static int CLIPS = 10;
	public final static double GROUPDISCOUNT = 0.9;
	public final static int GROUPDISCOUNTLIMIT = 10;
	public final static double FREAKYFRIDAY = 0.5; 
	private final int HEIGHT = 1000;
	private final int WIDTH = 1000; 

// WEATHER CONSTANTS
	public final static String SKY = "Sol";
	public final static String TEMPERATURE = "-4 C";
	public final static String SNOWDEPTH = "3 cm";
	public final static String AIRPRESSURE = "1000 (hPa)";
	public final static String WIND = "5 m/s";

// END CONSTANTS


	public static Date firstLight;

	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private BufferedImage newsBtnImg, infoBtnImg, offerBtnImg, pricesBtnImg,newsBtnImg1, infoBtnImg1, offerBtnImg1, pricesBtnImg1; 
    private static Listener listener;
    private static JLabel label;
    private static JButton button, newsButton, infoButton, offersButton, pricesButton;
    private static JPanel newsWindowPnl, infoWindowPnl, spOffersWindowPnl, panel, sideMenu, pricesWindowPnl, contentPanel;
 


	 /**
	  * The constructor creates all the elements and place them in the frame.
	  */
    public Info()
    {

    	try
		{
			firstLight = new SimpleDateFormat("ddMMyy").parse("010112");
		}
		catch( ParseException pe )
		{
			pe.printStackTrace( System.out );
		}

		GridBagLayout gbl = new GridBagLayout();

		setLayout(gbl);




		Toolkit toolbox = Toolkit.getDefaultToolkit();


		sideMenu = new JPanel()
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

				super.paintComponents(grphcs);
			}

		};


		sideMenu.setOpaque(false);

		sideMenu.setLayout( new GridLayout( 4,1 ) );

		/* News-button */
		try{
			newsBtnImg = ImageIO.read( new File("img/nyheter.png"));
			ImageIcon news = new ImageIcon(newsBtnImg);
			newsButton = new JButton(news);
			newsButton.setFocusPainted(false);
			newsButton.setBorderPainted(false);
			newsButton.setContentAreaFilled(false);

			} 
			catch (Exception e)
			{
				e.printStackTrace(System.out);
				JOptionPane.showMessageDialog(null, "En nødvendig fil mangler. Reinstaller programmet.", "Feil: manglende fil", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}

		try{
			newsBtnImg1 = ImageIO.read( new File("img/nyheter2.png"));
			ImageIcon news2 = new ImageIcon(newsBtnImg1);
			newsButton.setToolTipText("OFFPIST: Nyheter");
			newsButton.setPressedIcon(news2);
			}
			catch (Exception e)
			{
				e.printStackTrace(System.out);
				JOptionPane.showMessageDialog(null, "En fil(mindre viktig) mangler. Reinstaller programmet for bedre opplevelse.", "Feil: manglende fil", JOptionPane.INFORMATION_MESSAGE);
			}

			
		/* Info-button*/
		try{
			infoBtnImg = ImageIO.read( new File("img/info.png"));
			ImageIcon info = new ImageIcon(infoBtnImg);
			infoButton = new JButton(info);
			infoButton.setFocusPainted(false);
			infoButton.setBorderPainted(false);
			infoButton.setContentAreaFilled(false);
			} 
			catch (Exception e)
			{
				e.printStackTrace(System.out);
				JOptionPane.showMessageDialog(null, "En nødvendig fil mangler. Reinstaller programmet.", "Feil: manglende fil", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}

		try{
			infoBtnImg1 = ImageIO.read( new File("img/info2.png"));
			ImageIcon info2 = new ImageIcon(infoBtnImg1);
			infoButton.setToolTipText("OFFPIST: Informasjon");
			infoButton.setPressedIcon(info2);
			} 
			catch (Exception e)
			{
				e.printStackTrace(System.out);
				JOptionPane.showMessageDialog(null, "En fil(mindre viktig) mangler. Reinstaller programmet for bedre opplevelse.", "Feil: manglende fil", JOptionPane.INFORMATION_MESSAGE);
			}

		/* Offers-button */
		try{
			offerBtnImg = ImageIO.read( new File("img/tilbud.png"));
			ImageIcon offer = new ImageIcon(offerBtnImg);
			offersButton = new JButton(offer);
			offersButton.setFocusPainted(false);
			offersButton.setBorderPainted(false);
			offersButton.setContentAreaFilled(false);
			} 
			catch (Exception e)
			{
				e.printStackTrace(System.out);
				JOptionPane.showMessageDialog(null, "En nødvendig fil mangler. Reinstaller programmet.", "Feil: manglende fil", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}

		try{
			offerBtnImg1 = ImageIO.read( new File("img/tilbud2.png"));
			ImageIcon offer2 = new ImageIcon(offerBtnImg1);
			offersButton.setToolTipText("OFFPIST: Tilbud");
			offersButton.setPressedIcon(offer2);
			} 
			catch (Exception e)
			{
				e.printStackTrace(System.out);
				JOptionPane.showMessageDialog(null, "En fil(mindre viktig) mangler. Reinstaller programmet for bedre opplevelse.", "Feil: manglende fil", JOptionPane.INFORMATION_MESSAGE);
			}

		/* Prices-button */
		try{
			pricesBtnImg = ImageIO.read( new File("img/priser.png"));
			ImageIcon price = new ImageIcon(pricesBtnImg);
			pricesButton = new JButton(price);
			pricesButton.setFocusPainted(false);
			pricesButton.setBorderPainted(false);
			pricesButton.setContentAreaFilled(false);
			} 
			catch (Exception e)
			{
				e.printStackTrace(System.out);
				JOptionPane.showMessageDialog(null, "En nødvendig fil mangler. Reinstaller programmet.", "Feil: manglende fil", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}

		try{
			pricesBtnImg1 = ImageIO.read( new File("img/info.png"));
			ImageIcon price2 = new ImageIcon(pricesBtnImg1);
			pricesButton.setToolTipText("OFFPIST: Priser");
			pricesButton.setPressedIcon(price2);
			} 
			catch (Exception e)
			{
				e.printStackTrace(System.out);
				JOptionPane.showMessageDialog(null, "En fil(mindre viktig) mangler. Reinstaller programmet for bedre opplevelse.", "Feil: manglende fil", JOptionPane.INFORMATION_MESSAGE);
			}




		sideMenu.add(newsButton);	
		sideMenu.add(infoButton);
		sideMenu.add(offersButton);
		sideMenu.add(pricesButton);


		GridBagConstraints c = new GridBagConstraints();


		// first column /////////////////////////////
		c.insets = new Insets(0,0,0,0);
		c.fill = GridBagConstraints.BOTH;
		c.gridx=0;
		c.weightx = 0.1;
		c.gridheight = 3;
		c.gridwidth = 1;
		c.gridy=0;

		add(sideMenu, c);


		// second colum /////////////////////////////

		panel = new JPanel()
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

				super.paintComponents(grphcs);
			}

		};


		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.1;
		c.gridheight = 1 ;

		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 0;
		add(panel, c);



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


		c.fill = GridBagConstraints.BOTH;
		contentPanel.setBackground(new Color(220, 240, 255));

		c.gridheight = 2;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.PAGE_START;
		
		c.gridx = 1;
		c.gridy = 1;
		JScrollPane contentScrollPanel = new JScrollPane(contentPanel);
		add(contentScrollPanel, c);


		try{
			ImageIcon image = new ImageIcon("img/offpist_liten.png");
			label = new JLabel(image);
			panel.add(label);
			}
			catch(Exception e)
			{
				e.printStackTrace(System.out);
			}



		newsWindowPnl = new NewsWindowPanel()
		{
   			protected void paintComponent(Graphics g)
   		 	{
        		g.setColor( getBackground() );
        		g.fillRect(0, 0, getWidth(), getHeight());
        		super.paintComponent(g);
    		}
		};
		newsWindowPnl.setOpaque(false);
		newsWindowPnl.setBackground( new Color(0, 0, 0, 0) );


		infoWindowPnl = new InfoWindowPanel()
		{
   			protected void paintComponent(Graphics g)
   		 	{
        		g.setColor( getBackground() );
        		g.fillRect(0, 0, getWidth(), getHeight());
        		super.paintComponent(g);
    		}
		};
		infoWindowPnl.setOpaque(false);
		infoWindowPnl.setBackground( new Color(0, 0, 0, 0) );

		spOffersWindowPnl = new SpOfferWindowPanel()
		{
   			protected void paintComponent(Graphics g)
   		 	{
        		g.setColor( getBackground() );
        		g.fillRect(0, 0, getWidth(), getHeight());
        		super.paintComponent(g);
    		}
		};
		spOffersWindowPnl.setOpaque(false);
		spOffersWindowPnl.setBackground( new Color(0, 0, 0, 0) );

		pricesWindowPnl = new PricesWindowPanel()
		{
   			protected void paintComponent(Graphics g)
   		 	{
        		g.setColor( getBackground() );
        		g.fillRect(0, 0, getWidth(), getHeight());
        		super.paintComponent(g);
    		}
		};
		pricesWindowPnl.setOpaque(false);
		pricesWindowPnl.setBackground( new Color(0, 0, 0, 0) );


		contentPanel.add(newsWindowPnl);
		contentPanel.add(infoWindowPnl);
		contentPanel.add(spOffersWindowPnl);
		contentPanel.add(pricesWindowPnl);




		Border raisedBevel = BorderFactory.createRaisedBevelBorder();
		panel.setBorder(raisedBevel);

		newsWindowPnl.setVisible(true);
		infoWindowPnl.setVisible(false);
		spOffersWindowPnl.setVisible(false);
		pricesWindowPnl.setVisible(false);

		listener = new Listener(); 
		newsButton.addActionListener( listener );
		infoButton.addActionListener( listener );
		offersButton.addActionListener( listener );
		pricesButton.addActionListener( listener );

		getContentPane();



		String imagefile = "img/offpist_logo.png";
		Image icon = toolbox.getImage(imagefile);
		setIconImage(icon);
		//Display the window.
		pack();
		setSize(WIDTH,HEIGHT);
		setVisible(true);
	}


	 /**
	  * This Listener makes changing the content possible by clicking buttons.
	  */
	private class Listener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			newsWindowPnl.setVisible(false);
			infoWindowPnl.setVisible(false);
			spOffersWindowPnl.setVisible(false);
			pricesWindowPnl.setVisible(false);


			if( e.getSource() == newsButton )
			{
				newsWindowPnl.setVisible(true);
			}
				
			else if( e.getSource() == infoButton)
			{
				infoWindowPnl.setVisible(true);
			}

			else if( e.getSource() == offersButton)
			{
				spOffersWindowPnl.setVisible(true);
			}

			else if( e.getSource() == pricesButton)
			{
				pricesWindowPnl.setVisible(true);
			}

		}
	} //end of class Listener
} //end of class Info