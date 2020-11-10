import java.util.*;

public class AStarManhattan
{
	static final int goal[][] = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
	static PriorityQueue<Node> queue = new PriorityQueue<Node>(Comparator.comparing(Node::getHeuristicValue));
	
	public AStarManhattan(Node root)
	{
		queue.add(root);
		
		Node solution = null;
		
		while(!queue.isEmpty())
		{
			if(goalTest(queue.peek()))
			{
				solution = queue.poll();
				break;
			}
			
			expandNode(queue.poll());
			
		}
		
		if(solution != null)
		{
			printPath(solution);
		}
		else
		{
			System.out.println("No solution found.");
		}
	}
	
	public static void expandNode(Node node)
	{
		int[] loc = findValue(node.getMatrix(), 0);

		int[][] options = { { loc[0] - 1, loc[1] }, { loc[0] + 1, loc[1] }, { loc[0], loc[1] - 1 },
				{ loc[0], loc[1] + 1 } };

		for (int r = 0; r < options.length; r++)
		{
			if (!(options[r][0] < 0 || options[r][1] < 0 || options[r][0] > 2 || options[r][1] > 2))
			{
				Node child = new Node(swap(node.getMatrix(), options[r], loc), node, 0, node.level + 1);
				child.cost = sumOfManhattanDistances(child);
				if (!wasVisited(node, child))
				{
					queue.add(child);
				}
			}
		}
	}

	// method to check if goal state when new nodes are created
	public static boolean goalTest(Node node)
	{
		if (Arrays.deepEquals(node.getMatrix(), goal))
		{
			return true;
		}
		return false;
	}

	// displays the matrix to the console
	public static void displayMatrix(Node node)
	{
		int[][] matrix = node.getMatrix();
		for (int[] row : matrix)
			System.out.println(Arrays.toString(row));
	}

	// method that prints path to solution (only prints relevant nodes)
	public static void printPath(Node node)
	{
		if (node != null)
		{
			printPath(node.parent);
			System.out.println("\nLevel " + node.level);
			displayMatrix(node);
		}
	}

	public static int sumOfManhattanDistances(Node node)
	{
		int sum = 0;
		for (int x = 0; x < goal.length; x++)
		{
			for (int y = 0; y < goal[0].length; y++)
			{
				int[] loc = findValue(node.getMatrix(), goal[x][y]);
				sum += manhattanDistance(loc[0], loc[1], x, y);
			}
		}
		return sum;
	}

	public static int manhattanDistance(int x1, int y1, int x2, int y2)
	{
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	public static int[] findValue(int[][] matrix, int value)
	{
		int[] loc = { -1, -1 };

		for (int x = 0; x < matrix.length; x++)
		{
			for (int y = 0; y < matrix[0].length; y++)
			{
				if (matrix[x][y] == value)
				{
					loc[0] = x;
					loc[1] = y;
					return loc;
				}
			}
		}

		return loc;
	}

	public static int[][] swap(int[][] oldMatrix, int[] locA, int[] locB)
	{
		int[][] newMatrix = new int[oldMatrix.length][oldMatrix[0].length];
		for (int x = 0; x < newMatrix.length; x++)
		{
			for (int y = 0; y < newMatrix[0].length; y++)
			{
				newMatrix[x][y] = oldMatrix[x][y];
			}
		}

		int temp = newMatrix[locA[0]][locA[1]];
		newMatrix[locA[0]][locA[1]] = newMatrix[locB[0]][locB[1]];
		newMatrix[locB[0]][locB[1]] = temp;

		return newMatrix;
	}

	public static boolean wasVisited(Node node, Node child)
	{
		while (node != null)
		{
			if (Arrays.deepEquals(node.getMatrix(), child.getMatrix()))
				return true;
			node = node.parent;
		}
		return false;
	}
}
