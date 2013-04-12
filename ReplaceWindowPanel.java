import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;



public class ReplaceWindowPanel extends JPanel 
{
	private JButton  replaceWindowRepBtn;
	public static JTextField replaceWindowCustIdtf;
	private JTextArea statusTxt;
	private JPanel cntrPnl; 
	private Listener listener;
	public static JList<Card> cardIDList;
	private ListListener listListener;
	private JScrollPane cardScrollList;
	private Card card;
	
	public ReplaceWindowPanel(JTextArea s)
	{

		setLayout( new BorderLayout( 5, 5) );
		card = null;
	
		listener = new Listener();
		listListener = new ListListener(); 

		cardIDList = new JList<Card>( new DefaultListModel<Card>());
		cardIDList.setFixedCellHeight(15);
		cardIDList.setFixedCellWidth(100);
		cardIDList.setVisibleRowCount( 4 );
		cardIDList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		cardIDList.setCellRenderer( new CardListCellRenderer() );
		cardIDList.addListSelectionListener( listListener );
		cardScrollList = new JScrollPane( cardIDList);

		cntrPnl = new JPanel(new GridLayout( 4,2 )); 
		

		cntrPnl.add( new JLabel( "Kundenummer" ) );
		replaceWindowCustIdtf = new JTextField(5);
		replaceWindowCustIdtf.setEditable( false );
		cntrPnl.add(replaceWindowCustIdtf);

		cntrPnl.add( new JLabel( "Kortnummer" ) );
		cntrPnl.add(cardScrollList);

		replaceWindowRepBtn = new JButton(" Erstatt ");
		replaceWindowRepBtn.addActionListener( listener );
		cntrPnl.add(replaceWindowRepBtn);
		statusTxt = s;

    	
		add(cntrPnl, BorderLayout.CENTER );
		
		
	}

	public void search()
	{
		
		try	
		{
			int custid = Integer.parseInt(replaceWindowCustIdtf.getText());

			String item = ""; 

			ReplaceWindowPanel.cardIDList.setModel( Salesclerk.customer.listCards() );	
			
		}
		catch( NumberFormatException nfe )
		{

		}


	} 

	public void replace()
	{
		
		Salesclerk.customer.removeCard(card);
		Card nCard = new Card(); 
		nCard.setSkicardlist( card.getSkicardlist());
		nCard.setCurrent(card.getCurrent());
		Salesclerk.customer.addCard(nCard);

		statusTxt.setText( "Kortet med kortnr: " + card.getCardID() + " ble er erstattet med kortnr: " + nCard.getCardID());

	}

	private class Listener implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 	
      		
      		if(e.getSource() == replaceWindowRepBtn)
      		{
      			replace();
      			System.out.println("Du har trykka på erstatt");
      		}
      		
    	}
	}	

	private class ListListener implements ListSelectionListener
	{
		public void valueChanged( ListSelectionEvent lse )
		{
			try
			{
				card = (Card) cardIDList.getSelectedValue();
				Salesclerk.salesClerkSearchInfoTxt.setText(card.history());
			}
			catch( ArrayIndexOutOfBoundsException aioobe )
			{
				// when making a new search, index will be out of bound. We use this exception 
				// to clear the text field.	
				System.out.println( "Dette er en exception" );

			}
			catch( NullPointerException npe)
			{

			}

		}
	}
}