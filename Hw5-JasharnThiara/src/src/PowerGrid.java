/**
 * TCSS 343 B
 */

package src;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Class implements Kruskral's algorithm. A simple graph is created via text file and passes this graph to 
 * the Kruskral method. This method utilizes a priority queue and the Sets class to ensure the smallest edges are being 
 * added to the MST, without creating a cycle, until there are v - 1 edges added. 
 * 
 * @author Jasharn Thiara/ UW ID: jthiara
 * @version Spring 2022
 */

public class PowerGrid {

	/**
	 * String to represent a seperator in output.
	 */
	private static String seperator = "-------------------------------------------"
			+ "-----------------------------------------";
	
	/**
	 * This method takes a graph as an input and produces a
	 * set of edges that comprise a minimum spanning tree of the given graph.
	 * 
	 * @param graph
	 * @return Set e Minimum Spamming tree of graph. 
	 */
	public static Set<Edge> kruskal(SimpleGraph g) {
		
		//Priority Queue will be used to represent minheap and remove edges accordingly. 
		PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingDouble(e-> (double) e.getData()));
		//MST Set to be returned
		Set<Edge> MST = new HashSet<Edge>();
		
		//iterate through vertices and create set objects
		Iterator k;
		for (k = g.vertices(); k.hasNext(); ) {
			Vertex v = (Vertex) k.next();
			Sets.addSet((String) v.getName());
		}
		
		//iterate through edges and add to priorityQueue.
		Iterator i;
		System.out.println("\n"+ seperator + "\nEdge List (" + g.numEdges() + 
				" edges and " + g.numVertices() + " vertices)\n" + seperator);
		
		int counter2 = 0;
		for (i = g.edges(); i.hasNext(); ) {
			
			Edge e = (Edge)i.next();
			System.out.print(e.getFirstEndpoint().getName() + " -> " + e.getSecondEndpoint().getName() + " = " + e.getData() + "  |  ");
			counter2++;
			
			//statement to go to next line after each 5 edges that are listed (just for organization of table).
			if (counter2 % 5 == 0 || counter2 == g.numEdges()) {
				System.out.println();
			}
			//add edges to Priority Queue.
			minHeap.add(e);
		}
		System.out.println(seperator);
		
		// will add v-1 edges to our MST based on certain conditions.
		// utilizes the fact that vertexes with different roots within an uptree do not create cycles.
		int counter = 0;
		while (counter < g.numVertices() - 1) {
			Edge e = minHeap.remove();
			
			// Pass to find method to return root of each vertex in their respected sets.
			String root1 = Sets.find((String) e.getFirstEndpoint().getName());
			String root2 = Sets.find((String) e.getSecondEndpoint().getName());
		
			//if nodes do not share same root in respected sets, we add to MST.
			if (!root1.equals(root2)) {

				//Add to our MST set and union vertex's into one set
				MST.add(e);
				Sets.union(root1, root2);
				counter++;
			}
		}
		return MST;
	}
	
	/**
	 * Helper class to encompass uptree.
	 */
	public static class Sets {
		// Map D.S. used to link vertexes to the Root of their set and to the rank of the set they belong to.
		static HashMap<String, String> theRoot = new HashMap<>();
		static HashMap<String, Integer> theRank = new HashMap<>();
		
		public static void addSet(String v) { 
			theRoot.put(v, v);
			theRank.put(v, 0);
		}
		
		// Method will recursively call on itself until the vertex is the root. 
		// (This allows us to not have to update each vertexes root during a change in hierachy).
		public static String find(String v) {
			if (theRoot.get(v).equals(v)) {
				return v;
			} else {
				return find(theRoot.get(v));
			}
		}
		
		public static void union(String v, String w) {
			
			// change root of smaller rank vertex.
			if (theRank.get(v) > theRank.get(w)) {
				theRoot.replace(w, v);
			} else if (theRank.get(v) < theRank.get(w)) {
				theRoot.replace(v, w);
			} else {
				// Same rank case, either can be updated. Rank incremented.
				theRoot.replace(w, v);
				theRank.replace(v, theRank.get(v) + 1);
			}
		}
	}
	
	/**
	 * Let’s the user specify a graph file (you can use a method of the
	 * GraphInput class for this), calls the method kruskal for the specified graph as input, and outputs
	 * the set of edges and the total cost of the MST.
	 */
	public static void main(String[] args) {
		SimpleGraph g = new SimpleGraph();
		Set<Edge> MST; 
		Double totalLength = 0.0;
		
		//load graph
		GraphInput.LoadSimpleGraph(g, "graph2.txt");
		
		//Pass graph to kruskal method. 
		MST = kruskal(g);
	
		System.out.println("MST List ("+ MST.size() + " edges)\n" + seperator);
		
		int counter3 = 0;
		for (Edge e : MST) {
			System.out.print(e.getFirstEndpoint().getName() + " -> " + e.getSecondEndpoint().getName() + " = " + e.getData() + "  |  ");
			totalLength += (double) e.getData();
			
			counter3++;
			if (counter3 % 5 == 0 || counter3 == MST.size()) {
				System.out.println();
			}
		}
		System.out.println(seperator + "\nTotal length of MST = " + totalLength);
	}
}