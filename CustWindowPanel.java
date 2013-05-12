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
	private Toolkit toolbox;
	private Personlist custRegistry;
	private File img; 
	private ImageUtility iu;
	private SimpleDateFormat dateFormatter;
	


	public CustWindowPanel( Personlist cr, JTextArea s )
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


		statusTxt = s;

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
					statusTxt.setText( "Denne personen finnes allerede, finn personen i søkeresultatet.");
					findPerson(); 
					return null;
				}
				Person p = new Person( firstname, lastname, number, born, img );
			
				statusTxt.setText(custRegistry.input( p ));

				findPerson();

				blankOut();

				return p;
			}
			throw new NumberFormatException(); 

		}
		catch( ParseException pe )
		{
			statusTxt.setText( "Fødselsdato må være på formen ddmmyy" );
		}
		catch( NumberFormatException nfe )
		{
			//JOptionPane.showMessageDialog(null, "Telefonnummeret må kun bestå av siffer!");
			statusTxt.setText( "Telefonnummeret må bestå av 8 siffer" );
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

			listmodel = custRegistry.findPerson( number );
			list.setModel( listmodel );

			return;

			
		}
		catch( NumberFormatException nfe )
		{
			System.out.println("The phonenr has to be digits");
		}
		catch(NullPointerException npe)
		{
			statusTxt.setText("Må være 8 siffer i tlfnr");
		}


		if(firstname != null)
		{

			listmodel = custRegistry.findPerson( firstname, lastname );
			list.setModel( listmodel );


		}
		blankOut();

	}

	private void updateCust()
	{
		String fName = custWindowFirstName.getText();
		String lName = custWindowLastName.getText();
		try
		{
			int tlfNr = Integer.parseInt(custWindowPhone.getText());
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
			statusTxt.setText("Kundeinfo ble oppdatert.");

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

	private void imageUpload()
	{
		if( Salesclerk.customer == null )
		{
			statusTxt.setText("Du må opprette / velge en person før du velger bilde");
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
				statusTxt.setText("Du har valgt å åpne bildet: " + img.getName() );
			}



   		}
		catch(NumberFormatException nfe)
		{
			System.out.println("Wrong format");
		}
		catch(NullPointerException nfe)
		{
			statusTxt.setText("Ble ikke valgt et bilde");
			nfe.printStackTrace( System.out );
		}
		
		img = null;


	}

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
	}

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
	}

}