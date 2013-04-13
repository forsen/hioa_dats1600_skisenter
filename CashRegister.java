import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CashRegister extends JFrame
{
	private JTextArea orderList; 
	private ShoppingCart shoppingCart; 
	private JButton printReceipt, printCard, payByCash, payByCard;
	private JTextField cashInput;
	private BtnListener btnListener; 
	
	public CashRegister( ShoppingCart s )
	{
		super("Betaling");

		shoppingCart = s;
		btnListener = new BtnListener();

		orderList = new JTextArea( 30, 20 );
		printReceipt = new JButton( "Print kvittering" );
		printCard = new JButton( "Print skikort" );
		payByCard = new JButton( "Betal med kort" );
		payByCash = new JButton( "Betal med kontanter" );
		payByCash.addActionListener( btnListener );
		cashInput = new JTextField( 7 );


		setLayout( new FlowLayout() );

		add( orderList );
		add( cashInput );
		add( payByCash );
		add( payByCard );
		add( printCard );
		add( printReceipt );

		printReceipt.setVisible( false );
		printCard.setVisible( false );
		setSize( 400,400 );
		setVisible( true ); 
	}

	private class BtnListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			if( ae.getSource() == payByCash )
			{
				printCard.setVisible(true);
				printReceipt.setVisible( true );

			}
		}
	}

}