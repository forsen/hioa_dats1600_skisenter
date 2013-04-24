import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import javax.swing.border.*;


public class CashRegister extends JFrame
{
	private JTextArea orderList, total, overview; 
	private ShoppingCart shoppingCart; 
	private JList<CartItems> shoppingCartList;
	private List<Card> printableCards; 
	private JButton printReceipt, printCard, payByCash, payByCard, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, zeroBtn, cancelBtn, clearBtn, dotBtn, okBtn, corrBtn;
	private JTextField cashInput;
	private BtnListener btnListener;
	private JScrollPane scroll;  
	private JPanel contentPanel, orderPanel, overviewPanel, bigButtonPanel, smallButtonPanel;
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

		contentPanel = new JPanel()
		{
			@Override
			protected void paintComponent(Graphics grphcs)
			{		
				Graphics2D g2d = (Graphics2D) grphcs;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				Color color1 = new Color(44, 44, 44);
				Color color2 = new Color(70, 70, 70);

				GradientPaint gp = new GradientPaint(0,0, color1, 0, getHeight(), color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());

			}

		};

		contentPanel.setOpaque(false);
		contentPanel.setLayout(new GridLayout(2, 2));

		Border etched = BorderFactory.createEtchedBorder();

		// bilder i bigButtonPanel

				ImageIcon paycash = new ImageIcon("img/cash/paycash.png");
				ImageIcon paycashp = new ImageIcon("img/cash/paycashp.png");
				ImageIcon paycard = new ImageIcon("img/cash/paycard.png");
				ImageIcon paycardp = new ImageIcon("img/cash/paycardp.png");
				ImageIcon printreceipt = new ImageIcon("img/cash/printreceipt.png");
				ImageIcon printreceiptp = new ImageIcon("img/cash/printreceiptp.png");
				ImageIcon printcard = new ImageIcon("img/cash/printcard.png");
				ImageIcon printcardp = new ImageIcon("img/cash/printcardp.png");

		// bilder i smallButtonPanel


				ImageIcon one = new ImageIcon("img/cash/1.png");
				ImageIcon two = new ImageIcon("img/cash/2.png");
				ImageIcon three = new ImageIcon("img/cash/3.png");
				ImageIcon four = new ImageIcon("img/cash/4.png");
				ImageIcon five = new ImageIcon("img/cash/5.png");
				ImageIcon six = new ImageIcon("img/cash/6.png");
				ImageIcon seven = new ImageIcon("img/cash/7.png");
				ImageIcon eight = new ImageIcon("img/cash/8.png");
				ImageIcon nine = new ImageIcon("img/cash/9.png");
				ImageIcon zero = new ImageIcon("img/cash/0.png");
				ImageIcon clear = new ImageIcon("img/cash/clear.png");
				ImageIcon corr = new ImageIcon("img/cash/corr.png");
				ImageIcon dot = new ImageIcon("img/cash/dot.png");
				ImageIcon ok = new ImageIcon("img/cash/ok.png");
				ImageIcon cancel = new ImageIcon("img/cash/cancel.png");






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

			printReceipt = new JButton(printreceipt);
			printReceipt.addActionListener( btnListener );
			printCard = new JButton(printcard);
			printCard.addActionListener( btnListener );
			payByCard = new JButton(paycard);
			payByCard.addActionListener( btnListener );
			payByCash = new JButton(paycash);
			payByCash.addActionListener( btnListener );

