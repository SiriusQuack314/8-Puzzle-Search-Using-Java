import java.util.Arrays;
import java.util.Scanner;

public class Main
{

	final static int[][] startingState1 = { { 1, 4, 2 }, { 3, 0, 5 }, { 6, 7, 8 } };
	final static int[][] startingState2 = { { 1, 2, 5 }, { 3, 0, 8 }, { 6, 4, 7 } };
	final static int[][] startingState3 = { { 7, 2, 4 }, { 5, 0, 6 }, { 8, 3, 1 } };

	public static void main(String[] args)
	{

		Scanner input = new Scanner(System.in);

		System.out.println("Select a search algorithm:");
		System.out.println("--------------------------");
		System.out.println("1) Breadth-first search");
		System.out.println("2) Depth-limited search");
		System.out.println("3) Iterative deepening to a depth of 10");
		System.out.println("4) A* with h(n) = number of misplaced tiles");
		System.out.println("5) A* with h(n) = total Manhattan distance");
		int algorithm = input.nextInt();

		if (algorithm < 1 || algorithm > 5)
		{
			System.err.println("Invalid input. Please select a number from 1-5");
			System.exit(1);
		}

		System.out.println("\nSelect initial state:");
		System.out.println("---------------------");
		System.out.println("1)\t\t2)\t\t3)");
		System.out.println("   " + Arrays.toString(startingState1[0]) + "\t   " + Arrays.toString(startingState2[0])
				+ "\t   " + Arrays.toString(startingState3[0]));
		System.out.println("   " + Arrays.toString(startingState1[1]) + "\t   " + Arrays.toString(startingState2[1])
				+ "\t   " + Arrays.toString(startingState3[1]));
		System.out.println("   " + Arrays.toString(startingState1[2]) + "\t   " + Arrays.toString(startingState2[2])
				+ "\t   " + Arrays.toString(startingState3[2]));

		int initialState = input.nextInt();

		if (initialState < 1 || initialState > 3)
		{
			System.err.println("Invalid input. Please select a number from 1-3");
			System.exit(1);
		}

		if (algorithm == 1) // Breadth first search
		{
			Node rootNode = null;

			switch (initialState)
			{
				case 1:
					rootNode = new Node(startingState1, null, 0, 0);
					break;
				case 2:
					rootNode = new Node(startingState2, null, 0, 0);
					break;
				case 3:
					rootNode = new Node(startingState3, null, 0, 0);
					break;
			}

			// CHANGE TO BREADTH FIRST SEARCH
			Node bfs = BreadthFirst.pathFinder(rootNode);
			BreadthFirst.printSolution(bfs);
		}
		else if (algorithm == 2) // Depth limited search
		{
			Node rootNode = null;

			switch (initialState)
			{
				case 1:
					rootNode = new Node(startingState1, null, 0, 0);
					break;
				case 2:
					rootNode = new Node(startingState2, null, 0, 0);
					break;
				case 3:
					rootNode = new Node(startingState3, null, 0, 0);
					break;
			}

			Node depLim = DepthLimited.findPath(rootNode);
			DepthLimited.printSolution(depLim);
		}
		else if (algorithm == 3) // Iterative deepening
		{
			Node rootNode = null;

			switch (initialState)
			{
				case 1:
					rootNode = new Node(startingState1, null, 0, 0);
					break;
				case 2:
					rootNode = new Node(startingState2, null, 0, 0);
					break;
				case 3:
					rootNode = new Node(startingState3, null, 0, 0);
					break;
			}
			// CHANGE TO ITERATIVE DEEPENING
			Node iterDeep = IterativeDeepening.findPath(rootNode);
			IterativeDeepening.printSolution(iterDeep);
		}
		else if (algorithm == 4) // A* with misplaced tiles
		{
			Node rootNode = null;

			switch (initialState)
			{
				case 1:
					rootNode = new Node(startingState1, null, 0, 0);
					break;
				case 2:
					rootNode = new Node(startingState2, null, 0, 0);
					break;
				case 3:
					rootNode = new Node(startingState3, null, 0, 0);
					break;
			}

			AStarMisplacedTiles test = new AStarMisplacedTiles(rootNode);
		}
		else if (algorithm == 5) // A* with Manhattan distance
		{
			Node rootNode = null;

			switch (initialState)
			{
				case 1:
					rootNode = new Node(startingState1, null, 0, 0);
					break;
				case 2:
					rootNode = new Node(startingState2, null, 0, 0);
					break;
				case 3:
					rootNode = new Node(startingState3, null, 0, 0);
					break;
			}

			AStarManhattan test = new AStarManhattan(rootNode);
		}

	}

}
