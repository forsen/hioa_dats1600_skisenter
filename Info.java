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

public class Info extends JFrame
{
	public final static int PUNCHCARDPRICE = 200;
	public final static int DAYCARDPRICE = 320;
	public final static int HOURCARDPRICE = 120;
	public final static int SEASONCARDPRICE = 3000;
	public final static int CHILDLIMIT = 16;
	public final static double DISCOUNT = 0.5;

	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private static JButton button, newsButton, infoButton, offersButton, pricesButton;
    private static JPanel newsWindowPnl, infoWindowPnl, spOffersWindowPnl, pricesWindowPnl;
 
    public Info()
    {

		JPanel contentPanel;
		Listener listener;
		JPanel sideMenu;
		JPanel panel;
		JPanel image;
		JLabel label;

		GridBagLayout gbl = new GridBagLayout();

		setLayout(gbl);
/*
		gbl.layoutContainer( this );
		double[][] weights = gbl.getLayoutWeights();
		for (int i = 0; i < 2; i++) 
		{
			for (int j = 0; j < weights[i].length; j++) 
			{
				weights[i][j] = 1;
			}
		}
		
		gbl.columnWeights = weights[0];
		gbl.rowWeights = weights[1];

*/
		//GridBagConstraints sc = new GridBagConstraints();





		Toolkit verktoykasse = Toolkit.getDefaultToolkit();

		// første kolonne /////////////////////////////


		sideMenu = new JPanel();
		sideMenu.setLayout( new GridLayout( 4,1 ) );


		ImageIcon nyheter = new ImageIcon("img/nyheter.png");
		newsButton = new JButton(nyheter);
		ImageIcon nyheter2 = new ImageIcon("img/nyheter2.png");
		newsButton.setRolloverIcon(nyheter2);
		newsButton.setFocusPainted(false);
		newsButton.setBorderPainted(false);
		newsButton.setContentAreaFilled(false);
/*

		sc.fill = GridBagConstraints.BOTH;
		sc.gridx = 0;
		sc.gridy = 0;*/
		sideMenu.add(newsButton);

		ImageIcon info = new ImageIcon("img/info.png");
		infoButton = new JButton(info);
		ImageIcon info2 = new ImageIcon("img/info2.png");
		infoButton.setRolloverIcon(info2);
		infoButton.setFocusPainted(false);
		infoButton.setBorderPainted(false);
		infoButton.setContentAreaFilled(false);
/*
		sc.fill = GridBagConstraints.BOTH;
		sc.weightx = 0.5;
		sc.gridx = 0;
		sc.gridy = 1;*/
		sideMenu.add(infoButton);

		ImageIcon tilbud = new ImageIcon("img/tilbud.png");
		offersButton = new JButton(tilbud);
		ImageIcon tilbud2 = new ImageIcon("img/tilbud2.png");
		offersButton.setRolloverIcon(tilbud2);
		offersButton.setFocusPainted(false);
		offersButton.setBorderPainted(false);
		offersButton.setContentAreaFilled(false);
/*		sc.fill = GridBagConstraints.BOTH;
		sc.weightx = 0.5;
		sc.gridx = 0;
		sc.gridy = 2;*/
		sideMenu.add(offersButton);

	

		ImageIcon priser = new ImageIcon("img/priser.png");
		pricesButton = new JButton(priser);
		ImageIcon priser2 = new ImageIcon("img/priser2.png");
		pricesButton.setRolloverIcon(priser2);
		pricesButton.setFocusPainted(false);
		pricesButton.setBorderPainted(false);
		pricesButton.setContentAreaFilled(false);
/*		sc.fill = GridBagConstraints.BOTH;
		sc.weightx = 0.5;
		sc.gridx = 0;
		sc.gridy = 3;*/
		sideMenu.add(pricesButton);
		//sideMenu.setBackground(Color.RED);		muligens legge på en gradient her seinere

		GridBagConstraints c = new GridBagConstraints();
		//sideMenu.setPreferredSize(new Dimension(100, 800)); //finn en bedre måte å gjøre dette på. Skalering vil ødelegge
		//c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0,0,0,0);
		c.gridx=0;
		c.weightx = 0.1;
		c.gridheight = 3;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridy=0;

		add(sideMenu, c);

	



		// andre kolonne /////////////////////////////

		panel = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		panel.setBackground(new Color(200, 230, 255));
		c.weightx = 1.0;
		c.weighty = 0.1;
		c.gridheight = 1 ;
		//	c.gridwidth = 2;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 0;
		add(panel, c);



		contentPanel = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		contentPanel.setBackground(new Color(220, 240, 255));
	//	c.weightx = 1.0;
		c.gridheight = 2;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.PAGE_START;
		//	c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = 1;
		JScrollPane contentScrollPanel = new JScrollPane(contentPanel);
		add(contentScrollPanel, c);

		image = new JPanel(new BorderLayout());
		label = new JLabel(new ImageIcon("img/offpist_liten.png"));

		panel.add(label);




		// tredje kolonne/////////////

		//// fredje kolonne///////////////////////



		newsWindowPnl = new NewsWindowPanel();
		infoWindowPnl = new InfoWindowPanel();
		spOffersWindowPnl = new SpOfferWindowPanel();
		pricesWindowPnl = new PricesWindowPanel();


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



		//Set up the content pane.
		getContentPane();



		String bildefil = "img/offpist_logo.png";
		Image ikon = verktoykasse.getImage(bildefil);
		setIconImage(ikon);
		//Display the window.
		pack();
		setSize(1000,1000);
		setVisible(true);
	}

	// public void paint( Graphics g ) 
	// { 
	// 	super.paint(g);
	// 	g.setColor(new Color(230,245,255));
	// 	g.fillRect(0,0, getWidth(), getHeight());
		

	// 	int[]x={0,0,getWidth()};

	// 	int[]y={0,1400,getHeight()}; 

	// 	g.setColor(new Color(235,250,255));
	// 	g.fillPolygon(x,y,3);	

	// 	g.setColor(new Color(238, 238, 238));
	// 	g.fillRect(0,0, getWidth(), 100);

	// }

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




	// fjerde kolonne/////////////


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */




}/*<datafelt, inkludert statiske priskonstanter som brukes av de forskjellige heiskortklassene>

	<konstruktør som oppretter vindu>

	<grafiske metoder som vi enda ikke har lært (vi ønsker å ha et menybasert system, som endrer 
		hovedinnhold basert på hvilken knapp man trykker)>

	<metode for å hente ut informasjon om åpne heiser>

	<diverse knappelyttere og slikt>*/
