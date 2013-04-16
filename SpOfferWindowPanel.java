import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;

public class SpOfferWindowPanel extends JPanel
{
	private JLabel offer1;



	public SpOfferWindowPanel()
	{
	

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		setBackground(new Color(220, 240, 255));


		offer1 = new JLabel("TILBUJD");

	    offer1.setFont(new Font("Calibri", Font.PLAIN, 14));

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.PAGE_START;
		add(offer1, c);
	}
}
