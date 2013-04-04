import javax.swing.*;
import java.awt.*;

public class SearchListCellRenderer extends DefaultListCellRenderer
{
    public Component getListCellRendererComponent(
        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        // I know DefaultListCellRenderer always returns a JLabel
        // super setups up all the defaults
        JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // "value" is whatever object you put into the list, you can use it however you want here

        Person p = (Person) value; 

        // I'm going to prefix the label text to demonstrate the point

       label.setText( " " +  p.getLastName() + ", " + p.getFirstName() + " " );

       return label;

    }
}

// Some time later...
