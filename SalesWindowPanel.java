import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;

public class SalesWindowPanel extends JPanel
{
	private JLabel custIDLbl,cardnrLbl;
	public static JTextField salesWindowCustIDtf; 
	public static JTextField cardnrf;
	private JList<String> cardTypeList;
	private JList<CartItems> shoppingCartList;
	public static JList<Card> cardIDList;
	private DefaultListModel<Card> listmodel;
	private JButton salesAddCartBtn, salesCheckoutBtn, salesNewCardBtn, salesRemoveLineCart;
	private JButton salesReturnCardBtn;  
	private String[] cardTypeString; 
	private CardListener cardListener;
	private BtnListener btnListener;
	private JScrollPane cardScrolList, shoppingScrolList;
	private ShoppingCart shoppingCart; 
	private JLabel cartPrice;
	private Cardlist cardregistry;

	//private Person customer;

	//public SalesWindowPanel( Person p )
	public SalesWindowPanel(Cardlist cl)
	{
		setBackground(new Color(200, 230, 255));
		cardregistry = cl;

		Border etched = BorderFactory.createEtchedBorder();
		Border resultBdr = BorderFactory.createTitledBorder(etched, "Kortsalg");

		setBorder(resultBdr);

		custIDLbl = new JLabel( " Kundenr" );
		cardnrLbl = new JLabel(" Kortnr");
		

		salesWindowCustIDtf = new JTextField( 3 );
		salesWindowCustIDtf.setEditable(false);

		cardnrf = new JTextField( 3 );
		cardnrf.setEditable(true);

		cardTypeString = new String[4];
		cardTypeString[Skicard.DAYCARD] = "Dagskort";
		cardTypeString[Skicard.HOURCARD] = "1-timeskort";
		cardTypeString[Skicard.SEASONCARD] = "Sesongkort";
		cardTypeString[Skicard.PUNCHCARD] = "Klippekort";

		shoppingCart = new ShoppingCart(cardregistry);

		btnListener = new BtnListener();

		cartPrice = new JLabel(" Sum: 0kr");

		salesNewCardBtn = new JButton("Nytt kort");
		salesNewCardBtn.setMnemonic(KeyEvent.VK_N);
		salesNewCardBtn.addActionListener( btnListener );
		salesNewCardBtn.setToolTipText("Registrer nytt kort");

		salesReturnCardBtn = new JButton("Pant kort");
		salesReturnCardBtn.setMnemonic(KeyEvent.VK_P);
		salesReturnCardBtn.addActionListener( btnListener );
		salesReturnCardBtn.setToolTipText("<HTML>Pant et kort.<br>" +
			"Du kan enten velge et kortnr fra listen,<br>" +
			"eller skrive inn kortnr i kortnrfeltet.</HTML>");

		salesAddCartBtn = new JButton("Legg i handlevogn");
		salesAddCartBtn.setMnemonic(KeyEvent.VK_L);
		salesAddCartBtn.addActionListener( btnListener );
		salesAddCartBtn.setToolTipText("Legg til valgt kort i handlevognen");

		salesCheckoutBtn = new JButton("Til betaling");
		salesCheckoutBtn.setMnemonic(KeyEvent.VK_B);
		salesCheckoutBtn.addActionListener( btnListener );
		salesCheckoutBtn.setToolTipText("Ferdig? Gå til betaling");

		salesRemoveLineCart = new JButton("Slett valgt linje");
		salesRemoveLineCart.addActionListener( btnListener );
		salesRemoveLineCart.setToolTipText("Fjerner valgt linje fra handlekurven");


		//customer = p; 

		cardListener = new CardListener();

		cardTypeList = new JList<>(cardTypeString);
		cardTypeList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		cardTypeList.setSelectedIndex(0);
		cardTypeList.addListSelectionListener( cardListener );

		cardIDList = new JList<Card>( new DefaultListModel<Card>());
		cardIDList.setFixedCellHeight(15);
		cardIDList.setFixedCellWidth(100);
		cardIDList.setVisibleRowCount( 4 );
		cardIDList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		cardIDList.setCellRenderer( new CardListCellRenderer() );

		shoppingCartList = new JList<CartItems>( new DefaultListModel<CartItems>() );
		shoppingCartList.setFixedCellHeight(15);
		shoppingCartList.setFixedCellWidth( 100 );
		shoppingCartList.setVisibleRowCount( 4 );
		shoppingCartList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		shoppingCartList.setCellRenderer( new ShoppingCartCellListRenderer() );
		shoppingScrolList = new JScrollPane( shoppingCartList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );


		cardScrolList = new JScrollPane( cardIDList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );

		setLayout( new GridBagLayout() );

		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);



