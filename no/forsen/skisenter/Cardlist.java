package no.forsen.skisenter;

import java.io.*;
import java.util.*;
import javax.swing.DefaultListModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.*;

/**
 * Cardlist is a class that makes a list of Card objects. It is used both as
 * a list per person, and also as a universal list of all unregistered cards. 
 * 
 * @author Erik Haider Forsén
 * @author Ole Hansen
 * @author Julie Hill Roa
 * @version 0.9
 * @see Person#list
 * @see Skiresort#cardregistry
 */
public class Cardlist implements Serializable
{
	private List<Card> list;

/**
  * The constructor creates an empty LinkedList for Cards
  */
	public Cardlist()
	{
		list = new LinkedList<>();
	}

/**
  * Method to decide whether the list is empty or not.
  * @return True if empty, false if not.
  */
	public boolean isEmpty()
	{
		return list == null || list.size() == 0;
	}

/**
  * Method to delete a card from this list. 
  * @param obj 	Card to be deleted from list
  * @return Returns the same card if deletion was successful, else it returns null
  */
	public Card deleteCard(Card obj)
	{

		Iterator<Card> it = list.iterator();

		while (it.hasNext())
		{
			if(it.next().equals(obj))
			{
				Skiresort.unsaved = true;
				it.remove();
				return obj;
			}
		}
		return null;
	}
/**
  * Method to search the list for a card based on a cardID.
  * @param cardNumber 	The cardnumber to search for.
  * @return Returns the card if it was found, else it returns null
  * @see Card#cardNumber
  */
	public Card findCard(int cardNumber)
	{
		Iterator<Card> it = list.iterator();

		while (it.hasNext())
		{
			Card card = it.next();
			if(card.getCardID() == cardNumber)
			{
				return card;
			}

		}
		return null;
	}

/**
  * This method add a given card to the list
  * @param n 	Card to be added
  * @return Returns the ID of the Card added to the list. 
  */
	public int addCard( Card n )
	{
		Skiresort.unsaved = true; 
		list.add( n );
		return n.getCardID();
	}

/**
  * This method compile a listmodel including all the cards in this list as
  * elements.
  * @return Returns a listmodel of all cards in this list.
  */
	public DefaultListModel<Card> listCards()
	{
		DefaultListModel<Card> searchresult = new DefaultListModel<>();  

		Iterator<Card> it = list.iterator();

		while(it.hasNext())
		{
			Card card = it.next();
			if( !card.getReturned() )
				searchresult.addElement( card );
		}

		return searchresult;
	}

/**
  * Method to help generate statistics.
  * @param start 	The start date of the interval you want to get Cards from
  * @param end 		The end date of the interval you want to get Cards from
  * @return Returns a list of all the Cards in this list, between the given dates.
  * @see AdminStatisticsPanel
  */
	public List<Card> getRelevantCards(Date start, Date end)
	{
		List<Card> tempList = new LinkedList<>();
		Iterator<Card> it = list.iterator();

		while(it.hasNext())
		{
			tempList.add(it.next());
		}
		return tempList;
	}

/**
  * The toString method for this class. 
  * @return Returns a String containing Card.toString() and Card.history().
  * @see Card#history()
  * @see Card#toString()
  */

	public String toString()
	{
		StringBuilder text = new StringBuilder();
		
		Iterator<Card> it = list.iterator();
		
		
		while(it.hasNext())
		{
			Card runner = it.next();
			if( !runner.getReturned() )
			{
				text.append("\n");
				text.append(runner.toString());
				text.append("\n");
				text.append(runner.history());
			}
		
		} 
		
		String doneText = text.toString();
		
		return doneText;
	}

}//end of class Skicardlist