			oneBtn = new JButton(one);
			oneBtn.addActionListener( btnListener );
			twoBtn = new JButton(two);
			twoBtn.addActionListener( btnListener );
			threeBtn = new JButton(three);
			threeBtn.addActionListener( btnListener );
			fourBtn = new JButton(four);
			fourBtn.addActionListener( btnListener );
			fiveBtn = new JButton(five);
			fiveBtn.addActionListener( btnListener );
			sixBtn = new JButton(six);
			sixBtn.addActionListener( btnListener );
			sevenBtn = new JButton(seven);
			sevenBtn.addActionListener( btnListener );
			eightBtn = new JButton(eight);
			eightBtn.addActionListener( btnListener );
			nineBtn = new JButton(nine);
			nineBtn.addActionListener( btnListener );
			zeroBtn = new JButton(zero);
			zeroBtn.addActionListener( btnListener );
			clearBtn = new JButton(clear);
			clearBtn.addActionListener( btnListener );
			cancelBtn = new JButton(cancel);
			cancelBtn.addActionListener( btnListener );
			dotBtn = new JButton(dot);
			dotBtn.addActionListener( btnListener );
			okBtn = new JButton(ok);
			okBtn.addActionListener( btnListener );
			corrBtn = new JButton(corr);
			corrBtn.addActionListener( btnListener );









		cashInput = new JTextField( 7 );
		cashInn = new JLabel( "Betalt: " );

		scroll = new JScrollPane( orderList ); 


		paymentMethod = new double[2]; 

		printCard.setEnabled(false );
		printReceipt.setEnabled( false );



		// Panel 1/4


		orderPanel = new JPanel();
		orderPanel.setBorder(etched);
		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
		scroll.setMaximumSize(new Dimension(300,300));
		orderPanel.add(scroll);


		// Panel 2/4


		overviewPanel = new JPanel();
		overviewPanel.setBorder(etched);
		overviewPanel.setLayout(new BoxLayout(overviewPanel, BoxLayout.Y_AXIS));
		overview.setMaximumSize(new Dimension(460,300));
		overviewPanel.add(overview);
		cashInput.setMaximumSize(new Dimension(460, 30));
		overviewPanel.add(cashInput);


		// Panel 3/4


		bigButtonPanel = new JPanel();
		bigButtonPanel.setBorder(etched);




		bigButtonPanel.setLayout(new GridBagLayout());

		GridBagConstraints bc = new GridBagConstraints();

		bc.fill = GridBagConstraints.BOTH;
		bc.gridx=0;
		bc.gridy=0;
		bc.gridwidth=2;
		bigButtonPanel.add(printReceipt, bc);

		bc.gridx=0;
		bc.gridy=1;
		bigButtonPanel.add(printCard, bc);

		bc.gridy=2;
		bc.gridwidth=1;
		bigButtonPanel.add(payByCash, bc);

		bc.gridy=2;
		bc.gridx=1;
		bigButtonPanel.add(payByCard, bc);


		// Panel 4/4


		smallButtonPanel = new JPanel();
		smallButtonPanel.setBorder(etched);
		smallButtonPanel.setLayout(new GridBagLayout());

		GridBagConstraints sc = new GridBagConstraints();

		sc.fill = GridBagConstraints.BOTH;
		sc.gridx=0;
		smallButtonPanel.add(sevenBtn, sc);

		sc.gridx=1;
		smallButtonPanel.add(eightBtn, sc);

		sc.gridx=2;
		smallButtonPanel.add(nineBtn, sc);

		sc.gridx=3;
		smallButtonPanel.add(corrBtn, sc);

		sc.gridx=0;
		sc.gridy=1;
		smallButtonPanel.add(fourBtn, sc);

		sc.gridx=1;
		smallButtonPanel.add(fiveBtn, sc);

		sc.gridx=2;
		smallButtonPanel.add(sixBtn, sc);

		sc.gridx=3;
		smallButtonPanel.add(cancelBtn, sc);

		sc.gridx=0;
		sc.gridy=2;
		smallButtonPanel.add(oneBtn, sc);

		sc.gridx=1;
		smallButtonPanel.add(twoBtn, sc);

		sc.gridx=2;
		smallButtonPanel.add(threeBtn, sc);

		sc.gridx=3;
		sc.gridheight=2;
		smallButtonPanel.add(okBtn, sc);

		sc.gridx=0;
		sc.gridheight=1;
		sc.gridy=3;
		smallButtonPanel.add(zeroBtn, sc);

		sc.gridx=1;
		smallButtonPanel.add(dotBtn, sc);

