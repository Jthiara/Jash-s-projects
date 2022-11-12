/** 
 * TCSS 342 
 */

package model;
import model.HuffmanTree.HuffNode;

/**
 * Class Implements an Array Heap (min).
 * *
 * @author jthiara
 * @version Winter 2021-2022
 */

// Notes:
// heaps are complete and therefore balanced as well.. left adjusted... we are using min heap? (min at root)
// reorder to maintain property of min heap	
// Parent only needs to be less than or equal to its children. ht =log2(n) ht= max depth/level

public class ArrayHeap {
	
	private HuffNode huffArr[];
	
	public int currSize = -1; //flag for empty array
	 
	/**
	 * Constructor.
	 */
	public ArrayHeap(int theSize) { 
		this.huffArr = new HuffNode[theSize];
	}
	
	/**
	 * Takes a Huffman Tree node as parameter and adds it to to the ArrayHeap
	 * and reorders the heap (heapify).
	 * @param HuffmanTreeNode theNode
	 */
	public void add(HuffNode theNode) {
		if (currSize < 0) { //empty array case
			currSize = 0;
			huffArr[currSize] = theNode;
		} else {                 // not empty case
			currSize++;
			huffArr[currSize] = theNode;
			
			HuffNode childNode = theNode; //temp variables
			HuffNode parentNode = huffArr[(currSize - 1) / 2];
			int temp = currSize;
			
			//if the child node has priority over parent then they will be swapped
			while (childNode.compareTo(parentNode) == 1) {
				huffArr[(temp - 1) / 2] = childNode;
				huffArr[temp] = parentNode;
				temp = (temp - 1) / 2;
				childNode = huffArr[temp]; 
				parentNode = huffArr[(temp - 1) / 2];
			}
		}
	}
	
	/**
	 * Removes and returns the highest priority Huffman Tree Node (root node) from the ArrayHeap
	 * and reorders the heap (heapify)
	 * 
	 * @return HuffmanTreeNode theNode
	 */
	public HuffNode removeMin() {
		HuffNode temp;
		
		if (currSize == -1) { //cannot remove from empty list
			throw new NullPointerException();
		} else if (currSize == 0) { // only one element case
			 temp = huffArr[0];
			huffArr[0] = null;
		} else {                   // more than one element case
			temp = huffArr[0];
			huffArr[0] = huffArr[currSize];
			huffArr[currSize] = null;
			currSize--;
			heapify(); //reorder if necessary.
		}
		return temp;
	}
	
	public void heapify() {
		int tempSize = 0; //start at root node
		
		//logic here is to loop as long as some child has higher priority
		while (huffArr[tempSize].compareTo(huffArr[tempSize * 2 + 1]) == 1
				|| huffArr[tempSize].compareTo(huffArr[tempSize * 2 + 2]) == 1 ) { 
			
			//case where both left and right child are higher priority than parent
			if (huffArr[tempSize].compareTo(huffArr[tempSize * 2 + 1]) == 1
					&& huffArr[tempSize].compareTo(huffArr[tempSize * 2 + 2]) == 1) {
				if (huffArr[tempSize * 2 + 1].compareTo(huffArr[tempSize * 2  + 2]) == 1) { // if left child is higher priority than right
					HuffNode temp = huffArr[tempSize];
					huffArr[tempSize] = huffArr[tempSize * 2 + 1];
					huffArr[tempSize * 2 + 1] = temp;
					tempSize = tempSize * 2 + 1; // update current node
				} else {
					HuffNode temp = huffArr[tempSize];
					huffArr[tempSize] = huffArr[tempSize * 2 + 2]; // right child is higher priority then left.
					huffArr[tempSize * 2 + 2] = temp;
					tempSize = tempSize * 2 + 2; //update current node
				}
				
			//left child case
			} else if (huffArr[tempSize].compareTo(huffArr[tempSize * 2 + 1]) == 1) { 
				HuffNode temp = huffArr[tempSize];
				huffArr[tempSize] = huffArr[tempSize * 2 + 1];
				huffArr[tempSize * 2 + 1] = temp;
				tempSize = tempSize * 2 + 1; //update current node
				
			// right case
			} else {
				HuffNode temp = huffArr[tempSize];
				huffArr[tempSize] = huffArr[tempSize * 2 + 2];
				huffArr[tempSize * 2 + 2] = temp;
				tempSize = tempSize * 2 + 2;
			}
		}
	}
	
	//just for testing
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < this.currSize; i++) {
			sb.append((huffArr[i].myChar + ", "));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		ArrayHeap heap = new ArrayHeap(10);
		heap.add(new HuffNode('a', 1));
		heap.add(new HuffNode('b', 2));
		heap.add(new HuffNode('c', 4));
		heap.add(new HuffNode('f', 5));
		heap.add(new HuffNode('g', 7));
		System.out.println(heap.currSize);
		System.out.println(heap.toString());
	}
}
