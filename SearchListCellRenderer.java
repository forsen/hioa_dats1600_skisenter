import javax.swing.*;
import java.awt.*;

public class SearchListCellRenderer extends DefaultListCellRenderer
{
	public Component getListCellRendererComponent(
	JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		Person p = (Person) value; 

		label.setText( " " +  p.getLastName() + ", " + p.getFirstName() + " " );

		return label;
	}
}
