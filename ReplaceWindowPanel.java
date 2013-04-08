import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.*;
import java.text.ParseException;


public class ReplaceWindowPanel extends JPanel 
{
	private JButton replaceWindowSearchBtn, replaceWindowRepBtn;
	//private JPanel replaceWindowSearchInfoPnl, replaceWindowFirstNamePnl, replaceWindowLastNamePnl, replaceWindowPhonePnl, replaceWindowCardPnl, replaceWindowBtnPnl;
	private JTextField replaceWindowFirstName, replaceWindowLastName, replaceWindowOldcard, replaceWindowNewcard;
	//private JTextArea replaceWindowSearchInfoTxt;
	private JTextArea repstatusTxt;

	private Listener listener;
	private Personlist list;




	public ReplaceWindowPanel(Personlist l)
	{

		setLayout( new BorderLayout( 5, 5) );

		l = new Personlist();
		listener = new Listener();

	

   		
		replaceWindowFirstName = new JTextField(10);
		replaceWindowFirstName.setEditable( true );
		

		
		
		replaceWindowLastName = new JTextField(10);
		replaceWindowLastName.setEditable( true );
		


		
		replaceWindowOldcard = new JTextField(10);
		replaceWindowOldcard.setEditable( true );
		

		
		replaceWindowNewcard = new JTextField(10);
		replaceWindowNewcard.setEditable( true );
		

		repstatusTxt = new JTextArea(15,30);
		repstatusTxt.setEditable( false );
		repstatusTxt.setText("Her kommer status");
		


		replaceWindowSearchBtn = new JButton( "Søk ");
		replaceWindowSearchBtn.addActionListener( listener );
    	

		replaceWindowRepBtn = new JButton(" Erstatt ");
		replaceWindowRepBtn.addActionListener( listener );
    	



	}

	public Person search()
	{
		return null;
	}

	public String replace()
	{
		return null;
	}

	private class Listener implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 
     		if ( e.getSource() == replaceWindowSearchBtn )
      		{
       			search();
      		}
      		else if(e.getSource() == replaceWindowRepBtn)
      		{
      			replace();
      		}
      		
      		
    	}
	}
	

}