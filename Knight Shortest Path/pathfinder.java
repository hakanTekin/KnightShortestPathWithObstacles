
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//-------------------------------------------------
// Title: pathfinder class
// Author: Hakan Ahmet Tekin 
// ID: 30212357722 
// Section: 1 
// Assignment: 1 
// Description: Finds the shortest path to gold. 
//------------------------------------------------- 

public abstract class pathfinder {

	static int knightLocation, goldLocation;
	static int[] parents; // Parent of the vertex
	static boolean[] marked; // Vertexes that we visited before
	static int[] distanceToKnight; // Distance of each vertex to the knights original position
	static char[][] map;
	static Graph g;
	static Queue<Integer> q = new LinkedList<Integer>();

	public static void findPath(Graph g, char[][] map) {

		int mapSize = map[0].length * map.length;

		// Initializing needed arrays
		parents = new int[mapSize];
		marked = new boolean[mapSize];
		distanceToKnight = new int[mapSize];

		for (int i = 0; i < mapSize; i++) {
			parents[i] = -1;
			marked[i] = false;
			distanceToKnight[i] = -1;
		}

		pathfinder.map = map;
		pathfinder.g = g;
		knightLocation = findKnight(map);
		goldLocation = findGold(map);
		q.add(knightLocation);

		while (!q.isEmpty()) {
			int nextVertex = q.remove();
			createEdgesFromVertex(nextVertex);
			marked[nextVertex] = true;
		}
		couldNotFindPath();

	}

