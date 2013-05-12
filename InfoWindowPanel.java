import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;
import javax.imageio.*;
import java.awt.image.BufferedImage;



/**
  * InfoWindowPanel is one of the four panels available for displaying in the Info-window.
  * This one displays useful information to the customer. 
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */


public class InfoWindowPanel extends JPanel
{
	private JLabel headerImg1, headerImg2;
	private JLabel header;
	private JLabel content;
	private JTable liftTable;
	private JScrollPane liftScrollPane;
	private BufferedImage snowboarderImg, skimapImg;


	/**
	  * The constructor creates all the elements and place them on the panel.
	  * This panel has no other intention than to display information.
	  */

	public InfoWindowPanel()
	{
	

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();


		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.BOTH;
		setBackground(new Color(220, 240, 255));

		try{
			snowboarderImg = ImageIO.read( new File("img/snowboarder.png"));
			ImageIcon snowboarder = new ImageIcon(snowboarderImg);
			headerImg1 = new JLabel(snowboarder);
			headerImg1.setHorizontalAlignment(JLabel.CENTER);
			}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, "En fil(snowboarder.png) mangler. Reinstaller programmet for bedre opplevelse.", "Feil: manglende fil", JOptionPane.INFORMATION_MESSAGE);
		}

		try{
			skimapImg = ImageIO.read( new File("img/skimap.png"));
			ImageIcon skimap = new ImageIcon(skimapImg);
			headerImg2 = new JLabel(skimap);
			headerImg2.setHorizontalAlignment(JLabel.CENTER);
			}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
	    	JOptionPane.showMessageDialog(null, "En fil(skimap.png) mangler. Reinstaller programmet for bedre opplevelse.", "Feil: manglende fil", JOptionPane.INFORMATION_MESSAGE);

		}






		header = new JLabel("Skitrekket");
		header.setFont(new Font("Calibri", Font.BOLD, 20));
		header.setHorizontalAlignment(JLabel.CENTER);

		c.gridx =0;
		c.gridy =0;

		add(header, c);

		c.gridy=1;

		add(headerImg2, c);

		content = new JLabel("<html>Skitrekket består av to heiser, en stolheis og en krokheis. Vi har alt fra grønne til svarte løyper, <br>" +
			"altså løyper for både nybegynnere og viderekomne.<br><br><br></html>");
		content.setFont(new Font("Calibri", Font.PLAIN, 14));
		content.setHorizontalAlignment(JLabel.CENTER);







		c.gridy=2;
		add(content, c);


		header = new JLabel("Heiser");
		header.setFont(new Font("Calibri", Font.BOLD, 20));
		header.setHorizontalAlignment(JLabel.CENTER);
		c.gridy=3;
		add(header, c);

		/* ********************Creates the lifttable******************* */
		Object rowData[][] = { 
		{"Stolheis", Skiresort.chLift.getName(), Skiresort.chLift.getLiftNr() , Skiresort.chLift.getClips(), Skiresort.chLift.getLength() + " m."},
        {"Krokheis", Skiresort.tLift.getName(), Skiresort.tLift.getLiftNr() , Skiresort.tLift.getClips(), Skiresort.tLift.getLength() + " m."}};
     
    	Object columnNames[] = {"Type", "Heisnavn", "Heisnummer", "Tar antall klipp", "Lengde" };
 
    	liftTable = new JTable(rowData, columnNames);
    	liftTable.setEnabled(false);
    	liftTable.setBackground(new Color(220, 240, 255));
    	liftScrollPane = new JScrollPane(liftTable);
    	liftScrollPane.setPreferredSize(new Dimension(550,55));


        /* ******************************************************* */
		c.gridy=4;
		add(liftScrollPane, c);




		header = new JLabel("<html><br><br><br>Åpningstider</html>");
		header.setFont(new Font("Calibri", Font.BOLD, 20));
		header.setHorizontalAlignment(JLabel.CENTER);
		c.gridx = 0;
		c.gridy = 5;

		add(header,c);

		content = new JLabel("<html>Man: 10:00-16:30<br>"
							+"Tir: 10:00-17:00<br>"
							+"Ons: 9:30-17:00<br>"
							+"Tor: 10:00-17:00<br>"
							+"Fre: 9:00-17:30<br>"
							+"Lør: 9:00-18:00<br>"
							+"Søn: 9:00-18:00</html>");
	    content.setFont(new Font("Calibri", Font.PLAIN, 14));
	    content.setHorizontalAlignment(JLabel.CENTER);

		c.gridx = 0;
		c.gridy = 6;

		add(content, c);

		
		c.gridy=7;
		add(headerImg1, c);

		header = new JLabel("<html><br><br><br>Kontaktinformasjon</html>");
		header.setFont(new Font("Calibri", Font.BOLD, 20));
		header.setHorizontalAlignment(JLabel.CENTER);

		c.gridy=8;
		add(header, c);





		content = new JLabel("<html>Granskogen 1<br>"+
									"9999, HUNDREMETERFJELLET <br>" +
							        "Telefon: 22 33 44 66 <br>" +
							        "E-post : post@offpist.no<br>" +
							        "<br><br><br></html>");
	    content.setFont(new Font("Calibri", Font.PLAIN, 14));
	    content.setHorizontalAlignment(JLabel.CENTER);

		c.gridy=9;
		add(content,c);



	}
} //end of class InfoWindowPanel

