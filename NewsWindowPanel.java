package skisenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;
import java.awt.image.BufferedImage;
import javax.imageio.*;

/**
  * NewsWindowPanel is one of the four panels available for displaying in the Info-window.
  * This one displays news from the skiresort to the customers.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

public class NewsWindowPanel extends JPanel
{
// CONSTANTS
	private final int FONTSIZE = 14;
// END CONSTANTS

	private JLabel header, news1, news2, news3, news4, icon1, icon2, icon3, icon4;
	private BufferedImage icon4br, icon3br, icon2br, icon1br;


	/**
	  * The constructor creates all the elements and place them on the panel.
	  * This panel has no other intention than to display news.
	  */

	public NewsWindowPanel()
	{

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		setBackground(new Color(220, 240, 255));

		c.gridx = 0;
		c.gridy = 0;
		c.weighty=0.2;
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;


		/***** 1st ARTICLE ******************************************************/
		try{
			icon4br = ImageIO.read( getClass().getResource("img/npic4.png"));
			ImageIcon npic4 = new ImageIcon(icon4br);
			icon4 = new JLabel();
	    	icon4.setIcon(npic4);
	    	}
	    catch(Exception e)
	    {
	    	e.printStackTrace(System.out);
	    	JOptionPane.showMessageDialog(null, "En fil(npic4.png) mangler. Reinstaller programmet for bedre opplevelse.", "Feil: manglende fil", JOptionPane.INFORMATION_MESSAGE);
	    }

		add(icon4,c);

		news4 = new JLabel(
		"<html><i>Skrevet 01. mai 2013</i><br><br>" +
		"Takk for en knakendes fin sesong. Titt innom nærmere sesongstart for nyheter ang. sesongåpning 2013/2014!" +
		"Send oss gjerne ris <br> og ros. Vi håper å se deg her på Offpist Skiresort, neste sesong. <br><br><br><br></html>");


	    news4.setFont(new Font("Calibri", Font.PLAIN, FONTSIZE));

		c.gridy=1;
		add(news4,c);



		/***** 2nd ARTICLE ******************************************************/

		try{
			icon3br = ImageIO.read( getClass().getResource("img/npic3.png"));
			ImageIcon npic3 = new ImageIcon(icon3br);
			icon3 = new JLabel();
	    	icon3.setIcon(npic3);
	    	}
	    catch(Exception e)
	    {
	    	e.printStackTrace(System.out);
	    	JOptionPane.showMessageDialog(null, "En fil(npic3.png) mangler. Reinstaller programmet for bedre opplevelse.", "Feil: manglende fil", JOptionPane.INFORMATION_MESSAGE);
	    }


		c.gridy=2;
		add(icon3,c);

		news3 = new JLabel(
		"<html><i>Skrevet 20. mars 2013</i><br><br>" +
		"Vi åpner for mer kjøring i påsken. Mer kjøring, mer moro. Heisene vil være åpne for kveldskjøring hele påsken<br>" +
		" mellom klokka 17 og 21. <br><br><br><br></html>");


	    news3.setFont(new Font("Calibri", Font.PLAIN, FONTSIZE));

		c.gridy=3;
		add(news3,c);




		/***** 3rd ARTICLE ******************************************************/

		try{
			icon2br = ImageIO.read( getClass().getResource("img/npic2.png"));
			ImageIcon npic2 = new ImageIcon(icon2br);
			icon1 = new JLabel();
			icon1.setIcon(npic2);
	    	}
	    catch(Exception e)
	    {
	    	e.printStackTrace(System.out);
	    	JOptionPane.showMessageDialog(null, "En fil(npic2.png) mangler. Reinstaller programmet for bedre opplevelse.", "Feil: manglende fil", JOptionPane.INFORMATION_MESSAGE);

	    }


		c.gridy= 4;
		add(icon1,c);

		news1 = new JLabel(
		"<html><i>Skrevet 23. januar 2013</i><br><br>" +
		"Husk å melde på dine små troll og nisser til neste skiskole! Skiskolen starter 22. februar " +
	    "og varer til 26. februar. <br> Ring oss for påmelding og mer informasjon!<br><br><br><br></html>");    

	    news1.setFont(new Font("Calibri", Font.PLAIN, FONTSIZE));
		

		c.gridy= 5;
		add(news1,c);

		/***** 4th ARTICLE ******************************************************/

		try{
			icon1br = ImageIO.read( getClass().getResource("img/npic1.png"));
			ImageIcon npic1 = new ImageIcon(icon1br);
			icon2 = new JLabel();
	   	    icon2.setIcon(npic1);
	    	}
	    catch(Exception e)
	    {
	    	e.printStackTrace(System.out);
	   	    JOptionPane.showMessageDialog(null, "En fil(npic1.png) mangler. Reinstaller programmet for bedre opplevelse.", "Feil: manglende fil", JOptionPane.INFORMATION_MESSAGE);

	    }


		c.gridy=6;
		add(icon2,c);

		news2 = new JLabel(
		"<html><i>Skrevet 02. november 2012</i><br><br>" +
		"Vi åpner skianlegget 20. desember i år. Etter fjorårets supersesong, ser vi veldig frem til en ny sesong, <br>" +
		" og håper selvfølgelig å se deg i bakken. Følg med for mer informasjon!<br><br><br><br></html>");


	    news2.setFont(new Font("Calibri", Font.PLAIN, FONTSIZE));

		c.gridy=7;
		add(news2,c);
	}

}//end of NewsWindowPanel