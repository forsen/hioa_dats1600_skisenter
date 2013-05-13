import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.*;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;
import javax.imageio.*;

/**
  * Salesclerk is the class that creates the sale window. 
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

public class Salesclerk extends JFrame
{

	private final int DELAY = 5; // hvor mange sekunder en melding til statustxt skal vises
	private final int LEFT = 20;
	private final int RIGHT = 40;
	private JButton custWindowBtn, salesWindowBtn, replaceWindowBtn, nextCustBtn;
	private JPanel custWindowPnl, salesWindowPnl, replaceWindowPnl, statusPnl; 
	private JPanel framePnl, salesClerkCustInfoPnl;
	private BufferedImage nextCustBtnImg;
	public static JTextArea statusTxt;

	//replaceWindowTxt

	public static Person customer = null; 
	public static JTextArea salesClerkSearchInfoTxt;

	private Listener listener;

	private Personlist custRegistry; 
	private Cardlist cardregistry;

	private JTabbedPane salesClerkTabs;

	private Toolkit toolbox;

	private Container c;
	private BorderLayout layout;

/**
  * The constructor creates all the elements and place them in the frame.
  * It also contains a method to set the time for updating status field.
  * @param cr 		the customer registry
  * @param cardr 	the card registry
  * @param m 		the status panel text
  */

	public Salesclerk( Personlist cr, Cardlist cardr, String m )
	{
		super("Salgsvindu");




		getContentPane().setBackground(new Color(199,214, 226));
		toolbox = Toolkit.getDefaultToolkit();


		setBackground(new Color(200, 230, 255));
		framePnl = new JPanel()
		{
	/**
    * Paints the panel, so it appears like a gradient bakground.
	*/
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

		salesClerkSearchInfoTxt = new JTextArea( 5, 20 );
		salesClerkSearchInfoTxt.setEditable(false);
		salesClerkSearchInfoTxt.setToolTipText("Info om kunden du hjelper");

		Dimension windowDimension = toolbox.getScreenSize();

		int height = windowDimension.height;
		int width = windowDimension.width; 

		setSize(880, 580);
		setLocationByPlatform( true );

		custRegistry = cr; 
		cardregistry = cardr;

		salesClerkCustInfoPnl = new JPanel( new BorderLayout() );


		//Will not use "try-catch" here, because the panetabs will appear fine, even 
		//though the pictures cannot be found.
		ImageIcon kunde = new ImageIcon("img/kunde.png");
		ImageIcon salg = new ImageIcon("img/salg.png");
		ImageIcon erstatt = new ImageIcon("img/erstatt.png");

		try{
			nextCustBtnImg = ImageIO.read( new File("img/neste.png"));
			ImageIcon next = new ImageIcon(nextCustBtnImg);
			nextCustBtn = new JButton(next);
			nextCustBtn.setToolTipText("For å ekspedere neste kunde");
			}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
			nextCustBtn = new JButton("Neste kunde");
		}
		
		ImageIcon next2 = new ImageIcon("img/neste2.png");
		nextCustBtn.setRolloverIcon(next2);

		nextCustBtn.setFocusPainted(false);
		nextCustBtn.setContentAreaFilled(false);
		nextCustBtn.setBorderPainted(false);

		salesClerkCustInfoPnl.add( nextCustBtn, BorderLayout.PAGE_START );
		salesClerkCustInfoPnl.add( salesClerkSearchInfoTxt, BorderLayout.CENTER );

		statusTxt = new JTextArea(5,50){
  
  /**
	* This method is for clearing the status field every fifth second.
	* 
	*/
			@Override
			public void setText( String t )
			{
				super.setText( t );
				if( t != null )
				{
					Timer timer = new Timer(); 
					timer.schedule( new ClearStatusTxt(), DELAY*1000 );
				}
			}
		};

		statusTxt.setBackground(new Color(238, 248, 255));
		statusTxt.setEditable( false );


		custWindowPnl = new CustWindowPanel( custRegistry );

		salesWindowPnl = new SalesWindowPanel(cardregistry);

		replaceWindowPnl = new ReplaceWindowPanel();

		listener = new Listener(); 

		layout = new BorderLayout( 5, 5 );
		c = getContentPane();
		
		c.setLayout( layout );
		c.add(framePnl, BorderLayout.LINE_START );
		c.add(salesClerkCustInfoPnl, BorderLayout.CENTER );


		c.add(statusTxt, BorderLayout.PAGE_END );

		nextCustBtn.addActionListener(listener);


		salesClerkTabs = new JTabbedPane();
		salesClerkTabs.addTab( "Kunde", kunde, custWindowPnl );
		salesClerkTabs.addTab( "Nysalg", salg, salesWindowPnl);
		salesClerkTabs.addTab( "Erstatt", erstatt, replaceWindowPnl );
		framePnl.add(salesClerkTabs);

		statusTxt.setText(m);

		layout.layoutContainer( c );
		
	}

/**
  * This method is being excecuted by the seller when they are done with a registry or sale, and want to 
  * move on to the next customer.
  * It clears all the fields in the window, so the window is ready for new input.
  */

	private void clearSearch()
	{
		
		customer = null;
		CustWindowPanel.blankOut();
		SalesWindowPanel.salesWindowCustIDtf.setText( "");
		SalesWindowPanel.cardnrf.setText("");
		SalesWindowPanel.cardnrf.setEditable( true );
		salesClerkSearchInfoTxt.setText("");
		try
		{
			SalesWindowPanel.cardIDList.setModel( new DefaultListModel<Card>());
			ReplaceWindowPanel.replaceWindowCustIdtf.setText("");
			ReplaceWindowPanel.cardIDList.setModel( new DefaultListModel<Card>());
			salesClerkSearchInfoTxt.setBackground(Color.WHITE);
			CustWindowPanel.clearSearch(); 
			SalesWindowPanel.shoppingCartList.setModel(ShoppingCart.emptyCart());
			SalesWindowPanel.updateCartPrice();


		}
		catch(IllegalArgumentException iae)
		{
			System.out.println("Something went wrong");
		}
		
		
	}


/**
  * This Listener is for the tabbedpane, and the "next customer"-button.
  */


	private class Listener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			salesWindowPnl.setVisible(false);
			custWindowPnl.setVisible(false);
			replaceWindowPnl.setVisible(false);

			if( e.getSource() == nextCustBtn)
			{
				salesClerkTabs.setSelectedIndex(0);

				clearSearch();
			}
				

		}
	}

/**
  * This class uses time to clear the status field. It clears the status field 1000 ms * DELAY.
  *
  * @see Salesclerk#setText()
  */

	private class ClearStatusTxt extends TimerTask
	{
		public void run()
		{
			statusTxt.setText( null );
		}
	} //end of class ClearStatusTct

} //end of class Salesclerk