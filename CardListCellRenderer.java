import javax.swing.*;
import java.awt.*;

public class CardListCellRenderer extends DefaultListCellRenderer
{
	public Component getListCellRendererComponent(
	JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		Card c = (Card) value; 

		label.setText( " " +  c.getCardID() + " " );

		return label;
	}
}
