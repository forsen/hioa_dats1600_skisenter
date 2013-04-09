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
	private JPanel replaceWindowSearchInfoPnl, replaceWindowFirstNamePnl, replaceWindowLastNamePnl, replaceWindowCardPnl, replaceWindowBtnPnl;
	private JTextField replaceWindowFirstName, replaceWindowLastName, replaceWindowOldcard;
	private JTextArea replaceWindowSearchInfoTxt;
	private JTextArea repstatusTxt;

	private JPanel cntrPnl,btnPnl; 

	private Listener listener;
	private Personlist custRegistry;

	private JList list; 
	private DefaultListModel<Person> listmodel;
	private ListListener listListener;




	public ReplaceWindowPanel(Personlist cr)
	{

		setLayout( new BorderLayout( 5, 5) );

		custRegistry = cr;
		listener = new Listener();

		list = new JList<>( new DefaultListModel<>()); 

		list.setVisibleRowCount(5);
		list.setFixedCellHeight(15);
		list.setFixedCellWidth(100);

		list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		list.setCellRenderer( new SearchListCellRenderer());
		list.setVisible( true );

		

		listListener = new ListListener(); 
		list.addListSelectionListener( listListener );

	

   		cntrPnl.add( new JLabel( "Fornavn" ) );
		replaceWindowFirstName = new JTextField(10);
		replaceWindowFirstName.setEditable( true );
		cntrPnl.add(replaceWindowFirstName);
		

		
		cntrPnl.add( new JLabel( "Etternavn" ) );
		replaceWindowLastName = new JTextField(10);
		replaceWindowLastName.setEditable( true );
		cntrPnl.add(replaceWindowLastName);
		


		cntrPnl.add( new JLabel( "Kortnummer" ) );
		replaceWindowOldcard = new JTextField(10);
		replaceWindowOldcard.setEditable( true );
		cntrPnl.add(replaceWindowOldcard);
	

		repstatusTxt = new JTextArea(15,30);
		repstatusTxt.setEditable( false );
		btnPnl.add(repstatusTxt);
		repstatusTxt.setText("Her kommer status");
		

		replaceWindowSearchBtn = new JButton( "Søk ");
		replaceWindowSearchBtn.addActionListener( listener );
    	

		replaceWindowRepBtn = new JButton(" Erstatt ");
		replaceWindowRepBtn.addActionListener( listener );
    	
		add(cntrPnl, BorderLayout.CENTER );
		add(btnPnl, BorderLayout.PAGE_END );


	}

	public Person search()
	{
		String fn = replaceWindowFirstName.getText();
		String ln =  replaceWindowLastName.getText();
		int cn =  Integer.parseInt(replaceWindowOldcard.getText());

		if (!fn.isEmpty() || !ln.isEmpty())
			 custRegistry.findPerson(fn, ln);
			return null;


	}

	public String replace()
	{
		int old = Integer.parseInt(replaceWindowOldcard.getText());
		//int newc = Integer.parseInt(replaceWindowNewcard.getText());
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

	private class ListListener implements ListSelectionListener
	{
		public void valueChanged( ListSelectionEvent lse )
		{
			try
			{
				Salesclerk.customer = listmodel.get(list.getSelectedIndex());
				SalesWindowPanel.salesWindowCustIDtf.setText( "" + Salesclerk.customer.getCustId() );
				repstatusTxt.setText( Salesclerk.customer.getCustId() + "\n" + Salesclerk.customer.toString() );
			}
			catch( ArrayIndexOutOfBoundsException aioobe )
			{
				// when making a new search, index will be out of bound. We use this exception 
				// to clear the text field.

				repstatusTxt.setText( "" );
			}

		}
	}

}