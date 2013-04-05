import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;

public class SalesWindowPanel extends JPanel
{
	private JLabel custIDLbl, cardTypeLbl;
	private JTextField custIDtf; 
	private JList<String> cardTypeList;
	private JButton salesAddCartBtn, salesCheckoutBtn; 
	private String[] cardTypeString; 
	private CardListener cardListener;
	private Person customer;

	public SalesWindowPanel( Person p )
	{
		custIDLbl = new JLabel( "Kundenr" );
		cardTypeLbl = new JLabel( "Korttype" );
		custIDtf = new JTextField( 3 );
		cardTypeString = new String[4];
		cardTypeString[0] = "Klippekort";
		cardTypeString[1] = "1-timeskort";
		cardTypeString[2] = "Dagskort";
		cardTypeString[3] = "Sesongkort";

		customer = p; 

		cardListener = new CardListener();

		cardTypeList = new JList<>(cardTypeString);
		cardTypeList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		cardTypeList.setSelectedIndex(0);
		cardTypeList.addListSelectionListener( cardListener );

		add( custIDLbl );
		add( custIDtf );
		add( cardTypeLbl );
		add( cardTypeList );

		setCustId();

	} 

	public void setCustId()
	{
		if( customer != null )
			custIDtf.setText( "" + customer.getCustId() );
	}

	private class CardListener implements ListSelectionListener
	{
		public void valueChanged( ListSelectionEvent lse )
		{
			System.out.println( cardTypeList.getSelectedIndex() );
		}
	}
}