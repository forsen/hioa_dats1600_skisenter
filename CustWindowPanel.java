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

/**
  * This class sets up the customer panel, which is placed on Salesclerk JFrame. This 
  * is where you create new customers, and search for existing customers.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

public class CustWindowPanel extends JPanel
{	
	private JButton imageBtn,custWindowSearchBtn, custWindowRegBtn;
	private JPanel imagePnl,custWindowSearchInfoPnl, custWindowFirstNamePnl, custWindowLastNamePnl, custWindowPhonePnl, custWindowBornPnl, custWindowBtnPnl;
	private static JTextField custWindowFirstName, custWindowLastName, custWindowPhone, custWindowBorn;
	private JTextArea custWindowSearchInfoTxt;
	private JPanel formPnl, btnPnl, rsltPnl; 
	private CustListener custListener;
	private static JList<Person> list; 
	private DefaultListModel<Person> listmodel;
	private ListListener listListener;
	private JScrollPane scrolList;
	private Toolkit toolbox;
	private Personlist custRegistry;
	private File img; 
	private ImageUtility iu;
	private SimpleDateFormat dateFormatter;
	
/**
  * This constructor will set up all the elements on this panel. 
  * @param cr 	The person registry to operate on
  * @see Salesclerk
  * @see Personlist
  */

	public CustWindowPanel( Personlist cr )
	{
		setBackground(new Color(200, 230, 255));
		setLayout( new GridBagLayout() );

		dateFormatter = new SimpleDateFormat("ddMMyy");
		dateFormatter.setLenient( false );

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



		listListener = new ListListener(); 

		list.addListSelectionListener( listListener );

		toolbox = Toolkit.getDefaultToolkit();

		custRegistry = cr; 

		//Will not use "try-catch" here, because the buttons will appear fine, even 
		//though the pictures cannot be found.
		ImageIcon camera = new ImageIcon("img/camera.png");
		ImageIcon search = new ImageIcon("img/search.png");
		ImageIcon customer = new ImageIcon("img/customer.png");

		imageBtn = new JButton("Velg bilde", camera);
		imageBtn.setToolTipText("Last opp bilde til kortet");
		custWindowSearchBtn = new JButton("Søk", search);
		custWindowSearchBtn.setToolTipText("Søk på kunde");
		custWindowRegBtn = new JButton("Endre/Ny kunde", customer);
		custWindowRegBtn.setToolTipText("Registrer ny kunde");

		custWindowFirstName = new JTextField(10);
		custWindowLastName = new JTextField(10);
		custWindowPhone = new JTextField(10);
		custWindowBorn = new JTextField(10);

		custListener = new CustListener();

		imageBtn.addActionListener( custListener );
		custWindowRegBtn.addActionListener( custListener );
		custWindowSearchBtn.addActionListener( custListener );

		rsltPnl.add( scrolList );

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
		c.weightx=1;
		c.weighty=1;
		add(btnPnl, c);
	}

/**
  * this method will try to register a new customer based on input from the various 
  * text fields in this class. It will make sure the customer does not already exist,
  * but it will accept same phone number on two different customers.
  * @return Returns the newly created customer
  */
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
				Date born = dateFormatter.parse(custWindowBorn.getText());
				if( custRegistry.findPerson( number, firstname, lastname) != null )
				{
					Salesclerk.statusTxt.setText( "Denne personen finnes allerede, finn personen i søkeresultatet.");
					findPerson(); 
					return null;
				}
				Person p = new Person( firstname, lastname, number, born, img );
			
				Salesclerk.statusTxt.setText(custRegistry.input( p ));

				findPerson();

				blankOut();

				return p;
			}
			throw new NumberFormatException(); 

		}
		catch( ParseException pe )
		{
			Salesclerk.statusTxt.setText( "Fødselsdato må være på formen ddmmyy" );
		}
		catch( NumberFormatException nfe )
		{
			Salesclerk.statusTxt.setText( "Telefonnummeret må bestå av 8 siffer" );
		}


		return null;

	}

/**
  * this method will search for a customer in our registry based on input from the 
  * text fields. It will place the result in a listmodel, which is displayed in the window.
  */
	private void findPerson()
	{
		String firstname = custWindowFirstName.getText();
		String lastname = custWindowLastName.getText();
		try	
		{
			int number = Integer.parseInt(custWindowPhone.getText());

			listmodel = custRegistry.findPerson( number );
			list.setModel( listmodel );

			if(listmodel.getSize() == 0)
			Salesclerk.statusTxt.setText("Søket gav ingen treff");

			return;

			
		}
		catch( NumberFormatException nfe )
		{
			if( !custWindowPhone.getText().isEmpty() )
				Salesclerk.statusTxt.setText("Telefonnummeret kan kun bestå av tall");
		}
		catch(NullPointerException npe)
		{
			Salesclerk.statusTxt.setText("Må være 8 siffer i tlfnr");
		}


		if(firstname != null)
		{

			listmodel = custRegistry.findPerson( firstname, lastname );
			list.setModel( listmodel );

			if(listmodel.getSize() == 0)
			Salesclerk.statusTxt.setText("Søket gav ingen treff");

		}
		blankOut();

	}

