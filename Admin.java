import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class Admin extends JFrame
{
	private JButton beregnbn;
	private JTextField from, to;
	private JTextArea display;
	private Personlist list;
	private Lytter listener;
	private JCheckBox sale, passes;
	private CheckListner checkListner;
	private String[] type = {"Barn", "Innmeldt", "alle"};
	private JComboBox<String> box;

	public Admin(Personlist l)
	{
		super("Stitestikk");
		list = l;
		listener = new Lytter();
		checkListner = new CheckListner();
		box = new JComboBox<String>(type);
		


		Container c = getContentPane();
   		c.setLayout( new FlowLayout() );

   		
		c.add(new JLabel("Fra: "));
		from = new JTextField(4);
		from.setEditable( true );
		c.add(from);

		
		c.add(new JLabel("Til: "));
		to = new JTextField(4);
		to.setEditable( true );
		c.add(to);

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
		display.setText("Her kommer statestikk med paichart o.l");
		c.add(display);


		beregnbn = new JButton("Beregn");
		beregnbn.addActionListener( listener );
    	c.add( beregnbn );


		setSize( 400, 380 );
    	setVisible(true);
	}

	public void beregn()
	{

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
				beregn();

			else if (passes.isSelected())
				beregn();
		}
	}
}
