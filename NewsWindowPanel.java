import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;

public class NewsWindowPanel extends JPanel
{
	private JLabel header, news1, news2, news3, news4;



	public NewsWindowPanel()
	{
	

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		setBackground(new Color(220, 240, 255));

		header = new JLabel("Sesongåpning 20. desember 2013!");
		header.setFont(new Font("Calibri", Font.BOLD, 20));

		news1 = new JLabel("<html>" +
	    "Vi åpner skianlegget 20. desember i år. Kom for gratis kaffe og vafler.<br>" +
	    "________________________________________________________________________________________________________ <br><br></head>");

	    news1.setFont(new Font("Calibri", Font.PLAIN, 14));

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		add(header, c);

		c.gridy= 1;
		add(news1,c);
	}


}