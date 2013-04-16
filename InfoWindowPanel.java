import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;
import javax.imageio.*;

public class InfoWindowPanel extends JPanel
{
	private JLabel headerImg;
	private JLabel header;
	private JLabel content;



	public InfoWindowPanel()
	{
	

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		setBackground(new Color(220, 240, 255));

		ImageIcon snowboarder = new ImageIcon("snowboarder.png");


		headerImg = new JLabel(snowboarder);
		add(headerImg, c);


		header = new JLabel("Åpningstider");
		header.setFont(new Font("Calibri", Font.BOLD, 20));
		c.gridx = 0;
		c.gridy = 1;
		add(header,c);

		content = new JLabel("<html>Mandag: 10:00-16:30<br>"
							+"Tirsdag: 10:00-17:00<br>"
							+"Onsdag: 9:30-17:00<br>"
							+"Torsdag: 10:00-17:00<br>"
							+"Fredag: 9:00-17:30<br>"
							+"Lørdag: 9:00-18:00<br>"
							+"Søndag: 9:00-18:00</html>");
	    content.setFont(new Font("Calibri", Font.PLAIN, 14));
	    content.setHorizontalAlignment(SwingConstants.CENTER);

		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.PAGE_START;
		add(content, c);
	}
}

