import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;

public class SalesWindowPanel extends JPanel
{
	private JLabel custIDLbl, cardTypeLbl;
	public static JTextField salesWindowCustIDtf; 
	private JList<String> cardTypeList;
	public static JList cardIDList;
	private DefaultListModel<Card> listmodel;
	private JButton salesAddCartBtn, salesCheckoutBtn, salesNewCardBtn; 
	private String[] cardTypeString; 
	private CardListener cardListener;
	private BtnListener btnListener;
	private JScrollPane cardScrolList;
	//private Person customer;

	//public SalesWindowPanel( Person p )
	public SalesWindowPanel()
	{

		setLayout( new GridLayout(3,3));
		
		custIDLbl = new JLabel( "Kundenr" );
		cardTypeLbl = new JLabel( "Korttype" );
		salesWindowCustIDtf = new JTextField( 3 );
		cardTypeString = new String[4];
		cardTypeString[Skicard.DAYCARD] = "Dagskort";
		cardTypeString[Skicard.HOURCARD] = "1-timeskort";
		cardTypeString[Skicard.SEASONCARD] = "Sesongkort";
		cardTypeString[Skicard.PUNCHCARD] = "Klippekort";

		btnListener = new BtnListener();

		salesAddCartBtn = new JButton("Legg i handlevogn");
		salesAddCartBtn.addActionListener( btnListener );

		salesCheckoutBtn = new JButton("Til betaling");
		salesCheckoutBtn.addActionListener( btnListener );

		salesNewCardBtn = new JButton("Nytt kort");
		salesNewCardBtn.addActionListener( btnListener );
		//customer = p; 

		cardListener = new CardListener();

		cardTypeList = new JList<>(cardTypeString);
		cardTypeList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		cardTypeList.setSelectedIndex(0);
		cardTypeList.addListSelectionListener( cardListener );

		cardIDList = new JList<>( new DefaultListModel<>());
		cardIDList.setFixedCellHeight(15);
		cardIDList.setFixedCellWidth(100);
		cardIDList.setVisibleRowCount( 4 );
		cardIDList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		cardIDList.setCellRenderer( new CardListCellRenderer() );

		cardScrolList = new JScrollPane( cardIDList );


		add( custIDLbl );
		add( salesWindowCustIDtf );
		add( cardScrolList );
		add( cardTypeLbl );
		add( cardTypeList );
		add( salesAddCartBtn );
		add( salesCheckoutBtn );
		add( salesNewCardBtn );


	} 

	private void addProduct()
	{
		int cardType = cardTypeList.getSelectedIndex();
		Skicard sc;

		Date now = new Date();
		switch( cardType )
		{
			case Skicard.DAYCARD: sc = new Daycard( 100, 0, "barn", now );
									break;
			case Skicard.HOURCARD: sc = new Hourcard( 100, 0, "barn", now );
									break; 
			case Skicard.SEASONCARD: sc = new Seasoncard( 100, 0, "barn", now );
									break;
		//	case Skicard.PUNCHCARD: sc = new Punchcard( 10, 10, 0, "barn" ); 
		//							break;
			default: 				sc = null;
		}

		if( Salesclerk.customer == null )
		{
			JOptionPane.showMessageDialog( null, "Legger til et kort uten bruker");
		}
		else
		{
			JOptionPane.showMessageDialog( null, "Legger til et kort til bruker:\n" + 
				Salesclerk.customer +
				"\n\nDette kortet er av typen:" +
				cardTypeString[cardTypeList.getSelectedIndex()] );
			Card c = (Card) cardIDList.getSelectedValue();
			JOptionPane.showMessageDialog(null, c.input( sc ));

		}
	}

	private void newCard()
	{
		Card nCard = new Card(); 
		try
		{
			Salesclerk.customer.addCard( nCard );
			SalesWindowPanel.cardIDList.setModel( Salesclerk.customer.listCards() );
		}
		catch( NullPointerException npe )
		{
			JOptionPane.showMessageDialog( null, "Du må velge en person først!" );
		}
	}
/*
	public void setCustId()
	{
		if( customer != null )
			custIDtf.setText( "" + customer.getCustId() );
	}
*/
	private class CardListener implements ListSelectionListener
	{
		public void valueChanged( ListSelectionEvent lse )
		{
			System.out.println( cardTypeList.getSelectedIndex() );
		}
	}


/*
	private void registerCard()
	{
		int custID = custIDtf.getText();

		try
		{
			Skicard c = new Skicard(cardNumber, price, discount, ag);

//			card
		}
		catch( IOException ioe )
		{

		}
	}
*/

	private class BtnListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			if( ae.getSource() == salesCheckoutBtn )
			{
				addProduct();
			}
			if( ae.getSource() == salesNewCardBtn )
			{
				newCard();
			}
		}

	}
}