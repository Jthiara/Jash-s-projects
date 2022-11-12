/** 
 * TCSS 342
 * 
 */

package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import model.HuffmanTree.HuffNode;

/**
 * Class represents Driver for RLE Class and LinkedList class. 
 * 
 * @author jthiara
 * @version winter 2021-2022
 */

public class Driver {
	HashMap<Character, String> bitMap = new HashMap<>(); // map to hold char characters
	public static void main(String[] theArgs) throws FileNotFoundException {
		
		
		File Input = new File("input.txt");
		try {
			buildTable(Input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void buildTable(File theInput) 
			throws IOException {
		
		Scanner reader = new Scanner(theInput);	
		
		String[] holder = new String[5];
		
		//Get input for frequency table 
		for (int i = 0; i < 5; i++) {
			if (reader.hasNextLine()) {
				holder[i] = reader.nextLine();
			} 
		}
		
		HashMap<Character, HuffNode> map = new HashMap<>();
		
		//this will create a map that hold a character and its created huffnode
		for (int i = 0; i < holder[0].length(); i++) {
			char ch = holder[0].charAt(i);
			
			if (map.containsKey(ch)) { // increments if key already exists
				map.get(ch).myFrequency++; 
			} else {
				HuffNode temp = new HuffNode(ch, 1); // creates new huff node and map key
				map.put(ch, temp);
			}
		}
		
		//pass our map to build huffman tree function
		HuffmanTree tree = new HuffmanTree();
		tree = buildHuffmanTree(map);
		
		//
		Set<Character> chars = map.keySet();
		
		//iterate through chars and find bit path for each character.
		for (char c: chars) {
			findBitCode(c, tree.rootNode);
		}
		
		reader.close();
		
	}
	
	public static HuffmanTree buildHuffmanTree(HashMap<Character, HuffNode> theMap) {
		PriorityQueue treeMaker = new PriorityQueue(20); //random size made
		HuffmanTree tree = new HuffmanTree();
		Set<Character> keys = theMap.keySet();
		
		//iterates through our characters and places into priority queue.
		for (char x: keys) {
			treeMaker.add(theMap.get(x));
		}
		
		while (treeMaker.currSize != 1) { //iterates up to last element in queue
			HuffNode node1 = treeMaker.removeNext(); //remove min twice
			HuffNode node2 = treeMaker.removeNext(); //remove min twice
			treeMaker.add(tree.add(node1, node2));  // combine these into huffman tree and place in queue
		}
		return tree;
	}
	
	public static String findBitCode(char c, HuffNode theRoot) {
		if (theRoot == null) {
			return;
		} else {
			
		}
	
	}
	
	public static void encode() {
		
	}
	
	public static void decode() {

	}
}
