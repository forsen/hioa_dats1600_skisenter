import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.KeyStroke;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.*;
import java.io.IOException;
import java.net.URI;

/**
  * This is a class to create the menu bar we're using in our program.
  */

public class MenuBar
{
	private JMenuBar menuBar;
	private JMenu file; 
	private JMenu windows;	
	private JMenu help; 
	private MenuListener menuListener;

	private JTextArea aboutText;

	private JMenuItem save;
	private JMenuItem exit;
	private JMenuItem lifts;
	private JMenuItem admin; 
	private JMenuItem info;
	private JMenuItem javadoc;
	private JMenuItem userdoc;
	private JMenuItem about;
	private Container c; 

/**
  * The constructor creates the menu's
  */
	public MenuBar()
	{
		menuBar = new JMenuBar();
		file = new JMenu("File");
		windows = new JMenu("Windows");
		help = new JMenu("Help");

		menuListener = new MenuListener();

		c = (Container) menuBar.getTopLevelAncestor();

		aboutText = new JTextArea();

	}


	public JMenuBar createMenu()
	{
		makeFile();
		makeWindows();
		makeHelp();
		menuBar.add( file );
		menuBar.add( windows );
		menuBar.add( help );
		return menuBar;
	}

/**
  * This method sets up the "File" menu
  */
	private void makeFile()
	{
		save = new JMenuItem("Lagre");
		save.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() ) );
		exit = new JMenuItem("Lukk vindu");
		exit.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() ) );
		exit.addActionListener( menuListener );
		save.addActionListener( menuListener );

		file.add( save );
		file.addSeparator();
		file.add( exit );
	}
/**
  * This method sets up the "Windows" menu
  */
	private void makeWindows()
	{
		lifts = new JMenuItem("Heiskontroll");
		lifts.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() ) );
		info = new JMenuItem("Info");
		info.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_I, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() ) );
		admin = new JMenuItem("Admin");
		admin.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_M, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() ) );

		lifts.addActionListener( menuListener );
		info.addActionListener( menuListener );
		admin.addActionListener( menuListener );

		windows.add( lifts );
		windows.add( info );
		windows.add( admin );

	}

/**
  * This method sets up the "Help" menu
  */
	private void makeHelp()
	{
		about = new JMenuItem("Om");
		javadoc = new JMenuItem("JavaDoc");
		userdoc = new JMenuItem("Brukerdokumentasjon");

		about.addActionListener( menuListener );
		javadoc.addActionListener( menuListener );
		userdoc.addActionListener( menuListener );

		help.add( about );
		help.addSeparator();
		help.add( userdoc );
		help.add( javadoc ); 

	}

/** 
  * A method to take an incoming String url, and open your 
  * system's default browser. 
  * @param url 	The url to open
  */
	private void openURL( String url )
	{
		if( Desktop.isDesktopSupported() )
		{
			URI uri = URI.create( url );
			try
			{
				Desktop.getDesktop().browse( uri );
			}
			catch( IOException ioe )
			{
				ioe.printStackTrace( System.out );
			}
		}
	}

/**
  * This method will open a message dialog, with some 
  * information about this project and its developers.
  */
	private void printAbout()
	{
		aboutText.setText("Dette er et flott program\n");
		aboutText.append("Skrevet av en flott gjeng ungdom\n");
		aboutText.append("Vi har hatt det veldig gøy da vi skrev dette\n");
		aboutText.append("Vi fortjener en A!");

		JOptionPane.showMessageDialog( null, aboutText );
	}

	private class MenuListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			if( ae.getSource() == save )
				Skiresort.saveFile(); 
			if( ae.getSource() == exit )
			{
				JMenuItem menuItem = (JMenuItem) ae.getSource();
				JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();
				Component invoker = popupMenu.getInvoker();
				JComponent invokerAsJComponent = (JComponent) invoker;
				Container topLevel = invokerAsJComponent.getTopLevelAncestor();
				if( topLevel instanceof Salesclerk )
				{
					if( Skiresort.checkForUnsaved() )
						System.exit( 0 );
				}

				topLevel.setVisible(false );

			}
			if( ae.getSource() == lifts )
			{
				Skiresort.lift1.setVisible(true);
				Skiresort.lift2.setVisible(true);
			}
			if( ae.getSource() == info )
				Skiresort.i.setVisible( true );
			if( ae.getSource() == admin )
				Skiresort.a.setVisible( true );
			if( ae.getSource() == javadoc )
				openURL( "http://dev.forsen.no/skisenter/");
			if( ae.getSource() == about )
				printAbout();
		}
	}
}
