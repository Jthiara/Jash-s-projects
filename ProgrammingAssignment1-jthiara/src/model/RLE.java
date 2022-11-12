/** 
 * TCSS 342
 * 
 */

package model;

/**
 * Class represents RLE.
 * 
 * @author jthiara
 * @version winter 2021-2022
 */
public class RLE {

	/**
	 * Method takes in a linked list and returns a compressed string version.
	 * 
	 * @param theList
	 * @return sb.toString (compressed version of linked list).
	 */
	public static String encode(LinkedList<String> theList) {
		int count = 0;
		StringBuilder sb = new StringBuilder();
		
		if (theList.size == 0) {
			return sb.toString(); // returns empty string.
		} else {
			LinkedList<String>.Node<String> curr = theList.head; //special case to create head of list.
			String temp = curr.elem;
			count++;
			
			//the approach here is to iterate through the list and count values that are the same and in a sequence
			//so that we can compress the list. (fff -> 3f).
			while (curr.next != null) {
				curr = curr.next;
				if (temp.equals(curr.elem) && curr.next != null) { // case where elements are in sequence
					count++;
				} else if (curr.next == null) { //last node case.
					count++;
					sb.append(count + temp);
				} else {                        // case where elements are no longer in sequence. 
					sb.append(count + temp);
					temp = curr.elem;
					count = 1;
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * Method takes a encoded string and returns a string representation of a linked list. 
	 * 
	 * @param theEncodedString
	 * @return
	 */
	public static String decode(String theEncodedString) {
		LinkedList<String> decodedList = new LinkedList<String>();
		int count = 0;
		if (theEncodedString.length() == 0) { //empty string case
			return decodedList.toString();
		} else {

			//the approach here is to first read the number of times a element will be repeated
			// and then send this number to a loop that will create that many nodes. 
			while (count < theEncodedString.length() - 1) { //iterates through string.
				int iterator = (int) theEncodedString.charAt(count) - 48; //convert char
				String temp = "" + theEncodedString.charAt(++count);
				for (int i = 0; i < iterator; i++) {
					decodedList.add(temp);
				}
				count++;
			}
		return decodedList.toString();
		}
	}
	
	
	/**
	 * Method takes in two encoded strings and returns whether or not they are equal. 
	 * 
	 * @param theEncode1
	 * @param theEncode2
	 * @return
	 */
	public static boolean equals(final String theEncode1, final String theEncode2) {
		if (theEncode1 == null && theEncode2 == null) { // comparison of two empty Strings
			throw new IllegalArgumentException(); 
		} else if (theEncode1 == null || theEncode2 == null) {
			return false;
		} else {
			
			//approach here is to turn the strings back into a linked list string for comparison. 
			// (Ex. 2A1A = 3A because they will be the same in a linked list order).
			String holder = RLE.decode(theEncode1);
			String holder2 = RLE.decode(theEncode2);
			
			return (holder.equals(holder2));
		}
	}
}

