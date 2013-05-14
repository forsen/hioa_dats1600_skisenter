import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

/**
  * This class Renders an item in the list of items in the shoppingcart. Redefines how the items in the list is displayed.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */


public class ShoppingCartCellListRenderer extends DefaultListCellRenderer
{
	private NumberFormat paymentFormatter;
	public Component getListCellRendererComponent(
	JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		paymentFormatter = NumberFormat.getCurrencyInstance( new Locale("no", "NO") );

		JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		CartItems ci = (CartItems) value; 

		try
		{
			label.setText( ci.getCard().getCardID() + " " +  ci.getSkiCard().getType("") + ", " + paymentFormatter.format( ci.getSkiCard().getPrice() ) );
		}
		catch( NullPointerException npe )
		{
			label.setText( "-" + ci.getCard().getCardID() + ", " + paymentFormatter.format( Info.RETURNPRICE ) ); 
		}
		return label;
	}
}
