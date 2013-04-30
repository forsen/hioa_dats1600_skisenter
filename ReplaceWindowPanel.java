import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;



public class ReplaceWindowPanel extends JPanel 
{
	private JLabel custIdLb, cardNrLb;
	private JButton  replaceWindowRepBtn;
	public static JTextField replaceWindowCustIdtf;
	private JTextArea statusTxt;
	private JPanel fielPnl, butnFld, labelPnl; 
	private Listener listener;
	public static JList<Card> cardIDList;
	private ListListener listListener;
	private JScrollPane cardScrollList;
	private Card card;
	
	public ReplaceWindowPanel(JTextArea s)
	{
		setBackground(new Color(200, 230, 255));

		setLayout( new GridBagLayout( ) );
		//setBackground(new Color(200, 230, 255));
		Border etched = BorderFactory.createEtchedBorder();
		Border repBorder = BorderFactory.createTitledBorder(etched, "Erstatt kort");
		setBorder(repBorder);
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

		labelPnl= new JPanel(new  GridLayout( 2,2 )); 
		labelPnl.setBackground(new Color(200, 230, 255));
		fielPnl = new JPanel(new BorderLayout( 2,1 )); 
		fielPnl.setBackground(new Color(200, 230, 255));

		butnFld = new JPanel(new BorderLayout( 5,5)); 
		butnFld.setBackground(new Color(200, 230, 255));

		custIdLb = new JLabel( "Kundenummer" ) ;
		replaceWindowCustIdtf = new JTextField(5);
		replaceWindowCustIdtf.setEditable( false );
	//	fielPnl.add(replaceWindowCustIdtf);

		cardNrLb = new JLabel( "Kortnummer" ) ;
		
	//	fielPnl.add(cardScrollList);

		replaceWindowRepBtn = new JButton(" Erstatt ");
		replaceWindowRepBtn.setToolTipText("Erstatt kort");
		replaceWindowRepBtn.addActionListener( listener );
	//	butnFld.add(replaceWindowRepBtn);
		statusTxt = s;

	/*	add(labelPnl, BorderLayout.LINE_START);
		add(fielPnl, BorderLayout.LINE_END);
		add(butnFld, BorderLayout.PAGE_END);*/
		labelPnl.add(custIdLb);
		labelPnl.add(replaceWindowCustIdtf);
		labelPnl.add(cardNrLb);
		labelPnl.add(cardScrollList);


		butnFld.add(replaceWindowRepBtn);

		

		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,0,5,0);



		//Første kolonne////////////////////////////////////////////////////////////
		c.gridheight = 1; 
		c.gridx = 0; 
		c.gridy = 0;
		c.weighty = 0.2;
		add(labelPnl, c);
		
	/*	c.gridheight = 19 
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 1;
		c.gridwidth = 1; 
		c.weighty = 0.2;
		add(cardNrLb, c);*/

//ANDRE-KOLONNE ////////////////////
		c.gridheight = 1; 
		c.gridx = 1; 
		c.gridy = 0;
		c.weighty = 0.2;
		add(butnFld, c);

	/*	c.gridheight = 1; 
	//	c.weightx = 0.5;
		c.gridx = 1; 
		c.gridy = 1;
		c.gridwidth = 1; 
		c.weighty = 0;
		add(cardScrollList,c);	*/
	
		c.gridheight = 1;
//		c.weightx = 0.5;
		c.gridx = 1; 
		c.gridy = 1; 
		c.gridwidth = 1;
	//	c.weighty = 0.2;
		add(fielPnl, c);

    	

		
		
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