	// This method finds the knights position and assigns it to the global attribute
	// knightLocation
	public static int findKnight(char[][] map) {
		int knightLocation = -1;
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 'K')
					knightLocation = i * map[0].length + j;
			}
		System.out.println("KnightLocation Index: " + knightLocation);
		return knightLocation;
	}

	// This method finds the golds position and assigns it to the global attribute
	// goldLocation
	public static int findGold(char[][] map) {
		int goldLocation = -1;
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 'G')
					goldLocation = i * map[0].length + j;
			}
		System.out.println("goldLocation Index: " + goldLocation);
		return goldLocation;
	}

	// Creates edges from the given vertex. If there are no trees and the vertex has
	// not been created(has no parent),
	// then create the new vertex and connect it to the current vertex(parent)
	public static void createEdgesFromVertex(int originVertex) {
		/// map.length gives row count
		/// map[].length gives column count

		int x = originVertex % map[0].length; // x is the horizontal axis
		int y = originVertex / map[0].length; // y is the vertical axis

		// if gold is found. track down its parents (which is our answer)
		if (map[y][x] == 'G')
			trackDownParents(originVertex);

		// The 8 possible moves knight can do from a location. If knight can move there,
		// than add an edge to that location and add that vertex to the queue to visit
		// in the future.
		// when the vertex is created, mark it so that no other vertex can connect with
		// it (because if multiple positions connect to the same vertex than previous
		// moves were meaningless)
		if (map[0].length > x + 2 && map.length > y + 1 && map[y + 1][x + 2] != 'T') {
			if (!marked[convertToOneDimensionalIndex(y + 1, x + 2)]) {
				marked[convertToOneDimensionalIndex(y + 1, x + 2)] = true;
				g.addEdge(originVertex, convertToOneDimensionalIndex(y + 1, x + 2));
				q.add(convertToOneDimensionalIndex(y + 1, x + 2));
				parents[convertToOneDimensionalIndex(y + 1, x + 2)] = originVertex;
			}
		}
		if (map[0].length > x + 1 && map.length > y + 2 && map[y + 2][x + 1] != 'T') {
			if (!marked[convertToOneDimensionalIndex(y + 2, x + 1)]) {
				marked[convertToOneDimensionalIndex(y + 2, x + 1)] = true;
				g.addEdge(originVertex, convertToOneDimensionalIndex(y + 2, x + 1));
				q.add(convertToOneDimensionalIndex(y + 2, x + 1));
				parents[convertToOneDimensionalIndex(y + 2, x + 1)] = originVertex;
			}
		}
		if (map[0].length > x + 2 && map.length > y - 1 && y - 1 >= 0 && map[y - 1][x + 2] != 'T') {
			if (!marked[convertToOneDimensionalIndex(y - 1, x + 2)]) {
				marked[convertToOneDimensionalIndex(y - 1, x + 2)] = true;
				g.addEdge(originVertex, convertToOneDimensionalIndex(y - 1, x + 2));
				q.add(convertToOneDimensionalIndex(y - 1, x + 2));
				parents[convertToOneDimensionalIndex(y - 1, x + 2)] = originVertex;
			}
		}
		if (map[0].length > x - 2 && x - 2 >= 0 && map.length > y + 1 && map[y + 1][x - 2] != 'T') {
			if (!marked[convertToOneDimensionalIndex(y + 1, x - 2)]) {
				marked[convertToOneDimensionalIndex(y + 1, x - 2)] = true;
				g.addEdge(originVertex, convertToOneDimensionalIndex(y + 1, x - 2));
				q.add(convertToOneDimensionalIndex(y + 1, x - 2));
				parents[convertToOneDimensionalIndex(y + 1, x - 2)] = originVertex;
			}
		}
		if (map[0].length > x - 1 && x - 1 >= 0 && map.length > y + 2 && map[y + 2][x - 1] != 'T') {
			if (!marked[convertToOneDimensionalIndex(y + 2, x - 1)]) {
				marked[convertToOneDimensionalIndex(y + 2, x - 1)] = true;
				g.addEdge(originVertex, convertToOneDimensionalIndex(y + 2, x - 1));
				q.add(convertToOneDimensionalIndex(y + 2, x - 1));
				parents[convertToOneDimensionalIndex(y + 2, x - 1)] = originVertex;
			}
		}
		if (map[0].length > x - 1 && x - 1 >= 0 && map.length > y - 2 && y - 2 >= 0 && map[y - 2][x - 1] != 'T') {
			if (!marked[convertToOneDimensionalIndex(y - 2, x - 1)]) {
				marked[convertToOneDimensionalIndex(y - 2, x - 1)] = true;
				g.addEdge(originVertex, convertToOneDimensionalIndex(y - 2, x - 1));
				q.add(convertToOneDimensionalIndex(y - 2, x - 1));
				parents[convertToOneDimensionalIndex(y - 2, x - 1)] = originVertex;
			}
		}
		if (map[0].length > x - 2 && x - 2 >= 0 && map.length > y - 1 && y - 1 >= 0 && map[y - 1][x - 2] != 'T') {
			if (!marked[convertToOneDimensionalIndex(y - 1, x - 2)]) {
				marked[convertToOneDimensionalIndex(y - 1, x - 2)] = true;
				g.addEdge(originVertex, convertToOneDimensionalIndex(y - 1, x - 2));
				q.add(convertToOneDimensionalIndex(y - 1, x - 2));
				parents[convertToOneDimensionalIndex(y - 1, x - 2)] = originVertex;
			}
		}
		if (map[0].length > x + 1 && map.length > y - 2 && y - 2 >= 0 && map[y - 2][x + 1] != 'T') {
			if (!marked[convertToOneDimensionalIndex(y - 2, x + 1)]) {
				marked[convertToOneDimensionalIndex(y - 2, x + 1)] = true;
				g.addEdge(originVertex, convertToOneDimensionalIndex(y - 2, x + 1));
				q.add(convertToOneDimensionalIndex(y - 2, x + 1));
				parents[convertToOneDimensionalIndex(y - 2, x + 1)] = originVertex;
			}
		}
	}

	public static int convertToOneDimensionalIndex(int y, int x) {
		return y * map[0].length + x;
	}

	public static int[] convertToTwoDimensionalIndex(int i) {
		int[] xy = new int[2];
		xy[0] = i % map.length;
		xy[1] = i / map.length;
		return xy;
	}

	// parents array hold each vertex's parents. When we have the answer, we can
	// just look for previous vertexes that create a path from knight to the gold.
	public static void trackDownParents(int resultVertex) {
		int pathIndex = resultVertex;
		int distance = 0;
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		while (pathIndex != knightLocation) {
			distance++;
			resultList.add(pathIndex);
			pathIndex = parents[pathIndex];
		}

		System.out.println(distance + " steps");
		System.out.print("c" + (convertToTwoDimensionalIndex(knightLocation)[1] + 1) + ","
				+ (convertToTwoDimensionalIndex(knightLocation)[0] + 1) + " to " + "c"
				+ (convertToTwoDimensionalIndex(goldLocation)[1] + 1) + ","
				+ (convertToTwoDimensionalIndex(goldLocation)[0] + 1) + " : ");
		for (int i = resultList.size() - 1; i >= 0; i--) {
			int currentPosition = resultList.get(i);
			int curX = (convertToTwoDimensionalIndex(currentPosition)[1] + 1),
					curY = (convertToTwoDimensionalIndex(currentPosition)[0] + 1);
			System.out.print("c" + curX + "," + curY);
			if (i != 0)
				System.out.print(" -> ");
		}
		System.exit(0);
	}

	public static void couldNotFindPath() {
		System.out.println("No path to the target.");
	}
}
