import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class IterativeDeepening
{
	// used to find the 0 coordinates on the board as x,y
	private static class Pair
	{
		public final int x;
		public final int y;

		public Pair(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}

	// finds 0 coordinates on the board
	public static Pair findZero(int[][] ofNode)
	{
		for (int i = 0; i < ofNode.length; i++)
		{
			for (int j = 0; j < ofNode.length; j++)
			{
				if (ofNode[i][j] == 0)
				{
					return new Pair(i, j);
				}
			}
		}
		return null;
	}

	// method to create child nodes
	public static List<Node> nextStates(Node currentNode)
	{
		int[][] parentNode = currentNode.matrix;
		Pair zeroLocation = findZero(parentNode);
		// coordinates for swaping
		Pair optionOne = new Pair(zeroLocation.x - 1, zeroLocation.y);
		Pair optionTwo = new Pair(zeroLocation.x + 1, zeroLocation.y);
		Pair optionThree = new Pair(zeroLocation.x, zeroLocation.y - 1);
		Pair optionFour = new Pair(zeroLocation.x, zeroLocation.y + 1);
		// put coordinates in a list
		List<Pair> list = new ArrayList<>();
		list.add(optionOne);
		list.add(optionTwo);
		list.add(optionThree);
		list.add(optionFour);
		// takes list of new boards and checks which swaps are legal by checking limits
		// of board
		// executes swap and checks is new board was already visited
		// whatever nodes are left are collected into a list 'result'
		List<Node> result = list.stream().filter(x -> x.x >= 0 && x.x <= 2 && x.y >= 0 && x.y <= 2).map(pair -> {
			int[][] childNode = swap(parentNode, zeroLocation.x, zeroLocation.y, pair.x, pair.y);
			Node node = null;
			if (!isVisited(currentNode, childNode))
			{
				node = new Node(childNode, currentNode, 0, currentNode.level + 1);
			}
			return node;
		}).filter(x -> x != null).collect(Collectors.toList());
		return result;
	}

	// checks if a state was visited to prevent looping
	public static boolean isVisited(Node node1, int[][] child)
	{
		Node node = node1;
		while (node != null)
		{
			if (Arrays.deepEquals(node.matrix, child))
				return true;
			node = node.parent;
		}
		return false;
	}

	// method to swap tiles
	public static int[][] swap(int[][] oldState, int a, int b, int a1, int b1)
	{
		int[][] state = new int[oldState.length][];
		for (int i = 0; i < oldState.length; i++)
			state[i] = oldState[i].clone();
		int temp = state[a][b];
		state[a][b] = state[a1][b1];
		state[a1][b1] = temp;
		return state;
	}


	public static Node findPath(Node node, int depth)
	{
		int[][] currentNode = node.matrix;
		if (goalTest(currentNode))
			return node;
		else if (node.level > depth)
			return null;
		else
		{
			List<Node> children = nextStates(node);
			for (Node child : children)
			{
				Node resultTry = findPath(child, depth);
				if (resultTry != null)
				{
					return resultTry;
				}
			}
			return null;
		}

	}

	public static Node findPath(Node node)
	{
		Node solution = null;
		
		for (int i = 1; i <= 10; i++)
		{
			solution = findPath(node, i);
			if(solution != null)
			{
				break;
			}
		}
		return solution;

	}

	// method to check if goal state when new nodes are created
	public static boolean goalTest(int[][] state)
	{
		int[][] goal = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
		if (Arrays.deepEquals(state, goal))
		{
			return true;
		}
		return false;
	}

	// method that prints path to solution (only prints relevant nodes)
	public static void printPath(Node last)
	{
		if (last != null)
		{
			printPath(last.parent);
			System.out.println("\nLevel " + last.level);
			print2D(last.matrix);
		}
	}

	// converts the array to a 2D array to display as board
	public static void print2D(int mat[][])
	{
		// loop through all rows
		for (int[] row : mat)
			System.out.println(Arrays.toString(row));
	}

	// method that prints the solution
	// prints 'no solution to initial state in depth 10' if no solution was found
	public static void printSolution(Node node)
	{
		if (node != null)
		{
			printPath(node);
		}
		else
		{
			System.out.println("no solution to initial state in depth 10");
		}
	}
}