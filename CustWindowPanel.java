 import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.swing.border.*;
import java.text.ParseException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Calendar;


public class CustWindowPanel extends JPanel
{	
	private JButton imageBtn,custWindowSearchBtn, custWindowRegBtn;
	private JPanel imagePnl,custWindowSearchInfoPnl, custWindowFirstNamePnl, custWindowLastNamePnl, custWindowPhonePnl, custWindowBornPnl, custWindowBtnPnl;
	private static JTextField custWindowFirstName, custWindowLastName, custWindowPhone, custWindowBorn;
	private JTextArea custWindowSearchInfoTxt;
	private JTextArea statusTxt;

	private JPanel formPnl, btnPnl, rsltPnl; 

	private CustListener custListener;

	private static JList<Person> list; 
	private DefaultListModel<Person> listmodel;
	private ListListener listListener;

	private JScrollPane scrolList;
	//private Person customer;

	private Toolkit toolbox;

	private Personlist custRegistry;
	private File img; 
	private ImageUtility iu;
	

	//public CustWindowPanel( Personlist cr, JTextArea s, Person p )
	public CustWindowPanel( Personlist cr, JTextArea s )
	{
		setBackground(new Color(200, 230, 255));
		setLayout( new GridBagLayout() );

		iu = new ImageUtility();
		img = null;
		list = new JList<Person>( new DefaultListModel<Person>()); 

		list.setVisibleRowCount(5);
		list.setFixedCellHeight(15);
		list.setFixedCellWidth(100);

		list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		list.setCellRenderer( new SearchListCellRenderer());
		list.setVisible( true );

		scrolList = new JScrollPane( list );
		scrolList.setPreferredSize(new Dimension(120,140));

//		list.setModel( listmodel );
		//customer = p; 

		formPnl = new JPanel( new GridLayout( 4,2 ));
		
		btnPnl = new JPanel(); 
		rsltPnl = new JPanel(); 
		imagePnl = new JPanel();

		Border etched = BorderFactory.createEtchedBorder();
		Border resultBdr = BorderFactory.createTitledBorder(etched, "Resultater");
		rsltPnl.setBorder(resultBdr);

		Border custBdr = BorderFactory.createTitledBorder(etched, "Kunde");
		formPnl.setBorder(custBdr);


		formPnl.setBackground(new Color(200, 230, 255));
		btnPnl.setBackground(new Color(200, 230, 255));
		rsltPnl.setBackground(new Color(200, 230, 255));
		imagePnl.setBackground(new Color(200, 230, 255));


		statusTxt = s;

		listListener = new ListListener(); 

		list.addListSelectionListener( listListener );

		toolbox = Toolkit.getDefaultToolkit();

		custRegistry = cr; 

		imageBtn = new JButton("Velg bilde");
		imageBtn.setToolTipText("Last opp bilde til kortet");
		custWindowSearchBtn = new JButton("Søk");
		custWindowSearchBtn.setToolTipText("Søk på kunde");
		custWindowRegBtn = new JButton("Endre/Ny kunde");
		custWindowRegBtn.setToolTipText("Registrer ny kunde");

		//custWindowFirstNamePnl = new JPanel( new FlowLayout() );
		//custWindowLastNamePnl = new JPanel( new FlowLayout() );
		//custWindowPhonePnl = new JPanel( new FlowLayout() );
		//custWindowBornPnl = new JPanel( new FlowLayout() );
		//custWindowBtnPnl = new JPanel( new FlowLayout() );

		custWindowFirstName = new JTextField(10);
		custWindowLastName = new JTextField(10);
		custWindowPhone = new JTextField(10);
		custWindowBorn = new JTextField(10);

		custListener = new CustListener();

		imageBtn.addActionListener( custListener );
		custWindowRegBtn.addActionListener( custListener );
		custWindowSearchBtn.addActionListener( custListener );
/*
		add(custWindowFirstNamePnl);
		add(custWindowLastNamePnl);
		add(custWindowPhonePnl);
		add(custWindowBornPnl);
		add(custWindowBtnPnl);
		add(list);
		add(custWindowSearchInfoTxt);
*/

		rsltPnl.add( scrolList );
		//rsltPnl.add( custWindowSearchInfoTxt );


/*
		custWindowFirstNamePnl.add( new JLabel( "Fornavn" ) );
		custWindowFirstNamePnl.add( custWindowFirstName );
		custWindowLastNamePnl.add( new JLabel( "Etternavn" ) );
		custWindowLastNamePnl.add( custWindowLastName );
		custWindowPhonePnl.add( new JLabel( "Telefon" ) );
		custWindowPhonePnl.add( custWindowPhone );
		custWindowBornPnl.add( new JLabel( "Født") );
		custWindowBornPnl.add( custWindowBorn );
		custWindowBtnPnl.add( custWindowRegBtn );
		custWindowBtnPnl.add( custWindowSearchBtn );
*/

		formPnl.add( new JLabel( "Fornavn" ) );
		formPnl.add( custWindowFirstName );
		formPnl.add( new JLabel( "Etternavn" ) );
		formPnl.add( custWindowLastName );
		formPnl.add( new JLabel( "Telefon" ) );
		formPnl.add( custWindowPhone );
		formPnl.add( new JLabel( "Født" ) );
		formPnl.add( custWindowBorn );
		


		btnPnl.add(imageBtn);
		btnPnl.add(custWindowRegBtn );
		btnPnl.add(custWindowSearchBtn );
		btnPnl.add(imagePnl);


		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;

		add(formPnl, c);

		c.gridx=2;
		c.gridy=0;
		c.gridheight=2;

		add(rsltPnl, c);

		c.gridx=0;
		c.gridheight=1;
		c.gridy=1;
		add(btnPnl, c);
	}