		//Første kolonne////////////////////////////////////////////////////////////
		c.gridheight = 1; 
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 0;
		c.gridwidth = 1; 
		c.weighty = 0.2;
		add(custIDLbl, c);
		
		c.gridheight = 1; 
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 1;
		c.gridwidth = 1; 
		c.weighty = 0.2;
		add(cardnrLbl, c);

		c.gridheight = 2;
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 2; 
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(cardTypeList, c);

		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 4; 
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(cardScrolList, c);

		c.gridheight = 2;
		c.weightx = 1;
		c.gridx = 0; 
		c.gridy = 5; 
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(shoppingScrolList, c);

		//Andre kolonne////////////////////////////////////////////////////////////
		c.gridheight = 1; 
		c.weightx = 0.5;
		c.gridx = 1; 
		c.gridy = 0;
		c.gridwidth = 1; 
		c.weighty = 0.2;
		add(salesWindowCustIDtf, c);

		c.gridheight = 1; 
		c.weightx = 0.5;
		c.gridx = 1; 
		c.gridy = 1;
		c.gridwidth = 1; 
		c.weighty = 0.2;
		add(cardnrf,c);	

		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 1; 
		c.gridy = 2; 
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(salesNewCardBtn, c);

		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(salesReturnCardBtn, c);

		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 1; 
		c.gridy = 4; 
		c.gridwidth = 2;
		c.weighty = 0.2;
		add(salesAddCartBtn, c);

		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(salesRemoveLineCart, c);

		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 1; 
		c.gridy = 6; 
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(salesCheckoutBtn, c);

		//Tredje kolonne////////////////////////////////////////////////////////////





		/*add( custIDLbl );
		add( salesWindowCustIDtf );
		add( cardScrolList );
		add( cardTypeLbl );
		add( cardTypeList );
		add( salesAddCartBtn );
		add( salesCheckoutBtn );
		add( salesNewCardBtn );
		add( shoppingScrolList );
		add( cartPrice );*/

	} 

	private void addToCart()
	{
		int cardType = cardTypeList.getSelectedIndex();
		Skicard sc;
	
		Date now = new Date();

		Date bDate;

		try
		{
			bDate = Salesclerk.customer.getBirth();
		}
		catch( NullPointerException npe )
		{
			bDate = null; 
		}
		
		switch( cardType )
		{
			case Skicard.DAYCARD: sc = new Daycard( bDate, now );
									break;
			case Skicard.HOURCARD: sc = new Hourcard( bDate, now );
									break; 
			case Skicard.SEASONCARD: sc = new Seasoncard( bDate, now );
									break;
			case Skicard.PUNCHCARD: sc = new Punchcard( bDate, now ); 
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
			else
			{
				int cNr = Integer.parseInt(cardnrf.getText());
				c = cardregistry.findCard(cNr);
				shoppingCartList.setModel( shoppingCart.addToCart( c, sc ) );
				cartPrice.setText(" Sum: " + shoppingCart.getSum() + "kr");
			}			
		}
		catch( NumberFormatException nfe )
		{
			Salesclerk.statusTxt.setText("Du må gjøre noe");
		}
		catch( NullPointerException npe )
		{
			if( Salesclerk.customer.isEmpty() )
				JOptionPane.showMessageDialog( null, "Du må opprette et nytt" );

			else
				JOptionPane.showMessageDialog( null, "Du må velge hvilket kort fra kortlista som skal få det nye produktet" );
		}
	}

	private void deleteFromCart()
	{
		int removeLine = shoppingCartList.getSelectedIndex(); 
		
		shoppingCartList.setModel( shoppingCart.deleteFromCart( removeLine ) );

	}

	private void payment()
	{
		// tar i mot betaling og slikt.

		CashRegister cr = new CashRegister( shoppingCart, shoppingCartList );

		//shoppingCartList.setModel(shoppingCart.emptyCart());
		

	}
/*
	private void checkOut()
	{
		shoppingCart.checkOut();
		shoppingCartList.setModel(shoppingCart.emptyCart());
	}
*/
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
	
		SalesWindowPanel.cardIDList.setModel( shoppingCart.newCard() );
		cartPrice.setText(" Sum: " + shoppingCart.getSum() + "kr");
		

	/*	Card nCard = new Card(); 
		try
		{
			Salesclerk.customer.addCard( nCard );
			SalesWindowPanel.cardIDList.setModel( Salesclerk.customer.listCards() );
		}
		catch( NullPointerException npe )
		{
			JOptionPane.showMessageDialog( null, "Du må velge en person først!" );
		}*/
	}

	private void returnCard()
	{
	
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
			if( ae.getSource() == salesRemoveLineCart )
				deleteFromCart();
		}

	}
}