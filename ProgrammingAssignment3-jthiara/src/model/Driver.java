/** 
 * TCSS 342 
 */

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class represents a driver and holds a main method to test the Anagram and HashTable.
 * 
 * @author jthiara 
 * @version Winter 2021-2022
 */

public class Driver {
	
	public static void main(String[] theArgs) throws FileNotFoundException { 
		
		File words = new File("C:\\Users\\jasht\\downloads\\words.txt");
		try {
			buildHashTable(words);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method will build our own custom HashTable.
	 * @param theWords
	 * @throws IOException
	 */
	public static void buildHashTable(File theWords) 
			throws IOException {
		
		HashTable myTable = new HashTable();
		
		Scanner reader = new Scanner(theWords);
		
		long startTime = System.currentTimeMillis(); // timer to track runtime
		
		while (reader.hasNext()) {
			String temp = reader.next(); 
			myTable.add(temp.toLowerCase());
		}
		long endTime = System.currentTimeMillis(); //second timer
		
		long myHashTableTime = endTime-startTime; // difference of time
		
		int collision1 = myTable.getCollision(); // get the number of collisions in creating table.
		
		reader.close();
		buildJavaTable(myTable, theWords, myHashTableTime, collision1);
		
	}
	
	/**
	 * Method will build a java HashTable.
	 * @param theTable
	 * @param theWords
	 * @param theTime
	 * @param collision1
	 * @throws IOException
	 */
	public static void buildJavaTable(HashTable theTable, File theWords, long theTime, int collision1) 
			throws IOException {
		
		HashTable javaTable = new HashTable();
		Scanner reader2 = new Scanner(theWords);
		
		long startTime2 = System.currentTimeMillis(); // track time
		
		
		// Iterate through the list of words and add to hash table.
		while (reader2.hasNext()) {
			String temp2 = reader2.next(); 
			javaTable.addJava(temp2.toLowerCase());
		}
		long endTime2 = System.currentTimeMillis(); // track time
		
		long javaTime = endTime2-startTime2; // track time
		int collision2 = javaTable.getCollision(); // get number of collisions in creating table
		
		reader2.close();
		buildOutputTable(theTable, theTime, javaTime, collision1, collision2);
	}
	
	/**
	 * Method will produce desired output to output.txt file. 
	 * @param theTable
	 * @param theTime
	 * @param javaTime
	 * @param myCollision
	 * @param javaCollision
	 * @throws IOException
	 */
	public static void buildOutputTable(HashTable theTable, long theTime, long javaTime, int myCollision, int javaCollision) throws IOException { 
		FileWriter writer = new FileWriter("C:\\Users\\jasht\\downloads\\output.txt", false);
		
		writer.write("--------------------------------------------------------------\n");
		writer.write("My HashTable          |            JavaTable\n");
		writer.write("--------------------------------------------------------------\n");
		writer.write("My Collisions: " + myCollision + "   | Java Collisions " + javaCollision + "\n");
		writer.write("--------------------------------------------------------------\n");
		writer.write("My RunTime " + theTime + " MS      | Java RunTime " + javaTime + " MS\n\n");
		
		buildOutput2(theTable, writer);
		writer.close();
	}
	
	/**
	 * Method will create the second part of output for output.txt
	 * @param theTable
	 * @param theWriter
	 * @throws IOException
	 */
	public static void buildOutput2(HashTable theTable, FileWriter theWriter) throws IOException { 
		
		File words = new File("C:\\Users\\jasht\\downloads\\input.txt");
		
		
		theWriter.write("-------------------------Output part2-------------------------\n");
		
		Scanner reader3 = new Scanner(words);
		
		// Iterates through list of input and search for anagrams
		while (reader3.hasNext()) {
			String input = reader3.next().toLowerCase();
			
			theWriter.write(input + " "); 
			
			int indexAnagram = theTable.search(input); 
			Anagram temp = theTable.getAnagrams(indexAnagram); // finds anagram of the word
			theWriter.write(temp + "\n");
			
		}
		reader3.close();
	}
}


