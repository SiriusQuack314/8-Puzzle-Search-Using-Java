import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class BreadthFirst
{

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

	public static List<Node> nextStates(Node currentNode)
	{
		int[][] parentNode = currentNode.matrix;
		Pair blankSpace = findZero(parentNode);
		Pair first = new Pair(blankSpace.x - 1, blankSpace.y);
		Pair second = new Pair(blankSpace.x + 1, blankSpace.y);
		Pair third = new Pair(blankSpace.x, blankSpace.y - 1);
		Pair fourth = new Pair(blankSpace.x, blankSpace.y + 1);

		List<Pair> list = new ArrayList<>();
		list.add(first);
		list.add(second);
		list.add(third);
		list.add(fourth);

		List<Node> result = list.stream().filter(x -> x.x >= 0 && x.x <= 2 && x.y >= 0 && x.y <= 2).map(pair -> {
			int[][] childNode = swap(parentNode, blankSpace.x, blankSpace.y, pair.x, pair.y);
			Node node = null;
			if (!isVisited(currentNode, childNode))
			{
				node = new Node(childNode, currentNode, 0, currentNode.level + 1);
			}
			return node;
		}).filter(x -> x != null).collect(Collectors.toList());
		return result;
	}

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

	public static Node pathFinder(Node node)
	{
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);
		Node solution = null;
		while (!queue.isEmpty())
		{
			Node nodeNow = queue.poll();
			int[][] currentNode = nodeNow.matrix;

			if (goalTest(currentNode))
			{
				solution = nodeNow;
				break;
			}
			
			List<Node> children = nextStates(nodeNow);

			for(Node c : children)
			{
				queue.add(c);
			}
		
		}
		return solution;

	}

	public static boolean goalTest(int[][] state)
	{
		int[][] goal = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
		if (Arrays.deepEquals(state, goal))
		{
			return true;
		}
		return false;
	}

	public static void printPath(Node last)
	{
		if (last != null)
		{
			printPath(last.parent);
			System.out.println("\nLevel " + last.level);
			print2D(last.matrix);
		}
	}

	public static void print2D(int mat[][])
	{

		for (int[] row : mat)
			System.out.println(Arrays.toString(row));
	}

	public static void printSolution(Node node)
	{
		if (node != null)
		{
			printPath(node);
		}
		else
		{
			System.out.println("no solution");
		}
	}
}
