import java.io.*;


public abstract class Person implements Serializable 
{
	
	private int kundeNr;
	private String name;
	private int phoneNr;
	private int age;
	//BILDEDATAFELT?????
	
	protected Skicardlist list;

  	public Person( int k, String n, int p, int a )
  	{
   	 	kundeNr = k;
   	 	name = n;
   		phoneNr = p;
   		age = a;
   		list  = new Skicardlist();
 	}

	public int getKundeNr()
  	{
    	return kundeNr;
  	}

 	public String getName()
  	{
    	return name;
  	}

  	public int getphoneNr()
  	{
    	return phoneNr;
  	}

  	public int getAge()
  	{
    	return age;
  	}

  	public boolean hasCard()
  	{
  		return (list != null) && (!list.isEmpty());
  	}

  	public String newCard( Object c )
  	{
  		Skicard card = new SkiCard(t,p,o,s,v);
		if(list.newCard(card))
		{
			return "Skipass registrert";
		}
		return "Skipasset er allerede registrert";
  	}

  	public boolean equals( Object p )
	{
	  	return ((Person)p).getKundeNr().equals( kundeNr ) && ((Person)p).getName().equals( name ) &&
	          ((Person)p).getphoneNr().equals( phoneNr )  && ((Person)p).getAge().equals( age );
  	}


  	abstract public String toString();



  	abstract public boolean ownsCard(SkiCard card);

  	



	/*<datafelter, inkludert heiskortliste>

	<konstruktør som oppretter personobjektet>

	<metode for å returnere navn>

	<metode for å returnere kundenr>

	<metode for å registrere heiskort til navn>*/

	
}