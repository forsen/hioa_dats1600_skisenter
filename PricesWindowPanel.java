import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Date;

public class PricesWindowPanel extends JPanel
{
	private JLabel price1;
	private JTable priceTable;



	public PricesWindowPanel()
	{
		Object rowData[][] = { { "Dagskort", Info.DAYCARDPRICE, (Info.DAYCARDPRICE*Info.DISCOUNT)},
        { "Sesongkort", Info.SEASONCARDPRICE, (Info.SEASONCARDPRICE*Info.DISCOUNT)},
        {"Timeskort:", Info.HOURCARDPRICE, (Info.HOURCARDPRICE*Info.DISCOUNT)},
        {"Klippekort", Info.PUNCHCARDPRICE, (Info.PUNCHCARDPRICE*Info.DISCOUNT)} };
    	Object columnNames[] = { "Korttype", "Voksen (over " +Info.CHILDLIMIT+" år)", "Barn (tom. " + Info.CHILDLIMIT + "år.)", };
    	priceTable = new JTable(rowData, columnNames);
    	priceTable.setEnabled(false);
    	priceTable.setBackground(new Color(220, 240, 255));
	

		setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		setBackground(new Color(220, 240, 255));


		price1 = new JLabel("Heiskort");

	    price1.setFont(new Font("Calibri", Font.PLAIN, 20));

		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		add(price1, c);

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		add(new JScrollPane(priceTable), c);
	}
}