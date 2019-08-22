/* Team members
 * Aarti Nimhan - 801098198
 * Uma Sai Madhuri Jetty - 801101049
 * Sahithi Priya Gutta - 801098589
 * 
 * This class is a node class which consists of the unique state of the puzzle.
 * Along with the state the Node also contains fields like hcost => the heuristic cost of the current state that is the sum of the number of attacks each queen faces. 
 */
public class Node {
	private int[][] board;
	private int hCost;

	public int[][] getboard() {
		return board;
	}

	public void setboard(int[][] board) {
		this.board = board;
	}

	public int gethCost() {
		return hCost;
	}

	public void sethCost(int hCost) {
		this.hCost = hCost;
	}
}
