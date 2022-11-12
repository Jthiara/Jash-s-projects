/** 
 * TCSS 342
 * 
 */
package model;

/**
 * Class Implements a Linked List.
 * 
 * @author jthiara
 * @version Winter 2021-2022
 */
public class LinkedList<T> {
	
	/**
	 * Field represents head node of linked list.
	 */
	Node<T> head;
	
	/**
	 * Field represents size of linked list.
	 */
	int size = 0;	
	
	/**
	 * Constructor for LinkedList.
	 */
	public LinkedList () {
		this.head = null;
	}
	
	/**
	 * Method adds node to linked list along with its data. 
	 * 
	 * @param theElement
	 */
	public void add(T theElement) {
		if (theElement == null) {
			throw new IllegalArgumentException();
		}
		if (this.head == null) { // case where list is empty.
			this.head = new Node<T> (theElement);
		} else {
			Node<T>	curr = head; 
			while (curr.next != null) { //iterates list to last node.
				curr = curr.next;
			}
			Node<T> temp = new Node<T> (theElement);
			curr.next = temp;
		}
		size++;
	}
	
	/**
	 * method returns size of linked list.
	 * 
	 * @return size.
	 */
	public int size() {
		
		int size = 0;
		
		if (this.head == null) {
			return size; // returns 0.
		} else { 
			Node<T> curr = this.head;
			while (curr != null) {
				curr = curr.next;
				size++;
			}
			return size; // iterates through list and counts each node.
		}
	}
	
	/**
	 * method returns a String representation of linked list.
	 */
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		if (this.head == null) { // empty list case; returns empty string.
			return sb.toString();
		} else {
			Node<T> curr = this.head; 
			while (curr.next != null) { //iterates list 
				sb.append(curr.elem); 
				sb.append("->");
				curr = curr.next;
			}
			sb.append(curr.elem);
			return sb.toString();
		}
		
	}
	
	
	
	@SuppressWarnings("hiding")
	public class Node<T> {
	    T elem; // Holds element to be stored in Node.
	     
		Node<T> next; // Holds reference to next Node.
		
		Node(T theElem) {
			this.elem = theElem;
			this.next = null;
		}
		
	}
}
