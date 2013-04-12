import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class Admin extends JFrame
{	
	private JPanel framePnl,topPnl,adminInfoPnl, statInfoPnl;
	private JButton admInfoBtn, admStatBtn;
	private Listner listner;
	private Personlist list;
	private Container c;
	private BorderLayout layout;
	

	public Admin(Personlist l)
	{
		super("Administrator");
		list = l;
		topPnl = new JPanel( new FlowLayout() );
		framePnl = new JPanel(new FlowLayout());
		adminInfoPnl = new AdminInfoPanel(list);
		statInfoPnl = new JPanel();
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
       			System.out.println("du trykka på knappen");
      		}

      		if( e.getSource() == admStatBtn)
      		{
      			statInfoPnl.setVisible(true);
      		}
      		
      
    	}
	}
}
