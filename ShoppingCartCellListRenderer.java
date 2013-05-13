import javax.swing.*;
import java.awt.*;

/**
  * This class Renders an item in the list of items in the shoppingcart. Sets how the items in the list is displayed.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */


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
