import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;

public class InfoWindowPanel extends JPanel
{
	private JLabel info1;



	public InfoWindowPanel()
	{
	

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		setBackground(new Color(220, 240, 255));


		info1 = new JLabel("Kuuuuuuuuuuuult");

	    info1.setFont(new Font("Calibri", Font.PLAIN, 14));

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.PAGE_START;
		add(info1, c);
	}
}

