import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;
import java.text.NumberFormat;
import java.util.Locale;

/**
  * This class sets up the sales panel, which is placed in the Salesclerk JFrame.
  * This is where you do the selling and return of cards. 
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */
public class SalesWindowPanel extends JPanel
{
	private JLabel custIDLbl,cardnrLbl;
	public static JTextField salesWindowCustIDtf; 
	public static JTextField cardnrf, discountTf;
	private JList<String> cardTypeList;
	public static JList<CartItems> shoppingCartList;
	public static JList<Card> cardIDList;
	private DefaultListModel<Card> listmodel;
	private JButton salesAddCartBtn, salesCheckoutBtn, salesNewCardBtn, salesRemoveLineCart;
	private JButton salesReturnCardBtn;  
	private String[] cardTypeString; 
	private CardListener cardListener;
	private BtnListener btnListener;
	private JScrollPane cardScrolList, cardTypeScrolList;
	private static JScrollPane shoppingScrolList;
	private static ShoppingCart shoppingCart; 
	private JLabel discountLbl;
	private static double cartPrice;
	private Cardlist cardregistry;
	private static NumberFormat paymentFormat;
	private static Border etched;
	

/**
  * This constructor will set up all the elements, and place them on the panel
  * @param cl  	The card list to operate on
  * @see Salesclerk
  * @see Cardlist
  */

	public SalesWindowPanel(Cardlist cl)
	{
		setBackground(new Color(200, 230, 255));
		cardregistry = cl;
		

		paymentFormat = NumberFormat.getCurrencyInstance( new Locale( "no", "NO" ) );
		cartPrice = 0.0;

		etched = BorderFactory.createEtchedBorder();
		Border resultBdr = BorderFactory.createTitledBorder(etched, "Kortsalg");

		setBorder(resultBdr);

		custIDLbl = new JLabel( " Kundenr" );
		cardnrLbl = new JLabel(" Kortnr");
		

		salesWindowCustIDtf = new JTextField( 3 );
		salesWindowCustIDtf.setEditable(false);

		cardnrf = new JTextField( 3 );
		cardnrf.setEditable(true);

		discountTf= new JTextField( 1 );
		discountTf.setEditable(true);
		discountTf.setToolTipText( "Reduserer prisen i prosent" );

		discountLbl = new JLabel("Rabatt: ");
		discountLbl.setToolTipText( "Reduserer prisen i prosent" );

		cardTypeString = new String[4];
		cardTypeString[Skicard.DAYCARD] = "Dagskort";
		cardTypeString[Skicard.HOURCARD] = "1-timeskort";
		cardTypeString[Skicard.SEASONCARD] = "Sesongkort";
		cardTypeString[Skicard.PUNCHCARD] = "Klippekort";

		shoppingCart = new ShoppingCart(cardregistry);

		btnListener = new BtnListener();

		

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

		//Will not use "try-catch" here, because the buttons will appear fine, even 
		//though the pictures cannot be found.
		ImageIcon cart = new ImageIcon("img/cart.png");
		ImageIcon payment = new ImageIcon("img/payment.png");


		salesAddCartBtn = new JButton("Legg i handlevogn", cart);
		salesAddCartBtn.setMnemonic(KeyEvent.VK_L);
		salesAddCartBtn.addActionListener( btnListener );
		salesAddCartBtn.setToolTipText("Legg til valgt kort i handlevognen");

		salesCheckoutBtn = new JButton("Til betaling", payment);
		salesCheckoutBtn.setMnemonic(KeyEvent.VK_B);
		salesCheckoutBtn.addActionListener( btnListener );
		salesCheckoutBtn.setToolTipText("Ferdig? Gå til betaling");

		salesRemoveLineCart = new JButton("Slett valgt linje");
		salesRemoveLineCart.addActionListener( btnListener );
		salesRemoveLineCart.setToolTipText("Fjerner valgt linje fra handlekurven");



		cardListener = new CardListener();

		cardTypeList = new JList<>(cardTypeString);
		cardTypeList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		cardTypeList.setSelectedIndex(0);
		cardTypeList.setVisibleRowCount( 4 );
		cardTypeList.addListSelectionListener( cardListener );


		cardTypeScrolList = new JScrollPane( cardTypeList, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		cardTypeScrolList.setBorder(BorderFactory.createTitledBorder(etched, "Korttype"));
		cardTypeScrolList.setBackground(new Color(200,230,255));

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
		shoppingScrolList.setBorder(BorderFactory.createTitledBorder(etched, "Handlekurv: " + paymentFormat.format( cartPrice ) ) );
		shoppingScrolList.setBackground(new Color(200, 230, 255));

		cardScrolList = new JScrollPane( cardIDList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		cardScrolList.setBorder(BorderFactory.createTitledBorder(etched, "Valgte kort"));
		cardScrolList.setBackground(new Color(200, 230, 255));


		setLayout( new GridBagLayout() );

		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);



		//First column////////////////////////////////////////////////////////////
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
		add(cardTypeScrolList, c);

		c.gridheight = 2;
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 4; 
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(cardScrolList, c);

		c.gridheight = 2;
		c.weightx = 1;
		c.gridx = 0; 
		c.gridy = 6; 
		c.gridwidth = 1;
		c.weighty = 0.2;
		add(shoppingScrolList, c);

		//Second column////////////////////////////////////////////////////////////
		c.gridheight = 1; 
		c.weightx = 0.5;
		c.gridx = 1; 
		c.gridy = 0;
		c.gridwidth = 2; 
		c.weighty = 0.2;
		add(salesWindowCustIDtf, c);

		c.gridheight = 1; 
		c.weightx = 0.5;
		c.gridx = 1; 
		c.gridy = 1;
		c.gridwidth = 2; 
		c.weighty = 0.2;
		add(cardnrf,c);	

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(12,0,0,0);

		c.gridheight = 1;
		c.gridx = 1; 
		c.gridy = 2; 
		c.gridwidth = 2;
		c.weighty=0;
		add(salesNewCardBtn, c);

		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		add(salesReturnCardBtn, c);

		c.gridheight = 1;
		c.gridx = 1; 
		c.gridy = 4; 
		c.gridwidth = 1;
		add(discountLbl, c);

		c.gridheight = 1;
		c.gridx = 1; 
		c.gridy = 5; 
		c.gridwidth = 2;

		add(salesAddCartBtn, c);

		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 2;
		add(salesRemoveLineCart, c);

		c.gridheight = 1;
		c.gridx = 1; 
		c.gridy = 7; 
		c.gridwidth = 2;
		add(salesCheckoutBtn, c);

		//Third column////////////////////////////////////////////////////////////

		c.gridheight = 1;
		c.gridx = 2; 
		c.gridy = 4; 
		c.gridwidth = 1;
		add(discountTf, c);


	} 



/**
  * This method creates a Skicard object of selected type, with desired amount of discount. 
  * This card will be added to the shopping cart, if incoming data is ok. 
  * When a card is bought, a Date object will be created to keep track of buying date.
  * The method also retrieves a method that updates a "total price in shopping cart"-label.
  */


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
			default: 				return; // we want to return here. If not you'll get unexpected behaviour if the cardtypelist gets unselected and you try to add to cart
		}

