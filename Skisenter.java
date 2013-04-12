import java.io.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;

public class Skisenter
{
	private static String message = null; 
	private static Personlist registry; 
	public static void main(String[] args)
	{
		

		Skisenter test = new Skisenter(); 

		test.readFile(); 
		EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				final Salesclerk s = new Salesclerk(registry, message); 
				s.setVisible( true );
				s.addWindowListener( new WindowAdapter() 
				{
					public void windowClosing( WindowEvent e )
					{
						saveFile();
						System.exit( 0 );
					}
				});

				final Drittvindu  vindu = new Drittvindu(registry);


/*
				final Control control = new Control();
    			control.setVisible(true);
    			control.setLocation(0, 500);
*/
				vindu.setLocation(700,0);    			


				final Admin a = new Admin(registry);
				a.setLocation(0,425);    

				Lift chLift = new Chairlift("Superheisen", 2000, 3 );

				final Control lift1 = new Control( registry,  chLift );
				lift1.setVisible(true);
				lift1.setLocation(700,425);

				Lift tLift = new Tcuplift( "Rævvaheisen", 1433 );

				final Control lift2 = new Control( registry, tLift );   
				lift2.setVisible(true);
				lift2.setLocation(700,425);

			}			

		} );
	}

	private static void readFile()
	{
		try( ObjectInputStream input = new ObjectInputStream(
			new FileInputStream( "data.dta" ) ) )
		{
			registry = (Personlist) input.readObject();
			Person.setNext( input.readInt() );
			Card.setNext( input.readInt() );
		}
		
		catch( ClassNotFoundException cnfe )
		{
			message = "Ingen personliste funnet, oppretter ny";
			registry = new Personlist();
		}
		catch( FileNotFoundException fnfe )
		{
			message = "Finner ikke datafilen, oppretter ny datafil";
			registry = new Personlist();
		}
		catch( IOException ioe )
		{
			message = "Feil med lesing fra fil, oppretter ny datafil";
			registry = new Personlist();
		}
	}

	private static void saveFile()
	{
		try( ObjectOutputStream output = new ObjectOutputStream(
			new FileOutputStream( "data.dta" ) ) )
		{
			output.writeObject( registry );
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