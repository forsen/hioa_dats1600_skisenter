import java.io.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.LinkedList;

public class Skisenter
{
	private static String message = null; 
	private static Personlist registry; 
	private static Cardlist cardregistry;
	private static List<Validations> validations;

	public static void main(String[] args)
	{
		Toolkit verktoykasse = Toolkit.getDefaultToolkit();
  		String bildefil = "img/offpist_logo.png";
  		final Image ikon = verktoykasse.getImage(bildefil);
		

		Skisenter test = new Skisenter(); 

		test.readFile(); 
		

		EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				final Salesclerk s = new Salesclerk(registry, cardregistry, message); 
				s.setVisible( true );
				s.setIconImage(ikon);
				s.addWindowListener( new WindowAdapter() 
				{
					public void windowClosing( WindowEvent e )
					{
						saveFile();
						System.exit( 0 );
					}
				});

				final Drittvindu  vindu = new Drittvindu( validations, registry, cardregistry);

				final Info i = new Info();
				i.setVisible(true);
  		


/*
				final Control control = new Control();
    			control.setLocation(0, 500);
*/				vindu.setIconImage(ikon);
				vindu.setLocation(700,0); 		


				final Admin a = new Admin( registry,validations, cardregistry);
				a.setLocation(0,425);
				a.setIconImage(ikon);    

				Lift chLift = new Chairlift(validations, registry, "Superheisen", 2000, 3 );
				final Control lift1 = new Control( registry,  chLift );
				lift1.setVisible(true);
				lift1.setLocation(700,425);
				lift1.setIconImage(ikon);


				Lift tLift = new Tcuplift( validations, registry, "Rævvaheisen", 1433 );

				final Control lift2 = new Control( registry, tLift );   
				lift2.setVisible(true);
				lift2.setLocation(700,425);
				lift2.setIconImage(ikon);

			}			

		} );
	}

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

	private static void saveFile()
	{
		try( ObjectOutputStream output = new ObjectOutputStream(
			new FileOutputStream( "data.dta" ) ) )
		{
			output.writeObject( registry );
			output.writeObject( validations );
			output.writeObject(cardregistry);
			output.writeInt( Person.readNext() );
			output.writeInt( Card.readNext() );
		}
		catch( NotSerializableException nse )
		{
			JOptionPane.showMessageDialog(null, "Objektet er ikke serialisert", "Feil!", JOptionPane.ERROR_MESSAGE );
		}
		catch( IOException ioe )
		{
			JOptionPane.showMessageDialog(null, "Feil ved skriving til fil", "Feil!", JOptionPane.ERROR_MESSAGE );
		}
	}

/*	<datafelter>

	<konstruktør som oppretter alle lister / vinduer>

	<usikkert hva annet som skal være av metoder her>

	<metode for å lese fra fil>
	<main metoden>

	<vinduslytter for å lagre til fil>*/

}