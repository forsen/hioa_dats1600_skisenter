package skisenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.util.Date;
import java.util.List;
import java.awt.Font;
import java.util.Iterator;
import javax.swing.border.*;
import java.io.*;
import java.text.NumberFormat; 
import java.util.Locale; 

/**
  * This class creates the Cash Register dialog. 
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */
public class CashRegister extends JDialog
{
	private JTextArea orderList, total, overview; 
	private ShoppingCart shoppingCart; 
	private JList<CartItems> shoppingCartList;
	private List<Card> printableCards; 
	private JButton printReceipt, printCard, payByCash, payByCard, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, zeroBtn, cancelBtn, clearBtn, dotBtn, corrBtn;
	private JTextField cashInput;
	private BtnListener btnListener;
	private JScrollPane scroll;  
	private JPanel contentPanel, orderPanel, overviewPanel, bigButtonPanel, smallButtonPanel;
	private JLabel cashInn;
	private double remains;
	private double sum; 
	private double paid; 
	private double[] paymentMethod;
	private Action keyLstnr; 
	private Font font;
	private NumberFormat paymentFormat; 

	public static final int CARD = 0;
	public static final int CASH = 1; 





/**
  * This constructor sets up the Cash Register with all the panels / buttons / fields etc. 
  * @param parent 	The parent window, for modality
  * @param s 		The shoppingcart to pay
  * @param sl 		All the items in the shoppingcart
  */	
	public CashRegister(Window parent, ShoppingCart s, JList<CartItems> sl )
	{
		super(parent, "Betaling", Dialog.ModalityType.DOCUMENT_MODAL );


		addWindowListener( new WindowAdapter() 
		{

			public void windowClosing( WindowEvent e )
			{
				shoppingCartList.setModel( ShoppingCart.emptyCart() );
				SalesWindowPanel.updateCartPrice();
				SalesWindowPanel.cardIDList.setModel( new DefaultListModel<Card>() );
				
				
			}
		});

		paymentFormat = NumberFormat.getCurrencyInstance( new Locale( "no", "NO") );
		paymentFormat.setGroupingUsed( false );


		contentPanel = new JPanel()
		{
			@Override
			protected void paintComponent(Graphics grphcs)
			{		
				Graphics2D g2d = (Graphics2D) grphcs;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				Color color1 = new Color(100, 100, 100);
				Color color2 = new Color(44, 44, 44);

				GradientPaint gp = new GradientPaint(0,0, color1, 0, getHeight(), color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());

			}

		};

		contentPanel.setOpaque(false);
		contentPanel.setLayout(new GridLayout(2, 2));

		Border etched = BorderFactory.createEtchedBorder();

		// images in bigButtonPanel

		ImageIcon paycash = new ImageIcon(getClass().getResource("img/cash/paycash.png"));
		ImageIcon paycashp = new ImageIcon(getClass().getResource("img/cash/paycashp.png"));
		ImageIcon paycard = new ImageIcon(getClass().getResource("img/cash/paycard.png"));
		ImageIcon paycardp = new ImageIcon(getClass().getResource("img/cash/paycardp.png"));
		ImageIcon printreceipt = new ImageIcon(getClass().getResource("img/cash/printreceipt.png"));
		ImageIcon printreceiptp = new ImageIcon(getClass().getResource("img/cash/printreceiptp.png"));
		ImageIcon printcard = new ImageIcon(getClass().getResource("img/cash/printcard.png"));
		ImageIcon printcardp = new ImageIcon(getClass().getResource("img/cash/printcardp.png"));
		ImageIcon printcardua = new ImageIcon(getClass().getResource("img/cash/printcardua.png"));
		ImageIcon printreceiptua = new ImageIcon(getClass().getResource("img/cash/printreceiptua.png"));

		// images in smallButtonPanel


		ImageIcon one = new ImageIcon(getClass().getResource("img/cash/1.png"));
		ImageIcon two = new ImageIcon(getClass().getResource("img/cash/2.png"));
		ImageIcon three = new ImageIcon(getClass().getResource("img/cash/3.png"));
		ImageIcon four = new ImageIcon(getClass().getResource("img/cash/4.png"));
		ImageIcon five = new ImageIcon(getClass().getResource("img/cash/5.png"));
		ImageIcon six = new ImageIcon(getClass().getResource("img/cash/6.png"));
		ImageIcon seven = new ImageIcon(getClass().getResource("img/cash/7.png"));
		ImageIcon eight = new ImageIcon(getClass().getResource("img/cash/8.png"));
		ImageIcon nine = new ImageIcon(getClass().getResource("img/cash/9.png"));
		ImageIcon zero = new ImageIcon(getClass().getResource("img/cash/0.png"));
		ImageIcon clear = new ImageIcon(getClass().getResource("img/cash/clear.png"));
		ImageIcon corr = new ImageIcon(getClass().getResource("img/cash/corr.png"));
		ImageIcon dot = new ImageIcon(getClass().getResource("img/cash/dot.png"));

		ImageIcon cancel = new ImageIcon(getClass().getResource("img/cash/cancel.png"));
		ImageIcon onep= new ImageIcon(getClass().getResource("img/cash/1p.png"));
		ImageIcon twop = new ImageIcon(getClass().getResource("img/cash/2p.png"));
		ImageIcon threep = new ImageIcon(getClass().getResource("img/cash/3p.png"));
		ImageIcon fourp = new ImageIcon(getClass().getResource("img/cash/4p.png"));
		ImageIcon fivep = new ImageIcon(getClass().getResource("img/cash/5p.png"));
		ImageIcon sixp = new ImageIcon(getClass().getResource("img/cash/6p.png"));
		ImageIcon sevenp = new ImageIcon(getClass().getResource("img/cash/7p.png"));
		ImageIcon eightp = new ImageIcon(getClass().getResource("img/cash/8p.png"));
		ImageIcon ninep = new ImageIcon(getClass().getResource("img/cash/9p.png"));
		ImageIcon zerop = new ImageIcon(getClass().getResource("img/cash/0p.png"));
		ImageIcon clearp = new ImageIcon(getClass().getResource("img/cash/clearp.png"));
		ImageIcon corrp = new ImageIcon(getClass().getResource("img/cash/corrp.png"));
		ImageIcon dotp = new ImageIcon(getClass().getResource("img/cash/dotp.png"));
		ImageIcon cancelp = new ImageIcon(getClass().getResource("img/cash/cancelp.png"));




		shoppingCart = s;
		shoppingCartList = sl; 
		btnListener = new BtnListener();
		keyLstnr = new KeyLstnr(); 
		orderList = new JTextArea(  );
		orderList.setText( shoppingCart.toString() );
		orderList.setEditable(false);
		sum = shoppingCart.getSum(); 
		remains = sum; 
		total = new JTextArea();
		total.setMaximumSize(new Dimension(300,30));
		total.setBackground(new Color(187,229,171));
		total.setEditable(false);
		total.setText("Total: \t\t" + paymentFormat.format( sum ) );
		overview = new JTextArea();
		overview.setBackground(new Color(211,244,212));
		overview.setEditable(false);
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
		oneBtn.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke(KeyEvent.VK_1,0), "1");
		oneBtn.getActionMap().put("1", keyLstnr );
		oneBtn.addActionListener( btnListener );
		twoBtn = new JButton(two);
		twoBtn.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke( KeyEvent.VK_2,0), "2");
		twoBtn.getActionMap().put("2", keyLstnr );
		twoBtn.addActionListener( btnListener );
		threeBtn = new JButton(three);
		threeBtn.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke( KeyEvent.VK_3,0), "3");
		threeBtn.getActionMap().put("3", keyLstnr );
		threeBtn.addActionListener( btnListener );
		fourBtn = new JButton(four);
		fourBtn.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke( KeyEvent.VK_4,0), "4");
		fourBtn.getActionMap().put("4", keyLstnr );
		fourBtn.addActionListener( btnListener );
		fiveBtn = new JButton(five);
		fiveBtn.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke( KeyEvent.VK_5,0), "5");
		fiveBtn.getActionMap().put("5", keyLstnr );
		fiveBtn.addActionListener( btnListener );
		sixBtn = new JButton(six);
		sixBtn.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke( KeyEvent.VK_6,0), "6");
		sixBtn.getActionMap().put("6", keyLstnr );
		sixBtn.addActionListener( btnListener );
		sevenBtn = new JButton(seven);
		sevenBtn.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke( KeyEvent.VK_7,0), "7");
		sevenBtn.getActionMap().put("7", keyLstnr );
		sevenBtn.addActionListener( btnListener );
		eightBtn = new JButton(eight);
		eightBtn.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke( KeyEvent.VK_8,0), "8");
		eightBtn.getActionMap().put("8", keyLstnr );
		eightBtn.addActionListener( btnListener );
		nineBtn = new JButton(nine);
		nineBtn.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke( KeyEvent.VK_9,0), "9");
		nineBtn.getActionMap().put("9", keyLstnr );			
		nineBtn.addActionListener( btnListener );
		zeroBtn = new JButton(zero);
		zeroBtn.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke( KeyEvent.VK_0,0), "0");
		zeroBtn.getActionMap().put("0", keyLstnr );
		zeroBtn.addActionListener( btnListener );
		clearBtn = new JButton(clear);
		clearBtn.addActionListener( btnListener );
		cancelBtn = new JButton(cancel);
		cancelBtn.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke( KeyEvent.VK_ESCAPE,0), "esc");
		cancelBtn.getActionMap().put("esc", keyLstnr );
		cancelBtn.addActionListener( btnListener );
		dotBtn = new JButton(dot);
		dotBtn.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke( KeyEvent.VK_PERIOD,0), "dot");
		dotBtn.getActionMap().put("dot", keyLstnr );
		dotBtn.addActionListener( btnListener );
		corrBtn = new JButton(corr);
		corrBtn.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke( KeyEvent.VK_BACK_SPACE,0), "bckspc");
		corrBtn.getActionMap().put("bckspc", keyLstnr );
		corrBtn.addActionListener( btnListener );

		cashInput = new JTextField( 7 );
		cashInput.setBackground(new Color(187,229,171));
		cashInput.setEditable( false );

		cashInn = new JLabel( "Betalt: " );

		scroll = new JScrollPane( orderList );
		orderList.setBackground(new Color(211,244,212)); 
		orderList.setEditable(false);


		paymentMethod = new double[2]; 

		printCard.setEnabled(false );
		printReceipt.setEnabled( false );

		// Panel 1/4


		orderPanel = new JPanel()
		{
   			protected void paintComponent(Graphics g)
   		 	{
        		g.setColor( getBackground() );
        		g.fillRect(0, 0, getWidth(), getHeight());
        		super.paintComponent(g);
    		}
		};
		orderPanel.setOpaque(false);
		orderPanel.setBackground( new Color(0, 0, 0, 0) );
		orderPanel.setBorder(etched);
		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
		scroll.setMaximumSize(new Dimension(300,300));
		orderPanel.add(scroll);
		orderPanel.add(total);


		// Panel 2/4


		overviewPanel = new JPanel()
		{
   			protected void paintComponent(Graphics g)
   		 	{
        		g.setColor( getBackground() );
        		g.fillRect(0, 0, getWidth(), getHeight());
        		super.paintComponent(g);
    		}
		};
		overviewPanel.setOpaque(false);
		overviewPanel.setBackground( new Color(0, 0, 0, 0) );




		overviewPanel.setBorder(etched);
		overviewPanel.setLayout(new BoxLayout(overviewPanel, BoxLayout.Y_AXIS));
		overview.setMaximumSize(new Dimension(460,300));
		overviewPanel.add(overview);
		cashInput.setMaximumSize(new Dimension(460, 30));
		overviewPanel.add(cashInput);


		// Panel 3/4


		bigButtonPanel = new JPanel()
		{
   			protected void paintComponent(Graphics g)
   		 	{
        		g.setColor( getBackground() );
        		g.fillRect(0, 0, getWidth(), getHeight());
        		super.paintComponent(g);
    		}
		};

		bigButtonPanel.setOpaque(false);
		bigButtonPanel.setBackground( new Color(0, 0, 0, 0) );

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


		smallButtonPanel = new JPanel()
		{
   			protected void paintComponent(Graphics g)
   		 	{
        		g.setColor( getBackground() );
        		g.fillRect(0, 0, getWidth(), getHeight());
        		super.paintComponent(g);
    		}
		};
		smallButtonPanel.setOpaque(false);
		smallButtonPanel.setBackground( new Color(0, 0, 0, 0) );



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
		sc.gridheight=2;
		smallButtonPanel.add(corrBtn, sc);

		sc.gridx=0;
		sc.gridheight=1;
		sc.gridy=1;
		smallButtonPanel.add(fourBtn, sc);

		sc.gridx=1;
		smallButtonPanel.add(fiveBtn, sc);

		sc.gridx=2;
		smallButtonPanel.add(sixBtn, sc);


		sc.gridx=0;
		sc.gridy=2;
		smallButtonPanel.add(oneBtn, sc);

		sc.gridx=1;
		smallButtonPanel.add(twoBtn, sc);

		sc.gridx=2;
		smallButtonPanel.add(threeBtn, sc);

		sc.gridx=3;
		sc.gridheight=2;
		smallButtonPanel.add(cancelBtn, sc);

		sc.gridx=0;
		sc.gridheight=1;
		sc.gridy=3;
		smallButtonPanel.add(zeroBtn, sc);

		sc.gridx=1;
		smallButtonPanel.add(dotBtn, sc);

		sc.gridx=2;
		smallButtonPanel.add(clearBtn, sc);





		// button modifications

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

		oneBtn.setPressedIcon(onep);
		twoBtn.setPressedIcon(twop);
		threeBtn.setPressedIcon(threep);
		fourBtn.setPressedIcon(fourp);
		fiveBtn.setPressedIcon(fivep);
		sixBtn.setPressedIcon(sixp);
		sevenBtn.setPressedIcon(sevenp);
		eightBtn.setPressedIcon(eightp);
		nineBtn.setPressedIcon(ninep);
		zeroBtn.setPressedIcon(zerop);
		dotBtn.setPressedIcon(dotp);
		clearBtn.setPressedIcon(clearp);
		cancelBtn.setPressedIcon(cancelp);
		corrBtn.setPressedIcon(corrp);
		
		payByCash.setPressedIcon(paycashp);
		payByCard.setPressedIcon(paycardp);
		printReceipt.setPressedIcon(printreceiptp);
		printReceipt.setDisabledIcon(printreceiptua);
		printCard.setPressedIcon(printcardp);
		printCard.setDisabledIcon(printcardua);



		contentPanel.add(orderPanel);
		contentPanel.add(overviewPanel);
		contentPanel.add(bigButtonPanel);
		contentPanel.add(smallButtonPanel);
		add(contentPanel);

		setFonts(getClass().getResource("font/digital.ttf").getPath());
		pack();
		setVisible( true ); 
	
	}

