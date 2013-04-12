import javax.swing.*;
import java.awt.*;

public class ShoppingCartCellListRenderer extends DefaultListCellRenderer
{
	public Component getListCellRendererComponent(
	JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		CartItems ci = (CartItems) value; 

		label.setText( ci.getCardID() + " " +  ci.getType() + ", " + ci.getPrice() + "kr" );

		return label;
	}
}
