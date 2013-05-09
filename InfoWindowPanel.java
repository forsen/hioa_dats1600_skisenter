import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;
import javax.imageio.*;

// Til Ole: 
// String tekst = Skisenter.lift1.getLift().toString(); 

public class InfoWindowPanel extends JPanel
{
	private JLabel headerImg1, headerImg2;
	private JLabel header;
	private JLabel content;
	private JTable liftTable;
	private JScrollPane liftScrollPane;



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


		header = new JLabel("Heiser");
		header.setFont(new Font("Calibri", Font.BOLD, 20));
		c.gridy=3;
		add(header, c);

		/* ********************Lager heistabell******************* */
		Object rowData[][] = { 
		{"Stolheis", Skisenter.chLift.getName(), Skisenter.chLift.getLiftNr() , Skisenter.chLift.getClips(), Skisenter.chLift.getLength() + " m."},
        {"Krokheis", Skisenter.tLift.getName(), Skisenter.tLift.getLiftNr() , Skisenter.tLift.getClips(), Skisenter.tLift.getLength() + " m."}};
     
    	Object columnNames[] = {"Type", "Heisnavn", "Heisnummer", "Tar antall klipp", "Lengde" };
 
    	liftTable = new JTable(rowData, columnNames);
    	liftTable.setEnabled(false);
    	liftTable.setBackground(new Color(220, 240, 255));
    	liftScrollPane = new JScrollPane(liftTable);
    	liftScrollPane.setPreferredSize(new Dimension(550,55));

        /* ******************************************************* */
		c.gridy=4;
		add(liftScrollPane, c);


//****** NYTT ELEMENT *************************************//

		header = new JLabel("<html><br><br><br>Åpningstider</html>");
		header.setFont(new Font("Calibri", Font.BOLD, 20));
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
		c.anchor = GridBagConstraints.PAGE_START;
		add(content, c);
		headerImg1 = new JLabel(snowboarder);
		
		c.gridy=7;
		add(headerImg1, c);



	}
}