/**
  * Method to receive payment. It will register how you paid (by cash or card), how much you paid, 
  * how much is left to be paid, and finalize the payment if everything is paid for.
  * @param n 	This is the constant to tell if the payment was made by cash or by card. 
  */
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
				paymentMethod[CASH] += paid ; 
			}
			else if( n == CARD )
			{
				s += "med kort:";
				paymentMethod[CARD] += paid; 
			}

			s += " \t" + paymentFormat.format( paid ) + "\n\nRest: \t\t" + paymentFormat.format( remains );
			overview.append( s ); 
			cashInput.setText("");

		}
		catch( NumberFormatException nfe )
		{
			if( sum > 0.0 )
				JOptionPane.showMessageDialog(this, "Feltet kan kun bestå av tall, og \".\" er kommaseparator" );
		}

		if( remains <= 0.0 )
		{
			printReceipt.setEnabled( true );
			printCard.setEnabled( true );
			shoppingCart.checkOut();
			printableCards = shoppingCart.getNewCards();
			payByCash.setEnabled( false );
			payByCard.setEnabled( false );
		}
	}
/**
  * Method to print the purchased cards. The button to activate this method will be enabled
  * as soon as everything is paid. 
  * @see PrintWindow
  */
	private void printCard()
	{
		Iterator<Card> it = printableCards.iterator(); 

		while( it.hasNext() )
		{
			Card c = it.next();		
			if( c.getCurrent() == null )
			{
				PrintWindow w = new PrintWindow( this, c );
			}
		}

		for( int i = 0; i < shoppingCartList.getModel().getSize(); i++ )
		{
			Card pc = shoppingCartList.getModel().getElementAt(i).getCard();
			if( !pc.getReturned() )
			{
				PrintWindow w = new PrintWindow( this, pc );
			}
		}

	}
