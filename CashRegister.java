import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.util.Date;
import java.util.List;
import java.util.Iterator;


public class CashRegister extends JFrame
{
	private JTextArea orderList, total, overview; 
	private ShoppingCart shoppingCart; 
	private JList<CartItems> shoppingCartList;
	private List<Card> printableCards; 
	private JButton printReceipt, printCard, payByCash, payByCard;
	private JTextField cashInput;
	private BtnListener btnListener;
	private JScrollPane scroll;  
	private JLabel cashInn;
	private double remains;
	private double sum; 
	private double paid; 
	private double[] paymentMethod;
	public static final int CARD = 0;
	public static final int CASH = 1; 





	
	public CashRegister( ShoppingCart s, JList<CartItems> sl )
	{
		super("Betaling");


		shoppingCart = s;
		shoppingCartList = sl; 
		btnListener = new BtnListener();
		orderList = new JTextArea(  );
		orderList.setText( shoppingCart.toString() );
		sum = shoppingCart.getSum(); 
		remains = sum; 
		total = new JTextArea();
		total.setText("Total: \t\t" + sum + ",-");
		overview = new JTextArea();
		overview.setText("Betalt: \n\n");
		printReceipt = new JButton( "Print kvittering" );
		printReceipt.addActionListener( btnListener );
		printCard = new JButton( "Print skikort" );
		printCard.addActionListener( btnListener );
		payByCard = new JButton( "Betal med kort" );
		payByCard.addActionListener( btnListener );
		payByCash = new JButton( "Betal med kontanter" );
		payByCash.addActionListener( btnListener );
		cashInput = new JTextField( 7 );
		cashInn = new JLabel( "Betalt: " );

		scroll = new JScrollPane( orderList ); 


		paymentMethod = new double[2]; 

		printCard.setEnabled(false );
		printReceipt.setEnabled( false );

		setLayout( new GridBagLayout() );

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,10,10,10);
		c.gridheight = 4; 
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 0;
		c.gridwidth = 2; 
		c.weighty = 0.5;
		add( scroll , c);


		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 4; 
		c.gridwidth = 2;
		c.weighty = 0.5;
		add( total, c );

		c.gridheight = 1;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;

		add( cashInn, c );

		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		add( payByCard, c );

		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 2;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 2;
		add( overview, c );

		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridx = 2;
		c.gridy = 4; 
		c.gridwidth = 1;
		add( printCard, c );

		c.gridheight = 1;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add( cashInput, c );
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 1;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		add( payByCash, c );

		c.gridheight = 1;
		c.gridx = 3;
		c.gridy = 4;
		c.gridwidth = 1;
		add( printReceipt, c );

		pack();

		
		setSize( 600,400 );
		setVisible( true ); 
	}



	public void paid( int n )
	{
		try 
		{
			paid = Double.parseDouble( cashInput.getText() );
		
			remains -= paid;
			String s = "\nBetalt ";
			if( n == CASH )
			{
				s += "kontant:";
				paymentMethod[CASH] += paid; 
			}
			else if( n == CARD )
			{
				s += "med kort:";
				paymentMethod[CARD] += paid; 
			}

			s += " \t" + paid + "\n\nRest: \t\t" + remains;
			overview.append( s ); 

		}
		catch( NumberFormatException nfe )
		{
			JOptionPane.showMessageDialog(this, "Feltet kan kun bestå av tall, og \".\" er kommaseparator" );
		}

		if( remains <= 0.0 )
		{
			printReceipt.setEnabled( true );
			printCard.setEnabled( true );
			shoppingCartList.setModel( new DefaultListModel<CartItems>() );
			shoppingCart.checkOut();
			printableCards = shoppingCart.getNewCards();
			ShoppingCart.emptyCart();
		}
	}

	private void printCard()
	{
		Iterator<Card> it = printableCards.iterator(); 

		while( it.hasNext() )
		{
			System.out.println( "skjer det noe her??");
			PrintWindow w = new PrintWindow( it.next() );
		}

	}



	private class BtnListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			if( ae.getSource() == payByCash )
				paid(CASH);
			
			if( ae.getSource() == payByCard )
				paid(CARD);
			if( ae.getSource() == printReceipt )
			{
				PrintWindow w = new PrintWindow( orderList, paymentMethod, sum );
			}
			if( ae.getSource() == printCard )
				printCard(); 


		}


	}

}


