
/* Team members
 * Aarti Nimhan - 801098198
 * Uma Sai Madhuri Jetty - 801101049
 * Sahithi Priya Gutta - 801098589
 * 
 * This class implements Hill Climbing with Steepest Ascent.
 */
import java.util.ArrayList;
import java.util.List;

public class SteepestAscent {

	public int numberOfQueens = 0;
	public CommonUtils commonUtils = new CommonUtils();
	public boolean isSuccessful = false;
	public boolean isFail = false;
	List<Node> possibleSuccessors = new ArrayList<Node>();
	Node bestSuccessor = new Node();
	int regularMoves = 0;
	int totalSuccessfulMoves = 0;
	int totalFailMoves = 0;
	int totalSuccessfulIterations = 0;
	int totalFailedIterations = 0;
	int[] result = new int[4];

	/**
	 * This method implements Hill Climbing with Steepest Ascent.
	 * 
	 * @param numberOfQueens the total number of queens on the board.
	 * @param printOutput    True if board states are to be printed. False if no
	 *                       board states are printed.
	 * @return returns an integer array with moves required for success and failure
	 *         also includes success and failure iterations count.
	 */
	public int[] process(int numberOfQueens, boolean printOutput) {
		this.numberOfQueens = numberOfQueens;

		// Generate a random state with one queen in every column
		Node currentNode = commonUtils.generateRandomState(numberOfQueens);

		// calculate heuristic
		commonUtils.calculateHeuristic(currentNode);
		if (printOutput) {
			System.out.println("----Initial State-----");
			commonUtils.printNode(currentNode);
		}
		while (!isSuccessful && !isFail) {

			// get possible successors
			possibleSuccessors = commonUtils.findPossibleSuccessors(currentNode);
			// calculate heuristic of possible successors
			for (int i = 0; i < possibleSuccessors.size(); i++) {
				commonUtils.calculateHeuristic(possibleSuccessors.get(i));
			}

			// select best successor if hcost is less than current hcost
			bestSuccessor = commonUtils.bestSuccessor(possibleSuccessors);
			if (currentNode.gethCost() == 0) {
				totalSuccessfulMoves = +regularMoves;
				totalSuccessfulIterations++;
				isSuccessful = true;
				if (printOutput) {
					System.out.println("----Goal State-----");
					commonUtils.printNode(currentNode);
				}
			} else if (bestSuccessor.gethCost() < currentNode.gethCost()) {
				// if yes update current Node with best Successor
				currentNode = bestSuccessor;
				regularMoves++;
			} else {
				totalFailedIterations++;
				totalFailMoves = +regularMoves;
				isFail = true;
				if (printOutput) {
					System.out.println("----Shoulder/Local Minima State-----");
					commonUtils.printNode(currentNode);
				}
			}
		}

		result[0] = totalSuccessfulMoves;
		result[1] = totalSuccessfulIterations;
		result[2] = totalFailMoves;
		result[3] = totalFailedIterations;

		return result;

	}
}
