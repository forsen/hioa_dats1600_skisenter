import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Graphics;
import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Info extends JFrame 
{
	public final static int PUNCHCARDPRICE = 200;
	public final static int DAYCARDPRICE = 320;
	public final static int HOURCARDPRICE = 120;
	public final static int SEASONCARDPRICE = 3000;
	public final static int CHILDLIMIT = 16;
	public final static double DISCOUNT = 0.5;
	public final static int CARDPRICE = 70; 
	public final static int RETURNPRICE = -50; 
	public final static int LIFTS = 2; 
	public final static double GROUPDISCOUNT = 0.9;
	public final static double FREAKYFRIDAY = 0.5; 
	
	public static Date firstLight;

	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private static Listener listener;
    private static JLabel label;
    private static JButton button, newsButton, infoButton, offersButton, pricesButton;
    private static JPanel newsWindowPnl, infoWindowPnl, spOffersWindowPnl, panel, sideMenu, pricesWindowPnl, contentPanel;
 
    public Info()
    {

    	try
		{
			firstLight = new SimpleDateFormat("ddMMyy").parse("010105");
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


		ImageIcon news = new ImageIcon("img/nyheter.png");
		newsButton = new JButton(news);
		newsButton.setToolTipText("OFFPIST: Nyheter");
		ImageIcon news2 = new ImageIcon("img/nyheter2.png");
		newsButton.setPressedIcon(news2);
		newsButton.setFocusPainted(false);
		newsButton.setBorderPainted(false);
		newsButton.setContentAreaFilled(false);

		sideMenu.add(newsButton);

		ImageIcon info = new ImageIcon("img/info.png");
		infoButton = new JButton(info);
		infoButton.setToolTipText("OFFPIST: Informasjon");
		ImageIcon info2 = new ImageIcon("img/info2.png");
		infoButton.setPressedIcon(info2);
		infoButton.setFocusPainted(false);
		infoButton.setBorderPainted(false);
		infoButton.setContentAreaFilled(false);

		sideMenu.add(infoButton);

		ImageIcon offer = new ImageIcon("img/tilbud.png");
		offersButton = new JButton(offer);
		offersButton.setToolTipText("OFFPIST: Tilbud");
		ImageIcon offer2 = new ImageIcon("img/tilbud2.png");
		offersButton.setPressedIcon(offer2);
		offersButton.setFocusPainted(false);
		offersButton.setBorderPainted(false);
		offersButton.setContentAreaFilled(false);

		sideMenu.add(offersButton);

	

		ImageIcon price = new ImageIcon("img/priser.png");
		pricesButton = new JButton(price);
		pricesButton.setToolTipText("OFFPIST: Priser");
		ImageIcon price2 = new ImageIcon("img/priser2.png");
		pricesButton.setPressedIcon(price2);
		pricesButton.setFocusPainted(false);
		pricesButton.setBorderPainted(false);
		pricesButton.setContentAreaFilled(false);

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


		ImageIcon image = new ImageIcon("img/offpist_liten.png");
		label = new JLabel("<html>Granskogen 1 - Tlf: 22 33 44 55</html>", image, JLabel.CENTER);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setFont(new Font("Calibri", Font.PLAIN, 15));
		label.setForeground(Color.WHITE);

		panel.add(label);



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
		setSize(1000,1000);
		setVisible(true);
	}

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
	}
}