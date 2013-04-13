import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.util.Date;


public class CashRegister extends JFrame
{
	private JTextArea orderList, total, overview, receipt; 
	private ShoppingCart shoppingCart; 
	private JButton printReceipt, printCard, payByCash, payByCard;
	private JTextField cashInput;
	private BtnListener btnListener; 
	private JLabel cashInn;
	private double remains;
	private double sum; 
	private double paid; 





	
	public CashRegister( ShoppingCart s )
	{
		super("Betaling");

		shoppingCart = s;
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
		payByCard = new JButton( "Betal med kort" );
		payByCard.addActionListener( btnListener );
		payByCash = new JButton( "Betal med kontanter" );
		payByCash.addActionListener( btnListener );
		cashInput = new JTextField( 7 );
		cashInn = new JLabel( "Betalt: " );

		printCard.setEnabled(false );
		printReceipt.setEnabled( false );



//-----------------------------------------------------------
		receipt = new JTextArea( 30, 30);
		receipt.setText( "\t\tTidenes skisenter\t\t\n" );
		receipt.append( "\t\tORG.NR. 123 456 789 MVA\n" );
		receipt.append( "\t\tLangtvekkistan 1 \n" );
		receipt.append( "\t\tTLF: 22 33 22 33 \n" );
		receipt.append( "\n\n");
		receipt.append( new Date().toString() + "\n"); 
		receipt.append( "----------------------------------------------\n");
		receipt.append( orderList.getText() );
		receipt.append( "----------------------------------------------\n");
		receipt.append( "SUBTOTAL\t\t\t" + sum + "\n");
		receipt.append( "----------------------------------------------\n");
		
//-----------------------------------------------------------

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

		
		setSize( 600,400 );
		setVisible( true ); 
	}



	public void paid( String n )
	{
		try 
		{
			paid = Double.parseDouble( cashInput.getText() );
		
			remains -= paid;

			overview.append( "\nBetalt " + n + ": \t" + paid +
				"\n\nRest: \t\t" + remains ); 

		}
		catch( NumberFormatException nfe )
		{
			JOptionPane.showMessageDialog(this, "Feltet kan kun bestå av tall, og \".\" er kommaseparator" );
		}

		if( remains <= 0.0 )
		{
			printReceipt.setEnabled( true );
			printCard.setEnabled( true );
			//receipt.append( overview.toString() );
		}
	}



	private class BtnListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			if( ae.getSource() == payByCash )
				paid("kontant");
			
			if( ae.getSource() == payByCard )
				paid("med");
			if( ae.getSource() == printReceipt )
			{
				PrintWindow w = new PrintWindow( receipt );
			}


		}


	}

}