		try
		{
			if( !discountTf.getText().isEmpty())
			{
				double discount = Double.parseDouble( discountTf.getText());
				discount = (100-discount)/100;
				sc.setDiscount(discount);
				sc.setPrice( discount * sc.getPrice() );
				discountTf.setText(""); 
			}
		}
		catch( NumberFormatException nfe )
		{
			Salesclerk.statusTxt.setText( "Rabatten skal bestå av tall, i prosent" );
		}
		catch( NullPointerException npe )
		{
			// We do nothing here. This situation is cought by the nullpointerexception below.
		}
		
		try
		{	
			
			Card c = (Card) cardIDList.getSelectedValue();
			String stringcNr = cardnrf.getText(); 


			if (!stringcNr.isEmpty() )
			{
				String pattern = "\\d{6}";
				if(stringcNr.matches(pattern))
				{
					int cNr = Integer.parseInt(stringcNr);
					c = cardregistry.findCard(cNr);
					if( c == null )
						Salesclerk.statusTxt.setText("Fant ikke kortet.");
					else if ( c.getReturned() )
					{
						Salesclerk.statusTxt.setText("Dette kortet er pantet, man kan dermed ikke legge skikort på dette.");
						return;
					}
					else
					{
						shoppingCartList.setModel( shoppingCart.addToCart( c, sc ) );
						updateCartPrice();
					}
				}
				else
					throw new NumberFormatException(); 
			}
			else if( c != null )
			{
				shoppingCartList.setModel( shoppingCart.addToCart( c, sc ) );
				updateCartPrice();
			}
			else
				throw new NullPointerException();		
		}
		catch( NumberFormatException nfe )
		{
			Salesclerk.statusTxt.setText("Kortnr må bestå av 6 siffer.");
		}
		catch( NullPointerException npe )
		{
			Salesclerk.statusTxt.setText("Ingen kort valgt. Velg et fra listen, opprett et nytt, eller skriv inn kortnr i kortnrfeltet.");
		}
	}

