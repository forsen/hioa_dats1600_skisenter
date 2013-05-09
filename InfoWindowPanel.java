import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;
import javax.imageio.*;

public class InfoWindowPanel extends JPanel
{
	private JLabel headerImg1, headerImg2;
	private JLabel header;
	private JLabel content;



	public InfoWindowPanel()
	{
	

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		setBackground(new Color(220, 240, 255));

		ImageIcon snowboarder = new ImageIcon("img/snowboarder.png");
		ImageIcon skimap = new ImageIcon("img/skimap.png");





//****** NYTT ELEMENT *************************************//
		header = new JLabel("Skitrekket");
		header.setFont(new Font("Calibri", Font.BOLD, 20));

		c.gridx =0;
		c.gridy =0;

		add(header, c);

		c.gridy=1;
		headerImg2 = new JLabel(skimap);
		add(headerImg2, c);

		content = new JLabel("<html>Skitrekket består av to heiser, en stolheis og en krokheis. Vi har alt fra grønne til svarte løyper, <br>" +
			"altså løyper for både nybegynnere og viderekomne.<br><br><br></html>");
		content.setFont(new Font("Calibri", Font.PLAIN, 14));
		content.setHorizontalAlignment(JLabel.CENTER);

		c.gridy=2;
		add(content, c);


//****** NYTT ELEMENT *************************************//

		headerImg1 = new JLabel(snowboarder);
		
		c.gridy=3;
		add(headerImg1, c);


		header = new JLabel("Åpningstider");
		header.setFont(new Font("Calibri", Font.BOLD, 20));
		c.gridx = 0;
		c.gridy = 4;
		add(header,c);

		content = new JLabel("<html>Mandag: 10:00-16:30<br>"
							+"Tirsdag: 10:00-17:00<br>"
							+"Onsdag: 9:30-17:00<br>"
							+"Torsdag: 10:00-17:00<br>"
							+"Fredag: 9:00-17:30<br>"
							+"Lørdag: 9:00-18:00<br>"
							+"Søndag: 9:00-18:00</html>");
	    content.setFont(new Font("Calibri", Font.PLAIN, 14));
	    content.setHorizontalAlignment(JLabel.CENTER);

		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.PAGE_START;
		add(content, c);
	}
}

