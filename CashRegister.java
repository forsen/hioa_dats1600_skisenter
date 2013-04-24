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
	private Action keyLstnr; 
	private Font font;
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

		// bilder i bigButtonPanel

				ImageIcon paycash = new ImageIcon("img/cash/paycash.png");
				ImageIcon paycashp = new ImageIcon("img/cash/paycashp.png");
				ImageIcon paycard = new ImageIcon("img/cash/paycard.png");
				ImageIcon paycardp = new ImageIcon("img/cash/paycardp.png");
				ImageIcon printreceipt = new ImageIcon("img/cash/printreceipt.png");
				ImageIcon printreceiptp = new ImageIcon("img/cash/printreceiptp.png");
				ImageIcon printcard = new ImageIcon("img/cash/printcard.png");
				ImageIcon printcardp = new ImageIcon("img/cash/printcardp.png");
				ImageIcon printcardua = new ImageIcon("img/cash/printcardua.png");
				ImageIcon printreceiptua = new ImageIcon("img/cash/printreceiptua.png");

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

				ImageIcon onep= new ImageIcon("img/cash/1p.png");
				ImageIcon twop = new ImageIcon("img/cash/2p.png");
				ImageIcon threep = new ImageIcon("img/cash/3p.png");
				ImageIcon fourp = new ImageIcon("img/cash/4p.png");
				ImageIcon fivep = new ImageIcon("img/cash/5p.png");
				ImageIcon sixp = new ImageIcon("img/cash/6p.png");
				ImageIcon sevenp = new ImageIcon("img/cash/7p.png");
				ImageIcon eightp = new ImageIcon("img/cash/8p.png");
				ImageIcon ninep = new ImageIcon("img/cash/9p.png");
				ImageIcon zerop = new ImageIcon("img/cash/0p.png");
				ImageIcon clearp = new ImageIcon("img/cash/clearp.png");
				ImageIcon corrp = new ImageIcon("img/cash/corrp.png");
				ImageIcon dotp = new ImageIcon("img/cash/dotp.png");
				ImageIcon okp = new ImageIcon("img/cash/okp.png");
				ImageIcon cancelp = new ImageIcon("img/cash/cancelp.png");






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
		total.setText("Total: \t\t" + sum + ",-");
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
			okBtn = new JButton(ok);
			okBtn.addActionListener( btnListener );
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
						okBtn.setPressedIcon(okp);
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

		setFonts("digital.ttf");
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


	private class KeyLstnr extends AbstractAction
	{
		public void actionPerformed( ActionEvent e )
		{
			JButton button = (JButton)e.getSource();
			button.doClick(); 
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
					// no more characters to remove.. Doing nothing.
				}
			}
			if( ae.getSource() == clearBtn )
				cashInput.setText("");
			if (ae.getSource() == cancelBtn )
			{
				// find a way to close this window
			}


		}


	}



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
			JOptionPane.showMessageDialog(null,"Finner ikke fonten. Bruker standard font.");
			return false;
		}
	}


}