/**
  * Method to print the receipt. The button to activate this method will be enabled
  * as soon as everything is paid. 
  * @see PrintWindow
  */
	private void printReceipt()
	{
		PrintWindow w = new PrintWindow( this, orderList, paymentMethod, sum );
	}

/**
  * This class will simulate the "click" animation on the button when shortkeys are used instead of mouse click.
  */
	private class KeyLstnr extends AbstractAction
	{
		public void actionPerformed( ActionEvent e )
		{
			JButton button = (JButton)e.getSource();
			button.doClick(); 
		}
	}
/**
 * This Listener initiates methods and types inn the number on the butten by listening on clicked buttons.
 */


	private class BtnListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			if( ae.getSource() == payByCash )
				paid(CASH);
			
			if( ae.getSource() == payByCard )
				paid(CARD);
			if( ae.getSource() == printReceipt )
				printReceipt();
			
			if( ae.getSource() == printCard )
				printCard(); 

			if( ae.getSource() == oneBtn )
				cashInput.setText(cashInput.getText() + "1");
			if( ae.getSource() == twoBtn )
				cashInput.setText(cashInput.getText() + "2");

			if( ae.getSource() == threeBtn )
				cashInput.setText(cashInput.getText() + "3");

			if( ae.getSource() == fourBtn )
				cashInput.setText(cashInput.getText() + "4");

			if( ae.getSource() == fiveBtn )
				cashInput.setText(cashInput.getText() + "5");

			if( ae.getSource() == sixBtn )
				cashInput.setText(cashInput.getText() + "6");

			if( ae.getSource() == sevenBtn )
				cashInput.setText(cashInput.getText() + "7");

			if( ae.getSource() == eightBtn )
				cashInput.setText(cashInput.getText() + "8");

			if( ae.getSource() == nineBtn )
				cashInput.setText(cashInput.getText() + "9");
			if( ae.getSource() == zeroBtn )
				cashInput.setText(cashInput.getText() + "0");
			if( ae.getSource() == dotBtn )
				cashInput.setText(cashInput.getText() + ".");
			if( ae.getSource() == corrBtn )
			{
				try
				{
					cashInput.setText(cashInput.getText().substring(0, cashInput.getText().length() - 1) );
				}
				catch( StringIndexOutOfBoundsException sioobe )
				{
					// no more characters to remove, not necessary to throw any errors here
				}
			}
			if( ae.getSource() == clearBtn )
				cashInput.setText("");
			if (ae.getSource() == cancelBtn )
			{
				shoppingCartList.setModel( ShoppingCart.emptyCart() );
				SalesWindowPanel.cardIDList.setModel( new DefaultListModel<Card>() );
				dispose();
			}


		}


	}


/**
  * A method to set a custom font.
  * @param s 	The filepath of the custom font you want to use
  * @return Returns true if it succeeded in setting the new font, false otherwise.
  */
	public boolean setFonts(String s)
	{
		try{
			font = Font.createFont(Font.TRUETYPE_FONT, new File(s));
			font = font.deriveFont(Font.PLAIN, 20.0f);
			overview.setFont(font);
			orderList.setFont(font);
			total.setFont(font);
			cashInput.setFont(font);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace( System.out );
			JOptionPane.showMessageDialog(null,"Finner ikke fonten. Bruker standard font.");
			return false;
		}
	}


}


