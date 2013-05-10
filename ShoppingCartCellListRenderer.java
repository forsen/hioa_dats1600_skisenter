import javax.swing.*;
import java.awt.*;

public class ShoppingCartCellListRenderer extends DefaultListCellRenderer
{
	public Component getListCellRendererComponent(
	JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		CartItems ci = (CartItems) value; 

		try
		{
			label.setText( ci.getCard().getCardID() + " " +  ci.getSkiCard().getType() + ", " + ci.getSkiCard().getPrice() + "kr" );
		}
		catch( NullPointerException npe )
		{
			label.setText( "-" + ci.getCard().getCardID() + ", " + Info.RETURNPRICE + "kr" ); 
		}
		return label;
	}
}
