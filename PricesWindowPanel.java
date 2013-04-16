import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;

public class PricesWindowPanel extends JPanel
{
	private JLabel price1;



	public PricesWindowPanel()
	{
	

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		setBackground(new Color(220, 240, 255));


		price1 = new JLabel("Pris: Dyrt");

	    price1.setFont(new Font("Calibri", Font.PLAIN, 14));

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.PAGE_START;
		add(price1, c);
	}
}