import java.util.Arrays;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) {
		
		int[][] initialOne = { {1, 4, 2}, {3, 0, 5}, {6, 7, 8}};
	//	int[][] initialTwo= { {1, 2, 5}, {3, 0, 8}, {6, 4, 7} };
	//	int[][] initialThree = { {7, 2, 4}, {5, 0, 6}, {8, 3, 1}};
		
		//create root node
		Node rootNode = new Node(initialOne, null,0,0);
		//call depth limited seacrch
		Node depLim = DepthLimited.findPath(rootNode);
		DepthLimited.printSolution(depLim);

	}

}
