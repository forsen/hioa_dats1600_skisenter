import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Iterator;

public class Drittvindu extends JFrame
{
	private JButton persListbn, cardListbn, personWithCardbn;
	private JTextArea persontxt, cardtxt, persWcardtxt;
	private Personlist list;
	private JScrollPane scroll;
	private List<Validations> validations;
	private Cardlist cardregistry;


	private Lytter listener;

	public Drittvindu(List<Validations> lv, Personlist l, Cardlist cl)
	{
		super("Testvindu");
		list = l;
		validations = lv;
		cardregistry = cl;
		listener = new Lytter();

		Container c = getContentPane();
   		c.setLayout( new FlowLayout() );


   		ImageIcon visPersonliste1 = new ImageIcon("vispersliste.png");
		persListbn = new JButton(visPersonliste1);
		ImageIcon visPersonliste2 = new ImageIcon("vispersliste2.png");
		persListbn.setRolloverIcon(visPersonliste2);
		persListbn.setFocusPainted(false);
		persListbn.setContentAreaFilled(false);
		persListbn.setBorderPainted(false);
		persListbn.addActionListener( listener );
    	c.add( persListbn );

		ImageIcon visKortliste1 = new ImageIcon("viskortliste.png");
		cardListbn = new JButton(visKortliste1);
		ImageIcon visKortliste2 = new ImageIcon("viskortliste2.png");
		cardListbn.setRolloverIcon(visKortliste2);
		cardListbn.setFocusPainted(false);
		cardListbn.setContentAreaFilled(false);
		cardListbn.setBorderPainted(false);
		cardListbn.addActionListener( listener );
    	c.add( cardListbn );

    	ImageIcon visPersKortliste1 = new ImageIcon("visperskortliste.png");
		personWithCardbn = new JButton(visPersKortliste1);
		ImageIcon visPersKortliste2 = new ImageIcon("visperskortliste2.png");
		personWithCardbn.setRolloverIcon(visPersKortliste2);
		personWithCardbn.setFocusPainted(false);
		personWithCardbn.setContentAreaFilled(false);
		personWithCardbn.setBorderPainted(false);
		personWithCardbn.addActionListener( listener );
    	c.add( personWithCardbn );

		persontxt = new JTextArea(40,15);
		persontxt.setEditable( false );
		c.add(persontxt);
		
		cardtxt = new JTextArea(40,15);
		cardtxt.setEditable( false );
		c.add(cardtxt);

		persWcardtxt = new JTextArea(40,15);
		persWcardtxt.setEditable( false );
		scroll = new JScrollPane(persWcardtxt);
		c.add(scroll);

		


		setSize( 600, 700 );
    	setVisible(true);
	}


	public void showPersons()
	{
		persontxt.setText(list.personListe());
	}

	public void showCards()
	{
		cardtxt.setText(cardregistry.toString());
	}

	public void showPersWithCards()
	{
		list.sort();
		persWcardtxt.setText(list.toString());

	}
 	

  	private class Lytter implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{
	
     		 
     		if ( e.getSource() == persListbn )
      		{
       			showPersons();
      		}
      		else if ( e.getSource() == cardListbn)
      		{
        		showCards();
      		}
      		else if ( e.getSource() == personWithCardbn)
      		{
        		showPersWithCards();
      		}
      
     
    	}
	}
}