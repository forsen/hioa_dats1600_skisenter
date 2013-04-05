import java.util.*;
import java.text.*;
import javax.swing.JOptionPane;
import java.io.*;

public class Sortorder implements Serializable, Comparator<Person> 
{
	private String order = "<\0<0<1<2<3<4<5<6<7<8<9" +
                  "<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j" +
                 "<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t" +
                 "<U,u<V,v<W,w<X,x<Y,y<Z,z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa";

    private RuleBasedCollator col;

    public Sortorder()
    {
    	try 
    	{
    		col = new RuleBasedCollator(order);
    	}
    	catch(ParseException pe)
    	{
    		JOptionPane.showMessageDialog(null, "kollator feil.");
    		System.exit(0);
    	}
    } 

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


}