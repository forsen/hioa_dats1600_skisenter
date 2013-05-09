import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;

public class SpOfferWindowPanel extends JPanel
{
	private JLabel offerPic1, offerPic2, offerPic3, offer1, offer2, offer3;



	public SpOfferWindowPanel()
	{
	

		
		setBackground(new Color(220, 240, 255));

		ImageIcon ff = new ImageIcon("img/freakyfriday.png");
		ImageIcon gD = new ImageIcon("img/groupdiscount.png");
		ImageIcon sD = new ImageIcon("img/studentdiscount.png");
		offerPic1 = new JLabel(ff);
		offerPic2 = new JLabel(gD);
		offerPic3 = new JLabel(sD);

		offer1 = new JLabel("FREAKY FRIDAY YEEEEEAH");
		offer2 = new JLabel("Grupperabatt er lurt. ");
		offer3 = new JLabel("Studentrabatt er hendig.");

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();

	    offer1.setFont(new Font("Calibri", Font.PLAIN, 14));
	    offer2.setFont(new Font("Calibri", Font.PLAIN, 14));
	    offer3.setFont(new Font("Calibri", Font.PLAIN, 14));


	    c.insets = new Insets(10, 5, 5, 5);
	    c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;

		/* Første kolonne ****************/

		c.gridx=0;
		c.gridy=0;
		c.weightx=1;
		c.weighty=1;
		c.gridwidth = 2;
		add(offerPic1, c);

		c.gridy=1;
		add(offerPic2, c);

		c.gridy=2;
		add(offerPic3, c);

		/* Andre kolonne ****************/
		c.gridwidth=1;
		c.gridx=3;
		c.gridy=0;
		add(offer1, c);

		c.gridx = 3;
		c.gridy =1;
		add(offer2, c);

		c.gridy=2;
		add(offer3, c);
	}
}
