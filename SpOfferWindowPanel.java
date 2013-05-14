import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;
import java.awt.image.BufferedImage;
import javax.imageio.*;


/**
  * SpOfferWindowPanel is one of the four panels available for displaying in the Info-window.
  * This one displays special offers to the customer. 
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

public class SpOfferWindowPanel extends JPanel
{
// CONSTANTS
	private final int FONTSIZE = 14;
// END CONSTANTS

	private JLabel offerPic1, offerPic2, offerPic3, offer1, offer2, offer3;
	private BufferedImage offerPic1Img, offerPic2Img, offerPic3Img;


	/**
	  * The constructor creates all the elements and place them on the panel.
	  * This panel has no other intention than to display information.
	  */


	public SpOfferWindowPanel()
	{
	

		
		setBackground(new Color(220, 240, 255));

		try{
			offerPic1Img = ImageIO.read( new File("img/freakyfriday.png"));
			ImageIcon ff = new ImageIcon(offerPic1Img);
			offerPic1 = new JLabel(ff);
			}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, "En fil(freakyfriday.png) mangler. Reinstaller programmet for bedre opplevelse.", "Feil: manglende fil", JOptionPane.INFORMATION_MESSAGE);
		}

		try{
			offerPic2Img = ImageIO.read( new File("img/groupdiscount.png"));
			ImageIcon gD = new ImageIcon(offerPic2Img);
			offerPic2 = new JLabel(gD);
			}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, "En fil(groupdiscount.png) mangler. Reinstaller programmet for bedre opplevelse.", "Feil: manglende fil", JOptionPane.INFORMATION_MESSAGE);
		}

		try{
			offerPic3Img = ImageIO.read( new File("img/studentdiscount.png"));
			ImageIcon sD = new ImageIcon(offerPic3Img);
			offerPic3 = new JLabel(sD);
			}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, "En fil(studentdiscount.png) mangler. Reinstaller programmet for bedre opplevelse.", "Feil: manglende fil", JOptionPane.INFORMATION_MESSAGE);
		}

		
		
		
		
		

		offer1 = new JLabel("<html> Siste fredag i måneden, arrangerer vi FREAKY FRIDAY. Hver Freaky Friday får du halv pris på alle <br> heiskort. Dette er de desidert kuleste dagene i skitrekket våres. <br> <i>PS: FF-rabatten kan ikke kombineres med andre rabatter.</i></html>");
		offer2 = new JLabel("<html> Kommer du til skianlegget med 9 kamerater, familiemedlemmer eller kolleger kan du vente deg en <br>saftig rabatt. Er dere minst 10 personer, får dere 10% rabatt pr. kort. </html>");
		offer3 = new JLabel("<html> Er du en student med stram økonomi? Ikke bekymre deg. Hos oss får du 20% rabatt, samt gratis<br> inngang på afterski.</html>");

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();

	    offer1.setFont(new Font("Calibri", Font.PLAIN, FONTSIZE));
	    offer2.setFont(new Font("Calibri", Font.PLAIN, FONTSIZE));
	    offer3.setFont(new Font("Calibri", Font.PLAIN, FONTSIZE));


	    c.insets = new Insets(20, 5, 5, 5);
	    c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;

		/* First column ****************/

		c.gridx=0;
		c.gridy=0;
		c.weightx=0.5;
		c.weighty=0.5;
		c.gridwidth = 2;
		add(offerPic1, c);

		c.gridy=1;
		add(offerPic2, c);

		c.gridy=2;
		add(offerPic3, c);

		/* Second column ****************/
		c.gridwidth=1;
		c.weightx=1;
		c.gridx=3;
		c.gridy=0;
		add(offer1, c);

		c.gridx = 3;
		c.gridy =1;
		add(offer2, c);

		c.gridy=2;
		add(offer3, c);
	}
} // end of class SpOfferWindowPanel
