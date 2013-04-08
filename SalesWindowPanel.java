import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;

public class SalesWindowPanel extends JPanel
{
	private JLabel custIDLbl, cardTypeLbl;
	public static JTextField salesWindowCustIDtf; 
	private JList<String> cardTypeList;
	private JButton salesAddCartBtn, salesCheckoutBtn; 
	private String[] cardTypeString; 
	private CardListener cardListener;
	private BtnListener btnListener;
	//private Person customer;

	//public SalesWindowPanel( Person p )
	public SalesWindowPanel()
	{
		custIDLbl = new JLabel( "Kundenr" );
		cardTypeLbl = new JLabel( "Korttype" );
		salesWindowCustIDtf = new JTextField( 3 );
		cardTypeString = new String[4];
		cardTypeString[0] = "Klippekort";
		cardTypeString[1] = "1-timeskort";
		cardTypeString[2] = "Dagskort";
		cardTypeString[3] = "Sesongkort";

		btnListener = new BtnListener();

		salesAddCartBtn = new JButton("Legg i handlevogn");
		salesAddCartBtn.addActionListener( btnListener );

		salesCheckoutBtn = new JButton("Til betaling");
		salesCheckoutBtn.addActionListener( btnListener );
		//customer = p; 

		cardListener = new CardListener();

		cardTypeList = new JList<>(cardTypeString);
		cardTypeList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		cardTypeList.setSelectedIndex(0);
		cardTypeList.addListSelectionListener( cardListener );

		add( custIDLbl );
		add( salesWindowCustIDtf );
		add( cardTypeLbl );
		add( cardTypeList );
		add( salesAddCartBtn );
		add( salesCheckoutBtn );


	} 

	private void addCard()
	{
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
				addCard();
			}
		}

	}
}