/**
  * This method deletes a card from the shopping cart list. 
  */
	private void deleteFromCart()
	{
		try
		{	int removeLine = shoppingCartList.getSelectedIndex(); 

			CartItems c = shoppingCartList.getModel().getElementAt(removeLine);


		
		
		

			shoppingCartList.setModel( shoppingCart.deleteFromCart( removeLine ) );

			// need this, or we will get double entries in the cardidlist
			if( c.getSkiCard() == null )
			{
				DefaultListModel<Card> lmc = (DefaultListModel<Card>) cardIDList.getModel();
				lmc.addElement( c.getCard() );
				cardIDList.setModel( lmc );
			}
		
			updateCartPrice();
		}catch (ArrayIndexOutOfBoundsException aiobe)
		{
			Salesclerk.statusTxt.setText("Du må velge en linje du ønsker og slette");
		}

	}

/**
  * This method recieves payment, and creates a new CashRegister window.  
  */
	private void payment()
	{

		CashRegister cr = new CashRegister((Window) this.getRootPane().getParent(), shoppingCart, shoppingCartList );
	}


	private void newCard()
	{
	
		SalesWindowPanel.cardIDList.setModel( shoppingCart.newCard() );
		updateCartPrice();
		

/**
  * This updates the a label, so the seller can see the total sum in the shopping cart, whenever method is being
  * excecuted. 
  */

	}

	public static void updateCartPrice()
	{
		cartPrice = shoppingCart.getSum();
		shoppingScrolList.setBorder(BorderFactory.createTitledBorder(etched, "Handlekurv: " + paymentFormat.format( cartPrice ) ) );
	}

/**
  * This method marks a card as returned. 
  */

	private void returnCard()
	{
		try
		{	
			
			Card c = (Card) cardIDList.getSelectedValue();
			String stringcNr = cardnrf.getText(); 


			if (!stringcNr.isEmpty() )
			{
				String pattern = "\\d{6}";
				if(stringcNr.matches(pattern))
				{
					int cNr = Integer.parseInt(stringcNr);
					c = cardregistry.findCard(cNr);
					if( c == null )
						Salesclerk.statusTxt.setText("Fant ikke kortet.");
					else
					{
	
						returnCard( c );
					}
				}
				else
					throw new NumberFormatException(); 
			}
			else if( c != null )
			{

				returnCard( c ); 
			}
			else
				throw new NullPointerException();		
		}
		catch( NumberFormatException nfe )
		{
			Salesclerk.statusTxt.setText("Kortnr må bestå av 6 siffer.");
		}
		catch( NullPointerException npe )
		{
			Salesclerk.statusTxt.setText("Ingen kort valgt. Velg et fra listen, eller skriv inn kortnr i kortnrfeltet.");
		}
	}


/**
  * This method returns a card, and places a refund in the shopping cart. 
  * @param c 	The card which should be returned, if not already returned
  */

	private void returnCard( Card c )
	{
		try
		{
			if( c.getReturned() )
			{
				Salesclerk.statusTxt.setText("Dette kortet er allerede pantet");
				return;
			}


			shoppingCartList.setModel( shoppingCart.addToCart( c, null ) );
			DefaultListModel<Card> lmc = (DefaultListModel<Card>) cardIDList.getModel();
			lmc.removeElementAt( cardIDList.getSelectedIndex() );
			cardIDList.setModel( lmc );
			updateCartPrice();
		}
		catch(ArrayIndexOutOfBoundsException aiobe)
		{
			/*
			aiobe is catched by the code above if you return a card from the textfield, and not from the list.
			case: when a unregistered card is returned. This is expected, and we will not do anything about it.
			*/	
		}
	}


/**
  * CardListener makes sure a buyer must be registered, to be able to buy a Seasoncard. 
  * It also makes sure that a picture has been uploaded, for the customers Seasoncard. 
  * 
  */

	private class CardListener implements ListSelectionListener
	{
		public void valueChanged( ListSelectionEvent lse )
		{
			if( cardTypeList.getSelectedIndex() == Skicard.SEASONCARD )
			{
				if( Salesclerk.customer == null )
				{
					Salesclerk.statusTxt.setText("Sesongkort kan kun selges til registrerte kunder");
					cardTypeList.clearSelection();
				}
				else if( Salesclerk.customer.getImage() == null )
				{
					Salesclerk.statusTxt.setText("For å kjøpe Sesongkort må kunden ha et bilde");
					cardTypeList.clearSelection();
				}
			}
		}
	} //end of class CardListener

/**
  * BtnListener, to make action happend, when buttons are clicked.
  * 
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
			if( ae.getSource() == salesReturnCardBtn )
				returnCard();
		}

	} //end of class BtnListener
} //end of class SalesWindowPanel
