/** 
 * TCSS 342
 * 
 */

package model;

/**
 * Class Implements a Huffman Tree.
 * 
 * @author jthiara
 * @version Winter 2021-2022
 */

public class HuffmanTree {
		
		/**
		 * Root Node for Huffman Tree.
		 */
		public HuffNode rootNode;
		
		/**
		 * Method will take two nodes and create a huffman tree. 
		 * 
		 * @param theLeftNode
		 * @param theRightNode
		 * @return rootNode
		 */
		public HuffNode add(HuffNode theLeftNode, HuffNode theRightNode) {
			
			rootNode = new HuffNode(theLeftNode.myFrequency + theRightNode.myFrequency); //create new root node
			//case where the left node has higher priority
			if (theLeftNode.compareTo(theRightNode) > 0) {
				rootNode.myLeft = theLeftNode;
				rootNode.myRight = theRightNode;
			} else {
				rootNode.myLeft = theRightNode;
				rootNode.myRight = theLeftNode;
			}
			return rootNode;
		}
	
	
	
	/**
	 * Huffman Tree Node class. 
	 */
	public static class HuffNode {
	    char myChar;
	    int myFrequency;
	    HuffNode myLeft, myRight;
	    
	    /**
	     * HuffNode constructor.
	     */
	    HuffNode(char theChar, int theFrequency) {
	    	this.myChar = theChar;
	    	this.myFrequency = theFrequency;
	    }
	    
	    /**
	     * HuffNode constructor.
	     */
	    HuffNode(int theFrequency) {
	    	this.myFrequency = theFrequency;
	    }
	    
	    /**
	     * Method takes in a Huffman Node and compares it to 
	     * 
	     * @param theNode
	     * @return -1 
	     */
	    public int compareTo(HuffNode theNode) {
	    	if (this.myFrequency < theNode.myFrequency) {
	    		return 1;
	    	} else {
	    		return -1; 
	    	}
	    }
	}
	
	
}
