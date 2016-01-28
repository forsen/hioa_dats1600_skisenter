package no.forsen.skisenter;

import javax.swing.*;
import java.awt.*;

/**
  * This class Renders an item in the list of Cards. Redefines how the items in the list is displayed.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */


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
