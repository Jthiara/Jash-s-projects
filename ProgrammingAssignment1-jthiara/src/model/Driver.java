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
import java.util.Scanner;

/**
 * Class represents Driver for RLE Class and LinkedList class. 
 * 
 * @author jthiara
 * @version winter 2021-2022
 */

public class Driver {

	public static void main(String[] theArgs) throws FileNotFoundException { 
		
		File Input = new File("input.txt");
		try {
			test1(Input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests encoding.
	 */
	public static void test1(File theInput) 
		throws IOException {
		
		Scanner reader = new Scanner(theInput);	
		BufferedWriter writer = new BufferedWriter(new FileWriter("Output3.txt"));
		
		if (reader.hasNextLine()) {
			reader.nextLine(); // first line not necessary
		}
		
		// input 1
		writer.write("Test 1: Output for Encoding\n");
		
		// temporary list to convert string version of linked list into a real linked list so we can use the encode method.
		LinkedList<String> tempList = new LinkedList<String>();
		int count = 0;
		if (reader.hasNext()) { // convert input to LinkedList.
			String input = reader.next(); // temp string variable to hold input line
			while (count < input.length()) {
				tempList.add("" + input.charAt(count));
				count++;
			}
		}
		
		//create output.
		writer.write("[" + tempList.toString() + ": " +tempList.size() + "]\n");
		writer.write("[" + RLE.encode(tempList) + ":" + RLE.encode(tempList).length() + "]" + " ["
				+ (double) tempList.size()/RLE.encode(tempList).length() + "]\n\n");
		
		//input 2
		int count2 = 0;
		LinkedList<String> tempList2 = new LinkedList<String>(); // temp list to pass into encode method.
		if (reader.hasNext()) {
			String input2 = reader.next(); // temp string variable to hold input line
			while (count2 < input2.length()) {
				tempList2.add("" + input2.charAt(count2));
				count2++;
			}
		}
		
		//create output.
	    writer.write("[" + tempList2.toString() + ": " +tempList2.size() + "]\n");
		writer.write("[" + RLE.encode(tempList2) + ":" + RLE.encode(tempList2).length() + "]" + " ["
				+ (double) tempList2.size()/RLE.encode(tempList2).length() + "]\n\n");
		
		//input 3
		int count3 = 0;
		LinkedList<String> tempList3 = new LinkedList<String>(); //temp list
		String input3 = reader.next() + " " + reader.next() + " " 
				+ reader.next() + " " + reader.next() + " " 
				+ reader.next() + " " + reader.next(); //multiple reader.next calls are made to concatenate input into one string.
		
		while (count3 < input3.length()) { // convert string into linked list.
				tempList3.add("" + input3.charAt(count3));
				count3++;
		}
			
		//create output.
		writer.write("[" + tempList3.toString() + ": " +tempList3.size() + "]\n");
		writer.write("[" + RLE.encode(tempList3) + ":" + RLE.encode(tempList3).length() + "]" + " ["
				+ (double) tempList3.size()/RLE.encode(tempList3).length() + "]\n\n");
		
		// passes reader and writer to test 2 method to keep track of where we are in both input and output file. 
		test2(reader, writer);
	}
	
	/**
	 * Tests decoding.
	 * @throws IOException 
	 */
	public static void test2(Scanner theReader, BufferedWriter theWriter) throws IOException {
		if (theReader.hasNextLine()) {
			theReader.nextLine(); // first line not necessary.
		}
		if (theReader.hasNextLine()) {
			theReader.nextLine(); // cycle through to input.
		}
		if (theReader.hasNextLine()) {
			theReader.nextLine(); // cycle through to input.
		}
		
	    theWriter.write("Test 2: Output for Decoding\n");
	    
	    //input 1
		if (theReader.hasNext()) { // method converts input to string to pass to our decode method.
			String holder = theReader.next();
			theWriter.write(RLE.decode(holder)+ "\n");
		}
		
		//input 2
		if (theReader.hasNext()) {
			String holder2 = theReader.next();
			theWriter.write(RLE.decode(holder2)+ "\n");
		}
		
		//input 3
		if (theReader.hasNext()) {
			String holder3 = theReader.next();
			theWriter.write(RLE.decode(holder3)+ "\n\n");
		}
		
		test3(theReader, theWriter); //passes reader and writer to test3 method.
		
	}
	
	/**
	 * Tests equality.
	 * @throws IOException 
	 */
	public static void test3(Scanner theReader, BufferedWriter theWriter) throws IOException {
		String encoded = "";
		String encoded2 = "";
		if (theReader.hasNextLine()) {
			theReader.nextLine(); // skip this line.
		}
		
		if (theReader.hasNextLine()) {
			theReader.nextLine(); // skip this line.
		}
		
		theWriter.write("Test 3: Output for Equality\n");
		if (theReader.hasNext()) {
			encoded = theReader.next();
		}
		
		if (theReader.hasNext()) {
			encoded2 = theReader.next();
		}
		
		//compares the encoded input using the equals method and returns result of comparison.
		String result = String.valueOf(RLE.equals(encoded, encoded2));
		theWriter.write("["+ encoded + "] " + "[" + encoded2 + "] [" + result + "]\n");
		
		//input 2
		String encoded3 = "";
		String encoded4 = "";
		if (theReader.hasNext()) {
			encoded3 = theReader.next();
		}
		
		if (theReader.hasNext()) {
			encoded4 = theReader.next();
		}
		
		String result2 = String.valueOf(RLE.equals(encoded3, encoded4));
		theWriter.write("["+ encoded3 + "] " + "[" + encoded4 + "] [" + result2 + "]\n");
		
		//input 3
		String encoded5 = "";
		if (theReader.hasNext()) { // only one input available, other input passed in will be null by default. 
			encoded5 = theReader.next();
		}
		
		String result3 = String.valueOf(RLE.equals(encoded5, null));
		theWriter.write("["+ encoded5 + "] " + "[null] [" + result3 + "]\n");
		
		//input 2
		String encoded6 = "";
		String encoded7 = "";
		if (theReader.hasNext()) {
			encoded6 = theReader.next();
		}
				
		if (theReader.hasNext()) {
			encoded7 = theReader.next();
		}
		String result4 = String.valueOf(RLE.equals(encoded6, encoded7)); //compare and produce output.
		theWriter.write("["+ encoded6 + "] " + "[" + encoded7 + "] [" + result4 + "]\n");
		
		theWriter.close();
		theReader.close();
	}

	
	
}
