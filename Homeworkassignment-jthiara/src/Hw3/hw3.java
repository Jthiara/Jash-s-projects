package Hw3;

import java.util.Arrays;
import java.util.Random;

/**
 * TCSS 360
 * 
 * Class will find the Maximal between intervals. 
 */


public class hw3 {
	
	/**
	 * @author Jthiara / Jasharn Thiara
	 * @version Spring 2022
	 */

	/**
	 * Method will find maximal numbers in a given set. This will be done by using transform and conquer and sorting the set first.
	 * Algo also assumes in intervals (x1, y1) x1 <= y1, since intervals cannot be negative ex. (0, -5). 
	 * 
	 * @param theTemp
	 * @return integer of maximal numbers
	 */
	public static int findMaximalNumbers(Interval[] theTemp) {
		int mostFound = 1;
		int counter = 1;
		double high; // variables to keep track of indexes for our intersections
		double low;
		
		Arrays.sort(theTemp);
		/**
		 * now that we have sorted the array according to x values. we can make a single pass and count the maximals.
		 */
		if (theTemp.length == 0) { // base case
			return mostFound;
		} else {
			low = theTemp[0].myX; // initialize intersection boundaries
			high = theTemp[0].myY;
			theTemp[0].counted = true;
			
			for (int i = 1; i < theTemp.length; i++) {
				if (high < theTemp[i].myX) { // case where two intervals do not share any common points
					low = theTemp[i].myX;    // reset boundaries
					high = theTemp[i].myY;
					counter = 1; //reset counter 
				} else {
					theTemp[i].counted = true; // statement to ensure program is working correctly.
					if (theTemp[i].myX > low) {
						low = theTemp[i].myX;
					}
					if (theTemp[i].myY < high) {
						high = theTemp[i].myY;
					}
					counter++; // increment count of intervals with common points
					if (counter > mostFound) {
						mostFound = counter;
					}
				}
			}
		}
		return mostFound;
	}
	

	/**
	 * Interval class
	 * 
	 * @author jasht
	 *
	 */
	public static class Interval implements Comparable<Interval>{
		public double myX;
		public double myY;
		public boolean counted = false;
		
		public Interval(double theX, double theY) {
			myX = theX;
			myY = theY;
		}

		//Interval object will be compared according to x values. 
		public int compareTo(Interval other) {
			if (myX < other.myX) {
				return -1;
			} else if (myX > other.myX) {
				return 1;
			} else if ((myX == other.myX) && (myY > other.myY)){ //if X coordinates are same, Y will be looked at.
				return 1;
			} else if ((myX == other.myX) && (myY < other.myY)){
				return -1;
			} else {
				return 0; 
			}
		}
		
		public String toString() {
			return "(" + myX + ", " +myY + ")" + " Has common Point: " + counted;
		}
	}
		
	public static void main(final String[] theArgs) {
			 Interval[] tempArr = new Interval[3];
			 tempArr[0] = new Interval (1,3);
			 tempArr[1] = new Interval(2,5);
			 tempArr[2] = new Interval (4,9);
			
			 
			 
			 Arrays.sort(tempArr);
			 
			 int maximal = findMaximalNumbers(tempArr);
			 
			 //Prints each Interval and whether or not it shared a common point
			 for (int i = 0; i < tempArr.length; i++) {
				 System.out.println(tempArr[i]);
			 }
			 System.out.println("Maximal Number: " + maximal);
	}
	
			 

}
