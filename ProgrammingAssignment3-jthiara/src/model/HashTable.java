/** 
 * TCSS 342 
 */

package model;

import java.util.Arrays;

/**
 * Class represents a Hash Table to be utilized for creating anagrams.
 * *
 * @author jthiara
 * @version Winter 2021-2022
 */
public class HashTable {
	
	/**
	 * Anagram array field.
	 */
	private Anagram[] table; 
	
	/**
	 * Field to track collisions
	 */
	private int collisions = 0;
	
	/**
	 * Field to track size.
	 */
	private static int maxSize;
	
	/**
	 * Constructor for HashTable.
	 */
	public HashTable() {
		table = new Anagram[199999];
		maxSize = table.length;
	}
	
	
	/**
	 * takes key as a parameter, computes the correct index position 
	 * (location in the hash table) and returns it.
	 * @return location
	 */
	public int HashCode(String theKey) {
		int c = 1;
		
		//Hash function iterates through array and multiplies itself with char at the next location times 41.
		for (int i = 0; i < theKey.length(); i++) {
			c *= (int) theKey.charAt(i) * 41;
		}
		int indexLoco = Math.abs(c % maxSize);
		
		if (table[indexLoco] == null) { // case where the key does not yet exist
			return indexLoco;
		} else if (table[indexLoco].getKey().equals(theKey)) { //case where the key does already exist.
			return indexLoco;
		} else {
		
		int quadratic = 1;
		while (isSpotTaken(theKey, indexLoco)) { // quadratic probing
			indexLoco = Math.abs((indexLoco + (quadratic * quadratic)) % maxSize);
			quadratic++;
		}
		return indexLoco;
		}
	}
	
	
	/**
	 * adds a new key (if key is not in table already) Anagram Object to the Hash Table at the correct index 
	 * returned by Hashcode() or if the key exists then adds the new word as a value to the Anagram object.
	 */
	public void add(String theValue) {
		String myKey = sortKey(theValue);
		int key = HashCode(myKey);
		
		if (table[key] == null) { // key has not yet been added case
			
			table[key] = new Anagram(theValue);
		} else {                  // key already exists in our hash table, so we add the value to the anagram list.
			table[key].addValue(theValue);
		}
		
	}
	
	/**
	 * Method will serve to add values to Hashtable utilizing the java method.
	 * @param theValue
	 */
	public void addJava(String theValue) {
		String myValue = sortKey(theValue);
		int hashCode = Math.abs(myValue.hashCode() % maxSize);
		
		if (table[hashCode] == null) { // key has not yet been added case.
				table[hashCode] = new Anagram(theValue);
		} else {                       // key already exists in our hash table, so we add the value to the anagram list.
			table[hashCode].addValue(theValue);
			collisions++;
		}
	}
	
	/**
	 * takes search word as a parameter, computes its key then uses HashCode() method to return 
	 * index location where key can be found.
	 */
	public int search(String theValue) {
		theValue = theValue.toLowerCase();
		return HashCode(sortKey(theValue));
	}
	
	/**
	 * Method will check if spot is taken and collision will occur. 
	 * @param keyToCheck
	 * @param indexToCheck
	 * @return spotTaken boolean
	 */
	public boolean isSpotTaken(String keyToCheck, int indexToCheck) {
		if (table[indexToCheck] == null) {
			return false; 
		} else {
			collisions++;
			return true;
		}
	}
	
	/**
	 * Helper method to sort string values.
	 * @param theString
	 * @return
	 */
	public String sortKey(String theString) { 
		char c[] = theString.toCharArray();
		Arrays.sort(c);
		return new String(c);
	}
	
	/**
	 * Helper method to return a desired anagram list of a key
	 * 
	 * @param theIndex
	 * @return anagram.
	 */
	public Anagram getAnagrams(int theIndex) {
		return table[theIndex];
	}
	
	/**
	 * Method will return collisions
	 * @return collisions
	 */
	public int getCollision() {
		return collisions; 
	}
	
	/**
	 * Method will return a string representation for testing
	 * @return string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				sb.append(table[i]);
				sb.append("\n");
			}
		}
		
		sb.append(collisions+ "\n");
		return sb.toString(); 
	}
}
