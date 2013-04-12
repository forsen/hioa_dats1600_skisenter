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
	private JList<Skicard> shoppingCartList;
	public static JList cardIDList;
	private DefaultListModel<Card> listmodel;
	private JButton salesAddCartBtn, salesCheckoutBtn, salesNewCardBtn; 
	private String[] cardTypeString; 
	private CardListener cardListener;
	private BtnListener btnListener;
	private JScrollPane cardScrolList, shoppingScrolList;
	private ShoppingCart shoppingCart; 
	private JLabel cartPrice;

	//private Person customer;

	//public SalesWindowPanel( Person p )
	public SalesWindowPanel()
	{

		setLayout( new GridLayout(4,3));
		
		custIDLbl = new JLabel( " Kundenr" );
		cardTypeLbl = new JLabel( " Korttype" );
		salesWindowCustIDtf = new JTextField( 3 );
		cardTypeString = new String[4];
		cardTypeString[Skicard.DAYCARD] = "Dagskort";
		cardTypeString[Skicard.HOURCARD] = "1-timeskort";
		cardTypeString[Skicard.SEASONCARD] = "Sesongkort";
		cardTypeString[Skicard.PUNCHCARD] = "Klippekort";

		shoppingCart = new ShoppingCart();

		btnListener = new BtnListener();

		cartPrice = new JLabel(" Sum: 0kr");
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

		shoppingCartList = new JList<Skicard>( new DefaultListModel<Skicard>() );
		shoppingCartList.setFixedCellHeight(15);
		shoppingCartList.setFixedCellWidth( 100 );
		shoppingCartList.setVisibleRowCount( 4 );
		shoppingCartList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		shoppingCartList.setCellRenderer( new ShoppingCartCellListRenderer() );
		shoppingScrolList = new JScrollPane( shoppingCartList );


		cardScrolList = new JScrollPane( cardIDList );


		add( custIDLbl );
		add( salesWindowCustIDtf );
		add( cardScrolList );
		add( cardTypeLbl );
		add( cardTypeList );
		add( salesAddCartBtn );
		add( salesCheckoutBtn );
		add( salesNewCardBtn );
		add( shoppingScrolList );
		add( cartPrice );

	} 

	private void addToCart()
	{
		int cardType = cardTypeList.getSelectedIndex();
		Skicard sc;

		Date now = new Date();
		switch( cardType )
		{
			case Skicard.DAYCARD: sc = new Daycard( Info.DAYCARDPRICE, 0, "barn", now );
									break;
			case Skicard.HOURCARD: sc = new Hourcard( Info.HOURCARDPRICE, 0, "barn", now );
									break; 
			case Skicard.SEASONCARD: sc = new Seasoncard( Info.SEASONCARDPRICE, 0, "barn", now );
									break;
			case Skicard.PUNCHCARD: sc = new Punchcard( Info.PUNCHCARDPRICE, 0, "barn", now ); 
									break;
			default: 				sc = null;
		}
		try
		{
			Card c = (Card) cardIDList.getSelectedValue();
			if( c != null )
			{
				shoppingCartList.setModel( shoppingCart.addToCart( c, sc ) );
				cartPrice.setText(" Sum: " + shoppingCart.getSum() + "kr");
			}
		}
		catch( NullPointerException npe )
		{
			if( Salesclerk.customer.isEmpty() )
				JOptionPane.showMessageDialog(null, "Du må opprette et kort først, trykk på nytt kort");
			else
				JOptionPane.showMessageDialog( null, "Du må velge hvilket kort fra kortlista som skal få det nye produktet" );
		}



	}

	private void payment()
	{
		// tar i mot betaling og slikt.
		JOptionPane.showMessageDialog( null, shoppingCart.toString() );
		checkOut();

	}
	private void checkOut()
	{
		shoppingCart.checkOut();
	}

/*
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

			try
			{
				Card c = (Card) cardIDList.getSelectedValue();
				JOptionPane.showMessageDialog(null, c.input( sc ));
			}
			catch( NullPointerException npe )
			{
				if( Salesclerk.customer.isEmpty() )
					JOptionPane.showMessageDialog(null, "Du må opprette et kort først, trykk på nytt kort");
				else
					JOptionPane.showMessageDialog( null, "Du må velge hvilket kort fra kortlista som skal få det nye produktet" );

			}

		}
	}
*/
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
				payment();
			}
			if( ae.getSource() == salesNewCardBtn )
			{
				newCard();
			}
			if( ae.getSource() == salesAddCartBtn )
			{
				addToCart();
			}
		}

	}
}