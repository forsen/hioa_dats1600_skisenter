public class ShoppingCart
{
	private double sum; 
	private DefaultListModel<Skicard> items;

	public ShoppingCart()
	{
		items = new DefaultListModel<>();
	}

	public DefaultListModel addToCart( Skicard c )
	{
		items.addElement( c );
		sum += c.getPrice();
		return items;
	}

	public DefaultListModel deleteFromCart( Skicard c )
	{
		items.removeElement( c );
		sum -= c.getPrice();

		return items; 
	}

	public void checkOut() 
	{
		// do something more
	}
}