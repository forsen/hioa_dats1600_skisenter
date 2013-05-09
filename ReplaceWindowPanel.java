import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;


public class ReplaceWindowPanel extends JPanel 
{
	private JLabel custIdLb, cardNrLb;
	private JButton  replaceWindowRepBtn;
	public static JTextField replaceWindowCustIdtf;
	private JTextArea statusTxt;
	private JPanel formPnl, resultPnl, contentPnl;
	private Listener listener;
	public static JList<Card> cardIDList;
	private ListListener listListener;
	private JScrollPane cardScrollList;
	private Card card;
	private Toolkit toolbox;
	public ReplaceWindowPanel(JTextArea s)
	{
		Border etched = BorderFactory.createEtchedBorder();
		Border header = BorderFactory.createTitledBorder(etched, "Erstatt kort");
		Border repBorder = BorderFactory.createTitledBorder(etched, "Kundenummer");
		
		card = null;
		toolbox = Toolkit.getDefaultToolkit();


		setBackground(new Color(200, 230, 255));

		contentPnl = new JPanel();
		contentPnl.setBackground(new Color(200, 230, 255));
		contentPnl.setBorder(header);
	
		listener = new Listener();
		listListener = new ListListener(); 

		cardIDList = new JList<Card>( new DefaultListModel<Card>());
		cardIDList.setFixedCellHeight(15);
		cardIDList.setFixedCellWidth(100);
		cardIDList.setVisibleRowCount( 4 );
		cardIDList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		cardIDList.setCellRenderer( new CardListCellRenderer() );
		cardIDList.addListSelectionListener( listListener );
		cardScrollList = new JScrollPane( cardIDList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		resultPnl= new JPanel();
		resultPnl.add(cardScrollList);
		resultPnl.setBackground(new Color(200, 230, 255));

		Border resultBrd = BorderFactory.createTitledBorder(etched, "Kortnummer");
		resultPnl.setBorder(resultBrd);


		custIdLb = new JLabel( "Kundenummer" ) ;

		replaceWindowCustIdtf = new JTextField(12);
		replaceWindowCustIdtf.setToolTipText("Har du søkt på en kunde og trykket på denne vil kundenr automatisk komme opp her");


		replaceWindowCustIdtf.setEditable( false );
	//	fielPnl.add(replaceWindowCustIdtf);

		cardNrLb = new JLabel( "Kortnummer" ) ;
		cardNrLb.setToolTipText("Har du søkt på en kunde og trykket på denne vil kortene automatisk komme opp her");
	//	fielPnl.add(cardScrollList);

		replaceWindowRepBtn = new JButton(" Erstatt kort ");
		replaceWindowRepBtn.setToolTipText("Erstatt kortet du har valgt");
		replaceWindowRepBtn.addActionListener( listener );
	//	butnFld.add(replaceWindowRepBtn);
		statusTxt = s;

	/*	add(labelPnl, BorderLayout.LINE_START);
		add(fielPnl, BorderLayout.LINE_END);
		add(butnFld, BorderLayout.PAGE_END);*/

		formPnl = new JPanel();
		formPnl.setLayout(new GridBagLayout());
		formPnl.setBorder(repBorder);
		formPnl.setBackground(new Color(200, 230, 255));

		GridBagConstraints fc = new GridBagConstraints();

		fc.fill = GridBagConstraints.BOTH;

		formPnl.add(replaceWindowCustIdtf, fc);

		contentPnl.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;

		c.weightx = 0.5;
		c.weighty= 0.5;

		c.gridx = 0;
		c.gridy = 0;
		contentPnl.add(formPnl,c);

		c.gridx=0;
		c.gridy=1;
		contentPnl.add(replaceWindowRepBtn, c);

		c.gridx=1;
		c.gridy=0;
		c.gridheight=2;
		contentPnl.add(resultPnl,c);


		setLayout(new GridBagLayout());
		GridBagConstraints cc = new GridBagConstraints();

		cc.fill=GridBagConstraints.NONE;
		cc.anchor= GridBagConstraints.FIRST_LINE_START;
		cc.weighty=1;
		cc.weightx=1;
		add(contentPnl, cc);	
		
	}

	private void search()
	{
		
		try	
		{
			int custid = Integer.parseInt(replaceWindowCustIdtf.getText());

			//String item = ""; 

			ReplaceWindowPanel.cardIDList.setModel( Salesclerk.customer.listCards() );	
			
		}
		catch( NumberFormatException nfe )
		{
			//JOptionPane.showMessageDialog(null, "Må putte inn siffer");
			statusTxt.setText( "Kan kun bestå av siffer.");
		}


	} 

	private void replace()
	{
		
		Salesclerk.customer.removeCard(card);
		Card nCard = new Card( new Date() ); 
		nCard.setSkicardlist( card.getSkicardlist());
		nCard.setCurrent(card.getCurrent());
		Salesclerk.customer.addCard(nCard);

		statusTxt.setText( "Kortet med kortnr: " + card.getCardID() + " ble er erstattet med kortnr: " + nCard.getCardID());

	}

	private class Listener implements ActionListener
  	{
   		public void actionPerformed( ActionEvent e )
    	{ 	
      		
      		if(e.getSource() == replaceWindowRepBtn)
      		{
      			replace();
      			System.out.println("Du har trykka på erstatt");
      		}
      		
    	}
	}	

	private class ListListener implements ListSelectionListener
	{
		public void valueChanged( ListSelectionEvent lse )
		{
			try
			{
				card = (Card) cardIDList.getSelectedValue();
				Salesclerk.salesClerkSearchInfoTxt.setText(card.history());
			}
			catch( ArrayIndexOutOfBoundsException aioobe )
			{
				// when making a new search, index will be out of bound. We use this exception 
				// to clear the text field.	
				System.out.println( "This is an exception" );

			}
			catch( NullPointerException npe)
			{
				System.out.println( "No Value is selected" );
			}

		}
	}
}