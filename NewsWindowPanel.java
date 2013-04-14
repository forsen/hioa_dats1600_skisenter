import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;

public class NewsWindowPanel extends JPanel
{
	private JLabel news1, news2, news3, news4;



	public NewsWindowPanel()
	{
	

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		setBackground(new Color(220, 240, 255));


		news1 = new JLabel("<html><body><br><br><b> Sesongåpning 20. desember 2013!</b> <br>" +
	    "Vi åpner skianlegget 20. desember i år. Kom for gratis kaffe og vafler.<br>" +
	    "________________________________________________________________________________________________________ <br><br>" +
	    "<br><br><b> Sesongåpning 20. desember 2013!</b> <br>" +
	    "Vi åpner skianlegget 20. desember i år. Kom for gratis kaffe og vafler.<br>" +
	    "___________________________________________________________________________________________  <br><br>" +
	    " </body></head>");

	    news1.setFont(new Font("Calibri", Font.PLAIN, 14));

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.PAGE_START;
		add(news1, c);
	}


}