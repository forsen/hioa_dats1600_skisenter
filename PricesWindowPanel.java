import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;


public class PricesWindowPanel extends JPanel
{
	private JLabel header;
	private JLabel content;
	private JTable priceTable;
	private JScrollPane priceScrollPane;



	public PricesWindowPanel()
	{
		Object rowData[][] = { { "Dagskort", Info.DAYCARDPRICE + " kr.", (Info.DAYCARDPRICE*Info.DISCOUNT) + " kr."},
        { "Sesongkort", Info.SEASONCARDPRICE + " kr.", (Info.SEASONCARDPRICE*Info.DISCOUNT) + " kr."},
        {"Timeskort:", Info.HOURCARDPRICE + " kr.", (Info.HOURCARDPRICE*Info.DISCOUNT) + " kr."},
        {"Klippekort", Info.PUNCHCARDPRICE + " kr.", (Info.PUNCHCARDPRICE*Info.DISCOUNT) + " kr."} };
    	Object columnNames[] = { "Korttype", "Voksen (over " +Info.CHILDLIMIT+" år)", "Barn (tom. " + Info.CHILDLIMIT + "år.)", };
    	priceTable = new JTable(rowData, columnNames);
    	priceTable.setEnabled(false);
    	priceTable.setBackground(new Color(220, 240, 255));
    	priceScrollPane = new JScrollPane(priceTable);
    	priceScrollPane.setPreferredSize(new Dimension(550,87));
	

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		setBackground(new Color(220, 240, 255));


		/************ SAK 1 **************************************************/


		header = new JLabel("Heiskort");
	    header.setFont(new Font("Calibri", Font.BOLD, 20));

		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.VERTICAL;

		c.gridx = 1;
		c.gridy = 1;
		c.weighty=0.5;

		c.anchor = GridBagConstraints.LINE_START;
		add(header, c);

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		add(priceScrollPane, c);

		/************ SAK 2 ***************************************************/

		header = new JLabel("<html><br><br>Rabatter</html>");
	    header.setFont(new Font("Calibri", Font.BOLD, 20));

		c.gridx = 1;
		c.gridy= 3;
		c.weighty=0.5;
		add(header, c);

		content = new JLabel("<html>Hos oss vil alle småracere som er 16 år eller yngre få kjøre i bakken" +
							 "til halv pris, uansett type heiskort.<br><br><br></html>");
		content.setFont(new Font("Calibri", Font.PLAIN, 14));
		c.gridx = 1;
		c.weighty=0;
		c.gridy= 4;
		add(content, c);

		/************ SAK 3 *****************************************************/

		header = new JLabel("Afterski");
		header.setFont(new Font("Calibri", Font.BOLD, 20));
		c.gridy=5;
		c.weighty=0.5;
		add(header, c);

		content = new JLabel("<html>Skisenteret tilbyr egen afterski, som går for å være en av landets beste." +
							"Møt opp med godt humør og <br> tørst hals, her har vi både god partystemning og billige priser i baren.<br><br><br></html>");
		content.setFont(new Font("Calibri", Font.PLAIN, 14));
		c.gridy=6;
		c.weighty=0;
		add(content,c);

		/************ SAK 4 *****************************************************/

		header = new JLabel("Skiskole");
		header.setFont(new Font("Calibri", Font.BOLD, 20));
		c.gridy=7;
		c.weighty=0.5;
		add(header, c);

		content = new JLabel("<html> Vi tilbyr også vår egen skiskole. Skiskolen er beregnet på barn mellom 8 og 14 år. Den varer over en <br>"
									 +" uke og koster 2200 kr. Neste skiskole starter 28. november 2013. Ring 22 33 44 55 for påmelding!<br><br><br></html>");
		content.setFont(new Font("Calibri", Font.PLAIN, 14));
		c.gridy=8;
		c.weighty=0;
		add(content,c);

		/* For å legge til fler saker, kopier en tidligere sak, og endre vesentlige verdier */
	}
}