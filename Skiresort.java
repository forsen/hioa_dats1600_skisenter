import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Objects;  

/**
 * Skiresert is the class with the main-methode. 
 * Skiresort creates the Personlist, Cardlist and List of Validations.
 * 
 * @author Erik Haider Forsén
 * @author Ole Hansen
 * @author Julie Hill Roa
 * @version 0.9
 */

public class Skiresort
{
	private static String message = null; 
	private static Personlist registry; 
	private static Cardlist cardregistry;
	private static List<Validations> validations;
	private static RandomData r;

	public static Salesclerk s;
	public static Info i;
	public static Admin a;
	public static Control lift1;
	public static Control lift2; 

	public static Lift chLift;
	public static Lift tLift; 

	public static boolean unsaved; 

/**
 * main-methode tu run the program
 *Creates the Contol-Windows, Salesclerk-Window, Admin-Window and the Info-Window. 
 */

	public static void main(String[] args)
	{
		
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		Toolkit verktoykasse = Toolkit.getDefaultToolkit();
  		String bildefil = "img/offpist_logo.png";
  		final Image ikon = verktoykasse.getImage(bildefil);
		
		unsaved = false; 
		Skiresort test = new Skiresort(); 
		test.readFile(); 
		
		chLift = new Chairlift(validations, registry, "Eventyrheisen", 2000, 3 );
		tLift = new Tcuplift( validations, registry, "Lårgnagern", 1433 );
		

		EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
			
				int yn = JOptionPane.showOptionDialog(null, "Vil du få litt tilfeldig data?", "Random stash", JOptionPane.YES_NO_OPTION,
														JOptionPane.QUESTION_MESSAGE, null, null, null );

				if( yn == JOptionPane.YES_OPTION )
					r = new RandomData( registry, cardregistry, validations );

				s = new Salesclerk(registry, cardregistry, message); 
				s.setJMenuBar( new MenuBar().createMenu() );
				s.setVisible( true );
				s.setIconImage(ikon);
				s.setDefaultCloseOperation( WindowConstants.DO_NOTHING_ON_CLOSE );
				s.addWindowListener( new WindowAdapter() 
				{
					public void windowClosing( WindowEvent e )
					{
						if( checkForUnsaved())  
							System.exit( 0 );
					}
				});

			
				i = new Info();
				i.setJMenuBar( new MenuBar().createMenu() );
				i.setVisible(false);
  	

				a = new Admin( registry,validations, cardregistry);
				a.setLocation(0,425);
				a.setJMenuBar( new MenuBar().createMenu() );
				a.setIconImage(ikon);    
				a.setVisible( false );

				
				lift1 = new Control( registry,  chLift, cardregistry );
				lift1.setJMenuBar( new MenuBar().createMenu() );
				lift1.setLocation(700,625);
				lift1.setIconImage(ikon);
				lift1.setVisible(false);


				lift2 = new Control( registry, tLift, cardregistry );   
				lift2.setJMenuBar( new MenuBar().createMenu() );
				lift2.setLocation(200,625);
				lift2.setIconImage(ikon);
				lift2.setVisible(false);
			}			

		} );
	}
	/**
	 *This methode reads the objects from file and puts them in Personlist, Cardlist and a list of Validations. 
	 */

	private static void readFile()
	{
		try( ObjectInputStream input = new ObjectInputStream(
			new FileInputStream( "data.dta" ) ) )
		{
			registry = (Personlist) input.readObject();
			validations = (List<Validations>) input.readObject();
			cardregistry = (Cardlist) input.readObject();
			Person.setNext( input.readInt() );
			Card.setNext( input.readInt() );
		}
		
		catch( ClassNotFoundException cnfe )
		{
			message = "Ingen personliste funnet, oppretter ny";
			registry = new Personlist();
			validations = new LinkedList<>();
			cardregistry = new Cardlist();
		}
		catch( FileNotFoundException fnfe )
		{
			message = "Finner ikke datafilen, oppretter ny datafil";
			registry = new Personlist();
			validations = new LinkedList<>();
			cardregistry = new Cardlist();
		}
		catch( IOException ioe )
		{
			message = "Feil med lesing fra fil, oppretter ny datafil";
			registry = new Personlist();
			validations = new LinkedList<>();
			cardregistry = new Cardlist();
		}
	}

	/**
	 *This methode saves the objects from Personlist, Cardlist and a list of Validations onto a file 
	 */

	public static void saveFile()
	{
		try( ObjectOutputStream output = new ObjectOutputStream(
			new FileOutputStream( "data.dta" ) ) )
		{
			output.writeObject( registry );
			output.writeObject( validations );
			output.writeObject(cardregistry);
			output.writeInt( Person.readNext() );
			output.writeInt( Card.readNext() );
			
			s.statusTxt.setText( "alt ble lagret" );
			unsaved = false; 

		}
		catch( NotSerializableException nse )
		{
			JOptionPane.showMessageDialog(null, "Objektet er ikke serialisert", "Feil!", JOptionPane.ERROR_MESSAGE );
			nse.printStackTrace( System.out );
		}
		catch( IOException ioe )
		{
			JOptionPane.showMessageDialog(null, "Feil ved skriving til fil", "Feil!", JOptionPane.ERROR_MESSAGE );
		}
	}

	/**
	 * Checks if you have unsavedobjects when you exit the program. 
	 * if you do this methode displays a dialogbox asking if you want to save or not.
	 * @return Returns true if the user didn't have anything unsaved, pressed option YES and NO. False if they chose CANCEL
	 */

	public static boolean checkForUnsaved()
	{
		if( !unsaved )
			return true;	

		String[] options = {"Lagre", "Ikke lagre", "Avbryt"};

		int n = JOptionPane.showOptionDialog(null, "Du har ulagrete endringer.", "Lagringsdialog", JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, options, options[0] );


		switch( n )
		{
			case JOptionPane.YES_OPTION:
				saveFile();
				return true;
			case JOptionPane.NO_OPTION:
				return true; 
			case JOptionPane.CANCEL_OPTION:
				return false; 
		}

		return false; 
	}

}