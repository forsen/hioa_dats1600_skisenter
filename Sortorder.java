import java.util.*;
import java.text.*;
import javax.swing.JOptionPane;
import java.io.*;

/**
  * Sortorder is a class, used for sorting respectively by last and first name.
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

public class Sortorder implements Serializable, Comparator<Person> 
{
	private String order = "<\0<0<1<2<3<4<5<6<7<8<9" +
                  "<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j" +
                 "<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t" +
                 "<U,u<V,v<W,w<X,x<Y,y<Z,z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa";

    private RuleBasedCollator col;

/**
  * Creates the collator
  *
  * @see Personlist#sort()
  * @see AdminInfoPanel#showPersonsWithCards()
  */

    public Sortorder()
    {
    	try 
    	{
    		col = new RuleBasedCollator(order);
    	}
    	catch(ParseException pe)
    	{
    		pe.printStackTrace( System.out );
    		System.exit(0);
    	}
    } 

/**
  * This is the method that will do the sorting. It takes two person objects and sorts them on name.
  * It makes the sorting jump over to sorting by first name, if last names being compared are alike.
  * @param p1   The first person object to be compared
  * @param p2   The second person object to be compared
  */

    public int compare(Person p1, Person p2)
    {
    	String lname = p1.getLastName();
    	String lname2 = p2.getLastName();
    	String fname = p1.getFirstName();
    	String fname2 = p2.getFirstName();

    	int r = col.compare(lname,lname2);

    	if(r != 0)
    		return r;
    	else
    		return col.compare(fname,fname2);
    }


} // end of class Sortorder
