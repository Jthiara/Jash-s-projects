/** 
 * TCSS 342 
 */

package model;

import java.util.Arrays;

/**
 *  Implement a class called Anagram to store the key and value(s) pair.
 * 
 * @author jthiara
 * @version Winter 2021-2022
 */

public class Anagram {
	
	/**
	 * purpose of class: Storing the key & value(s): into array
	 * one must be able to efficiently search using the key this hash table to look for anagrams of a given word. 
	 * value: rat; sorted key (string sort) = art
	 */
	
	/**
	 * field to store values: (EX) rat, art, tar etc
	 */
	private String[] myValues; 
	
	/**
	 * field to store anagram key (sorted): (Ex) rat -> art 
	 */
	private String myKey;
	
	/**
	 * field to track values
	 */
	private int count = 0;
	
	/**
	 * Constructor for Anagram object.
	 * @param theValue
	 */
	public Anagram(String theValue) {
		myValues = new String[30]; //initialize array
		
		theValue = theValue.toLowerCase(); 
		myValues[0] = theValue;
		myKey = sortString(theValue); // pass to sort String method and return into myKey field.
		count++;
	}
	
	/**
	 * returns the Anagram Key
	 */
	public String getKey() {
		return myKey;
	}
	
	/**
	 * returns collection of value(s).
	 */
	public String[] getValues() {
		return myValues;
	}
	
	/**
	 * takes a value as a parameter and adds it to the existing collection of value(s).
	 */
	public void addValue(String theValue) {
		myValues[count] = theValue;
		count++;
	}
	
	/**
	 * Helper method to arrange String in correct order
	 * @param theString
	 * @return
	 */
	public String sortString(String theString) { 
		char c[] = theString.toCharArray();
		Arrays.sort(c);
		return new String(c);
	}
	
	/**
	 * toString method
	 * @return String representation.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(count + " "); 
		for (int i = 0; i < count; i++) {
			sb.append(myValues[i] + " ");
		}
		return sb.toString();
	}
}
