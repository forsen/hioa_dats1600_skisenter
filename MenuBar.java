import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.KeyStroke;

public class MenuBar
{
	private JMenuBar menuBar;
	private JMenu file; 
	private JMenu edit; 
	private JMenu windows;	
	private MenuListener menuListener;

	private JMenuItem save;
	private JMenuItem exit;
	private JMenuItem lifts;
	private JMenuItem admin; 
	private JMenuItem info;

	public MenuBar()
	{
		menuBar = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		windows = new JMenu("Windows");

		menuListener = new MenuListener();

	}

	public JMenuBar createMenu()
	{
		makeFile();
		makeWindows();
		menuBar.add( file );
		menuBar.add( edit );
		menuBar.add( windows );

		return menuBar;
	}

	private void makeFile()
	{
		save = new JMenuItem("Lagre");
		save.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() ) );
		exit = new JMenuItem("Exit");
		exit.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() ) );

		save.addActionListener( menuListener );

		file.add( save );
		file.addSeparator();
		file.add( exit );
	}

	private void makeWindows()
	{
		lifts = new JMenuItem("Heiskontroll");
		lifts.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK ) );
		info = new JMenuItem("Info");
		info.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_I, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() ) );
		admin = new JMenuItem("Admin");
		admin.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() ) );

		lifts.addActionListener( menuListener );
		info.addActionListener( menuListener );
		admin.addActionListener( menuListener );

		windows.add( lifts );
		windows.add( info );
		windows.add( admin );

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
			if( ae.getSource() == lifts )
			{
				Skisenter.lift1.setVisible(true);
				Skisenter.lift2.setVisible(true);
			}
			if( ae.getSource() == info )
				Skisenter.i.setVisible( true );
			if( ae.getSource() == admin )
				Skisenter.a.setVisible( true );
		}
	}
}