	private Person registerPerson()
	{
		String firstname = custWindowFirstName.getText();
		String lastname = custWindowLastName.getText();
		
		try
		{
			String pattern = "\\d{8}";
			String snumber = custWindowPhone.getText();
			if(snumber.matches(pattern))
			{
				int number = Integer.parseInt(snumber);
				Date born = new SimpleDateFormat("ddMMyy").parse(custWindowBorn.getText());
				Person p = new Person( firstname, lastname, number, born, img );
			
				statusTxt.setText(custRegistry.input( p ));

				findPerson();

				blankOut();

				return p;
			}
			JOptionPane.showMessageDialog(null, "Du må ha 8 siffere");

		}
		catch( ParseException pe )
		{
			JOptionPane.showMessageDialog(null, "Fødselsdato må være på formen ddmmyy!");
		}
		catch( NumberFormatException nfe )
		{
			JOptionPane.showMessageDialog(null, "Telefonnummeret må kun bestå av siffer!");
		}


		return null;

	}

	private void findPerson()
	{
		String firstname = custWindowFirstName.getText();
		String lastname = custWindowLastName.getText();
		try	
		{
			int number = Integer.parseInt(custWindowPhone.getText());

			statusTxt.setText( custRegistry.findPerson(number).toString());
		}
		catch( NumberFormatException nfe )
		{

		}


		if(firstname != null)
		{
			String item = ""; 

			listmodel = custRegistry.findPerson( firstname, lastname );
	
/*			list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
			list.setCellRenderer( new SearchListCellRenderer());
			list.addListSelectionListener( listListener );
*/
			list.setModel( listmodel );


		}
//		if(firstname != null)
//			statusTxt.setText(custRegistry.findPerson(firstname, lastname));
		blankOut();

	}

	public void updateCust()
	{
		String fName = custWindowFirstName.getText();
		String lName = custWindowLastName.getText();
		try
		{
			int tlfNr = Integer.parseInt(custWindowPhone.getText());
			Date born = new SimpleDateFormat("ddMMyy").parse(custWindowBorn.getText());
			if(img!= null)
			{
				moveAndRenameImg(img, Salesclerk.customer);
			}
			Salesclerk.customer.setFirstName(fName);
			Salesclerk.customer.setLastName(lName);
			Salesclerk.customer.setphoneNr(tlfNr);
			Salesclerk.customer.setBirth(born);

			Salesclerk.salesClerkSearchInfoTxt.setText( "Har oppdatert:\n"+ Salesclerk.customer.getCustId() + "\n" + Salesclerk.customer.toString() );

		}
		catch(NullPointerException npe)
		{

		}
		catch(ParseException pe)
		{

		}

		
	}

