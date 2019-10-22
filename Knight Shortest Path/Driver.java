
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//-------------------------------------------------
// Title: pathfinder class
// Author: Hakan Ahmet Tekin 
// ID: 30212357722 
// Section: 1 
// Assignment: 1 
// Description: Reads input from file and creates an 2-d array with it.
//				Then sends it to pathfinder class 
//------------------------------------------------- 

public class Driver {

	static char map[][]; // Array for holding chess board positions

	public static void main(String[] args) {
		File f = null;
		try {
			/// IMPORTANT: Command line argument must be without extension (.txt) below line
			/// adds the .txt itself
			// if the given name contains extension. than uncomment line 15 and comment line
			/// 14
			f = new File(args[0] + ".txt");
			// f = new File("file.txt");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Graph g = createGraphFromFile(f);

		// Actual code that finds shortest path. pathfinder is an abstract class with
		// static attributes and static methods
		pathfinder.findPath(g, map);
	}

	public static Graph createGraphFromFile(File f) {
		try {
			System.out.println("Trying to create map array");
			Scanner sc = new Scanner(f);
			int x = sc.nextInt();
			int y = sc.nextInt();
			Graph g = new Graph(x * y);
			map = new char[x][y];

			// Fill the board array with given input
			for (int i = 0; i < x; i++) {
				String line = sc.next();
				for (int k = 0; k < y; k++) {
					map[i][k] = line.charAt(k);
				}
			}
			return g;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