/**
  * this method will update a customer. If a customer is chosen, it will update the existing 
  * customer based on input from the text fields, instead of creating a new customer. 
  */
	private void updateCust()
	{
		String fName = custWindowFirstName.getText();
		String lName = custWindowLastName.getText();
		try
		{
			String pattern = "\\d{8}";
			String number = custWindowPhone.getText();
			if(number.matches(pattern))
			{
				int tlfNr = Integer.parseInt(number);
				Date born = dateFormatter.parse(custWindowBorn.getText());
				if(img!= null)
				{
					moveAndRenameImg(img, Salesclerk.customer);
				}
				Salesclerk.customer.setFirstName(fName);
				Salesclerk.customer.setLastName(lName);
				Salesclerk.customer.setphoneNr(tlfNr);
				Salesclerk.customer.setBirth(born);

				Salesclerk.salesClerkSearchInfoTxt.setText( "Har oppdatert:\n"+ Salesclerk.customer.getCustId() + "\n" + Salesclerk.customer.toString() );
				Salesclerk.statusTxt.setText("Kundeinfo ble oppdatert.");
			}Salesclerk.statusTxt.setText("Tlf nummer må bestå av 8 siffre.");

		}
		catch(NullPointerException npe)
		{
			System.out.println("custWindowPhone, and custWindowBorn needs to be filled");
		}
		catch(ParseException pe)
		{
			System.out.println("Couldn't convert the number into date format");
		}

		
	}

/**
  * this method will open a JFileChooser dialog when called. The file chosen will be assigned
  * to the File object img. 
  * @see CustWindowPanel#img
  */
	private void imageUpload()
	{
		if( Salesclerk.customer == null )
		{
			Salesclerk.statusTxt.setText("Du må opprette / velge en person før du velger bilde");
			return; 
		}
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
				moveAndRenameImg(img,  Salesclerk.customer);
				Salesclerk.statusTxt.setText("Bildet " + img.getName() + " er nå knyttet opp mot kunde nr: " + Salesclerk.customer.getCustId() );
			}




   		}
		catch(NumberFormatException nfe)
		{
			System.out.println("Wrong format");
		}
		catch(NullPointerException nfe)
		{

			Salesclerk.statusTxt.setText("Ble ikke valgt et bilde");
			nfe.printStackTrace( System.out );

		}
		
		img = null;


	}

/**
  * This method will take the current image file and Person object, and call
  * another method that will redraw the image, place it in the proper location
  * and assign it to the Person object.
  * @param p 	The person to assign the image to
  * @param f 	The file-object describing the image file
  * @see ImageUtility#saveImage( File f, Person p )
  */
	private void moveAndRenameImg(File f, Person p)
	{
		
		iu.saveImage(f,p);
	}

/**
  * This is a simple method to clear the textfields.
  */
	public static void blankOut()
	{
		custWindowFirstName.setText("");
		custWindowLastName.setText("");
		custWindowPhone.setText("");
		custWindowBorn.setText("");
	}

/**
  * This method will clear the list of search results, replacing the current
  * DefaultListModel with an empty DefaultListModel.
  */
	public static void clearSearch()
	{
		list.setModel(new DefaultListModel<Person>()); 

	}

/**
  * This class will listen for changes in the list. 
  */
	private class ListListener implements ListSelectionListener
	{
		/**
		  * This method will be called when a new selection is made in the list. 
		  * It will do a numerous necessary operations, to reflect that we're 
		  * handling a Person object.
		  */
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

				Date bDate = Salesclerk.customer.getBirth();
 				String born = dateFormatter.format(bDate);
 				custWindowBorn.setText(born);
				
			}
			catch( ArrayIndexOutOfBoundsException aioobe )
			{
				// when making a new search, index will be out of bound. We use this exception 
				// to clear the text field.

				Salesclerk.salesClerkSearchInfoTxt.setText( "" );
			}
			

		}
	} // end of class ListListener

/**
  * This is the button listen class for the buttons in this panel. 
  */
	private class CustListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			if( e.getSource() == custWindowRegBtn )
			{
				if(Salesclerk.customer != null)
					updateCust();	

				else
				{
					Person p = registerPerson();
				}
			}
			if( e.getSource() == custWindowSearchBtn )
				findPerson();
			if( e.getSource() == imageBtn)
				imageUpload();

		}
	}// end of class CustListener

}// end of class CustWindowPanel