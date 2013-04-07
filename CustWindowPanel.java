import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.*;
import java.text.ParseException;

public class CustWindowPanel extends JPanel
{
	private JButton custWindowBtn, custWindowSearchBtn, custWindowRegBtn;
	private JPanel custWindowSearchInfoPnl, custWindowFirstNamePnl, custWindowLastNamePnl, custWindowPhonePnl, custWindowBornPnl, custWindowBtnPnl;
	private JTextField custWindowFirstName, custWindowLastName, custWindowPhone, custWindowBorn;
	private JTextArea custWindowSearchInfoTxt;
	private JTextArea statusTxt;

	private CustListener custListener;

	private JList list; 
	private DefaultListModel<Person> listmodel;
	private ListListener listListener;

	//private Person customer;

	private Toolkit toolbox;

	private Personlist custRegistry; 

	//public CustWindowPanel( Personlist cr, JTextArea s, Person p )
	public CustWindowPanel( Personlist cr, JTextArea s )
	{
		list = new JList(); 

		//customer = p; 

		statusTxt = s;

		listListener = new ListListener(); 
		toolbox = Toolkit.getDefaultToolkit();

		custRegistry = cr; 

		custWindowSearchInfoTxt = new JTextArea( 4,4 ); 

		custWindowSearchBtn = new JButton("Søk");
		custWindowRegBtn = new JButton("Ny kunde");

		custWindowFirstNamePnl = new JPanel( new FlowLayout() );
		custWindowLastNamePnl = new JPanel( new FlowLayout() );
		custWindowPhonePnl = new JPanel( new FlowLayout() );
		custWindowBornPnl = new JPanel( new FlowLayout() );
		custWindowBtnPnl = new JPanel( new FlowLayout() );

		custWindowFirstName = new JTextField(25);
		custWindowLastName = new JTextField(25);
		custWindowPhone = new JTextField(25);
		custWindowBorn = new JTextField(25);

		custListener = new CustListener();

		custWindowRegBtn.addActionListener( custListener );
		custWindowSearchBtn.addActionListener( custListener );

		add(custWindowFirstNamePnl);
		add(custWindowLastNamePnl);
		add(custWindowPhonePnl);
		add(custWindowBornPnl);
		add(custWindowBtnPnl);
		add(list);
		add(custWindowSearchInfoTxt);

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

	}

	private void registerPerson()
	{
		String firstname = custWindowFirstName.getText();
		String lastname = custWindowLastName.getText();
		
		try
		{
			int number = Integer.parseInt(custWindowPhone.getText());
			Date born = new SimpleDateFormat("ddMMyy").parse(custWindowBorn.getText());
			Person p = new Person( firstname, lastname, number, born );
			
		

			statusTxt.setText(custRegistry.input( p ));


		}
		catch( ParseException pe )
		{
			JOptionPane.showMessageDialog(null, "Fødselsdato må være på formen ddmmyy!");
		}
		catch( NumberFormatException nfe )
		{
			JOptionPane.showMessageDialog(null, "Telefonnummeret må kun bestå av siffer!");
		}


		

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
			list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
			list.setCellRenderer( new SearchListCellRenderer());
			list.addListSelectionListener( listListener );
			list.setModel( listmodel );


		}
//		if(firstname != null)
//			statusTxt.setText(custRegistry.findPerson(firstname, lastname));


	}







	private class ListListener implements ListSelectionListener
	{
		public void valueChanged( ListSelectionEvent lse )
		{
			try
			{
				Salesclerk.customer = listmodel.get(list.getSelectedIndex());
				SalesWindowPanel.salesWindowCustIDtf.setText( "" + Salesclerk.customer.getCustId() );
				custWindowSearchInfoTxt.setText( Salesclerk.customer.getCustId() + "\n" + Salesclerk.customer.toString() );
			}
			catch( ArrayIndexOutOfBoundsException aioobe )
			{
				// when making a new search, index will be out of bound. We use this exception 
				// to clear the text field.

				custWindowSearchInfoTxt.setText( "" );
			}

		}
	}

	private class CustListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			if( e.getSource() == custWindowRegBtn )
				registerPerson();
			if( e.getSource() == custWindowSearchBtn )
				findPerson();

		}
	}

}