import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;
import java.text.NumberFormat;
import java.util.Locale;


public class PricesWindowPanel extends JPanel
{
	private JLabel header;
	private JLabel content;
	private JTable priceTable;
	private JScrollPane priceScrollPane;
	private NumberFormat paymentFormat; 



	public PricesWindowPanel()
	{
		paymentFormat = NumberFormat.getCurrencyInstance( new Locale( "no", "NO" ) );

		Object rowData[][] = { { "Dagskort", paymentFormat.format( Info.DAYCARDPRICE ), paymentFormat.format( Info.DAYCARDPRICE*Info.DISCOUNT ) },
        { "Sesongkort", paymentFormat.format( Info.SEASONCARDPRICE ), paymentFormat.format(Info.SEASONCARDPRICE*Info.DISCOUNT) },
        {"Timeskort:", paymentFormat.format( Info.HOURCARDPRICE ), paymentFormat.format(Info.HOURCARDPRICE*Info.DISCOUNT) },
        {"Klippekort", paymentFormat.format( Info.PUNCHCARDPRICE ), paymentFormat.format(Info.PUNCHCARDPRICE*Info.DISCOUNT) } };
    	Object columnNames[] = { "Korttype", "Voksen (over " +Info.CHILDLIMIT+" år)", "Barn (tom. " + Info.CHILDLIMIT + "år.)", };
    	priceTable = new JTable(rowData, columnNames);
    	priceTable.setEnabled(false);
    	priceTable.setBackground(new Color(220, 240, 255));
    	priceScrollPane = new JScrollPane(priceTable);
    	priceScrollPane.setPreferredSize(new Dimension(550,87));
	

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		setBackground(new Color(220, 240, 255));


		/************ ELEMENT 1 **************************************************/


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

		/************ ELEMENT 2 ***************************************************/

		header = new JLabel("<html><br><br>Rabatter</html>");
	    header.setFont(new Font("Calibri", Font.BOLD, 20));

		c.gridx = 1;
		c.gridy= 3;
		c.weighty=0.5;
		add(header, c);

		content = new JLabel("<html>Hos oss vil alle småracere som er 16 år eller yngre få kjøre i bakken" +
							 "til halv pris, uansett type heiskort.<br>Les om fler rabattyper i 'Tilbud' i fanen til venstre<br><br><br></html>");
		content.setFont(new Font("Calibri", Font.PLAIN, 14));
		c.gridx = 1;
		c.weighty=0;
		c.gridy= 4;
		add(content, c);

		/************ ELEMENT 3 *****************************************************/

		header = new JLabel("Afterski");
		header.setFont(new Font("Calibri", Font.BOLD, 20));
		c.gridy=5;
		c.weighty=0.5;
		add(header, c);

		content = new JLabel("<html>Skisenteret tilbyr egen afterski, som går for å være en av landets beste." +
							"Møt opp med godt humør og <br> tørst hals, her har vi både god partystemning og billige priser i baren.<br>"+
							"Inngang: 50kr. CC: 20 år.<br><br><br></html>");
		content.setFont(new Font("Calibri", Font.PLAIN, 14));
		c.gridy=6;
		c.weighty=0;
		add(content,c);

		/************ ELEMENT 4 *****************************************************/

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

		/* To add more elements, copy an existing element, and change values */
	}
}