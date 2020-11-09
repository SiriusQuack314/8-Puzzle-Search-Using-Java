public class Node {

	public Node parent;
	public int[][] matrix;
	
	// Number of misplaced tiles
	public int cost;
	
	// The number of moves so far
	public int level;
	
	public Node(int[][] matrix, Node parent, int cost, int level) {
		
		this.matrix = new int[matrix.length][];
		for (int i = 0; i < matrix.length; i++) {
			this.matrix[i] = matrix[i].clone();
		}
	
		this.parent = parent;
		this.cost = Integer.MAX_VALUE;
		this.level = level;
	}
	
	public int[][] getMatrix(){
		return matrix;
	}
}
