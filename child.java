import java.io.*;

public class Child extends Person implements Serializable
{
	private int rabatt;

	public Child (int k, String n, int p, int a)
	{
		super(k,n,p,a);

	}


  	public String toString()
  	{
  		StringBuilder text = new StringBuilder();
		
		text.append(name); 
		text.append("\ntlf: "); 
		text.append(phoneNr); 
		text.append("\nAlder "); 
	    text.append(age); 
	    text.append("\n"); 
		
		String ferdigTekst = text.toString();
		
		return ferdigTekst;
    	
  	}

}