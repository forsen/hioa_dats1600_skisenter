import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Iterator;

public class AdminStatistikkPanel extends JPanel
{	
	private JTextField fromFld, toFld, dayLfd,monthFld, yearFld, liftFLd;
	private JButton calculateBtn, graphBtn, searchBtn;
	private JTextArea display;
	private JPanel fieldPnl, choicePnl, dispPnl, graphPnl;
	private Listener listener;
	private JScrollPane scroll;
	private Personlist list;
	private JCheckBox sold, cutomers, passings, revenue;
	private CheckListner checklistner;
	private Calculator cal;
	private List<Validations> validations;

	public AdminStatistikkPanel(Personlist l,List<Validations> v )
	{
		list = l;
		validations = v;
		cal = new Calculator(list, validations);
		fieldPnl = new JPanel();
		choicePnl = new JPanel(new GridLayout( 5,10 ));
		dispPnl = new JPanel();
		graphPnl = new GraphPanel();
		setLayout( new BorderLayout( 5, 8) );

		listener = new Listener();
		checklistner = new CheckListner();

		fieldPnl.add( new JLabel( "Fra: " ) );
		fromFld = new JTextField(4);
		fromFld.setEditable( true );
		fieldPnl.add(fromFld);

		fieldPnl.add( new JLabel( "Til: " ) );
		toFld = new JTextField(4);
		toFld.setEditable( true );
		fieldPnl.add(toFld);

		fieldPnl.add( new JLabel( "Dag " ) );
		dayLfd = new JTextField(4);
		dayLfd.setEditable( true );
		fieldPnl.add(dayLfd);		

		fieldPnl.add( new JLabel( "Måned " ) );
		monthFld = new JTextField(4);
		monthFld.setEditable( true );
		fieldPnl.add(monthFld);

		fieldPnl.add( new JLabel( "år " ) );
		yearFld = new JTextField(4);
		yearFld.setEditable( true );
		fieldPnl.add(yearFld);

		fieldPnl.add( new JLabel( "Heisnr: " ) );
		liftFLd = new JTextField(4);
		liftFLd.setEditable( true );
		fieldPnl.add(liftFLd);

		searchBtn = new JButton( "Søk" );
		searchBtn.addActionListener( listener );
		choicePnl.add(searchBtn);

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

		add(fieldPnl, BorderLayout.PAGE_START);
		add(choicePnl, BorderLayout.CENTER);
		add(dispPnl, BorderLayout.PAGE_END);

	}

	public void totalRegPepole()
	{
		display.append("\nTotalt registrerte personer er: " + cal.totalRegPepole());
	}

	public void regThatTime()
	{
		try
		{
			int nr = Integer.parseInt(monthFld.getText());

			display.append("\nAntall personer som ble registrert i månede " + nr + " er " + cal.regThatTime(nr));
		}
		catch(NullPointerException npe)
		{
			display.setText("Må sette inn en Måned");
		}
		catch(NumberFormatException nfe)
		{
			display.setText("Må sette inn en tall");
		}
	}

	public void passings()
	{
		String lift = liftFLd.getText();
   		if(!(lift.isEmpty()))
   		{
   			int liftnr = Integer.parseInt(lift);
   			display.append("\nAntall passeringer gjennom heis nummer " + lift + " er " +cal.passesbyTypeofCard( liftnr));
   					
   		}
   		else display.append("\nAntall passeringer gjennom alle heiser er " + cal.showPassings());
	}

	public void revenue()
	{
		display.append("\nTotal omsetning er "+  cal.totalCost() + " KR");
	}
	private class Listener implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 	
      		
      		if(e.getSource() == calculateBtn)
      		{
      			display.setVisible( true );
      			graphPnl.setVisible(false);
      		}
      		if ( e.getSource()== graphBtn )
       		{
       			dispPnl.add(graphPnl);
       			graphPnl.setVisible(true);
       			display.setVisible( false );
       			
       		}
       		if(e.getSource() == searchBtn)
      		{
      			regThatTime();
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
      			totalRegPepole();

      		}
     			
   			else if ( passings.isSelected() )
   			{		
				
   				passings();
   				
   			}
   			else if(revenue.isSelected())
   			{
   				revenue();
   			}


   		}
 	}	
}
	