	private File imageUpload()
	{
		try
		{
			JFileChooser fc = new JFileChooser();

			FileNameExtensionFilter filter = new FileNameExtensionFilter(
        	"Image files", "jpg", "gif", "png");
    		fc.setFileFilter(filter);
			int returnValue = fc.showOpenDialog(formPnl);


			if( returnValue == JFileChooser.APPROVE_OPTION )
			{
				img = fc.getSelectedFile(); 
			}

			System.out.println("Du har valgt å åpne filen: " + img.getName() );


       		return img;
   		}
		catch(NumberFormatException nfe)
		{

		}
		


		return null;

//		JFrame fr = new JFrame ("Skønner ikke !");
//		FileDialog fd = new FileDialog(fr,"Åpne", FileDialog.LOAD);
//		FileDialog fdsave = new FileDialog(fr,"Lagre", FileDialog.SAVE);

//		fd.setVisible(true);

//		if(fd.getFile() == null)
//		{
//			statusTxt.setText("Du har ikke valgt noe bilde");
//		}
//		else
//		{
			
//			String d = (fd.getDirectory() + fd.getFile());
//			Toolkit toolkit = Toolkit.getDefaultToolkit();
//			img = toolkit.getImage(d);
//			statusTxt.setText("Bilde er lagret");
//			fdsave.setVisible(true);
			
//		}
	}

	/*public void paint (Graphics g)
	{
		if (img != null)
		{
			g.drawImage(img, 100, int, custListener);

		}
	}*/
	private void moveAndRenameImg(File f, Person p)
	{
		
		iu.saveImage(f,p);
	}

	public static void blankOut()
	{
		custWindowFirstName.setText("");
		custWindowLastName.setText("");
		custWindowPhone.setText("");
		custWindowBorn.setText("");
	}


	public static void clearSearch()
	{
		list.setModel(new DefaultListModel<Person>()); 

	}






	private class ListListener implements ListSelectionListener
	{
		public void valueChanged( ListSelectionEvent lse )
		{
			try
			{
				Salesclerk.customer = listmodel.get(list.getSelectedIndex());
				SalesWindowPanel.salesWindowCustIDtf.setText( "" + Salesclerk.customer.getCustId() );
				Salesclerk.salesClerkSearchInfoTxt.setText( Salesclerk.customer.getCustId() + "\n" + Salesclerk.customer.toString() );
				SalesWindowPanel.cardIDList.setModel( Salesclerk.customer.listCards() );
				ShoppingCart.setCardList( Salesclerk.customer.listCards() );
				ReplaceWindowPanel.replaceWindowCustIdtf.setText(Salesclerk.customer.getCustId() + "");
				ReplaceWindowPanel.cardIDList.setModel( Salesclerk.customer.listCards() );
				Salesclerk.salesClerkSearchInfoTxt.setBackground(Color.LIGHT_GRAY);
				SalesWindowPanel.cardnrf.setEditable( false );


				custWindowFirstName.setText(Salesclerk.customer.getFirstName());
				custWindowLastName.setText(Salesclerk.customer.getLastName());
				custWindowPhone.setText(""+Salesclerk.customer.getphoneNr());
				//Calendar cal = Calendar.getInstance(); 
				//cal.setTime(Salesclerk.customer.getBirth());
				//String bdate = (cal.get(Calendar.DAY_OF_MONTH) + "" + (cal.get(Calendar.MONTH) + 1) + "" + cal.get(Calendar.YEAR));
				//custWindowBorn.setText(bdate);

				Date bDate = Salesclerk.customer.getBirth();
 				String born = new SimpleDateFormat("ddMMyy").format(bDate);
 				custWindowBorn.setText(born);
				
			}
			catch( ArrayIndexOutOfBoundsException aioobe )
			{
				// when making a new search, index will be out of bound. We use this exception 
				// to clear the text field.

				Salesclerk.salesClerkSearchInfoTxt.setText( "" );
			}
			

		}
	}

	private class CustListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			if( e.getSource() == custWindowRegBtn )
			{
				if(Salesclerk.customer != null)
				{
					updateCust();	
					
				}
				Person p = registerPerson();
				if(img!= null)
				{
					moveAndRenameImg(img,  p);
				}
			}
			if( e.getSource() == custWindowSearchBtn )
				findPerson();
			if( e.getSource() == imageBtn)
				imageUpload();

		}
	}

}