import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class Drittvindu extends JFrame
{
	private JButton persListbn, cardListbn, personWithCardbn;
	private JTextArea persontxt, cardtxt, persWcardtxt;
	private Personlist list;


	private Lytter listener;

	public Drittvindu(Personlist l)
	{
		super("Testvindu");
		list = l;
		
		listener = new Lytter();

		Container c = getContentPane();
   		c.setLayout( new FlowLayout() );

		persListbn = new JButton("Vis personliste");
		persListbn.addActionListener( listener );
    	c.add( persListbn );

		cardListbn = new JButton("Vis Skikortliste");
		cardListbn.addActionListener( listener );
    	c.add( cardListbn );

		personWithCardbn = new JButton("Vis personer med kort");
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
		c.add(persWcardtxt);



		setSize( 600, 700 );
    	setVisible(true);
	}


	public void showPersons()
	{
		//persontxt.setText(personListe());
	}

	public void showCards()
	{
		cardtxt.setText("Her kommer lista");
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