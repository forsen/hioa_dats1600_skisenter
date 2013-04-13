import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class AdminStatistikkPanel extends JPanel
{	
	private JTextField fromFld, toFld;
	private JButton calculateBtn, graphBtn;
	private JTextArea display;
	private JPanel choicePnl, dispPnl;
	private Listener listener;
	private JScrollPane scroll;
	private Personlist list;
	private JCheckBox sold, cutomers, passings, revenue;
	private CheckListner checklistner;

	public AdminStatistikkPanel(Personlist l )
	{
		list = l;
	
		choicePnl = new JPanel(new GridLayout( 5,4 ));
		dispPnl = new JPanel();
		
		setLayout( new BorderLayout( 5, 5) );

		listener = new Listener();
		checklistner = new CheckListner();

		choicePnl.add( new JLabel( "Fra: " ) );
		fromFld = new JTextField(4);
		fromFld.setEditable( false );
		choicePnl.add(fromFld);

		choicePnl.add( new JLabel( "Til: " ) );
		toFld = new JTextField(4);
		toFld.setEditable( false );
		choicePnl.add(toFld);

		sold = new JCheckBox( "Solgte kort" );
		sold.addItemListener( checklistner );
		choicePnl.add(sold);

		cutomers = new JCheckBox( "Opprettede kunder" );
		cutomers.addItemListener( checklistner );
		choicePnl.add(cutomers);

		passings = new JCheckBox( "Passeringer i heis" );
		passings.addItemListener( checklistner );
		choicePnl.add(passings);

		revenue = new JCheckBox( "Omsetning" );
		revenue.addItemListener( checklistner );
		choicePnl.add(revenue);

		display = new JTextArea(20,40);
		display.setEditable( false );

		scroll = new JScrollPane(display);

		graphBtn = new JButton( "Graf" );
		graphBtn.addActionListener( listener );
		choicePnl.add(graphBtn);

		calculateBtn = new JButton(" Beregn ");
		calculateBtn.addActionListener( listener );
		choicePnl.add(calculateBtn);


		dispPnl.add(display);

		add(choicePnl, BorderLayout.PAGE_START);
		add(dispPnl);

	}

	private class Listener implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 	
      		
      		if(e.getSource() == calculateBtn)
      		{
      			//display.setText("Her skal det beregnes det noe ");
      		}
      		if ( e.getSource()== graphBtn )
       		{
       			dispPnl.add(new GraphPanel());
       			display.setVisible( false );

       			
       		}
	
    	}
	}

	private class CheckListner implements ItemListener
	{
       public void itemStateChanged( ItemEvent e )
       {
       		
       		if ( sold.isSelected() )
       		{

       		}	
      		else if ( cutomers.isSelected() )
      		{

      		}
     			
   			else if ( passings.isSelected() )
   			{

   			}	


   		}
 	}	
}
	
