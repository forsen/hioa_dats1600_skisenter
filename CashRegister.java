import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CashRegister extends JFrame
{
	private JTextArea orderList, total, overview; 
	private ShoppingCart shoppingCart; 
	private JButton printReceipt, printCard, payByCash, payByCard;
	private JTextField cashInput;
	private BtnListener btnListener; 
	private JLabel cashInn;


	
	public CashRegister( ShoppingCart s )
	{
		super("Betaling");

		shoppingCart = s;
		btnListener = new BtnListener();

		orderList = new JTextArea(  );
		orderList.setText( shoppingCart.toString() );

		total = new JTextArea();
		total.setText("Total: \t\t" + shoppingCart.getSum() + ",-");
		overview = new JTextArea();
		overview.setText(" overview ");
		printReceipt = new JButton( "Print kvittering" );
		printCard = new JButton( "Print skikort" );
		payByCard = new JButton( "Betal med kort" );
		payByCash = new JButton( "Betal med kontanter" );
		payByCash.addActionListener( btnListener );
		cashInput = new JTextField( 7 );
		cashInput.setText("cashinput");
		cashInn = new JLabel( "Betalt: " );

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
		add( orderList , c);


		c.gridheight = 1;
		c.weightx = 0.5;
		c.gridx = 0; 
		c.gridy = 4; 
		c.gridwidth = 2;
		c.weighty = 0.5;
		add( total, c );
//		add( orderList );

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