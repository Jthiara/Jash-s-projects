/** 
 * TCSS 342
 */

package model;

import model.HuffmanTree.HuffNode;

/**
 * Class Implements a Priority Queue using Array Heaps.
 * 
 * @author jthiara
 * @version Winter 2021-2022
 */

// Class extends ArrayHeap and inherits add() and removeMin()
public class PriorityQueue extends ArrayHeap {
	
	/**
	 * Constructor.
	 */
	public PriorityQueue(int theSize) { 
		super(theSize);
	}
	
	/**
	 * method takes element and priority to create a Huffman Tree Node and add it to the to
	 * the priority queue based on the priority value.
	 * @param theElem, theVal
	 */
	public void addElement(char theElem, int theVal) {
			add(new HuffNode(theElem, theVal)); //call add method from array heap class
	}
	
	/**
	 * Method returns node 
	 * @return Highest priority Huffman Tree Node from PQ
	 */
	public HuffNode removeNext() {
		return removeMin(); //call remove min from array heap class 
	}
}
