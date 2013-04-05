import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class Drittvindu extends JFrame
{
	private JButton showPersList, showCardList, showPersonWithCard;
	private JTextArea personDisp, cardDisp, persWCardDisp;
	private Personlist list;
	private Lytter listener;

	public Drittvindu(Personlist l)
	{
		super("Testvindu");
		list = new Personlist();
		listener = new Lytter();

		Container c = getContentPane();
   		c.setLayout( new FlowLayout() );

		showPersList = new JButton("Vis personliste");
		showPersList.addActionListener( listener );
    	c.add( showPersList );

		showCardList = new JButton("Vis Skikortliste");
		showCardList.addActionListener( listener );
    	c.add( showCardList );

		showPersonWithCard = new JButton("Vis personer med kort");
		showPersonWithCard.addActionListener( listener );
    	c.add( showPersonWithCard );

		personDisp = new JTextArea(20,40);
		personDisp.setEditable( false );
		
		cardDisp = new JTextArea(20,40);
		cardDisp.setEditable( false );
		
		persWCardDisp = new JTextArea(20,40);
		persWCardDisp.setEditable( false );

		setSize( 900, 800 );
    	setVisible(true);
	}


	showPersons()
	{
		
	}

	showCards()
	{

	}

	showPersWithCards()
	{
		
	}

	public static void main( String[] args )
  	{
    	final Drittvindu  vindu = new Drittvindu();
    	vindu.addWindowListener(
      	new WindowAdapter() 
      	{
        	public void windowClosing( WindowEvent e )
        	{
				System.exit( 0 );
        	}
      	} );
 	}

  	private class Lytter implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{
	
     		 
     		if ( e.getSource() == showPersList )
      		{
       			showPersons();
      		}
      		else if ( e.getSource() == showCardList)
      		{
        		showCards();
      		}
      		else if ( e.getSource() == howPersonWithCard)
      		{
        		showPersWithCards();
      		}
      
     
    	}
	}