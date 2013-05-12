import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Iterator;

/**
  * Admin is a window class for the admin interface.
  * Admin puts a JFrame on your screen, with several different
  * panels in JTabbedPane
  *
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  */
public class Admin extends JFrame
{	

	private JPanel framePnl,topPnl,adminInfoPnl, statInfoPnl;
	private JButton admInfoBtn, admStatBtn;
	private Personlist list;
	private Container c;
	private BorderLayout layout;
	private List<Validations> validations;
	private Cardlist cardregistry;
	private JTabbedPane adminTabs;
	

	
/**
  * This constructor sets up the window. 
  * @param l 	the person registry
  * @param v 	the validations registry
  * @param cl 	the cardlist registry (unregistered cards)
  * @see Personlist
  * @see Validations
  * @see Cardlist
  */ 	

	public Admin(Personlist l,List<Validations> v, Cardlist cl) 
	{
		super("Administrator");
		list = l;
		validations = v;
		cardregistry = cl;

		framePnl = new JPanel()
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

		adminInfoPnl = new AdminInfoPanel(list, validations, cardregistry);
		statInfoPnl = new AdminStatisticsPanel(list, validations, cardregistry );
		
		layout = new BorderLayout( 5, 5 );
		
		c = getContentPane();
		

		adminTabs = new JTabbedPane();
		framePnl.add(adminTabs);

		ImageIcon info = new ImageIcon("img/infoadmin.png");
		
		ImageIcon statistics = new ImageIcon("img/statistikkadmin.png");
		
		adminTabs.addTab("Info", info, adminInfoPnl);
		adminTabs.addTab("Statistikk", statistics, statInfoPnl );

		c.setLayout( layout );
		c.add(framePnl );

		framePnl.setVisible(true);

		layout.layoutContainer( c );

		setSize( 830, 780 );
	}
}
// end of class Admin