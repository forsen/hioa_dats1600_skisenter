import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class Admin extends JFrame
{
	private JButton beregnbn;
	private JTextField fromfld, tofld;
	private JTextArea display;
	private Personlist list;
	private Lytter listener;
	private JCheckBox sale, passes;
	private CheckListner checkListner;
	private String[] type = {"Barn", "Innmeldt", "alle"};
	private JComboBox<String> box;

	public Admin(Personlist l)
	{
		super("Statistikk");
		list = l;
		listener = new Lytter();
		checkListner = new CheckListner();
		box = new JComboBox<String>(type);
		


		Container c = getContentPane();
   		c.setLayout( new FlowLayout() );

   		
		c.add(new JLabel("Fra: "));
		fromfld = new JTextField(4);
		fromfld.setEditable( true );
		c.add(fromfld);

		
		c.add(new JLabel("Til: "));
		tofld = new JTextField(4);
		tofld.setEditable( true );
		c.add(tofld);

		sale = new JCheckBox("Salg");
		sale.addItemListener(checkListner);
		c.add(sale);

		passes = new JCheckBox("Passeringer");
		passes.addItemListener(checkListner);
		c.add(passes);

		box.setSelectedIndex(0);
		c.add(box);
		
		
		display = new JTextArea(15,30);
		display.setEditable( false );
		display.setText("Her kommer statestikk med piechart o.l");
		c.add(display);


		beregnbn = new JButton("Beregn");
		beregnbn.addActionListener( listener );
    	c.add( beregnbn );


		setSize( 400, 380 );
    	setVisible(true);
	}

	public void saleReport()
	{
		int from = 0;
		int to = 0;
		try
		{
			from = Integer.parseInt(fromfld.getText());
			to = Integer.parseInt(tofld.getText());

			display.setText("detta gikk ");		}
		catch(NullPointerException npe)
		{
			display.setText("tullbaøø");
		}
		catch(NumberFormatException nfe)
		{
			display.setText("Det må være tall");
		}

		
		

		display.setText("Du må fylle inn begge felter ");
		


		 
	}
	public void beregn()
	{	
		saleReport();

	}

	
 	

  	private class Lytter implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 
     		if ( e.getSource() == beregnbn )
      		{
       			beregn();
      		}
      		
      		JComboBox<String> box1 = (JComboBox<String>) e.getSource();
      		int n = box1.getSelectedIndex();
      		String type = box1.getItemAt(n);
      		beregn();
    	}
	}

	private class CheckListner implements ItemListener
	{
		public void itemStateChanged( ItemEvent e )
		{
			if(sale.isSelected())
				saleReport();

			else if (passes.isSelected())
				beregn();
		}
	}
}