		sc.gridx=2;
		smallButtonPanel.add(clearBtn, sc);







/*		setLayout( new GridBagLayout() );

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


	}

*/
			// knappemodifikasjoner

						oneBtn.setFocusPainted(false);
						oneBtn.setContentAreaFilled(false);
						oneBtn.setBorderPainted(false);
						twoBtn.setFocusPainted(false);
						twoBtn.setContentAreaFilled(false);
						twoBtn.setBorderPainted(false);
						threeBtn.setFocusPainted(false);
						threeBtn.setContentAreaFilled(false);
						threeBtn.setBorderPainted(false);
						fourBtn.setFocusPainted(false);
						fourBtn.setContentAreaFilled(false);
						fourBtn.setBorderPainted(false);
						fiveBtn.setFocusPainted(false);
						fiveBtn.setContentAreaFilled(false);
						fiveBtn.setBorderPainted(false);
						sixBtn.setFocusPainted(false);
						sixBtn.setContentAreaFilled(false);
						sixBtn.setBorderPainted(false);
						sevenBtn.setFocusPainted(false);
						sevenBtn.setContentAreaFilled(false);
						sevenBtn.setBorderPainted(false);
						eightBtn.setFocusPainted(false);
						eightBtn.setContentAreaFilled(false);
						eightBtn.setBorderPainted(false);
						nineBtn.setFocusPainted(false);
						nineBtn.setContentAreaFilled(false);
						nineBtn.setBorderPainted(false);
						zeroBtn.setFocusPainted(false);
						zeroBtn.setContentAreaFilled(false);
						zeroBtn.setBorderPainted(false);
						clearBtn.setFocusPainted(false);
						clearBtn.setContentAreaFilled(false);
						clearBtn.setBorderPainted(false);
						dotBtn.setFocusPainted(false);
						dotBtn.setContentAreaFilled(false);
						dotBtn.setBorderPainted(false);
						cancelBtn.setFocusPainted(false);
						cancelBtn.setContentAreaFilled(false);
						cancelBtn.setBorderPainted(false);
						okBtn.setFocusPainted(false);
						okBtn.setContentAreaFilled(false);
						okBtn.setBorderPainted(false);
						corrBtn.setFocusPainted(false);
						corrBtn.setContentAreaFilled(false);
						corrBtn.setBorderPainted(false);
						payByCard.setFocusPainted(false);
						payByCard.setContentAreaFilled(false);
						payByCard.setBorderPainted(false);
						payByCash.setFocusPainted(false);
						payByCash.setContentAreaFilled(false);
						payByCash.setBorderPainted(false);
						printReceipt.setFocusPainted(false);
						printReceipt.setContentAreaFilled(false);
						printReceipt.setBorderPainted(false);
						printCard.setFocusPainted(false);
						printCard.setContentAreaFilled(false);
						printCard.setBorderPainted(false);


		contentPanel.add(orderPanel);
		contentPanel.add(overviewPanel);
		contentPanel.add(bigButtonPanel);
		contentPanel.add(smallButtonPanel);
		add(contentPanel);


		pack();
		setVisible( true ); 

		this.addWindowListener( new WindowAdapter() 
		{
			public void windowClosing( WindowEvent e )
			{
				System.out.println("Sletter all dataaaaaa....");
				shoppingCartList.setModel( new DefaultListModel<CartItems>() );
				ShoppingCart.emptyCart();
			}
		});
	
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
			shoppingCart.checkOut();
			printableCards = shoppingCart.getNewCards();
		}
	}

	private void printCard()
	{
		Iterator<Card> it = printableCards.iterator(); 

		while( it.hasNext() )
		{
			PrintWindow w = new PrintWindow( it.next() );
		}

		System.out.println( "lengde: " + shoppingCartList.getModel().getSize() );
		for( int i = 0; i < shoppingCartList.getModel().getSize(); i++ )
		{
			System.out.println("Skjer det noe her?");
			PrintWindow w = new PrintWindow( shoppingCartList.getModel().getElementAt(i).getCard());
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


