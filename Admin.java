import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Iterator;

public class Admin extends JFrame
{	
	private JPanel framePnl,topPnl,adminInfoPnl, statInfoPnl;
	private JButton admInfoBtn, admStatBtn;
	private Listner listner;
	private Personlist list;
	private Container c;
	private BorderLayout layout;
	private List<Validations> validations;
	
	

	public Admin(Personlist l,List<Validations> v) 
	{
		super("Administrator");
		list = l;
		validations = v;

		topPnl = new JPanel( new FlowLayout() );
		framePnl = new JPanel(new FlowLayout());
		adminInfoPnl = new AdminInfoPanel(list, validations);
		statInfoPnl = new AdminStatistikkPanel(list, validations );
		listner = new Listner();

		layout = new BorderLayout( 5, 5 );
		c = getContentPane();

		admInfoBtn = new JButton("Info");
		admInfoBtn.addActionListener(listner);	
		admStatBtn = new JButton("Statistikk");
		admStatBtn.addActionListener(listner);	

		topPnl.add(admInfoBtn);
		topPnl.add(admStatBtn);
		
		framePnl.add(adminInfoPnl);
		framePnl.add(statInfoPnl );

		c.setLayout( layout );
		c.add(topPnl, BorderLayout.PAGE_START );
		c.add(framePnl );

		framePnl.setVisible(true);
		adminInfoPnl.setVisible(true);
		statInfoPnl.setVisible(false);

		layout.layoutContainer( c );

		setVisible(true);
		setSize( 400, 380 );

	}

	private class Listner implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 
     		
    		adminInfoPnl.setVisible(false);
			statInfoPnl.setVisible(false);
			
     		if ( e.getSource() == admInfoBtn )
      		{
       			adminInfoPnl.setVisible(true);
      		}

      		if( e.getSource() == admStatBtn)
      		{
      			statInfoPnl.setVisible(true);
      		}
    	}
	}
}
