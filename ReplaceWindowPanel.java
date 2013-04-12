import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.Date;
//import java.text.SimpleDateFormat;
import java.io.*;
//import java.text.ParseException;


public class ReplaceWindowPanel extends JPanel 
{
	private JButton  replaceWindowRepBtn;
	private JTextField replaceWindowOldcard;
	public static JTextField replaceWindowCustIdtf;
//	private JTextArea repstatusTxt;
	private JPanel cntrPnl,btnPnl; 
	private Listener listener;
	//private Personlist custRegistry;
	//private Cardlist cardlist = new Cardlist();
	//private JList list; 
	public static JList cardIDList;
	private DefaultListModel<Person> listmodel;
	private DefaultListModel<Card> cardlistmodel;
	private ListListener listListener;
	private JScrollPane cardScrollList;
	

//Cardlist cl
	public ReplaceWindowPanel()
	{

		setLayout( new BorderLayout( 5, 5) );

	
		listener = new Listener();
		

		//list = new JList<>( new DefaultListModel<>());

		cardIDList = new JList<>( new DefaultListModel<>());
		cardIDList.setFixedCellHeight(15);
		cardIDList.setFixedCellWidth(100);
		cardIDList.setVisibleRowCount( 4 );
		cardIDList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		cardIDList.setCellRenderer( new CardListCellRenderer() );
		cardIDlist.addListSelectionListener( listListener );

		cardScrollList = new JScrollPane( cardIDList);
	//	add( cardScrollList );

		cntrPnl = new JPanel(new GridLayout( 4,2 )); 
		btnPnl = new JPanel(); 

		listListener = new ListListener(); 
		//list.addListSelectionListener( listListener );

		cntrPnl.add( new JLabel( "Kundenummer" ) );
		replaceWindowCustIdtf = new JTextField(5);
		replaceWindowCustIdtf.setEditable( false );
		cntrPnl.add(replaceWindowCustIdtf);


		cntrPnl.add( new JLabel( "Kortnummer" ) );
		replaceWindowOldcard = new JTextField(10);
		replaceWindowOldcard.setEditable( true );
		cntrPnl.add(replaceWindowOldcard);
	

	//	repstatusTxt = new JTextArea(15,30);
	//	repstatusTxt.setEditable( false );
		btnPnl.add(cardScrollList);
	//	repstatusTxt.setText("Her kommer info");
		

	/*	replaceWindowSearchBtn = new JButton(" Søk ");
		replaceWindowSearchBtn.addActionListener( listener );
		cntrPnl.add(replaceWindowSearchBtn);*/

		replaceWindowRepBtn = new JButton(" Erstatt ");
		replaceWindowRepBtn.addActionListener( listener );
		cntrPnl.add(replaceWindowRepBtn);
    	
		add(cntrPnl, BorderLayout.CENTER );
		add(btnPnl, BorderLayout.PAGE_END );


	}

	public void search()
	{
		
		try	
		{
			int custid = Integer.parseInt(replaceWindowCustIdtf.getText());

			String item = ""; 

			
	
/*			list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
			list.setCellRenderer( new SearchListCellRenderer());
			list.addListSelectionListener( listListener );

*/
			ReplaceWindowPanel.cardIDList.setModel( Salesclerk.customer.listCards() );
			
			
		}
		catch( NumberFormatException nfe )
		{

		}


	} 

	public String replace()
	{
		
		int custid = Integer.parseInt(replaceWindowCustIdtf.getText());
		int cardnr = Integer.parseInt(replaceWindowOldcard.getText());
		return null;



	}

	private class Listener implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 
     		
      		
      		if(e.getSource() == replaceWindowRepBtn)
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
				Card card = cardlistmodel.get(cardIDList.getSelectedIndex());
				Salesclerk.salesClerkSearchInfoTxt.setText(card.history());
				System.out.println( "Dette er inni tryblokken" );
			}
			catch( ArrayIndexOutOfBoundsException aioobe )
			{
				// when making a new search, index will be out of bound. We use this exception 
				// to clear the text field.	
				System.out.println( "Dette er en exception" );

			//	repstatusTxt.setText( "" );
			}

		}
	}

	




}