import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar
{
	private JMenuBar menuBar;
	private JMenu file; 
	private JMenu edit; 
	private JMenu doSomethingFunny;

	public MenuBar()
	{
		menuBar = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		doSomethingFunny = new JMenu("doSomethingFunny");

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
		JMenuItem save = new JMenuItem("Lagre");
		JMenuItem exit = new JMenuItem("Exit");

		file.add( save );
		file.addSeparator();
		file.add( exit );
	}
}