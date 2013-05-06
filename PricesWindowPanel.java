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
    	priceScrollPane.setPreferredSize(new Dimension(550,88));
	

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		setBackground(new Color(220, 240, 255));


		header = new JLabel("Heiskort");
	    header.setFont(new Font("Calibri", Font.BOLD, 20));

		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		add(header, c);

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		add(priceScrollPane, c);

		header = new JLabel("Rabatter");
	    header.setFont(new Font("Calibri", Font.BOLD, 20));

		c.gridx = 1;
		c.gridy= 3;
		add(header, c);

		content = new JLabel("Hos oss vil alle småracere som er 16 år eller yngre få kjøre i bakken\n" +
							 "til halv pris, uansett type heiskort.");
		content.setFont(new Font("Calibri", Font.PLAIN, 14));
		c.gridx = 1;
		c.gridy= 4;
		add(content, c);

	}
}