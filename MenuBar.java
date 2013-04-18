import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.*;
import java.awt.event.*;

public class MenuBar
{
	private JMenuBar menuBar;
	private JMenu file; 
	private JMenu edit; 
	private JMenu doSomethingFunny;	
	private MenuListener menuListener;

	private JMenuItem save;
	private JMenuItem exit;

	public MenuBar()
	{
		menuBar = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		doSomethingFunny = new JMenu("doSomethingFunny");

		menuListener = new MenuListener();

	}

	public JMenuBar createMenu()
	{
		makeFile();
		menuBar.add( file );
		menuBar.add( edit );
		menuBar.add( doSomethingFunny );

		return menuBar;
	}

	private void makeFile()
	{
		save = new JMenuItem("Lagre");
		exit = new JMenuItem("Exit");

		save.addActionListener( menuListener );

		file.add( save );
		file.addSeparator();
		file.add( exit );
	}

	private class MenuListener implements ActionListener
	{
		public void actionPerformed( ActionEvent ae )
		{
			if( ae.getSource() == save )
				Skisenter.saveFile(); 
			if( ae.getSource() == exit )
			{
				if( Skisenter.checkForUnsaved() )
					System.exit( 0 );
			}
		}
	}
}